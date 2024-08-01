/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

/**
 *
 * @author Edgar
 */
public class SmartUpdate implements AutoCloseable {
    ConnectionManager connectionManager;
    UpdateResult result;
    
    private SmartUpdate(
            ConnectionManager connectionManager
    ) throws SQLException {
        this.connectionManager = connectionManager;
        connectionManager.getConnection().getRawConnection().setAutoCommit(false);
        this.result = UpdateResult.fromInteger(connectionManager.getPreparedStatement().executeUpdate());
    }
    
    public static SmartUpdate of(ConnectionManager connectionManager) throws SQLException {
        return new SmartUpdate(
                connectionManager
        );
    }

    public UpdateResult getResult() {
        return result;
    }
    
    public void commit() throws SQLException {
        connectionManager
                .getConnection()
                .getRawConnection()
                .commit();
    }
    
    public void rollback() throws SQLException {
        connectionManager
                .getConnection()
                .getRawConnection()
                .rollback();
    }
    
    @Override
    public void close() throws Exception {
        connectionManager.close();
    }

    public ConnectionManager getConnectionManager() {
        return connectionManager;
    }
}
