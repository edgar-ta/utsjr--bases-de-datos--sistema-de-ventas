/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import java.util.Optional;

/**
 *
 * @author Edgar
 * @param <ValueType>
 */
public class DisplayableValue<ValueType> extends PrettyPrintable {
    ValueType internalValue;
    
    public DisplayableValue(ValueType internalValue, Optional<String> defaultPrettyName) {
        super(defaultPrettyName);
        this.internalValue = internalValue;
    }

    public ValueType getInternalValue() {
        return internalValue;
    }

    public void setInternalValue(ValueType internalValue) {
        this.internalValue = internalValue;
    }
    
    @Override
    public String getSpareName() {
        return internalValue.toString();
    }
}
