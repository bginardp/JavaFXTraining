
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;

import org.junit.BeforeClass;
import org.junit.Test;

import es.palmademallorca.factuapp.model.dao.IProductosModel;
import es.palmademallorca.factuapp.model.dao.ProductosModelJpa;
import es.palmademallorca.factuapp.model.jpa.ProductoJPA;
import es.palmademallorca.factuapp.model.managers.EntityManagerProvider;

public class Test2 {

		private static EntityManager em = EntityManagerProvider.getProvider().getEntityManager();
	@BeforeClass
	public static void setUpClass() throws Exception {

	}


	@Test
	public void test() {
/* Metamodel metamodel = em.getMetamodel();
ManagedType<ProductoJPA> type1 = metamodel.managedType(ProductoJPA.class);
Set<Attribute<? super ProductoJPA,?>> attributes2 = type1.getAttributes();
System.out.println(attributes2.size());
for (Iterator<Attribute<? super ProductoJPA, ?>> iterator = attributes2.iterator(); iterator.hasNext();) {

	Attribute<ProductoJPA, ?> attribute = (Attribute<ProductoJPA, ?>) iterator.next();
    System.out.println(attribute.getName()+ " " + attribute.getJavaType());
}
*/

	     EntityManagerProvider.printAttributesSize(ProductoJPA.class);

		IProductosModel model = new ProductosModelJpa(em);
		List<ProductoJPA> productos= model.getProductos();

	    for (Iterator<ProductoJPA> iterator = productos.iterator(); iterator.hasNext();) {
	    	ProductoJPA productoJPA = iterator.next();
			System.out.println(productoJPA.getId() + " " + productoJPA.getDem() + " " + productoJPA.getDem());

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
