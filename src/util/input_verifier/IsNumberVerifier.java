/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util.input_verifier;

import javax.swing.JTextField;

/**
 *
 * @author Edgar
 * @param <NumberType>
 */
public class IsNumberVerifier<NumberType> extends BaseInputVerifier<JTextField> {
    
    public static final IsNumberVerifier<Integer> INTEGER_VERIFIER = new IsNumberVerifier<>(NumberClass.INTEGER);
    public static final IsNumberVerifier<Double> DOUBLE_VERIFIER = new IsNumberVerifier<>(NumberClass.DOUBLE);
    
    protected NumberClass number;

    public IsNumberVerifier(NumberClass number) {
        this.number = number;
    }
    
    @Override
    public String getInvalidMessage(VerifiableField<JTextField> field) {
        return "El campo " + quoteFieldName(field.getFieldName()) + " debe ser un número " + number.getName() + " válido";
    }
    
    @FunctionalInterface
    public interface NumberParser<OutputType> {
        public OutputType parse(String text);
    };
    
    public enum NumberClass {
        INTEGER(
                "entero",
                (String text) -> Integer.valueOf(text)
        ),
        DOUBLE(
                "decimal",
                (String text) -> Double.valueOf(text)
        )
        ;
        
        protected String name;
        protected NumberParser parser;

        public String getName() {
            return name;
        }

        public NumberParser getParser() {
            return parser;
        }

        private NumberClass(String name, NumberParser parser) {
            this.name = name;
            this.parser = parser;
        }
    };
    

    @Override
    public boolean performValidation(JTextField component) {
        try {
            NumberType value = (NumberType) number.getParser().parse(component.getText());
            return true;
        } catch (NumberFormatException exception) {
            return false;
        }
    }
    
}
