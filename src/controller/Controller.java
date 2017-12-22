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
 * @author hamza
 *
 */

public class Controller extends Thread implements EventHandler<KeyEvent> {

	private static Controller instance = null;
	private View view;
	private Model model;
	private boolean gameOver = false;
	
	private Controller() {
		// TODO Auto-generated constructor stub
		model = Model.getInstance();
		view = View.getInstance();
		this.start();
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
//					model.getEnemy().move(model.getLabyrinth(), model.getEnemy().getX(), model.getEnemy().getY());
				}
			}
			else if (event.getCode() == KeyCode.LEFT) {
				if(model.getPlayer().move(model.getLabyrinth(), Directions.WEST)){
					if(model.getLabyrinth().checkDead()){
						view.gameOver(model);
						gameOver = true;
						return;
					}
//					model.getEnemy().move(model.getLabyrinth(), model.getEnemy().getX(), model.getEnemy().getY());
				}
			}
			else if (event.getCode() == KeyCode.UP) {
				if(model.getPlayer().move(model.getLabyrinth(), Directions.NORTH)){
					if(model.getLabyrinth().checkDead()){
						view.gameOver(model);
						gameOver = true;
						return;
					}
//				model.getEnemy().move(model.getLabyrinth(), model.getEnemy().getX(), model.getEnemy().getY());
				}
			}
			else if (event.getCode() == KeyCode.DOWN) {
				if(model.getPlayer().move(model.getLabyrinth(), Directions.SOUTH)){
					if(model.getLabyrinth().checkDead()){
						view.gameOver(model);
						gameOver = true;
						return;
					}
//				model.getEnemy().move(model.getLabyrinth(), model.getEnemy().getX(), model.getEnemy().getY());
				}
			}		
			
			if(model.getLabyrinth().checkDead()){
				view.gameOver(model);
				gameOver = true;
			}
			else {
				view.updateSpritePosition(model.getPlayer());
				view.updateSpritePosition(model.getEnemy());
//				view.updatePlayerPosition(model);
//				view.updateEnemyPosition(model);
			}
		}
	}

	public void start(Stage primaryStage) {
		// TODO Auto-generated method stub
		view.start(primaryStage, model);
//		view.updatePlayerPosition(model);
		view.updateSpritePosition(model.getPlayer());
		view.addOnAction(this);
		
	}

	/* (non-Javadoc)
	 * @see java.lang.Thread#run()
	 */
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true) {
//			System.out.println("Test Thread dans Controller");

			model.getEnemy().move(model.getLabyrinth(), model.getEnemy().getX(), model.getEnemy().getY());
			view.updateSpritePosition(model.getEnemy());
			if(model.getLabyrinth().checkDead()){
				// The user interface cannot be directly updated from a non-application thread. Instead, use Platform.runLater()
				Platform.runLater(new Runnable() {
				    @Override
				    public void run() {
				        // if you change the UI, do it here !
				    	view.gameOver(model);
				    }
				});
				gameOver = true;
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
