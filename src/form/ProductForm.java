/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package form;

import component.LabeledComboBox;
import component.LabeledTextField;
import controller.Controller;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Optional;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import record.ProductRecord;
import util.DatabaseEntity;
import util.PrimaryKey;
import util.UpdateResult;
import util.input_verifier.IsNumberVerifier;
import util.input_verifier.MaxLengthVerifier;
import util.input_verifier.ComparisonValueVerifier;
import util.input_verifier.NotEmptyVerifier;
import util.input_verifier.NotUnselectedVerifier;
import util.input_verifier.VerifiableField;
import util.input_verifier.VerifiableFieldChain;

/**
 *
 * @author Edgar
 */
public class ProductForm extends Form<ProductRecord> {
    public ProductForm() throws SQLException, ClassNotFoundException, Exception {
        super();
    }

    public ProductForm(Optional<ProductRecord> currentRecord) throws SQLException, ClassNotFoundException, Exception {
        super(currentRecord);
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
        filler4 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 10), new java.awt.Dimension(0, 10), new java.awt.Dimension(32767, 10));
        filler5 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 10), new java.awt.Dimension(0, 10), new java.awt.Dimension(32767, 10));
        filler6 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 10), new java.awt.Dimension(0, 10), new java.awt.Dimension(32767, 10));
        filler7 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 10), new java.awt.Dimension(0, 10), new java.awt.Dimension(32767, 10));
        filler8 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 10), new java.awt.Dimension(0, 10), new java.awt.Dimension(32767, 10));
        discountTextField = new component.LabeledTextField();
        codeTextField = new component.LabeledTextField();
        categoryComboBox = new component.LabeledComboBox();
        supplierComboBox = new component.LabeledComboBox();
        nameTextField = new component.LabeledTextField();
        priceTextField = new component.LabeledTextField();
        stockTextField = new component.LabeledTextField();

        setBackground(util.ProjectColor.WHITE.getColor());
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
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        add(filler7, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        add(filler8, gridBagConstraints);

        discountTextField.setLabelText("Descuento");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        add(discountTextField, gridBagConstraints);

        codeTextField.setLabelText("Código");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        add(codeTextField, gridBagConstraints);

        categoryComboBox.setLabelText("Categoría");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        add(categoryComboBox, gridBagConstraints);

        supplierComboBox.setLabelText("Proveedor");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        add(supplierComboBox, gridBagConstraints);

        nameTextField.setLabelText("Nombre");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        add(nameTextField, gridBagConstraints);

        priceTextField.setLabelText("Precio");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        add(priceTextField, gridBagConstraints);

        stockTextField.setLabelText("Stock");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        add(stockTextField, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

    @Override
    protected void initializeComponents() throws SQLException, ClassNotFoundException, Exception {
        initComponents();
        
        setupComboBoxes();
    }
    
    public void setupComboBoxes() throws SQLException, ClassNotFoundException, Exception {
        LinkedList<PrimaryKey> categoryPrimaryKeys = Controller.getPrimaryKeysForDisplay(DatabaseEntity.CATEGORY);
        LinkedList<PrimaryKey> supplierPrimaryKeys = Controller.getPrimaryKeysForDisplay(DatabaseEntity.SUPPLIER);
        
        categoryComboBox.getComboBox().setModel(new DefaultComboBoxModel(new Vector<>(categoryPrimaryKeys)));
        supplierComboBox.getComboBox().setModel(new DefaultComboBoxModel(new Vector<>(supplierPrimaryKeys)));
        
        categoryComboBox.getComboBox().setSelectedItem(currentRecord.map((ProductRecord record) -> record.getCategoria()).orElse(null));
        supplierComboBox.getComboBox().setSelectedItem(currentRecord.map((ProductRecord record) -> record.getProveedor()).orElse(null));
    }

    @Override
    public void setInterfaceForCurrentRecord(Optional<ProductRecord> currentRecord) {
        if (currentRecord.isPresent()) {
            ProductRecord record = currentRecord.get();
            
            nameTextField.getTextField().setText(record.getNombre());
            codeTextField.getTextField().setText(record.getCodigo());
            
            discountTextField.getTextField().setText(String.valueOf(record.getDescuento()));
            priceTextField.getTextField().setText(String.valueOf(record.getPrecio()));
            
            stockTextField.getTextField().setText(String.valueOf(record.getStock()));
        } else {
            getTextFields().forEach((JTextField field) -> field.setText(""));
        }
        
        categoryComboBox.getComboBox().setSelectedItem(currentRecord.map((ProductRecord record) -> record.getCategoria()).orElse(null));
        supplierComboBox.getComboBox().setSelectedItem(currentRecord.map((ProductRecord record) -> record.getProveedor()).orElse(null));
    }

    @Override
    public LinkedList<JTextField> getTextFields() {
        return new LinkedList<JTextField>(new LinkedList<>(Arrays.asList(
                nameTextField, 
                codeTextField, 
                discountTextField, 
                priceTextField, 
                stockTextField
        )).stream().map((LabeledTextField field) -> field.getTextField()).toList());
    }

    @Override
    public LinkedList<JComboBox> getComboBoxes() {
        return new LinkedList<JComboBox>(new LinkedList<>(Arrays.asList(
                categoryComboBox,
                supplierComboBox
        )).stream().map((LabeledComboBox comboBox) -> comboBox.getComboBox()).toList());
    }

    @Override
    public ProductRecord buildRecord() {
        ProductRecord record = new ProductRecord();
        
        record.setId(currentRecord.map((ProductRecord previousRecord) -> previousRecord.getId()).orElse(-1));
        record.setCategoria((PrimaryKey) categoryComboBox.getComboBox().getSelectedItem());
        record.setCodigo(codeTextField.getText());
        record.setDescuento(Double.parseDouble(discountTextField.getText()) / 100.0);
        record.setNombre(nameTextField.getText());
        record.setPrecio(Double.parseDouble(priceTextField.getText()));
        record.setProveedor((PrimaryKey) supplierComboBox.getComboBox().getSelectedItem());
        record.setStock(Integer.parseInt(stockTextField.getText()));
        
        return record;
    }

    @Override
    public boolean isInputDifferentFromRecord() {
        ProductRecord record = currentRecord.get();
        
        if (!codeTextField.getText().equals(record.getCodigo())) return true;
        if (!nameTextField.getText().equals(record.getNombre())) return true;
        
        try {
            if (Double.parseDouble(priceTextField.getText()) != record.getPrecio()) return true;
            if (Double.parseDouble(discountTextField.getText()) != record.getDescuento()) return true;
        
            if (Integer.parseInt(stockTextField.getText()) != record.getStock()) return true;
        } catch (NumberFormatException ex) {
            return true;
        }
        
        if (!record.getCategoria().equals(categoryComboBox.getComboBox().getSelectedItem())) return true;
        if (!record.getProveedor().equals(supplierComboBox.getComboBox().getSelectedItem())) return true;
        
        return false;
    }

    @Override
    public VerifiableFieldChain getVerifiableFieldChain() {
        return VerifiableFieldChain.of(
                new VerifiableField<JTextField>("código", codeTextField.getTextField())
                    .add(new NotEmptyVerifier())
                    .add(new MaxLengthVerifier(20))
                ,
                new VerifiableField<JTextField>("nombre", nameTextField.getTextField())
                    .add(new NotEmptyVerifier())
                    .add(new MaxLengthVerifier(40))
                ,
                new VerifiableField<JTextField>("precio", priceTextField.getTextField())
                    .add(new NotEmptyVerifier())
                    .add(IsNumberVerifier.DOUBLE_VERIFIER)
                    .add(ComparisonValueVerifier.minDoubleValue(0.0))
                ,
                new VerifiableField<JTextField>("descuento", discountTextField.getTextField())
                    .add(new NotEmptyVerifier())
                    .add(IsNumberVerifier.DOUBLE_VERIFIER)
                    .add(ComparisonValueVerifier.minDoubleValue(0.0))
                    .add(ComparisonValueVerifier.maxDoubleValue(100.0))
                ,
                new VerifiableField<JTextField>("stock", stockTextField.getTextField())
                    .add(new NotEmptyVerifier())
                    .add(IsNumberVerifier.INTEGER_VERIFIER)
                    .add(ComparisonValueVerifier.minIntegerValue(0))
                ,
                new VerifiableField<JComboBox>("categoría", categoryComboBox.getComboBox())
                    .add(new NotUnselectedVerifier())
                ,
                new VerifiableField<JComboBox>("proveedor", supplierComboBox.getComboBox())
                    .add(new NotUnselectedVerifier())
        );
    }

    @Override
    public Integer getRecordId(ProductRecord record) {
        return record.getId();
    }

    @Override
    public boolean recordExists(Integer id) throws SQLException, ClassNotFoundException, Exception {
        return Controller.PRODUCT_CONTROLLER.exists(id);
    }

    @Override
    public UpdateResult deleteRecord(Integer id) throws SQLException, ClassNotFoundException, Exception {
        return Controller.PRODUCT_CONTROLLER.delete(id);
    }

    @Override
    public UpdateResult insertRecord(ProductRecord record) throws SQLException, ClassNotFoundException, Exception {
        return Controller.PRODUCT_CONTROLLER.insert(record);
    }

    @Override
    public UpdateResult updateRecord(ProductRecord record) throws SQLException, ClassNotFoundException, Exception {
        return Controller.PRODUCT_CONTROLLER.update(record);
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private component.LabeledComboBox categoryComboBox;
    private component.LabeledTextField codeTextField;
    private component.LabeledTextField discountTextField;
    private javax.swing.Box.Filler filler1;
    private javax.swing.Box.Filler filler2;
    private javax.swing.Box.Filler filler3;
    private javax.swing.Box.Filler filler4;
    private javax.swing.Box.Filler filler5;
    private javax.swing.Box.Filler filler6;
    private javax.swing.Box.Filler filler7;
    private javax.swing.Box.Filler filler8;
    private component.LabeledTextField nameTextField;
    private component.LabeledTextField priceTextField;
    private component.LabeledTextField stockTextField;
    private component.LabeledComboBox supplierComboBox;
    // End of variables declaration//GEN-END:variables
}
