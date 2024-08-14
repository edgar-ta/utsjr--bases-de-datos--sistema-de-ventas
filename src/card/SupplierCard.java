/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package card;

import component.GenericAddFrame;
import form.Form;
import form.SupplierForm;
import java.util.Optional;
import record.SupplierRecord;
import util.EntityHeaderData;
import util.functional.DatabaseErrorProneFunction;

/**
 *
 * @author Edgar
 */
public class SupplierCard extends Card<SupplierRecord> {

    public SupplierCard() {
        super();
    }

    public SupplierCard(Optional<SupplierRecord> currentRecord, DatabaseErrorProneFunction<Optional<SupplierRecord>, GenericAddFrame> addFrameFunction) {
        super(currentRecord, addFrameFunction);
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

        cardHeader = new component.CardHeader();
        addressLabel = new typography.TypographyLabelRegular();
        rfcLabel = new typography.TypographyLabelRegular();
        telephoneNumberLabel = new typography.TypographyLabelRegular();
        cellphoneLabel = new typography.TypographyLabelRegular();

        setBackground(util.ProjectColor.WHITE.getColor());
        setLayout(new java.awt.GridBagLayout());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        add(cardHeader, gridBagConstraints);

        addressLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/12-home.png"))); // NOI18N
        addressLabel.setText("stylizedLabel1");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        add(addressLabel, gridBagConstraints);

        rfcLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/12-barcode.png"))); // NOI18N
        rfcLabel.setText("stylizedLabel1");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        add(rfcLabel, gridBagConstraints);

        telephoneNumberLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/12-call.png"))); // NOI18N
        telephoneNumberLabel.setText("stylizedLabel1");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        add(telephoneNumberLabel, gridBagConstraints);

        cellphoneLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/12-ad-units.png"))); // NOI18N
        cellphoneLabel.setText("stylizedLabel1");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        add(cellphoneLabel, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

    @Override
    public void updateInterfaceForCurrentRecord() {
        if (currentRecord.isPresent()) {
            cardHeader.setData(currentRecord.get().getNombre(), currentRecord.get().getId());
            addressLabel.setText(currentRecord.get().getDireccion());
            rfcLabel.setText(currentRecord.get().getRfc());
            telephoneNumberLabel.setText(currentRecord.get().getTelefono());
            cellphoneLabel.setText(currentRecord.get().getCelular());
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private typography.TypographyLabelRegular addressLabel;
    private component.CardHeader cardHeader;
    private typography.TypographyLabelRegular cellphoneLabel;
    private typography.TypographyLabelRegular rfcLabel;
    private typography.TypographyLabelRegular telephoneNumberLabel;
    // End of variables declaration//GEN-END:variables

    @Override
    public void initializeComponents() {
        initComponents();
    }
}
