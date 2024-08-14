/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Optional;

/**
 *
 * @author Edgar
 */
public class EntityField extends DisplayableString {

    public EntityField(String internalValue, Optional<String> prettyName) {
        super(internalValue, prettyName);
    }

    public EntityField(String internalName) {
        this(internalName, Optional.empty());
    }
    
    public EntityField(String internalName, String externalName) {
        this(internalName, Optional.of(externalName));
    }
    
    @Override
    public boolean equals(Object other) {
        if (other == this) return true;
        if (!(other instanceof EntityField)) return false;
        EntityField field = (EntityField) other;
        return 
                this.internalValue.equals(field.getInternalValue()) &&
                this.defaultPrettyName == field.getDefaultPrettyName()
                ;
    }
    
    public static LinkedList<EntityField> of(String ...values) {
        return new LinkedList<>(Arrays.asList(values).stream().map((String value) -> new EntityField(value)).toList());
    }
    
    @Override
    public String toString() {
        return getPrettyName();
    }
}
