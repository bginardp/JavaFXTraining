package es.palmademallorca.factuapp.model.jpa;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the empresas database table.
 * 
 */
@Entity
@Table(name="empresas")
@NamedQuery(name="Empresa.findAll", query="SELECT e FROM Empresa e")
public class Empresa implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String dec;
	private String dem;
	private String hab;
	private List<Serie> series;

	public Empresa() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}


	public String getDec() {
		return this.dec;
	}

	public void setDec(String dec) {
		this.dec = dec;
	}


	public String getDem() {
		return this.dem;
	}

	public void setDem(String dem) {
		this.dem = dem;
	}


	public String getHab() {
		return this.hab;
	}

	public void setHab(String hab) {
		this.hab = hab;
	}


	//bi-directional many-to-one association to Serie
	@OneToMany(mappedBy="empresa")
	public List<Serie> getSeries() {
		return this.series;
	}

	public void setSeries(List<Serie> series) {
		this.series = series;
	}

	public Serie addSery(Serie sery) {
		getSeries().add(sery);
		sery.setEmpresa(this);

		return sery;
	}

	public Serie removeSery(Serie sery) {
		getSeries().remove(sery);
		sery.setEmpresa(null);

		return sery;
	}

}