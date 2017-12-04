package model;

public class Model {
	
	private static Model instance = null;
	private static Labyrinth labyrinth;
	private Graph graph;
	private Vertex vertex;
	
	
	private Model() {
		labyrinth = new Labyrinth();
		vertex = new Vertex();
		labyrinth.buildRandomPath(vertex);
	}
	
	public static Graph getGraph() {
		return labyrinth.getGraph();
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
