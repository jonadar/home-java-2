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
    	codeCol.setCellValueFactory(new PropertyValueFactory<>("orderCode"));
    	priceCol.setCellValueFactory(new PropertyValueFactory<>("finalPrice"));
    	statusCol.setCellValueFactory(new PropertyValueFactory<>("deliveryStatus"));
    	resturantCol.setCellValueFactory(new PropertyValueFactory<>("restaurantCode"));
    	System.out.println(Main.DDB.getOrders());
    	tblAllOrders.getItems().addAll(Main.DDB.getOrders());
    }
}