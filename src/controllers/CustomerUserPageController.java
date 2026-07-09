package controllers;

import homework2.Customer;
import homework2.Order;
import homework2.PremiumRestaurant;
import homework2.Restaurant;
import homework2.Services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import Utils.ConsolePrinter;
import application.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class CustomerUserPageController {
	public static Customer customer;
	
	ObservableList<Order> orders = FXCollections.observableArrayList();
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
    private TextField customerBalance;

    @FXML
    private TextField customerEmail;

    @FXML
    private TextField customerName;

    @FXML
    private TextField customerPhone;
    
    @FXML
    private TextField amountInput;

    @FXML
    private TextField restaurantCodeInput;

    

    @FXML
    void createOrder(ActionEvent event) {
    	createOrderController.referer = customer;
    	Main.setScene("/userPages/createOrder.fxml");
    }

    @FXML
    void logout(ActionEvent event) {
    	Main.goBackScene();
    }

    @FXML
    void searchRestaurantByCode(ActionEvent event) {
    	try {
			int restaurantCode = Integer.parseInt(restaurantCodeInput.getText());
			Restaurant restaurant = Services.findRestaurant(restaurantCode, Main.DDB.getRestaurants());

			if (restaurant != null) {
	        	restaurantTable.setItems(FXCollections.observableArrayList(List.of(restaurant)));
			}
	    } catch (Exception e) {
			ConsolePrinter.printError(e);
		}
    }

    @FXML
    void updatePersonalInfo(ActionEvent event) {
    	Main.setScene("/userPages/updatePersonalInfo.fxml");

    }

    @FXML
    void viewOrderedPremiumRestaurants(ActionEvent event) {
    	List<Restaurant> filtered = restaurants.stream().filter(restaurant -> restaurant instanceof PremiumRestaurant).collect(Collectors.toList());
    	restaurantTable.setItems(FXCollections.observableArrayList(filtered));
    }

    @FXML
    void viewOrderedRestaurant(ActionEvent event) {
    	restaurantTable.setItems(restaurants);
    }

    @FXML
    void withdrawBalance(ActionEvent event) {
    	try {
			double amount = Double.parseDouble(amountInput.getText());
			
			if(amount > customer.getRemainingCredit()) throw new Exception("cannot withdraw more than total balance");
			
			customer.setRemainingCredit(customer.getRemainingCredit() - amount);
			customerBalance.setText(customer.getRemainingCredit() + "");
	    } catch (Exception e) {
			ConsolePrinter.printError(e);
		}
    }
    
    @FXML
    void chargeBalance(ActionEvent event) {
    	try {
			Double amount = Double.parseDouble(amountInput.getText());
			
			customer.setRemainingCredit(customer.getRemainingCredit() + amount);
			customerBalance.setText(customer.getRemainingCredit() + "");
	    } catch (Exception e) {
			ConsolePrinter.printError(e);
		}
    }
    
    
    @FXML
    void viewAllRestaurant() {
    	restaurantTable.setItems(FXCollections.observableArrayList(Main.DDB.getRestaurants()));
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
        
        if(customer == null) {
        	ConsolePrinter.printError("no customer initialized...");
        	Main.goBackScene();
        }

        ArrayList<Order> customerOrders = Main.DDB.getCustomerOrders().getOrDefault(customer.getCustomerCode(), new ArrayList<Order>());
        orders = FXCollections.observableArrayList(customerOrders);
        
        ArrayList<Restaurant> customerRestaurants = Main.DDB.getCustomersOrderedRestaurants().getOrDefault(customer.getCustomerCode(), new ArrayList<Restaurant>());
        restaurants = FXCollections.observableArrayList(customerRestaurants);
        
        orderTable.setItems(orders);
        restaurantTable.setItems(restaurants);
        
        customerBalance.setText(customer.getRemainingCredit() + "");
        customerEmail.setText(customer.getEmail());
        customerName.setText(customer.getFirstName() + " " + customer.getLastName());
        customerPhone.setText(customer.getPhoneNumber());
    }


}
