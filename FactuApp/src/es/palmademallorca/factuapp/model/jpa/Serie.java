package es.palmademallorca.factuapp.model.jpa;

import java.io.Serializable;
import javax.persistence.*;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


/**
 * The persistent class for the serie database table.
 *
 */
@Entity

@Table(name="serie")

@NamedQuery(name = "Serie.findAll", query = "SELECT s FROM Serie s")
public class Serie implements Serializable {
	private static final long serialVersionUID = 1L;
	private StringProperty id=new SimpleStringProperty();
	private StringProperty dec=new SimpleStringProperty();
	private StringProperty hbl=new SimpleStringProperty();
	private Empresa empresa;

	public Serie() {
	}

	@Id
	@GeneratedValue(generator="SerieSeq")
    @SequenceGenerator(name="SerieSeq",sequenceName="factu.serie_seq", allocationSize=1)
	public String getId() {
		return this.id.get();
	}

	public void setId(String id) {
		this.id.set(id);
	}
	public StringProperty idProperty() {
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
	public String getHbl() {
		return this.hbl.get();
	}

	public void setHbl(String hbl) {
		this.hbl.set(hbl);
	}
	public StringProperty habProperty() {
		return hbl;
	}

	// bi-directional many-to-one association to Empresa
	@ManyToOne
	public Empresa getEmpresa() {
		return this.empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(this.getId()).append(" ").append(this.getDec());
		return sb.toString();
	}



}