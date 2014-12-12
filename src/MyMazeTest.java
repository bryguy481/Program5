import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

/**
 * @author Joshua Johnson
 * @author Brian Keith
 * @version 1.0
 * Class: CS 2321
 * Assignment: Program 5 MyMaze
 * Description: This class tests all the methods in MyMaze.java for edge
 *              cases.
 *
 */
public class MyMazeTest {

	/**
	 * Tests the generateMAze( int rows, int columns ) method for edge cases
	 */
	@Test
	public void testGenerateMaze( ) {
		
		//TestOne
		//Edge Case where there are 0 rows and some number of columns
		
		MyMaze testMaze = new MyMaze();
		testMaze.generateMaze( 0, 5 );
		
		//There should be no start 
		if ( testMaze.startVertex() != null ) {
			fail( "Start vertex not null when 0 rows!" );
		}
		
		//There should be no finish
		if ( testMaze.finishVertex() != null ) {
			fail( "Finish vertex not null when 0 rows!" );
		}
		
		//There should be no solution
		if ( testMaze.solveMaze().size() > 0 ) {
			fail( "There should be no solution!" );
		}
		
		//There should be no vertices in the maze
		if ( testMaze.toGraph().vertices().size() > 0 ) {
			fail( "There should be no vertices in the graph!" );
		}
		
		//The array representation should be of size 0
		if ( testMaze.toArray().length > 0 ) {
			fail( "Array should be of size 0!" );
		}
		
		//The toString Method should return ""
		if ( !testMaze.toString().equals( "" ) ) {
			fail( "ToString should be empty!" );
		}
		
		//TestTwo
		//Edge Case where there are 0 columns and some number of rows
		
		testMaze.generateMaze( 5, 0 );
		
		//There should be no start 
		if ( testMaze.startVertex() != null ) {
			fail( "Start vertex not null when 0 rows!" );
		}
		
		//There should be no finish
		if ( testMaze.finishVertex() != null ) {
			fail( "Finish vertex not null when 0 rows!" );
		}
		
		//There should be no solution
		if ( testMaze.solveMaze().size() > 0 ) {
			fail( "There should be no solution!" );
		}
		
		//There should be no vertices in the maze
		if ( testMaze.toGraph().vertices().size() > 0 ) {
			fail( "There should be no vertices in the graph!" );
		}
		
		//The array representation should be of size 0
		if ( testMaze.toArray().length > 0 ) {
			fail( "Array should be of size 0!" );
		}
		
		//The toString Method should return ""
		if ( !testMaze.toString().equals( "" ) ) {
			fail( "ToString should be empty!" );
		}
		
		//TestThree
		//Edge Case where rows and columns are both 1
		
		testMaze.generateMaze( 1, 1 );
		
		//The start and finish should be the same
		if ( testMaze.startVertex() != testMaze.finishVertex() ) {
			fail( "Start vertex should be the same as the finish vertex!" );
		}
		
		
		//There should be no solution
		if ( testMaze.solveMaze().size() > 1 ) {
			fail( "There should be a solution!" );
		}
		
		//There should be no vertices in the maze
		if ( testMaze.toGraph().vertices().size() > 1 ) {
			fail( "There should be 1 vertex in the graph!" );
		}
		
		//The array representation should be of size 0
		if ( testMaze.toArray().length > 1 ) {
			fail( "Array should be of size 1!" );
		}
		
		//The toString Method should return ""
		if ( testMaze.toString().equals( "" ) ) {
			fail( "ToString should not be empty!" );
		}
		
		//TestFour
		//Edge Case where there are a lot of rows and columns, checks to see if there are the right amount of vertices
		testMaze.generateMaze( 80, 50 );
		
		if ( testMaze.toGraph().vertices().size() != 80 * 50 ) {
			fail( " Did not generate the correct amount of vertices!" );
		}
		
	}
	
	/**
	 * Tests the solveMaze( ) method for edge cases
	 */
	@Test
	public void testSolveMAze( ) {
		
		MyMaze testMaze = new MyMaze();
		//TestOne
		//EdgeCase where the maze is not solvable 0 rows and 0 columns
		testMaze.generateMaze( 0, 0 );
		
		if ( testMaze.solveMaze().size() > 0 ) {
			fail( "The solution should be empty due to 0 rows and 0 columns!" );
		}
		
		//TestTwo
		//EdgeCase with 0 rows and some number of columns
		testMaze.generateMaze( 0, 50 );
		
		if ( testMaze.solveMaze().size() > 0 ) {
			fail( "The solution should be empty due to 0 rows and some # of columns!" );
		}
		
		//TestThree
		//EdgeCase with some number of rows and 0 columns
		testMaze.generateMaze(50, 0);
		
		if ( testMaze.solveMaze().size() > 0 ) {
			fail( "The solution should be empty due to some # of rows and 0 columns!" );
		}
		
		//TestThree
		//EdgeCase with equal rows and columns
		testMaze.generateMaze( 20, 20 );
		
		//check to see if solution is correct
		ArrayList< Vertex > solution = testMaze.solveMaze();
		//check the start and finish are correct
		System.out.println(testMaze.startVertex() + " " + solution.get( 0 ));
		System.out.println(testMaze.finishVertex() + " " + solution.get( solution.size() - 1 ));
		
		
		if ( !testMaze.startVertex().equals( solution.get( 0 ) ) ) {
			fail( "Solution does not start at the start!" ); 
		}
		
		if ( !testMaze.finishVertex().equals( solution.get( solution.size() - 1 ) ) ) {
			fail( "Solution does not end at the finish!" ); 
		}
		//check to make sure the path is correct
		for ( int ver = 0; ver < solution.size(); ver++ ) {
			//stop once we hit the finish
			if ( solution.get( ver ).equals( testMaze.finishVertex() ) ) {
				break;
			}
			if ( !solution.get( ver ).adjacentVertices().contains(solution.get( ver + 1) ) ) {
				fail( "Solution not connected!" );
			}
		}
	}
	
	/**
	 * Tests the toGraph( ) method for edge cases
	 */
	@Test
	public void testToGraph( ) {
		
	}
	
	/**
	 * Tests the toArray( ) method for edge cases
	 */
	@Test
	public void testToArray( ) {
		
	}
	
	/**
	 * Tests the startVertex( ) method for edge cases.
	 */
	@Test
	public void testStartVertex( ) {

	}
	
	/**
	 * Tests the finishVertex( ) method for edge cases.
	 */
	@Test
	public void testFinishVertex( ) {
		
	}
}
