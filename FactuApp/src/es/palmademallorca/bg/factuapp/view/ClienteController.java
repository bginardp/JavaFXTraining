package es.palmademallorca.bg.factuapp.view;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import javax.persistence.EntityManager;

import es.palmademallorca.bg.factuapp.MainApp;
import es.palmademallorca.bg.factuapp.model.dao.ClientesService;
import es.palmademallorca.bg.factuapp.model.dao.IClientesDAO;
import es.palmademallorca.bg.factuapp.model.jpa.Cliente;
import es.palmademallorca.bg.factuapp.model.managers.EntityManagerProvider;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class ClienteController implements Initializable {
	private MainApp mainApp;
	//ScreensController controlador;
	//private ControlesBasicos controlesBasicos = new ControlesBasicos();
	// @PersistenceContext(unitName = "factuPU")
	private static EntityManager em = EntityManagerProvider.getProvider().getEntityManager();

	private IClientesDAO model;
	private ObservableList<Cliente> clientesList = FXCollections.observableArrayList();

	@FXML
	private TableView<Cliente> tableView;
	@FXML
	private TableColumn<Cliente, Long> identificadorCol;
	@FXML
	private TableColumn<Cliente, String> nombreCol;
	@FXML
	private TableColumn<Cliente, String> cifCol;
	@FXML
	private TextField tfIdentificador;
	@FXML
	private TextField tfCif;
	@FXML
	private TextField tfNombre;
	@FXML
	private TextField tfDireccion;
	@FXML
	private TextField tfMunicipio;
	@FXML
	private TextField tfProvincia;
	@FXML
	private TextField tfPostal;
	@FXML
	private TextField tfTel;
	@FXML
	private TextField tfMovil;
	@FXML
	private TextField tfEmail;

	@FXML
	private Button btEliminar;
	@FXML
	private Button btInsertar;
	@FXML
	private Button btModificar;
	@FXML
	private Button btNuevo;
	@FXML
	private TextField tfBuscar;

	public ClienteController() {
	}

	@SuppressWarnings("unchecked")
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// nombreCol.setCellValueFactory(new PropertyValueFactory<ClienteJPA,
		// String>("nom"));
		// lastName.setCellValueFactory(new PropertyValueFactory<Person,
		// String>("lastName"));
		setUpCols();
		cargarDatosTabla();
		btEliminar.setDisable(true);
		btModificar.setDisable(true);
		btEliminar.setStyle("-fx-background-color:grey");
		btModificar.setStyle("-fx-background-color:grey");


	}

	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}
	private void cargarDatosTabla() {
		model = new ClientesService(em);
		clientesList = FXCollections.observableArrayList(model.getClientes());
		tableView.setItems(clientesList);
		// Listen for selection changes and show the client details when
		// changed.
		tableView.getSelectionModel().selectedItemProperty()
				.addListener((observable, oldValue, newValue) -> showDetailsClient(newValue));

	}

	private void setUpCols() {
		nombreCol.setCellValueFactory(cellData -> cellData.getValue().nomProperty());
		identificadorCol.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());
	}

	private void showDetailsClient(Cliente row) {

		if (row != null) {
			btInsertar.setDisable(true);
			btEliminar.setDisable(false);
			btModificar.setDisable(false);
			btInsertar.setStyle("-fx-background-color:grey");
			btEliminar.setStyle("-fx-background-color:#66CCCC");
			btModificar.setStyle("-fx-background-color:#66CCCC");

			tfIdentificador.setText(row.getId().toString());
			tfNombre.setText(row.getNom());
			tfCif.setText(row.getCif());
			tfDireccion.setText(row.getDireccion());
			tfMunicipio.setText(row.getMunicipio());
			tfProvincia.setText(row.getProvincia());
			tfPostal.setText(row.getPostal());
			tfTel.setText(row.getTel());
			tfMovil.setText(row.getMovil());
			tfEmail.setText(row.getEmail());

			tfNombre.requestFocus();
		} else {
			// Person is null, remove all the text.
			btInsertar.setDisable(false);
			btEliminar.setDisable(true);
			btModificar.setDisable(true);
			btInsertar.setStyle("-fx-background-color:#66CCCC");
			btEliminar.setStyle("-fx-background-color:grey");
			btModificar.setStyle("-fx-background-color:grey");
			tfIdentificador.setText("");
			tfNombre.setText("");
			tfCif.setText("");
			tfDireccion.setText("");
			tfMunicipio.setText("");
			tfProvincia.setText("");
			tfPostal.setText("");
			tfTel.setText("");
			tfMovil.setText("");
			tfEmail.setText("");

		}
		tfIdentificador.setEditable(false);
		tfIdentificador.setStyle("-fx-text-inner-color: red;");
	}

	/**
	 * Called when the user clicks on the delete button.
	 */
	@FXML
	private void handleDeleteClient() {

		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Confirmation Dialog");
		alert.setHeaderText("");
		alert.setContentText("Confirma borrado del registro?");

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK){
			int selectedIndex = tableView.getSelectionModel().getSelectedIndex();
			// esborra els items de la fila seleccionada
			Cliente cliSeleccionado = tableView.getSelectionModel().getSelectedItem();
			// borramos fila de la table view
			tableView.getItems().remove(selectedIndex);
			// ClienteJPA cliente = em.find(ClienteJPA.class, seleccionado.getId());
			model.eliminar(cliSeleccionado);
		} else {
		    // ... user chose CANCEL or closed the dialog
		}


	}



	@FXML
	private void nou(ActionEvent event) {
		showDetailsClient(null);

	}

	@FXML
	private void add(ActionEvent event) {
		Cliente cliente = new Cliente();
		cliente.setCif(tfCif.getText());
		cliente.setDireccion(tfDireccion.getText());
		cliente.setEmail(tfEmail.getText());
		cliente.setMovil(tfMovil.getText());
		cliente.setMunicipio(tfMunicipio.getText());
		cliente.setNom(tfNombre.getText());
		cliente.setPostal(tfPostal.getText());
		cliente.setProvincia(tfProvincia.getText());
		cliente.setTel(tfTel.getText());
		model.insertar(cliente);
		tableView.getItems().clear();
		cargarDatosTabla();
	}

	@FXML
	private void modificar(ActionEvent event) {

		Cliente cliente = tableView.getSelectionModel().getSelectedItem();
		cliente.setCif(tfCif.getText());
		cliente.setDireccion(tfDireccion.getText());
		cliente.setEmail(tfEmail.getText());
		cliente.setMovil(tfMovil.getText());
		cliente.setMunicipio(tfMunicipio.getText());
		cliente.setNom(tfNombre.getText());
		cliente.setPostal(tfPostal.getText());
		cliente.setProvincia(tfProvincia.getText());
		cliente.setTel(tfTel.getText());
		model.modificar(cliente);

	}

	@FXML
	private void buscarCliente(ActionEvent event) {

		tableView.getItems().clear();
		clientesList = FXCollections.observableArrayList(model.getClientesPorNombre(tfBuscar.getText()));
 		tableView.setItems(clientesList);


	}

	public ObservableList<Cliente> getClientesList() {
		return clientesList;
	}

	public void setClientesList(ObservableList<Cliente> clientesList) {
		this.clientesList = clientesList;
	}

	public TableView<Cliente> getTableView() {
		return tableView;
	}

	public void setTableView(TableView<Cliente> tableView) {
		this.tableView = tableView;
	}

	public TableColumn<Cliente, Long> getIdentificadorCol() {
		return identificadorCol;
	}

	public void setIdentificadorCol(TableColumn<Cliente, Long> identificadorCol) {
		this.identificadorCol = identificadorCol;
	}

	public TableColumn<Cliente, String> getNombreCol() {
		return nombreCol;
	}

	public void setNombreCol(TableColumn<Cliente, String> nombreCol) {
		this.nombreCol = nombreCol;
	}



}
