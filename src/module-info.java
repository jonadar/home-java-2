module homework2 {
	requires javafx.controls;
	requires javafx.fxml;
	
	opens application to javafx.graphics, javafx.fxml, javafx.base;
	opens controllers to javafx.graphics, javafx.fxml, javafx.base;
	opens homework2 to javafx.base;
}
