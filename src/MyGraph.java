import java.util.ArrayList;


public class MyGraph implements Graph{

	private ArrayList<Vertex> vertices = new ArrayList<Vertex>();
	private ArrayList<Edge> edges = new ArrayList<Edge>();
	private int idNum = 0;
	
	@Override
	public ArrayList<Vertex> vertices() {
		return vertices;
	}

	@Override
	public Vertex addVertex(Vertex v) {
		MyVertex newVertex = (MyVertex) v;
		//set id
		newVertex.setId(idNum);
		//increment for next addition
		idNum++;
		vertices.add(newVertex);
		return newVertex;
	}
	
	@Override
	public boolean removeVertex(Vertex v) {
		if (v == null) return false;
		
		//remove all edges containing the vertex
		for (int i = 0; i < edges.size(); i++) {
			//check edge for vertex, remove if there
			if (((MyEdge) edges.get(i)).contains(v)) {
				edges.remove(i);
			}
		}
		
		//remove v from all other vertecies
		for (int i = 0; i < vertices.size(); i++) {
			((MyVertex) vertices.get(i)).removeVertex(v);
		}
		//remove vertex
		return vertices.remove(v);
	}
	
	@Override
	public ArrayList<Edge> edges() {
		return edges;
	}

	@Override
	public Edge addEdge(Vertex v1, Vertex v2) {
		//if edge exists, return null
		if (findEdge(v1, v2) != null) return null;
		//if v1 = v2, return null
		if (v1.getElement().getX() == v2.getElement().getX() && v1.getElement().getY() == v2.getElement().getY()) return null;
		
		//create new MyEdge
		MyEdge newEdge = new MyEdge(v1, v2);
		edges.add(newEdge);

		for (int i = 0; i < vertices.size(); i++) {
			if (v1.getElement().getX() == vertices.get(i).getElement().getX() && v1.getElement().getY() == vertices.get(i).getElement().getY()) {
				((MyVertex) vertices.get(i)).addVertex(v2);
				((MyVertex) vertices.get(i)).addEdge(newEdge);
			} else if (v2.getElement().getX() == vertices.get(i).getElement().getX() && v2.getElement().getY() == vertices.get(i).getElement().getY()) {
				((MyVertex) vertices.get(i)).addVertex(v1);
				((MyVertex) vertices.get(i)).addEdge(newEdge);
			}
		}
		
		return newEdge;
	}

	@Override
	public Edge addEdge(Edge e) {
		//create new MyEdge
		MyEdge newEdge = (MyEdge) e;
		edges.add(newEdge);		
		return newEdge;
	}
	
	@Override
	public boolean removeEdge(Vertex v1, Vertex v2) {
		//find edge and remove
		boolean found = false;
		for (int i = 0; i < edges.size(); i++) {
			//if edge contains both points, remove it
			if (((MyEdge) edges.get(i)).contains(v1, v2)) {
				edges.remove(i);
				found = true;
			}
		}
		
		for (int i = 0; i < vertices.size(); i++) {
			((MyVertex) vertices.get(i)).removeEdge(v1,  v2);
		}
		
		return found;
	}

	@Override
	public boolean removeEdge(Edge e) {
		//use other removeEdge method. Send edge vertices as parameters.
		return removeEdge(e.vertices().get(0), e.vertices().get(1));
	}
	
	@Override
	public Edge findEdge(Vertex v1, Vertex v2) {
		//find edge
		for (int i = 0; i < edges.size(); i++) {
			//if edge contains both vertices, return it
			if (((MyEdge) edges.get(i)).contains(v1, v2)) {
				return edges.get(i);
			}
		}
		//edge not found
		return null;
	}

	@Override
	public boolean areConnected(Vertex v1, Vertex v2) {
		//use findEdge to see if two points are connected
		return (findEdge(v1, v2) != null);
	}

	@Override
	public ArrayList<Vertex> adjacentVertices(Vertex v1) {
		return ((MyVertex) v1).adjacentVertices();
	}

	@Override
	public ArrayList<Edge> incidentEdges(Vertex v1) {
		return ((MyVertex) v1).incidentEdges();
	}


	
	public String toString() {
		return "<Graph:" + vertices + ", " + edges + ">";
	}

	
	
	
	//NEW STUFF FOR MAZE
	
	@Override
	public Vertex addVertex(Pair p) {
		//create new vertex with pair p
		MyVertex v = new MyVertex();
		v.setElement(p);
		//add vertex
		return addVertex(v);
	}

	@Override
	public boolean removeVertex(Pair p) {
		//find vertex with pair p and remove it
		return removeVertex(findVertex(p));
	}

	@Override
	public Vertex findVertex(Pair p) {
		//find vertex with same x and y as p
		for (int i = 0; i < vertices.size(); i++) {
			if (vertices.get(i).getElement().getX() == p.getX() && vertices.get(i).getElement().getY() == p.getY())
				return vertices.get(i);
		}
		return null;
	}

	@Override
	public ArrayList<Vertex> shortestPath(Vertex v1, Vertex v2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Graph minimumSpanningTree() {
		// TODO Auto-generated method stub
		return null;
	}

}
