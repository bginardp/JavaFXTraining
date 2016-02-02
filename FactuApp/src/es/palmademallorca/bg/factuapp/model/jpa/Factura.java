package es.palmademallorca.bg.factuapp.model.jpa;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleObjectProperty;

/**
 * The persistent class for the facturas database table.
 *
 */
@Entity
@Table(name = "facturas")
@NamedQueries ({
	@NamedQuery(name = "Factura.findAll", query = "SELECT f FROM Factura f"),
	@NamedQuery(name="Factura.findByEmp", query="SELECT f FROM Factura f WHERE f.empresa.id = :empresa_id")
})
public class Factura implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 3838441874768652435L;

	private LongProperty id=new SimpleLongProperty();
	@Transient
	private SimpleObjectProperty<BigDecimal> baseirpf= new SimpleObjectProperty();;
	@Transient
	private SimpleObjectProperty<BigDecimal> baseiva1= new SimpleObjectProperty();;
	@Transient
	private SimpleObjectProperty<BigDecimal> baseiva2= new SimpleObjectProperty();;
	@Transient
	//@Temporal(TemporalType.DATE)
	private SimpleObjectProperty<LocalDate> dat=new SimpleObjectProperty();
	@Transient
	private IntegerProperty ejercicio= new SimpleIntegerProperty();
	@Transient
	private SimpleObjectProperty<BigDecimal> impbru= new SimpleObjectProperty();;
	@Transient
	private SimpleObjectProperty<BigDecimal> impdto= new SimpleObjectProperty();;
    @Transient
	private LongProperty numero=new SimpleLongProperty();;
	@Transient
	private SimpleObjectProperty<BigDecimal> porirpf= new SimpleObjectProperty();;
	@Transient
	private SimpleObjectProperty<BigDecimal> poriva1= new SimpleObjectProperty();;
	@Transient
	private SimpleObjectProperty<BigDecimal> poriva2= new SimpleObjectProperty();;
	@Transient
	private SimpleObjectProperty<BigDecimal> totfac= new SimpleObjectProperty();;

	// uni-directional many-to-one association to Cliente
	@ManyToOne
	private Cliente cliente;

	// uni-directional many-to-one association to Empresa
	/*
	 * Aquest atribut només s'hauria d'actualitzar a través de la serie i per
	 * evitar l'error Only one may be defined as writable, all others must be
	 * specified read-only s'afegeix insertable i updatable=false a la definició
	 * de la relació font https://www.eclipse.org/forums/index.php/t/485946/
	 */
	@ManyToOne
	@JoinColumn(name = "empresa_id", insertable = false, updatable = false)
	private Empresa empresa;

	// uni-directional many-to-one association to Formaspago
	@ManyToOne
	@JoinColumn(name = "forpag_id")
	private Formaspago formaspago;

	// uni-directional many-to-one association to Serie
	@ManyToOne
	@JoinColumns({ @JoinColumn(name = "empresa_id", referencedColumnName = "empresa_id"),
			@JoinColumn(name = "serie_id", referencedColumnName = "id") })
	private Serie serie;

	// bi-directional many-to-one association to Factureslin
	// @OneToMany(mappedBy = "factura")
	//private List<Factureslin> factureslins;

	public Factura() {
	}

