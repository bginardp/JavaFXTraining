package es.palmademallorca.factuapp.model.jpa;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the facturas database table.
 * 
 */
@Embeddable
public class FacturaPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String serieId;
	private Integer id;

	public FacturaPK() {
	}

	@Column(name="serie_id", insertable=false, updatable=false)
	public String getSerieId() {
		return this.serieId;
	}
	public void setSerieId(String serieId) {
		this.serieId = serieId;
	}

	public Integer getId() {
		return this.id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof FacturaPK)) {
			return false;
		}
		FacturaPK castOther = (FacturaPK)other;
		return 
			this.serieId.equals(castOther.serieId)
			&& this.id.equals(castOther.id);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.serieId.hashCode();
		hash = hash * prime + this.id.hashCode();
		
		return hash;
	}
}