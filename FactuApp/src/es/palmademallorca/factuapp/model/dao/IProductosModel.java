package es.palmademallorca.factuapp.model.dao;

import java.util.List;

import es.palmademallorca.factuapp.model.jpa.ProductoJPA;

public interface IProductosModel {

    public static final int EXITO = 0;

    public static final int FALLO = -1;

    public ProductoJPA getProductoPorId(String id);

    public List<ProductoJPA> getProductos();
    public List<ProductoJPA> getProductosPorDem(String valor);
    public int insertar(ProductoJPA producto);

    public int modificar(ProductoJPA producto);

    public int eliminar(ProductoJPA producto);

    public int eliminar(List<ProductoJPA> producto);
}
