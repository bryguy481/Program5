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
	private MyGraph graphMaze;
	//The array representation of the graph
	private MyVertex [ ][ ] graphArray;
	//The start and finish vertices of the maze
	private MyVertex start, finish;

	//The number of rows and columns in the maze
	int rows, columns;

	/**
	 * Generates a Maze with the passed in number of rows and columns
	 * @param rows Number of rows in the maze
	 * @param columns Number of columns in the maze
	 */
	@Override
	public void generateMaze(int rows, int columns) {

		//create the graphMaze and graphArray
		graphMaze = new MyGraph();

		if ( rows == 0 || columns == 0 ) {
			graphArray = new MyVertex [ 0 ] [ 0 ];
		} 
		else {
			graphArray = new MyVertex [ rows ] [ columns ]; 
		}
		
		this.rows = rows;
		this.columns = columns;

		//add vertices into graph
		addVertices();

		//set start and finish randomly, not using util.random
		setStartAndFinish();

		//generate paths in the maze
		depthFirstGeneration();

		//add the connected vertices into the graphArray
		for ( int i = 0; i < graphMaze.vertices().size(); i++ ) {
			MyVertex temp = ( MyVertex ) graphMaze.vertices().get( i );
			graphArray [ temp.getX() ] [ temp.getY() ] = temp;
		}
	}
	
	
	/**
	 * Sets the start and finish vertices for the maze.
	 * If the maze has 0 rows or columns they are not set and are null
	 * @param rows
	 * @param columns
	 */
	private void setStartAndFinish( ) {

		//If there are 0 rows or 0 columns stop
		if ( rows == 0 || columns == 0 ) {
			return;
		}
		
		//They can be null when its 1 by 1
		if ( rows <= 1 && columns <= 1 ) {
			start = null;
			finish = null;
			return;
		}

		int min = 0;
		int max = rows*columns;
		int startNum = min + (int)(Math.random() * ((max - min) ));
		int finishNum = min + (int)(Math.random() * ((max - min) ));
	
		//if they are the same randomize until different
		while( finishNum == startNum ) {
			finishNum = min + (int)(Math.random() * ((max - min) ));
		}

		start = ( MyVertex ) graphMaze.vertices().get( startNum );
		finish = ( MyVertex ) graphMaze.vertices().get( finishNum );


	}

	/**
	 * Generates the paths in the maze using a depth-first approach.
	 * Will only generate if rows and columns are above 0
	 * @param rows The number of rows in the maze.
	 * @param columns The number of columns in the maze.
	 */
	private void depthFirstGeneration(  ) {
		System.out.println("Making Maze.");

		//If there are 0 rows or 0 columns stop
		if ( rows == 0 || columns == 0 ) {
			return;
		}

		//To keep track of where you have been, this is used as a stack
		ArrayList< MyVertex > locations = new ArrayList< MyVertex >();
		int numOfVertices = graphMaze.vertices().size();

		int min = 0;
		int max = rows*columns;
		//Randomly pick a starting vertex to generate from
		MyVertex currentLocation = ( MyVertex ) ( graphMaze.vertices().get( min + ( int )( Math.random() * ( ( max - min ) ) ) ) );
		int visitedLocations = 1;
		//Add edges until every vertex has one edge
		while ( visitedLocations < numOfVertices ) {
			//find all vertices next to currentCell with no adjacent vertices
			ArrayList< MyVertex > unTouchedLocations = new ArrayList< MyVertex >();
			int curX = currentLocation.getX();
			int curY = currentLocation.getY();

			//This finds all neighbors to the current position that have not been visited and adds it to the List
			for ( int tempV = 0; tempV < graphMaze.vertices().size(); tempV++ ) {
				//so we can access the x and y
				MyVertex temp = (MyVertex) graphMaze.vertices().get( tempV );
				//If it is next to our current location
				if ( ( ( temp.getX() - 1 == curX || temp.getX() + 1 == curX ) && temp.getY() == curY ) ||  ( ( temp.getY() - 1 == curY || temp.getY() + 1 == curY ) && temp.getX() == curX ) ) {
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
				//Go back a step to look for neighbors with no connections
				currentLocation = locations.get( locations.size() - 1 );
				locations.remove( locations.size() - 1 );
			}
		}
	}

	/**
	 * This is a helper method that inserts all the vertices into the graphMaze and the grapArray
	 * Will only add vertices if rows and columns > 0
	 * @param rows The number of rows in the maze.
	 * @param columns The number of columns in the maze.
	 */
	private void addVertices( ) {
		System.out.println("Making vertices");

		//If there are 0 rows or 0 columns stop
		if ( rows == 0 || columns == 0 ) {
			return;
		}
		System.out.println("rows = " + rows + " cols = " + columns);
		//Loop through all vertices and set their x and y variables, then add them to the graph
		for ( int row = 0; row < rows ; row++ ) {
			for ( int col = 0; col < columns ; col++ ) {
				//create the vertex
				MyVertex temp = new MyVertex();
				temp.setY( col );
				temp.setX( row );
				
				graphMaze.addVertex( temp );
			}
		}
	}

	//Variables used to solve the maze
	private ArrayList< Vertex > solution;
	private boolean[ ][ ] visited;

	/**
	 * @return Returns an ArrayList of Vertices that is the 
	 *         path from the start to the finish of the maze.
	 */
	@Override
	public ArrayList< Vertex > solveMaze() {
		System.out.println("Solving maze");

		//reset solution and visited
		solution = new ArrayList< Vertex >();

		//If there are 0 rows or 0 columns stop
		if ( rows == 0 || columns == 0 ) {
			return solution;
		}

		visited = new boolean [ graphArray.length ][ graphArray[ 0 ].length ];

		//if result found, return the solution
		if  ( solveHelper( start ) )
			return solution;
		else
			return new ArrayList<Vertex>( );
	}

	//Recursive helper method to find the solution
	private boolean solveHelper( Vertex vertex ) {
		MyVertex v = ( MyVertex ) vertex;
		//this vertex is now visited
		visited[ v.getX() ][ v.getY() ] = true;
		//System.out.println("looking for the finish");
		//finish found
		if ( v.getX() == finish.getX() && v.getY() == finish.getY() ) return true;

		for ( int i = 0; i < v.adjacentVertices().size(); i++ ) {
			MyVertex next = ( MyVertex ) v.adjacentVertices().get(i);
			//if the next vertex hasn't been visited
			if ( !visited[ next.getX() ][ next.getY() ] ) {
				if ( solveHelper( next ) ) {
					//add if finish found ahead
					solution.add( v );
					return true;
				}
			}
		}

		return false;
	}

	/**
	 * @return Returns a graph representation of the maze.
	 */
	@Override
	public Graph toGraph( ) {
		return ( Graph ) graphMaze;
	}

	/**
	 * @return Returns an Array representation of the maze.
	 */
	@Override
	public Vertex[][] toArray( ) {
		return ( Vertex[ ][ ] ) graphArray;
	}

	/**
	 * @return Returns the start vertex of the maze.
	 */
	@Override
	public Vertex startVertex( ) {
		return ( Vertex ) start;
	}

	/**
	 * @return Returns the finish vertex of the maze.
	 */
	@Override
	public Vertex finishVertex( ) {
		return ( Vertex ) finish;
	}

	/**
	 * Returns a string representation of the maze
	 */
	@Override
	public String toString( ) {
		String result = "";
		//If there are 0 rows or 0 columns stop
		if ( rows != 0 && columns != 0 ) {
			return result;
		}

		return "";
	}

}
