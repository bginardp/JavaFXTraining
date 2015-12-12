package es.palmademallorca.factuapp.model.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import es.palmademallorca.factuapp.model.jpa.SerieJPA;

public class SeriesModelJpa implements ISeriesModel {

    private final EntityManager entityManager;

    public SeriesModelJpa(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public SerieJPA getSeriePorId(Long id) {
        return entityManager.find(SerieJPA.class, id);
    }
    @Override
    public List<SerieJPA> getSeries() {
    	TypedQuery<SerieJPA> query =
        		entityManager.createNamedQuery("Serie.findAll", SerieJPA.class);
        List<SerieJPA> SerieJPAs = new ArrayList<>();
        //String jpql = "SELECT f FROM SerieJPA f";
        //TypedQuery<SerieJPA> query = entityManager.createQuery(jpql, SerieJPA.class);
        SerieJPAs.addAll(query.getResultList());
        return SerieJPAs;
    }

    @Override
    public int insertar(SerieJPA registro) {
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
    public int modificar(SerieJPA registro) {
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
    public int eliminar(SerieJPA registro) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        try {
            SerieJPA SerieJPAAEliminar = entityManager.getReference(SerieJPA.class, registro.getId());
            entityManager.remove(SerieJPAAEliminar);
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
    public int eliminar(List<SerieJPA> lista) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        try {
            for (SerieJPA fila : lista) {
                SerieJPA SerieJPAAEliminar = entityManager.getReference(SerieJPA.class, fila.getId());
                entityManager.remove(SerieJPAAEliminar);
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
