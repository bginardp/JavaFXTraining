package es.palmademallorca.bg.factuapp.model.dao;

import java.util.List;

import es.palmademallorca.bg.factuapp.model.jpa.Tipiva;

public interface ITipivaDAO {

	public static final int EXITO = 0;

	public static final int FALLO = -1;

	public Tipiva getTipivaPorId(Long id);

	public List<Tipiva> getTipiva();

	public int insertar(Tipiva tipiva);

	public int modificar(Tipiva tipiva);

	public int eliminar(Tipiva tipiva);

	public int eliminar(List<Tipiva> lista);
}
