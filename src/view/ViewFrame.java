package view;

import javafx.scene.*;
import javafx.scene.image.ImageView;
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
	public static Scene scene;
	public static final Paint SCENE_COLOR = Color.WHITE;

	public ViewFrame() {
		
	}
	
	public static void drawFrame(Stage stage, int nbrX, int nbrY) {
		
		scene = new Scene(pane,
				((WALL + CELL) * nbrX + WALL) * SPAN, // The nbrX WALL+CELL and the last WALL
				((WALL + CELL) * nbrY + WALL) * SPAN); // --
				
		scene.setFill(SCENE_COLOR);
		
		Rectangle square;
		stage.setScene(scene);
		
		/* Tracer les quatre bordures (murs) du cadre */
		
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
		
		/* Placer les différents coins des cellules */
		
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
	
	public static void drawWall(int xs, int ys, int xt, int yt, Paint color) {
		
		int x = 0, y = 0, xspan = 0, yspan = 0;
		
		if (ys == yt) {
			x = ((WALL + CELL) + (WALL + CELL) * ((int)(xs + xt)/2)) * SPAN;
			y = (WALL + ys * (WALL + CELL)) * SPAN;
			xspan = WALL * SPAN;
			yspan = CELL * SPAN;
			Rectangle square  = new Rectangle(x, y, xspan, yspan);
			square.setFill(color);
			pane.getChildren().add(square);
		} else {
			x = (WALL + xs * (WALL + CELL)) * SPAN;
			y = ((WALL + CELL) + (WALL + CELL) * ((int)(ys + yt)/2)) * SPAN;
			xspan = CELL * SPAN;
			yspan = WALL * SPAN;
			Rectangle square = new Rectangle(x, y, xspan, yspan);
			square.setFill(color);
			pane.getChildren().add(square);
		}
		
	}
	
	public static void drawSprite(int x, int y, ImageView imageView) {
		
		double xt = (int) ((ViewFrame.WALL + x * (ViewFrame.WALL + ViewFrame.CELL)) * ViewFrame.SPAN);
		double yt = (int) ((ViewFrame.WALL + y * (ViewFrame.WALL + ViewFrame.CELL)) * ViewFrame.SPAN);
		imageView.setX(xt);
		imageView.setY(yt);
//		imageView.relocate(xt, yt);
		pane.getChildren().add(imageView);
	}
	
}
