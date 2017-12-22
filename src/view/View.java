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
 * @author Seerigne Amsatou SEYE & Fabien JACQUES
 *
 */
public class View {

	private static View instance = null;
	private Image player;
	private ImageView playerView;
	private Image enemy;
	private ImageView enemyView;
	private Image win;
	private ImageView winView;
	private Image dead;
	private ImageView deadView;
	private Image openedDoor;
	private ImageView openedDoorView;

	/**
	 * Crée une instance de View en initilisant les obbjets figurant dans le labyrinthe
	 */
	private View() {
		//Création de l'image pour Player
		FileInputStream playerInput = null;
		try {
			playerInput = new FileInputStream("img/player.png");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		player = new Image(playerInput);
		playerView = new ImageView(player);
		
		//Création de l'image pour Enemy
		FileInputStream enemyInput = null;
		try {
			enemyInput = new FileInputStream("img/bad.png");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		enemy = new Image(enemyInput);
		enemyView = new ImageView(enemy);
		
		//Création de l'image pour openDoor
		FileInputStream openedDoorInput = null;
		try {
			openedDoorInput = new FileInputStream("img/door_open.png");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		openedDoor = new Image(openedDoorInput);
		openedDoorView = new ImageView(openedDoor);
		
		//Création de l'image pour Dead
		FileInputStream winInput = null;
		try {
			winInput = new FileInputStream("img/win.png");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		win = new Image(winInput);
		winView = new ImageView(win);
		
		//Création de l'image pour Dead
		FileInputStream deadInput = null;
		try {
			deadInput = new FileInputStream("img/dead.png");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		dead = new Image(deadInput);
		deadView = new ImageView(dead);
	}

	/**
	 * Retourne une instance unique de View
	 * @return Une instance de View
	 */
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
		
		// Construction des murs verticaux et horizontaux
		for (int x = 0; x < 16; x++) {
			for (int y = 0; y < 16; y++) {
				ViewFrame.drawWall(x, y, x+1, y, ViewFrame.WALL_COLOR); // Murs verticaux
				ViewFrame.drawWall(x, y, x, y+1, ViewFrame.WALL_COLOR); // Murs horizontaux
			}
		}

		// construction des chemins et affichage des portes
		for (Edge edge : graph.edgeSet()) {
			switch (edge.getType()) {
				case CORRIDOR:	
					ViewFrame.drawWall(edge.getSource().getX(), edge.getSource().getY(), edge.getTarget().getX(), edge.getTarget().getY(), ViewFrame.SCENE_COLOR);
					break;
				case OPENED_DOOR:
					ViewFrame.drawWall(edge.getSource().getX(), edge.getSource().getY(), edge.getTarget().getX(), edge.getTarget().getY(), ViewFrame.SCENE_COLOR);
					
					
					ViewFrame.drawSprite(edge.getSource().getX(), edge.getSource().getY(), openedDoorView);
					break;
				case CLOSED_DOOR:
					ViewFrame.drawWall(edge.getSource().getX(), edge.getSource().getY(), edge.getTarget().getX(), edge.getTarget().getY(), Color.RED);
					break;
			}
		}
		
		// Affihage du joueur et des méchants
		ViewFrame.drawSprite(model.getPlayer().getX(), model.getPlayer().getY(), playerView);
		ViewFrame.drawSprite(model.getEnemy().getX(), model.getEnemy().getY(), enemyView);
				
		primaryStage.show();
	}
	
	/**
	 * Met à jour les positions des sprites
	 * @param sprite
	 * 			Le joueur ou les ennemis
	 */
	public void updateSpritePosition(Sprite sprite) {
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

	public void addOnAction(EventHandler<KeyEvent> eventHandler) {
		ViewFrame.scene.addEventHandler(KeyEvent.KEY_PRESSED, eventHandler);
		
	}
	
	/**
	 * Met à jour l'affichage à la fin du jeu
	 * @param model
	 * 			Le model qui contient les données du labyrinthe
	 * @param gameWon
	 * 			Variable booléenne pour tester si le joueur a gagné
	 * @param gameOver
	 * 			Variable booléenne pour tester si le joueur est perdu
	 */
	public void endOfGame(Model model, boolean gameWon, boolean gameOver) {
		int x = model.getPlayer().getX();
		int y = model.getPlayer().getY();
		if (gameWon) {
			ViewFrame.drawSprite(x, y, winView);
		}
		else if (gameOver) {
			ViewFrame.drawSprite(x, y, deadView);
		}
		playerView.setOpacity(0);
		enemyView.setOpacity(0);
	}

}
