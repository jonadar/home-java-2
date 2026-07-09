package controllers;

import application.Main;
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
    	
    }

    @FXML
    void password(ActionEvent event) {

    }

    @FXML
    void username(ActionEvent event) {

    }

}
