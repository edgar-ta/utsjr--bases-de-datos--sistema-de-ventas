/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import record.Record;
import util.ConnectionManager;
import util.DatabaseEntity;
import util.SmartConnection;
import util.SmartQuery;
import util.SmartUpdate;
import util.UpdateChain;
import util.UpdateResult;

/**
 * Utility class to interact with the `seriabilidad` table.
 * 
 * The `cima` column of said table can't be trusted fully,
 * given the fact that the database was modified previously
 * without updating the table appropriately
 * 
 * @author Edgar
 */
public class SeriabilityController extends Controller {    
    public Optional<Integer> getNexIdOfTable(DatabaseEntity table) throws SQLException, ClassNotFoundException, Exception {
        String internalName = table.getEntityName().getInternalValue();
        
        try (SmartQuery query = ConnectionManager
                .create("SELECT seriabilidad.cima FROM seriabilidad WHERE seriabilidad.nombre = ?")
                .setString(1, internalName)
                .query()
                ) {
            if (query.isEmpty()) {
                return Optional.empty();
            }
            return Optional.of(query.getInt("cima"));
        }
    }
    
    public UpdateChain increaseNextIdOfTable(SmartConnection connection, DatabaseEntity table) throws SQLException {
        return UpdateChain
                .of(connection, "UPDATE seriabilidad SET seriabilidad.cima = seriabilidad.cima + 1 WHERE seriabilidad.nombre = ?")
                .setString(1, table.getEntityName().getInternalValue())
                ;
    }
    
    public UpdateResult increaseNextIdOfTable(DatabaseEntity table) throws SQLException, ClassNotFoundException, Exception {
        try (SmartConnection connection = new SmartConnection()) {
            return increaseNextIdOfTable(connection, table).run().getResult();
        }
    }

    
    
    
    @Override
    public DatabaseEntity getDatabaseEntity() {
        return DatabaseEntity.SERIABILITY;
    }

    @Override
    public UpdateChain update(SmartConnection connection, Record record) throws SQLException, ClassNotFoundException, Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public UpdateChain insert(SmartConnection connection, Record record) throws SQLException, ClassNotFoundException, Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Record deserializeRecord(ResultSet query) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
