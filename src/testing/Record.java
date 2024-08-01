/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package testing;

import java.util.Optional;

/**
 *
 * @author Edgar
 */
public class Record {
    private int id;
    private Optional<String> name;
    
    public static final Record[] propRecords = new Record[] { 
        new Record(1, Optional.of("Nombre 1")),
        new Record(2, Optional.of("Nombre 2")),
        new Record(3, Optional.of("Nombre 3")),
        new Record(4, Optional.of("Nombre 4")),
        new Record(5, Optional.empty()),
        new Record(6, Optional.empty())
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Optional<String> getName() {
        return name;
    }

    public void setName(Optional<String> name) {
        this.name = name;
    }

    public Record(int id, Optional<String> name) {
        this.id = id;
        this.name = name;
    }
    
    public Record() {
        this(0, Optional.empty());
    }
}
