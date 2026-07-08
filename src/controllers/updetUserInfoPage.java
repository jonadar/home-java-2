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
		}  catch (InvalidPropertyException e) {
			ConsolePrinter.printError(e);
		}
    }

    @FXML
    void email(ActionEvent event) {
    	try {
			CustomerUserPageController.customer.setEmail(text.getText());
		} catch (InvalidPropertyException e) {
			ConsolePrinter.printError(e);
		}
    }

    @FXML
    void exit(ActionEvent event) {
    	Main.goBackScene();
    }

    @FXML
    void phonNum(ActionEvent event) {
    	try {
			CustomerUserPageController.customer.setPhoneNumber(text.getText());
		}  catch (InvalidPropertyException e) {
			ConsolePrinter.printError(e);
		}
    }


}
