package view;

import javafx.stage.Stage;

import model.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 * Classe qui s'occupe de l'affichage à l'écran
 * @author hamza
 *
 */
public class View {

	private static View instance = null;
	private Image player;
	private ImageView playerView;
	private Image enemy;
	private ImageView enemyView;
	private Image dead;
	private ImageView deadView;
	private Image openedDoor;
	private ImageView openedDoorView;

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

	public void start(Stage primaryStage, Model model) {
		Graph graph = model.getGraph();
		primaryStage.setTitle("Labyrinthe");
		ViewFrame.drawFrame(primaryStage, Labyrinth.WIDTH, Labyrinth.HEIGHT);
		for (int x = 0; x < 16; x++) {
			for (int y = 0; y < 16; y++) {
				ViewFrame.drawWall(x, y, x+1, y, ViewFrame.WALL_COLOR); // Murs verticaux
				ViewFrame.drawWall(x, y, x, y+1, ViewFrame.WALL_COLOR); // Murs horizontaux
			}
		}

		for (Edge edge : graph.edgeSet()) {
//			System.out.println("Edge's type: "+edge);
			switch (edge.getType()) {
				case CORRIDOR:	
					ViewFrame.drawWall(edge.getSource().getX(), edge.getSource().getY(), edge.getTarget().getX(), edge.getTarget().getY(), ViewFrame.SCENE_COLOR);
					break;
				case OPENED_DOOR:
					ViewFrame.drawWall(edge.getSource().getX(), edge.getSource().getY(), edge.getTarget().getX(), edge.getTarget().getY(), ViewFrame.SCENE_COLOR);
					//Affichage de player
					FileInputStream openedDoorInput = null;
					try {
						openedDoorInput = new FileInputStream("img/door_open.png");
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					}
					openedDoor = new Image(openedDoorInput);
					openedDoorView = new ImageView(openedDoor);
					
					ViewFrame.drawSprite(edge.getSource().getX(), edge.getSource().getY(), openedDoorView);
					break;
				case CLOSED_DOOR:
					ViewFrame.drawWall(edge.getSource().getX(), edge.getSource().getY(), edge.getTarget().getX(), edge.getTarget().getY(), Color.RED);
					break;
			}
		}
		
		//Affichage de player
		FileInputStream playerInput = null;
		try {
			playerInput = new FileInputStream("img/player.png");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		player = new Image(playerInput);
		playerView = new ImageView(player);
		
		ViewFrame.drawSprite(model.getPlayer().getX(), model.getPlayer().getY(), playerView);

		//Affichage de enemy
		FileInputStream enemyInput = null;
		try {
			enemyInput = new FileInputStream("img/bad.png");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		enemy = new Image(enemyInput);
		enemyView = new ImageView(enemy);
		
		ViewFrame.drawSprite(model.getEnemy().getX(), model.getEnemy().getY(), enemyView);

		//Affichage de dead
		FileInputStream deadInput = null;
		try {
			deadInput = new FileInputStream("img/dead.png");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		dead = new Image(deadInput);
		deadView = new ImageView(dead);
				
		primaryStage.show();
	}
	
	public void updatePlayerPosition(Sprite sprite) {
		int x = sprite.getX();
		int y = sprite.getY();
		double xt = (int) ((ViewFrame.WALL + x * (ViewFrame.WALL + ViewFrame.CELL)) * ViewFrame.SPAN);
		double yt = (int) ((ViewFrame.WALL + y * (ViewFrame.WALL + ViewFrame.CELL)) * ViewFrame.SPAN);
		if (sprite instanceof Player) {
			playerView.setX(xt);
			playerView.setY(yt);
		}
		else if (sprite instanceof Enemy) {
			enemyView.setX(xt);
			enemyView.setY(yt);
		}
	}
	
//	public void updatePlayerPosition(Model model) {
//		int x = model.getPlayer().getX();
//		int y = model.getPlayer().getY();
//		double xt = (int) ((ViewFrame.WALL + x * (ViewFrame.WALL + ViewFrame.CELL)) * ViewFrame.SPAN);
//		double yt = (int) ((ViewFrame.WALL + y * (ViewFrame.WALL + ViewFrame.CELL)) * ViewFrame.SPAN);
//		playerView.setX(xt);
//		playerView.setY(yt);
//	}
//	
//	public void updateEnemyPosition(Model model) {
//		int x = model.getEnemy().getX();
//		int y = model.getEnemy().getY();
//		double xt = (int) ((ViewFrame.WALL + x * (ViewFrame.WALL + ViewFrame.CELL)) * ViewFrame.SPAN);
//		double yt = (int) ((ViewFrame.WALL + y * (ViewFrame.WALL + ViewFrame.CELL)) * ViewFrame.SPAN);
//		enemyView.setX(xt);
//		enemyView.setY(yt);
//	}

	public void addOnAction(EventHandler<KeyEvent> eventHandler) {
		ViewFrame.scene.addEventHandler(KeyEvent.KEY_PRESSED, eventHandler);
		
	}
	
	//Affiche l'écran de game over
	public void gameOver(Model model){
		int x = model.getPlayer().getX();
		int y = model.getPlayer().getY();
		double xt = (int) ((ViewFrame.WALL + x * (ViewFrame.WALL + ViewFrame.CELL)) * ViewFrame.SPAN);
		double yt = (int) ((ViewFrame.WALL + y * (ViewFrame.WALL + ViewFrame.CELL)) * ViewFrame.SPAN);
		//On affiche dead.png
		ViewFrame.drawSprite(model.getPlayer().getX(), model.getPlayer().getY(), deadView);
		//On rend invisible player et enemy
		playerView.setOpacity(0);
		enemyView.setOpacity(0);
	}

}
