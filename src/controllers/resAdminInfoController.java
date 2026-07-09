package controllers;

import MyExceptions.InvalidPropertyException;
import Utils.ConsolePrinter;
import application.Main;
import homework2.RestAdmin;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class resAdminInfoController {
	

    @FXML
    private TextField text;

    @FXML
    void exit(ActionEvent event) {
    	Main.goBackScene();

    }

    @FXML
    void name(ActionEvent event) {
    	RestAdmin restA = RestAdminController.resAdmin;
    	if (restA != null) {
    		try {
				restA.setName(text.getText());
				ConsolePrinter.inform("name saved");
			}  catch (InvalidPropertyException e) {
				ConsolePrinter.printError(e);
			}
    	}
    }

    @FXML
    void password(ActionEvent event) {
    	RestAdmin restA = RestAdminController.resAdmin;
    	if (restA != null) {
    		try {
				restA.setPassword(text.getText());
				ConsolePrinter.inform("password saved");
			}  catch (InvalidPropertyException e) {
				ConsolePrinter.printError(e);
			}
    	}
    }

    @FXML
    void username(ActionEvent event) {
    	RestAdmin restA = RestAdminController.resAdmin;
    	if (restA != null) {
    		try {
				restA.setUsername(text.getText());
				ConsolePrinter.inform("username saved");
			}  catch (InvalidPropertyException e) {
				ConsolePrinter.printError(e);
			}
    	}
    }

}
