package Dashboard.Source;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;

import SqlConnection.JDBC;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;

public class SourceDelete {

	
	public static void SourceDeletes(String id) {
		 Connection con;
		 Statement s;	
	 String sql = "DELETE FROM distributeur WHERE DID= "+id;
	   con = JDBC.getcon();
	   try {
		   if(id=="") {
				Alert alert2 = new Alert(AlertType.ERROR);
							
							alert2.setTitle("ERROR");
							alert2.setHeaderText("Blank Fields");
							alert2.setContentText("please select one element before deleting");
							alert2.show();
						   
					   }else {
	Alert alert2 = new Alert(AlertType.CONFIRMATION);
				
				alert2.setTitle("CONFIRMATION");
				alert2.setHeaderText("Confirm command");
				alert2.setContentText("Are you sure that you want to delete item with id= "+id );
				
				if(alert2.showAndWait().get()== ButtonType.OK) {
					 s  = con.createStatement();
					   s.executeUpdate(sql);	
					   
						 
				
				
				
				}
					   }
		   
	   }catch(Exception e) {
	Alert alert2 = new Alert(AlertType.ERROR);
			
			alert2.setTitle("ERROR");
			alert2.setHeaderText("Error while Deleting");
			alert2.setContentText("please verify all the text fields "+e );
			alert2.show();
		   e.printStackTrace();
	   }
	   
	}
}
