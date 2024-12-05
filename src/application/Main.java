package application;
	
import javafx.application.Application;

import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.input.MouseEvent;



public class Main extends Application {
	private double x=0,y=0;

	@Override
	public void start(Stage primaryStage) {
		
		try {
			Parent root =  FXMLLoader.load(getClass().getResource("login.fxml"));
			Scene scene = new Scene(root);
			root.setOnMousePressed((MouseEvent e)->{
				x=e.getSceneX();
				y=e.getSceneX();
			});
			root.setOnMouseDragged((MouseEvent e)->{
				primaryStage.setX(e.getScreenX()-x);
				primaryStage.setY(e.getScreenY()-y);
				primaryStage.setOpacity(.9);});
			root.setOnMouseReleased((MouseEvent e)->{
		
				primaryStage.setOpacity(1);
			});
			primaryStage.initStyle(StageStyle.UNDECORATED);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	
	}
}
