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

	@Temporal(TemporalType.DATE)
	private Date dat;

	private Integer ejercicio;

	private BigDecimal impbru;

	private BigDecimal impdto;

	private Integer numero;

	private BigDecimal porirpf;

	private BigDecimal poriva1;

	private BigDecimal poriva2;

	private BigDecimal totfac;

	//uni-directional many-to-one association to Cliente
	@ManyToOne
	private Cliente cliente;

	//uni-directional many-to-one association to Empresa
	/* Aquest atribut només s'hauria d'actualitzar a través de la serie i per evitar l'error
	 * Only one may be defined as writable, all others must be specified read-only
	 * s'afegeix insertable i updatable=false a la definició de la relació
	 * font https://www.eclipse.org/forums/index.php/t/485946/
	 */
	@ManyToOne
	@JoinColumn(name="empresa_id", insertable=false, updatable=false)
	private Empresa empresa;

	//uni-directional many-to-one association to Formaspago
	@ManyToOne
	@JoinColumn(name="forpag_id")
	private Formaspago formaspago;

	//uni-directional many-to-one association to Serie
	@ManyToOne
	@JoinColumns({
		@JoinColumn(name="empresa_id", referencedColumnName="empresa_id"),
		@JoinColumn(name="serie_id", referencedColumnName="id")
		})
	private Serie serie;



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

	public Cliente getCliente() {
		return this.cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Empresa getEmpresa() {
		return this.empresa;
	}

	public Formaspago getFormaspago() {
		return this.formaspago;
	}

	public void setFormaspago(Formaspago formaspago) {
		this.formaspago = formaspago;
	}

	public Serie getSerie() {
		return this.serie;
	}

	public void setSerie(Serie serie) {
		this.serie = serie;
	}


}