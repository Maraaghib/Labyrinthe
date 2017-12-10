package view;

import javafx.stage.Stage;

import model.*;
import model.Labyrinth.Directions;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.paint.Color;

public class View {

	private static View instance = null;

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

		primaryStage.show();
	}

	public void addOnAction(EventHandler<ActionEvent> enventHandler) {

	}

}
