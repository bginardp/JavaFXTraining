package es.palmademallorca.factuapp.model.jpa;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * JPA Class Cliente
 */
@Entity
@Table(name = "clientes")
@NamedQueries ({
	@NamedQuery(name="Cliente.findAll", query="SELECT c FROM Cliente c"),
    @NamedQuery(name="Cliente.findByNom", query="SELECT c FROM Cliente c WHERE c.nom like :nom")
})
public class Cliente implements Serializable {

	private static final long serialVersionUID = 1L;
	private LongProperty id = new SimpleLongProperty();
	private StringProperty cif= new SimpleStringProperty();
   	private StringProperty nom = new SimpleStringProperty();
	private StringProperty direccion = new SimpleStringProperty();
	private StringProperty municipio = new SimpleStringProperty();
	private StringProperty provincia= new SimpleStringProperty();
	private StringProperty postal= new SimpleStringProperty();
	private StringProperty tel= new SimpleStringProperty();
	private StringProperty movil= new SimpleStringProperty();
	private StringProperty email= new SimpleStringProperty();



	/**
	 * Class constructor methods
	 */
	public Cliente() {
		super();
	}

	@Id
	@GeneratedValue(generator="CliSeq")
    @SequenceGenerator(name="CliSeq",sequenceName="factu.clientes_seq", allocationSize=1)
	public Long getId() {
		return id.get();
	}

	public void setId(Long id) {
		this.id.set(id);
	}

	public LongProperty idProperty() {
		return id;
	}

	public String getCif() {
		return cif.get();
	}

	public void setCif(String cif) {
		this.cif.set(cif);
	}
	public StringProperty cifProperty() {
		return cif;
	}
	public String getNom() {
		return nom.get();
	}

	public void setNom(String nom) {
		this.nom.set(nom);
	}

	public StringProperty nomProperty() {
		return nom;
	}

	public String getDireccion() {
		return direccion.get();
	}

	public void setDireccion(String direccion) {
		this.direccion.set(direccion);
	}
	public StringProperty direccionProperty() {
		return direccion;
	}
	public String getMunicipio() {
		return municipio.get();
	}

	public void setMunicipio(String municipio) {
		this.municipio.set(municipio);
	}
	public StringProperty municipioProperty() {
		return municipio;
	}
	public String getProvincia() {
		return provincia.get();
	}

	public void setProvincia(String provincia) {
		this.provincia.set(provincia);
	}

	public StringProperty provinciaProperty() {
		return provincia;
	}
	public String getPostal() {
		return postal.get();
	}

	public void setPostal(String postal) {
		this.postal.set(postal);
	}
	public StringProperty postalProperty() {
		return postal;
	}
	public String getTel() {
		return tel.get();
	}

	public void setTel(String tel) {
		this.tel.set(tel);
	}
	public StringProperty telProperty() {
		return tel;
	}
	public String getMovil() {
		return movil.get();
	}

	public void setMovil(String movil) {
		this.movil.set(movil);
	}
	public StringProperty movilProperty() {
		return movil;
	}
	public String getEmail() {
		return email.get();
	}

	public void setEmail(String email) {
		this.email.set(email);
	}
	public StringProperty emailProperty() {
		return email;
	}
	/**
	 * Methods get/set the fields of database
	 */


	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(this.getId()).append(" ").append(this.getCif()).append(" ").append(this.getNom());
		return sb.toString();
	}


}
