package model;

import model.Labyrinth.Directions;

/**
 * Interface que les sprites (Player, Enemy, ...) doivent impl�menter
 * @author Serigne Amsatou SEYE
 * @author Fabien JACQUES
 *
 */
public interface ISprite {
	
	/**
	 * D�place le sprite dans la direction donn�e si possible
	 * @param 
	 * 			Le labyrinthe dans lequel le sprite existe
	 * @param 
	 * 			La direction vers laquelle le sprite doit se diriger
	 * @return true si le d�placement a eu lieu et false sinon 
	 */
	public boolean move(Labyrinth labyrinth, Directions dir);
	
	/**
	 * D�place le sprite dans la direction donn�e
	 * @param
	 * 			La direction vers laquelle le sprite va se d�placer
	 */
	public void moveByDir(Directions dir);
	
	/**
	 * @param Le graphe du labyrinthe
	 * @return Renvoie le sommet sur lequel le sprite se trouve
	 */
	public Vertex getVertex(Graph graph);
	
}
