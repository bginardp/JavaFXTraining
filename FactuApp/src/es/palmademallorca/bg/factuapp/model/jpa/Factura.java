package es.palmademallorca.bg.factuapp.model.jpa;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.LongProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * The persistent class for the facturas database table.
 *
 */
@Entity
@Table(name = "facturas")
@NamedQueries({ @NamedQuery(name = "Factura.findAll", query = "SELECT f FROM Factura f"),
		@NamedQuery(name = "Factura.findByEmp", query = "SELECT f FROM Factura f WHERE f.empresaId = :empresa_id"),
		@NamedQuery(name = "Factura.findByEmpEje", query = "SELECT f FROM Factura f WHERE f.empresaId = :empresa_id AND f.ejercicioId=:ejercicio"),
		@NamedQuery(name = "Factura.findFacturasByString", query = "SELECT f FROM Factura f WHERE f.empresaId = :empresa_id AND f.ejercicioId=:ejercicio"
				+ "  AND (f.cliente.nom like :criteria)" + " ORDER BY f.numero"),
		@NamedQuery(name = "Factura.findFacturasByNumber", query = "SELECT f FROM Factura f WHERE f.empresaId = :empresa_id AND f.ejercicioId=:ejercicio"
				+ "  AND (f.numero = :numero OR f.totfac = :totfac)" + " ORDER BY f.numero"),
		@NamedQuery(name = "Factura.findFacturasByDate", query = "SELECT f FROM Factura f WHERE f.empresaId = :empresa_id AND f.ejercicioId=:ejercicio"
				+ " AND (f.dat = :criteria)" + " ORDER BY f.numero")

})
public class Factura implements Serializable {
	private static final long serialVersionUID = 1L;
	private LongProperty id = new SimpleLongProperty();
	private SimpleObjectProperty<BigDecimal> baseirpf = new SimpleObjectProperty();
	private SimpleObjectProperty<BigDecimal> baseiva1 = new SimpleObjectProperty();
	private SimpleObjectProperty<BigDecimal> baseiva2 = new SimpleObjectProperty();
	private LongProperty clienteId = new SimpleLongProperty();
	private ObjectProperty<LocalDate> dat = new SimpleObjectProperty();;
	private IntegerProperty ejercicioId = new SimpleIntegerProperty();
	private LongProperty empresaId = new SimpleLongProperty();
	private LongProperty forpagId = new SimpleLongProperty();
	private SimpleObjectProperty<BigDecimal> impbru = new SimpleObjectProperty();
	private SimpleObjectProperty<BigDecimal> pordto = new SimpleObjectProperty();
	private SimpleObjectProperty<BigDecimal> impdto = new SimpleObjectProperty();
	private LongProperty numero = new SimpleLongProperty();
	private SimpleObjectProperty<BigDecimal> porirpf = new SimpleObjectProperty();
	private SimpleObjectProperty<BigDecimal> poriva1 = new SimpleObjectProperty();
	private SimpleObjectProperty<BigDecimal> poriva2 = new SimpleObjectProperty();
	private SimpleStringProperty serieId = new SimpleStringProperty();
	private SimpleObjectProperty<BigDecimal> totfac = new SimpleObjectProperty();
	private SimpleObjectProperty<Cliente> cliente = new SimpleObjectProperty();
	private SimpleObjectProperty<Empresa> empresa = new SimpleObjectProperty();
	private SimpleObjectProperty<Formaspago> formaspago = new SimpleObjectProperty();
	private SimpleObjectProperty<Serie> serie = new SimpleObjectProperty();
	private List<Factureslin> factureslins;
	// bi-directional many-to-one association to Factureslin

	public Factura() {
	}

	@Id
	@GeneratedValue(generator = "FacturaSeq")
	@SequenceGenerator(name = "FacturaSeq", sequenceName = "factu.factura_seq", allocationSize = 1)
	@Access(AccessType.PROPERTY)
	public Long getId() {
		return this.idProperty().get();
	}

