package es.palmademallorca.bg.factuapp.model.jpa;

import java.io.Serializable;
import javax.persistence.*;

import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

import java.math.BigDecimal;


/**
 * The persistent class for the factureslin database table.
 *
 */
@Entity
@NamedQuery(name="Factureslin.findAll", query="SELECT f FROM Factureslin f")
public class Factureslin implements Serializable {
	private static final long serialVersionUID = 1L;
	private LongProperty id=new SimpleLongProperty();
	private SimpleObjectProperty<BigDecimal> cantidad=new SimpleObjectProperty();
	private SimpleStringProperty dem=new SimpleStringProperty();
	private SimpleObjectProperty<BigDecimal> importe=new SimpleObjectProperty();
	private SimpleObjectProperty<BigDecimal> pordte=new SimpleObjectProperty();
	private SimpleObjectProperty<BigDecimal> poriva=new SimpleObjectProperty();
	private SimpleObjectProperty<BigDecimal> preu=new SimpleObjectProperty();
	private SimpleStringProperty producteId=new SimpleStringProperty();
	private SimpleObjectProperty<BigDecimal> requiv=new SimpleObjectProperty();
	private LongProperty tipivaId=new SimpleLongProperty();
	private SimpleObjectProperty<Factura> factura=new SimpleObjectProperty();

	public Factureslin() {
	}


	public LongProperty idProperty() {
		return this.id;
	}

	@Id
	public long getId() {
		return this.idProperty().get();
	}


	public void setId(final long id) {
		this.idProperty().set(id);
	}


	public SimpleObjectProperty<BigDecimal> cantidadProperty() {
		return this.cantidad;
	}


	public java.math.BigDecimal getCantidad() {
		return this.cantidadProperty().get();
	}


	public void setCantidad(final java.math.BigDecimal cantidad) {
		this.cantidadProperty().set(cantidad);
	}


	public SimpleStringProperty demProperty() {
		return this.dem;
	}


	public java.lang.String getDem() {
		return this.demProperty().get();
	}


	public void setDem(final java.lang.String dem) {
		this.demProperty().set(dem);
	}


	public SimpleObjectProperty<BigDecimal> importeProperty() {
		return this.importe;
	}

	@Column(name="import")
	public java.math.BigDecimal getImporte() {
		return this.importeProperty().get();
	}


	public void setImporte(final java.math.BigDecimal importe) {
		this.importeProperty().set(importe);
	}


	public SimpleObjectProperty<BigDecimal> pordteProperty() {
		return this.pordte;
	}


	public java.math.BigDecimal getPordte() {
		return this.pordteProperty().get();
	}


	public void setPordte(final java.math.BigDecimal pordte) {
		this.pordteProperty().set(pordte);
	}


	public SimpleObjectProperty<BigDecimal> porivaProperty() {
		return this.poriva;
	}


	public java.math.BigDecimal getPoriva() {
		return this.porivaProperty().get();
	}


	public void setPoriva(final java.math.BigDecimal poriva) {
		this.porivaProperty().set(poriva);
	}


	public SimpleObjectProperty<BigDecimal> preuProperty() {
		return this.preu;
	}


	public java.math.BigDecimal getPreu() {
		return this.preuProperty().get();
	}


	public void setPreu(final java.math.BigDecimal preu) {
		this.preuProperty().set(preu);
	}


	public SimpleStringProperty producteIdProperty() {
		return this.producteId;
	}

	@Column(name="producte_id")
	public java.lang.String getProducteId() {
		return this.producteIdProperty().get();
	}


	public void setProducteId(final java.lang.String producteId) {
		this.producteIdProperty().set(producteId);
	}


	public SimpleObjectProperty<BigDecimal> requivProperty() {
		return this.requiv;
	}


	public java.math.BigDecimal getRequiv() {
		return this.requivProperty().get();
	}


	public void setRequiv(final java.math.BigDecimal requiv) {
		this.requivProperty().set(requiv);
	}


	public LongProperty tipivaIdProperty() {
		return this.tipivaId;
	}

	@Column(name="tipiva_id")
	public long getTipivaId() {
		return this.tipivaIdProperty().get();
	}


	public void setTipivaId(final long tipivaId) {
		this.tipivaIdProperty().set(tipivaId);
	}


	public SimpleObjectProperty<Factura> facturaProperty() {
		return this.factura;
	}

	@ManyToOne
	public es.palmademallorca.bg.factuapp.model.jpa.Factura getFactura() {
		return this.facturaProperty().get();
	}


	public void setFactura(final es.palmademallorca.bg.factuapp.model.jpa.Factura factura) {
		this.facturaProperty().set(factura);
	}


	@Override
	public String toString() {
		return "Factureslin [getId()=" + getId() + ", getCantidad()=" + getCantidad() + ", getDem()=" + getDem()
				+ ", getImporte()=" + getImporte() + ", getPordte()=" + getPordte() + ", getPoriva()=" + getPoriva()
				+ ", getPreu()=" + getPreu() + ", getProducteId()=" + getProducteId() + ", getRequiv()=" + getRequiv()
				+ ", getTipivaId()=" + getTipivaId() + "]";
	}



}