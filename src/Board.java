import java.awt.*;
import java.util.*;

public class Board {
    Grid grid;
    ArrayList<Cell> cellOverlay;
    ArrayList<Cell> offensiveOverlay;
    ArrayList<Piece> pieces;
    Optional<Piece> pieceInAction;
    ArrayList<Cell> occupied;

    GameState state = new PlayerOneTurn();

    public Board (Point p) {
        grid = new Grid (p);
        cellOverlay = new ArrayList<Cell>();
        offensiveOverlay = new ArrayList<Cell>();
        pieces = new ArrayList<Piece> ();
        state = new PlayerOneTurn();

        setBoardPieces(); // add and display pieces to the chess board
        occupied = setOccupiedCells();
    }

    public void drawBoard (Graphics g, Point mousePos) {
    	state.paint(g, this);
    	
        grid.paint (g, mousePos);
        for (Piece p : pieces) {
            p.paint(g);
        }

        grid.paintOverlay(g, cellOverlay, new Color(0f, 1f, 0.5f, 0.5f));
        grid.paintOverlay(g, offensiveOverlay, new Color(1f, 0f, 0f, 0.5f));
    }
    
    public void mouseClicked(int x, int y) {
    	state.mouseClick(x, y, this);
    }

    public void removeEnemyPiece(Cell clickedLoc) {
    	for (Piece p : pieces) {
        
            // find enemy piece occupying clicked cell & remove it from the board
            if (p.loc.equals(clickedLoc)) {
            	System.out.println(pieceInAction.get() + " takes out " + p + "!");
            	
                pieces.remove(p);
                break;
            }
        }
	}

    public void setPieceMovesOverlay(Piece piece) {
        cellOverlay = piece.moves; 
        offensiveOverlay = piece.offensiveMoves;
	}
	
	public void clearCellOverlay() {
        cellOverlay.clear(); 
        offensiveOverlay.clear();
	}

	// set pieceInAction to the clicked Piece (if it exists)
	public void setPieceInAction(int x, int y, Color c) {
        for (Piece p : pieces) {
            if (((p.loc).contains(x, y)) && (p.teamColour == c)) {
            	// store selected piece (if present)
                pieceInAction = Optional.of(p); 
                break; // stop loop since pieceInAction is set
            }
        }
	}
    
    public void clearPieceMoves() {
        pieceInAction.get().moves = new ArrayList<>();
        pieceInAction.get().offensiveMoves = new ArrayList<>();
    }

	// add a Piece to the global list 'pieces'
    public void addPiece (Piece p) {
        pieces.add(p);
    }

	// initialise and set all Pieces on the chessboard
    private void setBoardPieces () {
    	// PAWN
        for (int i = 0; i < 8; i++) {
            Pawn whitePawn = new Pawn(this.grid.cells[i][1], Color.WHITE);
            this.addPiece(whitePawn);
        }
        for (int i = 0; i < 8; i++) {
            Pawn blackPawn = new Pawn(this.grid.cells[i][6], Color.GRAY);
            this.addPiece(blackPawn);
        }

        // KING
        King whiteKing = new King(this.grid.cells[4][0], Color.WHITE);
        this.addPiece(whiteKing);
        King blackKing = new King(this.grid.cells[3][7], Color.GRAY);
        this.addPiece(blackKing);

        // QUEEN
        Queen whiteQueen = new Queen(this.grid.cells[3][0], Color.WHITE);
        this.addPiece(whiteQueen);
        Queen blackQueen = new Queen(this.grid.cells[4][7], Color.GRAY);
        this.addPiece(blackQueen);

        // BISHOP
        Bishop whiteBishop1 = new Bishop(this.grid.cells[1][0], Color.WHITE);
        Bishop whiteBishop2 = new Bishop(this.grid.cells[6][0], Color.WHITE);
        this.addPiece(whiteBishop1);
        this.addPiece(whiteBishop2);
        Bishop blackBishop1 = new Bishop(this.grid.cells[1][7], Color.GRAY);
        Bishop blackBishop2 = new Bishop(this.grid.cells[6][7], Color.GRAY);
        this.addPiece(blackBishop1);
        this.addPiece(blackBishop2);

        // KNIGHT
        Knight whiteKnight1 = new Knight(this.grid.cells[2][0], Color.WHITE);
        Knight whiteKnight2 = new Knight(this.grid.cells[5][0], Color.WHITE);
        this.addPiece(whiteKnight1);
        this.addPiece(whiteKnight2);
        Knight blackKnight1 = new Knight(this.grid.cells[2][7], Color.GRAY);
        Knight blackKnight2 = new Knight(this.grid.cells[5][7], Color.GRAY);
        this.addPiece(blackKnight1);
        this.addPiece(blackKnight2);

        // ROOK
        Rook whiteRook1 = new Rook(this.grid.cells[0][0], Color.WHITE);
        Rook whiteRook2 = new Rook(this.grid.cells[7][0], Color.WHITE);
        this.addPiece(whiteRook1);
        this.addPiece(whiteRook2);
        Rook blackRook1 = new Rook(this.grid.cells[0][7], Color.GRAY);
        Rook blackRook2 = new Rook(this.grid.cells[7][7], Color.GRAY);
        this.addPiece(blackRook1);
        this.addPiece(blackRook2);
    }

