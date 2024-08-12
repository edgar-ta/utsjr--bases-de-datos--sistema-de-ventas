/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util.input_verifier;

import java.util.Optional;
import javax.swing.JTextField;

/**
 *
 * @author Edgar
 */
public class NotEmptyVerifier extends BaseInputVerifier<JTextField> {
    @Override
    public String getInvalidMessage(VerifiableField<JTextField> field) {
        if (field.getComponent().getText().length() > 0) return "";
        return "El campo '" + field.getFieldName() + "' no puede estar vacío";
    }

    @Override
    public boolean performValidation(JTextField component) {
        return component.getText().length() > 0;
    }
    
}
