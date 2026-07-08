package controllers;

import java.util.ArrayList;

import Utils.ConsolePrinter;
import application.Main;
import homework2.Customer;
import homework2.Order;
import homework2.RestAdmin;
import homework2.Restaurant;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class RestAdminUserPageController {
	public static RestAdmin restaurantAdmin;
	
	ObservableList<Restaurant> restaurants = FXCollections.observableArrayList();
	
	// order table
	@FXML
    private TableView<Order> orderTable;
	
	@FXML
    private TableColumn<Order, Integer> codeCol;
	
	@FXML
    private TableColumn<Order, Integer> restaurantCol;
	
	@FXML
    private TableColumn<Order, String> orderDateCol;
	
    @FXML
    private TableColumn<Order, String> deliveryDateCol;
    
    @FXML
    private TableColumn<Order, Double> priceCol;
    
    @FXML
    private TableColumn<Order, String> riderCol;

    @FXML
    private TableColumn<Order, String> statusCol;
    
	// restaurant table
    @FXML
    private TableView<Restaurant> restaurantTable;
    
    @FXML
    private TableColumn<Restaurant, Double> deliveryFeeCol;

    @FXML
    private TableColumn<Restaurant, String> kitchenTypeCol;

    @FXML
    private TableColumn<Restaurant, Double> ratingCol;

    @FXML
    private TableColumn<Restaurant, Integer> restCodeCol;

    @FXML
    private TableColumn<Restaurant, String> restNameCol;

    @FXML
    private TableColumn<Restaurant, Boolean> restOpenCol;
    
    @FXML
    private TextField viewOpenResByKText;

    @FXML
    private TextField viewOrderText;
    
    @FXML
    void addCustomer(ActionEvent event) {
    	Main.setScene("/adminPages/createCustomer.fxml");

    }

    @FXML
    void addOrder(ActionEvent event) {

    }

    @FXML
    void assignRiderToOrder(ActionEvent event) {

    }

    @FXML
    void closeOrOpenRestaurant(ActionEvent event) {

    }

    @FXML
    void updateRestaurantRating(ActionEvent event) {

    }

    @FXML
    void viewMyRestaurants(ActionEvent event) {
    	restaurantTable.setItems(FXCollections.observableArrayList(restaurantAdmin.getRestaurants()));
    }

    @FXML
    void viewOpenRestaurantsByType(ActionEvent event) {
        	String type = viewOpenResByKText.getText();
        	ObservableList<Restaurant> restaurantsByType = FXCollections.observableArrayList();
        	for (Restaurant r : restaurantAdmin.getRestaurants()) {
				if (r.isOpen() && r.getKitchenType().equals(type)) {
					restaurantsByType.add(r);
				}
			}
        	restaurantTable.setItems(restaurantsByType);
    	
    }

    @FXML
    void viewOrdersByRestaurantCode(ActionEvent event) {
    	try {
        	Integer code = Integer.parseInt(viewOrderText.getText());
        	ObservableList<Order> ordersByRes = FXCollections.observableArrayList();
        	for (Order order : Main.DDB.getOrders()) {
        		if (order.getRestaurantCode() == code) {
        			ordersByRes.add(order);
        		}
			}
        	orderTable.setItems(ordersByRes);
    	} catch (Exception e) {
    			ConsolePrinter.printError(e);	
        	}
    }
    
    @FXML
    void initialize() {
        codeCol.setCellValueFactory(new PropertyValueFactory<>("orderCode"));
        restaurantCol.setCellValueFactory(new PropertyValueFactory<>("restaurantCode"));
        orderDateCol.setCellValueFactory(new PropertyValueFactory<>("orderDate"));
        deliveryDateCol.setCellValueFactory(new PropertyValueFactory<>("deliveryDate"));
        priceCol.setCellValueFactory(new PropertyValueFactory<>("finalPrice"));
        riderCol.setCellValueFactory(new PropertyValueFactory<>("driverId"));
        statusCol.setCellValueFactory(new PropertyValueFactory<>("deliveryStatus"));
        
        deliveryFeeCol.setCellValueFactory(new PropertyValueFactory<>("deliveryFee"));
        kitchenTypeCol.setCellValueFactory(new PropertyValueFactory<>("kitchenType"));
        ratingCol.setCellValueFactory(new PropertyValueFactory<>("rating"));
        restCodeCol.setCellValueFactory(new PropertyValueFactory<>("restaurantCode"));
        restNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        restOpenCol.setCellValueFactory(new PropertyValueFactory<>("isOpen"));
        
        if(restaurantAdmin == null) {
        	ConsolePrinter.printError("no restaurant admin initialized...");
        	Main.goBackScene();
        }
        
        restaurantTable.setItems(FXCollections.observableArrayList(restaurantAdmin.getRestaurants()));
    }
    
    
}
