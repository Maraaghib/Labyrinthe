package model;

import model.Labyrinth.Directions;

/**
 * Classe qui reprèsente le méchant qui essaie d'attraper le joueur
 * @author Fabien
 *
 */
public class Enemy extends Sprite {
	public Enemy(int x, int y) {
		super(x, y);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Calcul avec l'algorithme de Manhattan la direction que le méchant doit prendre 
	 * pour aller vers le joueur le plus rapidement possible
	 * @param labyrinth Le labyrinth dans lequel se déplace le méchant
	 * @param x position du joueur
	 * @param y position du joueur
	 */	
	public void move(Labyrinth labyrinth, int x, int y) {

		labyrinth.launchManhattan(labyrinth.getGraph().getvertexByCoord(labyrinth.getPlayer().getX(), labyrinth.getPlayer().getY()));
		
		Directions dir = null;
		int min = Integer.MAX_VALUE;
		
		if(labyrinth.getGraph().getVertexByDir(labyrinth.getGraph().getvertexByCoord(x, y), Directions.EAST) != null){
			try {
				if(labyrinth.isOpened(labyrinth.getGraph().getvertexByCoord(x, y), Directions.EAST)){
					if(labyrinth.getManhattan(x+1, y) < min){
						min = labyrinth.getManhattan(x+1, y);
						dir = Directions.EAST;
					}
				}
			} catch (CloneNotSupportedException e) {
				e.printStackTrace();
			}
		}
		
		if(labyrinth.getGraph().getVertexByDir(labyrinth.getGraph().getvertexByCoord(x, y), Directions.SOUTH) != null){
			try {
				if(labyrinth.isOpened(labyrinth.getGraph().getvertexByCoord(x, y), Directions.SOUTH)){
					if(labyrinth.getManhattan(x, y+1) < min){
						min = labyrinth.getManhattan(x, y+1);
						dir = Directions.SOUTH;
					}
				}
			} catch (CloneNotSupportedException e) {
				e.printStackTrace();
			}
		}
		
		if(labyrinth.getGraph().getVertexByDir(labyrinth.getGraph().getvertexByCoord(x, y), Directions.WEST) != null){
			try {
				if(labyrinth.isOpened(labyrinth.getGraph().getvertexByCoord(x, y), Directions.WEST)){
					if(labyrinth.getManhattan(x-1, y) < min){
						min = labyrinth.getManhattan(x-1, y);
						dir = Directions.WEST;
					}
				}
			} catch (CloneNotSupportedException e) {
				e.printStackTrace();
			}
		}
		
		if(labyrinth.getGraph().getVertexByDir(labyrinth.getGraph().getvertexByCoord(x, y), Directions.NORTH) != null){
			try {
				if(labyrinth.isOpened(labyrinth.getGraph().getvertexByCoord(x, y), Directions.NORTH)){
					if(labyrinth.getManhattan(x, y-1) < min){
						min = labyrinth.getManhattan(x, y-1);
						dir = Directions.NORTH;
					}
				}
			} catch (CloneNotSupportedException e) {
				e.printStackTrace();
			}
		}
		
		this.move(labyrinth, dir);
	}
}