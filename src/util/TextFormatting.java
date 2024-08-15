/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

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
        return formatNumber(percentage * 100) + "%";
    }
    
    public static String formatUnits(int numberOfUnits, String unitName, String pluralUnitForm) {
        String descriptor = numberOfUnits == 1? unitName: pluralUnitForm;
        
        return String.valueOf(numberOfUnits) + " " + descriptor;
    }
    
    public static String formatPhoneNumber(String number) {
        if (number.length() < 10) return number;
        
        String separator = " ";
        
        String firstComponent   = number.substring(0, 3);
        String secondComponent  = number.substring(3, 6);
        String thirdComponent   = number.substring(6, 10);
        
        return firstComponent + separator + secondComponent + separator + thirdComponent;
    }
    
    public static String formatAddress(
            String street,
            String houseNumber,
            String neighborhood,
            String county,
            String state
            ) {
        return street + " " + houseNumber + ", Col. " + neighborhood + ", " + county + ", " + state;
    }
    
    public static String getTwoDigitRepresentation(int digit) {
        String representation = String.valueOf(digit);
        if (digit < 10) {
            representation = "0" + representation;
        }
        return representation;
    }
    
    public static String formatDate(Date date) {
        return  date.getYear() + "-" + 
                getTwoDigitRepresentation(date.getMonth()) + "-" + 
                getTwoDigitRepresentation(date.getDate());
    }
    
    public static void main(String[] args) {
        System.out.println(formatCurrency(0));
        System.out.println(formatCurrency(500.98));
        System.out.println(formatCurrency(1200.03));
        
        System.out.println(formatUnits(5, "unidad", "unidades"));
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        System.out.println(LocalDate.now().format(formatter));
    }
}
