package controllers;

import Utils.ConsolePrinter;
import Utils.SaveManager;
import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;


public class AdminPageController {

    @FXML
    void customerManagement(ActionEvent event) {
    	Main.setScene("/adminPages/customerPage.fxml");
    }

    @FXML
    void orderManagment(ActionEvent event) {
    	Main.setScene("/adminPages/orderPage.fxml");
    }

    @FXML
    void restaurantAdminManangment(ActionEvent event) {
    	Main.setScene("/adminPages/restAdminPage.fxml");
    }

    @FXML
    void restaurantManagment(ActionEvent event) {
    	Main.setScene("/adminPages/restaurantPage.fxml");
    }

    @FXML
    void riderManangment(ActionEvent event) {
    	Main.setScene("/adminPages/riderPage.fxml");
    }

    @FXML
    void logout(ActionEvent event) {
    	Main.goBackScene();
    }
    
    @FXML
    void reportsAndSorting(ActionEvent event) {
    	ConsolePrinter.printError("not yet implemented :(");
    	//TODO
    }
    
    @FXML
    void save(ActionEvent event) {
    	SaveManager.save(Main.DDB);
    }
    
    @FXML
    void load(ActionEvent event) {
    	SaveManager.load(Main.DDB);
    }
}
