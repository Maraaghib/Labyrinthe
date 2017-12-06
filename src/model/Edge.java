/**
 * 
 */
package model;

import org.jgrapht.graph.DefaultEdge;

/**
 * @author hamza
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
	
	public Edge(Type type) {
//		super();
		this.type = type;
	}
	
	public Edge(Vertex source, Vertex target) {
//		super();
		this.source = source;
		this.target = target;
	}

	public Edge(Vertex source, Vertex target, Type type) {
		this.source = source;
		this.target = target;
		this.type = type;
	}

	// default
	public Edge() {
		super();
		this.type = Type.CORRIDOR;
	}
	
	public void setSource(Vertex source) {
		this.source = source;
	}

	public void setTarget(Vertex target) {
		this.target = target;
	}

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

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}
	

//	@Override
//	public boolean equals(Object obj) {
//		// TODO Auto-generated method stub
//		return this.getSource().equals(((Edge)obj).getSource()) && this.getTarget().equals(((Edge)obj).getTarget());
//	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return new Edge((Vertex)this.getSource().clone(), (Vertex)this.getTarget().clone(), this.getType());
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return this.getSource().equals(((Edge)obj).getSource()) && this.getTarget().equals(((Edge)obj).getTarget());
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
