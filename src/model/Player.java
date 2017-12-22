package model;

/**
 * Classe qui modélise le joueur
 * @author Serigne Amsatou SEYE
 * @author Fabien JACQUES
 *
 */
public class Player extends Sprite {
	
	private static Player instance = null;
	
	/**
	 * Crée une instance de Player de coordoonnées x et y
	 * @param x
	 * 			L'abscisse de la position initiale du joueur
	 * @param y
	 * 			L'ordonnée de la position initiale du joueur
	 */
	private Player(int x, int y) {
		super(x, y);
	}
	
	/**
	 * Retourne une instance unique de Player
	 * @param x
	 * 			L'abscisse de la position initiale du joueur
	 * @param y
	 * 			L'ordonnée de la position initiale du joueur
	 * @return
	 * 			une instance de Player
	 */
	public static Player getInstance(int x, int y) {
		if(instance == null) {
			instance = new Player(x, y);
			System.out.println("Instance de la classe Player créée !");
		}
		else {
			System.out.println("Instance de la classe Player existante !");
		}
		
		return instance;
	}

}
