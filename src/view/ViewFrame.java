package view;

import javafx.scene.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.stage.*;

public class ViewFrame {
	
	static final int SPAN = 4; // Pixels for a unit
	static final int WALL = 2; // Thickness of the walls (in units)
	static final int CELL = 9; // Size of the cells (in units)
	public static final Paint WALL_COLOR = Color.BURLYWOOD;
	private static BorderPane pane = new BorderPane();
	private static Scene scene;
	private static final Paint SCENE_COLOR = Color.WHITE;

	public ViewFrame() {
		
	}
	
	public static void drawFrame(Stage stage, int nbrX, int nbrY) {
		
		scene = new Scene(pane,
				((WALL + CELL) * nbrX + WALL) * SPAN, // The nbrX WALL+CELL and the last WALL
				((WALL + CELL) * nbrY + WALL) * SPAN); // --
				
		scene.setFill(SCENE_COLOR);
		
		Rectangle square;
		stage.setScene(scene);
		
		square = new Rectangle(0, 0, SPAN * (nbrX * (CELL + WALL) + WALL), WALL * SPAN);
		square.setFill(WALL_COLOR);
		pane.getChildren().add(square);
		
		square = new Rectangle(0, SPAN * (nbrY * (CELL + WALL)), SPAN * (nbrX * (CELL + WALL) + WALL), WALL * SPAN);
		square.setFill(WALL_COLOR);
		pane.getChildren().add(square);
		
		square = new Rectangle(0, 0, WALL * SPAN, SPAN * (nbrY * (CELL + WALL) + WALL));
		square.setFill(WALL_COLOR);
		pane.getChildren().add(square);
		
		square = new Rectangle(SPAN * (nbrX * (CELL + WALL)), 0, WALL * SPAN, SPAN * (nbrY * (CELL + WALL) + WALL));
		square.setFill(WALL_COLOR);
		pane.getChildren().add(square);
		
		for (int x = 0; x < nbrX-1; ++x) {
			int offsetX = ((WALL + CELL) + (WALL + CELL) * x) * SPAN;
			for (int y = 0; y < nbrY-1; ++y) {
				int offsetY = ((WALL + CELL) + (WALL + CELL) * y) * SPAN;
				square = new Rectangle(offsetX, offsetY, WALL * SPAN, WALL * SPAN);
				square.setFill(WALL_COLOR);
				pane.getChildren().add(square);
			}
		}
		
	}
	
}
