package application;

import java.io.IOException;

import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;


public class WelcomeMC {

	@FXML
    private Button imageViewerButton;
	
//	private AnchorPane aPane;
	
	
	 @FXML
	    private void goToMain(ActionEvent event) throws IOException {

	        //Parent bookscene_parent =FXMLLoader.load(getClass().getResource("Book.fxml"));
	        //Scene bookscene_scene = new Scene(bookscene_parent);
	        Stage first_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
	        first_stage.setScene(Main.scene2);
	        first_stage.show();
	        
//	        intitializeFade();
	        
   }
}


/*
 * P.S. to Peter: I wanted to implement a fading transistion between the scenes to make it slightly more advanced but I didn't have much luck or the time!
 */
//	 public void intitializeFade() {
//		 System.out.println("Fade is initialized");
//		 FadeTransition fadeTransition = new FadeTransition();
//		 fadeTransition.setDuration(Duration.millis(1000));
//		 fadeTransition.setNode(aPane);
//		 fadeTransition.setFromValue(1);
//		 fadeTransition.setToValue(0);
//		 
//		 fadeTransition.setOnFinished(ActionEvent event) -> {
//			 
//		 };
//		 fadeTransition.play();
//		 
//	 }
	 