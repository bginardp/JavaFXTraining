package es.palmademallorca.factuapp.model.jpa;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the serie database table.
 * 
 */
@Entity
@NamedQuery(name="Serie.findAll", query="SELECT s FROM Serie s")
public class Serie implements Serializable {
	private static final long serialVersionUID = 1L;
	private String id;
	private String dec;
	private String hab;
	private List<Factura> facturas;
	private Empresa empresa;

	public Serie() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}


	public String getDec() {
		return this.dec;
	}

	public void setDec(String dec) {
		this.dec = dec;
	}


	public String getHab() {
		return this.hab;
	}

	public void setHab(String hab) {
		this.hab = hab;
	}


	//bi-directional many-to-one association to Factura
	@OneToMany(mappedBy="serie")
	public List<Factura> getFacturas() {
		return this.facturas;
	}

	public void setFacturas(List<Factura> facturas) {
		this.facturas = facturas;
	}

	public Factura addFactura(Factura factura) {
		getFacturas().add(factura);
		factura.setSerie(this);

		return factura;
	}

	public Factura removeFactura(Factura factura) {
		getFacturas().remove(factura);
		factura.setSerie(null);

		return factura;
	}


	//bi-directional many-to-one association to Empresa
	@ManyToOne
	public Empresa getEmpresa() {
		return this.empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

}