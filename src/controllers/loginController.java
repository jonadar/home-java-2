package controllers;

import MyExceptions.CustomerNotFoundException;
import MyExceptions.RiderNotFoundException;
import Utils.ConsolePrinter;
import application.Main;
import enums.UserType;
import homework2.RestAdmin;
import homework2.Services;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class LoginController {

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
		
		clearFields();
    }

    /**
     * handle login for all types of users
     */
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
				
				Main.setScene("/adminPages/adminPage.fxml");
			} catch (Exception e) {
				ConsolePrinter.printError(e);
			}
			break;
		case UserType.RestAdmin:
			try {
				String userName = field1.getText();
				String pass = field2.getText();
				
				RestAdmin restaurantAdmin = Services.findRestAdmin(userName, Main.DDB.getRestaurantAdmins());
				
				if(restaurantAdmin.login(userName, pass) == false) {
					throw new Exception("username or password incorrect.");
				}
				
				RestAdminUserPageController.restaurantAdmin = restaurantAdmin;
				
				Main.setScene("/userPages/restAdminUserPage.fxml");
			} catch (Exception e) {
				ConsolePrinter.printError(e);
			}
			break;
		case UserType.Customer:
			try {
				int code = Integer.parseInt(field1.getText());
				
				// find user and set in relevant controller
				CustomerUserPageController.customer = Services.findCustomer(code, Main.DDB.getCustomers());
				
				Main.setScene("/userPages/customerUserPage.fxml");
			} catch (CustomerNotFoundException e) {
				ConsolePrinter.printError("could not find user with that code");
			} catch (NumberFormatException e) {
				ConsolePrinter.printError("code must be valid integer");
			}
			break;
		case UserType.Rider:
			try {
				String id = field1.getText();
				
				// find rider and set in relevant controller
				RiderUserPageController.rider = Services.findRider(id, Main.DDB.getRiders());
				
				Main.setScene("/userPages/riderUserPage.fxml");
			} catch (RiderNotFoundException e) {
				ConsolePrinter.printError("could not find user with that id");
			}
			break;
    	}
    }

    @FXML
    void quit(ActionEvent event) {
    	// check how to do this
    	Main.stage.close();
    }	
    
    @FXML
    void initialize() {
    		userType.getItems().addAll(UserType.values()); // add options to combo box
    		userType.getSelectionModel().select(UserType.Admin); // set default value
    }
    
    public void clearFields() {
    	field1.setText("");
    	field2.setText("");
    }
}
