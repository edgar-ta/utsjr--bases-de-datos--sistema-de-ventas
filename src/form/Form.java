/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package form;

import controller.UserController;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.Optional;
import java.util.function.BiConsumer;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import record.Record;
import util.UpdateResult;
import util.input_verifier.VerifiableFieldChain;

/**
 *
 * @author Edgar
 * @param <RecordType>
 */
public abstract class Form<RecordType extends Record> extends JPanel {
    public enum FormModality {
        SINGLE_EDITION,
        MULTIPLE_EDITION,
        NONE
        ;
    };
    
    protected Optional<RecordType> currentRecord;
    protected Runnable deletionListener = () -> {};
    protected Runnable recordChangeListener = () -> {};
    protected FormModality modality = FormModality.NONE;
    
    public Form() {
        this(Optional.empty());
    }
    
    public Form(Optional<RecordType> currentRecord) {
        super();
        initializeComponents();
        setCurrentRecord(currentRecord);
    }
    
    protected abstract void initializeComponents();
    
    /**
     * Adds listeners to the text fields and combo boxes
     * of the form that call the given runnable whenever
     * the inputs are modified; i. e., after calling this method,
     * every time a text field is edited or a combo box is changed
     * the runnable will be called.
     * 
     * This function is useful for doing things such as enabling
     * buttons immediately after certain criteria are met in the form,
     * and indeed, in the <code>GenericAddForm</code> class, it is used in
     * conjunction with the <code>isRecordValid</code> and 
     * <code>isRecordDifferent</code> functions to 
     * enable the insert/update button
     * 
     * @param callback 
     */
    public void addEditionListener(Runnable callback) {
        getTextFields().forEach((JTextField field) -> {
            field.getDocument().addDocumentListener(new DocumentListener() {
                @Override
                public void insertUpdate(DocumentEvent e) {
                    callback.run();
                }

                @Override
                public void removeUpdate(DocumentEvent e) {
                    callback.run();
                }

                @Override
                public void changedUpdate(DocumentEvent e) {}
            });
        });
        getComboBoxes().forEach((JComboBox comboBox) -> {
            comboBox.addActionListener((ActionEvent e) -> {
                callback.run();
            });
        });        
    }
    
    public void setDeletionListener(Runnable deletionListener) {
        this.deletionListener = deletionListener;
    }
    
    public void setRecordChangeListener(Runnable recordChangeListener) {
        this.recordChangeListener = recordChangeListener;
    }

    public Optional<RecordType> getCurrentRecord() {
        return currentRecord;
    }

    public void setCurrentRecord(Optional<RecordType> currentRecord) {
        if (modality == FormModality.NONE) {
            if (currentRecord.isEmpty()) {
                modality = FormModality.MULTIPLE_EDITION;
            } else {
                modality = FormModality.SINGLE_EDITION;
            }
        }
        
        if (this.currentRecord != currentRecord) {
            this.currentRecord = currentRecord;
            recordChangeListener.run();
        }
        setInterfaceForCurrentRecord(currentRecord);
    };
    
    public abstract void setInterfaceForCurrentRecord(Optional<RecordType> currentRecord);
    
    public abstract LinkedList<JTextField> getTextFields();
    public abstract LinkedList<JComboBox> getComboBoxes();
    
    public void delete() throws SQLException, ClassNotFoundException, Exception {
        if (currentRecord.isPresent()) {
            Integer recordId = getRecordId(currentRecord.get());
            if (recordExists(recordId)) {
                UpdateResult result = deleteRecord(recordId);
                if (result == UpdateResult.SUCCESS) {
                    showSuccessfulActionMessage("eliminó", "eliminado");
                    deletionListener.run();
                } else if (result == UpdateResult.FAILURE) {
                    showFailedActionMessage("eliminar");
                }
            } else {
                JOptionPane.showMessageDialog(
                        this, 
                        "El registro especificado ya no existe en la base de datos", 
                        "Registro no existente", 
                        JOptionPane.INFORMATION_MESSAGE
                );
            }
        }
    }
    
    /**
     * Tries to insert the current record specified in the form; it fails if
     * validations are not met
     * @throws SQLException
     * @throws ClassNotFoundException
     * @throws Exception 
     */
    public void insert() throws SQLException, ClassNotFoundException, Exception {
        if (!isRecordValid()) {
            showInvalidInputMessage();
            return;
        }
        
        if (showConfirmationDialog("insertar") != JOptionPane.YES_OPTION) return;
        
        RecordType record = buildRecord();
        UpdateResult result = insertRecord(record);
        
        if (result == UpdateResult.SUCCESS) {
            showSuccessfulActionMessage("insertó", "insertado");
            
            if (modality == FormModality.MULTIPLE_EDITION) {
                setCurrentRecord(Optional.empty());
            } else if (modality == FormModality.SINGLE_EDITION) {
                setCurrentRecord(Optional.of(record));
            }
            
        } else if (result == UpdateResult.FAILURE) {
            showFailedActionMessage("insertar");
        }
    }
    
