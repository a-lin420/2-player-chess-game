import java.awt.*;
import java.util.*;

public class P2SelectingNewLocation implements GameState {

	@Override
	public void mouseClick(int x, int y, Board b) {
		Optional<Cell> p2_clickedLoc = b.grid.cellAtPoint(new Point(x, y));
        
        if (p2_clickedLoc.isPresent()) {
            if (b.cellOverlay.contains(p2_clickedLoc.get())) {
            	// if a Pawn reaches the other end of the board
            	if (b.pieceInAction.get().getClass() == Pawn.class && b.isAlongTopBottom(p2_clickedLoc.get())) {
            		System.out.println("Changing Pawn to a higher rank Piece ...");
        			// * SUBJECT TO CHANGE * change Pawn to Queen (testing)
            		Piece pawnChange = new Queen(p2_clickedLoc.get(), b.pieceInAction.get().teamColour);
            		
            		b.pieces.add(pawnChange); 
            		b.pieces.remove(b.pieces.indexOf(b.pieceInAction.get()));
            	} else {
            		b.pieceInAction.get().setLocation(p2_clickedLoc.get()); 
            	}
            	
            	b.pieceInAction.get().unmoved = false; // applications for Pawn, King, and Rook
                
                // clear cell overlays
                b.clearCellOverlay();
                b.state = new PlayerOneTurn();
            } 
            else if (b.offensiveOverlay.contains(p2_clickedLoc.get())) {
            	b.removeEnemyPiece(p2_clickedLoc.get());
            	
            	// if a Pawn reaches the other end of the board
            	if (b.pieceInAction.get().getClass() == Pawn.class && b.isAlongTopBottom(p2_clickedLoc.get())) {
            		System.out.println("Changing Pawn to a higher rank Piece ...");
        			// * SUBJECT TO CHANGE * change Pawn to Queen (testing)
            		Piece pawnChange = new Queen(p2_clickedLoc.get(), b.pieceInAction.get().teamColour);
            		
            		b.pieces.add(pawnChange);                 			
            		b.pieces.remove(b.pieces.indexOf(b.pieceInAction.get()));
            	} else {
            		b.pieceInAction.get().setLocation(p2_clickedLoc.get()); 
            	}
                // clear cell overlays
                b.clearCellOverlay();
                b.state = new PlayerOneTurn();
            } else {
            	b.pieceInAction = Optional.empty();
            	// clear cell overlays
                b.clearCellOverlay();   
            	b.state = new PlayerTwoTurn();
            }
        } 
	}
	
	@Override
	public void paint(Graphics g, Board b) {
		
	}
}
