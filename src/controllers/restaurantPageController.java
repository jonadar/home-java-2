package controllers;


import java.util.stream.Collectors;

import MyExceptions.InvalidPropertyException;
import Utils.ConsolePrinter;
import application.Main;
import homework2.RestAdmin;
import homework2.Restaurant;
import homework2.Services;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class restaurantPageController {
	
	private ObservableList<Restaurant> restaurants = FXCollections.observableArrayList(Main.DDB.getRestaurants());


    @FXML
    private TableColumn<Restaurant, Integer> codeCol;

    @FXML
    private TableColumn<Restaurant, Double> feeCol;

    @FXML
    private TableColumn<Restaurant, Boolean> isOpenCol;

    @FXML
    private TableColumn<Restaurant, String> nameCol;

    @FXML
    private TableColumn<Restaurant, Double> ratingCol;

    @FXML
    private TableColumn<Restaurant, String> typeCol;
  
    @FXML
    private TextField resByTypeText;

    @FXML
    private TableView<Restaurant> resTbl;

    @FXML
    private TextField searchResText;

    @FXML
    private TextField updetRatingText;

    @FXML
    void addNewRestaurant(ActionEvent event) {
    	Main.setScene("/adminPages/chooseRes.fxml");
    }

    @FXML
    void closeRest(ActionEvent event) {
    	try {
        	Restaurant res = resTbl.getSelectionModel().getSelectedItem();
        	
        	if (res == null) throw new Exception("no restaurant selected");
      	
    		res.setOpen(false);
    		resTbl.refresh(); 
    	} catch (Exception e) {
			ConsolePrinter.printError(e);
    	}
    }

    @FXML
    void openRest(ActionEvent event) {
    	try {
    		Restaurant res = resTbl.getSelectionModel().getSelectedItem();
        	
        	if (res == null) throw new Exception("no restaurant selected");
      	
    		res.setOpen(true);
    		resTbl.refresh(); 
    	} catch (Exception e) {
			ConsolePrinter.printError(e);
    	}
    }

    @FXML
    void resByType(ActionEvent event) {
    	try {
    		String type = resByTypeText.getText();
        	resTbl.setItems(FXCollections.observableArrayList(Main.DDB.openRestaurantsByKitchenType(type)));
    	} catch (Exception e) {
			ConsolePrinter.printError(e);
    	}
    }

    @FXML
    void searchRes(ActionEvent event) {
    	try {
        	Integer code = Integer.parseInt(searchResText.getText());
        	Restaurant restaurant = Services.findRestaurant(code, Main.DDB.getRestaurants());
        	resTbl.setItems(FXCollections.observableArrayList(restaurant));
    	} catch (Exception e) {
			ConsolePrinter.printError(e);
    	}
    }

    @FXML
    void showAllRestaurants(ActionEvent event) {
    	restaurants = FXCollections.observableArrayList(Main.DDB.getRestaurants());
    	resTbl.setItems(restaurants);
    }

    @FXML
    void showOpenRestaurants(ActionEvent event) {
    	//restaurants.stream().filter(Restaurant::isOpen).collect(Collectors.toList());
    	ObservableList<Restaurant> restaurantslist = FXCollections.observableArrayList(restaurants.stream().filter(Restaurant::isOpen).collect(Collectors.toList()));
    	resTbl.setItems(restaurantslist);
    }

    @FXML
    void updetRating(ActionEvent event) {
    	try {
    		double rating = Double.parseDouble(updetRatingText.getText());
    		Restaurant res = resTbl.getSelectionModel().getSelectedItem();
    		
        	if (res == null) throw new Exception("no restaurant selected");
        	
        	res.setRating(rating);
    		resTbl.refresh(); 
		} catch (NumberFormatException e) {
			ConsolePrinter.printError("must enter valid number rating");
		} catch (Exception e) {
			ConsolePrinter.printError(e);
		}
    	
    }
    
    @FXML
    void exit(ActionEvent event) {
    	Main.goBackScene();
    }
    
    public void initialize() {
    	codeCol.setCellValueFactory(new PropertyValueFactory<>("restaurantCode"));
    	feeCol.setCellValueFactory(new PropertyValueFactory<>("deliveryFee"));
    	isOpenCol.setCellValueFactory(new PropertyValueFactory<>("isOpen"));
    	nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
    	ratingCol.setCellValueFactory(new PropertyValueFactory<>("rating"));
    	typeCol.setCellValueFactory(new PropertyValueFactory<>("kitchenType"));
    	
    	resTbl.setItems(restaurants);
    }

}
