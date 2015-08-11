package es.palmademallorca.factuapp.model.jpa;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the factureslin database table.
 * 
 */
@Embeddable
public class FactureslinPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String facturaSerial;
	private Integer facturaId;
	private Integer id;

	public FactureslinPK() {
	}

	@Column(name="factura_serial", insertable=false, updatable=false)
	public String getFacturaSerial() {
		return this.facturaSerial;
	}
	public void setFacturaSerial(String facturaSerial) {
		this.facturaSerial = facturaSerial;
	}

	@Column(name="factura_id", insertable=false, updatable=false)
	public Integer getFacturaId() {
		return this.facturaId;
	}
	public void setFacturaId(Integer facturaId) {
		this.facturaId = facturaId;
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
		if (!(other instanceof FactureslinPK)) {
			return false;
		}
		FactureslinPK castOther = (FactureslinPK)other;
		return 
			this.facturaSerial.equals(castOther.facturaSerial)
			&& this.facturaId.equals(castOther.facturaId)
			&& this.id.equals(castOther.id);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.facturaSerial.hashCode();
		hash = hash * prime + this.facturaId.hashCode();
		hash = hash * prime + this.id.hashCode();
		
		return hash;
	}
}