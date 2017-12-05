package model;

public class Model {
	
	private static Model instance = null;
	private Vertex vertex;
	private Graph graph;
	private static Labyrinth labyrinth;
	
	public static int cpt = 0;
	
	private Model() {
		this.vertex = new Vertex();
		this.graph = new Graph(vertex); 
		this.labyrinth = new Labyrinth(graph);
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
