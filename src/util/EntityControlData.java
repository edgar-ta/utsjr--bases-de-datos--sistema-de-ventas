/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package util;

import java.awt.Color;

/**
 *
 * @author Edgar
 */
public enum EntityControlData {
    CATEGORY("Categoría", new Color(250, 204, 21)),
    CLIENT("Cliente", new Color(249, 115, 22)),
    PRODUCT("Producto", new Color(14, 165, 233)),
    SALE("Venta", new Color(220, 38, 38)),
    SUPPLIER("Proveedor", new Color(194, 65, 12)),
    USER("Usuario", new Color(162, 28, 175))
    ;
    
    protected String externalName;
    protected Color color;

    private EntityControlData(String internalName, Color color) {
        this.externalName = internalName;
        this.color = color;
    }

    public String getExternalName() {
        return externalName;
    }

    public String getIconName() {
        String name = this.name();
        return "64-" + name.toLowerCase() + ".png";
    }

    public Color getColor() {
        return color;
    }
}
