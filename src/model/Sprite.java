/**
 * 
 */
package model;

import model.Edge.Type;
import model.Labyrinth.Directions;

/**
 * Classe qui mod�lise un sprite (tel que le joueur, le m�chant ou un bonbon
 * @author Serigne Amsatou SEYE
 * @author Fabien JACQUES
 *
 */
public class Sprite implements ISprite {

	/* (non-Javadoc)
	 * @see model.ISprite#move(model.Labyrinth)
	 */
	
	private int x;
	private int y;
	
	/**
	 * Cr�e une instance de Sprite avec ses coordoonn�es
	 * @param x
	 * 			L'abscisse de la position du sprite
	 * @param y
	 * 			L'ordoonn�es de la position du sprite
	 */
	public Sprite(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * Retourne l'abscisse de la position du Sprite
	 * @return L'abscisse de la position du Sprite
	 */
	public int getX() {
		return this.x;
	}

	/**
	 * Positionne le Sprite � l'abscisse sp�cifi�
	 * @param x
	 * 			L'abscisse de la position
	 */
	public void setX(int x) {
		if (x <= Labyrinth.WIDTH) {
			this.x = x;
		}
	}

	/**
	 * Retourne l'ordonn�e de la position du Sprite
	 * @return L'ordonn�e de la position du Sprite
	 */
	public int getY() {
		return this.y;
	}

	/**
	 * Positionne le Sprite � l'ordonn�e sp�cifi�
	 * @param x
	 * 			L'ordonn�e de la position
	 */
	public void setY(int y) {
		if (y <= Labyrinth.HEIGHT) {
			this.y = y;
		}
	}

	@Override
	public String toString() {
		return "Sprite [x=" + x + ", y=" + y + "]";
	}

	@Override
	public boolean move(Labyrinth labyrinth, Directions direc) {
		// TODO Auto-generated method stub
		Vertex vertex = this.getVertex(labyrinth.getGraph());
		for (Directions dir : Directions.values()) {
			Vertex next = labyrinth.getGraph().getVertexByDir(vertex, dir);
			if (dir == direc && next != null && labyrinth.getGraph().containsEdge(vertex, next) && labyrinth.getGraph().getEdge(vertex, next).getType() != Type.CLOSED_DOOR) { // && (next.getNbr() == vertex.getNbr()-1)
				this.moveByDir(dir);		
				return true;
			}
		}
		return false;
	}

	@Override
	public void moveByDir(Directions dir) { // ????????????????
		// TODO Auto-generated method stub
		switch (dir) {
			case NORTH:
				this.setX(this.getX());
				this.setY(this.getY()-1);
				break;
			case SOUTH:
				this.setY(this.getY()+1);
				this.setX(this.getX());
				break;
			case EAST:
				this.setX(this.getX()+1);
				this.setY(this.getY());
				break;
			case WEST:
				this.setX(this.getX()-1);
				this.setY(this.getY());
				break;
		}
	}

	@Override
	public Vertex getVertex(Graph graph) {
		// TODO Auto-generated method stub
		return graph.getvertexByCoord(this.getX(), this.getY());
	}

}
