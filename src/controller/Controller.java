package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;

public class Controller implements EventHandler<ActionEvent> {

	private static Controller instance = null;
	
	private Controller() {
		// TODO Auto-generated constructor stub
		
	}

	public static void makeInstance() {
		// TODO Auto-generated method stub
		if(instance == null) {
			instance = new Controller();
			System.out.println("Instance de la classe Controller créée !");
		}
		else {
			System.out.println("Instance de la classe Controller existante !");
		}
	}
	
	
	@Override
	public void handle(ActionEvent event) {
		// TODO Auto-generated method stub
		
	}

	public static void start(Stage stage) {
		// TODO Auto-generated method stub
		
	}

}
