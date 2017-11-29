package model;

public class Model {
	
	private static Model instance = null;
	

	private Model() {
		
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

}
