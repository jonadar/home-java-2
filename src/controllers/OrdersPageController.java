
package controllers;
import java.util.ArrayList;
import java.util.List;

import MyExceptions.CustomerNotFoundException;
import MyExceptions.RestaurantNotFoundException;
import Utils.ConsolePrinter;
import application.Main;
import homework2.Customer;
import homework2.DeliveryDataBase;
import homework2.Order;
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

public class OrdersPageController {
	
	private ObservableList<Order> orders = FXCollections.observableArrayList(Main.DDB.getOrders());
	
	@FXML
	private TableColumn<Order, Integer> customerCol;

	@FXML
	private TableColumn<Order, String> deliveryDateCol;

    @FXML
	private TableColumn<Order, Integer> driverIdCol;

	@FXML
	private TableColumn<Order, String> orderDateCol;
	
    @FXML
    private TableColumn<Order, Integer> codeCol;

    @FXML
    private TableColumn<Order, Double> priceCol;

    @FXML
    private TableColumn<Order, Integer> resturantCol;

    @FXML
    private TableColumn<Order, String> statusCol;

    @FXML
    private TableView<Order> tblAllOrders;
    
    @FXML
    private TextField text2;
    
    @FXML
    void showOrders(ActionEvent event) {
    	tblAllOrders.setItems(orders);

    }
    
    @FXML
    void showCustomerOrders(ActionEvent event) {
    	try {
    		int code = Integer.parseInt(text2.getText());
    		Customer customer = Services.findCustomer(code, Main.DDB.getCustomers());
    		if (customer != null) {
    			ArrayList<Order> customerOrders = Main.DDB.getCustomerOrders().get(customer.getCustomerCode());
            	tblAllOrders.setItems(FXCollections.observableArrayList(customerOrders));
    		}
	    } catch (NumberFormatException e) {
			ConsolePrinter.printError("enter valid code, digits only");
    	} catch (CustomerNotFoundException e) {
			ConsolePrinter.printError(e);
    	}
    }
    
    @FXML
    void showHighestPriceOrder(ActionEvent event) {
    	try {
    		Order order = orders.stream().max(Order.getComparator()).orElse(null);
        	if (order != null) {
        		tblAllOrders.setItems(FXCollections.observableArrayList(List.of(order)));
        	}
	    } catch (Exception e) {
			ConsolePrinter.printError(e);
		}
    }
    
    @FXML
    private TextField text3;
    
    @FXML
    void showOrdersByRest(ActionEvent event) {
	    try {
			int code = Integer.parseInt(text3.getText());
			Restaurant restaurant = Services.findRestaurant(code, Main.DDB.getRestaurants());
			if (restaurant != null) {
				ArrayList<Order> restOrders = Main.DDB.getCustomerOrders().get(restaurant.getRestaurantCode());
	        	tblAllOrders.setItems(FXCollections.observableArrayList(restOrders));
			}
	    } catch (NumberFormatException e) {
			ConsolePrinter.printError("enter valid code, digits only");
    	} catch (RestaurantNotFoundException e) {
			ConsolePrinter.printError(e);
    	}
    }
    
   
    
    @FXML
    private TextField text1;

    @FXML
    void findOrder(ActionEvent event) {
    	try {
    		int code = Integer.parseInt(text1.getText());
        	Order order = Services.findOrder(code, Main.DDB.getOrders());
        	if (order != null) {        		
        		tblAllOrders.setItems(FXCollections.observableArrayList(List.of(order)));
        	}
	    } catch (NumberFormatException e) {
			ConsolePrinter.printError("enter valid code, digits only");
    	}
    }
    
    @FXML
    void exit(ActionEvent event) {
    	Main.goBackScene();
    }

    @FXML
    public void initialize() {
    	customerCol.setCellValueFactory(new PropertyValueFactory<>("customerCode"));
    	deliveryDateCol.setCellValueFactory(new PropertyValueFactory<>("deliveryDate"));
    	driverIdCol.setCellValueFactory(new PropertyValueFactory<>("driverId"));
    	orderDateCol.setCellValueFactory(new PropertyValueFactory<>("orderDate"));
    	codeCol.setCellValueFactory(new PropertyValueFactory<>("orderCode"));
    	priceCol.setCellValueFactory(new PropertyValueFactory<>("finalPrice"));
    	statusCol.setCellValueFactory(new PropertyValueFactory<>("deliveryStatus"));
    	resturantCol.setCellValueFactory(new PropertyValueFactory<>("restaurantCode"));
    	tblAllOrders.setItems(orders);
    	
    }
}