package es.palmademallorca.bg.factuapp.model.test;

import java.util.List;

import javax.persistence.EntityManager;

import org.junit.BeforeClass;
import org.junit.Test;

import es.palmademallorca.bg.factuapp.model.dao.FacturasService;
import es.palmademallorca.bg.factuapp.model.dao.IFacturasDAO;
import es.palmademallorca.bg.factuapp.model.jpa.Cliente;
import es.palmademallorca.bg.factuapp.model.jpa.Empresa;
import es.palmademallorca.bg.factuapp.model.jpa.Factura;
import es.palmademallorca.bg.factuapp.model.jpa.Factureslin;
import es.palmademallorca.bg.factuapp.model.jpa.Producto;
import es.palmademallorca.bg.factuapp.model.managers.EntityManagerProvider;

public class Test2 {

	private static EntityManager em = EntityManagerProvider.getProvider().getEntityManager();

	
	public void test() {
		IFacturasDAO model1 = new FacturasService(em);
		String criteri = "01-01-2015";
		List<Factura> factures = model1.findFacturas(new Long(1), new Integer(2015), criteri);
		for (Factura fila : factures) {
			Cliente cliente = fila.getCliente();
			Empresa empresa = fila.getEmpresa();
			System.out.println("######## Datos factura:" + fila);
			System.out.println("######## Datos cliente:" + cliente.getNom() + " Datos Empresa:" + empresa.getDem());
			List<Factureslin> lista2 = fila.getFactureslins();
			for (Factureslin lin : lista2) {
				// Producto producto = lin.getProducto();
				System.out.println("######## Datos detalle factura:" + lin.toString());
			}
		}
	}

	@Test
	public void testporID() {
		IFacturasDAO model1 = new FacturasService(em);
		Long id = new Long(1);
		Factura fila = model1.getFacturaPorId(id);
		Cliente cliente = fila.getCliente();
		Empresa empresa = fila.getEmpresa();
		System.out.println("######## testporID Datos factura:" + fila);
		System.out.println("######## testporID Datos cliente:" + cliente.getNom() + " Datos Empresa:" + empresa.getDem()+ " count>>> ");
		List<Factureslin> lista2 = fila.getFactureslins();
		for (Factureslin lin : lista2) {
			// Producto producto = lin.getProducto();
			System.out.println("####### testporID Datos detalle factura:" + lin.toString());
		}

	}
}