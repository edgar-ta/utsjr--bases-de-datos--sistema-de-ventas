/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package record;

import util.PrimaryKey;

/**
 *
 * @author Edgar
 */
public class ProductRecord extends Record {
    int id;
    PrimaryKey categoria;
    PrimaryKey proveedor;
    String codigo;
    String nombre;
    double descuento;
    double precio;
    int stock;
    
    int productosVendidos;

    public ProductRecord() {
    }

    public ProductRecord(int id, PrimaryKey categoria, PrimaryKey proveedor, String codigo, String nombre, double descuento, double precio, int stock, int productosVendidos) {
        this.id = id;
        this.categoria = categoria;
        this.proveedor = proveedor;
        this.codigo = codigo;
        this.nombre = nombre;
        this.descuento = descuento;
        this.precio = precio;
        this.stock = stock;
        this.productosVendidos = productosVendidos;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public PrimaryKey getCategoria() {
        return categoria;
    }

    public void setCategoria(PrimaryKey categoria) {
        this.categoria = categoria;
    }

    public PrimaryKey getProveedor() {
        return proveedor;
    }

    public void setProveedor(PrimaryKey proveedor) {
        this.proveedor = proveedor;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getDescuento() {
        return descuento;
    }

    public void setDescuento(double descuento) {
        this.descuento = descuento;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getProductosVendidos() {
        return productosVendidos;
    }

    public void setProductosVendidos(int productosVendidos) {
        this.productosVendidos = productosVendidos;
    }
}
