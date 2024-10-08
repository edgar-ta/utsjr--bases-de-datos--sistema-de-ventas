/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package component;

import javax.swing.JLabel;

/**
 *
 * @author Edgar
 */
public class CardHeader extends javax.swing.JPanel {

    public CardHeader() {
        this("Entity", 0);
    }
    
    /**
     * Creates new form CardHeader
     */
    public CardHeader(String name, Integer id) {
        initComponents();
        
        setData(name, id);
    }
    
    public void setData(String name, Integer id) {
        nameLabel.setText(name);
        idLabel.setText("#" + String.valueOf(id));    
    }

    public JLabel getIdLabel() {
        return idLabel;
    }

    public JLabel getNameLabel() {
        return nameLabel;
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

        nameLabel = new javax.swing.JLabel();
        idLabel = new javax.swing.JLabel();
        filler5 = new javax.swing.Box.Filler(new java.awt.Dimension(10, 0), new java.awt.Dimension(10, 0), new java.awt.Dimension(10, 32767));

        setBackground(util.ProjectColor.WHITE.getColor());
        setLayout(new java.awt.GridBagLayout());

        nameLabel.setFont(new java.awt.Font("Inter 28pt ExtraBold", 0, 18)); // NOI18N
        nameLabel.setForeground(util.ProjectColor.BLACK.getColor());
        nameLabel.setText("Nombre");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        add(nameLabel, gridBagConstraints);

        idLabel.setFont(new java.awt.Font("Inter 28pt Medium", 0, 18)); // NOI18N
        idLabel.setForeground(new java.awt.Color(204, 204, 204));
        idLabel.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        idLabel.setText("#Id");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        add(idLabel, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        add(filler5, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.Box.Filler filler5;
    private javax.swing.JLabel idLabel;
    private javax.swing.JLabel nameLabel;
    // End of variables declaration//GEN-END:variables
}
