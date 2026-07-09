package controllers;


import MyExceptions.InvalidPropertyException;
import Utils.ConsolePrinter;
import application.Main;
import homework2.PremiumRestaurant;
import homework2.Restaurant;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;

public class createPremiumRestaurantController {

    @FXML
    private TextField deliveryFeeText;

    @FXML
    private CheckBox isOpenCheckbox;

    @FXML
    private TextField kitchenTypeText;

    @FXML
    private TextField minOrderValueText;

    @FXML
    private TextField nameText;

    @FXML
    private TextField orderFeePercentageText;

    @FXML
    private TextField ratingText;

    @FXML
    void create(ActionEvent event) {
    	try {
    		Double minOrderValue = Double.parseDouble(minOrderValueText.getText());
        	Double orderFeePercentage = Double.parseDouble(orderFeePercentageText.getText());
        	
    		Double deliveryFee = Double.parseDouble(deliveryFeeText.getText());
        	Double rating = Double.parseDouble(ratingText.getText());
        	Boolean isOpen = isOpenCheckbox.isSelected();
        	PremiumRestaurant res = new PremiumRestaurant(nameText.getText(), kitchenTypeText.getText(), rating, isOpen, deliveryFee, minOrderValue,orderFeePercentage);
        	Main.DDB.addRestaurant(res);
        	ConsolePrinter.inform("created and added restaurant");
        	Main.goBackScene();
    	} catch (NumberFormatException e) {
			ConsolePrinter.printError("could not parse values, make sure minOrderValue, orderFeePercentage, deliveryFee and rating are all numbers");
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
