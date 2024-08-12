/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.sql.SQLException;
import java.util.Optional;
import util.ConnectionManager;
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
    public static enum ValidTable {
        CATEGORY("categoria"),
        PRODUCT("producto"),
        SUPPLIER("proveedor"),
        CLIENT("cliente"),
        SALE("venta"), 
        USER("usuario"),
        CODE("folio");
        
        public String internalName;
        
        ValidTable(String internalName) {
            this.internalName = internalName;
        }
        
        public String getInternalName() {
            return internalName;
        }
    };
    
    public static Optional<Integer> getNexIdOfTable(ValidTable table) throws SQLException, ClassNotFoundException, Exception {
        String internalName = table.getInternalName();
        
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
    
    public static UpdateChain increaseNextIdOfTable(SmartConnection connection, ValidTable table) throws SQLException {
        return UpdateChain
                .of(connection, "UPDATE seriabilidad SET seriabilidad.cima = seriabilidad.cima + 1 WHERE seriabilidad.nombre = ?")
                .setString(1, table.getInternalName())
                ;
    }
    
    public static UpdateResult increaseNextIdOfTable(ValidTable table) throws SQLException, ClassNotFoundException, Exception {
        try (SmartConnection connection = new SmartConnection()) {
            return increaseNextIdOfTable(connection, table).run().getResult();
        }
    }
}
