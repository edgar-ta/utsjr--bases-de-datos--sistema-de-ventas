/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.function.BiFunction;
import record.ClientRecord;
import record.Record;
import record.UserRecord;
import util.ConnectionManager;
import util.DatabaseEntity;
import util.SmartConnection;
import util.SmartQuery;
import util.UpdateChain;
import util.UpdateResult;

import record.ClientRecord;

/**
 *
 * @author Edgar
 */
public class ClientController extends Controller<ClientRecord> {

    @Override
    public DatabaseEntity getDatabaseEntity() {
        return DatabaseEntity.CLIENT;
    }

    @Override
    public UpdateChain update(SmartConnection connection, ClientRecord record) throws SQLException, ClassNotFoundException, Exception {
        return createChain(
                "UPDATE cliente SET nombre = ?, rfc = ?, calle = ?, numero_de_casa = ?, colonia = ?, municipio = ?, estado = ?, telefono = ?, celular = ? WHERE id = ?", 
                (UpdateChain chain) -> chain
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
                , 
                connection
        );
    }

    @Override
    public UpdateChain insert(SmartConnection connection, ClientRecord record) throws SQLException, ClassNotFoundException, Exception {
        return createChain(
                "INSERT INTO cliente VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)", 
                (UpdateChain chain) -> chain
                    .setInteger(1, record.getId())
                    .setString(2, record.getNombre())
                    .setString(3, record.getRfc())
                    .setString(4, record.getCalle())
                    .setString(5, record.getNumero_de_casa())
                    .setString(6, record.getColonia())
                    .setString(7, record.getMunicipio())
                    .setString(8, record.getEstado())
                    .setString(9, record.getTelefono())
                    .setString(10, record.getCelular())
                , 
                connection
        );
    }

    @Override
    public ClientRecord deserializeRecord(ResultSet query) throws SQLException {
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
        record.setVentasRealizadas(query.getInt("ventasRealizadas"));
        
        return record;
    }
    
}
