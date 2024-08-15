/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package card_supplier;

import card.ClientCard;
import component.GenericAddFrame;
import controller.Controller;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.Optional;
import record.ClientRecord;
import util.EntityField;
import util.functional.DatabaseErrorProneFunction;

/**
 *
 * @author Edgar
 */
public class ClientCardSupplier extends CardSupplier<ClientRecord, ClientCard> {

    public ClientCardSupplier(LinkedList<EntityField> fields, DatabaseErrorProneFunction<Optional<ClientRecord>, GenericAddFrame> addFrameFunction) throws ClassNotFoundException, SQLException {
        super(fields, addFrameFunction);
    }

    @Override
    protected String getQueryString() {
        return "SELECT \n" +
"	cliente.id AS id,\n" +
"    cliente.nombre AS nombre,\n" +
"    cliente.rfc AS rfc,\n" +
"    cliente.calle AS calle,\n" +
"    cliente.numero_de_casa AS numero_de_casa,\n" +
"    cliente.colonia AS colonia,\n" +
"    cliente.municipio AS municipio,\n" +
"    cliente.estado AS estado,\n" +
"    cliente.telefono AS telefono,\n" +
"    cliente.celular AS celular,\n" +
"    ventas_por_cliente.ventas_por_cliente AS ventasRealizadas\n" +
"FROM cliente\n" +
"LEFT JOIN (\n" +
"	SELECT \n" +
"		cliente.id AS cliente,\n" +
"        COUNT(*) AS ventas_por_cliente\n" +
"	FROM cliente \n" +
"    INNER JOIN venta ON cliente.id = venta.cliente\n" +
"    GROUP BY cliente.id\n" +
") AS ventas_por_cliente ON cliente.id = ventas_por_cliente.cliente";
    }

    @Override
    protected ClientRecord buildRecord(ResultSet resultSet) throws SQLException {
        return Controller.CLIENT_CONTROLLER.deserializeRecord(resultSet);
    }

    @Override
    protected ClientCard renderRecord(ClientRecord record) {
        return new ClientCard(Optional.of(record), addFrameFunction);
    }
    
}
