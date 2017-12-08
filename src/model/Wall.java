package model;

import model.Labyrinth.Directions;

/*Classe mod�lisant un mur (les portes et les passages sont consid�r�s comme des murs)
 * 
 * Un mur est caract�ris� par son type, ses coordonn�es 
 * et sa direction (soit SOUTH soit EAST pour �viter les doublons)
 */
public class Wall {
	public enum WallType{
		WALL,
		EMPTY,
		DOOR;
	};
	
	private WallType type;
	private int x;
	private int y;
	private Directions dir;
	
	public Wall(WallType type, int x, int y, Directions dir){
		this.setType(type);
		this.x = x;
		this.y = y;
		this.dir = dir;
	}

	public WallType getType() {
		return type;
	}

	public void setType(WallType type) {
		this.type = type;
	}

	/**
	 * @return the x
	 */
	public int getX() {
		return x;
	}

	/**
	 * @return the y
	 */
	public int getY() {
		return y;
	}

	/**
	 * @return the dir
	 */
	public Directions getDir() {
		return dir;
	}

}
