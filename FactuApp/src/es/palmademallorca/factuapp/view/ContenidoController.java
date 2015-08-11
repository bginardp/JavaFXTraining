/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.palmademallorca.factuapp.view;

import java.net.URL;
import java.util.ResourceBundle;

import es.palmademallorca.factuapp.ControlesBasicos;
import es.palmademallorca.factuapp.MainApp;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

/**
 * FXML Controller class
 *
 * @author Escritorio
 */
public class ContenidoController implements Initializable, ControlledScreen {

    ScreensController controlador;

    private ControlesBasicos controlesBasicos = new ControlesBasicos();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @Override
    public void setScreenParent(ScreensController pantallaPadre) {
        controlador = pantallaPadre;
    }

    @FXML
    private void irMantenimientoProducto(ActionEvent  event) {

       controlador.setScreen(MainApp.mantenimientoProductoID);
    }

    @FXML
    private void irMantenimientoCliente(ActionEvent  event) {

       controlador.setScreen(MainApp.mantenimientoClientesID);
    }


    @FXML
    private void salir(ActionEvent event) {
        this.controlesBasicos.salirSistema();
    }

    @FXML
    private void cerrarSesion(ActionEvent event) {
        controlador.setScreen(MainApp.loginID);
    }
}
