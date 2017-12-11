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

	public Labyrinth() {
		this.graph = new Graph();
		this.player = new Player(0, 0);
		System.out.println("Intance de la classe Labyrinth cree !");
	}

	public Labyrinth(Graph graph) {
//		super();
		this.graph = graph;
		this.player = new Player(0, 0);
	}

	public Graph getGraph() {
		return this.graph;
	}
	
	public Player getPlayer() {
		return this.player;
	}

	private void calculateManhattanDistance(Vertex source, Vertex target) throws CloneNotSupportedException {
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
						if(next != source) { // next.equal(source) ??
							fifo.add(next);
						}
					}
				}
			}
		}
	}

	public void launchManhattan(Vertex source, Vertex target) throws CloneNotSupportedException {
		for (Vertex vertex : graph.vertexSet()) {
			vertex.setNbr(0);
		}
		calculateManhattanDistance(source, target);
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
