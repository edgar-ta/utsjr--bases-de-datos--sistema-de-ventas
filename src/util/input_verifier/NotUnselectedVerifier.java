/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util.input_verifier;

import java.util.Optional;
import javax.swing.JComboBox;

/**
 *
 * @author Edgar
 * @param <ItemType>
 */
public class NotUnselectedVerifier<ItemType> extends BaseInputVerifier<JComboBox<ItemType>> {

    @Override
    public String getInvalidMessage(VerifiableField<JComboBox<ItemType>> field) {
        return "Se debe seleccionar una opción para el campo " +  BaseInputVerifier.quoteFieldName(field.getFieldName());
    }

    @Override
    public boolean performValidation(JComboBox<ItemType> component) {
        return component.getSelectedIndex() != -1;
    }
    
}
