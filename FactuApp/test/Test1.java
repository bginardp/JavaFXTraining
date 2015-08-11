
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;

import org.junit.BeforeClass;
import org.junit.Test;

import es.palmademallorca.factuapp.model.dao.ClientesModelJpa;
import es.palmademallorca.factuapp.model.dao.IClientesModel;
import es.palmademallorca.factuapp.model.jpa.ClienteJPA;
import es.palmademallorca.factuapp.model.managers.EntityManagerProvider;
import es.palmademallorca.factuapp.model.jpa.ClienteJPA;

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

	@SuppressWarnings("unchecked")
	@Test
	public void test() {

		IClientesModel model = new ClientesModelJpa(em);
		List<ClienteJPA> clientes= model.getClientes();

	    for (Iterator<ClienteJPA> iterator = clientes.iterator(); iterator.hasNext();) {
			ClienteJPA clienteJPA = iterator.next();
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
