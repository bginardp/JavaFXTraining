package es.palmademallorca.bg.factuapp.model.dao;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;


import es.palmademallorca.bg.common.model.dao.AbstractJpaDao;
import es.palmademallorca.bg.common.util.dateutils.DateUtil;
import es.palmademallorca.bg.factuapp.model.errors.FactuException;
import es.palmademallorca.bg.factuapp.model.errors.Messages;
import es.palmademallorca.bg.factuapp.model.jpa.Cliente;
import es.palmademallorca.bg.factuapp.model.jpa.Factura;
import es.palmademallorca.bg.factuapp.model.jpa.Formaspago;
import es.palmademallorca.bg.factuapp.model.jpa.Producto;


public class FacturasService implements IFacturasDAO {

	private final EntityManager em;

	public FacturasService(EntityManager entityManager) {
		this.em = entityManager;
	}

	@Override
	public Factura getFacturaPorId(Long id) {
		return em.find(Factura.class, id);
	}

	@Override
	public Factura getFacturaPorEmpresaEjeSerieNumero(Long empresa, Integer ejercicio, String serie, BigDecimal num) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BigDecimal getNewNumeroFactura(Long empresa, Integer ejercicio, String serie) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Factura> getAllFacturas(Long empresa, Integer ejercicio) {

		if (empresa == null) {
			try {
				throw new FactuException(Messages.NO_EMPRESA);
			} catch (FactuException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		if (ejercicio == null || ejercicio.compareTo(new Integer(0)) <= 0) {
			try {
				throw new FactuException(Messages.NO_EJERCICIO);
			} catch (FactuException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		List<Factura> lista = new ArrayList<>();
		TypedQuery<Factura> query = em.createNamedQuery("Factura.findByEmpEje", Factura.class);
		query.setParameter("empresa_id", empresa);
		query.setParameter("ejercicio", ejercicio);

		lista.addAll(query.getResultList());
		return lista;

	}

	@Override
	public int insertar(Factura factura) {
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		try {
			em.persist(factura);
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
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		try {
			em.merge(factura);
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
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		try {
			Factura filaEliminar = em.getReference(Factura.class, factura.getId());
			em.remove(filaEliminar);
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
	public List<Factura> findFacturas(Long empresa, Integer ejercicio, String criteria) {
		LocalDate tempDate = null;
		List<Factura> lista = null;
        tempDate=DateUtil.parseLD(criteria);
		if (tempDate != null) {
			lista = em.createNamedQuery("Factura.findFacturasByDate").setParameter("empresa_id", empresa)
					.setParameter("ejercicio", ejercicio).setParameter("criteria", tempDate).getResultList();
		} else {
			if (StringUtils.isNumeric(criteria)) {
				lista = em.createNamedQuery("Factura.findFacturasByNumber").setParameter("empresa_id", empresa)
						.setParameter("ejercicio", ejercicio).setParameter("numero", new Long(criteria))
						.setParameter("totfac", new BigDecimal(criteria)).getResultList();
			} else {
				lista = em.createNamedQuery("Factura.findFacturasByString").setParameter("empresa_id", empresa)
						.setParameter("ejercicio", ejercicio).setParameter("criteria", "%" + criteria + "%")
						.getResultList();
			}
		}
		return lista;

	}

	public List<Factura> findByAttributes(Map<String, String> attributes) {
		List<Factura> results;
		// set up the Criteria query
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Factura> cq = cb.createQuery(Factura.class);
		Root<Factura> foo = cq.from(Factura.class);

		List<Predicate> predicates = new ArrayList<Predicate>();
		for (String s : attributes.keySet()) {
			if (foo.get(s) != null) {
				predicates.add(cb.like((Expression) foo.get(s), "%" + attributes.get(s) + "%"));
			}
		}
		cq.where(predicates.toArray(new Predicate[] {}));
		TypedQuery<Factura> q = em.createQuery(cq);

		results = q.getResultList();
		return results;
	}

	@Override
	public List<Cliente> getAllClientes() {
		// TODO Auto-generated method stub
		List<Cliente> llista= new ArrayList<>();
		TypedQuery<Cliente> query = em.createNamedQuery("Cliente.findAll", Cliente.class);
		llista.addAll(query.getResultList());
		return llista;
	}

	@Override
	public List<Formaspago> getAllFormasPago() {
		List<Formaspago> llista= new ArrayList<>();
		TypedQuery<Formaspago> query = em.createNamedQuery("Formaspago.findAll", Formaspago.class);
		llista.addAll(query.getResultList());
		return llista;
	}

	@Override
	public List<Producto> getAllProductos() {
		List<Producto> llista= new ArrayList<>();
		TypedQuery<Producto> query = em.createNamedQuery("Producto.findAll", Producto.class);
		llista.addAll(query.getResultList());
		return llista;
	}

}
