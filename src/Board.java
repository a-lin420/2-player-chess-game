import java.awt.*;
import java.util.*;

public class Board {
    Grid grid;
    ArrayList<Cell> cellOverlay;
    ArrayList<Cell> offensiveOverlay;
    ArrayList<Piece> pieces;
    Optional<Piece> pieceInAction;
    ArrayList<Cell> occupied;

    enum State { PlayerOneMoving, P1_SelectingNewLocation, 
                 PlayerTwoMoving, P2_SelectingNewLocation }
    State currentState = State.PlayerOneMoving;

    public Board (Point p) {
        grid = new Grid (p);
        cellOverlay = new ArrayList<Cell>();
        offensiveOverlay = new ArrayList<Cell>();
        pieces = new ArrayList<Piece> ();
        currentState = State.PlayerOneMoving;

        setBoardPieces(); // add and display pieces to the chess board
        occupied = setOccupiedCells();
    }

    public void drawBoard (Graphics g, Point mousePos) {
        grid.paint (g, mousePos);
        for (Piece p : pieces) {
            p.paint(g);
        }

        grid.paintOverlay(g, cellOverlay, new Color(0f, 1f, 0.5f, 0.5f));
        grid.paintOverlay(g, offensiveOverlay, new Color(1f, 0f, 0f, 0.5f));
    }

    public void mouseClicked(int x, int y) {
        System.out.println("Mouse location: " + x + ", " + y);

        switch (currentState) {
            case PlayerOneMoving : 
                System.out.println(currentState);

                occupied = setOccupiedCells(); // identify occupied Cells
                setPieceInAction(x, y, Color.WHITE);

                if (pieceInAction.isPresent()) {
                    System.out.println(pieceInAction.get().getClass() + " : " + pieceInAction.get().loc);
                    pieceInAction.get().setMoves(occupied, pieces);
                    
                    if (pieceInAction.get().getClass() == King.class) {
                    	ArrayList<Cell> enemyMoves = getEnemyMovesList(pieceInAction.get().teamColour);
                    	
//                    	for (Cell c : (pieceInAction.get()).moves) {
//                    		if (enemyMoves.contains(c)) {
//                    			pieceInAction.get().moves.remove(c);
//                    		}
//                    	}
                    }
                    
                    setPieceMovesOverlay(pieceInAction.get());
                    clearPieceMoves();

                    currentState = State.P1_SelectingNewLocation;
                } else {
                	System.out.println("Select a valid Piece to move.");
                }
                break;

            case P1_SelectingNewLocation : 
                System.out.println(currentState);

                Optional<Cell> p1_clickedLoc = grid.cellAtPoint(new Point(x, y));
                                
                if (p1_clickedLoc.isPresent()) {
                    if (cellOverlay.contains(p1_clickedLoc.get())) {
                    	// if a Pawn reaches the other end of the board
                    	if (pieceInAction.get().getClass() == Pawn.class && isAlongTopBottom(p1_clickedLoc.get())) {
                			// * SUBJECT TO CHANGE * change Pawn to Queen (testing)
                			pieces.add(new Queen(p1_clickedLoc.get(), pieceInAction.get().teamColour));
                			pieces.remove(pieces.indexOf(pieceInAction.get()));
                    	} else {
                            pieceInAction.get().setLocation(p1_clickedLoc.get()); 
                    	}
                    	
                        pieceInAction.get().unmoved = false; // applications for Pawn, King, and Rook
                        
                        // clear cell overlays
                        clearCellOverlay();
                        currentState = State.PlayerTwoMoving;
                    } 
                    else if (offensiveOverlay.contains(p1_clickedLoc.get())) {
                    	removeEnemyPiece(p1_clickedLoc.get());
                    	
                    	// if a Pawn reaches the other end of the board
                    	if (pieceInAction.get().getClass() == Pawn.class && isAlongTopBottom(p1_clickedLoc.get())) {
                    		// change Pawn to Queen
                			pieces.add(new Queen(p1_clickedLoc.get(), pieceInAction.get().teamColour)); 
                			pieces.remove(pieces.indexOf(pieceInAction.get()));
                    	} else {
                            pieceInAction.get().setLocation(p1_clickedLoc.get()); 
                    	}
                        // clear cell overlays
                        clearCellOverlay();
                        currentState = State.PlayerTwoMoving;
                    } else {
                    	pieceInAction = Optional.empty();
                    	// clear cell overlays
                        clearCellOverlay();   
                    	currentState = State.PlayerOneMoving;
                    }
                } 
                break;

            case PlayerTwoMoving : 
                System.out.println(currentState);
                
                occupied = setOccupiedCells(); // identify occupied Cells
                setPieceInAction(x, y, Color.GRAY);

                if (pieceInAction.isPresent()) {
                    System.out.println(pieceInAction.get().getClass() + " : " + pieceInAction.get().loc);
                    pieceInAction.get().setMoves(occupied, pieces);
                    
                    if (pieceInAction.get().getClass() == King.class) {
                    	ArrayList<Cell> enemyMoves = getEnemyMovesList(pieceInAction.get().teamColour);
                    	
//                    	for (Cell c : (pieceInAction.get()).moves) {
//                    		if (enemyMoves.contains(c)) {
//                    			pieceInAction.get().moves.remove(c);
//                    		}
//                    	}
                    }
                    
                    setPieceMovesOverlay(pieceInAction.get());
                    clearPieceMoves();

                    currentState = State.P2_SelectingNewLocation;
                } else {
                	System.out.println("Select a valid Piece to move.");
                }
                break;

            case P2_SelectingNewLocation : 
                System.out.println(currentState);

                Optional<Cell> p2_clickedLoc = grid.cellAtPoint(new Point(x, y));
                                
                if (p2_clickedLoc.isPresent()) {
                    if (cellOverlay.contains(p2_clickedLoc.get())) {
                    	// if a Pawn reaches the other end of the board
                    	if (pieceInAction.get().getClass() == Pawn.class && isAlongTopBottom(p2_clickedLoc.get())) {
                			// * SUBJECT TO CHANGE * change Pawn to Queen (testing)
                			pieces.add(new Queen(p2_clickedLoc.get(), pieceInAction.get().teamColour)); 
                			pieces.remove(pieces.indexOf(pieceInAction.get()));
                    	} else {
                            pieceInAction.get().setLocation(p2_clickedLoc.get()); 
                    	}
                    	
                        pieceInAction.get().unmoved = false; // applications for Pawn, King, and Rook

                        // clear cell overlays
                        clearCellOverlay(); 
                        currentState = State.PlayerOneMoving;
                    } 
                    else if (offensiveOverlay.contains(p2_clickedLoc.get())) {
                    	removeEnemyPiece(p2_clickedLoc.get());
                    	
                    	// if a Pawn reaches the other end of the board
                    	if (pieceInAction.get().getClass() == Pawn.class && isAlongTopBottom(p2_clickedLoc.get())) {
                    		// change Pawn to Queen
                			pieces.add(new Queen(p2_clickedLoc.get(), pieceInAction.get().teamColour)); 
                			pieces.remove(pieces.indexOf(pieceInAction.get()));
                    	} else {
                            pieceInAction.get().setLocation(p2_clickedLoc.get()); 
                    	}
                    	
                        // clear cell overlays
                        clearCellOverlay();
                        currentState = State.PlayerOneMoving;
                    } else {
                    	pieceInAction = Optional.empty();
                    	// clear cell overlays
                        clearCellOverlay();                        
                    	currentState = State.PlayerTwoMoving;
                    }
                }
                break;
        }
    }

