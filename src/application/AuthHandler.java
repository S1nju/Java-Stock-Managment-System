package application;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import Dashboard.GetData;
import SqlConnection.JDBC;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
public class AuthHandler implements AuthHandlerChain {
	private AuthHandlerChain nextHandler;
	private PreparedStatement st;
	private ResultSet res;
	private Connection con;
	private double x,y;
	@Override
	public void SetNextChain(AuthHandlerChain next) {
		
		// TODO Auto-generated method stub
		this.nextHandler= next;
	}
	@Override
	public void CheckEmailAndPass(String username, String pass, Stage loginpane) {
	
	if((username!=""&& pass!="") &&( username.length()>4 &&pass.length()>8)) {
		try {
			con=JDBC.getcon();
			String q ="Select * from auth Where username =? and pass=?";
			st=con.prepareStatement(q);
			st.setString(1, username);
			st.setString(2, pass);
			res= st.executeQuery();
			if(res.next()) {
				GetData.username=username;
				GetData.role = res.getInt("role");
				Parent root =FXMLLoader.load(getClass().getResource("/Dashboard/Dashboard.fxml"));
				Stage stage = new Stage();
				root.setOnMousePressed((MouseEvent e)->{
					x= e.getSceneX();
					y=e.getSceneX();
				});
				root.setOnMouseDragged((MouseEvent e)->{
					stage.setX(e.getScreenX()-x);
					stage.setY(e.getScreenY()-y);
					stage.setOpacity(.9);
				});
				root.setOnMouseReleased((MouseEvent e)->{
			
					stage.setOpacity(1);
				});
				
				stage.initStyle(StageStyle.TRANSPARENT);
		        loginpane.hide();
				Scene scene = new Scene (root);
				stage.setScene(scene);
				stage.show();
				
				
				
			}else {
				Alert alert2 = new Alert(AlertType.ERROR);
				alert2.setTitle("Inncorect Fields");	
				alert2.setContentText("Please check the UserName and the password fields before login");
				alert2.show();
			}
			
		}catch(SQLException e) {
			
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}

	}}

