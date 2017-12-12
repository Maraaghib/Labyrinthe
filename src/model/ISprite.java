package model;

import model.Labyrinth.Directions;

/**
 * interface que les sprites doivent respecter
 * @author Fabien
 *
 */
public interface ISprite {
	
	/*
	 * Déplace le sprite dans la direction donnée si possible
	 * @param labyrinth le labyrinth dans lequel le sprite existe
	 * @param la direction vers laquelle le sprite doit se diriger
	 * @return si le déplacement à eu lieu renvoie true sinon false
	 */
	public boolean move(Labyrinth labyrinth, Directions dir);
	
	/**
	 * Déplace le sprite dans la direction donnée
	 * @param dir la direction vers laquelle le sprite va se déplacer
	 */
	public void moveByDir(Directions dir);
	
	/*
	 * @param le graph du labyrinth
	 * @return renvoie le vertex sur lequel le sprite se trouve
	 */
	public Vertex getVertex(Graph graph);
	
}
