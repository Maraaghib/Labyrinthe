package model;

import model.Labyrinth.Directions;

import java.util.Set;

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

	public Set<Vertex> vertexSet() {
		// TODO Auto-generated method stub
		return this.graph.vertexSet();
	}

	public void addVertex(Vertex next) {
		// TODO Auto-generated method stub
		try {
			this.graph.addVertex(next);
		} catch (NullPointerException e) {
			// TODO: handle exception
			Model.usage("Graph.java:addVertex(...): The specified vertex is null !");
		}
		
	}

	public void addEdge(Vertex vertex, Vertex next) {
		// TODO Auto-generated method stub
		try {
			
		} catch (IllegalArgumentException e) {
			// TODO: handle exception
			Model.usage("Graph.java:addVertex(...): Source or target vertices are not found in the graph");
		}
	}

	public boolean doesntExist(Vertex vertex, Directions dir) {
		// TODO Auto-generated method stub
		boolean notExistVertex = (this.getVertexByDir(vertex, dir) == null) ? true : false;
		return notExistVertex;
	}
	
}
