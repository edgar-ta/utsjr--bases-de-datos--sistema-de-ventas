/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.sql.SQLException;
import java.util.Optional;
import java.util.function.BiFunction;
import record.ClientRecord;
import record.UserRecord;
import util.ConnectionManager;
import util.SmartConnection;
import util.SmartQuery;
import util.UpdateChain;
import util.UpdateResult;

/**
 *
 * @author Edgar
 */
public class ClientController extends Controller {
    public static ClientRecord buildRecord(SmartQuery query) throws SQLException {
        ClientRecord record = new ClientRecord();

        record.setId(query.getInt("id"));
        record.setNombre(query.getString("nombre"));
        record.setRfc(query.getString("rfc"));
        record.setCalle(query.getString("calle"));
        record.setNumero_de_casa(query.getString("numero_de_casa"));
        record.setColonia(query.getString("colonia"));
        record.setMunicipio(query.getString("municipio"));
        record.setEstado(query.getString("estado"));
        record.setTelefono(query.getString("telefono"));
        record.setCelular(query.getString("celular"));

        return record;
    }
    
    public static Optional<ClientRecord> getClientByName(String name) throws SQLException, ClassNotFoundException, Exception {
        try (SmartQuery query = ConnectionManager
                .create("SELECT * FROM cliente WHERE cliente.nombre = ?")
                .setString(1, name)
                .query()
                ) {
            if (!query.isPopulated()) return Optional.empty();
            
            ClientRecord record = ClientController.buildRecord(query);
            
            return Optional.of(record);
        }
    }

    public static Optional<ClientRecord> getClientById(Integer id) throws SQLException, ClassNotFoundException, Exception {
        try (SmartQuery query = ConnectionManager
                .create("SELECT * FROM cliente WHERE cliente.id = ?")
                .setInteger(1, id)
                .query()
                ) {
            if (!query.isPopulated()) return Optional.empty();
            
            ClientRecord record = ClientController.buildRecord(query);
            
            return Optional.of(record);
        }
    }
    
    public static UpdateChain insertClient(SmartConnection connection, ClientRecord clientRecord) throws SQLException, ClassNotFoundException, Exception {
        int nextId = SeriabilityController
                .getNexIdOfTable(SeriabilityController.ValidTable.CLIENT)
                .get();
        try {
            return UpdateChain
                    .of(connection, "INSERT INTO cliente VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)")
                    .setInteger(1, nextId)
                    .setString(2, clientRecord.getNombre())
                    .setString(3, clientRecord.getRfc())
                    .setString(4, clientRecord.getCalle())
                    .setString(5, clientRecord.getNumero_de_casa())
                    .setString(6, clientRecord.getColonia())
                    .setString(7, clientRecord.getMunicipio())
                    .setString(8, clientRecord.getEstado())
                    .setString(9, clientRecord.getTelefono())
                    .setString(10, clientRecord.getCelular())
                    .chain(SeriabilityController.increaseNextIdOfTable(connection, SeriabilityController.ValidTable.CLIENT))
                    ;
        } catch (SQLException e) {
            throw e;
        }
    }
    
    public static UpdateResult insertClient(ClientRecord clientRecord) throws SQLException, ClassNotFoundException, Exception {
        try (SmartConnection connection = new SmartConnection()) {
            UpdateChain updateChain = ClientController
                    .insertClient(connection, clientRecord)
                    .run();
            UpdateResult result = updateChain.getResult();
            
            return result;
        }
    }
    
    public static UpdateChain deleteClientById(SmartConnection connection, Integer id) throws SQLException {
        try {
            return UpdateChain
                    .of(connection, "DELETE FROM cliente WHERE cliente.id = ?")
                    .setInteger(1, id)
                    ;
        } catch (SQLException e) {
            throw e;
        }
    }
    
    public static UpdateResult deleteClientById(Integer id) throws ClassNotFoundException, SQLException, Exception {
        try (SmartConnection connection = new SmartConnection()) {
            UpdateChain updateChain = deleteClientById(connection, id).run();
            UpdateResult result = updateChain.getResult();
            if (result == UpdateResult.SUCCESS) {
                connection.getRawConnection().commit();
            } else {
                connection.getRawConnection().rollback();
            }
            return result;
        }
    }
    
    public static UpdateChain updateClientById(SmartConnection connection, ClientRecord record) throws SQLException {
        return UpdateChain
                .of(connection, "UPDATE cliente SET "
                        + "cliente.nombre = ?,"
                        + "cliente.rfc = ?,"
                        + "cliente.calle = ?,"
                        + "cliente.numero_de_casa = ?,"
                        + "cliente.colonia = ?,"
                        + "cliente.municipio = ?,"
                        + "cliente.estado = ?,"
                        + "cliente.telefono = ?,"
                        + "cliente.celular = ?"
                        + "WHERE cliente.id = ?"
                )
                .setString(1, record.getNombre())
                .setString(2, record.getRfc())
                .setString(3, record.getCalle())
                .setString(4, record.getNumero_de_casa())
                .setString(5, record.getColonia())
                .setString(6, record.getMunicipio())
                .setString(7, record.getEstado())
                .setString(8, record.getTelefono())
                .setString(9, record.getCelular())
                .setInteger(10, record.getId())
                ;
    }
    
    public static UpdateResult updateClientById(ClientRecord record) throws ClassNotFoundException, SQLException, Exception {
        try (SmartConnection connection = new SmartConnection()) {
            UpdateChain updateChain = updateClientById(connection, record).run();
            UpdateResult result = updateChain.getResult();
            if (result == UpdateResult.SUCCESS) {
                connection.getRawConnection().commit();
            } else {
                connection.getRawConnection().rollback();
            }
            return result;
        }
    }
}
