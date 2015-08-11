package es.palmademallorca.factuapp.model.jpa;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the facturas database table.
 *
 */
@Entity
@Table(name="facturas")
@NamedQuery(name="Factura.findAll", query="SELECT f FROM Factura f")
public class Factura implements Serializable {
	private static final long serialVersionUID = 1L;
	private FacturaPK id;
	private Date dat;
	private Cliente cliente;
	private Serie serie;
	private List<Factureslin> factureslins;

	public Factura() {
	}


	@EmbeddedId
	public FacturaPK getId() {
		return this.id;
	}

	public void setId(FacturaPK id) {
		this.id = id;
	}


	@Temporal(TemporalType.DATE)
	public Date getDat() {
		return this.dat;
	}

	public void setDat(Date dat) {
		this.dat = dat;
	}


	//bi-directional many-to-one association to Cliente
	@ManyToOne
	public Cliente getCliente() {
		return this.cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}


	//bi-directional many-to-one association to Serie
	@ManyToOne
	public Serie getSerie() {
		return this.serie;
	}

	public void setSerie(Serie serie) {
		this.serie = serie;
	}


	//bi-directional many-to-one association to Factureslin
	@OneToMany(mappedBy="factura")
	public List<Factureslin> getFactureslins() {
		return this.factureslins;
	}

	public void setFactureslins(List<Factureslin> factureslins) {
		this.factureslins = factureslins;
	}

	public Factureslin addFactureslin(Factureslin factureslin) {
		getFactureslins().add(factureslin);
		factureslin.setFactura(this);

		return factureslin;
	}

	public Factureslin removeFactureslin(Factureslin factureslin) {
		getFactureslins().remove(factureslin);
		factureslin.setFactura(null);

		return factureslin;
	}

}