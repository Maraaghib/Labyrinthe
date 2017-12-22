package model;

import java.util.Random;
/**
 * Classe qui gère tous les éléments du jeux
 * @author Seerigne Amsatou SEYE & Fabien JACQUES
 *
 */
public class Model {
	
	private static Model instance = null;
	private Labyrinth labyrinth;
	
	/**
	 * Crée une instance de Model en créant le labyrinthe avec un graphe d'un sommet de coordoonnées aléatoires
	 */
	private Model() {
		Random random = new Random();;
		int x = random.nextInt(Labyrinth.WIDTH);
		int y = random.nextInt(Labyrinth.HEIGHT);
		this.labyrinth = new Labyrinth(new Graph(new Vertex(x, y, 0)));
	}
	
	/**
	 * Retourne une instance unique de Model
	 * @return
	 * 			Une instance de Model
	 */
	public static Model getInstance() {
		if(instance == null) {
			instance = new Model();
			System.out.println("Instance de la classe Model créée !");
		}
		else {
			System.out.println("Instance de la classe Model existante !");
		}
		
		return instance;
	}
	
	/**
	 * Retourne une instance de Labyrinth
	 * @return
	 * 			Une instance de Labyrinth
	 */
	public Labyrinth getLabyrinth() {
		return this.labyrinth;
	}
	
	/**
	 * Retourne une instance de graph
	 * @return
	 * 			Une instance de Graph
	 */
	public Graph getGraph() {
		return labyrinth.getGraph();
	}
	
	/**
	 * Retourne une instance de Player
	 * @return
	 * 			Une instance de Player
	 */
	public Player getPlayer() {
		return labyrinth.getPlayer();
	}
	
	/**
	 * Retourne une instance de Enemy
	 * @return
	 * 			Une instance de Enemy
	 */
	public Enemy getEnemy() {
		return labyrinth.getEnemy();
	}
	
}
