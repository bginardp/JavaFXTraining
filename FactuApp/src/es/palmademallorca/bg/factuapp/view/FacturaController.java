package es.palmademallorca.bg.factuapp.view;

import java.math.BigDecimal;
import java.net.URL;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;

import es.palmademallorca.bg.common.controller.GenericFXController;
import es.palmademallorca.bg.factuapp.FactuApp;
import es.palmademallorca.bg.factuapp.model.dao.FacturasService;
import es.palmademallorca.bg.factuapp.model.dao.IFacturasDAO;
import es.palmademallorca.bg.factuapp.model.jpa.Cliente;
import es.palmademallorca.bg.factuapp.model.jpa.Factura;
import es.palmademallorca.bg.factuapp.model.jpa.Factureslin;
import es.palmademallorca.bg.factuapp.model.jpa.Formaspago;
import es.palmademallorca.bg.factuapp.model.jpa.Producto;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.util.StringConverter;
import javafx.util.converter.BigDecimalStringConverter;
import javafx.util.converter.FormatStringConverter;
import javafx.util.converter.LongStringConverter;
import javafx.util.converter.NumberStringConverter;

public class FacturaController extends GenericFXController {
	// private MainApp2 mainApp;
	// private static EntityManager em =
	// EntityManagerProvider.getProvider().getEntityManager();
	final NumberFormat nfFmtInstance = NumberFormat.getInstance(Locale.getDefault());
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
	private VBox detalls;
	@FXML
	private Button btNuevaLinea;
	@FXML
	private Button btGuardaLinea;
	@FXML
	private Button btEliminarLinea;
	@FXML
	private Button btNuevaFactura;
	@FXML
	private Button btGeneraFactura;
	@FXML
	private Button btImprimeFactura;

	@FXML
	private TextField tfIdentificador;
	@FXML
	private TextField tfNumeroFactura;
	@FXML
	private TextField tfSerie;
	@FXML
	private TextField tfImpBru;
	@FXML
	private TextField tfBaseIrpf;
	@FXML
	private TextField tfPorIrpf;
	@FXML
	private TextField tfImpIrpf;
	@FXML
	private TextField tfPorDto;
	@FXML
	private TextField tfImpDto;
	@FXML
	private TextField tfBaseIva1;
	@FXML
	private TextField tfPorIva1;
	@FXML
	private TextField tfImpIva1;
	@FXML
	private TextField tfTotFac;
	@FXML
	private DatePicker dtFechaFactura;
	@FXML
	private ComboBox<Cliente> clientesCombo;
	@FXML
	private ComboBox<Formaspago> formasPagoCombo;
	@FXML
	private ComboBox<Producto> productosCombo;
	@FXML
	private TextArea tfDescripcion;
	@FXML
	private TextField tfCantidad;
	@FXML
	private TextField tfPrecio;
	@FXML
	private TextField tfImporte;
	
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
	@FXML
	private Label lblEjercicio;

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		System.out.println(this.getClass().getSimpleName() + ".initialize");
		configureButtons();

