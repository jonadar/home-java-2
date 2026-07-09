package controllers;
import MyExceptions.InvalidPropertyException;
import Utils.ConsolePrinter;
import application.Main;
import homework2.Restaurant;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;

public class createRestaurantController {

    @FXML
    private TextField deliveryFeeText;

    @FXML
    private CheckBox isOpenCheckbox;

    @FXML
    private TextField kitchenTypeText;

    @FXML
    private TextField nameText;

    @FXML
    private TextField ratingText;

    @FXML
    void create(ActionEvent event) {
    	try {
    		Double deliveryFee = Double.parseDouble(deliveryFeeText.getText());
        	Double rating = Double.parseDouble(ratingText.getText());
        	Boolean isOpen = isOpenCheckbox.isSelected();
        	Restaurant res = new Restaurant(nameText.getText(), kitchenTypeText.getText(), rating, isOpen,  deliveryFee);
        	Main.DDB.addRestaurant(res);
        	ConsolePrinter.inform("created and added restaurant");
        	Main.goBackScene();
    	} catch (NumberFormatException e) {
			ConsolePrinter.printError("could not parse values, make sure deliveryFee and rating are all numbers");
		} catch (InvalidPropertyException e) {
			ConsolePrinter.printError(e);
		} catch (Exception e) {
			ConsolePrinter.printError(e);
    	}
    }
    
    @FXML
    void exit(ActionEvent event) {
    	Main.goBackScene();
    }
}