    public void update() throws SQLException, ClassNotFoundException, Exception {
        if (!isRecordDifferent()) {
            JOptionPane.showMessageDialog(
                    this, 
                    "Los valores del registro no han sido modificados todavía (no se pueden actualizar)",
                    "Valores sin modificar", 
                    JOptionPane.INFORMATION_MESSAGE
            );
            return;
        }
        
        if (!isRecordValid()) {
            showInvalidInputMessage();
            return;
        }
        
        if (showConfirmationDialog("actualizar") != JOptionPane.YES_OPTION) return;
        
        RecordType record = buildRecord();
        UpdateResult result = updateRecord(record);
        
        if (result == UpdateResult.SUCCESS) {
            showSuccessfulActionMessage("actualizó", "actualizado");
        } else if (result == UpdateResult.FAILURE) {
            showFailedActionMessage("actualizar");
        }
    }
    
    /**
     * Shows a message that tells the user why their input
     * is not valid; it leverages the getInvalidInputMessage
     * function
     */
    public void showInvalidInputMessage() {
        JOptionPane.showMessageDialog(
                this, 
                getInvalidInputMessage(), 
                "Valores erróneos", 
                JOptionPane.INFORMATION_MESSAGE
        );
    }
    
    /**
     * Shows a message dialog that tells the user the action
     * they requested was done correctly
     * @param pastTense The "past tense" component of the message, without surrounding spaces
     * @param pastParticiple The "past participle" component of the message, without surrounding spaces
     */
    public void showSuccessfulActionMessage(String pastTense, String pastParticiple) {
            JOptionPane.showMessageDialog(
                    this, 
                    "El registro se " + pastTense + " correctamente", 
                    "Registro " + pastParticiple, 
                    JOptionPane.INFORMATION_MESSAGE
            );
    }
    
    public int showConfirmationDialog(String action) {
        return JOptionPane.showConfirmDialog(
                this, 
                "¿Estás seguro de que deseas " + action + " este registro?", 
                "Confirmar acción", 
                JOptionPane.YES_NO_CANCEL_OPTION, 
                JOptionPane.QUESTION_MESSAGE
        );
    }

    /**
     * Shows a message dialog that tells the user the action
     * they requested went wrong, without specifying the cause
     * @param verb The "verb" component of the message, without surrounding spaces
     */
    public void showFailedActionMessage(String verb) {
        JOptionPane.showMessageDialog(
                this, 
                "El registro no se pudo " + verb + " en la base de datos", 
                "Registro sin " + verb, 
                JOptionPane.ERROR_MESSAGE
        );
    }    

    /**
     * Builds a valid record out of the fields specified in the form;
     * it is important that fields are validated before calling this function
     * or it will fail with an IllegalArgument exception
     * @return 
     */
    public abstract RecordType buildRecord();
    
    public boolean isRecordValid() {
        return getVerifiableFieldChain().performValidation().getSecond();
    }
    
    /**
     * Checks whether the record typed by the user is different than
     * the one stored in the form object; for forms that were initialized
     * with an empty record, this method is always true unless all of
     * the inputs are empty/unselected
     * @return 
     */
    public boolean isRecordDifferent() {
        if (getCurrentRecord().isEmpty()) {
            boolean someInputFilled = getTextFields()
                    .stream()
                    .anyMatch((JTextField field) -> field.getText().length() > 0);
            boolean someComboBoxSelected = getComboBoxes()
                    .stream()
                    .anyMatch((JComboBox comboBox) -> comboBox.getSelectedIndex() != -1);
            return someInputFilled || someComboBoxSelected;
        }
        return isInputDifferentFromRecord();
    }
    
    /**
     * Checks whether the input given by the user is different from
     * that of the current user, under the assumption that the current
     * user is not empty
     * @return 
     */
    public abstract boolean isInputDifferentFromRecord();
    
    /**
     * Gets a string message that lets the user know why their input doesn't
     * represent a valid record; this function returns an empty string
     * if the input is valid
     * @return 
     */
    public String getInvalidInputMessage() {
        return getVerifiableFieldChain().performValidation().getFirst();
    }
    
    
    public abstract VerifiableFieldChain getVerifiableFieldChain();
    
    public abstract Integer getRecordId(RecordType record);
    public abstract boolean recordExists(Integer id) throws SQLException, ClassNotFoundException, Exception;
    
    public abstract UpdateResult deleteRecord(Integer id) throws SQLException, ClassNotFoundException, Exception;
    public abstract UpdateResult insertRecord(RecordType record) throws SQLException, ClassNotFoundException, Exception;
    public abstract UpdateResult updateRecord(RecordType record) throws SQLException, ClassNotFoundException, Exception;

    public FormModality getModality() {
        return modality;
    }
}
