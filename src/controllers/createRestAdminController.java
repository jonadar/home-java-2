package controllers;

import MyExceptions.InvalidPropertyException;
import Utils.ConsolePrinter;
import application.Main;
import homework2.RestAdmin;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class createRestAdminController {

    @FXML
    private TextField nameInput;

    @FXML
    private TextField passwordInput;

    @FXML
    private TextField usernameInput;

    @FXML
    void createAdmin(ActionEvent event) {
    	try {
    		RestAdmin admin = new RestAdmin(nameInput.getText(), usernameInput.getText(), passwordInput.getText());
    		Main.DDB.addRestaurantAdmin(admin);
    		Main.goBackScene();
    	} catch (InvalidPropertyException e) {
			ConsolePrinter.printError(e);
		}
    }

    @FXML
    void exit(ActionEvent event) {
    	Main.goBackScene();
    }

}
