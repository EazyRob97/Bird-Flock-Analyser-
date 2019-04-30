package application;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;


public class Main extends Application {
	
	
	static Stage window;
	  public static Scene scene1, scene2;
	
	  
	  public static void main(String[] args) {
	    	launch(args);
	    }

	  
	  
	  
	  
	@Override
	    public void start(Stage primaryStage) {
	        try {
	        	window = primaryStage;
	        	window.setTitle("Robert's Image Processor System");
	            loadAllScenes();

	            primaryStage.setScene(scene1);
	            primaryStage.show();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }

	    public void loadAllScenes() {
	        Parent root;
	        try {
	            root = FXMLLoader.load(getClass().getResource("FirstPage.fxml"));
	            scene1 = new Scene(root, 400, 400);
	            scene1.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

	            root = FXMLLoader.load(getClass().getResource("MainScene.fxml"));
	            scene2 = new Scene(root);
	           
	        } 
	        catch (IOException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }

	    }
}