	public void setId(Long id) {
		this.idProperty().set(id);
	}

	public LongProperty idProperty() {
		return this.id;
	}

	@Column(name = "cliente_id")
	public long getClienteId() {
		return this.clienteIdProperty().get();
	}

	public void setClienteId(long clienteId) {
		this.clienteIdProperty().set(clienteId);
	}

	public LongProperty clienteIdProperty() {
		return this.clienteId;
	}

	@Column(name = "serie_id")
	public java.lang.String getSerieId() {
		return this.serieIdProperty().get();
	}

	public void setSerieId(final java.lang.String serieId) {
		this.serieIdProperty().set(serieId);
	}

	public SimpleStringProperty serieIdProperty() {
		return this.serieId;

	}

	public ObjectProperty<LocalDate> datProperty() {
		return dat;
	}

	@Column(name = "DAT")
	public LocalDate getDat() {
		return datProperty().get();
	}

	public void setDat(LocalDate dat) {
		datProperty().set(dat);
	}

	@Column(name = "empresa_id")
	public Long getEmpresaId() {
		return this.empresaId.get();
	}

	public void setEmpresaId(Long empresaId) {
		this.empresaId.set(empresaId);
	}

	@Column(name = "forpag_id")
	public Long getForpagId() {
		return this.forpagId.get();
	}

	public void setForpagId(Long forpagId) {
		this.forpagId.set(forpagId);
	}

	@ManyToOne
	@JoinColumn(name = "cliente_id", insertable = false, updatable = false)
	public Cliente getCliente() {
		return this.clienteProperty().get();
	}

	public void setCliente(Cliente cliente) {
		this.clienteProperty().set(cliente);
	}

	public SimpleObjectProperty<Cliente> clienteProperty() {
		return this.cliente;
	}

	public String getNomCliente() {
		return this.getCliente().getNom();
	}

	// uni-directional many-to-one association to Empresa
	@ManyToOne
	@JoinColumn(name = "empresa_id", insertable = false, updatable = false)
	public Empresa getEmpresa() {
		return this.empresa.get();
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa.set(empresa);
	}

	public SimpleObjectProperty<Empresa> empresaProperty() {
		return this.empresa;
	}

	// uni-directional many-to-one association to Formaspago
	@ManyToOne
	@JoinColumn(name = "forpag_id", insertable = false, updatable = false)
	public Formaspago getFormaspago() {
		return this.formaspago.get();
	}

	public void setFormaspago(Formaspago formaspago) {
		this.formaspago.set(formaspago);
	}

	public SimpleObjectProperty<Formaspago> formaspagoProperty() {
		return this.formaspago;
	}

	// uni-directional many-to-one association to Serie
	@ManyToOne
	@JoinColumns({
			@JoinColumn(name = "empresa_id", referencedColumnName = "empresa_id", insertable = false, updatable = false),
			@JoinColumn(name = "serie_id", referencedColumnName = "id", insertable = false, updatable = false), })
	public Serie getSerie() {
		return this.serie.get();
	}

	public void setSerie(Serie serie) {
		this.serie.set(serie);
	}

	public SimpleObjectProperty<Serie> serieProperty() {
		return this.serie;
	}

	@OneToMany(mappedBy = "factura")
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

	public SimpleObjectProperty<BigDecimal> baseirpfProperty() {
		return this.baseirpf;
	}

	public java.math.BigDecimal getBaseirpf() {
		return this.baseirpfProperty().get();
	}

	public void setBaseirpf(final java.math.BigDecimal baseirpf) {
		this.baseirpfProperty().set(baseirpf);
	}

	public SimpleObjectProperty<BigDecimal> baseiva1Property() {
		return this.baseiva1;
	}

	public java.math.BigDecimal getBaseiva1() {
		return this.baseiva1Property().get();
	}

	public void setBaseiva1(final java.math.BigDecimal baseiva1) {
		this.baseiva1Property().set(baseiva1);
	}

