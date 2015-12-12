package es.palmademallorca.factuapp.model.jpa;

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
@NamedQuery(name="Producto.findAll", query="SELECT p FROM ProductoJPA p"),
@NamedQuery(name="Producto.findByDem", query="SELECT p FROM ProductoJPA p WHERE p.dem like :dem")
})
public class ProductoJPA implements Serializable {
	private static final long serialVersionUID = 1L;
	private StringProperty id= new SimpleStringProperty();
   	private StringProperty dem = new SimpleStringProperty();
	private StringProperty hbl = new SimpleStringProperty();

	private List<Factureslin> factureslins;

	public ProductoJPA() {
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

	//bi-directional many-to-one association to Factureslin
	@OneToMany(mappedBy="producto")
	public List<Factureslin> getFactureslins() {
		return this.factureslins;
	}

	public void setFactureslins(List<Factureslin> factureslins) {
		this.factureslins = factureslins;
	}

	public Factureslin addFactureslin(Factureslin factureslin) {
		getFactureslins().add(factureslin);
		factureslin.setProducto(this);

		return factureslin;
	}

	public Factureslin removeFactureslin(Factureslin factureslin) {
		getFactureslins().remove(factureslin);
		factureslin.setProducto(null);

		return factureslin;
	}
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(this.getId()).append(" ").append(this.getDem());
		return sb.toString();
	}

}