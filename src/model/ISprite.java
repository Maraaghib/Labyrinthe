package model;

import model.Labyrinth.Directions;

public interface ISprite {
	
	public void move(Labyrinth labyrinth, Directions dir);
	
	public void moveByDir(Directions dir);
	
	public Vertex getVertex(Graph graph);
	
}
