import java.util.ArrayList;


public class MyEdge implements Edge {

	private ArrayList<Vertex> vertices = new ArrayList<Vertex>();
	
	public MyEdge(Vertex v1, Vertex v2) {
		vertices.add(v1);
		vertices.add(v2);
	}
	
	@Override
	public ArrayList<Vertex> vertices() {
		return vertices;
	}
	
	public Vertex getAdjacent(Vertex v) {
		if (v.getId() == vertices.get(0).getId())
			return vertices.get(1);
		else if (v.getId() == vertices.get(1).getId())
			return vertices.get(0);
		else
			return null;
	}
	
	public boolean contains(Vertex v) {
		return (v.getId() == vertices.get(0).getId() || v.getId() == vertices.get(1).getId());
	}
	
	//returns true if this edge contains v1 and v2
	public boolean contains(Vertex v1, Vertex v2) {
		return ((v1.getId() == vertices.get(0).getId() && v2.getId() == vertices.get(1).getId()) || (v1.getId() == vertices.get(1).getId() && v2.getId() == vertices.get(0).getId()));
	}

	public String toString() {
		return "<v" + vertices.get(0).getId() + "-v" + vertices.get(1).getId() + ">";
	}
}
