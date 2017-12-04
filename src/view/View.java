package view;

import javafx.stage.Stage;
import model.Labyrinth;
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
	
	public void start(Stage primaryStage) {
		primaryStage.setTitle("Labyrinth");
		ViewFrame.drawFrame(primaryStage, 16, 16);
		for (int x = 0; x < 16; x++) {
			for (int y = 0; y < 16; y++) {
				ViewFrame.drawWall(x, y, x+1, y, ViewFrame.WALL_COLOR);
				ViewFrame.drawWall(x, y, x, y+1, ViewFrame.WALL_COLOR);
			}
		}
		
		for (int y = 0; y < Labyrinth.HEIGHT; y++) {
			for (int x = 0; x < Labyrinth.WIDTH; x++) {
//				Effacer les mûrs sur le chemin
			}
		}
		
		primaryStage.show();
	}
	
	public void addOnAction(EventHandler<ActionEvent> enventHandler) {
		
	}

}
