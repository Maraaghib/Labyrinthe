package view;

import javafx.stage.Stage;


import model.*;
import model.Labyrinth.Directions;
import model.Wall;
import model.Wall.WallType;
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
		primaryStage.setTitle("Labyrinth");
		ViewFrame.drawFrame(primaryStage, Labyrinth.WIDTH, Labyrinth.HEIGHT);
		for (int x = 0; x < Labyrinth.WIDTH; x++) {
			for (int y = 0; y < Labyrinth.HEIGHT; y++) {
				if(model.getWallType(x, y, Directions.EAST) == WallType.WALL){
					ViewFrame.drawWall(x, y, x+1, y, ViewFrame.WALL_COLOR); // Murs verticaux
				}
				if(model.getWallType(x, y, Directions.SOUTH) == WallType.WALL){
					ViewFrame.drawWall(x, y, x, y+1, ViewFrame.WALL_COLOR); // Murs horizontaux
				}
			}
		}

		primaryStage.show();
	}

	public void addOnAction(EventHandler<ActionEvent> enventHandler) {

	}

}
