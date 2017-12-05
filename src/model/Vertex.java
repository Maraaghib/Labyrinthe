package model;

import model.Labyrinth.Directions;

public class Vertex implements Comparable<Vertex> {
	
	private int nbr;
	private int x;
	private int y;
	
	public Vertex() {
		super();
		this.nbr = 0;
		this.setX(0);
		this.setY(0);
	}
	
	public Vertex(int x, int y) {
		super();
		this.setX(x);
		this.setY(y);		
	}

	public Vertex(int x, int y, int nbr) {
		super();
		this.setX(x);
		this.setY(y);
		this.setNbr(nbr);
	}

	public int getNbr() {
		return this.nbr;
	}

	public void setNbr(int nbr) {
		if(nbr <= (Labyrinth.WIDTH * Labyrinth.HEIGHT)) {
			this.nbr = nbr;
		}
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

	public int compareTo(Vertex source) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String toString() {
		return "Vertex [nbr=" + this.getNbr()+ ", x=" + this.getX() + ", y=" + this.getY() + "]";
	}

	public boolean inBorders(Directions dir) {
		// TODO Auto-generated method stub
		Vertex vertex = new Vertex(this.getX(), this.getY());
		boolean inBorder = false;
		switch (dir) {
		case NORTH:
			inBorder = (vertex.getY()-1 < Labyrinth.TOP_BORDER) ? false : true;
			break;
		case SOUTH:
			inBorder = (vertex.getY()+1 > Labyrinth.DOWN_BORDER) ? false : true;
			break;
		case EAST:
			inBorder = (vertex.getX()+1 > Labyrinth.RIGHT_BORDER) ? false : true;
			break;
		case WEST:
			inBorder = (vertex.getX()-1 < Labyrinth.LEFT_BORDER) ? false : true;
			break;
	}
		return inBorder;
	}

	
}
