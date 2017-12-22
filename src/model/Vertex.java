package model;

import model.Labyrinth.Directions;

/**
 * Classe utilisée par Graph pour modéliser les sommets
 * @author Seerigne Amsatou SEYE & Fabien JACQUES
 *
 */
public class Vertex implements Comparable<Vertex> {

	private int nbr;
	private int x;
	private int y;

	/**
	 * Crée une instance de sommet de coordoonnées (0,0)
	 */
	public Vertex() {
		this.setX(0);
		this.setY(0);
		this.setNbr(0);
	}

	/**
	 * Crée une instance de sommet de coordoonnées passées en paramètre
	 * @param x
	 * 			L'abscisse de la position
	 * @param y
	 * 			L'ordoonnées de la position
	 */
	public Vertex(int x, int y) {
		this.setX(x);
		this.setY(y);
	}

	/**
	 * Crée une instance de sommet de coordoonnées passées en paramètre et de numéro nbr
	 * @param x
	 * 			L'abscisse de la position
	 * @param y
	 * 			L'ordoonnées de la position
	 * @param nbr
	 * 			Le numéro du sommet
	 */
	public Vertex(int x, int y, int nbr) {
		this.setX(x);
		this.setY(y);
		this.setNbr(nbr);
	}

	/**
	 * Retourne le numéro du sommet
	 * @return
	 * 			Le numéro du sommet
	 * 
	 */
	public int getNbr() {
		return this.nbr;
	}

	/**
	 * Définit le numéro du sommet
	 * @param nbr
	 * 			Le nombre à affecter au sommet
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
	 * Positionne le sommet à l'abscisse spécifié
	 * @param x
	 * 			L'abscisse de la position
	 */
	public void setX(int x) {
		if (x <= Labyrinth.WIDTH) {
			this.x = x;
		}
	}

	/**
	 * Retourne l'ordonnée de la position du sommet
	 * @return L'ordonnée de la position du sommet
	 */
	public int getY() {
		return this.y;
	}

	/**
	 * Positionne le sommet à l'ordonnée spécifié
	 * @param x
	 * 			L'ordonnée de la position
	 */
	public void setY(int y) {
		if (y <= Labyrinth.HEIGHT) {
			this.y = y;
		}
	}

	/**
	 * Vérifie si le sommet appelant cette bien méthode est bien dans le cadre du labyrinthe
	 * @param dir
	 * 			Direction selon laquelle on fait la vérification
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
