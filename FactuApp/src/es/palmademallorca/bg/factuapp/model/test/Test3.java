package es.palmademallorca.bg.factuapp.model.test;

import java.util.List;

import javax.persistence.EntityManager;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import es.palmademallorca.bg.factuapp.model.dao.ITipivaDAO;
import es.palmademallorca.bg.factuapp.model.dao.TipivaService;
import es.palmademallorca.bg.factuapp.model.jpa.Tipiva;
import es.palmademallorca.bg.factuapp.model.managers.EntityManagerProvider;

public class Test3 {
	private static EntityManager em = EntityManagerProvider.getProvider().getEntityManager();

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		ITipivaDAO model = new TipivaService(em);
		List<Tipiva> lista= model.getTipiva();

	    for (Tipiva e:lista) {
			System.out.println(e);

		}
	    
	    
	}

}
