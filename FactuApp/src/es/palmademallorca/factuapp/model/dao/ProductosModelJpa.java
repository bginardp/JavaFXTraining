package es.palmademallorca.factuapp.model.dao;

import java.util.List;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import es.palmademallorca.factuapp.model.jpa.ProductoJPA;

public class ProductosModelJpa implements IProductosModel {

    private final EntityManager entityManager;

    public ProductosModelJpa(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public ProductoJPA getProductoPorId(String id) {
        return entityManager.find(ProductoJPA.class, id);
    }

    @Override
    public List<ProductoJPA> getProductosPorDem(String valor) {
    	TypedQuery<ProductoJPA> query =
        		entityManager.createNamedQuery("Producto.findByDem", ProductoJPA.class);
    	query.setParameter("dem", "%" + valor + "%");
        List<ProductoJPA> productos = query.getResultList();
        return productos;
    }
    @Override
    public List<ProductoJPA> getProductos() {
        TypedQuery<ProductoJPA> query =
        		entityManager.createNamedQuery("Producto.findAll", ProductoJPA.class);
        List<ProductoJPA> productos = query.getResultList();
        return productos;
    }

    @Override
    public int insertar(ProductoJPA producto) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        try {
            entityManager.persist(producto);
            transaction.commit();
            return EXITO;
        } catch (EntityExistsException ex) {
         //   Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, ex);
        	System.out.println("Error:" + ex);
            transaction.rollback();
            return FALLO;
        }
    }

    @Override
    public int modificar(ProductoJPA producto) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        try {
            entityManager.merge(producto);
            transaction.commit();
            return EXITO;
        } catch (IllegalArgumentException ex) {
         //   Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, ex);
        	System.out.println("Error:" + ex);
            transaction.rollback();
            return FALLO;
        }
    }

    @Override
    public int eliminar(ProductoJPA producto) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        try {
        	ProductoJPA AEliminar = entityManager.getReference(ProductoJPA.class, producto.getId());
            entityManager.remove(AEliminar);
            transaction.commit();
            return EXITO;
        } catch (IllegalArgumentException ex) {
         //   Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, ex);
        	System.out.println("Error:" + ex);
            transaction.rollback();
            return FALLO;
        }
    }

    @Override
    public int eliminar(List<ProductoJPA> productos) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        try {
            for (ProductoJPA ProductoJPA : productos) {
            	ProductoJPA AEliminar = entityManager.getReference(ProductoJPA.class, ProductoJPA.getId());
                entityManager.remove(AEliminar);
            }
            transaction.commit();
            return EXITO;
        } catch (IllegalArgumentException ex) {
          //  Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, ex);
        	System.out.println("Error:" + ex);
            transaction.rollback();
            return FALLO;
        }
    }


}
