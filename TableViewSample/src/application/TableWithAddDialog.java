package application;

import java.util.function.Function;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;

public class TableWithAddDialog extends Application {

	@Override
	public void start(Stage primaryStage) {
		TableView<Person> table = new TableView<>();
		
		table.getColumns().add(column("First Name", Person::firstNameProperty));
		table.getColumns().add(column("Last Name", Person::lastNameProperty));
		table.getColumns().add(column("Email", Person::emailProperty));
		
		
		TextField firstNameTextField = new TextField();
		TextField lastNameTextField = new TextField();
		TextField emailTextField = new TextField();
		
		GridPane editPane = new GridPane();
		editPane.setAlignment(Pos.CENTER);
		editPane.setPadding(new Insets(10));
		editPane.setHgap(5);
		editPane.setVgap(10);
		ColumnConstraints leftCol = new ColumnConstraints();
		ColumnConstraints rightCol = new ColumnConstraints();
		leftCol.setHalignment(HPos.RIGHT);
		rightCol.setHgrow(Priority.ALWAYS);
		editPane.getColumnConstraints().addAll(leftCol, rightCol);
		
                editPane.addRow(0, new Label("First Name:"), firstNameTextField);
                editPane.addRow(1, new Label("Last Name:"), lastNameTextField);
                editPane.addRow(2, new Label("Email:"), emailTextField);
        
		Button addButton = new Button("New...");

		DialogPane dialogPane = new DialogPane();
		dialogPane.setContent(editPane);
		dialogPane.setHeaderText("Add new contact");
		
		Dialog<Person> dialog = new Dialog<Person>();
		dialog.setDialogPane(dialogPane);
		dialog.setResultConverter(buttonType -> buttonType == ButtonType.OK 
		        ? new Person(getAndClear(firstNameTextField), getAndClear(lastNameTextField), getAndClear(emailTextField))
		        : null);
		dialogPane.getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
		
		addButton.setOnAction(e -> 
		        dialog.showAndWait().ifPresent(table.getItems()::add));
		
		BorderPane.setAlignment(addButton, Pos.CENTER);
		BorderPane.setMargin(addButton, new Insets(10));
		
		BorderPane root = new BorderPane(table, null, null, addButton, null);
		Scene scene = new Scene(root, 600, 400);
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}
	
	private String getAndClear(TextField tf) {
	    String result = tf.getText();
	    tf.setText("");
	    return result ;
	}
	
	private <S,T> TableColumn<S,T> column(String title, Function<S, ObservableValue<T>> property) {
	    TableColumn<S,T> col = new TableColumn<>(title);
	    col.setCellValueFactory(cellData -> property.apply(cellData.getValue()));
	    return col ;
	}
	
	public static class Person {
	    private final StringProperty firstName = new SimpleStringProperty() ;
	    private final StringProperty lastName = new SimpleStringProperty() ;
	    private final StringProperty email = new SimpleStringProperty() ;
	    
	    public Person(String firstName, String lastName, String email) {
	        setFirstName(firstName);
	        setLastName(lastName);
	        setEmail(email);
	    }

        public final StringProperty firstNameProperty() {
            return this.firstName;
        }

        public final java.lang.String getFirstName() {
            return this.firstNameProperty().get();
        }

        public final void setFirstName(final java.lang.String firstName) {
            this.firstNameProperty().set(firstName);
        }

        public final StringProperty lastNameProperty() {
            return this.lastName;
        }

        public final java.lang.String getLastName() {
            return this.lastNameProperty().get();
        }

        public final void setLastName(final java.lang.String lastName) {
            this.lastNameProperty().set(lastName);
        }

        public final StringProperty emailProperty() {
            return this.email;
        }

        public final java.lang.String getEmail() {
            return this.emailProperty().get();
        }

        public final void setEmail(final java.lang.String email) {
            this.emailProperty().set(email);
        }
	    
	    
	}

	public static void main(String[] args) {
		launch(args);
	}
}
