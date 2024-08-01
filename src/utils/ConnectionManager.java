/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;


import com.mysql.cj.xdevapi.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

/**
 *
 * @author Edgar
 */
public class ConnectionManager extends StatementSetChain<ConnectionManager> implements AutoCloseable {
    SmartConnection connection;
    PreparedStatement preparedStatement;

    protected ConnectionManager(SmartConnection connection, PreparedStatement preparedStatement) {
        this.connection = connection;
        this.preparedStatement = preparedStatement;
    }

    public SmartConnection getConnection() {
        return connection;
    }
    
    public static ConnectionManager create(String query) throws SQLException, ClassNotFoundException {
        SmartConnection connection = new SmartConnection();
        PreparedStatement preparedStatement = connection.getRawConnection().prepareStatement(query);
        
        return new ConnectionManager(connection, preparedStatement);
    }
    
    public SmartQuery query() throws SQLException {
        return SmartQuery.of(this);
    }
    
    public SmartUpdate update() throws SQLException {
        return SmartUpdate.of(this);
    }

    @Override
    public void close() throws Exception {
        connection.close();
    }

    @Override
    public PreparedStatement getPreparedStatement() {
        return preparedStatement;
    }
}
