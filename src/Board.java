import java.awt.*;
import java.util.*;

public class Board {
    Grid grid;
    ArrayList<Cell> cellOverlay;
    ArrayList<Piece> pieces;
    Optional<Piece> pieceInAction;
    ArrayList<Cell> occupied;

    enum State { PlayerOneMoving, P1_SelectingNewLocation, 
                 PlayerTwoMoving, P2_SelectingNewLocation }
    State currentState = State.PlayerOneMoving;

    public Board (Point p) {
        grid = new Grid (p);
        cellOverlay = new ArrayList<Cell>();
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
    }

    public void mouseClicked(int x, int y) {
        System.out.println("Mouse location: " + x + ", " + y);

        switch (currentState) {
            case PlayerOneMoving : 
                System.out.println(currentState);

                pieceInAction = Optional.empty();

                for (Piece p : pieces) {
                    if (p.loc.contains(x, y) && p.teamColour == Color.WHITE) {
                        pieceInAction = Optional.of(p); // store selected piece if present

                        occupied = setOccupiedCells(); // recalculate occupied Cells
                        cellOverlay = p.setMoves(occupied); // return a list of all possible moves for the selected piece

                        System.out.println(pieceInAction.get().getClass() + " : " + pieceInAction.get().loc);
                        currentState = State.P1_SelectingNewLocation;
                    }
                }
                break;

            case P1_SelectingNewLocation : 
                System.out.println(currentState);

                Optional<Cell> p1_clicked = grid.cellAtPoint(new Point(x, y));
                                
                if (pieceInAction.isPresent() && p1_clicked.isPresent() && cellOverlay.contains(p1_clicked.get())) {
                    pieceInAction.get().setLocation(p1_clicked.get()); // refactor for more accurate piece relocation
                    pieceInAction.get().unmoved = false;
                    cellOverlay = new ArrayList<Cell>();

                    currentState = State.PlayerTwoMoving;
                }
                break;

            case PlayerTwoMoving : 
                System.out.println(currentState);

                pieceInAction = Optional.empty();

                for (Piece p : pieces) {
                    if (p.loc.contains(x, y) && p.teamColour == Color.GRAY) {
                        pieceInAction = Optional.of(p);

                        occupied = setOccupiedCells(); // recalculate occupied Cells
                        cellOverlay = p.setMoves(occupied); // return a list of all possible moves for the selected piece

                        System.out.println(pieceInAction.get().getClass() + " : " + pieceInAction.get().loc);
                        currentState = State.P2_SelectingNewLocation;
                    }
                }
                break;

            case P2_SelectingNewLocation : 
                System.out.println(currentState);

                Optional<Cell> p2_clicked = grid.cellAtPoint(new Point(x, y));
                                
                if (pieceInAction.isPresent() && p2_clicked.isPresent() && cellOverlay.contains(p2_clicked.get())) {
                    pieceInAction.get().setLocation(p2_clicked.get()); 
                    pieceInAction.get().unmoved = false;
                    cellOverlay = new ArrayList<Cell>();

                    currentState = State.PlayerOneMoving;
                }
                break;
        }
    }

    public Boolean cellIsOccupied (Cell c) {
        for (Piece pc : pieces) {
            if ((pc.loc).equals(c)) {
                return true;
            }
        }
        return false;
    }

    public void addPiece (Piece p) {
        pieces.add(p);
    }

    public void setBoardPieces () {
        // add pawns when a board is initialised
        for (int i = 0; i < 8; i++) {
            Pawn whitePawn = new Pawn (this.grid.cells[i][1], Color.WHITE);
            this.addPiece(whitePawn);
        }
        for (int i = 0; i < 8; i++) {
            Pawn blackPawn = new Pawn (this.grid.cells[i][6], Color.GRAY);
            this.addPiece(blackPawn);
        }

        King whiteKing = new King (this.grid.cells[4][0], Color.WHITE);
        this.addPiece(whiteKing);
        King blackKing = new King (this.grid.cells[3][7], Color.GRAY);
        this.addPiece(blackKing);

        Queen whiteQueen = new Queen (this.grid.cells[3][0], Color.WHITE);
        this.addPiece(whiteQueen);
        Queen blackQueen = new Queen (this.grid.cells[4][7], Color.GRAY);
        this.addPiece(blackQueen);

        Bishop whiteBishop1 = new Bishop(this.grid.cells[1][0], Color.WHITE);
        Bishop whiteBishop2 = new Bishop(this.grid.cells[6][0], Color.WHITE);
        this.addPiece(whiteBishop1);
        this.addPiece(whiteBishop2);
        Bishop blackBishop1 = new Bishop(this.grid.cells[1][7], Color.GRAY);
        Bishop blackBishop2 = new Bishop(this.grid.cells[6][7], Color.GRAY);
        this.addPiece(blackBishop1);
        this.addPiece(blackBishop2);

        Knight whiteKnight1 = new Knight (this.grid.cells[2][0], Color.WHITE);
        Knight whiteKnight2 = new Knight (this.grid.cells[5][0], Color.WHITE);
        this.addPiece(whiteKnight1);
        this.addPiece(whiteKnight2);
        Knight blackKnight1 = new Knight (this.grid.cells[2][7], Color.GRAY);
        Knight blackKnight2 = new Knight (this.grid.cells[5][7], Color.GRAY);
        this.addPiece(blackKnight1);
        this.addPiece(blackKnight2);

        Rook whiteRook1 = new Rook (this.grid.cells[0][0], Color.WHITE);
        Rook whiteRook2 = new Rook (this.grid.cells[7][0], Color.WHITE);
        this.addPiece(whiteRook1);
        this.addPiece(whiteRook2);
        Rook blackRook1 = new Rook (this.grid.cells[0][7], Color.GRAY);
        Rook blackRook2 = new Rook (this.grid.cells[7][7], Color.GRAY);
        this.addPiece(blackRook1);
        this.addPiece(blackRook2);
    }

    public ArrayList<Cell> setOccupiedCells () {
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

}
