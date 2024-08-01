/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package testing;

import java.lang.reflect.Field;
import java.util.ArrayList;

/**
 *
 * @author Edgar
 */
public class Schema<K> {
    private String internalName;
    private String externalName;
    private Class<K> _class;
    
    public String getImageName(int size) {
        String prefix;
        if (size <= 32) {
            prefix = "icon";
        } else {
            prefix = "img";
        }
        return prefix + "-" + internalName + "-" + String.valueOf(size) + ".jpg";
    }
    
    public String[] getPrettyFieldNames() {
        return null;
    }
    
    public static void main(String[] args) {
        
    }
}
