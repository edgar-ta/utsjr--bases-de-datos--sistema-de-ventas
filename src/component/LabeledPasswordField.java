/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package component;

import javax.swing.ImageIcon;
import javax.swing.JPasswordField;

/**
 *
 * @author Edgar
 */
public class LabeledPasswordField extends javax.swing.JPanel {
    
    protected String labelText;
    protected boolean isPasswordVisible;
    
    public LabeledPasswordField() {
        this("Label");
    }
    
    public LabeledPasswordField(String labelText) {
        this(labelText, false);
    }

    public LabeledPasswordField(String labelText, boolean isPasswordVisible) {
        initComponents();
        setLabelText(labelText);
        setIsPasswordVisible(isPasswordVisible);
    }

    public String getLabelText() {
        return labelText;
    }

    public void setLabelText(String labelText) {
        this.labelText = labelText;
        this.labelElement.setText(labelText);
    }

    public boolean getIsPasswordVisible() {
        return isPasswordVisible;
    }

    public void setIsPasswordVisible(boolean isPasswordVisible) {
        this.isPasswordVisible = isPasswordVisible;
        ImageIcon imageIcon;
        char echoChar;
        String tooltip;
        
        if (isPasswordVisible) {
            // style the button so that the input is visible
            // but the user can make it
            // invisible
            imageIcon = new ImageIcon(getClass().getResource("/images/20-visibility-off.png"));
            echoChar = (char) 0;
            tooltip = "Ocultar contraseña";
        } else {
            imageIcon = new ImageIcon(getClass().getResource("/images/20-visibility.png"));
            echoChar = '*';
            tooltip = "Mostrar contraseña";
        }
        
        toggleVisbilityButton.setIcon(imageIcon);
        passwordField.setEchoChar(echoChar);
    }

    public JPasswordField getPasswordField() {
        return passwordField;
    }
    
    public String getText() {
        char[] characters = passwordField.getPassword();
        return new String(characters);
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

        labelElement = new javax.swing.JLabel();
        passwordField = new javax.swing.JPasswordField();
        toggleVisbilityButton = new javax.swing.JButton();

        setBackground(util.ProjectColor.WHITE.getColor());
        setLayout(new java.awt.GridBagLayout());

        labelElement.setFont(new java.awt.Font("Open Sans Light", 0, 12)); // NOI18N
        labelElement.setText("Label");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        add(labelElement, gridBagConstraints);

        passwordField.setFont(new java.awt.Font("Open Sans Medium", 0, 15)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        add(passwordField, gridBagConstraints);

        toggleVisbilityButton.setBackground(util.ProjectColor.WHITE.getColor()
        );
        toggleVisbilityButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/20-visibility.png"))); // NOI18N
        toggleVisbilityButton.setToolTipText("Hacer visible");
        toggleVisbilityButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        toggleVisbilityButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                toggleVisbilityButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        add(toggleVisbilityButton, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

    private void toggleVisbilityButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_toggleVisbilityButtonActionPerformed
        setIsPasswordVisible(!isPasswordVisible);
    }//GEN-LAST:event_toggleVisbilityButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel labelElement;
    private javax.swing.JPasswordField passwordField;
    private javax.swing.JButton toggleVisbilityButton;
    // End of variables declaration//GEN-END:variables
}
