package lab2;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import lab2.Piece.Type;
import lab2.Exceptions.InvalidMoveException;

public class Board {
    public int size = 5;

    // 2D Array of Cells for representation of the game board
    public final Cell[][] board = new Cell[size][size];

    private Piece.Type turn;
    private Piece.Type winner;

    /**
     * Create a Board with the current player turn set.
     */
    public Board() {
        this.loadBoard("Boards/Starter.txt");
    }

    /**
     * Create a Board with the current player turn set and a specified board.
     * @param boardFilePath The path to the board file to import (e.g. "Boards/Starter.txt")
     */
    public Board(String boardFilePath) {
        this.loadBoard(boardFilePath);
    }

    /**
     * Creates a Board copy of the given board.
     * @param board Board to copy
     */
    public Board(Board board) {
        this.size = board.size;
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                this.board[row][col] = new Cell(board.board[row][col]);
            }
        }
        this.turn = board.turn;
        this.winner = board.winner;
    }

    /**
     * @return the Piece.Type (Muskeeteer or Guard) of the current turn
     */
    public Piece.Type getTurn() {
        return turn;
    }

    /**
     * Get the cell located on the board at the given coordinate.
     * @param coordinate Coordinate to find the cell
     * @return Cell that is located at the given coordinate
     */
    public Cell getCell(Coordinate coordinate) { // TODO
    	return board[coordinate.row][coordinate.col];
    }

    /**
     * @return the game winner Piece.Type (Muskeeteer or Guard) if there is one otherwise null
     */
    public Piece.Type getWinner() {
        return winner;
    }

    /**
     * Gets all the Musketeer cells on the board.
     * @return List of cells
     */
    public List<Cell> getMusketeerCells() { // TODO
    	List<Cell> musketerCells = new ArrayList<Cell>();
    	for(int i = 0; i < board.length; i++) {
    		for(int j = 0; j < board[i].length; j++) {
    			Cell cell = board[i][j];
    			if(cell.getPiece().getType() == Piece.Type.MUSKETEER) 
    				musketerCells.add(cell);
    		}
    	}
        return musketerCells;
    }

    /**
     * Gets all the Guard cells on the board.
     * @return List of cells
     */
    public List<Cell> getGuardCells() { // TODO
    	List<Cell> guardCells = new ArrayList<Cell>();
    	for(int i = 0; i < board.length; i++) {
    		for(int j = 0; j < board[i].length; j++) {
    			Cell cell = board[i][j];
    			if(cell.getPiece().getType() == Piece.Type.GUARD) 
    				guardCells.add(cell);
    		}
    	}
        return guardCells;
    }

    /**
     * Executes the given move on the board and changes turns at the end of the method.
     * @param move a valid move
     */
    public void move(Move move) throws InvalidMoveException { // TODO
    	
    	if(!isValidMove(move))
    		throw new InvalidMoveException("MOVE is invalid");
    	
    	Cell dest, src;
    	// delete src(source)
    	src = move.fromCell;
    	dest = move.toCell;
    	
    	// find where cell
    	for(int i = 0; i < board.length; i++) {
    		
    		// check if src or dest has been reahed
    		if(i == src.getCoordinate().row || i == src.getCoordinate().row) {
    			for(int j = 0; j < board[i].length; j++) {
        			
    				    				
    				// src found, set src to empty -> to indicate piece has been moved
    				if(j == src.getCoordinate().col) 
    					board[i][j].removePiece();
    				
    				// dest found, set dest to piece
    				if(j == dest.getCoordinate().col) 
    					board[i][j] = dest;
        		}
    		}
    	}
    	
    	// reset turn
    	turn = Piece.Type.MUSKETEER.getType() == turn.getType() ? Piece.Type.GUARD : Piece.Type.MUSKETEER;
    }

    /**
     * Undo the move given.
     * @param move Copy of a move that was done and needs to be undone. The move copy has the correct piece info in the
     *             from and to cell fields. Changes turns at the end of the method.
     * @throws InvalidMoveException 
     */
    public void undoMove(Move move) throws InvalidMoveException { // TODO
    	
    	if(!isValidMove(move))
    		throw new InvalidMoveException("MOVE is invalid");
    	
    	Cell dest, src;
    	// delete src(source)
    	src = move.fromCell;
    	dest = move.toCell;
    	
    	// find where cell
    	for(int i = 0; i < board.length; i++) {
    		
    		// check if src or dest has been reahed
    		if(i == src.getCoordinate().row || i == src.getCoordinate().row) {
    			for(int j = 0; j < board[i].length; j++) {
        			
    				// src found, set to cell
    				if(j == src.getCoordinate().col) 
    					board[i][j] = dest;
    				
    				// dest found so set to numm
    				if(j == dest.getCoordinate().col) 
    					board[i][j].removePiece();
        		}
    		}
    	}
    	
    	// inverse reset turn
    	turn = Piece.Type.MUSKETEER.getType() == turn.getType() ? Piece.Type.GUARD : Piece.Type.MUSKETEER;
    }

    /**
     * Checks if the given move is valid. Things to check:
     * (1) the toCell is next to the fromCell
     * (2) the fromCell piece can move onto the toCell piece.
     * @param move a move
     * @return     True, if the move is valid, false otherwise
     */
    public Boolean isValidMove(Move move) { // TODO
    	
    	// edge cases
    	if(move.fromCell.getCoordinate() == null || move.toCell.getCoordinate() == null)
    		return false;
    	
    	if(board[move.fromCell.getCoordinate().row][move.fromCell.getCoordinate().col] == null)
    		return false;
    	
    	if(move.fromCell.getCoordinate().row > board.length || move.fromCell.getCoordinate().row < 0)
    		return false;
    	
    	if(move.toCell.getCoordinate().row > board.length || move.toCell.getCoordinate().row < 0)
    		return false;
    	
    	
    	
    	boolean case1 = false, case2 = false;
    	
    	/**
    	 * Checking for case(1)
    	 */
    	
    	// The toCell must be next to the fromCell; dest and src respectively
    	// row must differ by 1 and must be same column or vice versa
    	int srcRow, destRow, srcCol, destCol;
    	srcRow = move.fromCell.getCoordinate().row;
    	destRow = move.toCell.getCoordinate().row;
    	srcCol = move.fromCell.getCoordinate().col;
    	destCol = move.toCell.getCoordinate().col;
    	
    	if(differBy1(srcRow, destRow) && srcCol == destCol) 
    		case1 = true;
    	else if(differBy1(srcCol, destCol) && srcRow == destRow)
    		case1 = true;
    	
    	
    	/**
    	 * Checking for case(2)
    	 *  the fromCell piece can move onto the toCell piece.
    	 */
    	
    	// check if piece that is being moved is a Musk
    	if(board[srcRow][srcCol].getPiece().getType() == Piece.Type.MUSKETEER) {

    		if(board[destRow][destCol].getPiece().getType() == Piece.Type.GUARD)
    			case2 = true;
    		else
    			case2 = false;
    	} else {
    		if(board[destRow][destCol].getPiece().getType() == Piece.Type.MUSKETEER)
    			case2 = true;
    		else
    			case2 = false;
    	}
    	
        return case1 && case2;
    }
    
    // HELPER METHOD
    private boolean differBy1(int a, int b) {    	
    	int greater = a >= b ? a : b; 
    	int lesser = a < b ? a : b;
    	return greater - lesser == 1 ? true : false;
    }

    /**
     * Get all the possible cells that have pieces that can be moved this turn.
     * @return Cells that can be moved from the given cells
     */
    public List<Cell> getPossibleCells() {
    	
    	/*
    	 * For each piece specific to this turn, check if;
    	 * getThePossible moves has size greater than zero and populate list
    	 */
    	List<Cell> out = new ArrayList<Cell>();
    	
    	// call getPossible moves and store all the cells that can be moved
    	List<Move> moves = getPossibleMoves();
    	
    	for(int i = 0; i < moves.size(); i++) 
    		out.add(moves.get(i).fromCell);
    	
    	
    	return out;
    }

    /**
     * Get all the possible cell destinations that is possible to move to from the fromCell.
     * @param fromCell The cell that has the piece that is going to be moved
     * @return List of cells that are possible to get to
     */
    public List<Cell> getPossibleDestinations(Cell fromCell) { // TODO
    	
    	List<Cell> out = new ArrayList<Cell>();
    	int row, col;
    	
    	/*
    	 * Check if it is possible to have a destination at the top
    	 */
    	if(fromCell.getCoordinate().row != 0) {
        	row = fromCell.getCoordinate().row - 1;
        	col = fromCell.getCoordinate().col;
        	Cell toCell = new Cell(new Coordinate(row, col));
        	if(isValidMove(new Move(fromCell, toCell)));
        		out.add(toCell);
    	}
    	
    	/*
    	 * Check if it is possible to have destination at the bottom
    	 */
    	if(fromCell.getCoordinate().row != (board.length-1)) {
    		row = fromCell.getCoordinate().row + 1;
    		col = fromCell.getCoordinate().col;
    		Cell toCell = new Cell(new Coordinate(row, col));
        	if(isValidMove(new Move(fromCell, toCell)));
        		out.add(toCell);
    	}
    	
    	/*
    	 * If moveLeft is possible
    	 */
    	if(fromCell.getCoordinate().col != 0) {
    		row = fromCell.getCoordinate().row;
    		col = fromCell.getCoordinate().col - 1;
    		Cell toCell = new Cell(new Coordinate(row, col));
        	if(isValidMove(new Move(fromCell, toCell)));
        		out.add(toCell);
    	}
    	
    	/*
    	 * If moveRight is possible
    	 */
    	if(fromCell.getCoordinate().col != board[0].length-1) {
    		row = fromCell.getCoordinate().row;
    		col = fromCell.getCoordinate().col + 1;
    		Cell toCell = new Cell(new Coordinate(row, col));
        	if(isValidMove(new Move(fromCell, toCell)));
        		out.add(toCell);
    	}
    
        return out;
    }

    /**
     * Get all the possible moves that can be made this turn.
     * @return List of moves that can be made this turn
     */
    public List<Move> getPossibleMoves() { // TODO
    	// get all pieces of the type of this turn 
    	// for all the pieces you get, get all the possible destinations
    	// specify the src position of this piece and their respective destinations
    	List<Move> out = new ArrayList<Move>();

    	for(int i = 0; i < board.length; i++) {
    		for(int j = 0; j < board[i].length; j++) {
    			// check if its right type
    			Cell curr = board[i][j];
    			
    			// moves must be specific to the type of turn
    			if(curr.getPiece().getType() == turn) {
    				// a move has one source and one destination. so for each destination populate move list
    				List<Cell> destinations = getPossibleDestinations(curr);
    				for(int k = 0; k < destinations.size(); k++) {
    					out.add(new Move(curr, destinations.get(k)));
    				}
    			}
    		}
    	}
        return out;
    }

    /**
     * Checks if the game is over and sets the winner if there is one.
     * @return True, if the game is over, false otherwise.
     */
    public boolean isGameOver() { // TODO
    	
    	
    	// regardless of the turn check if musks are on same column or row
    	int row = Integer.MIN_VALUE, col = Integer.MIN_VALUE, rowCount = 0, colCount = 0;
    	for(int i = 0; i < board.length; i++) {
    		for(int j = 0; j < board[i].length; j++) {
    			
    			// make sure its musk
    			if(turn == Piece.Type.MUSKETEER) {
    				
    				Cell curr = board[i][j];
    				
        			// check if same row
        			if(row == Integer.MIN_VALUE || row == curr.getCoordinate().row) {
        				row = curr.getCoordinate().row;
        				rowCount++;
        			}
        			
        			// check if same column
        			if(col == Integer.MIN_VALUE || col == curr.getCoordinate().col) {
        				col = curr.getCoordinate().col;
        				colCount++;
        			}
    			}    			
    		}
    	}
    	int noOfMusks = countMusks();
    	if(noOfMusks == rowCount || noOfMusks == colCount) {
    		winner = Piece.Type.GUARD; // guards only win if musks are aligned
    		return true;
    	}
    	
    	// if turn is musk, if they cant move, then they win
    	
    	// if there are no guards to move, and musks are not on same col or row, musks win
    	List<Move> moves = getPossibleMoves(); 
    	if(moves.size() < 1) {
    		winner = Piece.Type.MUSKETEER;
    		return true; // GAME OVER
    	}
    	
    	return false;
    }
    
    
	// HELPER METHODS
	/**
	 * Get number of MUSKS
	 */
	private int countMusks() {
		int count = 0;
    	for(int i = 0; i < board.length; i++) {
    		for(int j = 0; j < board[i].length; j++) {
    			count++;    			
    		}
    	}
		return count;
	}

    /**
     * Saves the current board state to the boards directory
     */
    public void saveBoard() {
        String filePath = String.format("boards/%s.txt",
                new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()));
        File file = new File(filePath);

        try {
            file.createNewFile();
            Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
            writer.write(turn.getType() + "\n");
            for (Cell[] row: board) {
                StringBuilder line = new StringBuilder();
                for (Cell cell: row) {
                    if (cell.getPiece() != null) {
                        line.append(cell.getPiece().getSymbol());
                    } else {
                        line.append("_");
                    }
                    line.append(" ");
                }
                writer.write(line.toString().strip() + "\n");
            }
            writer.close();
            System.out.printf("Saved board to %s.\n", filePath);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.printf("Failed to save board to %s.\n", filePath);
        }
    }

    @Override
    public String toString() {
        StringBuilder boardStr = new StringBuilder("  | A B C D E\n");
        boardStr.append("--+----------\n");
        for (int i = 0; i < size; i++) {
            boardStr.append(i + 1).append(" | ");
            for (int j = 0; j < size; j++) {
                Cell cell = board[i][j];
                boardStr.append(cell).append(" ");
            }
            boardStr.append("\n");
        }
        return boardStr.toString();
    }

    /**
     * Loads a board file from a file path.
     * @param filePath The path to the board file to load (e.g. "Boards/Starter.txt")
     */
    private void loadBoard(String filePath) {
        File file = new File(filePath);
        Scanner scanner = null;
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            System.err.printf("File at %s not found.", filePath);
            System.exit(1);
        }

        turn = Piece.Type.valueOf(scanner.nextLine().toUpperCase());

        int row = 0, col = 0;
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] pieces = line.trim().split(" ");
            for (String piece: pieces) {
                Cell cell = new Cell(new Coordinate(row, col));
                switch (piece) {
                    case "O" -> cell.setPiece(new Guard());
                    case "X" -> cell.setPiece(new Musketeer());
                    default -> cell.setPiece(null);
                }
                this.board[row][col] = cell;
                col += 1;
            }
            col = 0;
            row += 1;
        }
        scanner.close();
        System.out.printf("Loaded board from %s.\n", filePath);
    }
}
