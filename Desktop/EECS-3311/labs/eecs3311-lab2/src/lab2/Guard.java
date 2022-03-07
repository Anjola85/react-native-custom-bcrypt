package lab2;

public class Guard extends Piece {

    public Guard() {
        super("O", Type.GUARD);
    }

    /**
     * Returns true if the Guard can move onto the given cell.
     * @param cell Cell to check if the Guard can move onto
     * @return True, if Guard can move onto given cell, false otherwise
     */
    @Override
    public boolean canMoveOnto(Cell cell) { // TODO
    	
    	// cannot move diagonally, can only move to neighboring empty cells
    	if(!cell.hasPiece())
    		return true;
    	
        return false;
    }
}
