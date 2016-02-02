package es.palmademallorca.bg.factuapp.view;

import java.io.IOException;
import java.util.Optional;

import es.palmademallorca.bg.factuapp.MainApp2;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author Bernat
 */
public class AdministracionController {

	// Reference to the main application
	private MainApp2 mainApp;

	/**
	 * Is called by the main application to give a reference back to itself.
	 *
	 * @param mainApp
	 */

	public AdministracionController() {

	}

	public void setMainApp(MainApp2 mainApp) {
		this.mainApp = mainApp;

	}


	@FXML
	private void handleTipIva() {

		System.out.println("tipo iva");


	}
	/**
	 * Saves the file to the person file that is currently open. If there is no
	 * open file, the "save as" dialog is shown.
	 */
	@FXML
	private void handleEmpresa() {
		System.out.println("empresa");
		try {
			// Load the fxml file and set into the center of the main layout
			FXMLLoader loader = new FXMLLoader(MainApp2.class.getResource("view/Empresa.fxml"));
			AnchorPane overviewPage = (AnchorPane) loader.load();
			mainApp.getRootLayout().setCenter(overviewPage);

			// Give the controller access to the main app
			// EmpresaController controller = loader.getController();
			// controller.setMainApp(this.mainApp);
		} catch (IOException e) {
			// Exception gets thrown if the fxml file could not be loaded
			e.printStackTrace();
		}
	}

}