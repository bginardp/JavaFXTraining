package es.palmademallorca.bg.factuapp.model.dao;

import java.util.List;

import es.palmademallorca.bg.factuapp.model.jpa.Producto;

public interface IProductosModel {

    public static final int EXITO = 0;

    public static final int FALLO = -1;

    public Producto getProductoPorId(String id);

    public List<Producto> getProductos();
    public List<Producto> getProductosPorDem(String valor);
    public int insertar(Producto producto);

    public int modificar(Producto producto);

    public int eliminar(Producto producto);

    public int eliminar(List<Producto> producto);
}
