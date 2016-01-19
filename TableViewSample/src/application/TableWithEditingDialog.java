package application;

import java.util.List;
import java.util.Random;
import java.util.function.Function;
import java.util.regex.Pattern;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ReadOnlyDoubleWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class TableWithEditingDialog extends Application {

    private void showUpdateQuantitiesDialog(Stage primaryStage, List<Bill> billsToUpdate) {
        
        Stage dialog = new Stage();
        Button okButton = new Button("OK");
        TextField quantityTF = new TextField();

        okButton.setOnAction(evt -> {
            billsToUpdate.forEach(bill -> 
                bill.setQuantity(Integer.parseInt(quantityTF.getText())));
            dialog.hide();
        });
        
        Label title = new Label("Update quantity for "+billsToUpdate.size()+" bill(s)");
        Button cancelButton = new Button("Cancel");
        HBox buttons = new HBox(5, okButton, cancelButton);
        VBox root = new VBox(10, title, quantityTF, buttons);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(10));
        Scene scene = new Scene(root);
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.initOwner(primaryStage);
        dialog.setScene(scene);
        
        cancelButton.setOnAction(evt -> dialog.hide());
        
        Pattern quantityPattern = Pattern.compile("[1-9][0-9]*");
        okButton.disableProperty().bind(Bindings.createBooleanBinding(
                ()-> ! quantityPattern.matcher(quantityTF.getText()).matches(),
                quantityTF.textProperty()));
        dialog.show();
    }
    
	@Override
	public void start(Stage primaryStage) {
		TableView<Bill> table = new TableView<>();
		table.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		table.getColumns().add(column("Item", bill -> new ReadOnlyStringWrapper(bill.getItem()), Function.identity()));
		table.getColumns().add(column("Quantity", Bill::quantityProperty, Number::toString));
		table.getColumns().add(column("Unit price (no VAT)", Bill::rateWithoutVATProperty, r -> String.format("£%.2f", r.doubleValue())));
		table.getColumns().add(column("Total price (incl VAT)", Bill::totalWithVATProperty, t -> String.format("£%.2f", t.doubleValue())));
		
		Random rng = new Random();
		for (int i = 1; i <= 10 ; i++) {
		    Bill bill = new Bill("Item "+i, rng.nextInt(5)+1, rng.nextInt(10000)/100.0);
		    table.getItems().add(bill);
		}
		
		Button updateQuantities = new Button("Update Quantities");
		updateQuantities.setOnAction(e -> 
		    showUpdateQuantitiesDialog(primaryStage, table.getSelectionModel().getSelectedItems())
		);
		
		updateQuantities.disableProperty().bind(Bindings.isEmpty(table.getSelectionModel().getSelectedItems()));
		
		HBox controls = new HBox(5, updateQuantities);
		controls.setAlignment(Pos.CENTER);
		controls.setPadding(new Insets(5));
		
		BorderPane root = new BorderPane(table, null, null, controls, null);
		Scene scene = new Scene(root, 600, 600);
		primaryStage.setScene(scene);
		primaryStage.show();
	}


	
	public static <S,T> TableColumn<S,T> column(String title, Function<S, ObservableValue<T>> property, Function<T, String> formatter) {
	    TableColumn<S,T> col = new TableColumn<>(title);
	    col.setCellValueFactory(cellData -> property.apply(cellData.getValue()));
	    col.setCellFactory(tc -> new TableCell<S,T>() {
	        @Override
	        public void updateItem(T item, boolean empty) {
	            super.updateItem(item, empty);
	            if (empty) {
	                setText(null);
	            } else {
	                setText(formatter.apply(item));
	            }
	        }
	    });
	    return col ;
	}
	
	public static class Bill {
	    
	    private static final double VAT_RATE = 0.20 ;
	    
	    private final String item ;
	    private final IntegerProperty quantity = new SimpleIntegerProperty();
	    private final DoubleProperty rateWithoutVAT = new SimpleDoubleProperty();
	    private final ReadOnlyDoubleWrapper totalWithVAT = new ReadOnlyDoubleWrapper() ;
	    
	    public Bill(String item, int quantity, double rateWithoutVAT) {
	        this.item = item ;
	        setQuantity(quantity);
	        setRateWithoutVAT(rateWithoutVAT);
	        totalWithVAT.bind(this.quantity.multiply(this.rateWithoutVAT).multiply(1+VAT_RATE));
	    }
	    
	    public String getItem() {
	        return item ;
	    }

        public final IntegerProperty quantityProperty() {
            return this.quantity;
        }
        

        public final int getQuantity() {
            return this.quantityProperty().get();
        }
        

        public final void setQuantity(final int quantity) {
            this.quantityProperty().set(quantity);
        }
        

        public final DoubleProperty rateWithoutVATProperty() {
            return this.rateWithoutVAT;
        }
        

        public final double getRateWithoutVAT() {
            return this.rateWithoutVATProperty().get();
        }
        

        public final void setRateWithoutVAT(final double rateWithoutVAT) {
            this.rateWithoutVATProperty().set(rateWithoutVAT);
        }
        

        public final javafx.beans.property.ReadOnlyDoubleProperty totalWithVATProperty() {
            return this.totalWithVAT.getReadOnlyProperty();
        }
        

        public final double getTotalWithVAT() {
            return this.totalWithVATProperty().get();
        }
        
	    
	    
	}

	public static void main(String[] args) {
		launch(args);
	}
}