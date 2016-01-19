package es.palmademallorca.bg.factuapp.model.dao;

import java.util.List;

import es.palmademallorca.bg.factuapp.model.jpa.Empresa;




public interface IEmpresasModel {

    public static final int EXITO = 0;

    public static final int FALLO = -1;

    public Empresa getEmpresaPorId(Long id);
    public List<Empresa> getEmpresasPorNombre(String valor);
    public List<Empresa> getEmpresas();

    public int insertar(Empresa empresa);

    public int modificar(Empresa empresa);

    public int eliminar(Empresa empresa);

    public int eliminar(List<Empresa> lista);
}
