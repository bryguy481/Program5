import java.util.ArrayList;


public class MyVertex implements Vertex {

	private int id = 0;
	private GraphColor color;
	
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

	@Override
	public ArrayList<Edge> incidentEdges() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Vertex> adjacentVertices() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public String toString() {
		return "<v" + id + ":" + color + ">";
	}

}
