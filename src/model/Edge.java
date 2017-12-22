/**
 * 
 */
package model;


/**
 * Classe utilisée par la classe Graph pour représenter les arêtes du graphe
 * @author Serigne Amsatou SEYE
 * @author Fabien JACQUES
 *
 */
public class Edge implements Comparable<Edge> {
	
	private Vertex source;
	private Vertex target;

	public enum Type{
		OPENED_DOOR,
		CLOSED_DOOR,
		CORRIDOR;
	};
	
	private Type type;
	
	/**
	 * Crée une instance Edge en spécifiant le type
	 * 
	 * @param type
	 * 			Le type de l'arête
	 */
	public Edge(Type type) {
		this.type = type;
	}
	
	/**
	 * Crée un instance Edge en donnant les deux sommets
	 * 
	 * @param source
	 * 			Le sommet origine
	 * @param target
	 * 			Le sommet extrémité
	 */
	public Edge(Vertex source, Vertex target) {
		this.type = Type.CORRIDOR;
		this.source = source;
		this.target = target;
	}
	
	/**
	 * Crée une instance Edge en lui passant deux sommets et le type de l'arête
	 * 
	 * @param source
	 * 			Le sommet origine
	 * @param target
	 * 			Le soommet extrémité
	 * @param type
	 * 			Le type de l'arête
	 */
	public Edge(Vertex source, Vertex target, Type type) {
		this.source = source;
		this.target = target;
		this.type = type;
	}
	
	/**
	 * Le constructeur par défaut
	 */
	public Edge() {
		this.type = Type.CORRIDOR;
	}
	
	/**
	 * Définit le sommet origine de l'arête
	 * @param source
	 * 			Le nouveau sommet origine
	 */
	public void setSource(Vertex source) {
		this.source = source;
	}

	/**
	 * Définit le sommet extrémité
	 * @param target
	 * 			Le nouveau sommet extrémité
	 */
	public void setTarget(Vertex target) {
		this.target = target;
	}
	
	/**
	 * Retourne le sommet origine de cette arête
	 * @return Le sommet origine de cette classe 
	 */
	public Vertex getSource() {
		Vertex vertex = null;
		try {
			 vertex = (Vertex) this.source.clone();
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return vertex;
	}
	
	/**
	 * Retourne le sommet extrémité de cette arête
	 * @return Le sommet extrémité de cette arête
	 */
	public Vertex getTarget() {
		Vertex vertex = null;
		try {
			vertex = (Vertex) this.target.clone();
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return vertex;
	}

	/**
	 * Retourne le type de cette arête
	 * @return Le type de cette arête
	 */
	public Type getType() {
		return type;
	}

	/**
	 * Définit le type de cette arête
	 * @param type
	 * 			Le nouveau type de l'arête
	 */
	public void setType(Type type) {
		this.type = type;
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return new Edge((Vertex)this.getSource().clone(), (Vertex)this.getTarget().clone(), this.getType());
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return (this.getSource().equals(((Edge)obj).getSource()) && this.getTarget().equals(((Edge)obj).getTarget())) || (this.getSource().equals(((Edge)obj).getTarget()) && this.getTarget().equals(((Edge)obj).getSource()));
	}

	@Override
	public String toString() {
		return "Edge [" + source + "---" + target + "]";
	}

	@Override
	public int compareTo(Edge o) {
		// TODO Auto-generated method stub
		int source = this.getSource().compareTo((o).getSource());
		if (source != 0) {
			return source;
		} else {
			return this.getTarget().compareTo((o).getTarget());
		}
	}

}
