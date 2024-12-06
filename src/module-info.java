module GestionDeStock {
	requires javafx.controls;
	requires java.sql;
	requires javafx.graphics;
	requires javafx.fxml;
	requires javafx.base;
	
	opens application to javafx.graphics, javafx.fxml;
	opens application.Auth to javafx.graphics, javafx.fxml;
	
	opens Dashboard to javafx.fxml,javafx.base;
	opens Dashboard.Catg to javafx.fxml,javafx.base;
	opens Dashboard.Source to javafx.fxml,javafx.base;
	opens Dashboard.Type to javafx.fxml,javafx.base;
	opens Dashboard.Product to javafx.fxml,javafx.base;
}
