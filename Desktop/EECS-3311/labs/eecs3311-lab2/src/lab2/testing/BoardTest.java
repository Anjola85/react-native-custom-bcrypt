package lab2.testing;

import org.junit.*;

import lab2.Board;
import lab2.Cell;
import lab2.Coordinate;

public class BoardTest {

    private Board board;

    @Before
    public void setup() {
        this.board = new Board();
    }

    @Test
    public void testGetCell() {
        Cell cell = board.getCell(new Coordinate(1, 4));
        Assert.assertNotNull(cell.getPiece());
    }
    
    
    
	/**
	 * Gets a valid move from the given agent (Human/Random), adds a copy of the move using the Move
	 * copy constructor to the moves stack for undoing later, then does the move on the board.
	 */
    
    @Test
    public void testMove1() {
    	// Cell[][] board1 = new Cell[10][10];
        Board b1 = new Board();
    }
    
//    @Test
//    public void testUndomove1() {
//    	
//    }
}
