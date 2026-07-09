package application;
	
import java.io.IOException;
import java.util.Stack;

import Utils.ConsolePrinter;
import Utils.SaveManager;
import homework2.Customer;
import homework2.DeliveryDataBase;
import homework2.FastFoodRestaurant;
import homework2.Order;
import homework2.PremiumRestaurant;
import homework2.RestAdmin;
import homework2.Restaurant;
import homework2.Rider;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;


public class Main extends Application {
	public final static DeliveryDataBase DDB = new DeliveryDataBase();
	public static Stage stage;
	public static Stack<String> sceneStack = new Stack<>();
	
	@Override
	public void start(Stage primaryStage) {
		stage = primaryStage;
		
		SaveManager.load(DDB);
//		SaveManager.save(DDB);
		
		setScene("/application/loginPage.fxml");
//		setScene("/adminPages/restaurantPage.fxml");

		primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	public static void setScene(String path) {
		try{			
			Parent root = FXMLLoader.load(Main.class.getResource(path));
			
			Scene scene = new Scene(root);
			
			stage.setScene(scene);
			sceneStack.push(path);
		} catch (IOException e) {
			ConsolePrinter.printError(e.getMessage() + ". could not find view file " + path);
		} catch (Exception e) {
			ConsolePrinter.printError(e);
		}
	}
	
	public static void goBackScene() {
		try {
			if (sceneStack.size() >= 2) {			
				sceneStack.pop();
				Parent root = FXMLLoader.load(Main.class.getResource(sceneStack.peek()));
				
				Scene scene = new Scene(root);
				
				stage.setScene(scene);
			}
		} catch (IOException e) {
			ConsolePrinter.printError(e.getMessage() + ". could not find view file.");
		} catch (Exception e) {
			ConsolePrinter.printError(e);
		}
	}
}
