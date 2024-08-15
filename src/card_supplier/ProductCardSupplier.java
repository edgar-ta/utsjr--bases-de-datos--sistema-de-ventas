/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package card_supplier;

import card.ProductCard;
import component.GenericAddFrame;
import controller.Controller;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.Optional;
import record.ProductRecord;
import util.EntityField;
import util.Pair;
import util.functional.DatabaseErrorProneFunction;

/**
 *
 * @author Edgar
 */
public class ProductCardSupplier extends CardSupplier<ProductRecord, ProductCard> {

    public ProductCardSupplier(LinkedList<EntityField> fields, Optional<Integer> queryLimit, Optional<EntityField> orderBy, Optional<Pair<EntityField, String>> search, int batchLimit, DatabaseErrorProneFunction<Optional<ProductRecord>, GenericAddFrame> addFrameFunction) throws ClassNotFoundException, SQLException {
        super(fields, queryLimit, orderBy, search, batchLimit, addFrameFunction);
    }

    public ProductCardSupplier(LinkedList<EntityField> fields, Optional<Integer> queryLimit, Optional<EntityField> orderBy, Optional<Pair<EntityField, String>> search, DatabaseErrorProneFunction<Optional<ProductRecord>, GenericAddFrame> addFrameFunction) throws ClassNotFoundException, SQLException {
        super(fields, queryLimit, orderBy, search, addFrameFunction);
    }

    public ProductCardSupplier(LinkedList<EntityField> fields, DatabaseErrorProneFunction<Optional<ProductRecord>, GenericAddFrame> addFrameFunction) throws ClassNotFoundException, SQLException {
        super(fields, addFrameFunction);
    }

    @Override
    protected String getQueryString() {
        return "SELECT  \n" +
"	producto.id AS id,\n" +
"	categoria.nombre AS categoriaNombre,\n" +
"	producto.categoria AS categoriaId,\n" +
"	proveedor.nombre AS proveedorNombre,\n" +
"	producto.proveedor AS proveedorId,\n" +
"	producto.codigo AS codigo,\n" +
"	producto.nombre AS nombre,\n" +
"	producto.descuento AS descuento,\n" +
"	producto.precio AS precio,\n" +
"	producto.stock AS stock,\n" +
"    COALESCE(productos_vendidos.productos_vendidos, 0) AS productosVendidos\n" +
"FROM producto\n" +
"INNER JOIN categoria ON producto.categoria = categoria.id\n" +
"INNER JOIN proveedor ON producto.proveedor = proveedor.id\n" +
"LEFT JOIN (\n" +
"	SELECT \n" +
"		producto.id AS producto,\n" +
"		SUM(detalle.cantidad_de_producto) AS productos_vendidos\n" +
"    FROM producto\n" +
"    INNER JOIN detalle ON producto.id = detalle.producto\n" +
"    GROUP BY producto.id\n" +
") AS productos_vendidos ON producto.id = productos_vendidos.producto";
    }

    @Override
    protected ProductRecord buildRecord(ResultSet resultSet) throws SQLException {
        return Controller.PRODUCT_CONTROLLER.deserializeRecord(resultSet);
    }

    @Override
    protected ProductCard renderRecord(ProductRecord record) {
        return new ProductCard(Optional.of(record), addFrameFunction);
    }
    
}
