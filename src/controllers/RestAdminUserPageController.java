package controllers;

import java.util.ArrayList;

import MyExceptions.InvalidPropertyException;
import Utils.ConsolePrinter;
import application.Main;
import homework2.Customer;
import homework2.Order;
import homework2.RestAdmin;
import homework2.Restaurant;
import homework2.Rider;
import homework2.Services;
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
    private TextField riderCodeText;
    
    @FXML
    private TextField orderCodeText;

    @FXML
    private TextField uptresrateTextRate;

    @FXML
    private TextField uptresrateTextCode;
    
    @FXML
    void addCustomer(ActionEvent event) {
    	Main.setScene("/adminPages/createCustomer.fxml");
    }

    @FXML
    void addOrder(ActionEvent event) {
    	createOrderController.referer = restaurantAdmin;
    	Main.setScene("/userPages/createOrder.fxml");
    }

    @FXML
    void assignRiderToOrder(ActionEvent event) {
    	try {
    		Integer orderCode = Integer.parseInt(orderCodeText.getText());
    		String riderId = riderCodeText.getText();
    		Order order = Services.findOrder(orderCode, Main.DDB.getOrders());
    		Rider rider = Services.findRider(riderId, Main.DDB.getRiders());
    		
    		if(order == null) throw new Exception("order with code " + orderCode + " not found");
    		
    		if(Services.assignOrderToRider(rider, order)) ConsolePrinter.inform("assigned");
    		
    	} catch (InvalidPropertyException e) {
			ConsolePrinter.printError(e);
		} catch (Exception e) {
			ConsolePrinter.printError(e);	
    	}
    }

    

    @FXML
    void updateRestaurantRating(ActionEvent event) {
    	try {
    		Restaurant selectedRes = restaurantTable.getSelectionModel().getSelectedItem();
        	Double rate = Double.parseDouble(uptresrateTextRate.getText());
        	if (selectedRes != null) {
    			selectedRes.setRating(rate);
    			restaurantTable.refresh();
    		} else throw new Exception("select restaurant first from table");
    	} catch (InvalidPropertyException e) {
			ConsolePrinter.printError(e);
		} catch (NumberFormatException e) {
			ConsolePrinter.printError("rating must be a number");
		}catch (Exception e) {
			ConsolePrinter.printError(e);	
    	}
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
    void exit(ActionEvent event) {
    	Main.goBackScene();
    }
    
    @FXML
    void closeRestaurant(ActionEvent event) {
    	try {    		
    		Restaurant selectedRes = restaurantTable.getSelectionModel().getSelectedItem();
    		if (selectedRes != null) {
    			selectedRes.setOpen(false);
    			restaurantTable.refresh();
    		} else throw new Exception("select restaurant first");
    	} catch (Exception e) {
			ConsolePrinter.printError(e);
		}
    }

    @FXML
    void openRestaurant(ActionEvent event) {
    	try {    		
    		Restaurant selectedRes = restaurantTable.getSelectionModel().getSelectedItem();
    		if (selectedRes != null) {
    			selectedRes.setOpen(true);  
    			restaurantTable.refresh();
    		} else throw new Exception("select restaurant first");
    	} catch (Exception e) {
			ConsolePrinter.printError(e);
		}
    }
    
    @FXML
    void viewAllOrders() {
    	orderTable.setItems(FXCollections.observableArrayList(Main.DDB.getOrdersOfRestaurant(restaurantAdmin))); 
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
		    
		orderTable.setItems(FXCollections.observableArrayList(Main.DDB.getOrdersOfRestaurant(restaurantAdmin))); 
	   
		restaurantTable.setItems(FXCollections.observableArrayList(restaurantAdmin.getRestaurants()));
    }
    
    
}
