package es.palmademallorca.bg.factuapp.model.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import es.palmademallorca.bg.factuapp.model.jpa.Empresa;

public class EmpresaService implements IEmpresaDAO {

    private final EntityManager entityManager;

    public EmpresaService(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Empresa getEmpresaPorId(Long id) {
        return entityManager.find(Empresa.class, id);
    }
    @Override
    public List<Empresa> getEmpresas() {
    	TypedQuery<Empresa> query =
        		entityManager.createNamedQuery("Empresa.findAll", Empresa.class);
        List<Empresa> EmpresaJPAs = new ArrayList<>();
        //String jpql = "SELECT f FROM EmpresaJPA f";
        //TypedQuery<EmpresaJPA> query = entityManager.createQuery(jpql, EmpresaJPA.class);
        EmpresaJPAs.addAll(query.getResultList());
        return EmpresaJPAs;
    }

    @Override
    public int insertar(Empresa EmpresaJPA) {
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
    public int modificar(Empresa EmpresaJPA) {
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
    public int eliminar(Empresa empresa) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        try {
            Empresa EmpresaJPAAEliminar = entityManager.getReference(Empresa.class, empresa.getId());
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
    public int eliminar(List<Empresa> EmpresaJPAs) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        try {
            for (Empresa EmpresaJPA : EmpresaJPAs) {
                Empresa EmpresaJPAAEliminar = entityManager.getReference(Empresa.class, EmpresaJPA.getId());
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
	public List<Empresa> getEmpresasPorNombre(String valor) {
		TypedQuery<Empresa> query =
        		entityManager.createNamedQuery("Empresa.findByDem", Empresa.class);
    	query.setParameter("dem", "%" + valor + "%");
        List<Empresa> resultat = query.getResultList();
        return resultat;
	}


}
