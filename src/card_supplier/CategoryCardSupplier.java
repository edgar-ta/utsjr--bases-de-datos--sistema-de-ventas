/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package card_supplier;

import card.CategoryCard;
import component.GenericAddFrame;
import controller.Controller;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.Optional;
import record.CategoryRecord;
import util.EntityField;
import util.functional.DatabaseErrorProneFunction;

/**
 *
 * @author Edgar
 */
public class CategoryCardSupplier extends CardSupplier<CategoryRecord, CategoryCard> {

    public CategoryCardSupplier(LinkedList<EntityField> fields, DatabaseErrorProneFunction<Optional<CategoryRecord>, GenericAddFrame> addFrameFunction) throws ClassNotFoundException, SQLException {
        super(fields, addFrameFunction);
    }

    @Override
    protected String getQueryString() {
        return  "SELECT\n" +
"	categoria.nombre AS nombre,\n" +
"	categoria.id AS id,\n" +
"	productos_por_categoria.numero_de_productos AS numeroDeProductos\n" +
"FROM categoria \n" +
"INNER JOIN (\n" +
"	SELECT \n" +
"		categoria.id AS id,\n" +
"		COUNT(*) AS numero_de_productos\n" +
"	FROM categoria\n" +
"	LEFT JOIN producto ON producto.id = categoria.id \n" +
"	GROUP BY categoria.id\n" +
") AS productos_por_categoria ON productos_por_categoria.id = categoria.id"
                ;
    }

    @Override
    protected CategoryRecord buildRecord(ResultSet resultSet) throws SQLException {
        return Controller.CATEGORY_CONTROLLER.deserializeRecord(resultSet);
    }

    @Override
    protected CategoryCard renderRecord(CategoryRecord record) {
        return new CategoryCard(Optional.of(record), addFrameFunction);
    }
    
}
