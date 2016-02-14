package es.palmademallorca.bg.factuapp;

import java.io.IOException;

import es.palmademallorca.bg.common.util.properties.PropertiesCache;
import es.palmademallorca.bg.factuapp.model.jpa.Ejercicio;
import es.palmademallorca.bg.factuapp.model.jpa.Empresa;
import es.palmademallorca.bg.factuapp.view.ClienteController;
import es.palmademallorca.bg.factuapp.view.RootLayoutController;
import javafx.application.Application;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableBooleanValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

public class MainApp extends Application {
	private Stage primaryStage;
	private BorderPane rootLayout;
	// empresa cargada
    private Empresa empresa;
    // ejercicio cargado
    private Ejercicio ejercicio;
	@Override
	public void start(Stage primaryStage) {
		try {
			this.primaryStage = primaryStage;
			//readProperties();
			initRootLayout();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}

	public void initRootLayout() {
		try {
			// Load root layout from fxml file.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/RootLayout.fxml"));
			rootLayout = (BorderPane) loader.load();
            rootLayout.setPrefSize(1024, 700);
			// Show the scene containing the root layout.
			Scene scene = new Scene(rootLayout);
			primaryStage.setScene(scene);

			// Give the controller access to the main app.
			RootLayoutController controller = loader.getController();
			controller.setMainApp(this);
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}


	}
	public BorderPane getRootLayout() {
		return rootLayout;
	}

	public void setRootLayout(BorderPane rootLayout) {
		this.rootLayout = rootLayout;
	}

	public void readProperties() {
		PropertiesCache cache = PropertiesCache.getInstance();
		if (!cache.containsKey("app.NomApl")) {
			cache.setProperty("app.NomApl", "Facturación");
		}
		if (!cache.containsKey("app.Version")) {
			cache.setProperty("app.NomApl", "V1.0");
		}
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	/*public ObservableBooleanValue isEmpresaSelected() {
		return empresa==null?new SimpleBooleanProperty(true):new SimpleBooleanProperty(false);
	}*/

	public Ejercicio getEjercicio() {
		return ejercicio;
	}

	public void setEjercicio(Ejercicio ejercicio) {
		this.ejercicio = ejercicio;
	}

	/*public ObservableBooleanValue isEjercicioLoaded() {
		return ejercicio==null?new SimpleBooleanProperty(true):new SimpleBooleanProperty(false);
	}*/

	public boolean isEjercicioLoaded() {
		return ejercicio==null?true:false;
	}
	public boolean isEmpresaLoaded() {
		return empresa==null?true:false;
	}
}
