package model;

import java.util.Random;

public class Model {
	
	private static Model instance = null;
	private Vertex vertex;
	private Graph graph;
	private Labyrinth labyrinth;
	
	
	private Model() {
		Random random = new Random();;
		int x = random.nextInt(Labyrinth.WIDTH);
		int y = random.nextInt(Labyrinth.HEIGHT);
		this.vertex = new Vertex(x, y, 0);
		this.graph = new Graph(vertex); 
		this.labyrinth = new Labyrinth(graph);
		labyrinth.buildRandomPath(vertex);
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
