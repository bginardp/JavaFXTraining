package es.palmademallorca.factuapp.model.dao;

import java.util.List;

import es.palmademallorca.factuapp.model.jpa.ClienteJPA;
import es.palmademallorca.factuapp.model.jpa.ProductoJPA;

public interface IClientesModel {

    public static final int EXITO = 0;

    public static final int FALLO = -1;

    public ClienteJPA getClientePorId(Long id);
    public List<ClienteJPA> getClientesPorNombre(String valor);
    public List<ClienteJPA> getClientes();

    public int insertar(ClienteJPA cliente);

    public int modificar(ClienteJPA cliente);

    public int eliminar(ClienteJPA cliente);

    public int eliminar(List<ClienteJPA> cliente);
}
