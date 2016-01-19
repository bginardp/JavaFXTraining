package application;

import java.util.function.Function;

import javafx.application.Application;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.converter.NumberStringConverter;

public class TableViewSample extends Application {

 	private final TableView<Person> table = new TableView<>();
    private final ObservableList<Person> data =
            FXCollections.observableArrayList(
            new Person("Jacob", "Smith", "jacob.smith@example.com",100.00),
            new Person("Isabella", "Johnson", "isabella.johnson@example.com",200.00),
            new Person("Ethan", "Williams", "ethan.williams@example.com",300.00),
            new Person("Emma", "Jones", "emma.jones@example.com",400.00),
            new Person("Michael", "Brown", "michael.brown@example.com",500.00));



    final HBox hb = new HBox();
    final HBox hb1 = new HBox();
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {

// 1 crear Scene
        Scene scene = new Scene(new Group());
        stage.setTitle("Table View Sample");
        stage.setWidth(550);
        stage.setHeight(550);
// 2 crear Label
        final Label label = new Label("Address Book");
        label.setFont(new Font("Arial", 20));

        table.setEditable(true);

        Callback<TableColumn<Person, String>,
            TableCell<Person, String>> cellFactory
                = (TableColumn<Person, String> p) -> new EditingCell();

// 3 crear les columnes de la taula (com a contructor li pasem el Label de la columna)
// 3a sense utilitzar lambdas
     /*   TableColumn<Person, String> firstNameCol =
            new TableColumn<>("First Name");
        TableColumn<Person, String> lastNameCol =
            new TableColumn<>("Last Name");
        TableColumn<Person, String> emailCol =
            new TableColumn<>("Email");
        TableColumn<Person, Number> impCol =
                new TableColumn<>("Import");
     */
        // 3b utilitzant lambdas
        TableColumn<Person, String> firstNameCol=column("First Name", Person::firstNameProperty);
        TableColumn<Person, String> lastNameCol =column("Last Name", Person::lastNameProperty);
        TableColumn<Person, String> emailCol=column("Email", Person::emailProperty);
        TableColumn<Person, Number> impCol=column("Importe", Person::impProperty);


// 4 ajustem les propietats de la columna firsName
        firstNameCol.setMinWidth(100);
        firstNameCol.setCellValueFactory(
            new PropertyValueFactory<>("firstName"));
        firstNameCol.setCellFactory(cellFactory);
        firstNameCol.setOnEditCommit(
            (CellEditEvent<Person, String> t) -> {
                ((Person) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())
                        ).setFirstName(t.getNewValue());
        });

// 5 ajustem les propietats de la columna lastName

        lastNameCol.setMinWidth(100);
        lastNameCol.setCellValueFactory(
            new PropertyValueFactory<>("lastName"));
        lastNameCol.setCellFactory(cellFactory);
        lastNameCol.setOnEditCommit(
            (CellEditEvent<Person, String> t) -> {
                ((Person) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())
                        ).setLastName(t.getNewValue());
        });
     // 6 ajustem les propietats de la columna emal
        emailCol.setMinWidth(200);
        emailCol.setCellValueFactory(
            new PropertyValueFactory<>("email"));
        emailCol.setCellFactory(cellFactory);
        emailCol.setOnEditCommit(
            (CellEditEvent<Person, String> t) -> {
                ((Person) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())
                        ).setEmail(t.getNewValue());
        });

     // 7 ajustem les propietats de la columna import