	public SimpleObjectProperty<BigDecimal> baseiva2Property() {
		return this.baseiva2;
	}

	public java.math.BigDecimal getBaseiva2() {
		return this.baseiva2Property().get();
	}

	public void setBaseiva2(final java.math.BigDecimal baseiva2) {
		this.baseiva2Property().set(baseiva2);
	}

	public SimpleObjectProperty<BigDecimal> impbruProperty() {
		return this.impbru;
	}

	public java.math.BigDecimal getImpbru() {
		return this.impbruProperty().get();
	}

	public void setImpbru(final java.math.BigDecimal impbru) {
		this.impbruProperty().set(impbru);
	}

	public SimpleObjectProperty<BigDecimal> impdtoProperty() {
		return this.impdto;
	}

	public java.math.BigDecimal getImpdto() {
		return this.impdtoProperty().get();
	}

	public void setImpdto(final java.math.BigDecimal impdto) {
		this.impdtoProperty().set(impdto);
	}

	public LongProperty numeroProperty() {
		return this.numero;
	}

	public long getNumero() {
		return this.numeroProperty().get();
	}

	public void setNumero(final long numero) {
		this.numeroProperty().set(numero);
	}

	public SimpleObjectProperty<BigDecimal> porirpfProperty() {
		return this.porirpf;
	}

	public java.math.BigDecimal getPorirpf() {
		return this.porirpfProperty().get();
	}

	public void setPorirpf(final java.math.BigDecimal porirpf) {
		this.porirpfProperty().set(porirpf);
	}

	public final SimpleObjectProperty<BigDecimal> poriva1Property() {
		return this.poriva1;
	}

	public java.math.BigDecimal getPoriva1() {
		return this.poriva1Property().get();
	}

	public void setPoriva1(final java.math.BigDecimal poriva1) {
		this.poriva1Property().set(poriva1);
	}

	public SimpleObjectProperty<BigDecimal> poriva2Property() {
		return this.poriva2;
	}

	public java.math.BigDecimal getPoriva2() {
		return this.poriva2Property().get();
	}

	public void setPoriva2(final java.math.BigDecimal poriva2) {
		this.poriva2Property().set(poriva2);
	}

	public SimpleObjectProperty<BigDecimal> totfacProperty() {
		return this.totfac;
	}

	public java.math.BigDecimal getTotfac() {
		return this.totfacProperty().get();
	}

	public void setTotfac(final java.math.BigDecimal totfac) {
		this.totfacProperty().set(totfac);
	}

	public IntegerProperty ejercicioIdProperty() {
		return this.ejercicioId;
	}

	@Column(name = "ejercicio_id")
	public int getEjercicioId() {
		return this.ejercicioIdProperty().get();
	}

	public void setEjercicioId(final int ejercicioId) {
		this.ejercicioIdProperty().set(ejercicioId);
	}

	public SimpleObjectProperty<BigDecimal> pordtoProperty() {
		return this.pordto;
	}

	public java.math.BigDecimal getPordto() {
		return this.pordtoProperty().get();
	}

	public void setPordto(final java.math.BigDecimal pordto) {
		this.pordtoProperty().set(pordto);
	}

	@Override
	public String toString() {
		return "Factura [id=" + id.get() + ", baseirpf=" + baseirpf.get() + ", baseiva1=" + baseiva1.get()
				+ ", baseiva2=" + baseiva2.get() + ", clienteId=" + clienteId.get() + ", dat=" + dat + ", ejercicio="
				+ ejercicioId.get() + ", empresaId=" + empresaId.get() + ", forpagId=" + forpagId.get() + ", impbru="
				+ impbru.get() + ", pordto=" + pordto.get() + ", impdto=" + impdto.get() + ", numero=" + numero.get()
				+ ", porirpf=" + porirpf.get() + ", poriva1=" + poriva1.get() + ", poriva2=" + poriva2.get()
				+ ", serieId=" + serieId.get() + ", totfac=" + totfac.get() + ", cliente=" + cliente.getName() + "]";
	}

}