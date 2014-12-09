import java.util.ArrayList;


public class MyVertex implements Vertex {

	private int id = 0;
	private GraphColor color;
	private ArrayList<Edge> edges;
	private ArrayList<Vertex> vertices;
	private int x, y;
	
	
	public MyVertex() {
		edges = new ArrayList<Edge>();
		vertices = new ArrayList<Vertex>();
	}
	
	public int getX() {
		return x;
	}
	
	public void setX( int x ) {
		this.x = x;
	}
	
	public int getY() {
		return y;
	}
	
	public void setY( int y ) {
		this.y = y;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int newID) {
		id = newID;
	}
	
	@Override
	public GraphColor getColor() {
		return color;
	}

	@Override
	public void setColor(GraphColor newVal) {
		color = newVal;
	}

	public void addEdge(Edge e) {
		edges.add(e);
	}
	
	public void removeEdge(Vertex v1, Vertex v2) {
		for (int i = 0; i < edges.size(); i++) {
			if ((edges.get(i).vertices().get(0).getId() == v1.getId() && edges.get(i).vertices().get(1).getId() == v2.getId()) || (edges.get(i).vertices().get(0).getId() == v2.getId() && edges.get(i).vertices().get(1).getId() == v1.getId())) {
				//for (int j = 0; j < vertices.size(); j++ ) {
				//	if (vertices.get(i).getId() == v1.getId() || vertices.get(i).getId() == v2.getId())
				//		vertices.remove(i);
				//}
				edges.remove(i);
			}
		}
	}
	
	public void addVertex(Vertex v) {
		vertices.add(v);
	}
	
	public void removeVertex(Vertex v) {
		//find and remove vertex
		for (int i = 0; i < vertices.size(); i++) {
			if (vertices.get(i).getId() == v.getId())
				vertices.remove(i);
		}
		
		//remove all edges containing vertex
		for (int i = 0; i < edges.size(); i++) {
			if (((MyEdge) edges.get(i)).contains(v))
				edges.remove(i);
		}
	}
	
	@Override
	public ArrayList<Edge> incidentEdges() {
		return edges;
	}

	@Override
	public ArrayList<Vertex> adjacentVertices() {
		return vertices;
	}
	
	public String toString() {
		return "<v" + id + ":" + color + ":" + x + "," + y + ">";
	}

}
