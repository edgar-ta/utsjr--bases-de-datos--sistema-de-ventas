/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 *
 * @author Edgar
 */
public class SmartConnection implements AutoCloseable {
    Connection rawConnection;
    
    public SmartConnection() throws ClassNotFoundException, SQLException {
        Properties connectionProperties = new Properties();
        connectionProperties.put("user", "root");
        connectionProperties.put("password", "root");

        Class.forName("com.mysql.jdbc.Driver");
        rawConnection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/sistema_ventas", 
                connectionProperties
        );
        rawConnection.setAutoCommit(false);
        System.out.println("The database was successfully opened");
    }

    @Override
    public void close() throws Exception {
        if (!rawConnection.isClosed()) {
            rawConnection.rollback();
            rawConnection.close();
            System.out.println("The connection was succesfully closed");
        }
    }

    public Connection getRawConnection() {
        return rawConnection;
    }
}
