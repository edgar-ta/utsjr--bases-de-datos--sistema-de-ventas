/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util.input_verifier;

import javax.swing.JTextField;
import util.TextFormatting;

/**
 *
 * @author Edgar
 */
public class ExactLengthVerifier extends BaseInputVerifier<JTextField> {
    protected int length;

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public ExactLengthVerifier(int length) {
        this.length = length;
    }
    
    @Override
    public String getInvalidMessage(VerifiableField<JTextField> field) {
        return "El campo " + 
                BaseInputVerifier.quoteFieldName(field.getFieldName()) + 
                " debe tener exactamente " + 
                TextFormatting.formatUnits(length, "caracter", "caracteres");
    }

    @Override
    public boolean performValidation(JTextField component) {
        return component.getText().length() == length;
    }
}
