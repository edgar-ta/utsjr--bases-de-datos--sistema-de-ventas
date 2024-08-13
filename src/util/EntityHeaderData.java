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
public enum EntityHeaderData {
    CATEGORY(
            DatabaseEntity.CATEGORY,
            new Color(250, 204, 21)),
    CLIENT(
            DatabaseEntity.CLIENT,
            new Color(249, 115, 22)
    ),
    PRODUCT(
            DatabaseEntity.PRODUCT,
            new Color(14, 165, 233)
    ),
    SALE(
            DatabaseEntity.SALE,
            new Color(220, 38, 38)
    ),
    SUPPLIER(
            DatabaseEntity.SUPPLIER,
            new Color(194, 65, 12)
    ),
    USER(
            DatabaseEntity.USER,
            new Color(162, 28, 175)
    )
    ;
    
    protected DatabaseEntity databaseEntity;
    protected Color color;

    private EntityHeaderData(DatabaseEntity databaseEntity, Color color) {
        this.databaseEntity = databaseEntity;
        this.color = color;
    }

    public String getIconName() {
        String name = this.name();
        return "64-" + name.toLowerCase() + ".png";
    }

    public Color getColor() {
        return color;
    }
    
    public String getEntityName() {
        return databaseEntity.getEntityName().getPrettyName();
    }
}
