/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import record.ProductRecord;
import util.DatabaseEntity;
import util.PrimaryKey;
import util.SmartConnection;
import util.UpdateChain;

/**
 *
 * @author Edgar
 */
public class ProductController extends Controller<ProductRecord> {

    @Override
    public DatabaseEntity getDatabaseEntity() {
        return DatabaseEntity.PRODUCT;
    }

    @Override
    public UpdateChain update(SmartConnection connection, ProductRecord record) throws SQLException, ClassNotFoundException, Exception {
        return createChain(
                "UPDATE producto SET categoria = ?, proveedor = ?, codigo = ?, nombre = ?, descuento = ?, precio = ?, stock = ? WHERE id = ?", 
                (UpdateChain chain) -> chain
                    .setInteger(1, record.getCategoria().getInternalValue())
                    .setInteger(2, record.getProveedor().getInternalValue())
                    .setString(3, record.getCodigo())
                    .setString(4, record.getNombre())
                    .setDouble(5, record.getDescuento())
                    .setDouble(6, record.getPrecio())
                    .setInteger(7, record.getStock())
                    .setInteger(8, record.getId())
                , 
                connection
        );
    }

    @Override
    public UpdateChain insert(SmartConnection connection, ProductRecord record) throws SQLException, ClassNotFoundException, Exception {
        return insertEntity(
                "INSERT INTO producto VALUES (?, ?, ?, ?, ?, ?, ?, ?)",
                (UpdateChain chain, Integer id) -> chain
                    .setInteger(1, id)
                    .setInteger(2, record.getCategoria().getInternalValue())
                    .setInteger(3, record.getProveedor().getInternalValue())
                    .setString(4, record.getCodigo())
                    .setString(5, record.getNombre())
                    .setDouble(6, record.getDescuento())
                    .setDouble(7, record.getPrecio())
                    .setInteger(8, record.getStock())
                ,
                connection
        );
    }

    @Override
    public ProductRecord deserializeRecord(ResultSet query) throws SQLException {
        ProductRecord record = new ProductRecord();
        
        record.setId(query.getInt("id"));
        record.setCodigo(query.getString("codigo"));
        record.setNombre(query.getString("nombre"));
        record.setDescuento(query.getDouble("descuento"));
        record.setPrecio(query.getDouble("precio"));
        record.setStock(query.getInt("stock"));
        
        record.setCategoria(new PrimaryKey(query.getInt("categoriaId"), query.getString("categoriaNombre")));
        record.setProveedor(new PrimaryKey(query.getInt("proveedorId"), query.getString("proveedorNombre")));
        
        return record;
    }
    
}
