package Utils;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class ConsolePrinter {

	public static void printError(Exception e) {
		Alert alert = new Alert(AlertType.ERROR, e.getMessage());
		alert.showAndWait();
	}
	
	public static void printError(String message) {
		Alert alert = new Alert(AlertType.ERROR, message);
		alert.showAndWait();
	}
	
	public static void inform(String message) {
		Alert alert = new Alert(AlertType.INFORMATION, message);
		alert.showAndWait();
    }
}
