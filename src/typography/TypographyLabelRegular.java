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
public class TypographyLabelRegular extends TypographicalLabel {

    @Override
    protected ProjectFont getProjectFont() {
        return typography.ProjectFont.LABEL_REGULAR;
    }
}
