module GestionDeStock {
	requires javafx.controls;
	requires java.sql;
	requires javafx.graphics;
	requires javafx.fxml;
	requires javafx.base;
	requires mysql.connector.j;
	
	opens application to javafx.graphics, javafx.fxml;
	opens Dashboard to javafx.fxml,javafx.base;
}
