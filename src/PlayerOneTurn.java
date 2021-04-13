import java.awt.*;
import java.util.*;

public class PlayerOneTurn implements GameState {
	
	@Override
	public void mouseClick(int x, int y, Board b) {
		System.out.println("| PLAYER ONE IS MOVING |");
		
		b.occupied = b.setOccupiedCells(); // identify occupied Cells
		b.pieceInAction = Optional.empty();
		
		for (Piece p : b.pieces) {
            if (((p.loc).contains(x, y)) && (p.teamColour == Color.WHITE)) {
            	// store selected piece (if present)
                b.pieceInAction = Optional.of(p); 
                break; // stop loop since pieceInAction is set
            }
        }
		
		if (b.pieceInAction.isPresent()) {
            System.out.println(b.pieceInAction.get().getClass() + " : " + b.pieceInAction.get().loc);
            b.pieceInAction.get().setMoves(b.occupied, b.pieces);
            
            b.setPieceMovesOverlay(b.pieceInAction.get());
            b.clearPieceMoves();
            
            b.state = new P1SelectingNewLocation();
        } else {
        	System.out.println("Select a valid Piece to move.");
        }
	}
	
	@Override
	public void paint(Graphics g, Board b) {
		
	}
}
