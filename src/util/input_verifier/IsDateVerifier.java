/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util.input_verifier;

import javax.swing.JTextField;
import java.sql.Date;

/**
 *
 * @author Edgar
 */
public class IsDateVerifier extends BaseInputVerifier<JTextField> {
    @Override
    public String getInvalidMessage(VerifiableField<JTextField> field) {
        return "El campo " + quoteFieldName(field.getFieldName()) + " debe ser una fecha en el formato YYYY-MM-DD";
    }

    @Override
    public boolean performValidation(JTextField component) {
        try {
            Date value = Date.valueOf(component.getText());
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
}
