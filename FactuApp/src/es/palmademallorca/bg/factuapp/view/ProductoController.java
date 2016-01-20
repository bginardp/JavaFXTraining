package es.palmademallorca.bg.factuapp.view;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.persistence.EntityManager;
import javax.swing.JOptionPane;

import es.palmademallorca.bg.common.controls.ControlesBasicos;
import es.palmademallorca.bg.common.view.IControlledScreen;
import es.palmademallorca.bg.factuapp.MainApp;
import es.palmademallorca.bg.factuapp.model.dao.IProductosModel;
import es.palmademallorca.bg.factuapp.model.dao.ProductosModel;
import es.palmademallorca.bg.factuapp.model.jpa.Producto;
import es.palmademallorca.bg.factuapp.model.managers.EntityManagerProvider;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class ProductoController implements Initializable, IControlledScreen {

	ScreensController controlador;
	private ControlesBasicos controlesBasicos = new ControlesBasicos();
	private static EntityManager em = EntityManagerProvider.getProvider().getEntityManager();
	private IProductosModel model;

	@FXML
	private ObservableList<Producto> productosList=FXCollections.observableArrayList();
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
/*
		ObservableList<Object> categoriaID = FXCollections.observableArrayList();
		ObservableList<Object> categoriaNomnre = FXCollections.observableArrayList();
		ObservableList<Object> subCategoria = FXCollections.observableArrayList();
		ObservableList<Object> marcas = FXCollections.observableArrayList();


		 * try {
		 *
		 *
		 * // COMBOBOX DE CATEGORIA String slqCategoria =
		 * "SELECT idcategoria, nombre_categoria FROM categoria"; ResultSet
		 * resultadoCategoria =
		 * conexion.createStatement().executeQuery(slqCategoria);
		 * while(resultadoCategoria.next()) {
		 * cbCategoriaProducto.getItems().add(resultadoCategoria.getString(
		 * "nombre_categoria")); }
		 *
		 * // COMBOBOX DE MARCAS String slqMarcas =
		 * "SELECT idmarca, nombre_marca FROM marca"; ResultSet resultadoMarcas
		 * = conexion.createStatement().executeQuery(slqMarcas);
		 * while(resultadoMarcas.next()) {
		 * cbMarcaProducto.getItems().add(resultadoMarcas.getString(
		 * "nombre_marca")); }
		 *
		 * resultadoCategoria.close(); resultadoMarcas.close(); } catch
		 * (SQLException e) { System.out.println("Error " + e); }
		 */
	}

	@Override
	public void setScreenParent(ScreensController pantallaPadre) {
		controlador = pantallaPadre;
	}

	public  void cargarDatosTabla() {

        model = new ProductosModel(em);
 		//List<ProductoJPA> productos = model.getProductos();
 		productosList = FXCollections.observableArrayList(model.getProductos());
 		tableView.setItems(productosList);

        // Listen for selection changes and show the client details when changed.
		tableView.getSelectionModel().selectedItemProperty().addListener(
	            (observable, oldValue, newValue) -> showDetailsProducto(newValue));
      /*  if (productos.size()>0) {


            for (int i = 0; i < rs.getMetaData().getColumnCount(); i++ ) {
                final int j = i;
                col = new TableColumn(titulos[i]);
                col.setCellValueFactory(new Callback<CellDataFeatures<ObservableList, String>, ObservableValue<String>>(){
                    public ObservableValue<String> call(CellDataFeatures<ObservableList, String> parametro) {
                        return new SimpleStringProperty((String)parametro.getValue().get(j));
                    }
                });
                tablaProducto.getColumns().addAll(col);
                // Asignamos un tamaño a ls columnnas
                col.setMinWidth(100);
                System.out.println("Column ["+i+"] ");
                // Centrar los datos de la tabla
                col.setCellFactory(new Callback<TableColumn<String,String>,TableCell<String,String>>(){
                    @Override
                    public TableCell<String, String> call(TableColumn<String, String> p) {
                        TableCell cell = new TableCell(){
                            @Override
                            protected void updateItem(Object t, boolean bln) {
                                if(t != null){
                                    super.updateItem(t, bln);
                                    System.out.println(t);
                                    setText(t.toString());
                                    setAlignment(Pos.CENTER); //Setting the Alignment
                                }
                            }
                        };
                        return cell;
                    }
                });
            }

            // Cargamos de la base de datos *

            while(rs.next()){
                //Iterate Row
                ObservableList<String> row = FXCollections.observableArrayList();
                for(int i = 1 ; i <= rs.getMetaData().getColumnCount(); i++){
                    //Iterate Column
                    row.add(rs.getString(i));
                }
                System.out.println("Row [1] added "+row );
                producto.addAll(row);
            }
            //FINALLY ADDED TO TableView
            tablaProducto.setItems(producto);
            rs.close();
          }catch(SQLException e){
              System.out.println("Error "+e);
          } */
    }
	private void setUpCols() {
		descripcionCol.setCellValueFactory(cellData -> cellData.getValue().demProperty());
		identificadorCol.setCellValueFactory(cellData -> cellData.getValue().idProperty());
	}
	private void showDetailsProducto (Producto row) {
		btAddProducto.setDisable(true);
		btEliminarProducto.setDisable(false);
		btModificarProducto.setDisable(false);
		btAddProducto.setStyle("-fx-background-color:grey");
		btEliminarProducto.setStyle("-fx-background-color:#66CCCC");
		btModificarProducto.setStyle("-fx-background-color:#66CCCC");
		if (row!=null) {
			tfIdentificador.setText(row.getId());
			tfNombreProducto.setText(row.getDem());
			tfNombreProducto.requestFocus();
		}  else {
	        // Person is null, remove all the text.
			tfIdentificador.setText("");
			tfNombreProducto.setText("");
	    }
		tfIdentificador.setEditable(false);
		tfIdentificador.setStyle("-fx-text-inner-color: red;");

	}
	public void cargarProductosText(String valor) {

		/*try {

			conexion = DBConnection.connect();
			String sql = "SELECT p.*, c.*, m.* " + " FROM producto AS p, categoria AS c, marca AS m "
					+ " WHERE idproducto = " + valor + " AND " + " p.idcategoria = c.idcategoria AND "
					+ " p.idmarca = m.idmarca";
			ResultSet rs = conexion.createStatement().executeQuery(sql);

			while (rs.next()) {
				lbCodigoProducto.setText(rs.getString("idproducto"));
				tfNombreProducto.setText(rs.getString("nombre_producto"));
				tfPrecioProducto.setText(rs.getString("precio"));
				cbCategoriaProducto.setValue(rs.getString("nombre_categoria"));
				cbMarcaProducto.setValue(rs.getString("nombre_marca"));

			}
			rs.close();
		} catch (SQLException ex) {
			System.out.println("Error " + ex);
		} */

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
		Producto producto=new Producto();
        producto.setId(tfIdentificador.getText());
		producto.setDem(tfNombreProducto.getText());
		model.insertar(producto);
		tableView.getItems().clear();
		cargarDatosTabla();
		//int indiceCategoria = cbCategoriaProducto.getSelectionModel().getSelectedIndex() + 1;
		//  int indiceMarca = cbMarcaProducto.getSelectionModel().getSelectedIndex() + 1;

		/*try {
			conexion = DBConnection.connect();
			String sql = "INSERT INTO producto " + " (nombre_producto, precio, idcategoria, idmarca) "
					+ " VALUES (?, ?, ?, ?)";
			PreparedStatement estado = conexion.prepareStatement(sql);
			estado.setString(1, tfNombreProducto.getText());
			estado.setInt(2, Integer.parseInt(tfPrecioProducto.getText()));
			estado.setInt(3, indiceCategoria);
			estado.setInt(4, indiceMarca);

			int n = estado.executeUpdate();

			if (n > 0) {

				tablaProducto.getColumns().clear();
				tablaProducto.getItems().clear();
				cargarDatosTabla();
			}
			estado.close();

		} catch (SQLException e) {
			System.out.println("Error " + e);
		}
*/
	}

	@FXML
	private void modificarProducto(ActionEvent event) {

		Producto producto = tableView.getSelectionModel().getSelectedItem();
		producto.setDem(tfNombreProducto.getText());
		model.modificar(producto);

		//int indiceCategoria = cbCategoriaProducto.getSelectionModel().getSelectedIndex() + 1;
		// int indiceMarca = cbMarcaProducto.getSelectionModel().getSelectedIndex() + 1;

		/*try {
			conexion = DBConnection.connect();

			String sql = "UPDATE producto " + " SET nombre_producto = ?, " + " precio = ?, " + " idcategoria = ?, "
					+ " idmarca = ?" + " WHERE idproducto = " + lbCodigoProducto.getText() + "";

			PreparedStatement estado = conexion.prepareStatement(sql);

			estado.setString(1, tfNombreProducto.getText());
			estado.setInt(2, Integer.parseInt(tfPrecioProducto.getText()));
			estado.setInt(3, indiceCategoria);
			estado.setInt(4, indiceMarca);

			int n = estado.executeUpdate();

			if (n > 0) {
				tablaProducto.getColumns().clear();
				tablaProducto.getItems().clear();
				cargarDatosTabla();
			}

			estado.close();
		} catch (SQLException e) {
			System.out.println("Error " + e);
		}*/
	}

	@FXML
	private void eliminarProducto(ActionEvent event) {

		int confirmarEliminar = JOptionPane.showConfirmDialog(null, "Realmente desea eliminar este producto??");

		if (confirmarEliminar == 0) {
			;
			/*try {
				conexion = DBConnection.connect();

				String sql = "DELETE FROM producto WHERE idproducto = " + lbCodigoProducto.getText() + "";

				PreparedStatement estado = conexion.prepareStatement(sql);

				int n = estado.executeUpdate();

				if (n > 0) {
					tablaProducto.getColumns().clear();
					tablaProducto.getItems().clear();
					cargarDatosTabla();
				}

				estado.close();

			} catch (SQLException e) {
				System.out.println("Error " + e);
			}*/
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

	@FXML
	private void irInicioContenido(ActionEvent event) {
		controlador.setScreen(MainApp.contenidoID);
	}

	@FXML
	private void salir(ActionEvent event) {
		this.controlesBasicos.salirSistema();
	}

	@FXML
	private void cerrarSesion(ActionEvent event) {

		controlador.setScreen(MainApp.loginID);
	}
}