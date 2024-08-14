/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package typography;

import java.awt.Font;

/**
 *
 * @author Edgar
 */
public enum ProjectFont {
    HEADING_REGULAR(new java.awt.Font("Inter 28pt ExtraBold", 0, 36)),
    HEADING_SMALL(new java.awt.Font("Inter 28pt ExtraBold", 0, 18)),
    TEXT_REGULAR(new java.awt.Font("Open Sans Medium", 0, 15)),
    LABEL_REGULAR(new java.awt.Font("Open Sans Light", 0, 12)),
    ;

    private ProjectFont(Font font) {
        this.font = font;
    }
    
    private java.awt.Font font;

    public Font getFont() {
        return font;
    }

    public void setFont(Font font) {
        this.font = font;
    }
}
