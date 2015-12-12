package es.palmademallorca.factuapp.model.jpa;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
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

	@Id
	@SequenceGenerator(name="FACTURAS_ID_GENERATOR", sequenceName="FACTURA_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="FACTURAS_ID_GENERATOR")
	private Integer id;

	private BigDecimal baseirpf;

	private BigDecimal baseiva1;

	private BigDecimal baseiva2;

	@Column(name="cliente_id")
	private Integer clienteId;

	@Temporal(TemporalType.DATE)
	private Date dat;

	private Integer ejercicio;

	@Column(name="empresa_id")
	private Integer empresaId;

	@Column(name="forpag_id")
	private Integer forpagId;

	private BigDecimal impbru;

	private BigDecimal impdto;

	private Integer numero;

	private BigDecimal porirpf;

	private BigDecimal poriva1;

	private BigDecimal poriva2;

	@Column(name="serie_id")
	private String serieId;

	private BigDecimal totfac;

	//bi-directional many-to-one association to Factureslin
	@OneToMany(mappedBy="factura")
	private List<Factureslin> factureslins;

	public Factura() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public BigDecimal getBaseirpf() {
		return this.baseirpf;
	}

	public void setBaseirpf(BigDecimal baseirpf) {
		this.baseirpf = baseirpf;
	}

	public BigDecimal getBaseiva1() {
		return this.baseiva1;
	}

	public void setBaseiva1(BigDecimal baseiva1) {
		this.baseiva1 = baseiva1;
	}

	public BigDecimal getBaseiva2() {
		return this.baseiva2;
	}

	public void setBaseiva2(BigDecimal baseiva2) {
		this.baseiva2 = baseiva2;
	}

	public Integer getClienteId() {
		return this.clienteId;
	}

	public void setClienteId(Integer clienteId) {
		this.clienteId = clienteId;
	}

	public Date getDat() {
		return this.dat;
	}

	public void setDat(Date dat) {
		this.dat = dat;
	}

	public Integer getEjercicio() {
		return this.ejercicio;
	}

	public void setEjercicio(Integer ejercicio) {
		this.ejercicio = ejercicio;
	}

	public Integer getEmpresaId() {
		return this.empresaId;
	}

	public void setEmpresaId(Integer empresaId) {
		this.empresaId = empresaId;
	}

	public Integer getForpagId() {
		return this.forpagId;
	}

	public void setForpagId(Integer forpagId) {
		this.forpagId = forpagId;
	}

	public BigDecimal getImpbru() {
		return this.impbru;
	}

	public void setImpbru(BigDecimal impbru) {
		this.impbru = impbru;
	}

	public BigDecimal getImpdto() {
		return this.impdto;
	}

	public void setImpdto(BigDecimal impdto) {
		this.impdto = impdto;
	}

	public Integer getNumero() {
		return this.numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public BigDecimal getPorirpf() {
		return this.porirpf;
	}

	public void setPorirpf(BigDecimal porirpf) {
		this.porirpf = porirpf;
	}

	public BigDecimal getPoriva1() {
		return this.poriva1;
	}

	public void setPoriva1(BigDecimal poriva1) {
		this.poriva1 = poriva1;
	}

	public BigDecimal getPoriva2() {
		return this.poriva2;
	}

	public void setPoriva2(BigDecimal poriva2) {
		this.poriva2 = poriva2;
	}

	public String getSerieId() {
		return this.serieId;
	}

	public void setSerieId(String serieId) {
		this.serieId = serieId;
	}

	public BigDecimal getTotfac() {
		return this.totfac;
	}

	public void setTotfac(BigDecimal totfac) {
		this.totfac = totfac;
	}

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