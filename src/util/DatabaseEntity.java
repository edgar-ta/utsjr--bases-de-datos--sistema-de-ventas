/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package util;

/**
 *
 * @author Edgar
 */
public enum DatabaseEntity {
        CATEGORY(new DisplayableString("categoria", "Categoría")),
        CLIENT(new DisplayableString("cliente", "Cliente")),
        PRODUCT(new DisplayableString("producto", "Producto")),
        SALE(new DisplayableString("venta", "Venta")),
        SERIABILITY(new DisplayableString("seriabilidad", "Seriabilidad")),
        SUPPLIER(new DisplayableString("proveedor", "Proveedor")),
        USER(new DisplayableString("usuario", "Usuario")),
        DETAIL(new DisplayableString("detalle", "Detalle"))
    ;
    
    protected final DisplayableString entityName;

    private DatabaseEntity(DisplayableString entityName) {
        this.entityName = entityName;
    }

    public DisplayableString getEntityName() {
        return entityName;
    }
}
