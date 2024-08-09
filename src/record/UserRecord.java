/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package record;

import java.util.Optional;
import util.DisplayableString;

/**
 *
 * @author Edgar
 */
public class UserRecord extends record.Record {
    public enum UserType {
        ADMINISTRATOR(new DisplayableString("Administrador", Optional.of("Admin."))),
        CASHIER(new DisplayableString("Cajero"))
        ;
        
        protected final DisplayableString value;
        
        UserType(DisplayableString value) {
            this.value = value;
        }

        public DisplayableString getValue() {
            return value;
        }
        
        public static Optional<UserType> findUserType(String internalValue) {
            for (UserType type : UserType.values()) {
                if (type.getValue().getInternalValue().equals(internalValue)) {
                    return Optional.of(type);
                }
            }
            return Optional.empty();
        }
        
        @Override
        public String toString() {
            return value.getPrettyName();
        }
    };
    
    int id;
    String nombre;
    String contrasenia;
    UserType tipo;

    public UserRecord(int id, String nombre, String contrasenia, UserType tipo) {
        this.id = id;
        this.nombre = nombre;
        this.contrasenia = contrasenia;
        this.tipo = tipo;
    }

    public UserRecord() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public UserType getTipo() {
        return tipo;
    }

    public void setTipo(UserType tipo) {
        this.tipo = tipo;
    }
}
