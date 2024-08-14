/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import record.CategoryRecord;
import util.DatabaseEntity;
import util.SmartConnection;
import util.UpdateChain;

/**
 *
 * @author Edgar
 */
public class CategoryController extends Controller<CategoryRecord> {

    @Override
    public DatabaseEntity getDatabaseEntity() {
        return DatabaseEntity.CATEGORY;
    }

    @Override
    public UpdateChain update(SmartConnection connection, CategoryRecord record) throws SQLException, ClassNotFoundException, Exception {
        return createChain(
                "UPDATE categoria SET nombre = ? WHERE id = ?", 
                (UpdateChain chain) -> chain
                    .setString(1, record.getNombre())
                    .setInteger(2, record.getId())
                , 
                connection
        );
    }

    @Override
    public UpdateChain insert(SmartConnection connection, CategoryRecord record) throws SQLException, ClassNotFoundException, Exception {
        return insertEntity(
                "INSERT INTO categoria VALUES (?, ?)",
                (UpdateChain chain, Integer id) -> chain
                    .setInteger(1, id)
                    .setString(2, record.getNombre())
                ,
                connection
        );
    }

    @Override
    public CategoryRecord deserializeRecord(ResultSet query) throws SQLException {
        CategoryRecord record = new CategoryRecord();
        
        record.setNombre(query.getString("nombre"));
        record.setId(query.getInt("id"));
        record.setNumeroDeProductos(query.getInt("numeroDeProductos"));
        
        return record;
    }
    
}
