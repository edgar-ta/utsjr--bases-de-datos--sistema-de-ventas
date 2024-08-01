/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package utils;

/**
 *
 * @author Edgar
 */
public enum UpdateResult {
    UNDEFINED, SUCCESS, FAILURE;
    
    public static UpdateResult fromInteger(int value) {
        if (value == -1) return UpdateResult.UNDEFINED;
        if (value == 0)  return UpdateResult.FAILURE;
        if (value == 1)  return UpdateResult.SUCCESS;
        throw new IllegalArgumentException("The given value is supposed to be one of -1, 0 or 1");
    }
}
