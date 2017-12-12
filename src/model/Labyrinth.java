/**
 *
 */
package model;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Random;
import java.util.Vector;




/**
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
		this.player = new Player(0, 0);
		this.enemy = new Enemy(0, 0);
		System.out.println("Intance de la classe Labyrinth cree !");
	}

	public Labyrinth(Graph graph) {
//		super();
		this.graph = graph;
		this.player = new Player(0, 0);
		this.enemy = new Enemy(15, 15);
		this.manhattan = new int[WIDTH][HEIGHT];
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

	public void launchManhattan(Vertex target) throws CloneNotSupportedException {
		for(int i = 0; i < 16; i++){
			for(int j = 0; j < 16; j++){
				manhattan[i][j] = -1;
			}
		}
		calculateManhattanDistance(target, 0);
		int i = 0;
		i++;
	}

	public void buildRandomPath(Vertex vertex) {
		// Une liste aleatoire des 4 directions
		Vector<Directions> v = new Vector<Directions>();

		for (int i = 0; i < 4; ++i) {
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

	/* Quelques prédicats pour détecter une porte ouverte, fermée, un couloir ou un mur... */

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



}
