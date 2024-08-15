/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package form;

import controller.Controller;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.Optional;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import record.CategoryRecord;
import util.UpdateResult;
import util.input_verifier.LengthVerifier;
import util.input_verifier.MaxLengthVerifier;
import util.input_verifier.NotEmptyVerifier;
import util.input_verifier.VerifiableField;
import util.input_verifier.VerifiableFieldChain;

/**
 *
 * @author Edgar
 */
public class CategoryForm extends Form<CategoryRecord> {

    public CategoryForm() throws SQLException, ClassNotFoundException, Exception {
        super();
    }

    public CategoryForm(Optional<CategoryRecord> currentRecord) throws SQLException, ClassNotFoundException, Exception {
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

        nameTextField = new component.LabeledTextField();

        setBackground(util.ProjectColor.WHITE.getColor()
        );
        setLayout(new java.awt.GridBagLayout());

        nameTextField.setLabelText("Nombre");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        add(nameTextField, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

    @Override
    protected void initializeComponents() throws SQLException, ClassNotFoundException, Exception {
        initComponents();
    }

    @Override
    public void setInterfaceForCurrentRecord(Optional<CategoryRecord> currentRecord) {
        nameTextField.getTextField().setText(currentRecord.map((CategoryRecord record) -> record.getNombre()).orElse(""));
    }

    @Override
    public LinkedList<JTextField> getTextFields() {
        LinkedList<JTextField> list = new LinkedList<>();
        list.add(nameTextField.getTextField());
        return list;
    }

    @Override
    public LinkedList<JComboBox> getComboBoxes() {
        return new LinkedList<>();
    }

    @Override
    public CategoryRecord buildRecord() {
        CategoryRecord record = new CategoryRecord();
        
        record.setId(currentRecord.map((CategoryRecord __) -> __.getId()).orElse(-1));
        record.setNombre(nameTextField.getText());
        
        return record;
    }

    @Override
    public boolean isInputDifferentFromRecord() {
        return !currentRecord.get().getNombre().equals(nameTextField.getText());
    }

    @Override
    public VerifiableFieldChain getVerifiableFieldChain() {
        return VerifiableFieldChain.of(
                new VerifiableField<JTextField>("nombre", nameTextField.getTextField())
                    .add(new NotEmptyVerifier())
                    .add(new MaxLengthVerifier(20))
        );
    }

    @Override
    public Integer getRecordId(CategoryRecord record) {
        return record.getId();
    }

    @Override
    public boolean recordExists(Integer id) throws SQLException, ClassNotFoundException, Exception {
        return Controller.CATEGORY_CONTROLLER.exists(id);
    }

    @Override
    public UpdateResult deleteRecord(Integer id) throws SQLException, ClassNotFoundException, Exception {
        return Controller.CATEGORY_CONTROLLER.delete(id);
    }

    @Override
    public UpdateResult insertRecord(CategoryRecord record) throws SQLException, ClassNotFoundException, Exception {
        return Controller.CATEGORY_CONTROLLER.insert(record);
    }

    @Override
    public UpdateResult updateRecord(CategoryRecord record) throws SQLException, ClassNotFoundException, Exception {
        return Controller.CATEGORY_CONTROLLER.update(record);
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private component.LabeledTextField nameTextField;
    // End of variables declaration//GEN-END:variables
}
