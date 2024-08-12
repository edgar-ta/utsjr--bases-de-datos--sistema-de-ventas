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
 * @param <ComponentType>
 */
public abstract class BaseInputVerifier<ComponentType extends JComponent> extends InputVerifier {
    protected String fieldName;
    
    public static String quoteFieldName(String fieldName) {
        return "'" + fieldName + "'";
    }
    
    public abstract String getInvalidMessage(VerifiableField<ComponentType> field);
    public abstract boolean performValidation(ComponentType component);
    
    @Override
    public boolean verify(JComponent input) {
        return performValidation((ComponentType) input);
    }
}
