/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package card_supplier;

import card.UserCard;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.Optional;
import javax.swing.JPanel;
import record.UserRecord;
import util.EntityField;

/**
 *
 * @author Edgar
 */
public class UserCardSupplier extends CardSupplier<UserRecord, UserCard> {
    public UserCardSupplier(LinkedList<EntityField> fields) throws ClassNotFoundException, SQLException {
        super(fields);
    }

    @Override
    protected String getQueryString() {
        return "SELECT * FROM usuario";
    }

    @Override
    protected UserRecord buildRecord(ResultSet resultSet) throws SQLException {
        return new UserRecord(
                resultSet.getInt("id"), 
                resultSet.getString("nombre"), 
                resultSet.getString("contrasenia"), 
                resultSet.getString("tipo")
        );
    }

    @Override
    protected UserCard renderRecord(UserRecord record) {
        UserCard card = new UserCard();
        card.setCurrentRecord(Optional.of(record));
        return card;
    }
}
