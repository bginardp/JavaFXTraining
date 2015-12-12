package es.palmademallorca.factuapp.model.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import es.palmademallorca.factuapp.model.jpa.Cliente;
import es.palmademallorca.factuapp.model.jpa.Producto;

public class ClientesModelJpa implements IClientesModel {

    private final EntityManager entityManager;

    public ClientesModelJpa(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Cliente getClientePorId(Long id) {
        return entityManager.find(Cliente.class, id);
    }
    @Override
    public List<Cliente> getClientes() {
        List<Cliente> ClienteJPAs = new ArrayList<>();
        String jpql = "SELECT f FROM Cliente f";
        TypedQuery<Cliente> query = entityManager.createQuery(jpql, Cliente.class);
        ClienteJPAs.addAll(query.getResultList());
        return ClienteJPAs;
    }

    @Override
    public int insertar(Cliente ClienteJPA) {
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
    public int modificar(Cliente ClienteJPA) {
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
    public int eliminar(Cliente ClienteJPA) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        try {
            Cliente ClienteJPAAEliminar = entityManager.getReference(Cliente.class, ClienteJPA.getId());
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
    public int eliminar(List<Cliente> ClienteJPAs) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        try {
            for (Cliente ClienteJPA : ClienteJPAs) {
                Cliente ClienteJPAAEliminar = entityManager.getReference(Cliente.class, ClienteJPA.getId());
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
	public List<Cliente> getClientesPorNombre(String valor) {
		TypedQuery<Cliente> query =
        		entityManager.createNamedQuery("Cliente.findByNom", Cliente.class);
    	query.setParameter("nom", "%" + valor + "%");
        List<Cliente> clientes = query.getResultList();
        return clientes;
	}


}
