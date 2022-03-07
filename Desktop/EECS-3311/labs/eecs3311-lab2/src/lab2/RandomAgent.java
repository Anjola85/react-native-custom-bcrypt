package lab2;


public class RandomAgent extends Agent {

    public RandomAgent(Board board) {
        super(board);
    }

    /**
     * Gets a valid random move the RandomAgent can do.
     * @return a valid Move that the RandomAgent can perform on the Board
     */
    @Override
    public Move getMove() { 
    	/**
    	 * This returns a randomly generated move
    	 * It does this by
    	 */
    	int row, col;
    	Move randomMove;
    	boolean check;
    	
    	do {
        	row = getRandomPosition();
        	col = getRandomPosition();
        	
        	Cell src = new Cell(new Coordinate(row, col)); // create source cell    	
        	
        	
        	row = getRandomPosition();
        	col = getRandomPosition();
        	
        	Cell dest = new Cell(new Coordinate(row, col)); // create destination cell
        	 
        	
        	randomMove = new Move(src, dest);
         	
        	check = board.isValidMove(randomMove);
        
    	} while (!check);
    	
    	return randomMove;
    }
    
    private int getRandomPosition() {
    	int min = 0, max = board.size;
        return (int) ((Math.random() * (max - min)) + min);
    }

}
