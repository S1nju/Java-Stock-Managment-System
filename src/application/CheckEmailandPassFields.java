package application;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class CheckEmailandPassFields implements AuthHandlerChain {
	private AuthHandlerChain nextHandler;
	@Override
	public void SetNextChain(AuthHandlerChain next) {
		// TODO Auto-generated method stub
		this.nextHandler= next;
	}
	@Override
	public void CheckEmailAndPass(String username, String pass,Stage login) {
	if(username==""|| pass=="") {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Blank Field");	
		alert.setContentText("Please Fill the UserName and the password fields before login");
		alert.show();
	}else 
	if( username.length() <= 4 || pass.length()<=8) {
		Alert alert2 = new Alert(AlertType.ERROR);
		alert2.setTitle("Inncorect Fields");	
		alert2.setContentText("Please check the UserName and the password fields before login");
		alert2.show();
	}else
	{	nextHandler.CheckEmailAndPass(username, pass,login);
	
	}
	

	}}