/*	public List<Factureslin> getFactureslins() {
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
*/
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

	@Id
	@SequenceGenerator(name = "FACTURAS_ID_GENERATOR", sequenceName = "FACTURA_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "FACTURAS_ID_GENERATOR")
	public Long getId() {
		return this.id.get();
	}

	public void setId(Long id) {
		this.id.set(id);
	}


	public  Integer getEjercicio() {
		return this.ejercicio.get();
	}

	public  void setEjercicio(Integer ejercicio) {
		this.ejercicio.set(ejercicio);
	}


	public  Long getNumero() {
		return this.numero.get();
	}

	public void setNumero(Long numero) {
		this.numero.set(numero);
	}

	public final SimpleObjectProperty<LocalDate> datProperty() {
		return this.dat;
	}

	public final java.time.LocalDate getDat() {
		return this.datProperty().get();
	}

	public final void setDat(final java.time.LocalDate dat) {
		this.datProperty().set(dat);
	}

	public final SimpleObjectProperty<BigDecimal> baseirpfProperty() {
		return this.baseirpf;
	}

	public final java.math.BigDecimal getBaseirpf() {
		return this.baseirpfProperty().get();
	}

	public final void setBaseirpf(final java.math.BigDecimal baseirpf) {
		this.baseirpfProperty().set(baseirpf);
	}

	public final SimpleObjectProperty<BigDecimal> baseiva1Property() {
		return this.baseiva1;
	}

	public final java.math.BigDecimal getBaseiva1() {
		return this.baseiva1Property().get();
	}

	public final void setBaseiva1(final java.math.BigDecimal baseiva1) {
		this.baseiva1Property().set(baseiva1);
	}



	public final SimpleObjectProperty<BigDecimal> impbruProperty() {
		return this.impbru;
	}

	public final java.math.BigDecimal getImpbru() {
		return this.impbruProperty().get();
	}

	public final void setImpbru(final java.math.BigDecimal impbru) {
		this.impbruProperty().set(impbru);
	}

	public final SimpleObjectProperty<BigDecimal> impdtoProperty() {
		return this.impdto;
	}

	public final java.math.BigDecimal getImpdto() {
		return this.impdtoProperty().get();
	}

	public final void setImpdto(final java.math.BigDecimal impdto) {
		this.impdtoProperty().set(impdto);
	}

	public final SimpleObjectProperty<BigDecimal> porirpfProperty() {
		return this.porirpf;
	}

	public final java.math.BigDecimal getPorirpf() {
		return this.porirpfProperty().get();
	}

	public final void setPorirpf(final java.math.BigDecimal porirpf) {
		this.porirpfProperty().set(porirpf);
	}

	public final SimpleObjectProperty<BigDecimal> poriva1Property() {
		return this.poriva1;
	}

	public final java.math.BigDecimal getPoriva1() {
		return this.poriva1Property().get();
	}

	public final void setPoriva1(final java.math.BigDecimal poriva1) {
		this.poriva1Property().set(poriva1);
	}

	public final SimpleObjectProperty<BigDecimal> poriva2Property() {
		return this.poriva2;
	}

	public final java.math.BigDecimal getPoriva2() {
		return this.poriva2Property().get();
	}

	public final void setPoriva2(final java.math.BigDecimal poriva2) {
		this.poriva2Property().set(poriva2);
	}

	public final SimpleObjectProperty<BigDecimal> totfacProperty() {
		return this.totfac;
	}

	public final java.math.BigDecimal getTotfac() {
		return this.totfacProperty().get();
	}

	public final void setTotfac(final java.math.BigDecimal totfac) {
		this.totfacProperty().set(totfac);
	}

	public final SimpleObjectProperty<BigDecimal> baseiva2Property() {
		return this.baseiva2;
	}


	public final java.math.BigDecimal getBaseiva2() {
		return this.baseiva2Property().get();
	}


	public final void setBaseiva2(final java.math.BigDecimal baseiva2) {
		this.baseiva2Property().set(baseiva2);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Factura [id=");
		builder.append(id);
		builder.append(", baseirpf=");
		builder.append(baseirpf);
		builder.append(", baseiva1=");
		builder.append(baseiva1);
		builder.append(", baseiva2=");
		builder.append(baseiva2);
		builder.append(", dat=");
		builder.append(dat);
		builder.append(", ejercicio=");
		builder.append(ejercicio);
		builder.append(", impbru=");
		builder.append(impbru);
		builder.append(", impdto=");
		builder.append(impdto);
		builder.append(", numero=");
		builder.append(numero);
		builder.append(", porirpf=");
		builder.append(porirpf);
		builder.append(", poriva1=");
		builder.append(poriva1);
		builder.append(", poriva2=");
		builder.append(poriva2);
		builder.append(", totfac=");
		builder.append(totfac);
		builder.append("]");
		return builder.toString();
	}




}