    // return true if a given Cell is occupied by a Piece
    // otherwise return false
    public Boolean cellIsOccupied(Cell c) {
        for (Piece pc : pieces) {
            if ((pc.loc).equals(c)) {
                return true;
            }
        }
        return false;
    }
    
    // return true if a given Cell is along the 
    public Boolean isAlongTopBottom(Cell c) {
    	ArrayList<Cell> topBottomCells = new ArrayList<>() {
    		{
    			add(grid.cells[0][0]);
    			add(grid.cells[1][0]);
    			add(grid.cells[2][0]);
    			add(grid.cells[3][0]);
    			add(grid.cells[4][0]);
    			add(grid.cells[5][0]);
    			add(grid.cells[6][0]);
    			add(grid.cells[7][0]);

    			add(grid.cells[0][7]);
    			add(grid.cells[1][7]);
    			add(grid.cells[2][7]);
    			add(grid.cells[3][7]);
    			add(grid.cells[4][7]);
    			add(grid.cells[5][7]);
    			add(grid.cells[6][7]);
    			add(grid.cells[7][7]);
    		}
    	};
    	
    	if (topBottomCells.contains(c)) {
    		return true;
    	}
    	return false;
    }

    // return a list of Cells that are occupied by a Piece
    public ArrayList<Cell> setOccupiedCells() {
        ArrayList<Cell> occupiedCells = new ArrayList<>();
        
        for (int i = 0; i < grid.cells.length; i++) {
            for (int j = 0; j < grid.cells[i].length; j++) {
                if (cellIsOccupied(grid.cells[i][j])) {
                    occupiedCells.add(grid.cells[i][j]);
                }
            }
        }
        return occupiedCells;
    }
    
	public ArrayList<Cell> getEnemyMovesList(Color teamColour) {
		ArrayList<Cell> enemyMovesList = new ArrayList<>();
		
		for (Piece p : pieces) {
			// for all enemy pieces(opposite teamColour)
			if (!(p.teamColour).equals(teamColour)) {
				p.setMoves(occupied, pieces);
				p.offensiveMoves.clear();
								
				for (Cell c : p.moves) {
					if (!enemyMovesList.contains(c)) {
						enemyMovesList.add(c);
					}
				}
				p.moves.clear();
			}
		}
		
		return enemyMovesList;
	}

//    public Boolean gameOver() {
//        int whiteCount = 0;
//        int blackCount = 0;
//
//        for (Piece p : pieces) {
//            if (p.teamColour == Color.WHITE) {
//                whiteCount++;
//            } else {
//                blackCount++;
//            }
//        }
//
//        if (whiteCount == 0 || blackCount == 0) {
//            return true;
//        }
//        return false;
//    }
}
