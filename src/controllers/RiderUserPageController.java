package controllers;

import Utils.ConsolePrinter;
import application.Main;
import homework2.Order;
import homework2.Rider;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
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
    	int counter = (int) rider.getOrders().stream().filter(order -> order.getDeliveryStatus().equals("delivered")).count();
    		Alert alert = new Alert(AlertType.INFORMATION, "counter");
    		alert.showAndWait();
    }

    @FXML
    void updateDeliveryStatus(ActionEvent event) {
    	Order selectedOrder = tblAllOrders.getSelectionModel().getSelectedItem();
    	if (selectedOrder != null) {
    		selectedOrder.setDeliveryStatus("on the way");
    	}
    }

    @FXML
    void viewActiveOrder(ActionEvent event) {
    	ObservableList<Order> activeOrders = FXCollections.observableArrayList();
    	for (Order order : rider.getOrders()) {
			if (!order.getDeliveryStatus().equals("delivered")) {
				activeOrders.add(order);
			}
		}
    	tblAllOrders.setItems(activeOrders);
    }

    @FXML
    void updateOrderDelivered(ActionEvent event) {
    	Order selectedOrder = tblAllOrders.getSelectionModel().getSelectedItem();
    	if (selectedOrder != null) {
    		selectedOrder.setDeliveryStatus("delivered");
    	}
    }
    
    @FXML
    void viewAllOrders(ActionEvent event) {
    	tblAllOrders.setItems(FXCollections.observableArrayList(rider.getOrders()));
    }

    @FXML
    void viewOrderHistory(ActionEvent event) {
    	ObservableList<Order> activeOrders = FXCollections.observableArrayList();
    	for (Order order : rider.getOrders()) {
			if (order.getDeliveryStatus().equals("delivered")) {
				activeOrders.add(order);
			}
		}
    	tblAllOrders.setItems(activeOrders);
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
