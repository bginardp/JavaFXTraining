package es.palmademallorca.bg.factuapp;

import java.io.InputStream;
import java.util.jar.Attributes;
import java.util.jar.Manifest;

import es.palmademallorca.bg.common.model.IBaseFXApp;
import es.palmademallorca.bg.factuapp.view.ScreensController;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApp extends Application implements IBaseFXApp {

	private static final ProjectInfo projectInfo = new ProjectInfo();

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

	@Override
	public String getApplicationName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getApplicationDescription() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getProjectName() {
		return "ControlsFX";
	}

	@Override
	public String getProjectVersion() {
		// TODO Auto-generated method stub
		return projectInfo.getVersion();
	}

	private static class ProjectInfo {

		private String version;


		public ProjectInfo() {

			InputStream s = getClass().getClassLoader().getResourceAsStream(
					"META-INF/MANIFEST.MF");

			try {
				Manifest manifest = new Manifest(s);
				Attributes attr = manifest.getMainAttributes();
				version = attr.getValue("Implementation-Version");
			} catch (Throwable e) {
				System.out.println("Unable to load project version for FactuControlsFX "
				        + "Factu project as the manifest file can't be read "
				        + "or the Implementation-Version attribute is unavailable.");
				version = "";
			}
		}

		public String getVersion() {
			return version;
		}
	}


}
