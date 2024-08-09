/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package card;

import component.GenericAddForm;
import component.GenericQueryFrame;
import form.UserForm;
import java.util.Optional;
import javax.swing.JFrame;
import record.UserRecord;
import util.EntityControlData;

/**
 *
 * @author Edgar
 */
public class UserCard extends Card<UserRecord> {

    /**
     * Creates new form UserCard
     */
    public UserCard() {
        super();
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        filler5 = new javax.swing.Box.Filler(new java.awt.Dimension(10, 0), new java.awt.Dimension(10, 0), new java.awt.Dimension(10, 32767));
        nameLabel = new javax.swing.JLabel();
        typeLabel = new javax.swing.JLabel();
        idLabel = new javax.swing.JLabel();

        setBackground(util.ProjectColors.WHITE.getColor());
        setLayout(new java.awt.GridBagLayout());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        add(filler5, gridBagConstraints);

        nameLabel.setFont(new java.awt.Font("Inter 28pt ExtraBold", 0, 18)); // NOI18N
        nameLabel.setForeground(util.ProjectColors.BLACK.getColor());
        nameLabel.setText("Nombre");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        add(nameLabel, gridBagConstraints);

        typeLabel.setFont(new java.awt.Font("Open Sans Light", 0, 12)); // NOI18N
        typeLabel.setForeground(util.ProjectColors.BLACK.getColor());
        typeLabel.setText("Tipo");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        add(typeLabel, gridBagConstraints);

        idLabel.setFont(new java.awt.Font("Inter 28pt Medium", 0, 18)); // NOI18N
        idLabel.setForeground(new java.awt.Color(204, 204, 204));
        idLabel.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        idLabel.setText("#Id");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        add(idLabel, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

    @Override
    public void setCurrentRecord(Optional<UserRecord> record) {
        this.currentRecord = record;
        if (currentRecord.isPresent()) {
            nameLabel.setText(record.get().getNombre());
            idLabel.setText("#" + String.valueOf(record.get().getId()));
            typeLabel.setText(record.get().getTipo().getValue().getPrettyName());
        }
    }

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.Box.Filler filler5;
    private javax.swing.JLabel idLabel;
    private javax.swing.JLabel nameLabel;
    private javax.swing.JLabel typeLabel;
    // End of variables declaration//GEN-END:variables

    @Override
    public void seeMore() {
        UserForm form = new UserForm();
        form.setCurrentRecord(currentRecord);
        GenericAddForm addForm = new GenericAddForm(EntityControlData.USER, form);
        addForm.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        addForm.setVisible(true);
    }
}
