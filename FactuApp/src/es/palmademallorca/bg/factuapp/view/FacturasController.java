package es.palmademallorca.bg.factuapp.view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import org.controlsfx.control.spreadsheet.GridBase;
import org.controlsfx.control.spreadsheet.SpreadsheetCell;
import org.controlsfx.control.spreadsheet.SpreadsheetCellType;
import org.controlsfx.control.spreadsheet.SpreadsheetView;

import com.sun.corba.se.impl.orbutil.graph.Node;

import es.palmademallorca.bg.common.controller.IGenericController;
import es.palmademallorca.bg.factuapp.MainApp2;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author Bernat
 */
public class FacturasController implements Initializable, IGenericController {

	// Reference to the main application
	private MainApp2 mainApp;

	/**
	 * Is called by the main application to give a reference back to itself.
	 *
	 * @param mainApp
	 */

	public FacturasController() {

	}

	public void setMainApp(MainApp2 mainApp) {
		this.mainApp = mainApp;

	}


	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}

	public AnchorPane buildPanel(AnchorPane panel) {
		 int rowCount = 15;
	     int columnCount = 10;
	     GridBase grid = new GridBase(rowCount, columnCount);
	     ObservableList<ObservableList<SpreadsheetCell>> rows = FXCollections.observableArrayList();
	     for (int row = 0; row < grid.getRowCount(); ++row) {
	         final ObservableList<SpreadsheetCell> list = FXCollections.observableArrayList();
	         for (int column = 0; column < grid.getColumnCount(); ++column) {
	             list.add(SpreadsheetCellType.STRING.createCell(row, column, 1, 1,"value"));
	         }
	         rows.add(list);
	     }
	     grid.setRows(rows);
	     SpreadsheetView spv = new SpreadsheetView(grid);
	     panel.getChildren().add(spv);
	     return panel;
	}

	@Override
	public void postInitialize() {
		// TODO Auto-generated method stub

	}

}