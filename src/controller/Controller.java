package controller;

import view.View;
import model.Labyrinth.Directions;
import model.Model;

import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class Controller implements EventHandler<KeyEvent> {

	private static Controller instance = null;
	private View view;
	private Model model;
	private boolean gameOver = false;
	
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
		
		if(!gameOver){
			if (event.getCode() == KeyCode.RIGHT) {
				if(model.getPlayer().move(model.getLabyrinth(), Directions.EAST)){
					if(model.getLabyrinth().checkDead()){
						view.gameOver(model);
						gameOver = true;
						return;
					}
					model.getEnemy().move(model.getLabyrinth(), model.getEnemy().getX(), model.getEnemy().getY());
				}
			}
			else if (event.getCode() == KeyCode.LEFT) {
				if(model.getPlayer().move(model.getLabyrinth(), Directions.WEST)){
					if(model.getLabyrinth().checkDead()){
						view.gameOver(model);
						gameOver = true;
						return;
					}
					model.getEnemy().move(model.getLabyrinth(), model.getEnemy().getX(), model.getEnemy().getY());
				}
			}
			else if (event.getCode() == KeyCode.UP) {
				if(model.getPlayer().move(model.getLabyrinth(), Directions.NORTH)){
					if(model.getLabyrinth().checkDead()){
						view.gameOver(model);
						gameOver = true;
						return;
					}
				model.getEnemy().move(model.getLabyrinth(), model.getEnemy().getX(), model.getEnemy().getY());
				}
			}
			else if (event.getCode() == KeyCode.DOWN) {
				if(model.getPlayer().move(model.getLabyrinth(), Directions.SOUTH)){
					if(model.getLabyrinth().checkDead()){
						view.gameOver(model);
						gameOver = true;
						return;
					}
				model.getEnemy().move(model.getLabyrinth(), model.getEnemy().getX(), model.getEnemy().getY());
				}
			}		
			
			if(model.getLabyrinth().checkDead()){
				view.gameOver(model);
				gameOver = true;
			}
			else {
				view.updatePlayerPosition(model);
				view.updateEnemyPosition(model);
			}
		}
	}

	public void start(Stage primaryStage) {
		// TODO Auto-generated method stub
		view.start(primaryStage, model);
		view.updatePlayerPosition(model);
		view.addOnAction(this);
		
	}

}
