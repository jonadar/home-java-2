package controllers;

import MyExceptions.CustomerNotFoundException;
import Utils.ConsolePrinter;
import application.Main;
import enums.UserType;
import homework2.Customer;
import homework2.Services;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class loginController {

    @FXML
    private TextField field1;

    @FXML
    private TextField field2;
    
    @FXML
    private Label labelField1;

    @FXML
    private Label labelField2;

    @FXML
    private HBox row1;

    @FXML
    private HBox row2;

    @FXML
    private ComboBox<UserType> userType;

    @FXML
    void changeUserType(ActionEvent event) {
    		UserType selected = userType.getSelectionModel().getSelectedItem();
    		
    		// set fields to match user type trying to log-in
    		switch (selected) {
			case UserType.Admin, UserType.RestAdmin:
				row2.setVisible(true);
				labelField1.setText("username");				
				break;
			case UserType.Customer:
				labelField1.setText("code");
				row2.setVisible(false);
				break;
			case UserType.Rider:
				labelField1.setText("id");
				row2.setVisible(false);
				break;
		}
    }

    @FXML
    void login(ActionEvent event) {
    		UserType selected = userType.getSelectionModel().getSelectedItem();
    		
	    	switch (selected) {
			case UserType.Admin:
				try {
					String userName = field1.getText();
					String pass = field2.getText();
					
					if(Main.DDB.getSystemAdministrator().login(userName, pass) == false) {
						throw new Exception("username or password incorrect.");
					}
					
					Parent root = FXMLLoader.load(getClass().getResource("/adminPages/adminPage.fxml"));
					
					Scene scene = new Scene(root);
					
					Main.stage.setScene(scene);
				} catch (Exception e) {
					ConsolePrinter.printError(e);
				}
				break;
			case UserType.RestAdmin:				
				break;
			case UserType.Customer:
				try {
					int code = Integer.parseInt(field1.getText());
					
					Customer customer = Services.findCustomer(code, Main.DDB.getCustomers());
					
					// customer.menu(Main.DDB);
					Parent root = FXMLLoader.load(getClass().getResource("/userPages/customerUserPage.fxml"));
					
					Scene scene = new Scene(root);
					
					Main.stage.setScene(scene);
				} catch (CustomerNotFoundException e) {
					ConsolePrinter.printError("could not find user with that code");
				} catch (NumberFormatException e) {
					ConsolePrinter.printError("code must be valid integer");
				} catch (Exception e) {
					ConsolePrinter.printError(e);
				}
				break;
			case UserType.Rider:
				break;
		}
    }

    @FXML
    void quit(ActionEvent event) {
    		// check how to do this
    }
    
    @FXML
    void initialize() {
    		userType.getItems().addAll(UserType.values()); // add options to combo box
    		userType.getSelectionModel().select(UserType.Admin); // set default value
    }
}
