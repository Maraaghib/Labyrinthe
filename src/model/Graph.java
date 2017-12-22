package model;

import model.Edge.Type;
import model.Labyrinth.Directions;

import java.util.ArrayList;
import java.util.Random;

/**
 * Classe utilis�e pour repr�senter le labyrinthe mod�lis� par un graphe
 * @author Seerigne Amsatou SEYE & Fabien JACQUES
 *
 */
public class Graph {
	private ArrayList<Vertex> vertices;
	private ArrayList<Edge> edges;

	/**
	 * Cr�e une instance de Graph avec un seul sommet par d�faut
	 */
	public Graph() {
		vertices = new ArrayList<Vertex>();
		edges = new ArrayList<Edge>();
		this.vertices.add(new Vertex());
		System.out.println("Intance de la classe Graph cree !");
	}

	/**
	 * Cr�e une instance de Graph avec le sommet pass� en param�tre
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
	 * Cherche puis retourne une instance de Edge � partir d'un sommet et une
	 * direction, sinon retourne null
	 * 
	 * @param vertex
	 *            Le sommet origine de l'ar�te
	 * @param dir
	 *            La direction de l'ar�te
	 * @return Une instance de Edge
	 * @throws CloneNotSupportedException
	 *             Si le clone n'est pas support�
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
	 * Cherche puis retourne une instance de Edge � partir de deux sommets,
	 * sinon retourne null
	 * 
	 * @param source
	 *            Le sommet origine de l'ar�te
	 * @param target
	 *            Le sommet extr�mit� de l'ar�te
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
	 * Cherche puis retourne une instance de sommet � partir de ses coordonn�es, sinon retourne null
	 * 
	 * @param x
	 *            abscisse du sommet
	 * @param y
	 *            ordonn�e du sommet
	 * @return le sommet qui correspond aux coordon�es pass�es en param�tre
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
	 * Retourne une instance du sommet adjacent � celui pass� en param�tre selon la direction indiqu�e, sinon retourne null
	 * @param actual
	 *            Le sommet de d�part
	 * @param dir
	 *            La direction
	 * @return Le sommet situ� � la direction donn�e de actual s'il existe et s'il est reli� dans le graphe
	 */
	public Vertex getVertexByDir(Vertex actual, Directions dir) {
		// TODO Auto-generated method stub
		Vertex vertex = new Vertex(actual.getX(), actual.getY());
		Vertex testVertex = null;
		try {
			testVertex = (Vertex) vertex.clone(); // Avec leqeul tester le
													// d�bordement, avant la
													// mise � jour des
													// coordonn�es
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
	 * Retourne une instance de Edge selon le type pass� en param�tre
	 * @param type
	 * 			Le type de l'ar�te
	 * @return L'ar�te d�finie par le type
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
	 * Retourne une ar�te au hasard dans le graphe
	 * @return Une ar�te au hasard
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
	 * Retourne la liste de toutes les ar�tes du graphe
	 * @return la liste des arr�ts du graphe
	 */
	public ArrayList<Edge> edgeSet() {
		return this.edges;
	}
	
	/**
	 * Ajoute le sommet sp�cifi� dans le graphe
	 * @param next
	 * 		Le sommet � ajouter dans le graphe
	 * @return true si le sommet n'a pas encore exist� et false sinon
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
	 * Ajoute l'ar�te caract�ris�e par ses deux sommets pass�s en param�tre
	 * @param vertex
	 * 			Le sommet origine de l'ar�te
	 * @param next
	 * 			Le sommet extr�mit� de l'ar�te
	 */
	public void addEdge(Vertex vertex, Vertex next) {
		// TODO Auto-generated method stub
		Edge edge = new Edge(vertex, next);
		if (!this.containsEdge(vertex, next)) {
			this.edges.add(edge);
		}
	}

	/**
	 * Ajoute l'ar�te sp�cifi�e dans le graphe
	 * @param edge
	 * 			L'ar�te � ajouter dans le graphe
	 */
	public void addEdge(Edge edge) {
		if (!this.containsEdge(edge)) {
			this.edges.add(edge);
		}
	}

	/**
	 * Teste si le sommet adjacent � vertex en direction dir n'existe pas
	 * @param vertex
	 * 			Le sommet de d�part
	 * @param dir
	 * 			La direction
	 * @return true si le sommet n'existe pas et false sinon
	 */
	public boolean doesntExist(Vertex vertex, Directions dir) {
		// TODO Auto-generated method stub
		return !this.containsVertex(this.getVertexByDir(vertex, dir));
	}

	/**
	 * Teste si le sommet sp�cifi� appartient au graphe
	 * @param vertex
	 * 			Le sommet � chercher
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
	 * Teste si l'ar�te caract�ris�e par ces sommets source et target appartient au graphe
	 * @param source
	 * 			Le sommet origine de l'ar�te � chercher
	 * @param target
	 * 			Le sommet extr�mit� de l'ar�te � chercher
	 * @return true si l'ar�te reliant les deux sommets appartient au graphe
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
	 * Tetse si l'ar�te sp�cifi�e appartient au graphe
	 * @param edge
	 * 			L'ar�te � chercher
	 * @return true si l'arr�te appartient au graphe
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
