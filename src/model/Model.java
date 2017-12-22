package model;

import java.util.Random;
/**
 * Classe qui gère tous les éléments du jeux
 * @author hamza
 *
 */
public class Model {
	
	private static Model instance = null;
	private Labyrinth labyrinth;
	
	
	private Model() {
		Random random = new Random();;
		int x = random.nextInt(Labyrinth.WIDTH);
		int y = random.nextInt(Labyrinth.HEIGHT);
		this.labyrinth = new Labyrinth(new Graph(new Vertex(x, y, 0)));
	}
	
	public Labyrinth getLabyrinth() {
		return this.labyrinth;
	}
	
	public Graph getGraph() {
		return labyrinth.getGraph();
	}
	
	public Player getPlayer() {
		return labyrinth.getPlayer();
	}
	
	public Enemy getEnemy() {
		return labyrinth.getEnemy();
	}
	
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

	public static void usage(String message) {
		System.out.println(message);
		System.exit(0);
	}
}
