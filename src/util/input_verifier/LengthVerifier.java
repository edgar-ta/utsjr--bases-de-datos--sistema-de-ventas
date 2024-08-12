/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util.input_verifier;

import java.util.Optional;
import javax.swing.InputVerifier;
import javax.swing.JComponent;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author Edgar
 */
public class LengthVerifier extends BaseInputVerifier<JTextField> {
    private final int minLength;
    private final int maxLength;

    public LengthVerifier(int minLength, int maxLength) {
        this.minLength = minLength;
        this.maxLength = maxLength;
        if (maxLength <= minLength) throw new IllegalArgumentException("Ill values in the length verifier");
    }

    public int getMinLength() {
        return minLength;
    }

    public int getMaxLength() {
        return maxLength;
    }

    @Override
    public boolean performValidation(JTextField component) {
        String text = component.getText();
        return text.length() >= minLength && text.length() <= maxLength;
    }

    @Override
    public String getInvalidMessage(VerifiableField<JTextField> field) {
        String name = field.getFieldName();
        String text = field.getComponent().getText();
        if (text.length() >= maxLength) {
            return "El campo " + BaseInputVerifier.quoteFieldName(name) + " no puede tener más de " + maxLength + " caracteres";
        }
        if (text.length() <= minLength) {
            return "El campo " + BaseInputVerifier.quoteFieldName(name) + " no puede tener menos de " + minLength + " caracteres";
        }
        return "El campo " + BaseInputVerifier.quoteFieldName(name) + " debe tener entre " + minLength + " y " + maxLength + " caracteres";
    }
}
