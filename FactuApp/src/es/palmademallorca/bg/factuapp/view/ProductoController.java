package es.palmademallorca.bg.factuapp.view;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import es.palmademallorca.bg.common.controller.GenericFXController;
import es.palmademallorca.bg.factuapp.model.dao.IProductosModel;
import es.palmademallorca.bg.factuapp.model.dao.ProductosModel;
import es.palmademallorca.bg.factuapp.model.jpa.Producto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;

public class ProductoController extends GenericFXController {
	// private MainApp2 mainApp;
	// private static EntityManager em =
	// EntityManagerProvider.getProvider().getEntityManager();
	private IProductosModel model;

	@FXML
	private ObservableList<Producto> productosList = FXCollections.observableArrayList();
	@FXML
	private TableView<Producto> tableView;
	@FXML
	private TableColumn<Producto, String> identificadorCol;
	@FXML
	private TableColumn<Producto, String> descripcionCol;
	@FXML
	private Button btAddProducto;
	@FXML
	private Button btModificarProducto;
	@FXML
	private Button btEliminarProducto;
	@FXML
	private Button btNuevoProducto;
	@FXML
	private TextField tfNombreProducto;
	@FXML
	private TextField tfBuscarProducto;
	@FXML
	private TextField tfIdentificador;

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		this.setUpCols();
		this.cargarDatosTabla();

		btEliminarProducto.setDisable(true);
		btModificarProducto.setDisable(true);
		btEliminarProducto.setStyle("-fx-background-color:grey");
		btModificarProducto.setStyle("-fx-background-color:grey");

	}

	/*
	 * public void setMainApp(MainApp2 mainApp) { this.mainApp = mainApp; }
	 */
	public void cargarDatosTabla() {

		model = new ProductosModel(getEm());
		productosList = FXCollections.observableArrayList(model.getProductos());
		tableView.setItems(productosList);

		// Listen for selection changes and show the client details when
		// changed.
		tableView.getSelectionModel().selectedItemProperty()
				.addListener((observable, oldValue, newValue) -> showDetailsProducto(newValue));
	}

	private void setUpCols() {
		descripcionCol.setCellValueFactory(cellData -> cellData.getValue().demProperty());
		identificadorCol.setCellValueFactory(cellData -> cellData.getValue().idProperty());
	}

	private void showDetailsProducto(Producto row) {
		btAddProducto.setDisable(true);
		btEliminarProducto.setDisable(false);
		btModificarProducto.setDisable(false);
		btAddProducto.setStyle("-fx-background-color:grey");
		btEliminarProducto.setStyle("-fx-background-color:#66CCCC");
		btModificarProducto.setStyle("-fx-background-color:#66CCCC");
		if (row != null) {
			tfIdentificador.setText(row.getId());
			tfNombreProducto.setText(row.getDem());
			tfNombreProducto.requestFocus();
		} else {
			// Person is null, remove all the text.
			tfIdentificador.setText("");
			tfNombreProducto.setText("");
		}
		tfIdentificador.setEditable(false);
		tfIdentificador.setStyle("-fx-text-inner-color: red;");

	}

	public void cargarProductosText(String valor) {

		/*
		 * try {
		 *
		 * conexion = DBConnection.connect(); String sql =
		 * "SELECT p.*, c.*, m.* " +
		 * " FROM producto AS p, categoria AS c, marca AS m " +
		 * " WHERE idproducto = " + valor + " AND " +
		 * " p.idcategoria = c.idcategoria AND " + " p.idmarca = m.idmarca";
		 * ResultSet rs = conexion.createStatement().executeQuery(sql);
		 *
		 * while (rs.next()) {
		 * lbCodigoProducto.setText(rs.getString("idproducto"));
		 * tfNombreProducto.setText(rs.getString("nombre_producto"));
		 * tfPrecioProducto.setText(rs.getString("precio"));
		 * cbCategoriaProducto.setValue(rs.getString("nombre_categoria"));
		 * cbMarcaProducto.setValue(rs.getString("nombre_marca"));
		 *
		 * } rs.close(); } catch (SQLException ex) { System.out.println("Error "
		 * + ex); }
		 */

	}

	@FXML
	private void getProductoSeleccionado(MouseEvent event) {
		tableView.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				if (tableView != null) {

					btAddProducto.setDisable(true);
					btEliminarProducto.setDisable(false);
					btModificarProducto.setDisable(false);
					btAddProducto.setStyle("-fx-background-color:grey");
					btEliminarProducto.setStyle("-fx-background-color:#66CCCC");
					btModificarProducto.setStyle("-fx-background-color:#66CCCC");

					String valor = tableView.getSelectionModel().getSelectedItems().get(0).toString();

					String cincoDigitos = valor.substring(1, 6);
					String cuatroDigitos = valor.substring(1, 5);
					String tresDigitos = valor.substring(1, 4);
					String dosDigitos = valor.substring(1, 3);
					String unDigitos = valor.substring(1, 2);

					Pattern p = Pattern.compile("^[0-9]*$");

					Matcher m5 = p.matcher(cincoDigitos);
					Matcher m4 = p.matcher(cuatroDigitos);
					Matcher m3 = p.matcher(tresDigitos);
					Matcher m2 = p.matcher(dosDigitos);

					if (m5.find()) {
						cargarProductosText(cincoDigitos);
					} else {
						if (m4.find()) {
							cargarProductosText(cuatroDigitos);
						} else {
							if (m3.find()) {
								cargarProductosText(tresDigitos);
							} else {
								if (m2.find()) {
									cargarProductosText(dosDigitos);
								} else {
									cargarProductosText(unDigitos);
								}
							}
						}
					}
				}
			}
		});
	}

	@FXML
	private void addProducto(ActionEvent event) {
		Producto producto = new Producto();
		producto.setId(tfIdentificador.getText());
		producto.setDem(tfNombreProducto.getText());
		model.insertar(producto);
		tableView.getItems().clear();
		cargarDatosTabla();
	}

	@FXML
	private void modificarProducto(ActionEvent event) {

		Producto producto = tableView.getSelectionModel().getSelectedItem();
		producto.setDem(tfNombreProducto.getText());
		model.modificar(producto);
	}

	@FXML
	private void eliminarProducto(ActionEvent event) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Confirmación");
		alert.setHeaderText("Eliminar Producto");
		alert.setContentText("Realmente desea eliminar este producto?");
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK) {
			;
		}
	}

	@FXML
	private void buscarProducto(ActionEvent event) {
		tableView.getItems().clear();
		productosList = FXCollections.observableArrayList(model.getProductosPorDem(tfBuscarProducto.getText()));
		tableView.setItems(productosList);

	}

	@FXML
	private void nuevoProducto(ActionEvent event) {
		btAddProducto.setDisable(false);
		btEliminarProducto.setDisable(true);
		btModificarProducto.setDisable(true);
		btAddProducto.setStyle("-fx-background-color:#66CCCC");
		btEliminarProducto.setStyle("-fx-background-color:grey");
		btModificarProducto.setStyle("-fx-background-color:grey");
		tfIdentificador.setEditable(true);
		tfIdentificador.setText("");
		tfNombreProducto.setText("");
		tfIdentificador.requestFocus();
		tfIdentificador.setStyle("");
	}

	@Override
	public void postInitialize() {
		// TODO Auto-generated method stub

	}

}
