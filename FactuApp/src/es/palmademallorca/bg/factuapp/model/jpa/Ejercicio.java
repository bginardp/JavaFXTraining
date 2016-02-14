package es.palmademallorca.bg.factuapp.model.jpa;

import java.io.Serializable;
import javax.persistence.*;

import javafx.beans.property.SimpleIntegerProperty;

/**
 * The persistent class for the ejercicio database table.
 *
 */
@Entity
@NamedQueries({ @NamedQuery(name = "Ejercicio.findAll", query = "SELECT e FROM Ejercicio e") })
public class Ejercicio implements Serializable {



	private static final long serialVersionUID = 1L;

	private SimpleIntegerProperty id = new SimpleIntegerProperty();

	public Ejercicio() {
	}

	public final SimpleIntegerProperty idProperty() {
		return this.id;
	}

    @Id
	public int getId() {
		return this.idProperty().get();
	}


	public void setId(final int id) {
		this.idProperty().set(id);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(getId());
		return builder.toString();
	}



}