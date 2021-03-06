package es.palmademallorca.bg.factuapp.model.dao;

import java.util.List;

import es.palmademallorca.bg.factuapp.model.jpa.Serie;

public interface ISeriesDAO {

    public static final int EXITO = 0;

    public static final int FALLO = -1;

    public Serie getSeriePorId(Long id);

    public List<Serie> getSeries();

    public int insertar(Serie serie);

    public int modificar(Serie serie);

    public int eliminar(Serie serie);

    public int eliminar(List<Serie> lista);
}
