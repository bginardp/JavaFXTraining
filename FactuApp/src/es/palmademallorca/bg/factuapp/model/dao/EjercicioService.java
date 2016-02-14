package es.palmademallorca.bg.factuapp.model.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import es.palmademallorca.bg.factuapp.model.jpa.Ejercicio;

public class EjercicioService implements IEjercicioDAO {

    private final EntityManager entityManager;

    public EjercicioService(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Ejercicio getEjercicioById(Integer id) {
        return entityManager.find(Ejercicio.class, id);
    }
    @Override
    public List<Ejercicio> getEjercicios() {
    	TypedQuery<Ejercicio> query =
        		entityManager.createNamedQuery("Ejercicio.findAll", Ejercicio.class);
        List<Ejercicio> lista = new ArrayList<>();
        lista.addAll(query.getResultList());
        return lista;
    }

    @Override
    public int insertar(Ejercicio registro) {
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
    public int modificar(Ejercicio registro) {
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
    public int eliminar(Ejercicio registro) {

        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        try {
            Ejercicio aEliminar = entityManager.getReference(Ejercicio.class, registro.getId());
            entityManager.remove(aEliminar);
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
    public int eliminar(List<Ejercicio> lista) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        try {
            for (Ejercicio fila : lista) {
                Ejercicio aEliminar = entityManager.getReference(Ejercicio.class, fila.getId());
                entityManager.remove(aEliminar);
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
