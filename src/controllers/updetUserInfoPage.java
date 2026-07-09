package controllers;

import MyExceptions.InvalidPropertyException;
import Utils.ConsolePrinter;
import application.Main;
import homework2.Customer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import controllers.CustomerUserPageController;

public class updetUserInfoPage {
    @FXML
    private TextField text;

    @FXML
    void adress(ActionEvent event) {
    	try {
			CustomerUserPageController.customer.setAddress(text.getText());
			ConsolePrinter.inform("updated customer address to " + text.getText());
			Main.goBackScene();
		}  catch (InvalidPropertyException e) {
			ConsolePrinter.printError(e);
		}
    }

    @FXML
    void email(ActionEvent event) {
    	try {
			CustomerUserPageController.customer.setEmail(text.getText());
			ConsolePrinter.inform("updated customer email to " + text.getText());
			Main.goBackScene();
		} catch (InvalidPropertyException e) {
			ConsolePrinter.printError(e);
		}
    }

    

    @FXML
    void phonNum(ActionEvent event) {
    	try {
			CustomerUserPageController.customer.setPhoneNumber(text.getText());
			ConsolePrinter.inform("updated customer phone number to " + text.getText());
			Main.goBackScene();
		}  catch (InvalidPropertyException e) {
			ConsolePrinter.printError(e);
		}
    }

    @FXML
    void exit(ActionEvent event) {
    	Main.goBackScene();
    }
    
}
