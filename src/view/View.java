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
		System.out.println("Instance de la classe View créée !");
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
		System.out.println(graph);
		primaryStage.setTitle("Labyrinth");
		ViewFrame.drawFrame(primaryStage, 16, 16);
		for (int x = 0; x < 16; x++) {
			for (int y = 0; y < 16; y++) {
				ViewFrame.drawWall(x, y, x+1, y, ViewFrame.WALL_COLOR); // Murs verticaux
				ViewFrame.drawWall(x, y, x, y+1, ViewFrame.WALL_COLOR); // Murs horizontaux
			}
		}
		
		for (int y = 0; y < Labyrinth.HEIGHT; y++) {
			for (int x = 0; x < Labyrinth.WIDTH; x++) {
				Vertex source = new Vertex(x, y);
				
				Vertex vertexNORTH = graph.getVertexByDir(source, Directions.NORTH);
				if(graph.containsEdge(source, vertexNORTH) && vertexNORTH != null) {
					ViewFrame.drawWall(source.getX(), source.getY(), vertexNORTH.getX(), vertexNORTH.getY(), Color.AQUA);
				}

				Vertex vertexSOUTH = graph.getVertexByDir(source, Directions.SOUTH);
				if(graph.containsEdge(source, vertexSOUTH) && vertexSOUTH != null) {
					ViewFrame.drawWall(source.getX(), source.getY(), vertexSOUTH.getX(), vertexSOUTH.getY(), Color.AQUA);
				}

				Vertex vertexEAST = graph.getVertexByDir(source, Directions.EAST);
				if(graph.containsEdge(source, vertexEAST) && vertexEAST != null) {
					ViewFrame.drawWall(source.getX(), source.getY(), vertexEAST.getX(), vertexEAST.getY(), Color.AQUA);
				}

				Vertex vertexWEST = graph.getVertexByDir(source, Directions.WEST);
				if(graph.containsEdge(source, vertexWEST) && vertexWEST != null) {
					ViewFrame.drawWall(source.getX(), source.getY(), vertexWEST.getX(), vertexWEST.getY(), Color.AQUA);
				}
			}
		}
		
		primaryStage.show();
	}
	
	public void addOnAction(EventHandler<ActionEvent> enventHandler) {
		
	}

}
