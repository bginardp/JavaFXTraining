/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.palmademallorca.factuapp;

import javafx.application.Platform;
import javax.swing.JOptionPane;

/**
 *
 * @author Escritorio
 */
public class ControlesBasicos {

    public void salirSistema() {
        int pregunta = JOptionPane.showConfirmDialog(null, "Realmente desea salir del programa?");

        if (pregunta == 0) {
            Platform.exit();
        }
    }
}
