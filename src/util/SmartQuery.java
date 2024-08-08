/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Edgar
 */
public class SmartQuery implements AutoCloseable {
    ResultSet resultSet;
    ConnectionManager connectionManager;
    boolean isPopulated;
    
    private SmartQuery(
            ConnectionManager connectionManager
    ) throws SQLException {
        this.connectionManager = connectionManager;
        this.resultSet = connectionManager.getPreparedStatement().executeQuery();
        isPopulated = resultSet.next();
    }
    
    public static SmartQuery of(ConnectionManager connectionManager) throws SQLException {
        return new SmartQuery(
                connectionManager
        );
    }
    
    public boolean next() throws SQLException {
        return resultSet.next();
    }
    
    public String getString(String name) throws SQLException {
        return resultSet.getString(name);
    }
    
    public int getInt(String name) throws SQLException {
        return resultSet.getInt(name);
    }
    
    public boolean isPopulated() {
        return isPopulated;
    }
    
    public boolean isEmpty() {
        return !isPopulated;
    }

    @Override
    public void close() throws Exception {
        connectionManager.close();
    }
}
