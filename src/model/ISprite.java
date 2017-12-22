package model;

import model.Labyrinth.Directions;

/**
 * Interface que les sprites (Player, Enemy, ...) doivent implémenter
 * @author Serigne Amsatou SEYE
 * @author Fabien JACQUES
 *
 */
public interface ISprite {
	
	/**
	 * Déplace le sprite dans la direction donnée si possible
	 * @param 
	 * 			Le labyrinthe dans lequel le sprite existe
	 * @param 
	 * 			La direction vers laquelle le sprite doit se diriger
	 * @return true si le déplacement a eu lieu et false sinon 
	 */
	public boolean move(Labyrinth labyrinth, Directions dir);
	
	/**
	 * Déplace le sprite dans la direction donnée
	 * @param
	 * 			La direction vers laquelle le sprite va se déplacer
	 */
	public void moveByDir(Directions dir);
	
	/**
	 * @param Le graphe du labyrinthe
	 * @return Renvoie le sommet sur lequel le sprite se trouve
	 */
	public Vertex getVertex(Graph graph);
	
}
