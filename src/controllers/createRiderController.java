package controllers;

import MyExceptions.InvalidPropertyException;
import Utils.ConsolePrinter;
import application.Main;
import homework2.Rider;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;

public class createRiderController {

    @FXML
    private TextField idText;

    @FXML
    private CheckBox isAvailableBox;

    @FXML
    private TextField nameText;

    @FXML
    private TextField phonText;

    @FXML
    private TextField vehicleText;

    @FXML
    void creat(ActionEvent event) {
    	try {
        	Rider rider = new Rider(idText.getText(), nameText.getText(), phonText.getText(), vehicleText.getText(), isAvailableBox.isSelected());
        	Main.DDB.addRider(rider);
		} catch (InvalidPropertyException e) {
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
