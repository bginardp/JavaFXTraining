package es.palmademallorca.bg.factuapp.view;

import java.math.BigDecimal;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import es.palmademallorca.bg.common.controller.GenericFXController;
import es.palmademallorca.bg.factuapp.model.dao.FacturasService;
import es.palmademallorca.bg.factuapp.model.dao.IFacturasDAO;
import es.palmademallorca.bg.factuapp.model.jpa.Cliente;
import es.palmademallorca.bg.factuapp.model.jpa.Factura;
import es.palmademallorca.bg.factuapp.model.jpa.Factureslin;
import es.palmademallorca.bg.factuapp.model.jpa.Formaspago;
import es.palmademallorca.bg.factuapp.model.jpa.Producto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.util.StringConverter;

public class FacturaController extends GenericFXController {
	// private MainApp2 mainApp;
	// private static EntityManager em =
	// EntityManagerProvider.getProvider().getEntityManager();
	private IFacturasDAO model;
	private Factura factura;
	private String operacio;
	private Long idFactura;

	private ObservableList<Cliente> clientesList = FXCollections.observableArrayList();
	private ObservableList<Formaspago> forpagList = FXCollections.observableArrayList();
	private ObservableList<Producto> productosList = FXCollections.observableArrayList();
	private ObservableList<Factureslin> detalleList = FXCollections.observableArrayList();

