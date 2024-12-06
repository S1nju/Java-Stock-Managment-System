package Dashboard.Type;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import SqlConnection.JDBC;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class TypeCreate {

	
	public static void TypeCreates(String name) throws SQLException {
		 Connection con;
	 PreparedStatement prepare = null;	
	 String querry = "INSERT INTO taille (TID) VALUES (?)";
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
		   prepare.setString(1,name);

		 prepare.executeUpdate();
		}
	   }catch(Exception e) {
	Alert alert2 = new Alert(AlertType.ERROR);
			
			alert2.setTitle("ERROR");
			alert2.setHeaderText("Error while adding");
			alert2.setContentText("please verify all the text fields "+e );
			alert2.show();
		   e.printStackTrace();
	   }finally{
		   prepare.close();
		   con.close();
		 
		   
	   }
	}
}
