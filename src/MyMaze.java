import java.awt.BasicStroke;
import java.awt.Color;
import java.util.ArrayList;

/**
 * @author Joshua Johnson
 * @author Brian Keith
 * @version 1.0
 * Class: CS 2321
 * Assignment: Program 5 - MyMaze
 * Description: The maze class generates a maze with designated rows and columns
 * 				and is used in the Main.java class when drawing the GUI
 */
public class MyMaze implements Maze {

	//The graph representation of our maze
	//TODO
	//I'd though it would be easiest to just work with these
	//Instead of generating them on the toGraph and toArray methods
	//Let me know what you think
	private MyGraph graphMaze;
	private MyVertex [ ][ ] graphArray;

	private MyVertex start, finish;

	@Override
	public void generateMaze(int rows, int columns) {
		// TODO Auto-generated method stub

		//TODO
		//Check my stuff to make sure it looks cool, if not let me know ;)

		//create the graphMaze and graphArray
		graphMaze = new MyGraph();
		graphArray = new MyVertex [ rows ][ columns ]; 

		//add vertices into graph
		addVertices( rows , columns );

		//set start and finish randomly, not using util.random
		int min = 0;
		int max = rows*columns;
		int startNum = min + (int)(Math.random() * ((max - min) + 1));
		int finishNum = min + (int)(Math.random() * ((max - min) + 1));
		//if they are the same randomize until different
		while( finishNum == startNum ) {
			finishNum = min + (int)(Math.random() * ((max - min) + 1));
		}
		start = (MyVertex) graphMaze.vertices().get( startNum );
		finish = (MyVertex) graphMaze.vertices().get( finishNum );

		//generate paths in the maze
		depthFirstGeneration();

		//TODO
		//add the connected vertices into the graphArray
		for ( int i = 0; i < graphMaze.vertices().size(); i++ ) {
			MyVertex temp = (MyVertex) graphMaze.vertices().get( i );
			graphArray [ temp.getX() ] [ temp.getY() ] = temp;
		}


		//calculate number of connect vertices
		int connections = 0;
		for( int r = 0; r < rows; r++ ) {
			for( int c = 0; c < columns; c++ ) {
				if ( (c == 0) || ( !graphMaze.areConnected( graphArray[r][c], graphArray[r][c-1] ) ) ) {

				}
				if ( (c == columns - 1) || ( !graphMaze.areConnected( graphArray[r][c], graphArray[r][c + 1] ) ) ) {

				}
				if ( (r == 0) || ( !graphMaze.areConnected( graphArray[r][c], graphArray[r-1][c] ) ) ) {

				}
				if ( (r == rows - 1) || ( !graphMaze.areConnected( graphArray[r][c], graphArray[r + 1][c] ) ) ) {

				}
				else{
					connections++;
				}
			}
		}


	}

	/**
	 * Generates the paths in the maze using a depth-first approach
	 */
	private void depthFirstGeneration() {

		ArrayList< MyVertex > locations = new ArrayList< MyVertex >();
		int numOfVertices = graphMaze.vertices().size();
		MyVertex currentLocation = start;
		int visitedLocations = 1;
		while ( visitedLocations < numOfVertices ) {
			//find all vertices next to currentCell with no adjacent vertices
			ArrayList< MyVertex > unTouchedLocations = new ArrayList< MyVertex >();
			int curX = currentLocation.getX();
			int curY = currentLocation.getY();
			//TODO
			//this might be able to be rewritten for better time complexity
			for ( int tempV = 0; tempV < graphMaze.vertices().size(); tempV++ ) {
				//so we can access the x and y
				MyVertex temp = (MyVertex) graphMaze.vertices().get( tempV );
				//If it is next to our current location
				if ( ((temp.getX() - 1 == curX || temp.getX() + 1 == curX) && temp.getY() == curY) ||  ((temp.getY() - 1 == curY || temp.getY() + 1 == curY) && temp.getX() == curX)) {
					//if it has nothing adjacent to it
					if ( temp.adjacentVertices().size() == 0 ) {
						unTouchedLocations.add( temp );
					}
				}
			}
			//if we have locations next to us with no connections
			if ( unTouchedLocations.size() > 0 ) {
				//choose one at random
				int randVer =  ( int )( Math.random() *   unTouchedLocations.size() );
				MyVertex nextVer = unTouchedLocations.get( randVer );

				//connect them and move to the new location
				graphMaze.addEdge( currentLocation, nextVer );
				locations.add( currentLocation );

				currentLocation = nextVer;
				visitedLocations++;
			}
			//no untouched locations next to the current location
			else {
				//making the ArrayList act like a stack
				currentLocation = locations.get( locations.size() - 1 );
				locations.remove( locations.size() - 1 );
			}
		}
	}

	/**
	 * This is a helper method that inserts all the vertices into the graphMaze and the grapArray
	 * @param rows
	 * @param columns
	 */
	private void addVertices(int rows, int columns) {
		// TODO Auto-generated method stub
		for ( int row = 0; row < rows ; row++ ) {
			for ( int col = 0; col < columns ; col++ ) {
				//create the vertex
				MyVertex temp = new MyVertex();
				temp.setY( col );
				temp.setX( row );

				//TODO
				//Not to sure on this, let me know
				graphMaze.addVertex( temp );
			}
		}
	}


	private ArrayList<Vertex> solution;
	private boolean[][] visited;
	@Override
	public ArrayList<Vertex> solveMaze() {
		//reset solution and visited
		solution = new ArrayList<Vertex>();
		visited = new boolean[graphArray.length][graphArray[0].length];
	
		//if result found, return the solution
		if (solveHelper(start))
			return solution;
		else
			return new ArrayList<Vertex>();
	}
	
	//Recursive helper method to find the solution
	private boolean solveHelper(Vertex vertex) {
		MyVertex v = (MyVertex) vertex;
		//this vertex is now visited
		visited[v.getX()][v.getY()] = true;
		
		//finish found
		if (v.getX() == finish.getX() && v.getY() == finish.getY()) return true;
		
		for (int i = 0; i < v.adjacentVertices().size(); i++) {
			MyVertex next = (MyVertex) v.adjacentVertices().get(i);
			//if the next vertex hasn't been visited
			if (!visited[next.getX()][next.getY()]) {
				if (solveHelper(next)) {
					//add if finish found ahead
					solution.add(v);
					return true;
				}
			}
		}
		
		return false;
	}

	/**
	 * @return Returns a graph representation of the maze
	 */
	@Override
	public Graph toGraph() {
		return (Graph) graphMaze;
	}

	@Override
	public Vertex[][] toArray() {
		// TODO Auto-generated method stub
		return (Vertex[][]) graphArray;
	}

	@Override
	public Vertex startVertex() {
		// TODO Auto-generated method stub
		return (Vertex) start;
	}

	@Override
	public Vertex finishVertex() {
		// TODO Auto-generated method stub
		return (Vertex) finish;
	}

}
