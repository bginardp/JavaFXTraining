package es.palmademallorca.bg.factuapp.model.dao;

import java.util.List;

import es.palmademallorca.bg.factuapp.model.jpa.Ejercicio;

public interface IEjercicioDAO {

	public static final int EXITO = 0;

	public static final int FALLO = -1;

	public Ejercicio getEjercicioById(Integer id);

	public List<Ejercicio> getEjercicios();

	public int insertar(Ejercicio tipiva);

	public int modificar(Ejercicio tipiva);

	public int eliminar(Ejercicio tipiva);

	public int eliminar(List<Ejercicio> lista);
}
