package model;

import model.Labyrinth.Directions;

public interface ISprite {
	
	//Renvoie true si le d�placement a eu lieu
	public boolean move(Labyrinth labyrinth, Directions dir);
	
	public void moveByDir(Directions dir);
	
	public Vertex getVertex(Graph graph);
	
}
