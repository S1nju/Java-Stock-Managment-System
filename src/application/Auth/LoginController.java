package application.Auth;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import Dashboard.GetData;
import SqlConnection.JDBC;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class LoginController implements Initializable {
	@FXML
	private AnchorPane mainpane;
	private Stage stage;
	@FXML
	private TextField UserName;
	@FXML
	private TextField Password;
	@FXML
	private Button login;
	private  AuthHandlerChain chainfields,chaindb;
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
	}
	public void close(ActionEvent e) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("logout");
		alert.setHeaderText("you are goint to loout");
		alert.setContentText("save?");
		if(alert.showAndWait().get()== ButtonType.OK) {
		stage = (Stage) mainpane.getScene().getWindow();
		stage.close();
		System.exit(0);
	}
	}
	public void Auth(ActionEvent ev) {
	chainfields = new CheckEmailandPassFields();
	chaindb = new AuthHandler();
	chainfields.SetNextChain(chaindb);
	
	login.setText("Loading ...");
	chainfields.CheckEmailAndPass(UserName.getText(), Password.getText(),(Stage)(mainpane.getScene().getWindow()));
	login.setText("Login");

	}
}
