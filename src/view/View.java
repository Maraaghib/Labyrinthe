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
	private Image image;
	private ImageView imageView;

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
		
		FileInputStream input = null;
		try {
			input = new FileInputStream("img/player.png");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		image = new Image(input);
		imageView = new ImageView(image);
		
		ViewFrame.drawSprite(model.getPlayer().getX(), model.getPlayer().getY(), imageView);

		primaryStage.show();
	}
	
	public void updatePlayerPosition(Model model) {
//		ViewFrame.drawSprite(model.getPlayer().getX(), model.getPlayer().getY(), imageView);
		int x = model.getPlayer().getX();
		int y = model.getPlayer().getY();
		double xt = (int) ((ViewFrame.WALL + x * (ViewFrame.WALL + ViewFrame.CELL)) * ViewFrame.SPAN);
		double yt = (int) ((ViewFrame.WALL + y * (ViewFrame.WALL + ViewFrame.CELL)) * ViewFrame.SPAN);
		imageView.setX(xt);
		imageView.setY(yt);
	}

	public void addOnAction(EventHandler<KeyEvent> eventHandler) {
		ViewFrame.scene.addEventHandler(KeyEvent.KEY_PRESSED, eventHandler);
		
	}

}
