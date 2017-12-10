package model;

import model.Labyrinth.Directions;

import java.util.ArrayList;
import java.util.Set;

import org.jgrapht.*;
import org.jgrapht.graph.SimpleGraph;

public class Graph {

	private SimpleGraph<Vertex, Edge> simpleGraph;
	private ArrayList<Vertex> vertices;
	private ArrayList<Edge> edges;
	
	public Graph() {
//		super();
//		Vertex vertex = new Vertex();
//		this.simpleGraph = new SimpleGraph<Vertex, Edge>(Edge.class);
//		this.simpleGraph.addVertex(vertex);
		
		vertices = new ArrayList<Vertex>();
		edges = new ArrayList<Edge>();
		this.vertices.add(new Vertex());
		System.out.println("Intance de la classe Graph cree !");
	}
	
	public Graph(Vertex vertex) {
//		super();
//		this.simpleGraph = new SimpleGraph<Vertex, Edge>(Edge.class);
//		this.simpleGraph.addVertex(vertex);
		
		vertices = new ArrayList<Vertex>();
		edges = new ArrayList<Edge>();
		this.addVertex(vertex);
	}

//	public SimpleGraph<Vertex, Edge> getGraph() {
//		return this.simpleGraph;
//	}

	public Edge getEdge(Vertex vertex, Directions dir) throws CloneNotSupportedException {
		// TODO Auto-generated method stub
//		return this.getEdge(vertex, this.getVertexByDir(vertex, dir));
		
		for (Edge edge : this.edges) {
			if(edge.equals(new Edge(vertex, this.getVertexByDir(vertex, dir)))) {
				return (Edge) edge.clone();
			}
		}
		return null;
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

	/*public Set<Vertex> vertexSet() {
		// TODO Auto-generated method stub
		return this.simpleGraph.vertexSet();
	}*/
	
	public ArrayList<Vertex> vertexSet() {
		// TODO Auto-generated method stub
		return this.vertices;
	}
	
	public ArrayList<Edge> edgeSet() {
		return this.edges;
	}

	public boolean addVertex(Vertex next) {
		// TODO Auto-generated method stub

		/*try {
			if (!this.containsVertex(next)) {
				this.simpleGraph.addVertex(next);
			}
		} catch (NullPointerException e) {
			// TODO: handle exception
			Model.usage("Graph.java:addVertex(...): The specified vertex is null !");
		}
		System.out.println("addVertex appelee avec succès !");*/
		
		if(!this.containsVertex(next)) {
			this.vertices.add(next); // Récupérer le résultat booléen et le retourner
			return true;
		}
		return false;
	}

	public void addEdge(Vertex vertex, Vertex next) {
		// TODO Auto-generated method stub
		/*try {
			if(!this.containsEdge(vertex, next)) {
				this.simpleGraph.addEdge(vertex, next);
			}
		} catch (IllegalArgumentException e) {
			// TODO: handle exception
			Model.usage("Graph.java:addEdge(...): Source or target vertices are not found in the graph");
		} catch (NullPointerException npe) {
			// TODO: handle exception
			Model.usage("One of the vertices is null");
		}
		System.out.println("addEdge appelee avec succès !");*/
		
		Edge edge = new Edge(vertex, next);
		if(!this.containsEdge(vertex, next)){
			this.edges.add(edge);
		}
	}

	public boolean doesntExist(Vertex vertex, Directions dir) {
		// TODO Auto-generated method stub
//		boolean notExistVertex = (this.getVertexByDir(vertex, dir) == null) ? true : false;
		return !this.containsVertex(this.getVertexByDir(vertex, dir));
	}
	
	public boolean containsVertex(Vertex vertex) {
//		return this.simpleGraph.containsVertex(vertex);
		
		if (vertex != null) {
			for (Vertex ver : vertices) {
				if (ver.equals(vertex)) {
					return true;
				}
			}
		}
		
		return false;
		
//		return this.vertices.contains(vertex);
	}
	
	public boolean containsEdge(Vertex source, Vertex target) {
//		return this.simpleGraph.containsEdge(source, target);
		
		if (source == null || target == null) {
			return false;
		}
		
		for (Edge edge : this.edgeSet()) {
			if (edge.equals(new Edge(source, target))) {
				return true;
			}
		}
		
		return false;
		
//		return this.edges.contains(new Edge(source, target));
	}
	
	public boolean containsEdge(Edge edge) {
		if (edge == null) {
			return false;
		}
		return this.edges.contains(edge);
		
	}
	
	public String toString() {
		StringBuffer str = new StringBuffer("Graph: \n");
//		for (Vertex vertex : this.vertexSet()) {
//			str.append(vertex.toString());
//			str.append("\n");
//		}
		for (Edge edge : this.edgeSet()) {
			str.append(edge.toString());
			str.append("\n");
		}
		return str.toString();
	}
}
