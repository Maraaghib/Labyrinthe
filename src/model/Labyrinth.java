/**
 *
 */
package model;

import java.util.Random;
import java.util.Vector;

import model.Edge.Type;




/**
 * Classe qui modélise le labyrinthe
 * @author saseye
 *
 */
public class Labyrinth {

	protected static final int TOP_BORDER = 0;
	protected static final int DOWN_BORDER = 15;
	protected static final int LEFT_BORDER = 0;
	protected static final int RIGHT_BORDER = 15;

	public static final int WIDTH = 16;
	public static final int HEIGHT = 16;

	/**
	 * Énumération représentant les quatres directions orthogonales
	 * @author Fabien
	 *
	 */
	public enum Directions{
		EAST,
		WEST,
		NORTH,
		SOUTH;
	};

	private Graph graph;
	private Player player;
	private Enemy enemy;
	private int[][] manhattan;

	public Labyrinth() {
		this.graph = new Graph();
		this.player = Player.getInstance(0, 0);
		this.enemy = new Enemy(0, 0);
		System.out.println("Intance de la classe Labyrinth cree !");
	}

	public Labyrinth(Graph graph) {
		this.graph = graph;
		this.manhattan = new int[WIDTH][HEIGHT];
		this.player = Player.getInstance(0, 0);
		buildRandomPath(graph.getvertexByCoord(WIDTH, HEIGHT));
		try {
			openDoorRandom();
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		closeDoorRandom();
		//On place enemy le plus loin possible du joueur
		launchManhattan(graph.getvertexByCoord(0, 0));
		
		int max = 0;
		int x = 0;
		int y = 0;
		for(int i = 0; i < WIDTH; i++){
			for(int j = 0; j < HEIGHT; j++){
				if(manhattan[i][j] > max){
					max = manhattan[i][j];
					x = i;
					y = j;
				}
			}
		}
		this.enemy = new Enemy(x, y);
		
	}

	public Graph getGraph() {
		return this.graph;
	}
	
	public Player getPlayer() {
		return this.player;
	}
	
	public Enemy getEnemy(){
		return this.enemy;
	}
	
	public int getManhattan(int x, int y){
		return manhattan[x][y];
	}
	/**
	 * Fonction récursive qui permet d'implémenter l'algorithme de Manhattan
	 */
	private void calculateManhattanDistance(Vertex current, int depth) throws CloneNotSupportedException {
		if(manhattan[current.getX()][current.getY()] == -1){
			manhattan[current.getX()][current.getY()] = depth;
			if(graph.getVertexByDir(current, Directions.EAST) != null){
				if(isOpened(current, Directions.EAST)){
					calculateManhattanDistance(graph.getVertexByDir(current, Directions.EAST), depth+1);
				}
			}
			
			if(graph.getVertexByDir(current, Directions.SOUTH) != null){
				if(isOpened(current, Directions.SOUTH)){
					calculateManhattanDistance(graph.getVertexByDir(current, Directions.SOUTH), depth+1);
				}
			}
			
			if(graph.getVertexByDir(current, Directions.WEST) != null){
				if(isOpened(current, Directions.WEST)){
					calculateManhattanDistance(graph.getVertexByDir(current, Directions.WEST), depth+1);
				}
			}
			
			if(graph.getVertexByDir(current, Directions.NORTH) != null){
				if(isOpened(current, Directions.NORTH)){
					calculateManhattanDistance(graph.getVertexByDir(current, Directions.NORTH), depth+1);
				}
			}
		}
		
	}

	/**
	 * Fonction qui exécute l'algorithme de Manhattan
	 * Écrit dans manhattan[][] la distance par rapport à target
	 * @param target l'origine de l'alogirthme de manhattan
	 */
	public void launchManhattan(Vertex target){
		for(int i = 0; i < WIDTH; i++){
			for(int j = 0; j < HEIGHT; j++){
				manhattan[i][j] = -1;
			}
		}
		try {
			calculateManhattanDistance(target, 0);
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Génère un labyrinthe parfait
	 */
	public void buildRandomPath(Vertex vertex) {
		// Une liste aleatoire des 4 directions
		Vector<Directions> v = new Vector<Directions>();

		for (int i = 0; i < Directions.values().length; ++i) {
			v.add(Directions.values()[i]);
		}

		Directions directions[] = new Directions[4];
		Random random = new Random(); 
		for (int i = 0; i < directions.length; ++i) {
			int index = random.nextInt(v.size());
			directions[i] = v.get(index);
			v.remove(index);
		}

		// Pour chacune de ces directions, on avance en profondeur d'abord
		for (int i = 0; i < 4; ++i) {
			Directions dir = directions[i];
			if (vertex.inBorders(dir) && this.graph.doesntExist(vertex, dir)) {
				int x = vertex.getX();
				int y = vertex.getY();
				int xt = 0, yt = 0;

				switch (dir) {
					case NORTH:
						xt = x;
						yt = y-1;
						break;
					case SOUTH:
						xt = x;
						yt = y+1;
						break;
					case EAST:
						xt = x+1;
						yt = y;
						break;
					case WEST:
						xt = x-1;
						yt = y;
						break;
				}
				Vertex next = new Vertex(xt, yt, vertex.getNbr()+1);
				boolean vertexAdded = this.graph.addVertex(next);
				if (vertexAdded) {
					this.graph.addEdge(vertex, next);
					buildRandomPath(next);
				}					
			}
		}
	}

	/* Quelques prÃ©dicats pour dÃ©tecter une porte ouverte, fermÃ©e, un couloir ou un mur... */

	public boolean isWall(Vertex vertex, Directions dir) throws CloneNotSupportedException {
		Edge edge = graph.getEdge(vertex, dir);
		return (edge == null);
	}

	public boolean isClosed(Vertex vertex, Directions dir) throws CloneNotSupportedException {
		Edge edge = graph.getEdge(vertex, dir);
		return (edge == null || (edge.getType() == Edge.Type.CLOSED_DOOR));
	}

	public boolean isOpened(Vertex vertex, Directions dir) throws CloneNotSupportedException {
		Edge edge = graph.getEdge(vertex, dir);
		return (edge != null && (edge.getType() != Edge.Type.CLOSED_DOOR));
	}

	public boolean isClosedDoor(Vertex vertex, Directions dir) throws CloneNotSupportedException {
		Edge edge = graph.getEdge(vertex, dir);
		return (edge != null && (edge.getType() == Edge.Type.CLOSED_DOOR));
	}

	public boolean isOpenedDoor(Vertex vertex, Directions dir) throws CloneNotSupportedException {
		Edge edge = graph.getEdge(vertex, dir);
		return (edge != null && (edge.getType() == Edge.Type.OPENED_DOOR));
	}
	
	public void openDoorRandom() throws CloneNotSupportedException {
		// On essaie 1000 fois, après quoi on renonce
		for (int i = 1; i <= 1000; ++i) {
			// On choisit un sommet au hasard
			Vertex vertex = graph.randomVertex();
			System.out.println("RandomVertex: "+vertex);
			Random random = new Random();
			if (vertex != null) {
				// On choisit une direction au hasard (on devrait prendre seulement celles qui correspondent à des murs...)
				Labyrinth.Directions dir = Directions.values()[random.nextInt(Directions.values().length)];
				if (isWall(vertex, dir)) {
					Vertex vertex2 = graph.getVertexByDir(vertex, dir);
					if (vertex2 != null) {
						Edge edge = graph.getEdge(vertex, vertex2); // ça doit normalement retourner null ??
						if (edge == null) {
							// on ajoute un saut entre ces sommets
							Edge newEdge = new Edge(vertex, vertex2);
							newEdge.setType(Type.OPENED_DOOR);
							graph.addEdge(newEdge);
							return;
						}
					}
				}
			}
		}
	}
	
	public void openDoorFurthestFromPlayer() throws CloneNotSupportedException {
		// On prend le sommet le plus loin de player
		int max = 0;
		int x = 0;
		int y = 0;
		for(int i = 0; i < WIDTH; i++){
			for(int j = 0; j < HEIGHT; j++){
				if(manhattan[i][j] > max){
					max = manhattan[i][j];
					x = i;
					y = j;
				}
			}
		}
		Vertex vertex = graph.getvertexByCoord(x, y);
		
		System.out.println("RandomVertex: "+vertex);
		Random random = new Random();
		if (vertex != null) {
			// On choisit une direction au hasard (on devrait prendre seulement celles qui correspondent à des murs...)
			Labyrinth.Directions dir = Directions.values()[random.nextInt(Directions.values().length)];
			if (isWall(vertex, dir)) {
				Vertex vertex2 = graph.getVertexByDir(vertex, dir);
				if (vertex2 != null) {
					Edge edge = graph.getEdge(vertex, vertex2); // ça doit normalement retourner null ??
					if (edge == null) {
						// on ajoute un saut entre ces sommets
						Edge newEdge = new Edge(vertex, vertex2);
						newEdge.setType(Type.OPENED_DOOR);
						graph.addEdge(newEdge);
						return;
					}
				}
			}
		}
	}
	
	public void closeDoor(Edge edge) {
		edge.setType(Type.CLOSED_DOOR);
	}
	
	public void closeDoorRandom() {
		Edge edge = graph.randomEdge();
		if (edge.getType() != Type.CLOSED_DOOR) {
			closeDoor(edge);
		}
	}

	/**
	 * Fonction qui regarde si le joueure est mort (si le méchant et le joueur sont sur la même case)
	 * @return vrai si le joueur est mort faux sinon
	 */
	public boolean checkDead(){
		return player.getX() == enemy.getX() && player.getY() == enemy.getY();
	}
}
