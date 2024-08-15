/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util.input_verifier;

import java.util.function.BiFunction;
import java.util.function.Function;
import javax.swing.JTextField;

/**
 *
 * @author Edgar
 * @param <NumberType>
 */
public class ComparisonValueVerifier<NumberType extends Comparable<NumberType>> extends IsNumberVerifier<NumberType> {
    
    public enum ComparisonFunction {
        GREATER_THAN_OR_EQUAL((Integer value) -> value >= 0, "mayor o igual"),
        LESSER_THAN_OR_EQUAL((Integer value) -> value < 0, "menor o igual"),
        EQUAL((Integer value) -> value == 0, "igual");

        protected Function<Integer, Boolean> function;
        protected String description;
        
        private ComparisonFunction(Function<Integer, Boolean> function, String description) {
            this.function = function;
            this.description = description;
        }

        public Function<Integer, Boolean> getFunction() {
            return function;
        }
        
        public String getDescription() {
            return description;
        }
    };
    
    public static ComparisonValueVerifier<Double> minDoubleValue(Double minValue) {
        return new ComparisonValueVerifier<>(
                IsNumberVerifier.NumberClass.DOUBLE, 
                ComparisonFunction.GREATER_THAN_OR_EQUAL,
                minValue
        );
    }
    
    public static ComparisonValueVerifier<Integer> minIntegerValue(Integer minValue) {
        return new ComparisonValueVerifier<>(
                IsNumberVerifier.NumberClass.INTEGER, 
                ComparisonFunction.GREATER_THAN_OR_EQUAL,
                minValue
        );
    }
    
    public static ComparisonValueVerifier<Double> maxDoubleValue(Double minValue) {
        return new ComparisonValueVerifier<>(
                IsNumberVerifier.NumberClass.DOUBLE, 
                ComparisonFunction.LESSER_THAN_OR_EQUAL,
                minValue
        );
    }
    
    public static ComparisonValueVerifier<Integer> maxIntegerValue(Integer minValue) {
        return new ComparisonValueVerifier<>(
                IsNumberVerifier.NumberClass.INTEGER, 
                ComparisonFunction.LESSER_THAN_OR_EQUAL,
                minValue
        );
    }
    
    public NumberType minValue;
    public ComparisonFunction comparisonFunction;
    
    public ComparisonValueVerifier(NumberClass number, ComparisonFunction function, NumberType minValue) {
        super(number);
        this.minValue = minValue;
        this.comparisonFunction = function;
    }
    
    @Override
    public String getInvalidMessage(VerifiableField<JTextField> field) {
        return "El campo " + quoteFieldName(field.getFieldName()) + " debe ser un número " + number.getName() + " " + comparisonFunction.getDescription() + " a " + String.valueOf(minValue);
    }
    
    @Override
    public boolean performValidation(JTextField component) {
        try {
            NumberType value = (NumberType) number.getParser().parse(component.getText());
            return comparisonFunction.getFunction().apply(value.compareTo(minValue));
        } catch (Exception e) {
            return false;
        }
    }
}
