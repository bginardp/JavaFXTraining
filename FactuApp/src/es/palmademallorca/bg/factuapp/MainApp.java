package es.palmademallorca.bg.factuapp;

import java.io.IOException;

import es.palmademallorca.bg.factuapp.view.ScreensController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainApp extends Application {

	public static String loginID = "main";
    public static String loginFile = "Login.fxml";

    public static String registroID = "registro";
    public static String registroFile = "Registro.fxml";

    public static String contenidoID = "contenido";
    public static String contenidoFile = "Contenido.fxml";

    public static String mantenimientoProductoID = "producto";
    public static String mantenimientoProductoFile = "Producto.fxml";

    public static String mantenimientoClientesID = "cliente";
    public static String mantenimientoClientesFile = "Cliente.fxml";


	// private Stage primaryStage;



	// private BorderPane rootLayout;

	@Override
	public void start(Stage primaryStage) {

		ScreensController mainContainer = new ScreensController();
        mainContainer.loadScreen(MainApp.loginID, MainApp.loginFile);
        mainContainer.loadScreen(MainApp.contenidoID, MainApp.contenidoFile);
        mainContainer.loadScreen(MainApp.mantenimientoProductoID, MainApp.mantenimientoProductoFile);
        mainContainer.loadScreen(MainApp.mantenimientoClientesID, MainApp.mantenimientoClientesFile);

         mainContainer.setScreen(MainApp.loginID);

        Group root = new Group();
        root.getChildren().addAll(mainContainer);
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();


	/*	this.primaryStage=primaryStage;
		this.primaryStage.setTitle("Factu Aplicación");
		initRootLayout();


		showClient(); */
	}

	/**
     * Initializes the root layout.
     */
  /*  public void initRootLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();

            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }  */

    /**
     * Shows the person overview inside the root layout.
     */
    /* public void showClient() {
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/Cliente.fxml"));
            AnchorPane personOverview = (AnchorPane) loader.load();

            // Set person overview into the center of root layout.
            rootLayout.setCenter(personOverview);
        } catch (IOException e) {
            e.printStackTrace();
        }
    } */




	public static void main(String[] args) {
		launch(args);
	}
}
