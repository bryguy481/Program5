import java.util.ArrayList;


public interface Vertex {

	// First vertex created is 0, second is 1, etc.
	// Accessors for the element of the vertex
	public Pair getElement( );
	public void setElement( Pair e );

	// List edges connected to the vertex
	public ArrayList< Edge > incidentEdges( );
	// List vertices connected to this vertex by a single edge
	public ArrayList< Vertex > adjacentVertices( );
}