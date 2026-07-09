package controllers;

import Utils.ConsolePrinter;
import application.Main;
import homework2.RestAdmin;
import homework2.Services;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class RestAdminController {

	private ObservableList<RestAdmin> restAdmins = FXCollections.observableArrayList(Main.DDB.getRestaurantAdmins());


    @FXML
    private TableColumn<RestAdmin, String> colname;

    @FXML
    private TableColumn<RestAdmin, String> colusername;

    @FXML
    private TextField idMtext;

    @FXML
    private TextField idResttext;

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
    	} catch (NumberFormatException e) {
			ConsolePrinter.printError("could not parse values, make sure restaurant code is number");
		} catch (Exception e) {
			ConsolePrinter.printError(e);	
    	}
    }

    @FXML
    void searchRestaurantManager(ActionEvent event) {
    	try {
    		String userName = text3.getText();
    		RestAdmin restAdmin = Services.findRestAdmin(userName, Main.DDB.getRestaurantAdmins());
			tblAllRestAdmin.setItems(FXCollections.observableArrayList(restAdmin));
    	} catch (Exception e) {
			ConsolePrinter.printError(e);
    	}
    }


    @FXML
    void updateManagerStatus(ActionEvent event) {
    	// to do

    }
    
    @FXML
    private TableView<RestAdmin> tblAllRestAdmin;
    
    @FXML
    void showAllMangers(ActionEvent event) {
    	tblAllRestAdmin.setItems(restAdmins);
    }
    
    @FXML
    void exit(ActionEvent event) {
    	Main.goBackScene();
    }
    
    public void initialize() {
    	colname.setCellValueFactory(new PropertyValueFactory<>("name"));
    	colusername.setCellValueFactory(new PropertyValueFactory<>("username"));
    	
    	tblAllRestAdmin.setItems(restAdmins);
    }
}
