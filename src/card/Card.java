/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package card;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.util.Optional;
import javax.swing.JPanel;
import javax.swing.Box;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.border.Border;
import record.Record;

/**
 *
 * @author Edgar
 * @param <RecordType>
 */
public abstract class Card<RecordType extends Record> extends JPanel {
    protected Optional<RecordType> currentRecord;
    
    public Optional<RecordType> getCurrentRecord() {
        return currentRecord;
    }
    
    protected JPanel contentPane;
    
    public Card() {
        super(new GridBagLayout());
        
        setupMargins();
        
        setBackground(util.ProjectColors.WHITE.getColor());
        setBorder(new javax.swing.border.LineBorder(new java.awt.Color(153, 153, 153), 1, true));
        
        contentPane = new JPanel(new java.awt.GridBagLayout());
        
        JButton button = new JButton("Ver más");
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                seeMore();
            }
        });
        
        addAtCoordinates(contentPane, 1, 1, 3, 1.0f, true);
        addAtCoordinates(button, 1, 3, 1, 1.0f, true);
    }
    
    @Override
    public void setLayout(LayoutManager manager) {
        if (contentPane == null) {
            super.setLayout(manager);
        } else {
            contentPane.setLayout(manager);
        }
    }
    
    public abstract void seeMore();
    
    @Override
    public void setBackground(Color color) {
        if (contentPane == null) {
            super.setBackground(color);
        } else {
            contentPane.setBackground(color);
        }
    }
    
    @Override
    public void add(Component component, Object constraints) {
        contentPane.add(component, constraints);
    }
    
    protected void setupMargins() {
        Box.Filler horizontalFiller1 = new Box.Filler(new Dimension(10, 0), new Dimension(10, 0), new Dimension(10, 32767));
        Box.Filler horizontalFiller2 = new Box.Filler(new Dimension(10, 0), new Dimension(10, 0), new Dimension(10, 32767));
        Box.Filler horizontalFiller3 = new Box.Filler(new Dimension(10, 0), new Dimension(10, 0), new Dimension(10, 32767));
        
        Box.Filler verticalFiller1 = new Box.Filler(new Dimension(0, 10), new Dimension(0, 10), new Dimension(32767, 10));
        Box.Filler verticalFiller2 = new Box.Filler(new Dimension(0, 10), new Dimension(0, 10), new Dimension(32767, 10));
        Box.Filler verticalFiller3 = new Box.Filler(new Dimension(0, 10), new Dimension(0, 10), new Dimension(32767, 10));
        
        addAtCoordinates(horizontalFiller1, 0, 0, 1, 0);
        addAtCoordinates(horizontalFiller2, 2, 0, 1, 0);
        addAtCoordinates(horizontalFiller3, 4, 0, 1, 0);
        
        addAtCoordinates(Box.createHorizontalGlue(), 1, 0, 1, 1.0f, true);
        addAtCoordinates(Box.createHorizontalGlue(), 3, 0, 1, 1.0f, true);
        
        addAtCoordinates(verticalFiller1, 0, 0, 1, 0);
        addAtCoordinates(verticalFiller2, 0, 2, 1, 0);
        addAtCoordinates(verticalFiller3, 0, 4, 1, 0);
    }
    
    protected void addAtCoordinates(
            Component component,
            int x,
            int y,
            int width,
            float weightX
    ) {
        addAtCoordinates(component, x, y, width, weightX, false);
    }
    
    protected void addAtCoordinates(
            Component component, 
            int x, 
            int y, 
            int width, 
            float weightX,
            boolean fillHorizontally
    ) {
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = x;
        gridBagConstraints.gridy = y;
        gridBagConstraints.gridwidth = width;
        gridBagConstraints.gridheight = 1;
        gridBagConstraints.weightx = weightX;
        gridBagConstraints.weighty = 0.0;
        if (fillHorizontally) {
            gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        }
        this.addImpl(component, gridBagConstraints, -1);
    }
    
    /**
     * Updates the UI depending on the provided record value
     * @param record 
     */
    public abstract void setCurrentRecord(Optional<RecordType> record);
}
