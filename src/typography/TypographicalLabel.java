/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package typography;

import javax.swing.Icon;
import javax.swing.JLabel;
import util.ProjectColor;

/**
 *
 * @author Edgar
 */
public abstract class TypographicalLabel extends JLabel {

    public TypographicalLabel(String text, Icon icon, int horizontalAlignment) {
        super(text, icon, horizontalAlignment);
        stylize();
    }

    public TypographicalLabel(String text, int horizontalAlignment) {
        super(text, horizontalAlignment);
        stylize();
    }

    public TypographicalLabel(String text) {
        super(text);
        stylize();
    }

    public TypographicalLabel(Icon image, int horizontalAlignment) {
        super(image, horizontalAlignment);
        stylize();
    }

    public TypographicalLabel(Icon image) {
        super(image);
        stylize();
    }

    public TypographicalLabel() {
        super();
        stylize();
    }
    
    protected abstract ProjectFont getProjectFont();
    
    protected void stylize() {
        setFont(getProjectFont().getFont());
        setForeground(util.ProjectColor.BLACK.getColor());
    }
}
