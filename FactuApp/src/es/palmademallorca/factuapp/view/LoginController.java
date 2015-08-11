/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.palmademallorca.factuapp.view;

import java.net.URL;
import java.util.ResourceBundle;

import es.palmademallorca.factuapp.MainApp;
import es.palmademallorca.factuapp.Validaciones;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Escritorio
 */
public class LoginController implements Initializable, ControlledScreen {
    ScreensController controlador;
    private Validaciones validation = new Validaciones();
    public TextField tfUsuario;
    public PasswordField tfPass;
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
    private void iniciarSesion(ActionEvent event){
        /**********************************
         *         Area de validaciones
         ***********************************/
        if (!validation.validarVacios(tfUsuario.getText(), "USUARIO")) {
            return;
        }


        if (!validation.validarMaximo(tfUsuario.getText(), "USUARIO", 20, 2)) {
            return;
        }
        controlador.setScreen(MainApp.contenidoID);
        /**********************************
         *     Fin de las validaciones
         ***********************************/

        //______________________________________________________
        /* SE HACE EL LLAMADO AL MODELO PARA ENTRAR AL SISTEMA */
       /* try {
            conexion = DBConnection.connect();
            String sql = "SELECT * FROM "
                    + " usuarios WHERE "
                    + " usuario = '"+tfUsuario.getText()+"' AND "
                    + " pass = '"+DigestUtils.sha1Hex(tfPass.getText())+"'";
            ResultSet rs = conexion.createStatement().executeQuery(sql);

            boolean existeUsuario = rs.next();

            if (existeUsuario) {
                tfUsuario.setText("");
                tfPass.setText("");
                controlador.setScreen(ScreensFramework.contenidoID);
            } else {
                JOptionPane.showMessageDialog(null, "Este usuario no está registrado");
            }

        } catch (SQLException e) {
            System.out.println("Error " + e.getMessage());
        } */
    }

    @FXML
    private void irFormRegistro(ActionEvent event) {
        controlador.setScreen(MainApp.registroID);
    }

    @FXML
    private void salir(ActionEvent event) {
        Platform.exit();
    }
}
