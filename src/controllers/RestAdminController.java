package controllers;
import java.util.ArrayList;

import Utils.ConsolePrinter;
import application.Main;
import homework2.DeliveryDataBase;
import homework2.RestAdmin;
import homework2.Restaurant;
import homework2.Services;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
public class RestAdminController {


    @FXML
    private TableColumn<RestAdmin, String> colname;

    @FXML
    private TableColumn<RestAdmin, String> colusername;

    @FXML
    private TextField idMtext;

    @FXML
    private TextField idResttext;

    @FXML
    private TextField text1;

    @FXML
    private TextField text2;

    @FXML
    private TextField text3;

    @FXML
    void addManagerToRest(ActionEvent event) {
    	try {
    		String userName = idMtext.getText();
    		int rectCode = Integer.parseInt(idResttext.getText());
    		Services.assignRestAdminToRestaurant(Main.DDB.getRestaurantAdmins(), Main.DDB.getRestaurants(), userName, rectCode);
	        	
    	} catch (Exception e) {
			ConsolePrinter.printError(e);	
    	}
    }

    @FXML
    void searchRestaurantManager(ActionEvent event) {

    }

    @FXML
    void searchRider(ActionEvent event) {

    }

    @FXML
    void updateManagerStatus(ActionEvent event) {

    

    }

}