    private void removeEnemyPiece(Cell clickedLoc) {
    	for (Piece p : pieces) {
        
            // find enemy piece occupying clicked cell & remove it from the board
            if (p.loc.equals(clickedLoc)) {
                pieces.remove(p);
                break;
            }
        }
	}

	private void setPieceMovesOverlay(Piece piece) {
        cellOverlay = piece.moves; 
        offensiveOverlay = piece.offensiveMoves;
	}
	
	private void clearCellOverlay() {
        cellOverlay.clear(); 
        offensiveOverlay.clear();
	}

	// set pieceInAction to the clicked Piece (if it exists)
    private void setPieceInAction(int x, int y, Color c) {
        pieceInAction = Optional.empty();

        for (Piece p : pieces) {
            if (((p.loc).contains(x, y)) && (p.teamColour == c)) {
            	// store selected piece (if present)
                pieceInAction = Optional.of(p); 
                break; // stop loop since pieceInAction is set
            }
        }
	}
    
    private void clearPieceMoves() {
        pieceInAction.get().moves = new ArrayList<>();
        pieceInAction.get().offensiveMoves = new ArrayList<>();
    }

	// add a Piece to the global list 'pieces'
	private void addPiece (Piece p) {
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
    private Boolean cellIsOccupied(Cell c) {
        for (Piece pc : pieces) {
            if ((pc.loc).equals(c)) {
                return true;
            }
        }
        return false;
    }
    
    // return true if a given Cell is along the 
    private Boolean isAlongTopBottom(Cell c) {
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
    private ArrayList<Cell> setOccupiedCells() {
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
    
	private ArrayList<Cell> getEnemyMovesList(Color teamColour) {
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
