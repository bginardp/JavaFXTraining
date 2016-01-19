package es.palmademallorca.bg.common.javafx;
import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;

public class Dialegs {
/**
 * 
 * @param title
 * @param header
 * @param content
 * @return if (result.get() == ButtonType.OK){
 */
	public static Optional<ButtonType> confirma(String title, String header, String content) {
		
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle(title);
		alert.setHeaderText(header);
		alert.setContentText(content);
    	Optional<ButtonType> result=alert.showAndWait();
		return result;	
	}
	
	public static void informa(String title, String header, String content) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle(title);
		alert.setHeaderText(header);
		alert.setContentText(content);
		alert.show();
			
	}
}
