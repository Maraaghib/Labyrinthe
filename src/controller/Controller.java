package controller;

import view.View;
import view.ViewFrame;
import model.Labyrinth.Directions;
import model.Model;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class Controller implements EventHandler<KeyEvent> {

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
	public void handle(KeyEvent event) {
		// TODO Auto-generated method stub
//		if (event.getSource() instanceof ImageView) {
//			
//		}
		if (event.getCode() == KeyCode.RIGHT) {
			model.getPlayer().move(model.getLabyrinth(), Directions.EAST);
			view.updatePlayerPosition(model);
			model.getEnemy().move(model.getLabyrinth(), Directions.EAST);
			view.updateEnemyPosition(model);
		}
		else if (event.getCode() == KeyCode.LEFT) {
			model.getPlayer().move(model.getLabyrinth(), Directions.WEST);
			view.updatePlayerPosition(model);
		}
		else if (event.getCode() == KeyCode.UP) {
			model.getPlayer().move(model.getLabyrinth(), Directions.NORTH);
			view.updatePlayerPosition(model);
		}
		else if (event.getCode() == KeyCode.DOWN) {
			model.getPlayer().move(model.getLabyrinth(), Directions.SOUTH);
			view.updatePlayerPosition(model);
		}
	}

	public void start(Stage primaryStage) {
		// TODO Auto-generated method stub
		view.start(primaryStage, model);
		view.updatePlayerPosition(model);
		view.addOnAction(this);
		
	}

}
