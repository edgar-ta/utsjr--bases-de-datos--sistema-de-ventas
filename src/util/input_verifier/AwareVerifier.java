/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util.input_verifier;

import java.util.Optional;
import javax.swing.InputVerifier;
import javax.swing.JComponent;

/**
 *
 * @author Edgar
 */
public abstract class AwareVerifier extends InputVerifier {
    public abstract Optional<String> getInvalidMessage(JComponent input);
}
