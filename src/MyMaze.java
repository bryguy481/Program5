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
	private Vertex [ ][ ] graphArray;
	
	private Vertex start, finish;
	
	@Override
	public void generateMaze(int rows, int columns) {
		// TODO Auto-generated method stub
		
		//TODO
		//Check my stuff to make sure it looks cool, if not let me know ;)
		
		//create the graphMaze and graphArray
		graphMaze = new MyGraph();
		graphArray = new Vertex [ rows ][ columns ]; 
		
		//add vertices into both graphs
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
		start = graphMaze.vertices().get( startNum );
		finish = graphMaze.vertices().get( finishNum );
		
		
	}

	/**
	 * This is a helper method that inserts all the vertices into the graphMaze and the grapArray
	 * @param rows
	 * @param columns
	 */
	private void addVertices(int rows, int columns) {
		// TODO Auto-generated method stub
		for ( int row = 0; row < rows; row++ ) {
			for ( int col = 0; col < columns; col++ ) {
				//create the vertex
				MyVertex temp = new MyVertex();
				temp.setY( row );
				temp.setX( col );
				
				//add it to the graph and the array
				graphArray[ row ] [ col ] = temp;
				
				//TODO
				//Not to sure on this, let me know
				graphMaze.addVertex( temp );
			}
		}
	}

	@Override
	public ArrayList<Vertex> solveMaze() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @return Returns a graph representation of the maze
	 */
	@Override
	public Graph toGraph() {
		return graphMaze;
	}

	@Override
	public Vertex[][] toArray() {
		// TODO Auto-generated method stub
		return graphArray;
	}

	@Override
	public Vertex startVertex() {
		// TODO Auto-generated method stub
		return start;
	}

	@Override
	public Vertex finishVertex() {
		// TODO Auto-generated method stub
		return finish;
	}

}
