package controllers;

import java.util.ArrayList;

import Utils.ConsolePrinter;
import application.Main;
import homework2.Customer;
import homework2.Order;
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

public class customerPageController {
	
	private ObservableList<Customer> Customers = FXCollections.observableArrayList(Main.DDB.getCustomers());

    @FXML
    private TableView<Customer> CustomersTbl;

    @FXML
    private TableColumn<Customer, Integer> cusCodeCol;

    @FXML
    private TableColumn<Customer, Double> cusCreditCol;

    @FXML
    private TableColumn<Customer, String> cusLastNameCol;

    @FXML
    private TableColumn<Customer, String> cusNameCol;

    @FXML
    private TextField customerSearchText;

    @FXML
    private TextField displayiRestsOfCustomerText;

    @FXML
    private TextField luxuryText;


    @FXML
    private TableView<Order> ordersOfCustomerTbl;

    @FXML
    private TableColumn<Restaurant, Integer> restColcode;

    @FXML
    private TableColumn<Restaurant, String> restColname;

    @FXML
    private TableView<Restaurant> restTbl;

    @FXML
    private TableColumn<Order, Integer> tbl3nameCol; //code

    @FXML
    private TableColumn<Order, Double> tbl3priceCol;

    @FXML
    private TableColumn<Order, String> tbl3statusCol;

    @FXML
    private TextField viewOrdersOfCustomerText;


    @FXML
    void addNewcustomer(ActionEvent event) {
    	Main.setScene("/adminPages/createCustomer.fxml");

    }

    @FXML
    void customerSearch(ActionEvent event) {
    	try {
	    	Integer code = Integer.parseInt(customerSearchText.getText());
	    	Customer customer = Services.findCustomer(code, Main.DDB.getCustomers());
	    	CustomersTbl.setItems(FXCollections.observableArrayList(customer));

    	} catch (Exception e) {
			ConsolePrinter.printError(e);	
    	}
 
    }

    @FXML
    void displayiRestsOfCustomer(ActionEvent event) {
    	try {
	    	Integer code = Integer.parseInt(displayiRestsOfCustomerText.getText());
	    	Customer customer = Services.findCustomer(code, Main.DDB.getCustomers());
	    	ArrayList<Restaurant> rests = Main.DDB.getCustomersOrderedRestaurants().get(customer.getCustomerCode());
	    	if (rests != null && !rests.isEmpty()) {
	    		ObservableList<Restaurant> restsList = FXCollections.observableArrayList(rests);
	    		restTbl.setItems(restsList);
	    }
	    	} catch (Exception e) {
				ConsolePrinter.printError(e);
	    	}
    }

    @FXML
    void displayingallcustomers(ActionEvent event) {
    	CustomersTbl.setItems(Customers);

    }

    @FXML
    void luxuryrest(ActionEvent event) {
    	try {
    		Integer code = Integer.parseInt(luxuryText.getText());
	    	Customer customer = Services.findCustomer(code, Main.DDB.getCustomers());
	    	if (customer != null) {
	    		ArrayList<Restaurant> primumRests = Main.DDB.customerOrdersFromPremiumRest(customer);
	    		if (primumRests != null && !primumRests.isEmpty()) {
		    		ObservableList<Restaurant> primumRestsList = FXCollections.observableArrayList(primumRests);
		    		restTbl.setItems(primumRestsList);
	    		}
	    	}
    	} catch (Exception e) {
			ConsolePrinter.printError(e);
    	}
    }

    @FXML
    void updateCustomerDetails(ActionEvent event) {
    	Customer customer = CustomersTbl.getSelectionModel().getSelectedItem();

    }

    @FXML
    void viewOrdersOfCustomer(ActionEvent event) {
    	try {
	    	Integer code = Integer.parseInt(viewOrdersOfCustomerText.getText());
	    	ObservableList<Order> orders = FXCollections.observableArrayList();
	    	for (Order order : Main.DDB.getOrders()) {
	    		if (order.getCustomerCode() == code) {
	    			orders.add(order);
	    		}
	    	}
	    	ordersOfCustomerTbl.setItems(orders);
		} catch (Exception e) {
			ConsolePrinter.printError(e);
    	}


    }
    
    @FXML
    void exit(ActionEvent event) {
    	Main.goBackScene();
    }

    
    public void initialize() {
    	//customers
    	cusCodeCol.setCellValueFactory(new PropertyValueFactory<>("customerCode"));
    	cusCreditCol.setCellValueFactory(new PropertyValueFactory<>("remainingCredit"));
    	cusLastNameCol.setCellValueFactory(new PropertyValueFactory<>("lastName"));
    	cusNameCol.setCellValueFactory(new PropertyValueFactory<>("firstName"));
    	CustomersTbl.setItems(Customers);
    	
    	// rests
    	restColcode.setCellValueFactory(new PropertyValueFactory<>("restaurantCode"));
    	restColname.setCellValueFactory(new PropertyValueFactory<>("name"));
    	
    	// orders
    	tbl3nameCol.setCellValueFactory(new PropertyValueFactory<>("restaurantCode"));
    	tbl3priceCol.setCellValueFactory(new PropertyValueFactory<>("finalPrice"));
    	tbl3statusCol.setCellValueFactory(new PropertyValueFactory<>("deliveryStatus"));

    }


}
