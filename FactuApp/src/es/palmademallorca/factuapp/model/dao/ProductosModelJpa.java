package es.palmademallorca.factuapp.model.dao;

import java.util.List;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import es.palmademallorca.factuapp.model.jpa.Producto;

public class ProductosModelJpa implements IProductosModel {

    private final EntityManager entityManager;

    public ProductosModelJpa(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Producto getProductoPorId(String id) {
        return entityManager.find(Producto.class, id);
    }

    @Override
    public List<Producto> getProductosPorDem(String valor) {
    	TypedQuery<Producto> query =
        		entityManager.createNamedQuery("Producto.findByDem", Producto.class);
    	query.setParameter("dem", "%" + valor + "%");
        List<Producto> productos = query.getResultList();
        return productos;
    }
    @Override
    public List<Producto> getProductos() {
        TypedQuery<Producto> query =
        		entityManager.createNamedQuery("Producto.findAll", Producto.class);
        List<Producto> productos = query.getResultList();
        return productos;
    }

    @Override
    public int insertar(Producto producto) {
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
    public int modificar(Producto producto) {
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
    public int eliminar(Producto producto) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        try {
        	Producto AEliminar = entityManager.getReference(Producto.class, producto.getId());
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
    public int eliminar(List<Producto> productos) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        try {
            for (Producto ProductoJPA : productos) {
            	Producto AEliminar = entityManager.getReference(Producto.class, ProductoJPA.getId());
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
