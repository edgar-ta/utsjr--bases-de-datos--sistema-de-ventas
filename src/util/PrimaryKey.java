/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import java.util.Optional;

/**
 * This class represents a primary key value in one of the
 * entities that the system can handle; it is intended to store both
 * the actual value of an id of a referenced table as well a string
 * that's friendly to the user
 * 
 * @author Edgar
 */
public class PrimaryKey extends DisplayableValue<Integer> {
    public PrimaryKey(Integer internalValue, Optional<String> defaultPrettyName) {
        super(internalValue, defaultPrettyName);
    }
    
    @Override
    public String getSpareName() {
        return String.valueOf(internalValue);
    }
}
