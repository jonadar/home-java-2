package controllers;


import MyExceptions.InvalidPropertyException;
import MyExceptions.RiderNotFoundException;
import Utils.ConsolePrinter;
import application.Main;
import homework2.Order;
import homework2.RestAdmin;
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


public class riderPageController {
	
	private ObservableList<Rider> riders = FXCollections.observableArrayList(Main.DDB.getRiders());

    @FXML
    private TableColumn<Order, String> col2date;

    @FXML
    private TableColumn<Order, Integer> col2restaurant;

    @FXML
    private TableColumn<Order, String> col2status;

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
    private TextField searchRiderText;

    @FXML
    void addNewRider(ActionEvent event) {
    	// to do
    }

    @FXML
    void searchRider(ActionEvent event) {
    	String rider = searchRiderText.getText();
    	try {
			Rider rideerForTbl = Services.findRider(rider, Main.DDB.getRiders());
			riderTbl.setItems(FXCollections.observableArrayList(rideerForTbl));
    	} catch (Exception e) {
			ConsolePrinter.printError(e);	
    	}

    }

    @FXML
    void showAllRiders(ActionEvent event) {
    	riderTbl.setItems(riders);

    }

    @FXML
    private TextField showRiderOrdersText;
    
    @FXML
    void showRiderOrders(ActionEvent event) {
    	String riderId = showRiderOrdersText.getText();
    	try {
			Rider rider = Services.findRider(riderId, Main.DDB.getRiders());
			ordersTbl.setItems(FXCollections.observableArrayList(rider.getOrders()));
    	} catch (Exception e) {
			ConsolePrinter.printError(e);
    	}
    }

    

    @FXML
    void showRiderwithMostDeliveries(ActionEvent event) {
    	try {
    		Rider rider = Main.DDB.riderWithMostOrders();
        	if (rider != null) {
        		riderTbl.setItems(FXCollections.observableArrayList(rider));
        	}
        }catch (Exception e) {
    		ConsolePrinter.printError(e);
        }
    	
    	
    }

    @FXML
    void updateOrderStatus(ActionEvent event) {
    	// to do
    	Order selectedOrder = ordersTbl.getSelectionModel().getSelectedItem();
    	if (selectedOrder != null) {
    		try {
				selectedOrder.setDeliveryStatus("delivered");
			} catch (InvalidPropertyException e) {
				ConsolePrinter.printError(e);
			}
    	}

    }
    
    // to do
    @FXML
    private TableView<Order> ordersTbl;
    	
    @FXML
    private TableView<Rider> riderTbl;

    public void initialize() {
    	col2date.setCellValueFactory(new PropertyValueFactory<>("deliveryDate"));
    	col2restaurant.setCellValueFactory(new PropertyValueFactory<>("restaurantCode"));
    	col2status.setCellValueFactory(new PropertyValueFactory<>("restaurantCode"));
    	

    	colAvailable.setCellValueFactory(new PropertyValueFactory<>("isAvailable"));
    	colid.setCellValueFactory(new PropertyValueFactory<>("id"));
    	colname.setCellValueFactory(new PropertyValueFactory<>("fullName"));
    	colphone.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
    	colvehicle.setCellValueFactory(new PropertyValueFactory<>("vehicle"));
    	riderTbl.setItems(riders);
    }

}
