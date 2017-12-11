package view;

import javafx.stage.Stage;

import model.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;

public class View {

	private static View instance = null;
	private Image player;
	private ImageView playerView;
	private Image enemy;
	private ImageView enemyView;

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
			ViewFrame.drawWall(edge.getSource().getX(), edge.getSource().getY(), edge.getTarget().getX(), edge.getTarget().getY(), ViewFrame.SCENE_COLOR);
		}
		
		//Affichage de player
		FileInputStream playerInput = null;
		try {
			playerInput = new FileInputStream("img/player.png");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		enemy = new Image(enemyInput);
		enemyView = new ImageView(enemy);
		
		ViewFrame.drawSprite(model.getEnemy().getX(), model.getEnemy().getY(), enemyView);

		primaryStage.show();
	}
	
	public void updatePlayerPosition(Model model) {
//		ViewFrame.drawSprite(model.getPlayer().getX(), model.getPlayer().getY(), imageView);
		int x = model.getPlayer().getX();
		int y = model.getPlayer().getY();
		double xt = (int) ((ViewFrame.WALL + x * (ViewFrame.WALL + ViewFrame.CELL)) * ViewFrame.SPAN);
		double yt = (int) ((ViewFrame.WALL + y * (ViewFrame.WALL + ViewFrame.CELL)) * ViewFrame.SPAN);
		playerView.setX(xt);
		playerView.setY(yt);
	}
	
	public void updateEnemyPosition(Model model) {
//		ViewFrame.drawSprite(model.getPlayer().getX(), model.getPlayer().getY(), imageView);
		int x = model.getEnemy().getX();
		int y = model.getEnemy().getY();
		double xt = (int) ((ViewFrame.WALL + x * (ViewFrame.WALL + ViewFrame.CELL)) * ViewFrame.SPAN);
		double yt = (int) ((ViewFrame.WALL + y * (ViewFrame.WALL + ViewFrame.CELL)) * ViewFrame.SPAN);
		enemyView.setX(xt);
		enemyView.setY(yt);
	}

	public void addOnAction(EventHandler<KeyEvent> eventHandler) {
		ViewFrame.scene.addEventHandler(KeyEvent.KEY_PRESSED, eventHandler);
		
	}

}
