package controllers;


import MyExceptions.InvalidPropertyException;
import Utils.ConsolePrinter;
import application.Main;
import homework2.Customer;
import homework2.DeliveryDataBase;
import homework2.Services;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class createCustomerController {

    @FXML
    private TextField address;

    @FXML
    private TextField emailText;

    @FXML
    private TextField firstNameText;

    @FXML
    private TextField lastNameText;

    @FXML
    private TextField phoneNumberText;

    @FXML
    private TextField remainingCreditText;

    @FXML
    void create(ActionEvent event) {
    	try {
    		// 	public Customer(String firstName, String lastName, String address, String phoneNumber, String email, double remainingCredit) {
    		Double remainingCredit = Double.parseDouble(remainingCreditText.getText());
        	Customer customer = new Customer(firstNameText.getText(), lastNameText.getText(), address.getText(), phoneNumberText.getText(), emailText.getText(), remainingCredit);
        	Main.DDB.addCustomer(customer);
        	ConsolePrinter.inform("created new customer");
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
    
}
