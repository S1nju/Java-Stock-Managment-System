package Dashboard.Source;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import SqlConnection.JDBC;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
public class SourceUpdate {
	public static void SourceUpdates(String name,String firstName ,String willaya,int id) {
		 Connection con;
	Statement s;	
	  String sql = "UPDATE distributeur SET nom= '"+ name +"',prenom='"+ firstName+"',willaya='"+willaya+"' WHERE DID="+id+"";
	   con = JDBC.getcon();
	   try {
		   if(name==""||firstName==""||willaya==""||id==0) {
	Alert alert2 = new Alert(AlertType.ERROR);
				alert2.setTitle("ERROR");
				alert2.setHeaderText("Blank Fields");
				alert2.setContentText("please Fill all the Texts Feilds");
				alert2.show();
		   }else {
	Alert alert2 = new Alert(AlertType.CONFIRMATION);
				alert2.setTitle("CONFIRMATION");
				alert2.setHeaderText("Confirm command");
				alert2.setContentText("Are you sure that you want to edit "+ name);
				
				if(alert2.showAndWait().get()== ButtonType.OK) {
					 s  = con.createStatement();
					   s.executeUpdate(sql);	
				}
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
