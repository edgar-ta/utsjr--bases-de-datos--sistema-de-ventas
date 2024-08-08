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
public class EntityField {
    protected String internalName;
    protected Optional<String> defaultPrettyName;

    public EntityField(String internalName, Optional<String> prettyName) {
        this.internalName = internalName;
        this.defaultPrettyName = prettyName;
    }

    public EntityField(String internalName) {
        this(internalName, Optional.empty());
    }
    
    @Override
    public boolean equals(Object other) {
        if (other == this) return true;
        if (!(other instanceof EntityField)) return false;
        EntityField field = (EntityField) other;
        return 
                this.internalName == field.getInternalName() &&
                this.defaultPrettyName == field.getDefaultPrettyName()
                ;
    }
    
    public static LinkedList<EntityField> of(String ...values) {
        return new LinkedList<EntityField>(Arrays.asList(values).stream().map((String value) -> new EntityField(value)).toList());
    }
    
    public static String snakeCaseToText(String text) {
        String[] components = text.trim().split("_");
        String result = "";
        for (String component : components) {
            if (component.length() > 0) {
                result += component;
            }
        }
        return result;
    }
    
    public static String capitalize(String word) {
        return word.substring(0, 1).toUpperCase() + word.substring(1);
    }
    
    public String getPrettyName() {
        if (defaultPrettyName.isPresent()) {
            return defaultPrettyName.get();
        }
        return capitalize(snakeCaseToText(internalName));
    }

    public String getInternalName() {
        return internalName;
    }

    public Optional<String> getDefaultPrettyName() {
        return defaultPrettyName;
    }
    
    @Override
    public String toString() {
        return getPrettyName();
    }
}
