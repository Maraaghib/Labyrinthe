package view;

import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class View {
	
	private static View instance = null;
	
	private View() {
		
	}

	public static View getInstance() {
		// TODO Auto-generated method stub
		if(instance == null) {
			instance = new View();
			System.out.println("Instance de la classe View créée !");
		}
		else {
			System.out.println("Instance de classe View existante !");
		}
		
		return instance;
	}
	
	public void start(Stage primaryStage) {
		ViewFrame.drawFrame(primaryStage, 16, 16);
		
		primaryStage.show();
	}
	
	public void addOnAction(EventHandler<ActionEvent> enventHandler) {
		
	}

}
