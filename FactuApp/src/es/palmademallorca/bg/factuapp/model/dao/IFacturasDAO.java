package es.palmademallorca.bg.factuapp.model.dao;

import java.math.BigDecimal;
import java.util.List;

import es.palmademallorca.bg.factuapp.model.jpa.Factura;

public interface IFacturasDAO {

    public static final int EXITO = 0;

    public static final int FALLO = -1;

    public Factura getFacturaPorId(Long id);

    public Factura getFacturaPorEmpresaEjeSerieNumero(Long empresa, Integer ejercicio, String serie, BigDecimal num);

    public BigDecimal getNewNumeroFactura(Long empresa, Integer ejercicio, String serie);

    public List<Factura> getAllFacturas(Long empresa, Integer ejercicio);

    public List<Factura> findFacturas(Long empresa, Integer ejercicio, String criteria);

    public int insertar(Factura factura);

    public int modificar(Factura factura);

    public int eliminar(Factura factura);

}
