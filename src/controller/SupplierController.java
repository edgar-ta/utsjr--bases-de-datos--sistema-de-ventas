/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import record.SupplierRecord;
import util.DatabaseEntity;
import util.SmartConnection;
import util.UpdateChain;
import util.UpdateResult;

/**
 *
 * @author Edgar
 */
public class SupplierController extends Controller<SupplierRecord> {

    @Override
    public DatabaseEntity getDatabaseEntity() {
        return DatabaseEntity.SUPPLIER;
    }

    @Override
    public UpdateChain update(SmartConnection connection, SupplierRecord record) throws SQLException, ClassNotFoundException, Exception {
        return createChain(
                "UPDATE proveedor SET nombre = ?, rfc = ?, direccion = ?, telefono = ?, celular = ? WHERE id = ?", 
                (UpdateChain chain) -> chain
                    .setString(1, record.getNombre())
                    .setString(2, record.getRfc())
                    .setString(3, record.getDireccion())
                    .setString(4, record.getTelefono())
                    .setString(5, record.getCelular())
                    .setInteger(6, record.getId())
                , 
                connection
        );
    }

    @Override
    public UpdateChain insert(SmartConnection connection, SupplierRecord record) throws SQLException, ClassNotFoundException, Exception {
        return insertEntity(
                "INSERT INTO proveedor VALUES (?, ?, ?, ?, ?, ?)",
                (UpdateChain chain, Integer id) -> chain
                    .setInteger(1, id)
                    .setString(2, record.getNombre())
                    .setString(3, record.getRfc())
                    .setString(4, record.getDireccion())
                    .setString(5, record.getTelefono())
                    .setString(6, record.getCelular())
                ,
                connection
        );
    }

    @Override
    public SupplierRecord deserializeRecord(ResultSet query) throws SQLException {
        SupplierRecord record = new SupplierRecord();
        
        record.setId(query.getInt("id"));
        record.setNombre(query.getString("nombre"));
        record.setRfc(query.getString("rfc"));
        record.setDireccion(query.getString("direccion"));
        record.setTelefono(query.getString("telefono"));
        record.setCelular(query.getString("celular"));
        record.setProductosOfrecidos(query.getInt("productosOfrecidos"));
        
        return record;
    }
}
