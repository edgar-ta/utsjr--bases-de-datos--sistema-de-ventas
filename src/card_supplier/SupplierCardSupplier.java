/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package card_supplier;

import card.SupplierCard;
import controller.Controller;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.Optional;
import record.SupplierRecord;
import util.EntityField;

/**
 *
 * @author Edgar
 */
public class SupplierCardSupplier extends CardSupplier<SupplierRecord, SupplierCard> {

    public SupplierCardSupplier(LinkedList<EntityField> fields) throws ClassNotFoundException, SQLException {
        super(fields);
    }

    @Override
    protected String getQueryString() {
        return "SELECT * FROM proveedor";
    }

    @Override
    protected SupplierRecord buildRecord(ResultSet resultSet) throws SQLException {
        return Controller.SUPPLIER_CONTROLLER.deserializeRecord(resultSet);
    }

    @Override
    protected SupplierCard renderRecord(SupplierRecord record) {
        return new SupplierCard(Optional.of(record));
    }
    
}
