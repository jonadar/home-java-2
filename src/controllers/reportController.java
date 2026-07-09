package controllers;

import java.util.Collections;

import Utils.ConsolePrinter;
import application.Main;
import homework2.Customer;
import homework2.Order;
import homework2.Restaurant;
import homework2.Rider;
import homework2.Services;
import homework2.SystemReports;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class reportController {

	// ============ customer table
	@FXML
    private TableView<Customer> customerTable;
	
	@FXML
	private TableColumn<Customer, Integer> cusCodeCol;
	
	@FXML
	private TableColumn<Customer, Double> cusCreditCol;
	
	@FXML
	private TableColumn<Customer, String> cusLastNameCol;
	
	@FXML
	private TableColumn<Customer, String> cusNameCol;
	
	
	// ============ rider table
	
    @FXML
    private TableView<Rider> riderTable;
    
    @FXML
    private TableColumn<Rider, Boolean> riderAvailableCol;

    @FXML
    private TableColumn<Rider, String> riderIdCol;

    @FXML
    private TableColumn<Rider, String> riderNameCol;

    @FXML
    private TableColumn<Rider, String> riderPhoneCol;

    @FXML
    private TableColumn<Rider, String> riderVehicleCol;

    
	// ============ restaurant table
	
    @FXML
    private TableView<Restaurant> restaurantTable;
    
    @FXML
    private TableColumn<Restaurant, Integer> resCodeCol;

    @FXML
    private TableColumn<Restaurant, Double> resFeeCol;

    @FXML
    private TableColumn<Restaurant, String> resNameCol;

    @FXML
    private TableColumn<Restaurant, Boolean> resOpenCol;

    @FXML
    private TableColumn<Restaurant, Double> resRatingCol;

    @FXML
    private TableColumn<Restaurant, String> resTypeCol;
	
	// ============ order table
	
    @FXML
    private TableView<Order> orderTable;
	
	@FXML
    private TableColumn<Order, Integer> codeCol;

    @FXML
    private TableColumn<Order, Integer> customerCol;

    @FXML
    private TableColumn<Order, String> deliveryDateCol;

    @FXML
    private TableColumn<Order, String> driverIdCol;

    @FXML
    private TableColumn<Order, String> orderDateCol;

    @FXML
    private TableColumn<Order, Double> priceCol;

    @FXML
    private TableColumn<Order, Integer> resturantCol;

    @FXML
    private TableColumn<Order, String> statusCol;
    
    
    // button functions
    
    @FXML
    void showAllAvailableRiders(ActionEvent event) {
    	riderTable.setItems(FXCollections.observableArrayList(Main.DDB.getAvailableRiders()));
    }

    @FXML
    void showAllLuxuryRestaurants(ActionEvent event) {
    	restaurantTable.setItems(FXCollections.observableArrayList(Main.DDB.getPremiumRestaurants()));
    }

    @FXML
    void showAllOpenRestaurants(ActionEvent event) {
    	restaurantTable.setItems(FXCollections.observableArrayList(Main.DDB.getOpenRestaurants()));
    }

    @FXML
    void showTotalPayments(ActionEvent event) {
    	ConsolePrinter.inform("total paid in system: " + Main.DDB.getSumOfAllOrdersPrices());
    }
    
    // ================= sorts ===============
    
    @FXML
    void sortCustomersByCreditBalance(ActionEvent event) {
    	Main.DDB.sortCustomersByCredit();
    	customerTable.setItems(FXCollections.observableArrayList(Main.DDB.getCustomers()));
    	ConsolePrinter.inform("sorted customers");
    }

    @FXML
    void sortCustomersByFirstName(ActionEvent event) {
    	Main.DDB.sortCustomersByFirstName();
    	customerTable.setItems(FXCollections.observableArrayList(Main.DDB.getCustomers()));
    	ConsolePrinter.inform("sorted customers");
    }

    @FXML
    void sortOrdersByFinalPrice(ActionEvent event) {
    	Main.DDB.sortOrdersByFinalPrice();
    	orderTable.setItems(FXCollections.observableArrayList(Main.DDB.getOrders()));
    	ConsolePrinter.inform("sorted orders");
    }

    @FXML
    void sortRestaurantsByRating(ActionEvent event) {
    	Main.DDB.sortRestaurantsByRating();
    	restaurantTable.setItems(FXCollections.observableArrayList(Main.DDB.getRestaurants()));
    	ConsolePrinter.inform("sorted restaurants");
    	
    }

    @FXML
    void sortRidersByDeliveryCount(ActionEvent event) {
    	Main.DDB.sortRidersByDeliveries();
    	riderTable.setItems(FXCollections.observableArrayList(Main.DDB.getRiders()));
    	ConsolePrinter.inform("sorted riders");
    }
    
    @FXML
    void exit(ActionEvent event) {
    	Main.goBackScene();
    }
    
    @FXML
    void viewSystemReport(ActionEvent event) {
    	String message = "";
    	message += "system has " + Main.DDB.getOrders().size() + " orders.\n";
    	message += "system has " + Main.DDB.getRiders().size() + " riders.\n";
    	message += "system has " + Main.DDB.getRestaurantAdmins().size() + " restaurant admins.\n";
    	message += "total orders value is " + SystemReports.getTotalOrderFinalPriceSum(Main.DDB.getOrders()) + "\n";
		Customer bestCustomer = SystemReports.getGreatestItem(Main.DDB.getCustomers());
		if(bestCustomer != null) {
			message += "the customer with most money to spend is " + bestCustomer;
		}
		
		ConsolePrinter.inform(message);
    }

    @FXML
    public void initialize() {
    	// customer table
    	cusCodeCol.setCellValueFactory(new PropertyValueFactory<>("customerCode"));
    	cusCreditCol.setCellValueFactory(new PropertyValueFactory<>("remainingCredit"));
    	cusLastNameCol.setCellValueFactory(new PropertyValueFactory<>("lastName"));
    	cusNameCol.setCellValueFactory(new PropertyValueFactory<>("firstName"));
    	customerTable.setItems(FXCollections.observableArrayList(Main.DDB.getCustomers()));
    	
    	// rider table
    	
    	riderAvailableCol.setCellValueFactory(new PropertyValueFactory<>("isAvailable"));
    	riderIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
    	riderNameCol.setCellValueFactory(new PropertyValueFactory<>("fullName"));
    	riderPhoneCol.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
    	riderVehicleCol.setCellValueFactory(new PropertyValueFactory<>("vehicle"));
    	riderTable.setItems(FXCollections.observableArrayList(Main.DDB.getRiders()));
    	
    	// res table
    	
    	resCodeCol.setCellValueFactory(new PropertyValueFactory<>("restaurantCode"));
    	resFeeCol.setCellValueFactory(new PropertyValueFactory<>("deliveryFee"));
    	resOpenCol.setCellValueFactory(new PropertyValueFactory<>("isOpen"));
    	resNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
    	resRatingCol.setCellValueFactory(new PropertyValueFactory<>("rating"));
    	resTypeCol.setCellValueFactory(new PropertyValueFactory<>("kitchenType"));
    	
    	restaurantTable.setItems(FXCollections.observableArrayList(Main.DDB.getRestaurants()));
    	
    	// order table
    	
    	customerCol.setCellValueFactory(new PropertyValueFactory<>("customerCode"));
    	deliveryDateCol.setCellValueFactory(new PropertyValueFactory<>("deliveryDate"));
    	driverIdCol.setCellValueFactory(new PropertyValueFactory<>("driverId"));
    	orderDateCol.setCellValueFactory(new PropertyValueFactory<>("orderDate"));
    	codeCol.setCellValueFactory(new PropertyValueFactory<>("orderCode"));
    	priceCol.setCellValueFactory(new PropertyValueFactory<>("finalPrice"));
    	statusCol.setCellValueFactory(new PropertyValueFactory<>("deliveryStatus"));
    	resturantCol.setCellValueFactory(new PropertyValueFactory<>("restaurantCode"));
    	orderTable.setItems(FXCollections.observableArrayList(Main.DDB.getOrders()));
    }
}
