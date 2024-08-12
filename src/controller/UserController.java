/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import util.ConnectionManager;
import util.SmartQuery;
import record.UserRecord;
import java.sql.SQLException;
import java.util.Optional;
import java.sql.ResultSet;
import util.SmartConnection;
import util.UpdateChain;
import util.UpdateResult;


/**
 *
 * @author Edgar
 */
public class UserController extends Controller {
    public static boolean userExists(String name) throws SQLException, ClassNotFoundException, Exception {
        try (SmartQuery query = ConnectionManager
                .create("SELECT usuario.id FROM usuario WHERE usuario.nombre = ?")
                .setString(1, name)
                .query()
                ) {
            return query.isPopulated();
        }
    }
    
    public static boolean userExists(Integer id) throws SQLException, ClassNotFoundException, Exception {
        try (SmartQuery query = ConnectionManager
                .create("SELECT usuario.id FROM usuario WHERE usuario.id = ?")
                .setInteger(1, id)
                .query()
                ) {
            return query.isPopulated();
        }
    }
    
    public static UpdateResult deleteUser(Integer id) throws SQLException, ClassNotFoundException, Exception {
        return Controller.runChain((SmartConnection connection) -> UserController.deleteUser(connection, id));
    }
    
    public static UpdateChain deleteUser(SmartConnection connection, Integer id) throws SQLException, ClassNotFoundException, Exception {
        return Controller.createChain(
                "DELETE FROM usuario WHERE usuario.id = ?", 
                (UpdateChain chain) -> chain.setInteger(1, id), 
                connection
        );
    }
    
    public static UpdateChain insertUser(SmartConnection connection, UserRecord record) throws SQLException, ClassNotFoundException, Exception {
        return Controller.insertEntity(
                "INSERT INTO usuario VALUES (?, ?, ?, ?)",
                SeriabilityController.ValidTable.USER,
                (UpdateChain chain, Integer id) -> chain
                    .setInteger(1, id)
                    .setString(2, record.getNombre())
                    .setString(3, record.getContrasenia())
                    .setString(4, record.getTipo().getValue().getInternalValue())
                ,
                connection
        );
    }
    
    public static UpdateResult insertUser(UserRecord record) throws SQLException, ClassNotFoundException, Exception {
        return Controller.runChain((SmartConnection connection) -> UserController.insertUser(connection, record));
    }
    
    public static UpdateChain updateUser(SmartConnection connection, UserRecord record) throws SQLException, ClassNotFoundException, Exception {
        return Controller.createChain(
                "UPDATE usuario SET nombre = ?, contrasenia = ?, tipo = ? WHERE id = ?", 
                (UpdateChain chain) -> chain
                    .setString(1, record.getNombre())
                    .setString(2, record.getContrasenia())
                    .setString(3, record.getTipo().getValue().getInternalValue())
                    .setInteger(4, record.getId())
                , 
                connection
        );
    }
    
    public static UpdateResult updateUser(UserRecord record) throws SQLException, ClassNotFoundException, Exception {
        return Controller.runChain((SmartConnection connection) -> UserController.updateUser(connection, record));
    }
    
    public static UserRecord buildUser(ResultSet query) throws SQLException {
        UserRecord record = new UserRecord();

        record.setId(query.getInt("id"));
        record.setNombre(query.getString("nombre"));
        record.setContrasenia(query.getString("contrasenia"));

        String recordedType = query.getString("tipo");
        Optional<UserRecord.UserType> type = UserRecord.UserType.findUserType(recordedType);
        if (type.isEmpty()) {
            throw new IllegalArgumentException("The user's type is unrecognized: " + recordedType);
        }

        record.setTipo(type.get());
        
        return record;
    }
    
    public static Optional<UserRecord> getUserByName(String name) throws SQLException, ClassNotFoundException, Exception {
        try (SmartQuery query = ConnectionManager
                .create("SELECT * FROM usuario WHERE usuario.nombre = ?")
                .setString(1, name)
                .query()
                ) {
            if (!query.isPopulated()) return Optional.empty();
            return Optional.of(UserController.buildUser(query.getResultSet()));
        }
    }
}
