package model;

import model.Labyrinth.Directions;

public class Vertex implements Comparable<Vertex> {
	
	private int nbr;
	private int x;
	private int y;
	
	public Vertex() {
		
	}
	
	public Vertex(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}

	public Vertex(int nbr, int x, int y) {
		super();
		this.nbr = nbr;
		this.x = x;
		this.y = y;
	}

	public int getNbr() {
		return nbr;
	}

	public void setNbr(int nbr) {
		this.nbr = nbr;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int compareTo(Vertex source) {
		// TODO Auto-generated method stub
		return 0;
	}

	public boolean inBorders(Directions dir) {
		// TODO Auto-generated method stub
		Vertex vertex = new Vertex(this.getX(), this.getY());
		boolean inBorder = false;
		switch (dir) {
		case NORTH:
			inBorder = (vertex.getY()-1 < Labyrinth.TOP) ? false : true;
			break;
		case SOUTH:
			inBorder = (vertex.getY()+1 > Labyrinth.DOWN) ? false : true;
			break;
		case EAST:
			inBorder = (vertex.getX()+1 > Labyrinth.RIGHT) ? false : true;
			break;
		case WEST:
			inBorder = (vertex.getX()-1 < Labyrinth.LEFT) ? false : true;
			break;
	}
		return inBorder;
	}

	
}
