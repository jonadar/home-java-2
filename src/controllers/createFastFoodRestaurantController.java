package controllers;


import Utils.ConsolePrinter;
import application.Main;
import homework2.FastFoodRestaurant;
import homework2.Restaurant;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class createFastFoodRestaurantController {

    @FXML
    private TextField averageCookTimeText;

    @FXML
    private TextField deliveryFeeText;

    @FXML
    private TextField fastDeliveryFeeText;

    @FXML
    private TextField isOpenText;

    @FXML
    private TextField kitchenTypeText;

    @FXML
    private TextField nameText;

    @FXML
    private TextField ratingText;

    @FXML
    void create(ActionEvent event) {
    	try {
    		
    		Double averageCookTime = Double.parseDouble(averageCookTimeText.getText());
    		Double fastDeliveryFee = Double.parseDouble(fastDeliveryFeeText.getText());

    		Double deliveryFee = Double.parseDouble(deliveryFeeText.getText());
        	Double rating = Double.parseDouble(ratingText.getText());
        	Boolean isOpen = Boolean.parseBoolean(isOpenText.getText());
        	FastFoodRestaurant res = new FastFoodRestaurant(nameText.getText(), kitchenTypeText.getText(), rating, isOpen,  deliveryFee, averageCookTime, fastDeliveryFee);
        	Main.DDB.addRestaurant(res);
    	} catch (Exception e) {
			ConsolePrinter.printError(e);
    	}

    }
    @FXML
    void exit(ActionEvent event) {
    	Main.goBackScene();
    }
}
