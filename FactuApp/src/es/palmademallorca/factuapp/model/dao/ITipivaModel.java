package es.palmademallorca.factuapp.model.dao;

import java.util.List;

import es.palmademallorca.factuapp.model.jpa.TipivaJPA;

public interface ITipivaModel {

    public static final int EXITO = 0;

    public static final int FALLO = -1;

    public TipivaJPA getTipivaPorId(Long id);

    public List<TipivaJPA> getTipiva();

    public int insertar(TipivaJPA tipiva);

    public int modificar(TipivaJPA tipiva);

    public int eliminar(TipivaJPA tipiva);

    public int eliminar(List<TipivaJPA> lista);
}
