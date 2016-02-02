package es.palmademallorca.bg.factuapp.view;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import es.palmademallorca.bg.common.controller.GenericFXController;
import es.palmademallorca.bg.factuapp.MainApp2;
import es.palmademallorca.bg.factuapp.model.dao.EmpresaService;
import es.palmademallorca.bg.factuapp.model.dao.IEmpresaDAO;
import es.palmademallorca.bg.factuapp.model.dao.IProductosModel;
import es.palmademallorca.bg.factuapp.model.dao.ProductosModel;
import es.palmademallorca.bg.factuapp.model.errors.Messages;
import es.palmademallorca.bg.factuapp.model.jpa.Empresa;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.StringConverter;

/**
 * The controller for the root layout. The root layout provides the basic
 * application layout containing a menu bar and space where other JavaFX
 * elements can be placed.
 *
 * @author Marco Jakob
 */
public class RootLayoutController extends GenericFXController {

	ObservableList<Object> empresasList = FXCollections.observableArrayList();
	// Reference to the main application
	private IEmpresaDAO model;

	@FXML
	private Label aplicacioL;
	@FXML
	private ImageView imagen;
	@FXML
	private ComboBox<Empresa> empresasCombo;
	@FXML
	private Label empresaLabel;
	@FXML
	private Button cambiarButton;
	@FXML
	private Button facturasButton;

	/**
	 * Is called by the main application to give a reference back to itself.
	 *
	 * @param mainApp
	 */

	public RootLayoutController() {

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		super.initialize(location, resources);
		aplicacioL.setText("Facturación V1.0");
		carregarDades();
	}

	public void postInitialize () {

	   facturasButton.disabledProperty().booleanExpression(this.getMainApp().isEmpresaSelected());
System.out.println(facturasButton.disabledProperty().get());
}
	@FXML
	private void handleClientes() {

		System.out.println("clientes");
		showClientes();

	}

	public void showClientes() {
		try {
			// Load the fxml file and set into the center of the main layout
			FXMLLoader loader = new FXMLLoader(MainApp2.class.getResource("view/Cliente.fxml"));
			AnchorPane node = (AnchorPane) loader.load();

			// this.contentApp.getChildren().add((AnchorPane) loader.load());
			getMainApp().getRootLayout().setCenter(node);

			// Give the controller access to the main app
			ClienteController controller = loader.getController();
			controller.setMainApp(getMainApp());
		} catch (IOException e) {
			// Exception gets thrown if the fxml file could not be loaded
			e.printStackTrace();
		}
	}

	/**
	 * Saves the file to the person file that is currently open. If there is no
	 * open file, the "save as" dialog is shown.
	 */
	@FXML
	private void handleProductos() {
		System.out.println("productos");
		try {
			// Load the fxml file and set into the center of the main layout
			FXMLLoader loader = new FXMLLoader(MainApp2.class.getResource("view/Producto.fxml"));

			AnchorPane node = (AnchorPane) loader.load();
			// this.contentApp.getChildren().add(node);
			getMainApp().getRootLayout().setCenter(node);

			// Give the controller access to the main app
			ProductoController controller = loader.getController();
			controller.setMainApp(getMainApp());
		} catch (IOException e) {
			// Exception gets thrown if the fxml file could not be loaded
			e.printStackTrace();
		}
	}

	@FXML
	private void handleFacturas() {

		System.out.println("Facturas");
		try {
			// Load the fxml file and set into the center of the main layout
			FXMLLoader loader = new FXMLLoader(MainApp2.class.getResource("view/Facturas.fxml"));
			AnchorPane panel = (AnchorPane) loader.load();
			FacturasController controller = loader.getController();
			controller.setMainApp(getMainApp());
			getMainApp().getRootLayout().setCenter(controller.buildPanel(panel));

		} catch (IOException e) {
			// Exception gets thrown if the fxml file could not be loaded
			e.printStackTrace();
		}

	}

	@FXML
	private void handleAdministracion() {
		System.out.println("administracion");
		try {
			// Load the fxml file and set into the center of the main layout
			FXMLLoader loader = new FXMLLoader(MainApp2.class.getResource("view/Administracion.fxml"));
			AnchorPane panel = (AnchorPane) loader.load();
			getMainApp().getRootLayout().setCenter(panel);
			// Give the controller access to the main app
			AdministracionController controller = loader.getController();
			controller.setMainApp(getMainApp());
		} catch (IOException e) {
			// Exception gets thrown if the fxml file could not be loaded
			e.printStackTrace();
		}
	}

	/**
	 * Opens an about dialog.
	 */
	@FXML
	private void handleAbout() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Information");
		alert.setHeaderText("About");
		alert.setContentText("Author: Bernat\nWebsite:");
		alert.showAndWait();
	}

	/**
	 * Closes the application.
	 */
	@FXML
	private void handleExit() {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Confirmación");
		alert.setHeaderText("Salir del Sistema");
		alert.setContentText("Seguro que quiere salir del Sistema?");
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK) {
			System.exit(0);
		}
	}

	@FXML
	private void handleExitEmpresa() {
		empresaLabel.setText("");
		empresaLabel.setVisible(false);
		empresasCombo.setVisible(true);
		cambiarButton.setVisible(false);
		this.getMainApp().setEmpresa(null);
	}

	private void carregarDades() {
		model = new EmpresaService(getEm());
		List<Empresa> empreses = model.getEmpresas();
		// extreure una llista de String a partir d'una llista de empreses
		// utilitzant lambdas
		// List<String> nomsEmpreses =
		// empresas.stream().map(Empresa::getDem).collect(Collectors.toList());

		empresasList = FXCollections.observableArrayList();
		empresasCombo.getItems().addAll(empreses);
		empresasCombo.setConverter(new StringConverter<Empresa>() {

			@Override
			public String toString(Empresa e) {
				return e.getDem();
			}

			@Override
			public Empresa fromString(String string) {
				// TODO Auto-generated method stub
				throw new UnsupportedOperationException(Messages.NO_OPERATION);
			}
		});
		empresasCombo.setOnAction(event -> empresaSeleccionada( empresasCombo.getSelectionModel().getSelectedItem()));
		//empresaLabel.textProperty().bind(empresasCombo.getSelectionModel().selectedItemProperty());

		/* Listen for selection changes and show the client details when
		* changed.
		empresasCombo.getSelectionModel().selectedItemProperty()
				.addListener((observable, oldValue, newValue) -> empresaSeleccionada(newValue)); */
	}

	private void empresaSeleccionada(Empresa empresa) {
		empresaLabel.setText(empresa.getDem());
		empresaLabel.setVisible(true);
		empresasCombo.setVisible(false);
		cambiarButton.setVisible(true);
		this.getMainApp().setEmpresa(empresa);
	}
}