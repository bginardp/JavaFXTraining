package es.palmademallorca.bg.factuapp.model.jpa;

import java.io.Serializable;
import javax.persistence.*;

import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.List;


/**
 * The persistent class for the productos database table.
 *
 */
@Entity
@Table(name="productos")
@NamedQueries ({
@NamedQuery(name="Producto.findAll", query="SELECT p FROM Producto p"),
@NamedQuery(name="Producto.findByDem", query="SELECT p FROM Producto p WHERE p.dem like :dem")
})
public class Producto implements Serializable {
	private static final long serialVersionUID = 1L;
	private StringProperty id= new SimpleStringProperty();
   	private StringProperty dem = new SimpleStringProperty();
	private StringProperty hbl = new SimpleStringProperty();

	private List<Factureslin> factureslins;

	public Producto() {
	}


	@Id
	public String getId() {
		return this.id.get();
	}

	public void setId(String id) {
		this.id.set(id);
	}

	public StringProperty idProperty() {
		return id;
	}

	public String getDem() {
		return this.dem.get();
	}

	public void setDem(String dem) {
		this.dem.set(dem);
	}

	public StringProperty demProperty() {
		return dem;
	}

	public String getHbl() {
		return this.hbl.get();
	}

	public void setHbl(String hbl) {
		this.hbl.set(hbl);
	}

	public StringProperty hblProperty() {
		return hbl;
	}


	public void setFactureslins(List<Factureslin> factureslins) {
		this.factureslins = factureslins;
	}


	@Override
	public String toString() {
		return "Producto [id=" + id + ", dem=" + dem + ", hbl=" + hbl + "]";
	}




}