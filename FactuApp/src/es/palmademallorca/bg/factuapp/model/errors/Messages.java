package es.palmademallorca.bg.factuapp.model.errors;

/**
 * Interficie que defineix tots els missatges necessaris per a les excepcions generades
 *
 * @author   Equip docent de Disseny d'Estructura de Dades de la UOC
 * @version  Tardor 2015
 */
public interface Messages {

	public static final String LS = System.getProperty("line.separator");
	public static final String PREFIX = "\t";

	public static final String NO_OPERATION = "Operació no implementada";
	public static final String NO_EMPRESA = "Falta seleccionar una empresa";
	public static final String NO_EJERCICIO = "Falta seleccionar un ejercicio";
	public static final String CHANNEL_NOT_FOUND = "Channel not found";
	public static final String NO_PROGRAMS = "There are no programs";
	public static final String PROGRAM_NOT_FOUND = "Program not found";
	public static final String PROGRAM_FOUND_IN_CHANNEL = "Program found in channel";
	public static final String RATTING_ERROR = "Ratting must be between 0 and 5.";

}
