package controller;

import view.View;
import model.Model;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;

public class Controller implements EventHandler<ActionEvent> {

	private static Controller instance = null;
	private View view;
	private Model model;
	
	private Controller() {
		// TODO Auto-generated constructor stub
		view = View.getInstance();
		model = Model.getInstance();
	}

	public static Controller getInstance() {
		// TODO Auto-generated method stub
		if(instance == null) {
			instance = new Controller();
			System.out.println("Instance de la classe Controller créée !");
		}
		else {
			System.out.println("Instance de la classe Controller existante !");
		}
		
		return instance;
	}
	
	
	@Override
	public void handle(ActionEvent event) {
		// TODO Auto-generated method stub
		
	}

	public void start(Stage primaryStage) {
		// TODO Auto-generated method stub
		view.start(primaryStage, model);
		view.addOnAction(this);
		
	}

}
