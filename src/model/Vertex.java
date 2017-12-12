package model;

import model.Labyrinth.Directions;

/**
 * Classe utilisée par graph pour modéliser les sommets
 * @author Fabien
 *
 */
public class Vertex implements Comparable<Vertex> {

	private int nbr;
	private int x;
	private int y;

	public Vertex() {
		this.setX(0);
		this.setY(0);
		this.setNbr(0);
	}

	public Vertex(int x, int y) {
		this.setX(x);
		this.setY(y);
	}

	public Vertex(int x, int y, int nbr) {
		this.setX(x);
		this.setY(y);
		this.setNbr(nbr);
	}

	public int getNbr() {
		return this.nbr;
	}

	public void setNbr(int nbr) {
			this.nbr = nbr;
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
	protected Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		Vertex vertex = new Vertex(this.getX(), this.getY(), this.getNbr());
		return vertex;
	}

	public int compareTo(Vertex source) {
		// TODO Auto-generated method stub
		if(this.x != source.x) {
			return source.x - this.x;
		}
		else {
			return source.x - this.y;
		}
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return this.getX() == ((Vertex)obj).getX() && this.getY() == ((Vertex)obj).getY();
	}

	@Override
	public String toString() {
		return "["+this.getNbr()+"]("+this.getX()+" , "+this.getY()+")";
	}

	public boolean inBorders(Directions dir) {
		// TODO Auto-generated method stub
		Vertex vertex = new Vertex(this.getX(), this.getY());
		boolean inBorder = false;
		switch (dir) {
			case NORTH:
				inBorder = ((vertex.getY()-1) >= Labyrinth.TOP_BORDER) ? true : false;
				break;
			case SOUTH:
				inBorder = ((vertex.getY()+1) <= Labyrinth.DOWN_BORDER) ? true : false;
				break;
			case EAST:
				inBorder = ((vertex.getX()+1) <= Labyrinth.RIGHT_BORDER) ? true : false;
				break;
			case WEST:
				inBorder = ((vertex.getX()-1) >= Labyrinth.LEFT_BORDER) ? true : false;
				break;
		}
		return inBorder;
	}

}
