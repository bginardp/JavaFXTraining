package es.palmademallorca.bg.factuapp;

import java.io.InputStream;
import java.util.jar.Attributes;
import java.util.jar.Manifest;

import es.palmademallorca.bg.common.controls.ScreensController;
import es.palmademallorca.bg.common.model.IBaseFXApp;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApp extends Application implements IBaseFXApp {

	private static final ProjectInfo projectInfo = new ProjectInfo();

	public static final String loginID = "main";
    public static final String loginFile = "Login.fxml";

    public static final String registroID = "registro";
    public static final String registroFile = "Registro.fxml";

    public static final String contenidoID = "contenido";
    public static final String contenidoFile = "Contenido.fxml";

    public static final String mantenimientoProductoID = "producto";
    public static final String mantenimientoProductoFile = "Producto.fxml";

    public static final String mantenimientoClientesID = "cliente";
    public static final String mantenimientoClientesFile = "Cliente.fxml";

    public static final String FACTURASID = "factura";
    public static final String FACTURASFILE = "Factura.fxml";

	/**
	 * anar afegint les pantalles a
	 */
	@Override
	public void start(Stage primaryStage) {

		ScreensController mainController = new ScreensController();
        mainController.loadScreen(MainApp.loginID, MainApp.loginFile);
        mainController.loadScreen(MainApp.contenidoID, MainApp.contenidoFile);
        mainController.loadScreen(MainApp.mantenimientoProductoID, MainApp.mantenimientoProductoFile);
        mainController.loadScreen(MainApp.mantenimientoClientesID, MainApp.mantenimientoClientesFile);
        mainController.loadScreen(MainApp.FACTURASID, MainApp.FACTURASFILE);

        mainController.setScreen(MainApp.loginID);

        //Group root = new Group();
        //root.getChildren().addAll(mainController);
        Scene scene = new Scene(mainController);
        primaryStage.setScene(scene);
        primaryStage.show();
	}

	

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public String getApplicationName() {
		// TODO Auto-generated method stub
		return "Facturación";
	}

	@Override
	public String getApplicationDescription() {
		// TODO Auto-generated method stub
		return "Facturación de coña";
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
