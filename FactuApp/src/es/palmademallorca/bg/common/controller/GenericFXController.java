package es.palmademallorca.bg.common.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javax.persistence.EntityManager;

import es.palmademallorca.bg.factuapp.MainApp;
import es.palmademallorca.bg.factuapp.model.managers.EntityManagerProvider;
import javafx.fxml.Initializable;

public abstract class GenericFXController implements Initializable, IGenericController {
	private static EntityManager em = EntityManagerProvider.getProvider().getEntityManager();
	private MainApp mainApp;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

	}


	public MainApp getMainApp() {
		return mainApp;
	}
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
		postInitialize();
	}



	public static EntityManager getEm() {
		return em;
	}
	public static void setEm(EntityManager em) {
		GenericFXController.em = em;
	}


}
