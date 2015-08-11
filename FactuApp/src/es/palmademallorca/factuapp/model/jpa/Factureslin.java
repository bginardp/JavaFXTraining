package es.palmademallorca.factuapp.model.jpa;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the factureslin database table.
 * 
 */
@Entity
@NamedQuery(name="Factureslin.findAll", query="SELECT f FROM Factureslin f")
public class Factureslin implements Serializable {
	private static final long serialVersionUID = 1L;
	private FactureslinPK id;
	private BigDecimal cantidad;
	private String dem;
	private BigDecimal import_;
	private BigDecimal perdte;
	private BigDecimal preu;
	private Factura factura;
	private ProductoJPA producto;

	public Factureslin() {
	}


	@EmbeddedId
	public FactureslinPK getId() {
		return this.id;
	}

	public void setId(FactureslinPK id) {
		this.id = id;
	}


	public BigDecimal getCantidad() {
		return this.cantidad;
	}

	public void setCantidad(BigDecimal cantidad) {
		this.cantidad = cantidad;
	}


	public String getDem() {
		return this.dem;
	}

	public void setDem(String dem) {
		this.dem = dem;
	}


	@Column(name="import")
	public BigDecimal getImport_() {
		return this.import_;
	}

	public void setImport_(BigDecimal import_) {
		this.import_ = import_;
	}


	public BigDecimal getPerdte() {
		return this.perdte;
	}

	public void setPerdte(BigDecimal perdte) {
		this.perdte = perdte;
	}


	public BigDecimal getPreu() {
		return this.preu;
	}

	public void setPreu(BigDecimal preu) {
		this.preu = preu;
	}


	//bi-directional many-to-one association to Factura
	@ManyToOne
	@JoinColumns({
		@JoinColumn(name="factura_id", referencedColumnName="id"),
		@JoinColumn(name="factura_serial", referencedColumnName="serie_id")
		})
	public Factura getFactura() {
		return this.factura;
	}

	public void setFactura(Factura factura) {
		this.factura = factura;
	}


	//bi-directional many-to-one association to Producto
	@ManyToOne
	@JoinColumn(name="producte_id")
	public ProductoJPA getProducto() {
		return this.producto;
	}

	public void setProducto(ProductoJPA producto) {
		this.producto = producto;
	}

}