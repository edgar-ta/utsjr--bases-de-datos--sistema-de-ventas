/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import record.SaleRecord;
import util.ConnectionManager;
import util.DatabaseEntity;
import util.PrimaryKey;
import util.SmartConnection;
import util.UpdateChain;

/**
 *
 * @author Edgar
 */
public class SaleController extends Controller<SaleRecord> {

    @Override
    public DatabaseEntity getDatabaseEntity() {
        return DatabaseEntity.SALE;
    }

    @Override
    public UpdateChain update(SmartConnection connection, SaleRecord record) throws SQLException, ClassNotFoundException, Exception {
        return createChain(
                "UPDATE venta SET cliente = ?, folio = ?, fecha = ? WHERE id = ?", 
                (UpdateChain chain) -> chain
                    .setInteger(1, record.getCliente().getInternalValue())
                    .setInteger(2, record.getFolio())
                    .setDate(3, record.getFecha())
                    .setInteger(4, record.getId())
                , 
                connection
        ).chain(createChain(
                "UPDATE detalle SET cantidad_de_producto = ? WHERE venta = ? AND producto = ?",
                (UpdateChain chain) -> chain
                    .setInteger(1, record.getCantidadDeProducto())
                    .setInteger(2, record.getId())
                    .setInteger(3, record.getProducto().getInternalValue()),
                connection
        ));
    }

    @Override
    public UpdateChain insert(SmartConnection connection, SaleRecord record) throws SQLException, ClassNotFoundException, Exception {
        Optional<Integer> optionalId = Controller.SERIABILITY_CONTROLLER.getNexIdOfTable(DatabaseEntity.SALE);
        if (optionalId.isEmpty()) {
            throw new Exception("The sale table doesn't exist");
        }
        int saleId = optionalId.get();
        int randomFolio = (int)(Math.random() * 50000);
        
        int stockAmount = ConnectionManager
                .create("SELECT stock FROM producto WHERE id = ?")
                .setInteger(1, record.getProducto().getInternalValue())
                .query()
                .getResultSet()
                .getInt("stock")
                ;
        
        if (stockAmount < record.getCantidadDeProducto()) {
            throw new Exception("There aren't enough products to do the sale");
        }
        
        return createChain(
                "INSERT INTO venta VALUES(?, ?, ?, ?)", 
                (UpdateChain chain) -> chain
                    .setInteger(1, saleId)
                    .setInteger(2, record.getCliente().getInternalValue())
                    .setInteger(3, randomFolio)
                    .setDate(4, record.getFecha())
                , 
                connection
        ).chain(createChain(
                "INSERT INTO detalle VALUES (?, ?, ?)",
                (UpdateChain chain) -> chain
                    .setInteger(1, saleId)
                    .setInteger(2, record.getProducto().getInternalValue())
                    .setInteger(3, record.getCantidadDeProducto())
                ,
                connection
        )).chain(createChain(
                "UPDATE producto SET stock = stock - " + String.valueOf(record.getCantidadDeProducto()) + " WHERE id = ?",
                (UpdateChain chain) -> chain
                    .setInteger(1, record.getProducto().getInternalValue())
                ,
                connection
        )).chain(Controller.SERIABILITY_CONTROLLER.increaseNextIdOfTable(connection, DatabaseEntity.SALE))
                
                ;
    }

    @Override
    public SaleRecord deserializeRecord(ResultSet query) throws SQLException {
        SaleRecord record = new SaleRecord();
        
        record.setId(query.getInt("id"));
        record.setFecha(query.getDate("fecha"));
        record.setFolio(query.getInt("folio"));
        
        record.setCliente(new PrimaryKey(query.getInt("clienteId"), query.getString("clienteNombre")));
        record.setProducto(new PrimaryKey(query.getInt("productoId"), query.getString("productoNombre")));
        
        record.setCantidadDeProducto(query.getInt("cantidadDeProducto"));
        
        return record;
    }
}
