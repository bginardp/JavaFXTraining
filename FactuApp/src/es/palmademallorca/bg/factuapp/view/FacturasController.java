package es.palmademallorca.bg.factuapp.view;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Function;

import javax.persistence.EntityManager;

import org.apache.commons.lang3.StringUtils;
import org.controlsfx.control.spreadsheet.GridBase;

import es.palmademallorca.bg.common.controller.GenericFXController;
import es.palmademallorca.bg.common.controller.IGenericController;
import es.palmademallorca.bg.factuapp.MainApp;
import es.palmademallorca.bg.factuapp.model.dao.FacturasService;
import es.palmademallorca.bg.factuapp.model.dao.IFacturasDAO;
import es.palmademallorca.bg.factuapp.model.jpa.Cliente;
import es.palmademallorca.bg.factuapp.model.jpa.Factura;
import es.palmademallorca.bg.factuapp.model.managers.EntityManagerProvider;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
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

		table = new TableView<>();
		table.setEditable(false);
		// columns: arguments to utility function are:
		// title, property, editable, converter (for editing cell)
		table.getColumns().add(column("Serie", Factura::serieIdProperty, false, null));
		table.getColumns().add(column("Numero",Factura::numeroProperty, false, null));
		table.getColumns().add(column("Fecha", Factura::datProperty, false, new LocalDateStringConverter()));
		table.getColumns().add(column("Base Imponible", Factura::baseiva1Property, false, new BigDecimalStringConverter()));
		table.getColumns().add(column("%IVA", Factura::poriva1Property, false, new BigDecimalStringConverter()));
		//table.getColumns().add(column("Imp. Iva", Factura::impiva1Property, false, new BigDecimalStringConverter()));
		table.getColumns().add(column("Base Irpf", Factura::baseirpfProperty, false, new BigDecimalStringConverter()));
		table.getColumns().add(column("%Irpf", Factura::porirpfProperty, false, new BigDecimalStringConverter()));
		table.getColumns().add(column("Importe bruto", Factura::impbruProperty, false, new BigDecimalStringConverter()));
		table.getColumns().add(column("Total fra", Factura::totfacProperty, false, new BigDecimalStringConverter()));

		table.setItems(facturasList);

		// 4 afegir component al seu pare

		vbox.getChildren().add(table);
		// panel.getChildren().add();
		return panel;
	}

	private void cargarDatosTabla() {
		model = new FacturasService(em);
		facturasList = FXCollections.observableArrayList(model.getAllFacturas(empresaId,ejercicio));

	}

	@Override
	public void postInitialize() {
		empresaId = this.getMainApp().getEmpresa().getId();
		ejercicio = this.getMainApp().getEjercicio().getId();
		cargarDatosTabla();

	    System.out.println(empresaId+" "+ejercicio);
	}

	@FXML
	private void cercarFactures(ActionEvent event) {
		table.getItems().clear();
		facturasList = FXCollections.observableArrayList(model.findFacturas(empresaId, ejercicio,this.cercarText.getText()));
		table.setItems(facturasList);

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

}