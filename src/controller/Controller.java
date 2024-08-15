/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.LinkedList;
import util.SmartConnection;
import util.UpdateChain;
import util.UpdateResult;
import record.Record;
import util.ConnectionManager;
import util.DatabaseEntity;
import util.EntityHeaderData;
import util.PrimaryKey;
import util.SmartQuery;

/**
 *
 * @author Edgar
 */
public abstract class Controller<RecordType extends Record> {
    
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
    public UpdateChain insertEntity(
            String statement,
            InsertEntityBiFunction callback,
            SmartConnection connection
    ) throws SQLException, ClassNotFoundException, Exception {
        
        DatabaseEntity table = getDatabaseEntity();
        
        int nextId = Controller.SERIABILITY_CONTROLLER
                .getNexIdOfTable(table)
                .get();
        
        try {
            UpdateChain chain = UpdateChain
                    .of(connection, statement);
            callback.call(chain, nextId);
            chain.chain(Controller.SERIABILITY_CONTROLLER.increaseNextIdOfTable(connection, table));
            return chain;
            
        } catch (SQLException e) {
            throw e;
        }
    }
    
    public UpdateResult runChain(
            RunChainFunction callback
    ) throws SQLException, ClassNotFoundException, Exception {
        try (SmartConnection connection = new SmartConnection()) {
            return callback.call(connection).run().getResult();
        }
    }
    
    public UpdateChain createChain(String statement, CreateChainConsumer callback, SmartConnection connection) throws SQLException, ClassNotFoundException, Exception {
        try {
            UpdateChain chain = UpdateChain.of(connection, statement);
            callback.call(chain);
            return chain;
        } catch (Exception e) {
            throw e;
        }
    }
    
    public abstract DatabaseEntity getDatabaseEntity();
    
    
    
    public boolean exists(Integer id) throws SQLException, ClassNotFoundException, Exception {
        String internalName = getDatabaseEntity().getEntityName().getInternalValue();
        
        try (SmartQuery query = ConnectionManager
                .create("SELECT " + internalName + ".id FROM " + internalName + " WHERE " + internalName + ".id = ?")
                .setInteger(1, id)
                .query()
                ) {
            return query.isPopulated();
        }
    }
    
    
    
    public UpdateResult delete(Integer id) throws SQLException, ClassNotFoundException, Exception {
        return runChain((SmartConnection connection) -> this.delete(connection, id));
    }
    public UpdateChain delete(SmartConnection connection, Integer id) throws SQLException, ClassNotFoundException, Exception {
        String internalName = getDatabaseEntity().getEntityName().getInternalValue();
        return createChain(
                "DELETE FROM " + internalName + " WHERE " + internalName + ".id = ?", 
                (UpdateChain chain) -> chain.setInteger(1, id), 
                connection
        );
    }
    
    /**
     * Gets primary keys that can be used to set the valus of combo boxes in
     * forms that require interaction with other entities
     * @param entity
     * @return 
     */
    public static LinkedList<PrimaryKey> getPrimaryKeysForDisplay(DatabaseEntity entity) throws ClassNotFoundException, SQLException, Exception {
        if (
                entity == DatabaseEntity.DETAIL || 
                entity == DatabaseEntity.SALE ||
                entity == DatabaseEntity.SERIABILITY
        ) {
            throw new IllegalArgumentException("There is no agreed upon way to represent the passed entity");
        }
        
        String internalName = entity.getEntityName().getInternalValue();
        
        try (SmartQuery query = ConnectionManager
                .create("SELECT id, nombre FROM " + internalName)
                .query()
                ) {
            
            LinkedList<PrimaryKey> primaryKeys = new LinkedList<>();
            if (query.isPopulated()) {
                do {

                    int internalValue = query.getInt("id");
                    String externalValue = query.getString("nombre");

                    PrimaryKey primaryKey = new PrimaryKey(internalValue, externalValue);
                    primaryKeys.add(primaryKey);
                } while (query.next());
            }
            
            return primaryKeys;
        }
    }
    
    
    
    public UpdateResult update(RecordType record) throws SQLException, ClassNotFoundException, Exception {
        return runChain((SmartConnection connection) -> this.update(connection, record));
    }
    public abstract UpdateChain update(SmartConnection connection, RecordType record) throws SQLException, ClassNotFoundException, Exception;
    
    
    
    public UpdateResult insert(RecordType record) throws SQLException, ClassNotFoundException, Exception {
        return runChain((SmartConnection connection) -> this.insert(connection, record));
    }
    public abstract UpdateChain insert(SmartConnection connection, RecordType record) throws SQLException, ClassNotFoundException, Exception;
    
    public abstract RecordType deserializeRecord(ResultSet query) throws SQLException;
    
    
    
    public static final UserController USER_CONTROLLER = new UserController();
    public static final SupplierController SUPPLIER_CONTROLLER = new SupplierController();
    public static final ProductController PRODUCT_CONTROLLER = new ProductController();
    public static final CategoryController CATEGORY_CONTROLLER = new CategoryController();
    public static final SaleController SALE_CONTROLLER = new SaleController();
    
    public static final SeriabilityController SERIABILITY_CONTROLLER = new SeriabilityController();
}
