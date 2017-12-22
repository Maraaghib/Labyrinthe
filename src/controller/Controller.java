package controller;

import view.View;
import model.Labyrinth.Directions;
import model.Model;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

/**
 * Classe qui crée View et Model et qui récupère les entrées du clavier (singleton)
 * @author Seerigne Amsatou SEYE & Fabien JACQUES
 *
 */

public class Controller extends Thread implements EventHandler<KeyEvent> {

	private static Controller instance = null;
	private View view;
	private Model model;
	private boolean gameOver = false;
	private boolean gameWon = false;
	
	private Controller() {
		// TODO Auto-generated constructor stub
		model = Model.getInstance();
		view = View.getInstance();
		this.start();
	}

	
	/**
	 * Retourne une instance unique de la classe Controller
	 * @return une instance de la classe Controller
	 */
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
		
		if(!gameOver && !gameWon){
			if (event.getCode() == KeyCode.RIGHT) {
				model.getPlayer().move(model.getLabyrinth(), Directions.EAST);
			}
			else if (event.getCode() == KeyCode.LEFT) {
				model.getPlayer().move(model.getLabyrinth(), Directions.WEST);
			}
			else if (event.getCode() == KeyCode.UP) {
				model.getPlayer().move(model.getLabyrinth(), Directions.NORTH);
			}
			else if (event.getCode() == KeyCode.DOWN) {
				model.getPlayer().move(model.getLabyrinth(), Directions.SOUTH);
			}		
			
			if(model.getLabyrinth().checkDead()){
				view.endOfGame(model, gameWon, gameOver);
				gameOver = true;
			}
			else if (model.getLabyrinth().hasWon()) {
				view.endOfGame(model, gameWon, gameOver);
				gameWon = true;
			}
			else {
				view.updateSpritePosition(model.getPlayer());
				view.updateSpritePosition(model.getEnemy());
			}
		}
	}

	public void start(Stage primaryStage) {
		// TODO Auto-generated method stub
		view.start(primaryStage, model);
		view.updateSpritePosition(model.getPlayer());
		view.addOnAction(this);
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true) {
			model.getEnemy().move(model.getLabyrinth(), model.getEnemy().getX(), model.getEnemy().getY());
			view.updateSpritePosition(model.getEnemy());
			
			// Vérifier si le joueur est mort
			if(model.getLabyrinth().hasWon() || model.getLabyrinth().checkDead()){
				// The user interface cannot be directly updated from a non-application thread. Instead, use Platform.runLater()
				Platform.runLater(new Runnable() {
				    @Override
				    public void run() {
				        // if you change the UI, do it here !
				    	view.endOfGame(model, gameWon, gameOver);
				    }
				});
				gameWon = model.getLabyrinth().hasWon();
				gameOver = model.getLabyrinth().checkDead();
				return;
			}

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
