package es.palmademallorca.bg.factuapp.model.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import es.palmademallorca.bg.factuapp.model.jpa.Formaspago;

public class ForPagModel implements IForPagModel {

    private final EntityManager entityManager;

    public ForPagModel(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Formaspago getForpagPorId(Long id) {
        return entityManager.find(Formaspago.class, id);
    }
    @Override
    public List<Formaspago> getForpag() {
    	TypedQuery<Formaspago> query =
        		entityManager.createNamedQuery("Forpag.findAll", Formaspago.class);
        List<Formaspago> llista = new ArrayList<>();
           llista.addAll(query.getResultList());
        return llista;
    }

    @Override
    public int insertar(Formaspago registro) {
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
    public int modificar(Formaspago registro) {
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
    public int eliminar(Formaspago registro) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        try {
            Formaspago regABorrar = entityManager.getReference(Formaspago.class, registro.getId());
            entityManager.remove(regABorrar);
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
    public int eliminar(List<Formaspago> lista) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        try {
            for (Formaspago fila : lista) {
                Formaspago regABorrar = entityManager.getReference(Formaspago.class, fila.getId());
                entityManager.remove(regABorrar);
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
