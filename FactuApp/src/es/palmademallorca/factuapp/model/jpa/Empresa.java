package es.palmademallorca.factuapp.model.jpa;

import java.io.Serializable;
import javax.persistence.*;

import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


/**
 * The persistent class for the empresas database table.
 *
 */
@Entity
@Table(name="empresas")

@NamedQueries ({
	@NamedQuery(name="Empresa.findAll", query="SELECT e FROM Empresa e"),
    @NamedQuery(name="Empresa.findByDem", query="SELECT e FROM Empresa e WHERE e.dem like :dem")
})

public class Empresa implements Serializable {
	private static final long serialVersionUID = 1L;
	private LongProperty id= new SimpleLongProperty();;
	private StringProperty dec= new SimpleStringProperty();
	private StringProperty dem= new SimpleStringProperty();
	private StringProperty direccion= new SimpleStringProperty();
	private StringProperty fax= new SimpleStringProperty();
	private StringProperty municipio= new SimpleStringProperty();
	private StringProperty nif= new SimpleStringProperty();
	private StringProperty provincia= new SimpleStringProperty();
	private StringProperty tel= new SimpleStringProperty();
	private StringProperty web= new SimpleStringProperty();

	public Empresa() {
	}


	@Id
	public Long getId() {
		return this.id.get();
	}

	public void setId(Long id) {
		this.id.set(id);
	}

	public LongProperty idProperty() {
		return id;
	}

	public String getDec() {
		return this.dec.get();
	}

	public void setDec(String dec) {
		this.dec.set(dec);
	}
	public StringProperty decProperty() {
		return dec;
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

	public String getDireccion() {
		return this.direccion.get();
	}

	public void setDireccion(String direccion) {
		this.direccion.set(direccion);
	}
	public StringProperty direccionProperty() {
		return direccion;
	}

	public String getFax() {
		return this.fax.get();
	}

	public void setFax(String fax) {
		this.fax.set(fax);
	}
	public StringProperty faxProperty() {
		return fax;
	}

	public String getMunicipio() {
		return this.municipio.get();
	}

	public void setMunicipio(String municipio) {
		this.municipio.set(municipio);
	}
	public StringProperty municipioProperty() {
		return municipio;
	}

	public String getNif() {
		return this.nif.get();
	}

	public void setNif(String nif) {
		this.nif.set(nif);
	}
	public StringProperty nifProperty() {
		return nif;
	}

	public String getProvincia() {
		return this.provincia.get();
	}

	public void setProvincia(String provincia) {
		this.provincia.set(provincia);
	}

	public StringProperty provinciaProperty() {
		return provincia;
	}
	public String getTel() {
		return this.tel.get();
	}

	public void setTel(String tel) {
		this.tel.set(tel);
	}

	public StringProperty telProperty() {
		return tel;
	}
	public String getWeb() {
		return this.web.get();
	}

	public void setWeb(String web) {
		this.web.set(web);
	}

	public StringProperty webProperty() {
		return web;
	}
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(this.getId()).append(" ").append(this.getDec());
		return sb.toString();
	}


}