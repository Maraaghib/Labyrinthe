/**
 * 
 */
package model;

import java.util.ArrayDeque;
import java.util.Queue;


/**
 * @author saseye
 *
 */
public class Labyrinth {
	
	public enum Directions{
		EAST,
		WEST,
		NORTH,
		SOUTH;
	};
	
	private Directions dir;
	private Graph graph;
	
	public Labyrinth() {
		
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
	
//	public void launchManhattan(Vertex source, Vertex target) {
//		for (Vertex vertex : graph.vertexSet()) {
//			vertex.setNbr(0);
//		}
//		calculateManhattanDistance(source, target);
//	}
	
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
