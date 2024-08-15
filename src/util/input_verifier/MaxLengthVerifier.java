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
public class MaxLengthVerifier extends BaseInputVerifier<JTextField> {
    protected int maxLength;

    public MaxLengthVerifier(int maxLength) {
        this.maxLength = Math.max(maxLength, 0);
    }

    public int getMaxLength() {
        return maxLength;
    }

    public void setMaxLength(int maxLength) {
        this.maxLength = maxLength;
    }
    
    @Override
    public String getInvalidMessage(VerifiableField<JTextField> field) {
        return "El campo " + 
                BaseInputVerifier.quoteFieldName(field.getFieldName()) + 
                " no puede tener más de " + 
                TextFormatting.formatUnits(maxLength, "caracter", "caracteres");
    }

    @Override
    public boolean performValidation(JTextField component) {
        return component.getText().length() <= maxLength;
    }
    
}
