/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package card_supplier;

import card.SupplierCard;
import component.GenericAddFrame;
import controller.Controller;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.Optional;
import record.SupplierRecord;
import util.EntityField;
import util.functional.DatabaseErrorProneFunction;

/**
 *
 * @author Edgar
 */
public class SupplierCardSupplier extends CardSupplier<SupplierRecord, SupplierCard> {
    public SupplierCardSupplier(LinkedList<EntityField> fields, DatabaseErrorProneFunction<Optional<SupplierRecord>, GenericAddFrame> addFrameFunction) throws ClassNotFoundException, SQLException {
        super(fields, addFrameFunction);
    }

    @Override
    protected String getQueryString() {
        return "SELECT\n" +
"	proveedor.id AS id,\n" +
"    proveedor.nombre AS nombre,\n" +
"    proveedor.rfc AS rfc,\n" +
"    proveedor.direccion AS direccion,\n" +
"    proveedor.telefono AS telefono,\n" +
"    proveedor.celular AS celular,\n" +
"    COALESCE(productos_ofrecidos.productos_ofrecidos, 0) AS productosOfrecidos\n" +
"FROM proveedor\n" +
"LEFT JOIN (\n" +
"	SELECT\n" +
"		proveedor.id AS proveedor,\n" +
"        COUNT(*) AS productos_ofrecidos\n" +
"	FROM proveedor\n" +
"    INNER JOIN producto ON proveedor.id = producto.proveedor\n" +
"    GROUP BY proveedor.id\n" +
") AS productos_ofrecidos ON proveedor.id = productos_ofrecidos.proveedor";
    }

    @Override
    protected SupplierRecord buildRecord(ResultSet resultSet) throws SQLException {
        return Controller.SUPPLIER_CONTROLLER.deserializeRecord(resultSet);
    }

    @Override
    protected SupplierCard renderRecord(SupplierRecord record) {
        return new SupplierCard(Optional.of(record), addFrameFunction);
    }
    
}
