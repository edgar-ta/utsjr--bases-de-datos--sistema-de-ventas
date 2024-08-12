/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.sql.SQLException;
import util.SmartConnection;
import util.UpdateChain;
import util.UpdateResult;

/**
 *
 * @author Edgar
 */
public class Controller {
    @FunctionalInterface
    public interface InsertEntityBiFunction {
        public UpdateChain call(UpdateChain chain, Integer id) throws SQLException;
    }
    
    @FunctionalInterface
    public interface RunChainFunction {
        public UpdateChain call(SmartConnection connection) throws SQLException, ClassNotFoundException, Exception;
    }
    
    @FunctionalInterface
    public interface CreateChainConsumer {
        public UpdateChain call(UpdateChain chain) throws SQLException, ClassNotFoundException, Exception;
    };
    
    /**
     * Function that regularizes the process of inserting entities within the
     * system; it calls the appropriate insertion code and also chains the 
     * insertion with an increase in the value of the corresponding 
     * Seriability record
     * 
     * @param statement The "INSERT INTO" part of the regular SQL statement for the entity
     * @param table The table that the entity belongs to; as per the ValidTable enum
     * @param callback Function that inserts parameters into the UpdateChain object that corresponds to the given statement
     * @param connection A connection to perform the insertion with
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     * @throws Exception 
     */
    public static UpdateChain insertEntity(
            String statement,
            SeriabilityController.ValidTable table,
            InsertEntityBiFunction callback,
            SmartConnection connection
    ) throws SQLException, ClassNotFoundException, Exception {
        
        int nextId = SeriabilityController
                .getNexIdOfTable(table)
                .get();
        
        try {
            UpdateChain chain = UpdateChain
                    .of(connection, statement);
            callback.call(chain, nextId);
            chain.chain(SeriabilityController.increaseNextIdOfTable(connection, table));
            return chain;
            
        } catch (SQLException e) {
            throw e;
        }
    }
    
    public static UpdateResult runChain(
            RunChainFunction callback
    ) throws SQLException, ClassNotFoundException, Exception {
        try (SmartConnection connection = new SmartConnection()) {
            return callback.call(connection).run().getResult();
        }
    }
    
    public static UpdateChain createChain(String statement, CreateChainConsumer callback, SmartConnection connection) throws SQLException, ClassNotFoundException, Exception {
        try {
            UpdateChain chain = UpdateChain.of(connection, statement);
            callback.call(chain);
            return chain;
        } catch (Exception e) {
            throw e;
        }
    }
}
