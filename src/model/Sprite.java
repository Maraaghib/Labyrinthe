/**
 * 
 */
package model;

import model.Labyrinth.Directions;

/**
 * @author hamza
 *
 */
public class Sprite implements ISprite {

	/* (non-Javadoc)
	 * @see model.ISprite#move(model.Labyrinth)
	 */
	
	private int x;
	private int y;
	
	/**
	 * @param x
	 * @param y
	 */
	public Sprite(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return this.x;
	}

	public void setX(int x) {
		if (x <= Labyrinth.WIDTH) {
			this.x = x;
		}
	}

	public int getY() {
		return this.y;
	}

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
	public void move(Labyrinth labyrinth, Directions direc) {
		// TODO Auto-generated method stub
		Vertex vertex = this.getVertex(labyrinth.getGraph());
		for (Directions dir : Directions.values()) {
			Vertex next = labyrinth.getGraph().getVertexByDir(vertex, dir);
			if (dir == direc && next != null && labyrinth.getGraph().containsEdge(vertex, next)) { // && (next.getNbr() == vertex.getNbr()-1)
				this.moveByDir(dir);			}
		}
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
