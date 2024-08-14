/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package util;

import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.Date;

/**
 *
 * @author Edgar
 */
public abstract class StatementSetChain<K> {
    public abstract PreparedStatement getPreparedStatement();
    
    public K setString(int index, String value) throws SQLException {
        getPreparedStatement().setString(index, value);
        return (K) this;
    }
    
    public K setInteger(int index, Integer value) throws SQLException {
        getPreparedStatement().setInt(index, value);
        return (K) this;
    }
    
    public K setDouble(int index, Double value) throws SQLException {
        getPreparedStatement().setDouble(index, value);
        return (K) this;
    }
    
    public K setDate(int index, Date value) throws SQLException {
        getPreparedStatement().setDate(index, value);
        return (K) this;
    }
}
