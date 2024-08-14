/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package card_supplier;

import card.Card;
import component.GenericAddFrame;
import component.GenericQueryFrame;
import java.util.LinkedList;
import java.util.Optional;
import javax.swing.JPanel;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.util.Vector;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.Pair;
import util.SmartConnection;
import record.Record;
import util.EntityField;
import util.functional.DatabaseErrorProneFunction;



/**
 * This class is intended to work with the 
 * {@link components.GenericQueryFrame GenericQueryFrame}. 
 * <br>
 * The task of this class is to generate "Cards" (i. e., JPanel's) for 
 * the given <code>RecordType</code>, following a series of constraints
 * such as a <code>queryLimit</code>, <code>orderBy</code> and 
 * <code>search</code> parameters, which should be ultimately provided 
 * by the user; internally, it contains the list of the fields of the entity 
 * and also provides methods to query the database
 * 
 * @author Edgar
 * @param <RecordType>
 * @param <CardType>
 */
public abstract class CardSupplier<RecordType extends Record, CardType extends Card<RecordType>> {
    protected LinkedList<EntityField> fields;
    
    protected Optional<Integer> queryLimit;
    protected Optional<EntityField> orderBy;
    protected Optional<Pair<EntityField, String>> search;
    
    protected int cardBatchLimit;
    protected Optional<ResetListener> resetListener;
    
    /**
     * This field can only accurately say
     * if the supplier is empty; it CAN NOT say
     * whether it is populated or not
     */
    protected boolean isDepleted;
    
    protected SmartConnection smartConnection;
    protected Optional<ResultSet> query = Optional.empty();
    
    DatabaseErrorProneFunction<Optional<RecordType>, GenericAddFrame> addFrameFunction;
    
    @FunctionalInterface
    public interface ResetListener {
        public void call(
                Optional<Integer> queryLimit, 
                Optional<EntityField> orderBy,
                Optional<Pair<EntityField, String>> search
        );
    }

    public CardSupplier(
            LinkedList<EntityField> fields, 
            Optional<Integer> queryLimit, 
            Optional<EntityField> orderBy, 
            Optional<Pair<EntityField, String>> search,
            int batchLimit,
            DatabaseErrorProneFunction<Optional<RecordType>, GenericAddFrame> addFrameFunction
            
    ) throws ClassNotFoundException, SQLException {
        this.fields = fields;
        this.queryLimit = queryLimit;
        this.orderBy = orderBy;
        this.search = search;
        this.cardBatchLimit = batchLimit;
        this.isDepleted = false;
        this.smartConnection = new SmartConnection();
        this.resetListener = Optional.empty();
        this.addFrameFunction = addFrameFunction;
    }
    
    public CardSupplier(
            LinkedList<EntityField> fields, 
            Optional<Integer> queryLimit, 
            Optional<EntityField> orderBy, 
            Optional<Pair<EntityField, String>> search,
            DatabaseErrorProneFunction<Optional<RecordType>, GenericAddFrame> addFrameFunction
            
    ) throws ClassNotFoundException, SQLException {
        this(fields, queryLimit, orderBy, search, 10, addFrameFunction);
    }
    
    public CardSupplier(
            LinkedList<EntityField> fields,
            DatabaseErrorProneFunction<Optional<RecordType>, GenericAddFrame> addFrameFunction
            
    ) throws ClassNotFoundException, SQLException {
        this(fields, Optional.empty(), Optional.empty(), Optional.empty(), addFrameFunction);
    }
    
    /**
     * This method returns a string that can be used in a SQL statement
     * that provides the necessary values to build a record of type
     * <code>RecordType</code>
     * 
     * @return The string to select values from the database; i. e., the 
     * "SELECT" part of a SQL statement. 
     */
    protected abstract String getQueryString();
    protected abstract RecordType buildRecord(ResultSet resultSet) throws SQLException;
    protected abstract CardType renderRecord(RecordType record);
    
    public static String surroundWithBackticks(String string) {
        return "`" + string + "`";
    }
    
    protected PreparedStatement buildPreparedStatement() throws SQLException {
        String queryString = getQueryString();
        
        if (search.isPresent()) {
            String searchField = search.get().getFirst().getInternalValue();
            queryString += " WHERE " + surroundWithBackticks(searchField) + " = ?";
        }
        
        if (orderBy.isPresent()) {
            String orderByField = orderBy.get().getInternalValue();
            queryString += " ORDER BY " + surroundWithBackticks(orderByField);
        }
        
        if (queryLimit.isPresent()) {
            queryString += " LIMIT ?";
        }
        
        
        PreparedStatement statement = smartConnection
                .getRawConnection()
                .prepareStatement(queryString);
        
        int indexOfLastArgument = 1;
        
        if (search.isPresent()) {
            String searchValue = search.get().getSecond();
            statement.setString(indexOfLastArgument, searchValue);
            indexOfLastArgument++;
        }
        
        if (queryLimit.isPresent()) {
            statement.setInt(indexOfLastArgument, queryLimit.get());
        }
        
        return statement;
    }
    
    public Optional<LinkedList<CardType>> getNextCardBatch() throws SQLException {
        LinkedList<CardType> cards = new LinkedList();
        
        if (query.isEmpty()) {
            PreparedStatement preparedStatement = buildPreparedStatement();
            query = Optional.of(preparedStatement.executeQuery());    
        } else if (isDepleted)  {
            return Optional.empty();
        }
        
        ResultSet rawQuery = query.get();
        
        int cardsInBatch = 0;
        while (rawQuery.next()) {
            RecordType record = buildRecord(rawQuery);
            CardType card = renderRecord(record);
            cards.add(card);
            cardsInBatch++;
            if (cardsInBatch >= cardBatchLimit) {
                break;
            }
        }
        
        if (cardsInBatch == 0) {
            isDepleted = true;
            return Optional.empty();
        }
        
        return Optional.of(cards);
    }

    public LinkedList<EntityField> getFields() {
        return fields;
    }

    public Optional<Integer> getQueryLimit() {
        return queryLimit;
    }

    public Optional<EntityField> getOrderBy() {
        return orderBy;
    }

    public Optional<Pair<EntityField, String>> getSearch() {
        return search;
    }

    public void setQueryLimit(Optional<Integer> queryLimit) {
        if (this.queryLimit == queryLimit) return;
        
        this.queryLimit = queryLimit;
        this.resetQuery();
    }

    public void setOrderBy(Optional<EntityField> orderBy) {
        if (this.orderBy == orderBy) return;
        
        this.orderBy = orderBy;
        this.resetQuery();
    }

    public void setSearch(Optional<Pair<EntityField, String>> search) {
        if (this.search == search) return;
        
        this.search = search;
        this.resetQuery();
    }
    
    public void resetQuery() {
        try {
            if (this.query.isPresent()) {
                this.query.get().close();
                this.query = Optional.empty();
                this.smartConnection.close();
            }
            
            this.smartConnection = new SmartConnection();
            
            this.isDepleted = false;
            if (this.resetListener.isPresent()) {
                this.resetListener.get().call(queryLimit, orderBy, search);
            }
        } catch (SQLException ex) {
            System.out.println("Couldn't reset the query");
            Logger.getLogger(GenericQueryFrame.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(CardSupplier.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean isDepleted() {
        return isDepleted;
    }

    public void setResetListener(Optional<ResetListener> resetListener) {
        this.resetListener = resetListener;
    }
}
