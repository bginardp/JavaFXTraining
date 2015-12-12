
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;

import org.junit.BeforeClass;
import org.junit.Test;

import es.palmademallorca.factuapp.model.dao.ClientesModelJpa;
import es.palmademallorca.factuapp.model.dao.FacturasModel;
import es.palmademallorca.factuapp.model.dao.ForPagModel;
import es.palmademallorca.factuapp.model.dao.IClientesModel;
import es.palmademallorca.factuapp.model.dao.IFacturasModel;
import es.palmademallorca.factuapp.model.dao.IForPagModel;
import es.palmademallorca.factuapp.model.dao.IProductosModel;
import es.palmademallorca.factuapp.model.dao.ISeriesModel;
import es.palmademallorca.factuapp.model.dao.ITipivaModel;
import es.palmademallorca.factuapp.model.dao.ProductosModel;
import es.palmademallorca.factuapp.model.dao.SeriesModelJpa;
import es.palmademallorca.factuapp.model.dao.TipivaModel;
import es.palmademallorca.factuapp.model.jpa.Cliente;
import es.palmademallorca.factuapp.model.jpa.Empresa;
import es.palmademallorca.factuapp.model.jpa.Factura;
import es.palmademallorca.factuapp.model.jpa.Factureslin;
import es.palmademallorca.factuapp.model.jpa.Formaspago;
import es.palmademallorca.factuapp.model.jpa.Producto;
import es.palmademallorca.factuapp.model.jpa.Serie;
import es.palmademallorca.factuapp.model.jpa.Tipiva;
import es.palmademallorca.factuapp.model.managers.EntityManagerProvider;

public class Test2 {

	private static EntityManager em = EntityManagerProvider.getProvider().getEntityManager();

	@BeforeClass
	public static void setUpClass() throws Exception {

	}

	@Test
	public void test() {

		IProductosModel model = new ProductosModel(em);
		List<Producto> productos = model.getProductos();

		for (Iterator<Producto> iterator = productos.iterator(); iterator.hasNext();) {
			Producto producto = iterator.next();
			System.out.println(producto);

		}

		/*
		 * EntityManagerProvider.printAttributesSize(SerieJPA.class);
		 *
		 * ISeriesModel model = new SeriesModelJpa(em); List<SerieJPA> lista=
		 * model.getSeries();
		 *
		 * for (Iterator<SerieJPA> iterator = lista.iterator();
		 * iterator.hasNext();) { SerieJPA fila = iterator.next();
		 * System.out.println(fila.getId() + " " + fila.getDec());
		 *
		 * }
		 */

		EntityManagerProvider.printAttributesSize(Factura.class);

		IFacturasModel model1 = new FacturasModel(em);
		List<Factura> lista1 = model1.getAllFacturas(new Long(1));

		for (Iterator<Factura> iterator = lista1.iterator(); iterator.hasNext();) {
			Factura fila = iterator.next();
			Cliente cliente = fila.getCliente();
			Empresa empresa = fila.getEmpresa();
			System.out.println("Datos factura:" + fila.toString() + " Datos cliente:" + cliente.getNom()
					+ " Datos Empresa:" + empresa.getDem());
			List<Factureslin> lista2 = fila.getFactureslins();

			for (Iterator<Factureslin> itFaclin = lista2.iterator(); itFaclin.hasNext();) {
				Factureslin faclin = itFaclin.next();
				Producto producto = faclin.getProducto();
				System.out.println("Datos detalle factura:" + faclin.toString());
			}

		}
/*
		EntityManagerProvider.printAttributesSize(Tipiva.class);

		ITipivaModel model1 = new TipivaModel(em);
		List<Tipiva> lista1 = model1.getTipiva();

		for (Iterator<Tipiva> iterator = lista1.iterator(); iterator.hasNext();) {
			Tipiva fila = iterator.next();
			System.out.println(fila.toString());

		}

		EntityManagerProvider.printAttributesSize(Formaspago.class);

		IForPagModel model2 = new ForPagModel(em);
		List<Formaspago> lista = model2.getForpag();

		for (Iterator<Formaspago> iterator = lista.iterator(); iterator.hasNext();) {
			Formaspago fila = iterator.next();
			System.out.println(fila.toString());

		}
*/
		// fail("Not yet implemented");
	}

}
