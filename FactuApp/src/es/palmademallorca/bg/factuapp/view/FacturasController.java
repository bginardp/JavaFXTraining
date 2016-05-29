package es.palmademallorca.bg.factuapp.view;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.function.Function;

import javax.persistence.EntityManager;

import org.apache.commons.lang3.StringUtils;
import org.controlsfx.control.spreadsheet.GridBase;

import es.palmademallorca.bg.common.controller.GenericFXController;
import es.palmademallorca.bg.common.controller.IGenericController;
import es.palmademallorca.bg.factuapp.FactuApp;
import es.palmademallorca.bg.factuapp.MainApp;
import es.palmademallorca.bg.factuapp.model.dao.FacturasService;
import es.palmademallorca.bg.factuapp.model.dao.IFacturasDAO;
import es.palmademallorca.bg.factuapp.model.jpa.Cliente;
import es.palmademallorca.bg.factuapp.model.jpa.Factura;
import es.palmademallorca.bg.factuapp.model.managers.EntityManagerProvider;
import javafx.beans.property.ObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableSet;
import javafx.css.PseudoClass;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Skin;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import javafx.util.StringConverter;
import javafx.util.converter.BigDecimalStringConverter;
import javafx.util.converter.LocalDateStringConverter;

/**
 *
 * @author Bernat
 */
public class FacturasController extends GenericFXController implements Initializable, IGenericController {

	private static EntityManager em = EntityManagerProvider.getProvider().getEntityManager();

	private IFacturasDAO model;
	private TableView<Factura> table;
	private Long empresaId;
	private Integer ejercicio;
	private long idFactura;

	@FXML
	private AnchorPane panel;
	@FXML
	private TextField cercarText;
	@FXML
	private Button cercarBtn;
	@FXML
	private VBox vbox;

	private ObservableList<Factura> facturasList = FXCollections.observableArrayList();

	/**
	 * Is called by the main application to give a reference back to itself.
	 *
	 * @param mainApp
	 */

	public FacturasController() {

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}

