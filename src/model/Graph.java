package model;

import model.Labyrinth.Directions;

import java.util.Set;

import org.jgrapht.*;
import org.jgrapht.graph.SimpleGraph;

public class Graph {

	private SimpleGraph<Vertex, Edge> simpleGraph;

	public Graph() {
		super();
		this.simpleGraph = new SimpleGraph<Vertex, Edge>(Edge.class);
		System.out.println("Intance de la classe Graph cree !");
	}
	
	public SimpleGraph<Vertex, Edge> getGraph() {
		return this.simpleGraph;
	}

	public Edge getEdge(Vertex vertex, Directions dir) {
		// TODO Auto-generated method stub
		return this.simpleGraph.getEdge(vertex, this.getVertexByDir(vertex, dir));
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
		return this.simpleGraph.vertexSet();
	}

	public void addVertex(Vertex next) {
		// TODO Auto-generated method stub
		try {
			this.simpleGraph.addVertex(next);
		} catch (NullPointerException e) {
			// TODO: handle exception
			Model.usage("Graph.java:addVertex(...): The specified vertex is null !");
		}

	}

	public void addEdge(Vertex vertex, Vertex next) {
		// TODO Auto-generated method stub
		try {
			this.simpleGraph.addEdge(vertex, next);
		} catch (IllegalArgumentException e) {
			// TODO: handle exception
			Model.usage("Graph.java:addEdge(...): Source or target vertices are not found in the graph");
		} catch (NullPointerException npe) {
			// TODO: handle exception
			Model.usage("One of the vertices is null");
		}
	}

	public boolean doesntExist(Vertex vertex, Directions dir) {
		// TODO Auto-generated method stub
		boolean notExistVertex = (this.getVertexByDir(vertex, dir) == null) ? true : false;
		return notExistVertex;
	}
	
	public boolean containsEdge(Vertex source, Vertex target) {
		return this.simpleGraph.containsEdge(source, target);
	}
	
	public String toString() {
		StringBuffer str = new StringBuffer("Graph: \n");
		for (Vertex vertex : this.vertexSet()) {
			str.append(vertex.toString());
			str.append("\n");
		}
		return str.toString();
	}
}
