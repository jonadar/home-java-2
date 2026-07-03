package controllers;

import java.io.IOException;

import Utils.ConsolePrinter;
import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class AdminPageController {

    @FXML
    void customerManagement(ActionEvent event) {
    	loadScene("/adminPages/customerPage.fxml");
    }

    @FXML
    void orderManagment(ActionEvent event) {
    	loadScene("/adminPages/orderPage.fxml");
    }

    @FXML
    void restaurantAdminManangment(ActionEvent event) {
    	loadScene("/adminPages/restAdminPage.fxml");
    }

    @FXML
    void restaurantManagment(ActionEvent event) {
    	loadScene("/adminPages/restaurantPage.fxml");
    }

    @FXML
    void riderManangment(ActionEvent event) {
    	loadScene("/adminPages/riderPage.fxml");
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

    }
    
    @FXML
    void load(ActionEvent event) {
    	
    }
    
    private void loadScene(String path) {
    	try {
    		Parent root = FXMLLoader.load(getClass().getResource(path));
    		
    		Scene scene = new Scene(root);
    		
    		Main.setScene(scene);
    	} catch (IOException e) {
			ConsolePrinter.printError("cannot find view " + path);
		}
    }
}
