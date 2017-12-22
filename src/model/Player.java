package model;

/**
 * Classe qui mod�lise le joueur
 * @author Fabien
 *
 */
public class Player extends Sprite {
	
	private static Player instance = null;
	
	private Player(int x, int y) {
		super(x, y);
	}
	
	public static Player getInstance(int x, int y) {
		if(instance == null) {
			instance = new Player(x, y);
			System.out.println("Instance de la classe Model cr��e !");
		}
		else {
			System.out.println("Instance de la classe Model existante !");
		}
		
		return instance;
	}

}
