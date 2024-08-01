/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package records;

/**
 *
 * @author Edgar
 */
public class ClientRecord {
    int id;
    String nombre;
    String rfc;
    String calle;
    String numero_de_casa;
    String colonia;
    String municipio;
    String estado;
    String telefono;
    String celular;

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

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getNumero_de_casa() {
        return numero_de_casa;
    }

    public void setNumero_de_casa(String numero_de_casa) {
        this.numero_de_casa = numero_de_casa;
    }

    public String getColonia() {
        return colonia;
    }

    public void setColonia(String colonia) {
        this.colonia = colonia;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
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

    public ClientRecord() {
    }

    public ClientRecord(int id, String nombre, String rfc, String calle, String numero_de_casa, String colonia, String municipio, String estado, String telefono, String celular) {
        this.id = id;
        this.nombre = nombre;
        this.rfc = rfc;
        this.calle = calle;
        this.numero_de_casa = numero_de_casa;
        this.colonia = colonia;
        this.municipio = municipio;
        this.estado = estado;
        this.telefono = telefono;
        this.celular = celular;
    }
    
}
