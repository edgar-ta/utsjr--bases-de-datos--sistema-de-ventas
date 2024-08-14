/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package util.functional;

import java.sql.SQLException;

/**
 *
 * @author Edgar
 */
public interface DatabaseErrorProneFunction<InputType, OutputType> {
    public OutputType call(InputType value) throws SQLException, ClassNotFoundException, Exception;
}
