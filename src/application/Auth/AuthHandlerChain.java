package application.Auth;

import javafx.stage.Stage;

public interface AuthHandlerChain {
	public void SetNextChain(AuthHandlerChain next);
	public void CheckEmailAndPass(String email,String pass,Stage login);
	
	

}
