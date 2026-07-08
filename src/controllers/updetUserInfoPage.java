package controllers;

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
    	CustomerUserPageController.customer.setAddress(text.getText());
    }

    @FXML
    void email(ActionEvent event) {
    	CustomerUserPageController.customer.setEmail(text.getText());
    }

    @FXML
    void exit(ActionEvent event) {
    	Main.goBackScene();
    }

    @FXML
    void phonNum(ActionEvent event) {
    	CustomerUserPageController.customer.setPhoneNumber(text.getText());

    }


}