	public AnchorPane buildPanel() {
		DateTimeFormatter myDateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

		table = new TableView<>();
		table.setEditable(false);

		// columns: arguments to utility function are:
		// title, property, editable, converter (for editing cell)
		table.getColumns().add(column("Serie", Factura::serieIdProperty, false, null));
		table.getColumns().add(column("Numero", Factura::numeroProperty, false, null));

		TableColumn<Factura, String> nomClienteCol = new TableColumn<>("Cliente");
		nomClienteCol.setPrefWidth(235);
		nomClienteCol.setCellValueFactory(new PropertyValueFactory<Factura, String>("nomCliente"));

		table.getColumns().add(nomClienteCol);
		TableColumn<Factura, LocalDate> fechaCol = column("Fecha", Factura::datProperty, false,
				new LocalDateStringConverter());
		fechaCol.setCellFactory(column -> {
			return new TableCell<Factura, LocalDate>() {
				@Override
				protected void updateItem(LocalDate item, boolean empty) {
					super.updateItem(item, empty);
					if (item == null || empty) {
						setText(null);
					} else {
						setText(item.format(myDateFormatter));
					}
				}
			};
		});


		table.getColumns().add(fechaCol);

		TableColumn<Factura,BigDecimal> baseIva1Col = new TableColumn();
        baseIva1Col.setText("Base Imponible");
       // baseIva1.setMinWidth(100);
        baseIva1Col.setCellValueFactory(new PropertyValueFactory<Factura,BigDecimal>("baseiva1"));
        baseIva1Col.setCellFactory(new Callback<TableColumn<Factura,BigDecimal>, TableCell<Factura,BigDecimal>>() {
            public TableCell<Factura,BigDecimal> call(TableColumn<Factura,BigDecimal> p) {
                TableCell<Factura,BigDecimal> cell = new NumberCell();
                cell.setStyle("-fx-alignment: top-right;");
                return cell;
            }
        });

        table.getColumns().add(baseIva1Col);


        TableColumn<Factura,BigDecimal> porIva1Col = new TableColumn();
        porIva1Col.setText("%iva");
        porIva1Col.setMinWidth(50);
        porIva1Col.setCellValueFactory(new PropertyValueFactory<Factura,BigDecimal>("poriva1"));
        porIva1Col.setCellFactory(new Callback<TableColumn<Factura,BigDecimal>, TableCell<Factura,BigDecimal>>() {
            public TableCell<Factura,BigDecimal> call(TableColumn<Factura,BigDecimal> p) {
                TableCell<Factura,BigDecimal> cell = new NumberCell();
                cell.setStyle("-fx-alignment: top-right;");
                return cell;
            }
        });
        table.getColumns().add(porIva1Col);

        //table.getColumns().add(
		//		column("Base Imponible", Factura::baseiva1Property, false, new BigDecimalStringConverter(), "Number"));
		//table.getColumns()
		//	.add(column("%IVA", Factura::poriva1Property, false, new BigDecimalStringConverter()));
		// table.getColumns().add(column("Imp. Iva", Factura::impiva1Property,
		// false, new BigDecimalStringConverter()));

        TableColumn<Factura,BigDecimal> baseirpfCol= new TableColumn();
        baseirpfCol.setText("Base Irpf");
        baseirpfCol.setMinWidth(100);
        baseirpfCol.setCellValueFactory(new PropertyValueFactory<Factura,BigDecimal>("baseirpf"));
        baseirpfCol.setCellFactory(new Callback<TableColumn<Factura,BigDecimal>, TableCell<Factura,BigDecimal>>() {
            public TableCell<Factura,BigDecimal> call(TableColumn<Factura,BigDecimal> p) {
                TableCell<Factura,BigDecimal> cell = new NumberCell();
                cell.setStyle("-fx-alignment: top-right;");
                return cell;
            }
        });
        table.getColumns().add(baseirpfCol);
		//table.getColumns()
		//		.add(column("Base Irpf", Factura::baseirpfProperty, false, new BigDecimalStringConverter()));
        TableColumn<Factura,BigDecimal> porirpfCol= new TableColumn();
        porirpfCol.setText("% irpf");
        porirpfCol.setMinWidth(50);
        porirpfCol.setCellValueFactory(new PropertyValueFactory<Factura,BigDecimal>("porirpf"));
        porirpfCol.setCellFactory(new Callback<TableColumn<Factura,BigDecimal>, TableCell<Factura,BigDecimal>>() {
            public TableCell<Factura,BigDecimal> call(TableColumn<Factura,BigDecimal> p) {
                TableCell<Factura,BigDecimal> cell = new NumberCell();
                cell.setStyle("-fx-alignment: top-right;");
                return cell;
            }
        });
        table.getColumns().add(porirpfCol);
		//table.getColumns()
		//		.add(column("%Irpf", Factura::porirpfProperty, false, new BigDecimalStringConverter()));


      		//table.getColumns()
      		//		.add(column("Base Irpf", Factura::baseirpfProperty, false, new BigDecimalStringConverter()));
              TableColumn<Factura,BigDecimal> impbruCol= new TableColumn();
              impbruCol.setText("Importe Bruto");
              impbruCol.setMinWidth(100);
              impbruCol.setCellValueFactory(new PropertyValueFactory<Factura,BigDecimal>("impbru"));
              impbruCol.setCellFactory(new Callback<TableColumn<Factura,BigDecimal>, TableCell<Factura,BigDecimal>>() {
                  public TableCell<Factura,BigDecimal> call(TableColumn<Factura,BigDecimal> p) {
                      TableCell<Factura,BigDecimal> cell = new NumberCell();
                      cell.setStyle("-fx-alignment: top-right;");
                      return cell;
                  }
              });
              table.getColumns().add(impbruCol);

        //table.getColumns().add(
		//		column("Importe bruto", Factura::impbruProperty, false, new BigDecimalStringConverter()));
              TableColumn<Factura,BigDecimal> totfacCol= new TableColumn();
              totfacCol.setText("Total Factura");
              totfacCol.setMinWidth(100);
              totfacCol.setCellValueFactory(new PropertyValueFactory<Factura,BigDecimal>("totfac"));
              totfacCol.setCellFactory(new Callback<TableColumn<Factura,BigDecimal>, TableCell<Factura,BigDecimal>>() {
                  public TableCell<Factura,BigDecimal> call(TableColumn<Factura,BigDecimal> p) {
                      TableCell<Factura,BigDecimal> cell = new NumberCell();
                      cell.setStyle("-fx-alignment: top-right;");
                      return cell;
                  }
              });
              table.getColumns().add(totfacCol);
		// table.getColumns()
		//		.add(column("Total fra", Factura::totfacProperty, false, new BigDecimalStringConverter()));




		table.setItems(facturasList);
		table.getSelectionModel().selectedItemProperty()
		.addListener((observable, oldValue, newValue) -> facturaSeleccionada(newValue));
		// 4 afegir component al seu pare

		vbox.getChildren().add(table);
		// panel.getChildren().add();
		return panel;
	}

