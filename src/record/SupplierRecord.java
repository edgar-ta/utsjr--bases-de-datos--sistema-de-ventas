/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package record;

/**
 *
 * @author Edgar
 */
public class SupplierRecord extends Record {
    int id;
    String nombre;
    String direccion;
    String telefono;
    String celular;
    String rfc;
    
    int productosOfrecidos;

    public SupplierRecord() {
    }
    
    public SupplierRecord(int id, String nombre, String direccion, String telefono, String celular, String rfc, int productosOfrecidos) {
        this.id = id;
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.celular = celular;
        this.rfc = rfc;
        this.productosOfrecidos = productosOfrecidos;
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

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public int getProductosOfrecidos() {
        return productosOfrecidos;
    }

    public void setProductosOfrecidos(int productosOfrecidos) {
        this.productosOfrecidos = productosOfrecidos;
    }
}
