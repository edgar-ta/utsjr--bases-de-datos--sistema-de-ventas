/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package card;

import java.util.Optional;
import javax.swing.JPanel;
import records.Record;

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
    
    /**
     * Updates the UI depending on the provided record value
     * @param record 
     */
    public abstract void setCurrentRecord(Optional<RecordType> record);
}
