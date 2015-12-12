package es.palmademallorca.factuapp.model.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import es.palmademallorca.factuapp.model.jpa.EmpresaJPA;

public class EmpresasModelJpa implements IEmpresasModel {

    private final EntityManager entityManager;

    public EmpresasModelJpa(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public EmpresaJPA getEmpresaPorId(Long id) {
        return entityManager.find(EmpresaJPA.class, id);
    }
    @Override
    public List<EmpresaJPA> getEmpresas() {
    	TypedQuery<EmpresaJPA> query =
        		entityManager.createNamedQuery("Empresa.findAll", EmpresaJPA.class);
        List<EmpresaJPA> EmpresaJPAs = new ArrayList<>();
        //String jpql = "SELECT f FROM EmpresaJPA f";
        //TypedQuery<EmpresaJPA> query = entityManager.createQuery(jpql, EmpresaJPA.class);
        EmpresaJPAs.addAll(query.getResultList());
        return EmpresaJPAs;
    }

    @Override
    public int insertar(EmpresaJPA EmpresaJPA) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        try {
            entityManager.persist(EmpresaJPA);
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
    public int modificar(EmpresaJPA EmpresaJPA) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        try {
            entityManager.merge(EmpresaJPA);
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
    public int eliminar(EmpresaJPA EmpresaJPA) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        try {
            EmpresaJPA EmpresaJPAAEliminar = entityManager.getReference(EmpresaJPA.class, EmpresaJPA.getId());
            entityManager.remove(EmpresaJPAAEliminar);
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
    public int eliminar(List<EmpresaJPA> EmpresaJPAs) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        try {
            for (EmpresaJPA EmpresaJPA : EmpresaJPAs) {
                EmpresaJPA EmpresaJPAAEliminar = entityManager.getReference(EmpresaJPA.class, EmpresaJPA.getId());
                entityManager.remove(EmpresaJPAAEliminar);
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

	@Override
	public List<EmpresaJPA> getEmpresasPorNombre(String valor) {
		TypedQuery<EmpresaJPA> query =
        		entityManager.createNamedQuery("Empresa.findByDem", EmpresaJPA.class);
    	query.setParameter("dem", "%" + valor + "%");
        List<EmpresaJPA> resultat = query.getResultList();
        return resultat;
	}


}