//7a visualitzacio
        impCol.setMinWidth(100);
        impCol.setCellValueFactory(
                new PropertyValueFactory<Person, Number>("imp"));

      //7b

     /*   impCol.setCellFactory(col ->  new TableCell<Person, Number>() {
            @Override
            public void updateItem(Number imp, boolean empty) {
                super.updateItem(imp, empty);
                if (empty) {
                    setText(null);
                } else {
                    setText(String.format("%.2f", imp));
                }
            }
        });
*/
        impCol.setCellFactory(TextFieldTableCell.forTableColumn(new NumberStringConverter()));
        impCol.setOnEditCommit(
            new EventHandler<CellEditEvent<Person, Number>>() {
                @Override
                public void handle(CellEditEvent<Person, Number> t) {
                    ((Person) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())
                            ).setImp(t.getNewValue());
                }
            }
        );

        table.setItems(data);
        table.getColumns().addAll(firstNameCol, lastNameCol, emailCol, impCol);

        final TextField addFirstName = new TextField();
        addFirstName.setPromptText("First Name");
        addFirstName.setMaxWidth(firstNameCol.getPrefWidth());
        final TextField addLastName = new TextField();
        addLastName.setMaxWidth(lastNameCol.getPrefWidth());
        addLastName.setPromptText("Last Name");

        final TextField addEmail = new TextField();
        addEmail.setMaxWidth(emailCol.getPrefWidth());
        addEmail.setPromptText("Email");

        final NumberTextField addImp = new NumberTextField();
        addImp.setMaxWidth(impCol.getPrefWidth());
        addImp.setPromptText("Import");

        final Button addButton = new Button("Add");
        addButton.setOnAction((ActionEvent e) -> {
            data.add(new Person(
                    addFirstName.getText(),
                    addLastName.getText(),
                    addEmail.getText(),
                    Double.parseDouble(addImp.getText())));
            addFirstName.clear();
            addLastName.clear();
            addEmail.clear();
        });

        hb.getChildren().addAll(addFirstName, addLastName, addEmail,addImp, addButton);
        hb.setSpacing(3);


        final Label sumaLabel = new Label("Importe");
        final NumberTextField suma = new NumberTextField();
        suma.setMaxWidth(impCol.getPrefWidth());
suma.setEditable(false);
        hb1.getChildren().addAll(sumaLabel, suma);
        hb1.setSpacing(3);
        hb1.alignmentProperty().setValue(Pos.BOTTOM_RIGHT);

        final VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 10, 10, 10));
        vbox.getChildren().addAll(label, table, hb, hb1);

        ((Group) scene.getRoot()).getChildren().addAll(vbox);

        stage.setScene(scene);
        stage.show();
    }

    private <S,T> TableColumn<S,T> column(String title, Function<S, ObservableValue<T>> property) {
	    TableColumn<S,T> col = new TableColumn<>(title);
	    col.setCellValueFactory(cellData -> property.apply(cellData.getValue()));
	    return col ;
	}


    public static class Person {

        private final SimpleStringProperty firstName;
        private final SimpleStringProperty lastName;
        private final SimpleStringProperty email;
        private final DoubleProperty impProperty;

        private Person(String fName, String lName, String email, Double imp) {
            this.firstName = new SimpleStringProperty(fName);
            this.lastName = new SimpleStringProperty(lName);
            this.email = new SimpleStringProperty(email);
            this.impProperty = new SimpleDoubleProperty(imp);
        }

        public String getFirstName() {
            return firstName.get();
        }

        public void setFirstName(String fName) {
            firstName.set(fName);
        }

        public String getLastName() {
            return lastName.get();
        }

        public void setLastName(String fName) {
            lastName.set(fName);
        }

        public String getEmail() {
            return email.get();
        }

        public void setEmail(String fName) {
            email.set(fName);
        }

        public final StringProperty firstNameProperty() {
            return this.firstName;
        }
        public final StringProperty lastNameProperty() {
            return this.lastName;
        }
        public final StringProperty emailProperty() {
            return this.lastName;
        }
        public final DoubleProperty impProperty() {
            return this.impProperty;
        }
		public Number getImp() {
			return impProperty.getValue();
		}
		public void setImp(Number imp) {
			impProperty.set(imp.doubleValue());
		}

    }

    class EditingCell extends TableCell<Person, String> {

        private TextField textField;

        public EditingCell() {
        }

        @Override
        public void startEdit() {
            if (!isEmpty()) {
                super.startEdit();
                createTextField();
                setText(null);
                setGraphic(textField);
                textField.selectAll();
            }
        }

        @Override
        public void cancelEdit() {
            super.cancelEdit();

            setText((String) getItem());
            setGraphic(null);
        }

        @Override
        public void updateItem(String item, boolean empty) {
            super.updateItem(item, empty);

            if (empty) {
                setText(null);
                setGraphic(null);
            } else {
                if (isEditing()) {
                    if (textField != null) {
                        textField.setText(getString());
                    }
                    setText(null);
                    setGraphic(textField);
                } else {
                    setText(getString());
                    setGraphic(null);
                }
            }
        }

        private void createTextField() {
            textField = new TextField(getString());
            textField.setMinWidth(this.getWidth() - this.getGraphicTextGap()* 2);
            textField.focusedProperty().addListener(
                (ObservableValue<? extends Boolean> arg0,
                Boolean arg1, Boolean arg2) -> {
                    if (!arg2) {
                        commitEdit(textField.getText());
                    }
            });
        }

        private String getString() {
            return getItem() == null ? "" : getItem().toString();
        }
    }


}
