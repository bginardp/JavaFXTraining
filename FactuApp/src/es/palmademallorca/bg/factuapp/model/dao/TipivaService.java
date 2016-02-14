package es.palmademallorca.bg.factuapp.model.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import es.palmademallorca.bg.factuapp.model.jpa.Tipiva;

public class TipivaService implements ITipivaDAO {

    private final EntityManager entityManager;

    public TipivaService(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Tipiva getTipivaPorId(Long id) {
        return entityManager.find(Tipiva.class, id);
    }
    @Override
    public List<Tipiva> getTipiva() {
    	TypedQuery<Tipiva> query =
        		entityManager.createNamedQuery("Tipiva.findAll", Tipiva.class);
        List<Tipiva> TipivaJPAs = new ArrayList<>();
        //String jpql = "SELECT f FROM TipivaJPA f";
        //TypedQuery<TipivaJPA> query = entityManager.createQuery(jpql, TipivaJPA.class);
        TipivaJPAs.addAll(query.getResultList());
        return TipivaJPAs;
    }

    @Override
    public int insertar(Tipiva registro) {
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
    public int modificar(Tipiva registro) {
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
    public int eliminar(Tipiva registro) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        try {
            Tipiva TipivaJPAAEliminar = entityManager.getReference(Tipiva.class, registro.getId());
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
    public int eliminar(List<Tipiva> lista) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        try {
            for (Tipiva fila : lista) {
                Tipiva TipivaJPAAEliminar = entityManager.getReference(Tipiva.class, fila.getId());
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
