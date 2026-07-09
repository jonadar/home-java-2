package controllers;

import homework2.Customer;
import homework2.Rider;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class reportController {

    @FXML
    private TableView<Rider> riderTbl;

    @FXML
    private TableColumn<?, ?> codeCol;

    @FXML
    private TableColumn<?, ?> codeCol1;

    @FXML
    private TableColumn<Rider, Boolean> colAvailable;
    @FXML
    private TableColumn<Rider, String> colid;
    @FXML
    private TableColumn<Rider, String> colname;
    @FXML
    private TableColumn<Rider, String> colphone;
    @FXML
    private TableColumn<Rider, String> colvehicle;


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
    private TableColumn<?, ?> customerCol;

    @FXML
    private TableColumn<?, ?> deliveryDateCol;

    @FXML
    private TableColumn<?, ?> driverIdCol;

    @FXML
    private TableColumn<?, ?> feeCol;

    @FXML
    private TableColumn<?, ?> isOpenCol;

    @FXML
    private TableColumn<?, ?> nameCol;

    @FXML
    private TableColumn<?, ?> orderDateCol;

    @FXML
    private TableColumn<?, ?> priceCol;

    @FXML
    private TableColumn<?, ?> ratingCol;

    @FXML
    private TableView<?> resTbl;

    @FXML
    private TableColumn<?, ?> resturantCol;

    @FXML
    private TableView<?> riderTbl;

    @FXML
    private TableColumn<?, ?> statusCol;

    @FXML
    private TableView<?> tblAllOrders;

    @FXML
    private TableColumn<?, ?> typeCol;

    @FXML
    void showAllAvailableRiders(ActionEvent event) {

    }

    @FXML
    void showAllLuxuryRestaurants(ActionEvent event) {

    }

    @FXML
    void showAllOpenRestaurants(ActionEvent event) {

    }

    @FXML
    void showTotalPayments(ActionEvent event) {

    }

    @FXML
    void sortCustomersByCreditBalance(ActionEvent event) {

    }

    @FXML
    void sortCustomersByFirstName(ActionEvent event) {

    }

    @FXML
    void sortOrdersByFinalPrice(ActionEvent event) {

    }

    @FXML
    void sortRestaurantsByRating(ActionEvent event) {

    }

    @FXML
    void sortRidersByDeliveryCount(ActionEvent event) {

    }

}
