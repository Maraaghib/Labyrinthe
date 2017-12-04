package model;

import model.Labyrinth.Directions;
import org.jgrapht.*;
import org.jgrapht.graph.SimpleGraph;

public class Graph {
	
	private SimpleGraph<Vertex, Edge> graph;
	
	public Graph() {
		super();
		this.graph = new SimpleGraph<Vertex, Edge>(Edge.class);
	}

	public Edge getEdge(Vertex vertex, Directions dir) {
		// TODO Auto-generated method stub
		return this.graph.getEdge(vertex, this.getVertexByDir(vertex, dir));
	}

	public Vertex getVertexByDir(Vertex actual, Directions dir) {
		// TODO Auto-generated method stub
		Vertex vertex = new Vertex(actual.getX(), actual.getY());
		switch (dir) {
			case NORTH:
				vertex.setY(actual.getY()-1);
				break;
			case SOUTH:
				vertex.setY(actual.getY()+1);
				break;
			case EAST:
				vertex.setX(actual.getX()+1);
				break;
			case WEST:
				vertex.setX(actual.getX()-1);
				break;
		}
		
		if (vertex.inBorders(dir)) {
			return vertex;
		}
		
		return null;
	}

	public Vertex vertexSet() {
		// TODO Auto-generated method stub
		return null;
	}

	public void addVertex(Vertex next) {
		// TODO Auto-generated method stub
		
	}

	public void addEdge(Vertex vertex, Vertex next) {
		// TODO Auto-generated method stub
		
	}

	public boolean doesntExist(Vertex vertex, Directions dir) {
		// TODO Auto-generated method stub
		return false;
	}
	
}
