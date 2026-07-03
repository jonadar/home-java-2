package controllers;

import homework2.Customer;
import homework2.Order;
import homework2.Restaurant;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class customerPageController {
	
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
    private TextField moneyText;

    @FXML
    private TableView<?> ordersOfCustomerTbl;

    @FXML
    private TableColumn<Restaurant, Integer> restColcode;

    @FXML
    private TableColumn<Restaurant, String> restColname;

    @FXML
    private TableView<Restaurant> restTbl;

    @FXML
    private TableColumn<Order, String> tbl3nameCol;

    @FXML
    private TableColumn<Order, Double> tbl3priceCol;

    @FXML
    private TableColumn<Order, String> tbl3statusCol;

    @FXML
    private TextField viewOrdersOfCustomerText;

    @FXML
    void AddMoney(ActionEvent event) {

    }

    @FXML
    void addNewcustomer(ActionEvent event) {

    }

    @FXML
    void customerSearch(ActionEvent event) {

    }

    @FXML
    void displayiRestsOfCustomer(ActionEvent event) {

    }

    @FXML
    void displayingallcustomers(ActionEvent event) {

    }

    @FXML
    void luxuryrest(ActionEvent event) {

    }

    @FXML
    void updateCustomerDetails(ActionEvent event) {

    }

    @FXML
    void viewOrdersOfCustomer(ActionEvent event) {

    }

    @FXML
    void withdrawingMoney(ActionEvent event) {

    }



}
