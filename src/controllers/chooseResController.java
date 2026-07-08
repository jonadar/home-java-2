package controllers;


import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class chooseResController {

    @FXML
    void fastFoodRestaurant(ActionEvent event) {
    	Main.setScene("/adminPages/createFastFoodRestaurant.fxml");
    }

    @FXML
    void premiumRestaurant(ActionEvent event) {
    	Main.setScene("/adminPages/createPremiumRestaurant.fxml");
    }

    @FXML
    void restaurant(ActionEvent event) {
    	Main.setScene("/adminPages/createRestaurant.fxml");
    }

    @FXML
    void exit(ActionEvent event) {
    	Main.goBackScene();
    }
}
