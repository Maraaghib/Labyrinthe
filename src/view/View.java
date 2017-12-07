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

		/*
		for (Edge edge : graph.edgeSet()) {

			ViewFrame.drawWall(edge.getSource().getX(), edge.getSource().getY(), edge.getTarget().getX(), edge.getTarget().getY(), ViewFrame.SCENE_COLOR);

			ViewFrame.drawWall(edge.getSource().getX(), edge.getSource().getY(), edge.getTarget().getX(), edge.getTarget().getY(), ViewFrame.SCENE_COLOR);

			ViewFrame.drawWall(edge.getSource().getX(), edge.getSource().getY(), edge.getTarget().getX(), edge.getTarget().getY(), ViewFrame.SCENE_COLOR);

			ViewFrame.drawWall(edge.getSource().getX(), edge.getSource().getY(), edge.getTarget().getX(), edge.getTarget().getY(), ViewFrame.SCENE_COLOR);
		}
		*/

		/*for (int y = 0; y < Labyrinth.HEIGHT; y++) {
			for (int x = 0; x < Labyrinth.WIDTH; x++) {
				Vertex source = new Vertex(x, y);

					Vertex vertexNORTH = graph.getVertexByDir(source, Directions.NORTH);
					if(graph.containsEdge(source, vertexNORTH) && vertexNORTH != null) {
						ViewFrame.drawWall(source.getX(), source.getY(), vertexNORTH.getX(), vertexNORTH.getY(), ViewFrame.SCENE_COLOR);
					}

					Vertex vertexSOUTH = graph.getVertexByDir(source, Directions.SOUTH);
					if(graph.containsEdge(source, vertexSOUTH) && vertexSOUTH != null) {
						ViewFrame.drawWall(source.getX(), source.getY(), vertexSOUTH.getX(), vertexSOUTH.getY(), ViewFrame.SCENE_COLOR);
					}

					Vertex vertexEAST = graph.getVertexByDir(source, Directions.EAST);
					if(graph.containsEdge(source, vertexEAST) && vertexEAST != null) {
						ViewFrame.drawWall(source.getX(), source.getY(), vertexEAST.getX(), vertexEAST.getY(), ViewFrame.SCENE_COLOR);
					}

					Vertex vertexWEST = graph.getVertexByDir(source, Directions.WEST);
					if(graph.containsEdge(source, vertexWEST) && vertexWEST != null) {
						ViewFrame.drawWall(source.getX(), source.getY(), vertexWEST.getX(), vertexWEST.getY(), ViewFrame.SCENE_COLOR);
					}
			}
		}*/

		primaryStage.show();
	}

	public void addOnAction(EventHandler<ActionEvent> enventHandler) {

	}

}
