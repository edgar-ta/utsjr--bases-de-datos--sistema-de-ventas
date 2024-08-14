/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package util.functional;

import java.sql.SQLException;

/**
 *
 * @author Edgar
 * @param <K> The type of object that the supplier is expected to return
 */
@FunctionalInterface
public interface DatabaseErrorProneSupplier<K> {    
    public K call() throws SQLException, ClassNotFoundException, Exception;
}
