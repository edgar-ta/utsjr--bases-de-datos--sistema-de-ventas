/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import java.util.LinkedList;
import java.util.Optional;
import javax.swing.JPanel;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.util.function.Consumer;
import java.util.function.Function;


/**
 *
 * @author Edgar
 * @param <RecordType>
 */
public abstract class CardSupplier<RecordType> {
    protected LinkedList<String> fields;
    
    protected Optional<Integer> queryLimit;
    protected Optional<String> orderBy;
    protected Optional<Pair<String, String>> search;
    protected int cardBatchLimit;
    
    protected SmartConnection smartConnection;
    protected Optional<ResultSet> resultSet = Optional.empty();

    public CardSupplier(
            LinkedList<String> fields, 
            Optional<Integer> queryLimit, 
            Optional<String> orderBy, 
            Optional<Pair<String, String>> search,
            int batchLimit
    ) throws ClassNotFoundException, SQLException {
        this.fields = fields;
        this.queryLimit = queryLimit;
        this.orderBy = orderBy;
        this.search = search;
        this.cardBatchLimit = batchLimit;
        this.smartConnection = new SmartConnection();
    }
    
    public CardSupplier(
            LinkedList<String> fields, 
            Optional<Integer> queryLimit, 
            Optional<String> orderBy, 
            Optional<Pair<String, String>> search
    ) throws ClassNotFoundException, SQLException {
        this(fields, queryLimit, orderBy, search, 10);
    }
    
    public CardSupplier(LinkedList<String> fields) throws ClassNotFoundException, SQLException {
        this(fields, Optional.empty(), Optional.empty(), Optional.empty());
    }
    
    /**
     * 
     * @return The string to select values from the database
     */
    protected abstract String getQueryString();
    protected abstract RecordType buildRecord(ResultSet resultSet);
    protected abstract JPanel renderRecord(RecordType record);
    
    public static String surroundWithBackticks(String string) {
        return "`" + string + "`";
    }
    
    protected PreparedStatement buildPreparedStatement() throws SQLException {
        String queryString = getQueryString();
        
        if (search.isPresent()) {
            String searchField = search.get().getFirst();
            queryString += " WHERE " + surroundWithBackticks(searchField) + " = ?";
        }
        
        if (orderBy.isPresent()) {
            String orderByField = orderBy.get();
            queryString += " ORDER BY " + surroundWithBackticks(orderByField);
        }
        
        if (queryLimit.isPresent()) {
            queryString += " LIMIT ?";
        }
        
        PreparedStatement statement = smartConnection
                .getRawConnection()
                .prepareStatement(queryString);
        
        int indexOfLastArgument = 0;
        
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
    
    public Optional<LinkedList<JPanel>> getNextCardBatch() throws SQLException {
        LinkedList<JPanel> cards = new LinkedList();
        if (resultSet.isEmpty()) {
            PreparedStatement preparedStatement = buildPreparedStatement();
            resultSet = Optional.of(preparedStatement.executeQuery());
        }
        ResultSet rawResults = resultSet.get();
        
        if (!rawResults.next()) return Optional.empty();
        
        int cardsInBatch = 0;
        do {
            RecordType registry = buildRecord(rawResults);
            JPanel card = renderRecord(registry);
            cards.add(card);
            cardsInBatch++;
        } while (rawResults.next() && cardsInBatch <= cardBatchLimit);
        
        return Optional.of(cards);
    }

    public LinkedList<String> getFields() {
        return fields;
    }

    public Optional<Integer> getQueryLimit() {
        return queryLimit;
    }

    public Optional<String> getOrderBy() {
        return orderBy;
    }

    public Optional<Pair<String, String>> getSearch() {
        return search;
    }

    public void setQueryLimit(Optional<Integer> queryLimit) {
        this.queryLimit = queryLimit;
    }

    public void setOrderBy(Optional<String> orderBy) {
        this.orderBy = orderBy;
    }

    public void setSearch(Optional<Pair<String, String>> search) {
        this.search = search;
    }
}
