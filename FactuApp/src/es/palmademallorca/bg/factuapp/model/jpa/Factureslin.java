package es.palmademallorca.bg.factuapp.model.jpa;

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

	@Id
	@SequenceGenerator(name="FACTURESLIN_ID_GENERATOR", sequenceName="FACLIN_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="FACTURESLIN_ID_GENERATOR")
	private Integer id;

	private BigDecimal cantidad;

	private String dem;

	@Column(name="import")
	private BigDecimal import_;

	private BigDecimal pordte;

	private BigDecimal poriva;

	private BigDecimal preu;


	private BigDecimal requiv;

	//bi-directional many-to-one association to Factura
	@ManyToOne
	private Factura factura;

			//uni-directional many-to-one association to Producto
		@ManyToOne
		@JoinColumn(name="producte_id")
		private Producto producto;

		//uni-directional many-to-one association to Tipiva
		@ManyToOne
		private Tipiva tipiva;


	public Factureslin() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
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

	public BigDecimal getImport_() {
		return this.import_;
	}

	public void setImport_(BigDecimal import_) {
		this.import_ = import_;
	}

	public BigDecimal getPordte() {
		return this.pordte;
	}

	public void setPordte(BigDecimal pordte) {
		this.pordte = pordte;
	}

	public BigDecimal getPoriva() {
		return this.poriva;
	}

	public void setPoriva(BigDecimal poriva) {
		this.poriva = poriva;
	}

	public BigDecimal getPreu() {
		return this.preu;
	}

	public void setPreu(BigDecimal preu) {
		this.preu = preu;
	}



	public BigDecimal getRequiv() {
		return this.requiv;
	}

	public void setRequiv(BigDecimal requiv) {
		this.requiv = requiv;
	}



	public Factura getFactura() {
		return this.factura;
	}

	public void setFactura(Factura factura) {
		this.factura = factura;
	}

	public Producto getProducto() {
		return this.producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public Tipiva getTipiva() {
		return this.tipiva;
	}

	public void setTipiva(Tipiva tipiva) {
		this.tipiva = tipiva;
	}

}