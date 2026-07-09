package controllers;

import MyExceptions.InvalidPropertyException;
import Utils.ConsolePrinter;
import application.Main;
import homework2.Customer;
import homework2.Order;
import homework2.RestAdmin;
import homework2.Restaurant;
import homework2.Services;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class createOrderController {
	public static Object referer = null;
	
    @FXML
    private TextField basePrice;

    @FXML
    private TextField customerCode;

    @FXML
    private TextField orderDate;

    @FXML
    private TextField restauranCode;
    
    @FXML
    private Label customerCodeLable;

    @FXML
    void create(ActionEvent event) {
    	try {
    		Integer code = Integer.parseInt(customerCode.getText());
    		Double price = Double.parseDouble(basePrice.getText());
        	Integer resCode = Integer.parseInt(restauranCode.getText());
        	Restaurant res = Services.findRestaurant(resCode, Main.DDB.getRestaurants());
        	if (referer instanceof RestAdmin && !((RestAdmin) referer).getRestaurants().contains(res)) {
        		throw new Exception("restaurant is not owned by this admin");
        	}
        	Order order = new Order(code, res, price, orderDate.getText());
        	Main.DDB.addOrder(order);
        	ConsolePrinter.inform("created new order");
        	Main.goBackScene();
    	}  catch (InvalidPropertyException e) {
			ConsolePrinter.printError(e);
		} catch (Exception e) {
			ConsolePrinter.printError(e);
    	}

    }
    @FXML
    void exit(ActionEvent event) {
    	Main.goBackScene();
    }
    
    @FXML
    void initialize() {
    	// if customer no need to insert customer code
    	if(referer instanceof Customer) {
    		customerCode.setVisible(false);
    		customerCodeLable.setVisible(false);
    		customerCode.setText(((Customer) referer).getCustomerCode() + "");
    	} else {
    		customerCode.setVisible(true);
    		customerCodeLable.setVisible(true);
    	}
    }

}
