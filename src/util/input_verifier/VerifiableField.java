/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util.input_verifier;

import java.util.LinkedList;
import javax.swing.JComponent;
import util.Pair;

/**
 *
 * @author Edgar
 */
public class VerifiableField<ComponentType extends JComponent> {
    protected String fieldName;
    protected ComponentType component;
    protected LinkedList<BaseInputVerifier<ComponentType>> verifiers;

    public VerifiableField(String fieldName, ComponentType component) {
        this.fieldName = fieldName;
        this.component = component;
        this.verifiers = new LinkedList<>();
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public ComponentType getComponent() {
        return component;
    }
    
    public VerifiableField<ComponentType> add(BaseInputVerifier<ComponentType> verifier) {
        this.verifiers.add(verifier);
        return this;
    }
    
    public VerifiableFieldChain chain(VerifiableField field) {
        return VerifiableFieldChain.of(this, field);
    }
    
    public ValidationResult performValidation() {
        ValidationResult result = new ValidationResult("", true);
        for (BaseInputVerifier<ComponentType> verifier : verifiers) {
            if (!verifier.performValidation(component)) {
                result.setFirst(verifier.getInvalidMessage(this));
                result.setSecond(false);
                break;
            }
        }
        return result;
    }
}
