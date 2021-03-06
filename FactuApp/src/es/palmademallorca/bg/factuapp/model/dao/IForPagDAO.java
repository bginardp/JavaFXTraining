package es.palmademallorca.bg.factuapp.model.dao;

import java.util.List;

import es.palmademallorca.bg.factuapp.model.jpa.Formaspago;

public interface IForPagDAO {

    public static final int EXITO = 0;

    public static final int FALLO = -1;

    public Formaspago getForpagPorId(Long id);

    public List<Formaspago> getForpag();

    public int insertar(Formaspago forpag);

    public int modificar(Formaspago forpag);

    public int eliminar(Formaspago forpag);

    public int eliminar(List<Formaspago> lista);
}
