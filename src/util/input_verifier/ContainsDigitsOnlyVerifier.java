/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util.input_verifier;

import javax.swing.JTextField;

/**
 *
 * @author Edgar
 */
public class ContainsDigitsOnlyVerifier extends BaseInputVerifier<JTextField> {

    @Override
    public String getInvalidMessage(VerifiableField<JTextField> field) {
        return "El campo " + quoteFieldName(field.getFieldName()) + " solo puede contener dígitos";
    }

    @Override
    public boolean performValidation(JTextField component) {
        char[] characters = component.getText().toCharArray();
        char[] validCharacters = "0123456789".toCharArray();
        
        outer_loop:
        for (char character : characters) {
            for (char validCharacter : validCharacters) {
                if (character == validCharacter) {
                   continue outer_loop; 
                }
            }
            return false;
        }
        
        return true;
    }
    
}
