import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;


public class MyGraphTest {

	@Test
	public void givenTest() {
		ArrayList< GraphColor > colors = new ArrayList< GraphColor >( );
		colors.add( GraphColor.RED );
		colors.add( GraphColor.GREEN );
		colors.add( GraphColor.BLUE );
		MyGraph mygraph = new MyGraph( );
		MyVertex v0 = new MyVertex( );
		MyVertex v1 = new MyVertex( );
		MyVertex v2 = new MyVertex( );  
		mygraph.addVertex( v0 );
		mygraph.addVertex( v1 );
		mygraph.addVertex( v2 );
		mygraph.addEdge( v0, v1 );
		mygraph.addEdge( v1, v2 );
		mygraph.addEdge( v2, v0 );

		System.out.println("Adjacent to v0: " + v0.adjacentVertices());
		System.out.println("Adjacent to v0 (from graph): " + mygraph.adjacentVertices(v0));
		
		System.out.println("Incedent to v0: " + v0.incidentEdges());
		System.out.println("Incedent to v0 (from graph): " + mygraph.incidentEdges(v0));
		
		mygraph.removeEdge( v0, v1 );
		System.out.println("\nRemoved v0-v1");

		System.out.println("\nAdjacent to v0: " + v0.adjacentVertices());
		System.out.println("Adjacent to v0 (from graph): " + mygraph.adjacentVertices(v0));
		
		System.out.println("Incedent to v0: " + v0.incidentEdges());
		System.out.println("Incedent to v0 (from graph): " + mygraph.incidentEdges(v0));
		
		int numColors = mygraph.colorGraph( colors );
		System.out.println( "Number of Colors = " + numColors );
		System.out.println( mygraph );
	}

}
