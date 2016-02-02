package es.palmademallorca.bg.factuapp.test;

import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import es.palmademallorca.bg.factuapp.model.dao.ClientesModelJpa;
import es.palmademallorca.bg.factuapp.model.dao.IClientesModel;
import es.palmademallorca.bg.factuapp.model.dao.ITipivaModel;
import es.palmademallorca.bg.factuapp.model.dao.TipivaModel;
import es.palmademallorca.bg.factuapp.model.jpa.Cliente;
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
		ITipivaModel model = new TipivaModel(em);
		List<Tipiva> lista= model.getTipiva();

	    for (Tipiva e:lista) {
			System.out.println(e);

		}
	}

}
