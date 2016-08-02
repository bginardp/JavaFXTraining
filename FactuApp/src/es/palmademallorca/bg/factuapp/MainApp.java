package es.palmademallorca.bg.factuapp;

import java.io.IOException;
import java.io.InputStream;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.jar.Attributes;
import java.util.jar.Manifest;

import es.palmademallorca.bg.common.model.IBaseFXApp;
import es.palmademallorca.bg.common.util.properties.PropertiesCache;
import es.palmademallorca.bg.factuapp.model.jpa.Ejercicio;
import es.palmademallorca.bg.factuapp.model.jpa.Empresa;
import es.palmademallorca.bg.factuapp.view.RootLayoutController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

public class MainApp extends Application implements IBaseFXApp {
	
	private Stage primaryStage;
	private BorderPane rootLayout;
	// empresa cargada
    private Empresa empresa;
    // ejercicio cargado
    private Ejercicio ejercicio;
    private Long idFacturaSeleccionada;
    private static final ProjectInfo projectInfo = new ProjectInfo();
    
    
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
				System.out.println("Unable to load project version for ControlsFX "
				        + "samples project as the manifest file can't be read "
				        + "or the Implementation-Version attribute is unavailable.");
				version = "";
			}
		}

		public String getVersion() {
			return version;
		}
	}

    
    
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
			Locale locale = new Locale("es","ES");
			Locale.setDefault(locale);
			
			NumberFormat currenyFormat = NumberFormat.getCurrencyInstance(locale);
			
			
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/RootLayout.fxml"));
			rootLayout = (BorderPane) loader.load();
            rootLayout.setPrefSize(1124, 768);
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

	@Override
	public String getApplicationName() {
		// TODO Auto-generated method stub
		return "Facturación";
	}

	@Override
	public String getApplicationDescription() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getProjectName() {
		return "Facturación";
		
	}

	@Override
	public String getProjectVersion() {
		// TODO Auto-generated method stub
		return projectInfo.getVersion();
	}

	public Long getIdFacturaSeleccionada() {
		return idFacturaSeleccionada;
	}

	public void setIdFacturaSeleccionada(Long idFacturaSeleccionada) {
		this.idFacturaSeleccionada = idFacturaSeleccionada;
	}
}
