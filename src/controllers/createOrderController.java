package controllers;

import Utils.ConsolePrinter;
import application.Main;
import homework2.Order;
import homework2.Restaurant;
import homework2.Services;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class createOrderController {

    @FXML
    private TextField basePrice;

    @FXML
    private TextField customerCode;

    @FXML
    private TextField orderDate;

    @FXML
    private TextField restauranCode;

    @FXML
    void create(ActionEvent event) {
    	try {
    		Integer Code = Integer.parseInt(customerCode.getText());
        	Integer resCode = Integer.parseInt(restauranCode.getText());
        	Restaurant res = Services.findRestaurant(resCode, Main.DDB.getRestaurants());
        	Double price = Double.parseDouble(basePrice.getText());
        	Order order = new Order(Code, res, price, orderDate.getText());
    	} catch (Exception e) {
			ConsolePrinter.printError(e);
		}
    }
    @FXML
    void exit(ActionEvent event) {
    	Main.goBackScene();
    }

}
