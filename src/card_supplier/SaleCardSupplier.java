/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package card_supplier;

import card.SaleCard;
import component.GenericAddFrame;
import controller.Controller;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.Optional;
import record.SaleRecord;
import util.EntityField;
import util.functional.DatabaseErrorProneFunction;

/**
 *
 * @author Edgar
 */
public class SaleCardSupplier extends CardSupplier<SaleRecord, SaleCard> {

    public SaleCardSupplier(LinkedList<EntityField> fields, DatabaseErrorProneFunction<Optional<SaleRecord>, GenericAddFrame> addFrameFunction) throws ClassNotFoundException, SQLException {
        super(fields, addFrameFunction);
    }

    @Override
    protected String getQueryString() {
        return "SELECT \n" +
"	venta.id AS id,\n" +
"    venta.cliente AS clienteId,\n" +
"    cliente.nombre AS clienteNombre,\n" +
"    detalle.producto AS productoId,\n" +
"    producto.nombre AS productoNombre,\n" +
"    detalle.cantidad_de_producto AS cantidadDeProducto,\n" +
"    venta.fecha AS fecha,\n" +
"    venta.folio AS folio\n" +
"FROM venta\n" +
"INNER JOIN cliente ON venta.cliente = cliente.id\n" +
"INNER JOIN detalle ON detalle.venta = venta.id\n" +
"INNER JOIN producto ON detalle.producto = producto.id";
    }

    @Override
    protected SaleRecord buildRecord(ResultSet resultSet) throws SQLException {
        return Controller.SALE_CONTROLLER.deserializeRecord(resultSet);
    }

    @Override
    protected SaleCard renderRecord(SaleRecord record) {
        return new SaleCard(Optional.of(record), addFrameFunction);
    }
    
}
