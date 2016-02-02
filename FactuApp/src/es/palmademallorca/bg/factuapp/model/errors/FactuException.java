package es.palmademallorca.bg.factuapp.model.errors;

public class FactuException extends Exception {
	private static final long serialVersionUID = -2577150645305791318L;

	/**
	    * Constructor amb un paràmetre.
	    * @param msg  missatge que ha de mostrar l'excepció
	    */
	   public FactuException(String msg) { super(msg); }
	}