package es.palmademallorca.bg.factuapp.model.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import es.palmademallorca.bg.factuapp.model.errors.FactuException;
import es.palmademallorca.bg.factuapp.model.errors.Messages;
import es.palmademallorca.bg.factuapp.model.jpa.Factura;

public class FacturasService implements IFacturasDAO {

	private final EntityManager entityManager;

	public FacturasService(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public Factura getFacturaPorId(Long id) {
		return entityManager.find(Factura.class, id);
	}

	@Override
	public Factura getFacturaPorEmpresaEjeSerieNumero(Long empresa, Long ejercicio, String serie, BigDecimal num) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BigDecimal getNewNumeroFactura(Long empresa, Long ejercicio, String serie) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Factura> getAllFacturas(Long idEmpresa, Long ejercicio) {

		if (idEmpresa == null) {
			try {
				throw new FactuException(Messages.NO_EMPRESA);
			} catch (FactuException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		if (ejercicio == null && ejercicio <= 0) {
			try {
				throw new FactuException(Messages.NO_EJERCICIO);
			} catch (FactuException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		List<Factura> lista = new ArrayList<>();
		TypedQuery<Factura> query = entityManager.createNamedQuery("Factura.findByEmpEje", Factura.class);
		query.setParameter("empresa", idEmpresa);
		query.setParameter("ejercicio", ejercicio);

		lista.addAll(query.getResultList());
		return lista;

	}

	@Override
	public List<Factura> getAllFacturas(Long empresa) {
		List<Factura> lista = new ArrayList<>();
		TypedQuery<Factura> query = entityManager.createNamedQuery("Factura.findByEmp", Factura.class);
		query.setParameter("empresa", empresa);
		lista.addAll(query.getResultList());
		return lista;
	}

	@Override
	public int insertar(Factura factura) {
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		try {
			entityManager.persist(factura);
			transaction.commit();
			return EXITO;
		} catch (EntityExistsException ex) {
			// Logger.getLogger(getClass().getName()).log(Level.SEVERE, null,
			// ex);
			System.out.println("Error:" + ex);
			transaction.rollback();
			return FALLO;
		}
	}

	@Override
	public int modificar(Factura factura) {
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		try {
			entityManager.merge(factura);
			transaction.commit();
			return EXITO;
		} catch (IllegalArgumentException ex) {
			// Logger.getLogger(getClass().getName()).log(Level.SEVERE, null,
			// ex);
			System.out.println("Error:" + ex);
			transaction.rollback();
			return FALLO;
		}
	}

	@Override
	public int eliminar(Factura factura) {
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		try {
			Factura filaEliminar = entityManager.getReference(Factura.class, factura.getId());
			entityManager.remove(filaEliminar);
			transaction.commit();
			return EXITO;
		} catch (IllegalArgumentException ex) {
			// Logger.getLogger(getClass().getName()).log(Level.SEVERE, null,
			// ex);
			System.out.println("Error:" + ex);
			transaction.rollback();
			return FALLO;
		}
	}

}
