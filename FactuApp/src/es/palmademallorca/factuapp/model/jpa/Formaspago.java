package es.palmademallorca.factuapp.model.jpa;

import java.io.Serializable;
import javax.persistence.*;

import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


/**
 * The persistent class for the formaspago database table.
 *
 */
@Entity
@NamedQuery(name="Forpag.findAll", query="SELECT f FROM Formaspago f")
public class Formaspago implements Serializable {
	private static final long serialVersionUID = 1L;
	private LongProperty id=new SimpleLongProperty();
	private StringProperty dem=new SimpleStringProperty();
	private StringProperty hbl=new SimpleStringProperty();
	// private List<Factura> facturas;

	public Formaspago() {
	}


	@Id
	@GeneratedValue(generator="ForPagSeq")
    @SequenceGenerator(name="ForPagSeq",sequenceName="forpag_seq", allocationSize=1)
	public Long getId() {
		return this.id.get();
	}

	public void setId(Long id) {
		this.id.set(id);
	}


	public String getDem() {
		return this.dem.get();
	}

	public void setDem(String dem) {
		this.dem.set(dem);
	}


	public String getHbl() {
		return this.hbl.get();
	}

	public void setHbl(String hbl) {
		this.hbl.set(hbl);
	}
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(this.getId()).append(" ").append(this.getDem());
		return sb.toString();
	}


	/*
	//bi-directional many-to-one association to Factura
	@OneToMany(mappedBy="formaspago")
	public List<Factura> getFacturas() {
		return this.facturas;
	}

	public void setFacturas(List<Factura> facturas) {
		this.facturas = facturas;
	}

	public Factura addFactura(Factura factura) {
		getFacturas().add(factura);
		factura.setFormaspago(this);

		return factura;
	}

	public Factura removeFactura(Factura factura) {
		getFacturas().remove(factura);
		factura.setFormaspago(null);

		return factura;
	}
*/
}