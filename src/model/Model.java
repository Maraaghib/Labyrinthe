package model;
import model.Wall.WallType;
import model.Labyrinth.Directions;

public class Model {
	
	private static Model instance = null;
	private static Labyrinth labyrinth;
	
	
	private Model() {
		this.labyrinth = new Labyrinth();
		labyrinth.buildLabyrinth();
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
	
	public WallType getWallType(int x, int y, Directions dir){
		return labyrinth.getWallType(x, y, dir);
	}
}
