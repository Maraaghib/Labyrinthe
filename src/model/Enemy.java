package model;

import com.sun.org.apache.xalan.internal.xsltc.dom.ClonedNodeListIterator;

import model.Labyrinth.Directions;

public class Enemy extends Sprite {
	public Enemy(int x, int y) {
		super(x, y);
		// TODO Auto-generated constructor stub
	}
	
	//Calcul avec l'algorithme de Manhattan la direction que le méchant doit prendre pour aller vers le gentil
	public void move(Labyrinth labyrinth, int x, int y) {
		try {
		labyrinth.launchManhattan(labyrinth.getGraph().getvertexByCoord(this.getX(), this.getY()), labyrinth.getGraph().getvertexByCoord(x, y));
		}catch (CloneNotSupportedException e){
			e.printStackTrace();
		}
		Directions dir = Directions.EAST;
		int min = labyrinth.getGraph().getVertexByDir(labyrinth.getGraph().getvertexByCoord(this.getX(), this.getY()), Directions.EAST).getNbr();
		
		if(labyrinth.getGraph().getVertexByDir(labyrinth.getGraph().getvertexByCoord(this.getX(), this.getY()), Directions.SOUTH).getNbr() < min){
			min = labyrinth.getGraph().getVertexByDir(labyrinth.getGraph().getvertexByCoord(this.getX(), this.getY()), Directions.SOUTH).getNbr();
			dir = Directions.SOUTH;
		}
		
		if(labyrinth.getGraph().getVertexByDir(labyrinth.getGraph().getvertexByCoord(this.getX(), this.getY()), Directions.WEST).getNbr() < min){
			min = labyrinth.getGraph().getVertexByDir(labyrinth.getGraph().getvertexByCoord(this.getX(), this.getY()), Directions.WEST).getNbr();
			dir = Directions.WEST;
		}
		
		if(labyrinth.getGraph().getVertexByDir(labyrinth.getGraph().getvertexByCoord(this.getX(), this.getY()), Directions.NORTH).getNbr() < min){
			min = labyrinth.getGraph().getVertexByDir(labyrinth.getGraph().getvertexByCoord(this.getX(), this.getY()), Directions.NORTH).getNbr();
			dir = Directions.NORTH;
		}
		this.move(labyrinth, dir);
	}
}