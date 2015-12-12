package es.palmademallorca.factuapp.model.dao;

import java.util.List;

import es.palmademallorca.factuapp.model.jpa.SerieJPA;

public interface ISeriesModel {

    public static final int EXITO = 0;

    public static final int FALLO = -1;

    public SerieJPA getSeriePorId(Long id);

    public List<SerieJPA> getSeries();

    public int insertar(SerieJPA serie);

    public int modificar(SerieJPA serie);

    public int eliminar(SerieJPA serie);

    public int eliminar(List<SerieJPA> lista);
}
