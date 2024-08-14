/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

/**
 *
 * @author Edgar
 */
public class TextFormatting {
    public static String formatNumber(double number) {
        int integerAmount = (int) Math.round(number * 100);
        
        int fractionalPart = integerAmount % 100;
        int integralPart = (integerAmount - fractionalPart) / 100;
        
        String fractionalPartRepresentation;
        if (fractionalPart == 0) {
            fractionalPartRepresentation = "00";
        } else {
            fractionalPartRepresentation = String.valueOf(fractionalPart);
            if (fractionalPart < 10) {
                fractionalPartRepresentation = "0" + fractionalPartRepresentation;
            }
        }
        
        String integralPartRepresentation;
        if (integralPart == 0) {
            integralPartRepresentation = "0";
        } else {
            String tentativeRepresentation = String.valueOf(integralPart);
            int leftOutDigits = tentativeRepresentation.length() % 3;
            String firstBit = tentativeRepresentation.substring(0, leftOutDigits);
            
            integralPartRepresentation = firstBit;
            for (int i = leftOutDigits; i < tentativeRepresentation.length(); i += 3) {
                String nextBit = tentativeRepresentation.substring(i, i + 3);
                if (i != 0) {
                    integralPartRepresentation += ",";
                }
                integralPartRepresentation += nextBit;
            }
        }
        
        return integralPartRepresentation + "." + fractionalPartRepresentation;
    }
    
    public static String formatCurrency(double amount) {
        return "$" + formatNumber(amount);
    }
    
    public static String formatPercentage(double percentage) {
        return "%" + formatNumber(percentage * 100);
    }
    
    public static String formatUnits(int numberOfUnits, String unitName, String pluralUnitForm) {
        String descriptor = numberOfUnits == 1? unitName: pluralUnitForm;
        
        return String.valueOf(numberOfUnits) + " " + descriptor;
    }
    
    public static void main(String[] args) {
        System.out.println(formatCurrency(0));
        System.out.println(formatCurrency(500.98));
        System.out.println(formatCurrency(1200.03));
        
        System.out.println(formatUnits(5, "unidad", "unidades"));
    }
}
