/**
 * 
 */
package model;

import org.jgrapht.graph.DefaultEdge;

/**
 * @author hamza
 *
 */
public class Edge extends DefaultEdge implements Comparable<Edge> {

	public enum Type{
		OPENED_DOOR,
		CLOSED_DOOR,
		CORRIDOR;
	};
	
	private Type type;
	
	public Edge(Type type) {
		super();
		this.type = type;
	}
	
	// default
	public Edge() {
		super();
		this.type = Type.CORRIDOR;
	}
	
	public Vertex getSource() {
		return (Vertex) super.getTarget();
	}
	
	public Vertex getTarget() {
		return (Vertex) super.getTarget();
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
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
