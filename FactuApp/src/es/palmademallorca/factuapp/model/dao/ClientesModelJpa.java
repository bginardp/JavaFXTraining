package es.palmademallorca.factuapp.model.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import es.palmademallorca.factuapp.model.jpa.ClienteJPA;
import es.palmademallorca.factuapp.model.jpa.ProductoJPA;

public class ClientesModelJpa implements IClientesModel {

    private final EntityManager entityManager;

    public ClientesModelJpa(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public ClienteJPA getClientePorId(Long id) {
        return entityManager.find(ClienteJPA.class, id);
    }
    @Override
    public List<ClienteJPA> getClientes() {
        List<ClienteJPA> ClienteJPAs = new ArrayList<>();
        String jpql = "SELECT f FROM ClienteJPA f";
        TypedQuery<ClienteJPA> query = entityManager.createQuery(jpql, ClienteJPA.class);
        ClienteJPAs.addAll(query.getResultList());
        return ClienteJPAs;
    }

    @Override
    public int insertar(ClienteJPA ClienteJPA) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        try {
            entityManager.persist(ClienteJPA);
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
    public int modificar(ClienteJPA ClienteJPA) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        try {
            entityManager.merge(ClienteJPA);
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
    public int eliminar(ClienteJPA ClienteJPA) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        try {
            ClienteJPA ClienteJPAAEliminar = entityManager.getReference(ClienteJPA.class, ClienteJPA.getId());
            entityManager.remove(ClienteJPAAEliminar);
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
    public int eliminar(List<ClienteJPA> ClienteJPAs) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        try {
            for (ClienteJPA ClienteJPA : ClienteJPAs) {
                ClienteJPA ClienteJPAAEliminar = entityManager.getReference(ClienteJPA.class, ClienteJPA.getId());
                entityManager.remove(ClienteJPAAEliminar);
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
	public List<ClienteJPA> getClientesPorNombre(String valor) {
		TypedQuery<ClienteJPA> query =
        		entityManager.createNamedQuery("Cliente.findByNom", ClienteJPA.class);
    	query.setParameter("nom", "%" + valor + "%");
        List<ClienteJPA> clientes = query.getResultList();
        return clientes;
	}


}
