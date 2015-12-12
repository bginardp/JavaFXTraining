package es.palmademallorca.factuapp.model.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import es.palmademallorca.factuapp.model.jpa.TipivaJPA;

public class TipivaModel implements ITipivaModel {

    private final EntityManager entityManager;

    public TipivaModel(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public TipivaJPA getTipivaPorId(Long id) {
        return entityManager.find(TipivaJPA.class, id);
    }
    @Override
    public List<TipivaJPA> getTipiva() {
    	TypedQuery<TipivaJPA> query =
        		entityManager.createNamedQuery("Tipiva.findAll", TipivaJPA.class);
        List<TipivaJPA> TipivaJPAs = new ArrayList<>();
        //String jpql = "SELECT f FROM TipivaJPA f";
        //TypedQuery<TipivaJPA> query = entityManager.createQuery(jpql, TipivaJPA.class);
        TipivaJPAs.addAll(query.getResultList());
        return TipivaJPAs;
    }

    @Override
    public int insertar(TipivaJPA registro) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        try {
            entityManager.persist(registro);
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
    public int modificar(TipivaJPA registro) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        try {
            entityManager.merge(registro);
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
    public int eliminar(TipivaJPA registro) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        try {
            TipivaJPA TipivaJPAAEliminar = entityManager.getReference(TipivaJPA.class, registro.getId());
            entityManager.remove(TipivaJPAAEliminar);
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
    public int eliminar(List<TipivaJPA> lista) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        try {
            for (TipivaJPA fila : lista) {
                TipivaJPA TipivaJPAAEliminar = entityManager.getReference(TipivaJPA.class, fila.getId());
                entityManager.remove(TipivaJPAAEliminar);
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
