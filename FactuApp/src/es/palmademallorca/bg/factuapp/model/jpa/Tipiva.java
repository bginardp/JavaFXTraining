package es.palmademallorca.bg.factuapp.model.jpa;

import java.io.Serializable;
import javax.persistence.*;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.FloatProperty;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.math.BigDecimal;
import java.util.List;


/**
 * The persistent class for the tipiva database table.
 *
 */
@Entity
@Table(name="tipiva")

@NamedQuery(name="Tipiva.findAll", query="SELECT t FROM Tipiva t")
public class Tipiva implements Serializable {
	private static final long serialVersionUID = 1L;
	private LongProperty id=new SimpleLongProperty();
	private LongProperty anyo=new SimpleLongProperty();
	private StringProperty dem=new SimpleStringProperty();
	private LongProperty mes=new SimpleLongProperty();
	private SimpleObjectProperty<BigDecimal> poriva = new SimpleObjectProperty();
	private SimpleObjectProperty<BigDecimal> requiv = new SimpleObjectProperty();
	private StringProperty tipo=new SimpleStringProperty();

	public Tipiva() {
	}


	@Id
	@GeneratedValue(generator="TipivaSeq")
    @SequenceGenerator(name="TipivaSeq",sequenceName="factu.tipiva_seq", allocationSize=1)
	public Long getId() {
		return this.id.get();
	}

	public void setId(Long id) {
		this.id.set(id);
	}


	public Long getAnyo() {
		return this.anyo.get();
	}

	public void setAnyo(Long anyo) {
		this.anyo.set(anyo);
	}


	public String getDem() {
		return this.dem.get();
	}

	public void setDem(String dem) {
		this.dem.set(dem);
	}


	public Long getMes() {
		return this.mes.get();
	}

	public void setMes(Long mes) {
		this.mes.set(mes);
	}


	public BigDecimal getPoriva() {
		return this.poriva.get();
	}

	public void setPoriva(BigDecimal poriva) {
		this.poriva.set(poriva);
	}


	public BigDecimal getRequiv() {
		return this.requiv.get();
	}

	public void setRequiv(BigDecimal requiv) {
		this.requiv.set(requiv);
	}


	public String getTipo() {
		return this.tipo.get();
	}

	public void setTipo(String tipo) {
		this.tipo.set(tipo);
	}


	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(this.getId()).append(" ").append(this.getDem()).append(" ").append(this.getPoriva()).append(" ").append(this.getRequiv());
		return sb.toString();
	}




}