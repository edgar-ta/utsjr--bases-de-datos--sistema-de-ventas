/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package util;

import java.util.Optional;

/**
 *
 * @author Edgar
 */
public abstract class PrettyPrintable {
    protected Optional<String> defaultPrettyName;

    public PrettyPrintable(Optional<String> defaultPrettyName) {
        this.defaultPrettyName = defaultPrettyName;
    }
    
    public PrettyPrintable(String defaultPrettyName) {
        this(Optional.of(defaultPrettyName));
    }

    public Optional<String> getDefaultPrettyName() {
        return defaultPrettyName;
    }

    public void setDefaultPrettyName(Optional<String> defaultPrettyName) {
        this.defaultPrettyName = defaultPrettyName;
    }
    
    public abstract String getSpareName();
    
    public String getPrettyName() {
        if (defaultPrettyName.isPresent()) {
            return defaultPrettyName.get();
        }
        return getSpareName();
    }
}
