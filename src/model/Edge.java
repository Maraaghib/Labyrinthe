/**
 * 
 */
package model;


/**
 * Classe utilis�e par la classe Graph pour repr�senter les ar�tes du graphe
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
	 * Cr�e une instance Edge en sp�cifiant le type
	 * 
	 * @param type
	 * 			Le type de l'ar�te
	 */
	public Edge(Type type) {
		this.type = type;
	}
	
	/**
	 * Cr�e un instance Edge en donnant les deux sommets
	 * 
	 * @param source
	 * 			Le sommet origine
	 * @param target
	 * 			Le sommet extr�mit�
	 */
	public Edge(Vertex source, Vertex target) {
		this.type = Type.CORRIDOR;
		this.source = source;
		this.target = target;
	}
	
	/**
	 * Cr�e une instance Edge en lui passant deux sommets et le type de l'ar�te
	 * 
	 * @param source
	 * 			Le sommet origine
	 * @param target
	 * 			Le soommet extr�mit�
	 * @param type
	 * 			Le type de l'ar�te
	 */
	public Edge(Vertex source, Vertex target, Type type) {
		this.source = source;
		this.target = target;
		this.type = type;
	}
	
	/**
	 * Le constructeur par d�faut
	 */
	public Edge() {
		this.type = Type.CORRIDOR;
	}
	
	/**
	 * D�finit le sommet origine de l'ar�te
	 * @param source
	 * 			Le nouveau sommet origine
	 */
	public void setSource(Vertex source) {
		this.source = source;
	}

	/**
	 * D�finit le sommet extr�mit�
	 * @param target
	 * 			Le nouveau sommet extr�mit�
	 */
	public void setTarget(Vertex target) {
		this.target = target;
	}
	
	/**
	 * Retourne le sommet origine de cette ar�te
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
	 * Retourne le sommet extr�mit� de cette ar�te
	 * @return Le sommet extr�mit� de cette ar�te
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
	 * Retourne le type de cette ar�te
	 * @return Le type de cette ar�te
	 */
	public Type getType() {
		return type;
	}

	/**
	 * D�finit le type de cette ar�te
	 * @param type
	 * 			Le nouveau type de l'ar�te
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
