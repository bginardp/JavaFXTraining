package es.palmademallorca.bg.factuapp.view;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import es.palmademallorca.bg.common.controller.GenericFXController;
import es.palmademallorca.bg.factuapp.FactuApp;
import es.palmademallorca.bg.factuapp.MainApp;
import es.palmademallorca.bg.factuapp.model.dao.EjercicioService;
import es.palmademallorca.bg.factuapp.model.dao.EmpresaService;
import es.palmademallorca.bg.factuapp.model.dao.IEjercicioDAO;
import es.palmademallorca.bg.factuapp.model.dao.IEmpresaDAO;
import es.palmademallorca.bg.factuapp.model.errors.Messages;
import es.palmademallorca.bg.factuapp.model.jpa.Ejercicio;
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
import javafx.scene.layout.VBox;
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
	ObservableList<Object> ejerciciosList = FXCollections.observableArrayList();
	// Reference to the main application
	private IEmpresaDAO mEmpresa;
	private IEjercicioDAO mEjercicio;

	@FXML
	private Label aplicacioL;
	@FXML
	private ImageView imagen;
	@FXML
	private ComboBox<Empresa> empresasCombo;
	@FXML
	private ComboBox<Ejercicio> ejerciciosCombo;
	@FXML
	private Label empresaLabel;
	@FXML
	private Label ejercicioLabel;
	@FXML
	private Button cambiarButton;
	@FXML
	private Button facturasButton;
	@FXML
	private Button altaFacturaBtn;

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
		ejercicioLabel.setText("");
		// empresaLabel.setText("");
		empresaLabel.textProperty().bind(Bindings.selectString(empresasCombo.getSelectionModel().selectedItemProperty(),"dem"));
		ejercicioLabel.textProperty().bind(Bindings.selectString(ejerciciosCombo.getSelectionModel().selectedItemProperty(),"id"));

		carregarDades();
	}

	/**
	 * s'executa un cop s'ha carregat el setMainApp al controller generic.
	 */
	public void postInitialize() {
	//	BooleanBinding boolBind = Bindings.isNotEmpty(empresaLabel.textProperty())
	//			.and(Bindings.isNotEmpty(empresaLabel.textProperty()));

		//facturasButton.disableProperty().bind(boolBind);
		facturasButton.disableProperty().bind(Bindings.or(empresaLabel.textProperty().isEqualTo(""),ejercicioLabel.textProperty().isEqualTo("")));
		altaFacturaBtn.disableProperty().bind(Bindings.or(empresaLabel.textProperty().isEqualTo(""),ejercicioLabel.textProperty().isEqualTo("")));
	}

	@FXML
	private void handleClientes() {

		System.out.println("clientes");
		showClientes();

	}

	public void showClientes() {
		try {
			// Load the fxml file and set into the center of the main layout
			FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("view/Cliente.fxml"));
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
			FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("view/Producto.fxml"));

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
	private void handleFactura() {

		try {
			// Load the fxml file and set into the center of the main layout
			FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("view/Factura.fxml"));

			VBox node = (VBox) loader.load();
			// this.contentApp.getChildren().add(node);
			getMainApp().getRootLayout().setCenter(node);

			// Give the controller access to the main app
			FacturaController controller = loader.getController();
			controller.setMainApp(getMainApp());

		} catch (IOException e) {
			// Exception gets thrown if the fxml file could not be loaded
			e.printStackTrace();
		}
	}


	@FXML
	private void handleFacturas() {

		// Load the fxml file and set into the center of the main layout
		FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("view/Facturas.fxml"));
		try {
			AnchorPane panel = (AnchorPane) loader.load();
			FacturasController controller = loader.getController();
			controller.setMainApp(getMainApp());
			getMainApp().getRootLayout().setCenter(controller.buildPanel());

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// getMainApp().getRootLayout().setCenter(panel);

	}

	@FXML
	private void handleAdministracion() {
		System.out.println("administracion");
		try {
			// Load the fxml file and set into the center of the main layout
			FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("view/Administracion.fxml"));
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
	private void handleCambiar() {
//		System.out.println("is empresa label not empty? " + empresaLabel.textProperty().isNotEmpty().get());
	    empresaLabel.textProperty().unbind();
		empresaLabel.setText(null);
		empresasCombo.setValue(null);
//		System.out.println("is empresa label not empty? " + empresaLabel.textProperty().isNotEmpty().get());
		empresaLabel.setVisible(false);
		empresaLabel.textProperty().bind(Bindings.selectString(empresasCombo.getSelectionModel().selectedItemProperty(),"dem"));
		empresasCombo.setVisible(true);
//		System.out.println("is ejercicio label not empty? " + ejercicioLabel.textProperty().isNotEmpty().get());
	    ejercicioLabel.textProperty().unbind();
		ejercicioLabel.setText("");
		ejerciciosCombo.setValue(null);
//		System.out.println("is ejercicio label not empty? " + ejercicioLabel.textProperty().isNotEmpty().get());
		ejercicioLabel.setVisible(false);
		ejercicioLabel.textProperty().bind(Bindings.selectString(ejerciciosCombo.getSelectionModel().selectedItemProperty(),"id"));

		ejerciciosCombo.setVisible(true);
		cambiarButton.setVisible(false);
		this.getMainApp().setEmpresa(null);
		this.getMainApp().setEjercicio(null);
	}

	private void carregarDades() {
		// empresas
		mEmpresa = new EmpresaService(getEm());
		List<Empresa> empreses = mEmpresa.getEmpresas();
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
		empresasCombo.setOnAction(event -> empresaSeleccionada(empresasCombo.getSelectionModel().getSelectedItem()));

		// ejercicios
		mEjercicio = new EjercicioService(getEm());
		List<Ejercicio> ejercicios = mEjercicio.getEjercicios();
		ejerciciosList = FXCollections.observableArrayList();
		ejerciciosCombo.getItems().addAll(ejercicios);
		ejerciciosCombo.setConverter(new StringConverter<Ejercicio>() {
			@Override
			public String toString(Ejercicio e) {
				return e.toString();
			}

			@Override
			public Ejercicio fromString(String string) {
				// TODO Auto-generated method stub
				throw new UnsupportedOperationException(Messages.NO_OPERATION);
			}
		});
		ejerciciosCombo
				.setOnAction(event -> ejercicioSeleccionado(ejerciciosCombo.getSelectionModel().getSelectedItem()));

	}

	private void empresaSeleccionada(Empresa empresa) {
		empresaLabel.setVisible(true);
//		empresaLabel.setText(empresa.getDem());
//		empresasCombo.setValue(null);
		empresasCombo.setVisible(false);

		this.getMainApp().setEmpresa(empresa);
		if (this.getMainApp().isEjercicioLoaded()) {
			cambiarButton.setVisible(true);
		}

	}

	private void ejercicioSeleccionado(Ejercicio ejercicio) {
		ejercicioLabel.setVisible(true);
	//	ejercicioLabel.setText(ejercicio.toString());
		ejerciciosCombo.setVisible(false);

		//ejerciciosCombo.setValue(null);
		this.getMainApp().setEjercicio(ejercicio);
		cambiarButton.setVisible(true);

	}


}