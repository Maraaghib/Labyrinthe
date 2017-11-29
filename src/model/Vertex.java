package model;

import model.Labyrinth.Directions;

public class Vertex implements Comparable<Vertex> {
	
	private int nbr;
	
	public Vertex() {
		
	}
	
	public Vertex(int xt, int yt, int i) {
		// TODO Auto-generated constructor stub
	}

	public int getNbr() {
		return nbr;
	}

	public void setNbr(int nbr) {
		this.nbr = nbr;
	}

	public int compareTo(Vertex source) {
		// TODO Auto-generated method stub
		return 0;
	}

	public boolean inBorders(Directions dir) {
		// TODO Auto-generated method stub
		return false;
	}

	public int getX() {
		// TODO Auto-generated method stub
		return 0;
	}

	public int getY() {
		// TODO Auto-generated method stub
		return 0;
	}

	
}