	@FXML
	private TableView<Factureslin> tableView;
	@FXML
	private Button btAdd;
	@FXML
	private Button btModificar;
	@FXML
	private Button btEliminar;
	@FXML
	private Button btNuevaFactura;
	@FXML
	private Button btGeneraFactura;
	@FXML
	private Button btImprimeFactura;
	@FXML
	private Button btNuevaLinea;
	@FXML
	private TextField tfIdentificador;
	@FXML
	private TextField tfNumeroFactura;
	@FXML
	private TextField tfSerie;
	@FXML
	private DatePicker fechaFactura;
	@FXML
	private ComboBox<Cliente> clientesCombo;
	@FXML
	private ComboBox<Formaspago> formasPagoCombo;
	@FXML
	private ComboBox<Producto> productosCombo;
	@FXML
	private TextField tfDescripcion;
	@FXML
	private TextField tfCantidad;
	@FXML
	private TextField tfPrecio;
	@FXML
	private TextField tfImporte;
	@FXML
	private TextField tfImpBru;
	@FXML
	private TextField tfBaseIrpf;
	@FXML
	private TextField tfPorIrpf;
	@FXML
	private TextField tfImpIrpf;
	@FXML
	private TextField tfBaseIva1;
	@FXML
	private TextField tfPorIva1;
	@FXML
	private TextField tfImpIva1;
	@FXML
	private TextField tfTotFac;
	@FXML
	private TableColumn<Factureslin, String> producteIdCol;
	@FXML
	private TableColumn<Factureslin, String> demCol;
	@FXML
	private TableColumn<Factureslin, BigDecimal> qttCol;
	@FXML
	private TableColumn<Factureslin, BigDecimal> precioCol;
	@FXML
	private TableColumn<Factureslin, BigDecimal> importeCol;
	@FXML
	private Label lCodCli;

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		this.setUpCols();
		this.cargarDatosTabla();
		btEliminar.setDisable(true);
		btModificar.setDisable(true);
		btEliminar.setStyle("-fx-background-color:grey");
		btModificar.setStyle("-fx-background-color:grey");

	}

	/*
	 * public void setMainApp(MainApp2 mainApp) { this.mainApp = mainApp; }
	 */
	public void cargarDatosTabla() {
		// obtenir factura
		model = new FacturasService(getEm());
		factura = model.getFacturaPorId(new Long(1));

		// omplir camps
		this.tfIdentificador.setText(String.valueOf(factura.getId()));
		this.tfSerie.setText(factura.getSerieId());
		// omplir detall
		detalleList.addAll(factura.getFactureslins());
		tableView.setItems(detalleList);
		clientesList.addAll(model.getAllClientes());
		setupClientesCombobox();
		// Listen for selection changes and show the client details when
		// changed.
		// tableView.getSelectionModel().selectedItemProperty()
		// .addListener((observable, oldValue, newValue) ->
		// showDetailsProducto(newValue));
	}

	private void setUpCols() {
		/*
		 * afegir una validació de numeric o de lletres als camps que pertoquin
		 *******/
		tfTotFac.addEventFilter(KeyEvent.KEY_TYPED, numeric_Validation(10));
		tfDescripcion.addEventFilter(KeyEvent.KEY_TYPED, letter_Validation(10));

		demCol.setCellValueFactory(cellData -> cellData.getValue().demProperty());
		producteIdCol.setCellValueFactory(cellData -> cellData.getValue().producteIdProperty());
		qttCol.setCellValueFactory(cellData -> cellData.getValue().cantidadProperty());
		precioCol.setCellValueFactory(cellData -> cellData.getValue().preuProperty());
		importeCol.setCellValueFactory(cellData -> cellData.getValue().importeProperty());
	}

	private void setupClientesCombobox() {
		// Init ComboBox items.
		clientesCombo.setItems(clientesList);
		// Define rendering of the list of values in ComboBox drop down.
		clientesCombo.setCellFactory((comboBox) -> {
			return new ListCell<Cliente>() {
				@Override
				protected void updateItem(Cliente item, boolean empty) {
					super.updateItem(item, empty);

					if (item == null || empty) {
						setText(null);
					} else {
						setText(item.getNom());
					}
				}
			};
		});
		// Define rendering of selected value shown in ComboBox.
		clientesCombo.setConverter(new StringConverter<Cliente>() {
			@Override
			public String toString(Cliente item) {
				return (item.getNom());
			}

			@Override
			public Cliente fromString(String string) {
				throw new UnsupportedOperationException("Not supported yet.");
			}
		});
		// Handle ComboBox event.
		clientesCombo.setOnAction(e -> System.out.println("Action Nueva Selección: " + clientesCombo.getValue()));
		// Handle ComboBox event.

		clientesCombo.setOnAction((event) -> {
			Cliente selected = clientesCombo.getSelectionModel().getSelectedItem();
			lCodCli.setText(selected.toString());
			System.out.println("ComboBox Action (selected: " + selected.toString() + ")\n");
		});
		clientesCombo.valueProperty().addListener((ov, p1, p2) -> {
			System.out.println("Nueva Selección: " + p2);
			System.out.println("Vieja Selección: " + p1);
		});

	}

	private void showDetailsProducto(Producto row) {
		btAdd.setDisable(true);
		btEliminar.setDisable(false);
		btModificar.setDisable(false);
		btAdd.setStyle("-fx-background-color:grey");
		btEliminar.setStyle("-fx-background-color:#66CCCC");
		btModificar.setStyle("-fx-background-color:#66CCCC");
		if (row != null) {
			tfIdentificador.setText(row.getId());

		} else {
			// Person is null, remove all the text.
			tfIdentificador.setText("");

		}
		tfIdentificador.setEditable(false);
		tfIdentificador.setStyle("-fx-text-inner-color: red;");

	}

	@FXML
	private void getProductoSeleccionado(MouseEvent event) {
		tableView.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				if (tableView != null) {

					btAdd.setDisable(true);
					btEliminar.setDisable(false);
					btModificar.setDisable(false);
					btAdd.setStyle("-fx-background-color:grey");
					btEliminar.setStyle("-fx-background-color:#66CCCC");
					btModificar.setStyle("-fx-background-color:#66CCCC");

					String valor = tableView.getSelectionModel().getSelectedItems().get(0).toString();

				}
			}
		});
	}

	@FXML
	private void addLinea(ActionEvent event) {
		/*
		 * Producto producto = new Producto();
		 * producto.setId(tfIdentificador.getText());
		 * producto.setDem(tfNombreProducto.getText());
		 * model.insertar(producto); tableView.getItems().clear();
		 * cargarDatosTabla();
		 */
	}

	@FXML
	private void modificarLinea(ActionEvent event) {

		/*
		 * Producto producto = tableView.getSelectionModel().getSelectedItem();
		 * producto.setDem(tfNombreProducto.getText());
		 * model.modificar(producto);
		 */
	}

	@FXML
	private void eliminarLinea(ActionEvent event) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Confirmación");
		alert.setHeaderText("Eliminar Lineao");
		alert.setContentText("Realmente desea eliminar este registro?");
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK) {
			;
		}
	}

	@FXML
	private void nuevoLinea(ActionEvent event) {
		/*
		 * btAddProducto.setDisable(false); btEliminarProducto.setDisable(true);
		 * btModificarProducto.setDisable(true);
		 * btAddProducto.setStyle("-fx-background-color:#66CCCC");
		 * btEliminarProducto.setStyle("-fx-background-color:grey");
		 * btModificarProducto.setStyle("-fx-background-color:grey");
		 * tfIdentificador.setEditable(true); tfIdentificador.setText("");
		 * tfNombreProducto.setText(""); tfIdentificador.requestFocus();
		 * tfIdentificador.setStyle("");
		 */
	}

	@Override
	public void postInitialize() {
		// TODO Auto-generated method stub

	}

	/*
	 * Numeric Validation Limit the characters to maxLengh AND to ONLY DigitS
	 *************************************/
	public EventHandler<KeyEvent> numeric_Validation(final Integer max_Lengh) {
		return new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent e) {
				TextField txt_TextField = (TextField) e.getSource();
				if (txt_TextField.getText().length() >= max_Lengh) {
					e.consume();
				}
				if (e.getCharacter().matches("[0-9.]")) {
					if (txt_TextField.getText().contains(".") && e.getCharacter().matches("[.]")) {
						e.consume();
					} else if (txt_TextField.getText().length() == 0 && e.getCharacter().matches("[.]")) {
						e.consume();
					}
				} else {
					e.consume();
				}
			}
		};
	}

	/*****************************************************************************************/

	/*
	 * Letters Validation Limit the characters to maxLengh AND to ONLY Letters
	 *************************************/
	public EventHandler<KeyEvent> letter_Validation(final Integer max_Lengh) {
		return new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent e) {
				TextField txt_TextField = (TextField) e.getSource();
				if (txt_TextField.getText().length() >= max_Lengh) {
					e.consume();
				}
				if (e.getCharacter().matches("[A-Za-z]")) {
				} else {
					e.consume();
				}
			}
		};
	}

	public String getOperacio() {
		return operacio;
	}

	public void setOperacio(String operacio) {
		this.operacio = operacio;
	}

	public Long getIdFactura() {
		return idFactura;
	}

	public void setIdFactura(Long idFactura) {
		this.idFactura = idFactura;
	}

	

}