		setUpTable();
		configuraDetalls();

	}

	@Override
	public void postInitialize() {
		System.out.println("######## FacturasController: postInitialize");

		this.cargarDatosTabla();
		setUpBindings();

	}

	private void setUpTable() {
		
		nfFmtInstance.setMaximumFractionDigits(2);
		nfFmtInstance.setMinimumFractionDigits(1);
		TextFormatter<Double> qttFmt = new TextFormatter<Double>(new FormatStringConverter<Double>(nfFmtInstance), 0.0);
		TextFormatter<Double> preuFmt = new TextFormatter<Double>(new FormatStringConverter<Double>(nfFmtInstance),
				0.0);
		TextFormatter<Double> importFmt = new TextFormatter<Double>(new FormatStringConverter<Double>(nfFmtInstance),
				0.0);
		// configurar camps
		// tfCantidad.addEventFilter(KeyEvent.KEY_TYPED,
		// numeric_Validation(10));
		// tfPrecio.addEventFilter(KeyEvent.KEY_TYPED, numeric_Validation(10));
		tfDescripcion.addEventFilter(KeyEvent.KEY_TYPED, letter_Validation(10));
		tfDescripcion.setMaxWidth(300);

		tfCantidad.setTextFormatter(qttFmt);
		tfPrecio.setTextFormatter(preuFmt);
		tfImporte.setTextFormatter(importFmt);
		// tfImporte.addEventFilter(KeyEvent.KEY_TYPED, numeric_Validation(10));

		// configurar columnes
		demCol.setCellValueFactory(cellData -> cellData.getValue().demProperty());
		producteIdCol.setCellValueFactory(cellData -> cellData.getValue().producteIdProperty());
		qttCol.setCellValueFactory(cellData -> cellData.getValue().cantidadProperty());
		precioCol.setCellValueFactory(cellData -> cellData.getValue().preuProperty());
		importeCol.setCellValueFactory(cellData -> cellData.getValue().importeProperty());

		
		tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

	}

	private void setUpBindings() {
		
		lblEjercicio.textProperty().bindBidirectional(this.getMainApp().getEjercicio().idProperty(),
				new NumberStringConverter());
		
		tfSerie.textProperty().bindBidirectional(factura.serieIdProperty());
		tfNumeroFactura.textProperty().bindBidirectional(factura.numeroProperty(),new NumberStringConverter());
		tfIdentificador.textProperty().bindBidirectional(factura.idProperty(), new NumberStringConverter());
		dtFechaFactura.valueProperty().bindBidirectional(factura.datProperty());
		
		TextFormatter<BigDecimal> impbrufFmt = new TextFormatter<BigDecimal>(new FormatStringConverter<BigDecimal>(nfFmtInstance),factura.getImpbru());
		impbrufFmt.valueProperty().bindBidirectional(factura.impbruProperty());	
		tfImpBru.setTextFormatter(impbrufFmt);
		// tfImpBru.textProperty().bindBidirectional(factura.impbruProperty(), new BigDecimalStringConverter());
		
		TextFormatter<BigDecimal> baseIrpfFmt = new TextFormatter<BigDecimal>(new FormatStringConverter<BigDecimal>(nfFmtInstance),factura.getBaseirpf());
		baseIrpfFmt.valueProperty().bindBidirectional(factura.baseirpfProperty());	
		tfBaseIrpf.setTextFormatter(baseIrpfFmt);
		
		// tfBaseIrpf.textProperty().bindBidirectional(factura.baseirpfProperty(), new BigDecimalStringConverter());
		TextFormatter<BigDecimal> porirpfFmt = new TextFormatter<BigDecimal>(new FormatStringConverter<BigDecimal>(nfFmtInstance),factura.getPorirpf());
		porirpfFmt.valueProperty().bindBidirectional(factura.porirpfProperty());	
		tfPorIrpf.setTextFormatter(porirpfFmt);
		//tfPorIrpf.textProperty().bindBidirectional(factura.porirpfProperty(), new BigDecimalStringConverter());
		
		TextFormatter<BigDecimal> impirpfFmt = new TextFormatter<BigDecimal>(new FormatStringConverter<BigDecimal>(nfFmtInstance),factura.getImpirpf());
		impirpfFmt.valueProperty().bindBidirectional(factura.impirpfProperty());	
		tfImpIrpf.setTextFormatter(impirpfFmt);
		// tfImpIrpf.textProperty().bindBidirectional(factura.impIrpfProperty(), new BigDecimalStringConverter());
		
		TextFormatter<BigDecimal> baseIva1Fmt = new TextFormatter<BigDecimal>(new FormatStringConverter<BigDecimal>(nfFmtInstance),factura.getBaseiva1());
		baseIva1Fmt.valueProperty().bindBidirectional(factura.baseiva1Property());	
		tfBaseIva1.setTextFormatter(baseIva1Fmt);
		//tfBaseIva1.textProperty().bindBidirectional(factura.baseiva1Property(), new BigDecimalStringConverter());
		
		TextFormatter<BigDecimal> poriva1Fmt = new TextFormatter<BigDecimal>(new FormatStringConverter<BigDecimal>(nfFmtInstance),factura.getPoriva1());
		poriva1Fmt.valueProperty().bindBidirectional(factura.poriva1Property());	
		tfPorIva1.setTextFormatter(poriva1Fmt);
		// tfPorIva1.textProperty().bindBidirectional(factura.poriva1Property(), new BigDecimalStringConverter());
		
		TextFormatter<BigDecimal> impiva1Fmt = new TextFormatter<BigDecimal>(new FormatStringConverter<BigDecimal>(nfFmtInstance),factura.getImpiva1());
		impiva1Fmt.valueProperty().bindBidirectional(factura.impiva1Property());	
		tfImpIva1.setTextFormatter(impiva1Fmt);
		// tfImpIva1.textProperty().bindBidirectional(factura.impiva1Property(), new BigDecimalStringConverter());

		TextFormatter<BigDecimal> pordtoFmt = new TextFormatter<BigDecimal>(new FormatStringConverter<BigDecimal>(nfFmtInstance),factura.getPordto());
		pordtoFmt.valueProperty().bindBidirectional(factura.pordtoProperty());	
		tfPorDto.setTextFormatter(pordtoFmt);
		//tfBaseIva1.textProperty().bindBidirectional(factura.baseiva1Property(), new BigDecimalStringConverter());
		
		TextFormatter<BigDecimal> impDtoFmt = new TextFormatter<BigDecimal>(new FormatStringConverter<BigDecimal>(nfFmtInstance),factura.getImpdto());
		impDtoFmt.valueProperty().bindBidirectional(factura.impdtoProperty());	
		tfImpDto.setTextFormatter(impDtoFmt);
		
		TextFormatter<BigDecimal> totfacFmt = new TextFormatter<BigDecimal>(new FormatStringConverter<BigDecimal>(nfFmtInstance),factura.getTotfac());
		totfacFmt.valueProperty().bindBidirectional(factura.totfacProperty());	
		tfTotFac.setTextFormatter(totfacFmt);
		// tfTotFac.textProperty().bindBidirectional(factura.totfacProperty(), new BigDecimalStringConverter());

	}

	// This listener listen to changes in the table widget selection and
	// update the DeleteIssue button state accordingly.
	private final ListChangeListener<Factureslin> tableSelectionChanged = new ListChangeListener<Factureslin>() {

		@Override
		public void onChanged(Change<? extends Factureslin> c) {

			// updateDeleteIssueButtonState();
			updateDetalls();
			// updateSaveIssueButtonState();
		}
	};

	private void configureButtons() {
		btEliminarLinea.setDisable(true);
		btGuardaLinea.setDisable(true);
		btEliminarLinea.setStyle("-fx-background-color:grey");
		btGuardaLinea.setStyle("-fx-background-color:grey");

	}

	protected void updateDetalls() {
		System.out.println("Update Detalls");

	}

	private void configuraDetalls() {
		if (detalls != null) {
			detalls.setVisible(false);
		}

		if (detalls != null) {
			detalls.addEventFilter(EventType.ROOT, new EventHandler<Event>() {

				@Override
				public void handle(Event event) {
					if (event.getEventType() == MouseEvent.MOUSE_RELEASED
							|| event.getEventType() == KeyEvent.KEY_RELEASED) {
						System.out.println("alguna cosa ha passat al panel de detalls");
						// updateSaveIssueButtonState();
					}
				}
			});
		}
	}

	/*
	 * public void setMainApp(MainApp2 mainApp) { this.mainApp = mainApp; }
	 */
	public void cargarDatosTabla() {
		// obtenir factura
		model = new FacturasService(getEm());
		clientesList.addAll(model.getAllClientes());
		setupClientesCombobox();
		forpagList.addAll(model.getAllFormasPago());
		setupForPagCombobox();
		productosList.addAll(model.getAllProductos());
		setupProductosCombobox();

		if (operacio == null) {
			System.out.println("operacio es null");

		} else {
			switch (this.operacio) {
			case FactuApp.EDIT_FACTURA:
				if (this.idFactura != null && this.idFactura > 0) {
					factura = model.getFacturaPorId(this.idFactura);
					System.out.println("###### operacio " + FactuApp.EDIT_FACTURA + " :" + factura);// omplir
																									// camps
					/*
					 * this.tfIdentificador.setText(String.valueOf(factura.getId
					 * ())); this.tfSerie.setText(factura.getSerieId());
					 * this.tfNumeroFactura.setText(String.valueOf(factura.
					 * getNumero()));
					 * this.dtFechaFactura.setValue(factura.getDat());
					 * this.clientesCombo.setValue(factura.getCliente());
					 * this.formasPagoCombo.setValue(factura.getFormaspago());
					 * this.tfImpBru.setText(String.valueOf(factura.getImpbru())
					 * ); this.tfBaseIrpf.setText(String.valueOf(factura.
					 * getBaseirpf()));
					 * this.tfPorIrpf.setText(String.valueOf(factura.getPorirpf(
					 * ))); this.tfImpIrpf.setText(String.valueOf(0));
					 * this.tfBaseIva1.setText(String.valueOf(factura.
					 * getBaseiva1()));
					 * this.tfPorIva1.setText(String.valueOf(factura.getPoriva1(
					 * )));
					 * this.tfImpIva1.setText(String.valueOf(factura.getImpiva1(
					 * )));
					 */
					// omplir detall
					if (detalleList == null) {
						System.out.println("###### detalleList null ");
						detalleList = FXCollections.observableArrayList(factura.getFactureslins());
						tableView.setItems(detalleList);
					} else {
						detalleList.clear();
						System.out.println("###### detalleList no null "+factura.getFactureslins().size());
						detalleList.addAll(factura.getFactureslins());
						tableView.setItems(detalleList);
						// detalleList = tableView.getSelectionModel().getSelectedItems();
						
						detalleList.addListener(tableSelectionChanged);

					}
					
				}
				break;

			default: // alta
				this.dtFechaFactura.setValue(LocalDate.now());
				break;
			}
		}

		// Listen for selection changes and show the client details when
		// changed.
		// tableView.getSelectionModel().selectedItemProperty()
		// .addListener((observable, oldValue, newValue) ->
		// showDetailsProducto(newValue));
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
		// clientesCombo.setOnAction(e -> System.out.println("Action Nueva
		// Selección: " + clientesCombo.getValue()));
		// Handle ComboBox event.

		clientesCombo.setOnAction((event) -> {
			Cliente selected = clientesCombo.getSelectionModel().getSelectedItem();
			lCodCli.setText(selected.toString());
			System.out.println("ComboBox Action (Cliente selected: " + selected.toString() + ")\n");
		});
		clientesCombo.valueProperty().addListener((ov, p1, p2) -> {
			System.out.println("Nueva Selección: " + p2);
			System.out.println("Vieja Selección: " + p1);
		});

	}

	private void setupForPagCombobox() {
		// Init ComboBox items.
		formasPagoCombo.setItems(forpagList);
		// Define rendering of the list of values in ComboBox drop down.
		formasPagoCombo.setCellFactory((comboBox) -> {
			return new ListCell<Formaspago>() {
				@Override
				protected void updateItem(Formaspago item, boolean empty) {
					super.updateItem(item, empty);

					if (item == null || empty) {
						setText(null);
					} else {
						setText(item.getDem());
					}
				}
			};
		});
		// Define rendering of selected value shown in ComboBox.
		formasPagoCombo.setConverter(new StringConverter<Formaspago>() {
			@Override
			public String toString(Formaspago item) {
				return (item.getDem());
			}

			@Override
			public Formaspago fromString(String string) {
				throw new UnsupportedOperationException("Not supported yet.");
			}
		});
		// Handle ComboBox event.
		// formasPagoCombo.setOnAction(e -> System.out.println("Action Nueva
		// Selección: " + formasPagoCombo.getValue()));
		// Handle ComboBox event.

		formasPagoCombo.setOnAction((event) -> {
			Formaspago selected = formasPagoCombo.getSelectionModel().getSelectedItem();
			// lCodCli.setText(selected.toString());
			System.out.println("ComboBox Action (Formas Pago selected: " + selected.toString() + ")\n");
		});
		formasPagoCombo.valueProperty().addListener((ov, p1, p2) -> {
			System.out.println("Nueva Selección: " + p2);
			System.out.println("Vieja Selección: " + p1);
		});

	}

	private void setupProductosCombobox() {
		// Init ComboBox items.
		productosCombo.setItems(productosList);
		// Define rendering of the list of values in ComboBox drop down.
		productosCombo.setCellFactory((comboBox) -> {
			return new ListCell<Producto>() {
				@Override
				protected void updateItem(Producto item, boolean empty) {
					super.updateItem(item, empty);

					if (item == null || empty) {
						setText(null);
					} else {
						setText(item.getDem());
					}
				}
			};
		});
		// Define rendering of selected value shown in ComboBox.
		productosCombo.setConverter(new StringConverter<Producto>() {
			@Override
			public String toString(Producto item) {
				return (item.getDem());
			}

			@Override
			public Producto fromString(String string) {
				throw new UnsupportedOperationException("Not supported yet.");
			}
		});
		// Handle ComboBox event.
		// formasPagoCombo.setOnAction(e -> System.out.println("Action Nueva
		// Selección: " + formasPagoCombo.getValue()));
		// Handle ComboBox event.

		productosCombo.setOnAction((event) -> {
			Producto selected = productosCombo.getSelectionModel().getSelectedItem();
			// lCodCli.setText(selected.toString());
			System.out.println("ComboBox Action (Productos selected: " + selected.toString() + ")\n");
		});
		productosCombo.valueProperty().addListener((ov, p1, p2) -> {
			System.out.println("Nueva Selección: " + p2);
			System.out.println("Vieja Selección: " + p1);
		});

	}

	private void showDetailsProducto(Producto row) {

		btEliminarLinea.setDisable(false);
		btGuardaLinea.setDisable(false);

		btEliminarLinea.setStyle("-fx-background-color:#66CCCC");
		btGuardaLinea.setStyle("-fx-background-color:#66CCCC");
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
					btEliminarLinea.setDisable(false);
					btGuardaLinea.setDisable(false);
					btEliminarLinea.setStyle("-fx-background-color:#66CCCC");
					btGuardaLinea.setStyle("-fx-background-color:#66CCCC");
					String valor = tableView.getSelectionModel().getSelectedItems().get(0).toString();

				}
			}
		});
	}

	@FXML
	private void handleNewLinea(ActionEvent event) {
		/*
		 * Producto producto = new Producto();
		 * producto.setId(tfIdentificador.getText());
		 * producto.setDem(tfNombreProducto.getText());
		 * model.insertar(producto); tableView.getItems().clear();
		 * cargarDatosTabla();
		 */
		System.out.println("add linea");
		detalls.setVisible(true);

	}

	@FXML
	private void handleGuardaLinea(ActionEvent event) {

		/*
		 * Producto producto = tableView.getSelectionModel().getSelectedItem();
		 * producto.setDem(tfNombreProducto.getText());
		 * model.modificar(producto);
		 */
		System.out.println("modificar linea");
	}

	@FXML
	private void handleEliminarLinea(ActionEvent event) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Confirmación");
		alert.setHeaderText("Eliminar Lineao");
		alert.setContentText("Realmente desea eliminar este registro?");
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK) {
			;
		}
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
		System.out.println("######## FacturasController: setOperacio " + operacio);
		this.operacio = operacio;
	}

	public Long getIdFactura() {
		return idFactura;
	}

	public void setIdFactura(Long idFactura) {
		this.idFactura = idFactura;
	}

}
