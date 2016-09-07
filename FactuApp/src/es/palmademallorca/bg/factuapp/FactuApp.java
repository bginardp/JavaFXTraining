package es.palmademallorca.bg.factuapp;

import java.math.BigDecimal;

public interface FactuApp {

	public static final String LS = System.getProperty("line.separator");
	public static final String PREFIX = "\t";

	public static final String NEW_FACTURA = "NEW";
	public static final String EDIT_FACTURA = "EDIT";
	public static final BigDecimal CIEN = new BigDecimal(100);
	public static final double CERO = 0;


}
