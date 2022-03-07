package lab2;

import java.util.Scanner;

public class HumanAgent extends Agent {

    public HumanAgent(Board board) {
        super(board);
    }

    /**
     * Asks the human for a move with from and to coordinates and makes sure its valid.
     * Create a Move object with the chosen fromCell and toCell
     * @return the valid human inputed Move
     */
//    @Override
//    public Move getMove() { 
//    	
//    	int row, col;
//    	Move userMove;
//    	boolean check;
//    	
//    	/**
//    	 *  A B C D E
//    	 *  0 1 2 3 4
//    	 */
//    	do {
//        	Scanner scan = new Scanner(System.in);
//        	System.out.println("Enter source(from) row: ");
//        	row = validate(scan.nextInt()-1, scan);
//        	
//        	System.out.println("Enter source(from) column: ");
//        	
//        	col = validate(chooseCol(scan.next().charAt(0))-1, scan);
//        	
//        	Cell src = new Cell(new Coordinate(row, col)); // create source cell    	
//        	
//        	
//        	System.out.println("Enter destination(to) row which can only be a number/integer: ");
//        	row = validate(scan.nextInt()-1, scan);
//        	System.out.println("Enter destination(to) col which can only be a number/integer: ");
//        	col = validate(chooseCol(scan.next().charAt(0))-1, scan);
//        	
//        	Cell dest = new Cell(new Coordinate(row, col)); // create destination cell
//        	 
//        	scan.close(); 
//        	
//        	userMove = new Move(src, dest);
//         	
//        	check = board.isValidMove(userMove);
//        	
//        	if(!check)
//        		System.out.println("Invalid move");
//        
//    	} while (!check);
//    	
//    	return userMove;
//
//    }
    
    @Override
    public Move getMove() { 
    	
    	Move userMove;
    	boolean check;
    	
    	/**
    	 *  A B C D E
    	 *  0 1 2 3 4
    	 */
    	Scanner scan = new Scanner(System.in);

    	do {
        	
        	System.out.println("Enter source(from) e.g E1: ");
        	Cell src = toCoordinates(scan.nextLine());      	
        	
         	System.out.println("Enter destination(to) e.g. D1: ");
         	Cell dest = toCoordinates(scan.nextLine());
                 	         	
        	userMove = new Move(src, dest);
         	
        	check = board.isValidMove(userMove);
        	
        	if(!check)
        		System.out.println("Invalid move, Try again");
        
    	} while (!check);
    	
    	scan.close(); 

    	
    	return userMove;

    }
    
    // turn to coordinates
    private Cell toCoordinates(String s) {
    	int col = chooseCol(s.charAt(0));
    	int row = s.charAt(1)-'0';
    	return new Cell(new Coordinate(row, col));
    }
    

    
    private int chooseCol(char c) {
    	if(c == 'A')
    		return 0;
    	else if(c == 'B')
    		return 1;
    	else if(c == 'C')
    		return 2;
    	else if(c == 'D')
    		return 3;
    	
    	return 4;
    }
        
}
