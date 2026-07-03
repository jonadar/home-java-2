package controllers;
import Utils.ConsolePrinter;
import application.Main;
import homework2.Restaurant;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class createRestaurantController {

    @FXML
    private TextField deliveryFeeText;

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
    		Double deliveryFee = Double.parseDouble(deliveryFeeText.getText());
        	Double rating = Double.parseDouble(ratingText.getText());
        	Boolean isOpen = Boolean.parseBoolean(isOpenText.getText());
        	Restaurant res = new Restaurant(nameText.getText(), kitchenTypeText.getText(), rating, isOpen,  deliveryFee);
        	Main.DDB.addRestaurant(res);
    	} catch (Exception e) {
			ConsolePrinter.printError(e);
    	}
    	
    }

}
