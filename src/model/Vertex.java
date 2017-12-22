package model;

import model.Labyrinth.Directions;

/**
 * Classe utilis�e par Graph pour mod�liser les sommets
 * @author Seerigne Amsatou SEYE & Fabien JACQUES
 *
 */
public class Vertex implements Comparable<Vertex> {

	private int nbr;
	private int x;
	private int y;

	/**
	 * Cr�e une instance de sommet de coordoonn�es (0,0)
	 */
	public Vertex() {
		this.setX(0);
		this.setY(0);
		this.setNbr(0);
	}

	/**
	 * Cr�e une instance de sommet de coordoonn�es pass�es en param�tre
	 * @param x
	 * 			L'abscisse de la position
	 * @param y
	 * 			L'ordoonn�es de la position
	 */
	public Vertex(int x, int y) {
		this.setX(x);
		this.setY(y);
	}

	/**
	 * Cr�e une instance de sommet de coordoonn�es pass�es en param�tre et de num�ro nbr
	 * @param x
	 * 			L'abscisse de la position
	 * @param y
	 * 			L'ordoonn�es de la position
	 * @param nbr
	 * 			Le num�ro du sommet
	 */
	public Vertex(int x, int y, int nbr) {
		this.setX(x);
		this.setY(y);
		this.setNbr(nbr);
	}

	/**
	 * Retourne le num�ro du sommet
	 * @return
	 * 			Le num�ro du sommet
	 * 
	 */
	public int getNbr() {
		return this.nbr;
	}

	/**
	 * D�finit le num�ro du sommet
	 * @param nbr
	 * 			Le nombre � affecter au sommet
	 */
	public void setNbr(int nbr) {
			this.nbr = nbr;
	}


	/**
	 * Retourne l'abscisse de la position du sommet
	 * @return L'abscisse de la position du sommet
	 */
	public int getX() {
		return this.x;
	}

	/**
	 * Positionne le sommet � l'abscisse sp�cifi�
	 * @param x
	 * 			L'abscisse de la position
	 */
	public void setX(int x) {
		if (x <= Labyrinth.WIDTH) {
			this.x = x;
		}
	}

	/**
	 * Retourne l'ordonn�e de la position du sommet
	 * @return L'ordonn�e de la position du sommet
	 */
	public int getY() {
		return this.y;
	}

	/**
	 * Positionne le sommet � l'ordonn�e sp�cifi�
	 * @param x
	 * 			L'ordonn�e de la position
	 */
	public void setY(int y) {
		if (y <= Labyrinth.HEIGHT) {
			this.y = y;
		}
	}

	/**
	 * V�rifie si le sommet appelant cette bien m�thode est bien dans le cadre du labyrinthe
	 * @param dir
	 * 			Direction selon laquelle on fait la v�rification
	 * @return true si le sommet est dans le labyrinthe, false sinon
	 */
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

}
