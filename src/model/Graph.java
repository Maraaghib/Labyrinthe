package model;

import model.Labyrinth.Directions;

import java.util.ArrayList;

public class Graph {
	private ArrayList<Vertex> vertices;
	private ArrayList<Edge> edges;
	
	public Graph() {
		vertices = new ArrayList<Vertex>();
		edges = new ArrayList<Edge>();
		this.vertices.add(new Vertex());
		System.out.println("Intance de la classe Graph cree !");
	}
	
	public Graph(Vertex vertex) {
		vertices = new ArrayList<Vertex>();
		edges = new ArrayList<Edge>();
		this.addVertex(vertex);
	}

	public Edge getEdge(Vertex vertex, Directions dir) throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		for (Edge edge : this.edges) {
			if(edge.equals(new Edge(vertex, this.getVertexByDir(vertex, dir)))) {
				return (Edge) edge.clone();
			}
		}
		return null;
	}
	
	public Vertex getvertexByCoord(int x, int y) {
		Vertex vertex = new Vertex(x, y);
		for (Vertex ver : this.vertexSet()) {
			if (vertex.equals(new Vertex(x, y))) {
				return vertex;
			}
		}
		return null;
	}

	public Vertex getVertexByDir(Vertex actual, Directions dir) {
		// TODO Auto-generated method stub
		Vertex vertex = new Vertex(actual.getX(), actual.getY());
		Vertex testVertex = null;
		try {
			testVertex = (Vertex) vertex.clone(); // Avec leqeul tester le débordement, avant la mise à jour des coordonnées
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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

		if (testVertex.inBorders(dir)) {
			return vertex;
		}

		return null;
	}

	public ArrayList<Vertex> vertexSet() {
		// TODO Auto-generated method stub
		return this.vertices;
	}
	
	public ArrayList<Edge> edgeSet() {
		return this.edges;
	}

	public boolean addVertex(Vertex next) {
		// TODO Auto-generated method stub
		
		if(!this.containsVertex(next)) {
			this.vertices.add(next); 
			return true;
		}
		return false;
	}

	public void addEdge(Vertex vertex, Vertex next) {
		// TODO Auto-generated method stub
		Edge edge = new Edge(vertex, next);
		if(!this.containsEdge(vertex, next)){
			this.edges.add(edge);
		}
	}

	public boolean doesntExist(Vertex vertex, Directions dir) {
		// TODO Auto-generated method stub
		return !this.containsVertex(this.getVertexByDir(vertex, dir));
	}
	
	public boolean containsVertex(Vertex vertex) {
		if (vertex != null) {
			for (Vertex ver : vertices) {
				if (ver.equals(vertex)) {
					return true;
				}
			}
		}
		
		return false;
	}
	
	public boolean containsEdge(Vertex source, Vertex target) {
		if (source == null || target == null) {
			return false;
		}
		
		for (Edge edge : this.edgeSet()) {
			if (edge.equals(new Edge(source, target))) {
				return true;
			}
		}
		
		return false;
	}
	
	public boolean containsEdge(Edge edge) {
		if (edge == null) {
			return false;
		}
		return this.edges.contains(edge);
		
	}
	
	public String toString() {
		StringBuffer str = new StringBuffer("Graph: \n");
		for (Edge edge : this.edgeSet()) {
			str.append(edge.toString());
			str.append("\n");
		}
		return str.toString();
	}
}
