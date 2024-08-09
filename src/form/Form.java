/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package form;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.Optional;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JTextField;
import record.Record;

/**
 *
 * @author Edgar
 * @param <RecordType>
 */
public abstract class Form<RecordType extends Record> extends JPanel {
    protected Optional<RecordType> currentRecord;
    
    public Form() {
        super();
    }
    
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
    }

    public Optional<RecordType> getCurrentRecord() {
        return currentRecord;
    }

    public abstract void setCurrentRecord(Optional<RecordType> currentRecord);
    
    public abstract LinkedList<JTextField> getTextFields();
    public abstract LinkedList<JComboBox> getComboBoxes();
    
    public abstract void delete() throws SQLException, ClassNotFoundException, Exception;
    public abstract void insert() throws SQLException, ClassNotFoundException, Exception;
    public abstract void update() throws SQLException, ClassNotFoundException, Exception;
    
    public abstract RecordType buildRecord();
    
    public abstract boolean isRecordValid();
    public abstract boolean isRecordDifferent();
}
