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
    	try {
	    	RestAdmin restA = RestAdminController.resAdmin;
	    	if (restA == null) throw new Exception("invalid rest admin");
			
			restA.setName(text.getText());
			ConsolePrinter.inform("name updated");
		} catch (InvalidPropertyException e) {
			ConsolePrinter.printError(e);
		} catch (Exception e) {
			ConsolePrinter.printError(e);
		}
    }

    @FXML
    void password(ActionEvent event) {
    	try {
	    	RestAdmin restA = RestAdminController.resAdmin;
	    	if (restA == null) throw new Exception("invalid rest admin");
			
			restA.setPassword(text.getText());
			ConsolePrinter.inform("password updated");
		} catch (InvalidPropertyException e) {
			ConsolePrinter.printError(e);
		} catch (Exception e) {
			ConsolePrinter.printError(e);
		}
    }

    @FXML
    void username(ActionEvent event) {
    	try {
	    	RestAdmin restA = RestAdminController.resAdmin;
	    	if (restA == null) throw new Exception("invalid rest admin");
			
			restA.setUsername(text.getText());
			ConsolePrinter.inform("username updated");
		} catch (InvalidPropertyException e) {
			ConsolePrinter.printError(e);
		} catch (Exception e) {
			ConsolePrinter.printError(e);
		}
    }

}
