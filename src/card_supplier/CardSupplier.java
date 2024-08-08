/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package card_supplier;

import card.Card;
import java.util.LinkedList;
import java.util.Optional;
import javax.swing.JPanel;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.util.Vector;
import java.util.function.Consumer;
import java.util.function.Function;
import utils.Pair;
import utils.SmartConnection;
import records.Record;
import utils.EntityField;



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
    protected Optional<StateChangeListener> stateChangeListener;
    
    @FunctionalInterface
    public interface StateChangeListener {
        public void call(
                Optional<Integer> queryLimit, 
                Optional<EntityField> orderBy,
                Optional<Pair<EntityField, String>> search
        );
    }
    
    /**
     * This field can only accurately say
     * if the supplier is empty; it CAN NOT say
     * whether it is populated or not
     */
    protected boolean isEmpty;
    
    protected SmartConnection smartConnection;
    protected Optional<ResultSet> resultSet = Optional.empty();

    public CardSupplier(
            LinkedList<EntityField> fields, 
            Optional<Integer> queryLimit, 
            Optional<EntityField> orderBy, 
            Optional<Pair<EntityField, String>> search,
            int batchLimit
    ) throws ClassNotFoundException, SQLException {
        this.fields = fields;
        this.queryLimit = queryLimit;
        this.orderBy = orderBy;
        this.search = search;
        this.cardBatchLimit = batchLimit;
        this.isEmpty = false;
        this.smartConnection = new SmartConnection();
        this.stateChangeListener = Optional.empty();
    }
    
    public CardSupplier(
            LinkedList<EntityField> fields, 
            Optional<Integer> queryLimit, 
            Optional<EntityField> orderBy, 
            Optional<Pair<EntityField, String>> search
    ) throws ClassNotFoundException, SQLException {
        this(fields, queryLimit, orderBy, search, 10);
    }
    
    public CardSupplier(LinkedList<EntityField> fields) throws ClassNotFoundException, SQLException {
        this(fields, Optional.empty(), Optional.empty(), Optional.empty());
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
            String searchField = search.get().getFirst().getInternalName();
            queryString += " WHERE " + surroundWithBackticks(searchField) + " = ?";
        }
        
        if (orderBy.isPresent()) {
            String orderByField = orderBy.get().getInternalName();
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
        if (resultSet.isEmpty()) {
            PreparedStatement preparedStatement = buildPreparedStatement();
            resultSet = Optional.of(preparedStatement.executeQuery());
        }
        ResultSet rawResults = resultSet.get();
        
        if (!rawResults.next()) { 
            isEmpty = true;
            return Optional.empty(); 
        }
        
        int cardsInBatch = 0;
        do {
            RecordType registry = buildRecord(rawResults);
            CardType card = renderRecord(registry);
            cards.add(card);
            cardsInBatch++;
            if (!rawResults.next()) {
                isEmpty = true;
                break;
            }
        } while (cardsInBatch <= cardBatchLimit);
        
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
        this.onStateChange();
    }

    public void setOrderBy(Optional<EntityField> orderBy) {
        if (this.orderBy == orderBy) return;
        
        this.orderBy = orderBy;
        this.onStateChange();
    }

    public void setSearch(Optional<Pair<EntityField, String>> search) {
        if (this.search == search) return;
        
        this.search = search;
        this.onStateChange();
    }
    
    public void onStateChange() {
        this.resultSet = Optional.empty();
        this.isEmpty = false;
        if (this.stateChangeListener.isPresent()) {
            this.stateChangeListener.get().call(queryLimit, orderBy, search);
        }
    }

    public boolean isEmpty() {
        return isEmpty;
    }

    public void setStateChangeListener(Optional<StateChangeListener> stateChangeListener) {
        this.stateChangeListener = stateChangeListener;
    }
}
