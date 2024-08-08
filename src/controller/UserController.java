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

/**
 *
 * @author Edgar
 */
public class UserController {
    public static boolean userExists(String name) throws Exception {
        try (SmartQuery query = ConnectionManager
                .create("SELECT usuario.id FROM usuario WHERE usuario.nombre = ?")
                .setString(1, name)
                .query()
                ) {
            return query.isPopulated();
        }
    }
    
    public static Optional<UserRecord> getUserByName(String name) throws SQLException, ClassNotFoundException, Exception {
        try (SmartQuery query = ConnectionManager
                .create("SELECT * FROM usuario WHERE usuario.nombre = ?")
                .setString(1, name)
                .query()
                ) {
            if (!query.isPopulated()) return Optional.empty();
            
            UserRecord record = new UserRecord();
            
            record.setId(query.getInt("id"));
            record.setNombre(query.getString("nombre"));
            record.setContrasenia(query.getString("contrasenia"));
            record.setTipo(query.getString("tipo"));
            
            return Optional.of(record);
        }
    }
}
