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
		int numColors = mygraph.colorGraph( colors );
		System.out.println( "Number of Colors = " + numColors );
		System.out.println( mygraph );
	}

}
