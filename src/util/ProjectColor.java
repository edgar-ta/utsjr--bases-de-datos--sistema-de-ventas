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
public enum ProjectColor {
    WHITE(new Color(248, 250, 252)), 
    BLACK(new Color(30, 41, 59));
    
    private Color color;

    private ProjectColor(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }
}
