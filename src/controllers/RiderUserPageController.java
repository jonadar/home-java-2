package controllers;

import Utils.ConsolePrinter;
import application.Main;
import homework2.Order;
import homework2.Rider;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class RiderUserPageController {
	public static Rider rider;
	
	private ObservableList<Order> orders = FXCollections.observableArrayList();
	
	@FXML
	private TableColumn<Order, Integer> customerCol;

	@FXML
	private TableColumn<Order, String> deliveryDateCol;

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
    void logout(ActionEvent event) {
    	Main.goBackScene();
    }

    @FXML
    void numberOfDeliveries(ActionEvent event) {
    	// do with stream api
    }

    @FXML
    void updateDeliveryStatus(ActionEvent event) {
    	
    }

    @FXML
    void viewActiveOrder(ActionEvent event) {
    	
    }

    @FXML
    void viewAllOrders(ActionEvent event) {

    }

    @FXML
    void viewOrderHistory(ActionEvent event) {

    }
    
    @FXML
    public void initialize() {
    	customerCol.setCellValueFactory(new PropertyValueFactory<>("customerCode"));
    	deliveryDateCol.setCellValueFactory(new PropertyValueFactory<>("deliveryDate"));
    	orderDateCol.setCellValueFactory(new PropertyValueFactory<>("orderDate"));
    	codeCol.setCellValueFactory(new PropertyValueFactory<>("orderCode"));
    	priceCol.setCellValueFactory(new PropertyValueFactory<>("finalPrice"));
    	statusCol.setCellValueFactory(new PropertyValueFactory<>("deliveryStatus"));
    	resturantCol.setCellValueFactory(new PropertyValueFactory<>("restaurantCode"));
    	
    	if(rider == null) {
        	ConsolePrinter.printError("no rider initialized...");
        	Main.goBackScene();
        }
    	
    	orders = FXCollections.observableArrayList(rider.getOrders());
    	
    	tblAllOrders.setItems(orders);
    	
    	
    }
}
