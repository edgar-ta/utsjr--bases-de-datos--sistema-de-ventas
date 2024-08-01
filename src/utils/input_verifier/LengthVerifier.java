/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils.input_verifier;

import java.util.Optional;
import javax.swing.InputVerifier;
import javax.swing.JComponent;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.text.JTextComponent;

/**
 *
 * @author Edgar
 * @param <K>
 */
public class LengthVerifier<K extends JTextComponent> extends AwareVerifier {
    private int minLength;
    private int maxLength;

    public LengthVerifier(int minLength, int maxLength) {
        this.minLength = minLength;
        this.maxLength = maxLength;
    }

    public int getMinLength() {
        return minLength;
    }

    public int getMaxLength() {
        return maxLength;
    }
    
    @Override
    public Optional<String> getInvalidMessage(JComponent input) {
        K _input = (K) input;
        String name = _input.getName();
        String text = _input.getText();
        if (text.length() > maxLength) {
            return Optional.of("El campo '" + name + "' no puede tener más de " + maxLength + " caracteres");
        }
        if (text.length() < minLength) {
            return Optional.of("El campo '" + name + "' no puede tener menos de " + minLength + " caracteres");
        }
        return Optional.empty();
    }

    @Override
    public boolean verify(JComponent input) {
        K _input = (K) input;
        String text = _input.getText();
        return text.length() > minLength && text.length() < maxLength;
    }
}
