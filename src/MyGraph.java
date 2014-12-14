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
		for (int i = 0; i < edges.size(); i++) {
			//if edge contains both points, remove it
			if (((MyEdge) edges.get(i)).contains(v1, v2)) {
				edges.remove(i);
				return true;
			}
		}
		
		for (int i = 0; i < vertices.size(); i++) {
			((MyVertex) vertices.get(i)).removeEdge(v1,  v2);
		}
		//edge not found
		return false;
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
		ArrayList<Vertex> ret = new ArrayList<Vertex>();
		//find edges
		for (int i = 0; i < edges.size(); i++) {
			//if edge contains vertex, add its adjacent point to ret
			if (((MyEdge) edges.get(i)).contains(v1)) {
				ret.add(((MyEdge) edges.get(i)).getAdjacent(v1));
			}
		}
		return ret;
	}

	@Override
	public ArrayList<Edge> incidentEdges(Vertex v1) {
		ArrayList<Edge> ret = new ArrayList<Edge>();
		//find edges
		for (int i = 0; i < edges.size(); i++) {
			//if edge contains vertex, add it to ret
			if (((MyEdge) edges.get(i)).contains(v1)) {
				ret.add(((MyEdge) edges.get(i)));
			}
		}
		return ret;
	}


	
	public String toString() {
		return "<Graph:" + vertices + ", " + edges + ">";
	}

	
	
	
	//NEW STUFF FOR MAZE
	
	@Override
	public Vertex addVertex(Pair p) {
		MyVertex v = new MyVertex();
		v.setElement(p);
		return addVertex(v);
	}

	@Override
	public boolean removeVertex(Pair p) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Vertex findVertex(Pair p) {
		// TODO Auto-generated method stub
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
