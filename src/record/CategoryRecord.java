/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package record;

/**
 *
 * @author Edgar
 */
public class CategoryRecord extends Record {
    int id;
    String nombre;
    int numeroDeProductos;

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public int getNumeroDeProductos() {
        return numeroDeProductos;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public void setNumeroDeProductos(int numeroDeProductos) {
        this.numeroDeProductos = numeroDeProductos;
    }

    public CategoryRecord(int id, String nombre, int numeroDeProductos) {
        this.id = id;
        this.nombre = nombre;
        this.numeroDeProductos = numeroDeProductos;
    }

    public CategoryRecord() {
    }
}
