/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;

/**
 *
 * @author Edgar
 */
public class UpdateChain extends StatementSetChain<UpdateChain> {
    LinkedList<PreparedStatement> statements;
    UpdateResult result = UpdateResult.UNDEFINED;
    SmartConnection connection;

    public UpdateChain(
            PreparedStatement statement,
            UpdateResult result,
            SmartConnection connection
    ) {
        this.statements = new LinkedList<>();
        this.statements.add(statement);
        this.result = result;
        this.connection = connection;
    }
    
    public static UpdateChain of(SmartConnection connection, String query) throws SQLException {
        PreparedStatement preparedStatement = connection.getRawConnection().prepareStatement(query);
        return new UpdateChain(preparedStatement, UpdateResult.UNDEFINED, connection);
    }
    
    public UpdateChain chain(Connection connection, String statement) throws SQLException {
        if (result != UpdateResult.FAILURE) {
            PreparedStatement newStatement = connection.prepareStatement(statement);
            statements.add(newStatement);
        }
        return this;
    }
    
    public UpdateChain chain(UpdateChain anotherChain) {
        if (anotherChain.getResult() == UpdateResult.UNDEFINED && result != UpdateResult.FAILURE) {
            statements.addAll(anotherChain.getStatements());
        }
        return this;
    }
    
    /**
     * Executes the chain of statements and automatically commits the 
     * results
     * @return
     * @throws SQLException 
     */
    public UpdateChain run() throws SQLException {
        // this method uses undefined because an update chain
        // is only supposed to be run once
        if (result == UpdateResult.UNDEFINED) {
            result = UpdateResult.SUCCESS;
            for (PreparedStatement statement : statements) {
                int currentResult = statement.executeUpdate();
                if (currentResult == 0) {
                    result = UpdateResult.FAILURE;
                    break;
                }
            }
        }
        
        if (result == UpdateResult.SUCCESS) {
            connection.getRawConnection().commit();
        } else if (result == UpdateResult.FAILURE) {
            connection.getRawConnection().rollback();
        }
        
        return this;
    }
    
    @Override
    public PreparedStatement getPreparedStatement() {
        return statements.getLast();
    }
    
    public UpdateResult getResult() {
        return result;
    }
    
    public LinkedList<PreparedStatement> getStatements() {
        return statements;
    }
}
