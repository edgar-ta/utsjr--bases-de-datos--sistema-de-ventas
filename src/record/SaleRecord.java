/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package record;

import java.sql.Date;
import util.PrimaryKey;

/**
 *
 * @author Edgar
 */
public class SaleRecord extends Record {
    int id;
    int cantidadDeProducto;
    
    PrimaryKey cliente;
    PrimaryKey producto;
    
    int folio;
    Date fecha;

    public SaleRecord(int id, int cantidadDeProducto, PrimaryKey cliente, PrimaryKey producto, int folio, Date fecha) {
        this.id = id;
        this.cantidadDeProducto = cantidadDeProducto;
        this.cliente = cliente;
        this.producto = producto;
        this.folio = folio;
        this.fecha = fecha;
    }

    public SaleRecord() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCantidadDeProducto() {
        return cantidadDeProducto;
    }

    public void setCantidadDeProducto(int cantidadDeProducto) {
        this.cantidadDeProducto = cantidadDeProducto;
    }

    public PrimaryKey getCliente() {
        return cliente;
    }

    public void setCliente(PrimaryKey cliente) {
        this.cliente = cliente;
    }

    public PrimaryKey getProducto() {
        return producto;
    }

    public void setProducto(PrimaryKey producto) {
        this.producto = producto;
    }

    public int getFolio() {
        return folio;
    }

    public void setFolio(int folio) {
        this.folio = folio;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    
}
