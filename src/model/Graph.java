package model;

import model.Edge.Type;
import model.Labyrinth.Directions;

import java.util.ArrayList;
import java.util.Random;

/**
 * Classe utilisée pour représenter le labyrinthe modélisé par un graphe
 * @author Seerigne Amsatou SEYE & Fabien JACQUES
 *
 */
public class Graph {
	private ArrayList<Vertex> vertices;
	private ArrayList<Edge> edges;

	/**
	 * Crée une instance de Graph avec un seul sommet par défaut
	 */
	public Graph() {
		vertices = new ArrayList<Vertex>();
		edges = new ArrayList<Edge>();
		this.vertices.add(new Vertex());
		System.out.println("Intance de la classe Graph cree !");
	}

	/**
	 * Crée une instance de Graph avec le sommet passé en paramètre
	 * 
	 * @param vertex
	 *            Le seul sommet de cette arbre
	 */
	public Graph(Vertex vertex) {
		vertices = new ArrayList<Vertex>();
		edges = new ArrayList<Edge>();
		this.addVertex(vertex);
	}

	/**
	 * Cherche puis retourne une instance de Edge à partir d'un sommet et une
	 * direction, sinon retourne null
	 * 
	 * @param vertex
	 *            Le sommet origine de l'arête
	 * @param dir
	 *            La direction de l'arête
	 * @return Une instance de Edge
	 * @throws CloneNotSupportedException
	 *             Si le clone n'est pas supporté
	 */
	public Edge getEdge(Vertex vertex, Directions dir) throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		for (Edge edge : this.edges) {
			if (edge.equals(new Edge(vertex, this.getVertexByDir(vertex, dir)))) {
				return (Edge) edge.clone();
			}
		}
		return null;
	}

	/**
	 * Cherche puis retourne une instance de Edge à partir de deux sommets,
	 * sinon retourne null
	 * 
	 * @param source
	 *            Le sommet origine de l'arête
	 * @param target
	 *            Le sommet extrémité de l'arête
	 * @return Une instance de Edge
	 */
	public Edge getEdge(Vertex source, Vertex target) {
		Edge newEdge = new Edge(source, target);
		for (Edge edge : edges) {
			if (edge.equals(newEdge)) {
				try {
					return (Edge) edge.clone();
				} catch (CloneNotSupportedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return null;
	}

	/**
	 * Cherche puis retourne une instance de sommet à partir de ses coordonnées, sinon retourne null
	 * 
	 * @param x
	 *            abscisse du sommet
	 * @param y
	 *            ordonnée du sommet
	 * @return le sommet qui correspond aux coordonées passées en paramètre
	 */
	public Vertex getvertexByCoord(int x, int y) {
		Vertex vertex = new Vertex(x, y);
		for (Vertex ver : this.vertexSet()) {
			if (vertex.equals(new Vertex(x, y))) {
				return vertex;
			}
		}
		return null;
	}

	/**
	 * Retourne une instance du sommet adjacent à celui passé en paramètre selon la direction indiquée, sinon retourne null
	 * @param actual
	 *            Le sommet de départ
	 * @param dir
	 *            La direction
	 * @return Le sommet situé à la direction donnée de actual s'il existe et s'il est relié dans le graphe
	 */
	public Vertex getVertexByDir(Vertex actual, Directions dir) {
		// TODO Auto-generated method stub
		Vertex vertex = new Vertex(actual.getX(), actual.getY());
		Vertex testVertex = null;
		try {
			testVertex = (Vertex) vertex.clone(); // Avec leqeul tester le
													// débordement, avant la
													// mise à jour des
													// coordonnées
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		switch (dir) {
		case NORTH:
			vertex.setY(actual.getY() - 1);
			break;
		case SOUTH:
			vertex.setY(actual.getY() + 1);
			break;
		case EAST:
			vertex.setX(actual.getX() + 1);
			break;
		case WEST:
			vertex.setX(actual.getX() - 1);
			break;
		}

		if (testVertex.inBorders(dir)) {
			return vertex;
		}

		return null;
	}

	/**
	 * Retourne une instance de Edge selon le type passé en paramètre
	 * @param type
	 * 			Le type de l'arête
	 * @return L'arête définie par le type
	 */
	public Edge getEdgeByType(Type type) {
		for (Edge edge : edges) {
			if (edge.getType() == type) {
				try {
					return (Edge) edge.clone();
				} catch (CloneNotSupportedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return null;
	}
	
	/**
	 * Retourne un sommet au hasard dans le graphe
	 * @return Un sommet auu hasard
	 */
	public Vertex randomVertex() {
		Random random = new Random();
		Vertex randVertex = this.vertices.get(random.nextInt(this.vertices.size()));
		if (this.containsVertex(randVertex)) {
			return randVertex;
		}
		return null;
	}
	
	/**
	 * Retourne une arête au hasard dans le graphe
	 * @return Une arête au hasard
	 */
	public Edge randomEdge() {
		Random random = new Random();
		Edge randEdge = this.edges.get(random.nextInt(this.edges.size()));
		if (this.containsEdge(randEdge)) {
			return randEdge;
		}
		return null;
	}

	/**
	 * Retourne la liste de tous les sommets du graphe
	 * @return la liste des sommets du graphe
	 */
	public ArrayList<Vertex> vertexSet() {
		// TODO Auto-generated method stub
		return this.vertices;
	}

	/**
	 * Retourne la liste de toutes les arêtes du graphe
	 * @return la liste des arrêts du graphe
	 */
	public ArrayList<Edge> edgeSet() {
		return this.edges;
	}
	
	/**
	 * Ajoute le sommet spécifié dans le graphe
	 * @param next
	 * 		Le sommet à ajouter dans le graphe
	 * @return true si le sommet n'a pas encore existé et false sinon
	 */
	public boolean addVertex(Vertex next) {
		// TODO Auto-generated method stub

		if (!this.containsVertex(next)) {
			this.vertices.add(next);
			return true;
		}
		return false;
	}

	/**
	 * Ajoute l'arête caractérisée par ses deux sommets passés en paramètre
	 * @param vertex
	 * 			Le sommet origine de l'arête
	 * @param next
	 * 			Le sommet extrémité de l'arête
	 */
	public void addEdge(Vertex vertex, Vertex next) {
		// TODO Auto-generated method stub
		Edge edge = new Edge(vertex, next);
		if (!this.containsEdge(vertex, next)) {
			this.edges.add(edge);
		}
	}

	/**
	 * Ajoute l'arête spécifiée dans le graphe
	 * @param edge
	 * 			L'arête à ajouter dans le graphe
	 */
	public void addEdge(Edge edge) {
		if (!this.containsEdge(edge)) {
			this.edges.add(edge);
		}
	}

	/**
	 * Teste si le sommet adjacent à vertex en direction dir n'existe pas
	 * @param vertex
	 * 			Le sommet de départ
	 * @param dir
	 * 			La direction
	 * @return true si le sommet n'existe pas et false sinon
	 */
	public boolean doesntExist(Vertex vertex, Directions dir) {
		// TODO Auto-generated method stub
		return !this.containsVertex(this.getVertexByDir(vertex, dir));
	}

	/**
	 * Teste si le sommet spécifié appartient au graphe
	 * @param vertex
	 * 			Le sommet à chercher
	 * @return true si vertex appartient au graphe
	 */
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

	/**
	 * Teste si l'arête caractérisée par ces sommets source et target appartient au graphe
	 * @param source
	 * 			Le sommet origine de l'arête à chercher
	 * @param target
	 * 			Le sommet extrémité de l'arête à chercher
	 * @return true si l'arête reliant les deux sommets appartient au graphe
	 */
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

	/**
	 * Tetse si l'arête spécifiée appartient au graphe
	 * @param edge
	 * 			L'arête à chercher
	 * @return true si l'arrête appartient au graphe
	 */
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
