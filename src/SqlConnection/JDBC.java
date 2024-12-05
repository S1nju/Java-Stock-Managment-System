package SqlConnection;

import java.sql.Connection;
import java.sql.DriverManager;

import java.sql.SQLException;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class JDBC  {

	
	public static Connection getcon() {
			String url="jdbc:mysql://localhost:3306/miniprj";
		try {
			Connection con = DriverManager.getConnection(url,"root","");
		return con;
			}catch(SQLException e) {
				e.printStackTrace();
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("NO DATABASE Conection");
				alert.setHeaderText("Database error");
				alert.setContentText("Please connect to the database");
				alert.show();
				
			
			}
		return null;
		
	}

}
