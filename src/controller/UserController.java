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
import record.Record;
import util.DatabaseEntity;
import util.SmartConnection;
import util.UpdateChain;
import util.UpdateResult;


/**
 *
 * @author Edgar
 */
public class UserController extends Controller<UserRecord> {
    public boolean existsByName(String name) throws SQLException, ClassNotFoundException, Exception {        
        try (SmartQuery query = ConnectionManager
                .create("SELECT usuario.id FROM usuario WHERE usuario.nombre = ?")
                .setString(1, name)
                .query()
                ) {
            return query.isPopulated();
        }
    }
    
    public Optional<UserRecord> getByName(String name) throws SQLException, ClassNotFoundException, Exception {
        try (SmartQuery query = ConnectionManager
                .create("SELECT * FROM usuario WHERE usuario.nombre = ?")
                .setString(1, name)
                .query()
                ) {
            if (!query.isPopulated()) return Optional.empty();
            return Optional.of(deserializeRecord(query.getResultSet()));
        }
    }

    @Override
    public UpdateChain update(SmartConnection connection, UserRecord record) throws SQLException, ClassNotFoundException, Exception {
        return createChain(
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

    @Override
    public UpdateChain insert(SmartConnection connection, UserRecord record) throws SQLException, ClassNotFoundException, Exception {
        return insertEntity(
                "INSERT INTO usuario VALUES (?, ?, ?, ?)",
                (UpdateChain chain, Integer id) -> chain
                    .setInteger(1, id)
                    .setString(2, record.getNombre())
                    .setString(3, record.getContrasenia())
                    .setString(4, record.getTipo().getValue().getInternalValue())
                ,
                connection
        );
    }

    @Override
    public UserRecord deserializeRecord(ResultSet query) throws SQLException {
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

    @Override
    public DatabaseEntity getDatabaseEntity() {
        return DatabaseEntity.USER;
    }
}
