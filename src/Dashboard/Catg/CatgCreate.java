package Dashboard.Catg;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;

import SqlConnection.JDBC;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class CatgCreate {

	
	public static void CatgCreates(String name) {
		 Connection con;
	 PreparedStatement prepare;	
	 String querry = "INSERT INTO catg (CID) VALUES (?)";
	   con = JDBC.getcon();
	   try {
		   if(name=="") {
	Alert alert2 = new Alert(AlertType.ERROR);
				
				alert2.setTitle("ERROR");
				alert2.setHeaderText("Blank Fields");
				alert2.setContentText("please Fill all the Texts Feilds");
				alert2.show();
			   
		   }else {
		   prepare = con.prepareStatement(querry);
		   prepare.setString(1, name);

		 prepare.executeUpdate();
		}
	   }catch(Exception e) {
	Alert alert2 = new Alert(AlertType.ERROR);
			
			alert2.setTitle("ERROR");
			alert2.setHeaderText("Error while adding");
			alert2.setContentText("please verify all the text fields "+e );
			alert2.show();
		   e.printStackTrace();
	   }
	}
}
