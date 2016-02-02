package es.palmademallorca.bg.factuapp.test;


import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;

import org.junit.BeforeClass;
import org.junit.Test;

import es.palmademallorca.bg.factuapp.model.dao.ClientesModelJpa;
import es.palmademallorca.bg.factuapp.model.dao.IClientesModel;
import es.palmademallorca.bg.factuapp.model.jpa.Cliente;
import es.palmademallorca.bg.factuapp.model.managers.EntityManagerProvider;



public class Test1 {
	// @PersistenceContext(unitName = "factuPU")
	// private static EntityManager em = null;
	private static EntityManager em = EntityManagerProvider.getProvider().getEntityManager();
	@BeforeClass
	public static void setUpClass() throws Exception {
	/*	if (em == null) {
			em = (EntityManager) Persistence.createEntityManagerFactory("factuPU").createEntityManager();
		} */
	}

	@Test
	public void test() {

		IClientesModel model = new ClientesModelJpa(em);
		List<Cliente> clientes= model.getClientes();

	    for (Iterator<Cliente> iterator = clientes.iterator(); iterator.hasNext();) {
			Cliente clienteJPA = iterator.next();
			System.out.println(clienteJPA.getId() + " " + clienteJPA.getCif() + " " + clienteJPA.getNom());

		}


		/*try {
			clientes = em
					.createQuery("FROM ClienteJPA c").getResultList();
			for (Iterator<ClienteJPA> iterator = clientes.iterator(); iterator.hasNext();) {
				ClienteJPA clienteJPA = iterator.next();
				System.out.println(clienteJPA.getId() + " " + clienteJPA.getCif() + " " + clienteJPA.getNom());

			}

		} catch (PersistenceException e) {
			System.out.println(e);
		} */
	//	List<ClienteJPA> clientes = em.createQuery("FROM factu.clientes c").getResultList();


		// fail("Not yet implemented");
	}

}