	private void facturaSeleccionada(Factura newValue) {
		// TODO Auto-generated method stub
		System.out.println("Factura seleccinada:"+newValue.getId());
		this.idFactura=newValue.getId();
	}

	private void cargarDatosTabla() {
		model = new FacturasService(em);
		facturasList = FXCollections.observableArrayList(model.getAllFacturas(empresaId, ejercicio));

	}

	@Override
	public void postInitialize() {
		empresaId = this.getMainApp().getEmpresa().getId();
		ejercicio = this.getMainApp().getEjercicio().getId();
		cargarDatosTabla();

		System.out.println(empresaId + " " + ejercicio);
	}

	@FXML
	private void cercarFactures(ActionEvent event) {
		table.getItems().clear();
		facturasList = FXCollections
				.observableArrayList(model.findFacturas(empresaId, ejercicio, this.cercarText.getText()));
		table.setItems(facturasList);

	}

	@FXML
	private void altaFactura(ActionEvent event) {
					// Load the fxml file and set into the center of the main layout
			FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("view/Factura.fxml"));
			try {
				AnchorPane panel = (AnchorPane) loader.load();
				FacturaController controller = loader.getController();
				controller.setMainApp(getMainApp());
				controller.setOperacio(FactuApp.NEW_FACTURA);
				getMainApp().getRootLayout().setCenter(panel);

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// getMainApp().getRootLayout().setCenter(panel);
	}

	@FXML
	private void handleEditFactura(ActionEvent event) {
					// Load the fxml file and set into the center of the main layout
		if (this.idFactura > 0) {
			FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("view/Factura.fxml"));
			try {
				AnchorPane panel = (AnchorPane) loader.load();
				FacturaController controller = loader.getController();
				controller.setMainApp(getMainApp());
				controller.setOperacio(FactuApp.EDIT_FACTURA);

				controller.setIdFactura(this.idFactura);
				getMainApp().getRootLayout().setCenter(panel);

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

			// getMainApp().getRootLayout().setCenter(panel);



	}


	private static <S, T> TableColumn<S, T> column(String title, Function<S, ObservableValue<T>> property,
			boolean editable, StringConverter<T> converter) {

		TableColumn<S, T> col = new TableColumn<>(title);
		col.setCellValueFactory(cellData -> property.apply(cellData.getValue()));
		col.setEditable(editable);
		if (editable) {
			col.setCellFactory(TextFieldTableCell.forTableColumn(converter));
		}
		return col;
}

class NumberCell extends TableCell<Factura, BigDecimal> {
	public NumberCell() {

	}

	@Override
	protected void updateItem(BigDecimal item, boolean empty) {
		// TODO Auto-generated method stub
		super.updateItem(item, empty);
		setText(empty ? null : getString());
		setGraphic(null);
	}

	private String getString() {
		String ret = "";
		if (getItem() != null) {
			String gi = getItem().toString();
			NumberFormat df = DecimalFormat.getInstance();
			df.setMinimumFractionDigits(2);
			df.setRoundingMode(RoundingMode.DOWN);

			ret = df.format(Double.parseDouble(gi));
		} else {
			ret = "0.00";
		}
		return ret;
	}

}

}