/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util.input_verifier;

import java.util.LinkedList;
import java.util.Arrays;
import util.Pair;

/**
 *
 * @author Edgar
 */
public class VerifiableFieldChain {
    protected LinkedList<VerifiableField> fields;

    public VerifiableFieldChain(LinkedList<VerifiableField> fields) {
        this.fields = fields;
    }
    
    public static VerifiableFieldChain of(VerifiableField ... values) {
        return new VerifiableFieldChain(new LinkedList<>(Arrays.asList(values)));
    }
    
    public ValidationResult performValidation() {
        ValidationResult result = new ValidationResult("", true);
        for (VerifiableField field : fields) {
            ValidationResult pair = field.performValidation();
            if (!pair.getSecond()) {
                return pair;
            }
        }
        return result;
    }
}
