/**
 * 
 */
package model;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Random;
import java.util.Vector;

import com.sun.javafx.scene.traversal.Direction;



/**
 * @author saseye
 *
 */
public class Labyrinth {
	
	protected static final int TOP = 0;
	protected static final int DOWN = 15;
	protected static final int LEFT = 0;
	protected static final int RIGHT = 15;
	
	public static final int WIDTH = 16;
	public static final int HEIGHT = 16;
	
	public enum Directions{
		EAST,
		WEST,
		NORTH,
		SOUTH;
	};
	
	private Directions dir;
	private Graph graph;
	
	public Labyrinth() {
		graph = new Graph();
		System.out.println("Intance de la classe Labyrinth cree !");
	}
	
	public Graph getGraph() {
		return this.graph;
	}
	
	private void calculateManhattanDistance(Vertex source, Vertex target) {
		Queue<Vertex> fifo = new ArrayDeque<Vertex>();
		target.setNbr(1);
		fifo.add(target);
		while (!fifo.isEmpty()) {
			Vertex actual = fifo.remove();
			for (Directions dir : Directions.values()) {
				if (this.isOpened(actual, dir)) {
					Vertex next = graph.getVertexByDir(actual, dir);
					if (next.getNbr() == 0) {
						next.setNbr(actual.getNbr()+1);
						if(next != source) {
							fifo.add(next);
						}
					}
				}
			}
		}
	}
	
	public void launchManhattan(Vertex source, Vertex target) {
		for (Vertex vertex : graph.vertexSet()) { // vertexSet peut vouloir dire ensemble de sommets (set of vertexes)
			vertex.setNbr(0);
		}
		calculateManhattanDistance(source, target);
	}
	
	public void buildRandomPath(Vertex vertex) {
		System.out.println("buildRandomPath appel�e "+(++Model.cpt)+" fois !");
		// Une liste aléatoire des 4 directions
		Vector<Directions> v = new Vector<Directions>();
		
		for (int i = 0; i < 4; ++i) {
			v.add(Directions.values()[i]);
		}
		
		Directions directions[] = new Directions[4];
		Random random = new Random(); // A déplacer dans la boucle ?
		for (int i = 0; i < directions.length; ++i) {
			int index = random.nextInt(v.size());
			directions[i] = v.get(index);
			v.remove(index);
		}

		System.out.println("buildRandomPath: vertex = "+vertex);
		for (int i = 0; i < directions.length; i++) {
			System.out.println(directions[i]);
		}
		
		// Pour chacune de ces directions, on avance en profondeur d'abord
		for (int i = 0; i < 4; ++i) {
			Directions dir = directions[i];
			if (vertex.inBorders(dir) && graph.doesntExist(vertex, dir)) {
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
				graph.addVertex(vertex);
				graph.addVertex(next);
				graph.addEdge(vertex, next);
				buildRandomPath(next);
			}
		}
	}
	
	/* Quelques prédicats pour détecter une porte ouverte, fermée, un couloir ou un mur... */
	
	public boolean isWall(Vertex vertex, Directions dir) {
		Edge edge = graph.getEdge(vertex, dir);
		return (edge == null);
	}
	
	public boolean isClosed(Vertex vertex, Directions dir) {
		Edge edge = graph.getEdge(vertex, dir);
		return (edge == null || (edge.getType() == Edge.Type.CLOSED_DOOR));
	}
	
	public boolean isOpened(Vertex vertex, Directions dir) {
		Edge edge = graph.getEdge(vertex, dir);
		return (edge != null && (edge.getType() != Edge.Type.CLOSED_DOOR));
	}
	
	public boolean isClosedDoor(Vertex vertex, Directions dir) {
		Edge edge = graph.getEdge(vertex, dir);
		return (edge != null && (edge.getType() == Edge.Type.CLOSED_DOOR));
	}
	
	public boolean isOpenedDoor(Vertex vertex, Directions dir) {
		Edge edge = graph.getEdge(vertex, dir);
		return (edge != null && (edge.getType() == Edge.Type.OPENED_DOOR));
	}
	
	
	
}
