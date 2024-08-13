/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package component;

import javax.swing.Icon;
import javax.swing.JLabel;

/**
 *
 * @author Edgar
 */
public class StylizedLabel extends JLabel {

    public StylizedLabel(String text, Icon icon, int horizontalAlignment) {
        super(text, icon, horizontalAlignment);
        stylize();
    }

    public StylizedLabel(String text, int horizontalAlignment) {
        super(text, horizontalAlignment);
        stylize();
    }

    public StylizedLabel(String text) {
        super(text);
        stylize();
    }

    public StylizedLabel(Icon image, int horizontalAlignment) {
        super(image, horizontalAlignment);
        stylize();
    }

    public StylizedLabel(Icon image) {
        super(image);
        stylize();
    }

    public StylizedLabel() {
        super();
        stylize();
    }
    
    protected void stylize() {
        setFont(new java.awt.Font("Open Sans Light", 0, 12));
        setForeground(util.ProjectColors.BLACK.getColor());
    }
}
