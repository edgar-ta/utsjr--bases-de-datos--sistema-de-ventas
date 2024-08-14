/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package card_supplier;

import card.UserCard;
import component.GenericAddFrame;
import controller.Controller;
import controller.UserController;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.Optional;
import javax.swing.JPanel;
import record.UserRecord;
import util.EntityField;
import util.functional.DatabaseErrorProneFunction;

/**
 *
 * @author Edgar
 */
public class UserCardSupplier extends CardSupplier<UserRecord, UserCard> {
    public UserCardSupplier(LinkedList<EntityField> fields, DatabaseErrorProneFunction<Optional<UserRecord>, GenericAddFrame> addFrameFunction) throws ClassNotFoundException, SQLException {
        super(fields, addFrameFunction);
    }

    @Override
    protected String getQueryString() {
        return "SELECT * FROM usuario";
    }

    @Override
    protected UserRecord buildRecord(ResultSet resultSet) throws SQLException {
        return Controller.USER_CONTROLLER.deserializeRecord(resultSet);
    }

    @Override
    protected UserCard renderRecord(UserRecord record) {
        return new UserCard(Optional.of(record), addFrameFunction);
    }
}
