/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package record;

/**
 *
 * @author Edgar
 */
public class SeriabilityRecord {
    private int id;
    private String nombre;
    private int cima;

    public SeriabilityRecord() {
    }

    public SeriabilityRecord(int id, String nombre, int cima) {
        this.id = id;
        this.nombre = nombre;
        this.cima = cima;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCima() {
        return cima;
    }

    public void setCima(int cima) {
        this.cima = cima;
    }
    
    
}
