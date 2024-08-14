/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package form;

import controller.Controller;
import java.sql.Date;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.Optional;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import record.ProductRecord;
import record.SaleRecord;
import util.DatabaseEntity;
import util.PrimaryKey;
import util.UpdateResult;
import util.input_verifier.VerifiableFieldChain;

/**
 *
 * @author Edgar
 */
public class SaleForm extends Form<SaleRecord> {
    public SaleForm() throws SQLException, ClassNotFoundException, Exception {
    }

    public SaleForm(Optional<SaleRecord> currentRecord) throws SQLException, ClassNotFoundException, Exception {
        super(currentRecord);
    }
    
    @Override
    protected void initializeComponents() throws SQLException, ClassNotFoundException, Exception {
        initComponents();
        
        setupComboBoxes();
    }
    
    protected void setupComboBoxes() throws SQLException, ClassNotFoundException, Exception {
        try {
        LinkedList<PrimaryKey> clientPrimaryKeys = Controller.getPrimaryKeysForDisplay(DatabaseEntity.CLIENT);
        LinkedList<PrimaryKey> productPrimaryKeys = Controller.getPrimaryKeysForDisplay(DatabaseEntity.PRODUCT);
        
        System.out.println("The length of the clients is: " + productPrimaryKeys.size());
        
        clientComboBox.getComboBox().setModel(new DefaultComboBoxModel(new Vector<>(clientPrimaryKeys)));
        productComboBox.getComboBox().setModel(new DefaultComboBoxModel(new Vector<>(productPrimaryKeys)));
        
        clientComboBox.getComboBox().setSelectedItem(currentRecord.map((SaleRecord record) -> record.getCliente()).orElse(null));
        productComboBox.getComboBox().setSelectedItem(currentRecord.map((SaleRecord record) -> record.getProducto()).orElse(null));
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }

    }

    @Override
    public void setInterfaceForCurrentRecord(Optional<SaleRecord> currentRecord) {
        clientComboBox.getComboBox().setSelectedItem(currentRecord.map((SaleRecord record) -> record.getCliente()).orElse(null));
        productComboBox.getComboBox().setSelectedItem(currentRecord.map((SaleRecord record) -> record.getProducto()).orElse(null));
        
        folioTextField.getTextField().setText(currentRecord.map((SaleRecord record) -> String.valueOf(record.getFolio())).orElse(""));
        dateTextField.getTextField().setText(currentRecord.map((SaleRecord record) -> record.getFecha().toString()).orElse(""));
        amountTextField.getTextField().setText(currentRecord.map((SaleRecord record) -> String.valueOf(record.getCantidadDeProducto())).orElse(""));
    }

    @Override
    public LinkedList<JTextField> getTextFields() {
        LinkedList<JTextField> list = new LinkedList<>();
        
        list.add(folioTextField.getTextField());
        list.add(dateTextField.getTextField());
        list.add(amountTextField.getTextField());
        
        return list;
    }

    @Override
    public LinkedList<JComboBox> getComboBoxes() {
        LinkedList<JComboBox> list = new LinkedList<>();
        
        list.add(clientComboBox.getComboBox());
        list.add(productComboBox.getComboBox());
        
        return list;
    }

    @Override
    public SaleRecord buildRecord() {
        SaleRecord record = new SaleRecord();
        
        record.setProducto((PrimaryKey) productComboBox.getComboBox().getSelectedItem());
        record.setCliente((PrimaryKey) clientComboBox.getComboBox().getSelectedItem());
        
        record.setCantidadDeProducto(Integer.parseInt(amountTextField.getText()));
        record.setFecha(Date.valueOf(dateTextField.getText()));
        record.setFolio(Integer.parseInt(folioTextField.getText()));
        
        return record;
    }

    @Override
    public boolean isInputDifferentFromRecord() {
        return true;
    }

    @Override
    public VerifiableFieldChain getVerifiableFieldChain() {
        return VerifiableFieldChain.of();
    }

    @Override
    public Integer getRecordId(SaleRecord record) {
        return record.getId();
    }

    @Override
    public boolean recordExists(Integer id) throws SQLException, ClassNotFoundException, Exception {
        return Controller.SALE_CONTROLLER.exists(id);
    }

    @Override
    public UpdateResult deleteRecord(Integer id) throws SQLException, ClassNotFoundException, Exception {
        return Controller.SALE_CONTROLLER.delete(id);
    }

    @Override
    public UpdateResult insertRecord(SaleRecord record) throws SQLException, ClassNotFoundException, Exception {
        return Controller.SALE_CONTROLLER.insert(record);
    }

    @Override
    public UpdateResult updateRecord(SaleRecord record) throws SQLException, ClassNotFoundException, Exception {
        return Controller.SALE_CONTROLLER.update(record);
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

        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(10, 0), new java.awt.Dimension(10, 0), new java.awt.Dimension(10, 32767));
        filler2 = new javax.swing.Box.Filler(new java.awt.Dimension(10, 0), new java.awt.Dimension(10, 0), new java.awt.Dimension(10, 32767));
        filler3 = new javax.swing.Box.Filler(new java.awt.Dimension(10, 0), new java.awt.Dimension(10, 0), new java.awt.Dimension(10, 32767));
        clientComboBox = new component.LabeledComboBox();
        productComboBox = new component.LabeledComboBox();
        folioTextField = new component.LabeledTextField();
        dateTextField = new component.LabeledTextField();
        amountTextField = new component.LabeledTextField();
        filler4 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 10), new java.awt.Dimension(0, 10), new java.awt.Dimension(32767, 10));
        filler5 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 10), new java.awt.Dimension(0, 10), new java.awt.Dimension(32767, 10));
        filler6 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 10), new java.awt.Dimension(0, 10), new java.awt.Dimension(32767, 10));

        setLayout(new java.awt.GridBagLayout());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        add(filler1, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        add(filler2, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        add(filler3, gridBagConstraints);

        clientComboBox.setLabelText("Cliente");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        add(clientComboBox, gridBagConstraints);

        productComboBox.setLabelText("Producto");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        add(productComboBox, gridBagConstraints);

        folioTextField.setLabelText("Folio");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        add(folioTextField, gridBagConstraints);

        dateTextField.setLabelText("Fecha");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        add(dateTextField, gridBagConstraints);

        amountTextField.setLabelText("Cantidad de producto");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        add(amountTextField, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        add(filler4, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        add(filler5, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        add(filler6, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private component.LabeledTextField amountTextField;
    private component.LabeledComboBox clientComboBox;
    private component.LabeledTextField dateTextField;
    private javax.swing.Box.Filler filler1;
    private javax.swing.Box.Filler filler2;
    private javax.swing.Box.Filler filler3;
    private javax.swing.Box.Filler filler4;
    private javax.swing.Box.Filler filler5;
    private javax.swing.Box.Filler filler6;
    private component.LabeledTextField folioTextField;
    private component.LabeledComboBox productComboBox;
    // End of variables declaration//GEN-END:variables
}
