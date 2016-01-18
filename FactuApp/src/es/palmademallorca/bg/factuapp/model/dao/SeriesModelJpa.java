package es.palmademallorca.bg.factuapp.model.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import es.palmademallorca.bg.factuapp.model.jpa.Serie;

public class SeriesModelJpa implements ISeriesModel {

    private final EntityManager entityManager;

    public SeriesModelJpa(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Serie getSeriePorId(Long id) {
        return entityManager.find(Serie.class, id);
    }
    @Override
    public List<Serie> getSeries() {
    	TypedQuery<Serie> query =
        		entityManager.createNamedQuery("Serie.findAll", Serie.class);
        List<Serie> SerieJPAs = new ArrayList<>();
        //String jpql = "SELECT f FROM SerieJPA f";
        //TypedQuery<SerieJPA> query = entityManager.createQuery(jpql, SerieJPA.class);
        SerieJPAs.addAll(query.getResultList());
        return SerieJPAs;
    }

    @Override
    public int insertar(Serie registro) {
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
    public int modificar(Serie registro) {
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
    public int eliminar(Serie registro) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        try {
            Serie SerieJPAAEliminar = entityManager.getReference(Serie.class, registro.getId());
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
    public int eliminar(List<Serie> lista) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        try {
            for (Serie fila : lista) {
                Serie SerieJPAAEliminar = entityManager.getReference(Serie.class, fila.getId());
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
