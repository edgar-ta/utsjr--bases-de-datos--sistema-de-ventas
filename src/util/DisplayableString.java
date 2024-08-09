/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import java.util.Optional;

/**
 *
 * @author Edgar
 */
public class DisplayableString extends DisplayableValue<String> {
    public DisplayableString(String internalValue, Optional<String> defaultPrettyName) {
        super(internalValue, defaultPrettyName);
    }
    
    public DisplayableString(String internalName) {
        this(internalName, Optional.empty());
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
    
    @Override
    public String getSpareName() {
        return capitalize(snakeCaseToText(internalValue));
    }
}
