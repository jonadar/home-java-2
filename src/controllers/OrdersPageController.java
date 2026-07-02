package controllers;

import application.Main;
import homework2.Order;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class OrdersPageController {
	
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
    void showOrders(ActionEvent event) {
    	System.out.println("no good");
    }
    
    @FXML
    void showHighestPriceOrder(ActionEvent event) {
    	
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
    	System.out.println(Main.DDB.getOrders());
    	tblAllOrders.getItems().addAll(Main.DDB.getOrders());
    }
}