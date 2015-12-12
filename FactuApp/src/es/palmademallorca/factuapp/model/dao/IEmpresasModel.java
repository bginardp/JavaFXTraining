package es.palmademallorca.factuapp.model.dao;

import java.util.List;

import es.palmademallorca.factuapp.model.jpa.EmpresaJPA;




public interface IEmpresasModel {

    public static final int EXITO = 0;

    public static final int FALLO = -1;

    public EmpresaJPA getEmpresaPorId(Long id);
    public List<EmpresaJPA> getEmpresasPorNombre(String valor);
    public List<EmpresaJPA> getEmpresas();

    public int insertar(EmpresaJPA empresa);

    public int modificar(EmpresaJPA empresa);

    public int eliminar(EmpresaJPA empresa);

    public int eliminar(List<EmpresaJPA> lista);
}
