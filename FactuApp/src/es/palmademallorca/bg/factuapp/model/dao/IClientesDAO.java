package es.palmademallorca.bg.factuapp.model.dao;

import java.util.List;

import es.palmademallorca.bg.factuapp.model.jpa.Cliente;
import es.palmademallorca.bg.factuapp.model.jpa.Producto;

public interface IClientesDAO {

    public static final int EXITO = 0;

    public static final int FALLO = -1;

    public Cliente getClientePorId(Long id);
    public List<Cliente> getClientesPorNombre(String valor);
    public List<Cliente> getClientes();

    public int insertar(Cliente cliente);

    public int modificar(Cliente cliente);

    public int eliminar(Cliente cliente);

    public int eliminar(List<Cliente> cliente);
}
