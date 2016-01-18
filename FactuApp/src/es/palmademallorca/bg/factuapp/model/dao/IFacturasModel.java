package es.palmademallorca.bg.factuapp.model.dao;

import java.math.BigDecimal;
import java.util.List;

import es.palmademallorca.bg.factuapp.model.jpa.Factura;

public interface IFacturasModel {

    public static final int EXITO = 0;

    public static final int FALLO = -1;

    public Factura getFacturaPorId(Long id);

    public Factura getFacturaPorEmpresaEjeSerieNumero(Long empresa, Long ejercicio, String serie, BigDecimal num);

    public BigDecimal getNewNumeroFactura(Long empresa, Long ejercicio, String serie);

    public List<Factura> getAllFacturas(Long empresa, Long ejercicio);

    public List<Factura> getAllFacturas(Long empresa);

    public int insertar(Factura factura);

    public int modificar(Factura factura);

    public int eliminar(Factura factura);

}
