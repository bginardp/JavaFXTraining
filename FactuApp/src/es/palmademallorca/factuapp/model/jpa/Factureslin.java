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

	@Column(name="producte_id")
	private String producteId;

	private BigDecimal requiv;

	@Column(name="tipiva_id")
	private Integer tipivaId;

	//bi-directional many-to-one association to Factura
	@ManyToOne
	private Factura factura;

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

	public String getProducteId() {
		return this.producteId;
	}

	public void setProducteId(String producteId) {
		this.producteId = producteId;
	}

	public BigDecimal getRequiv() {
		return this.requiv;
	}

	public void setRequiv(BigDecimal requiv) {
		this.requiv = requiv;
	}

	public Integer getTipivaId() {
		return this.tipivaId;
	}

	public void setTipivaId(Integer tipivaId) {
		this.tipivaId = tipivaId;
	}

	public Factura getFactura() {
		return this.factura;
	}

	public void setFactura(Factura factura) {
		this.factura = factura;
	}

}