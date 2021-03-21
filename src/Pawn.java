import java.awt.*;
import java.util.ArrayList;

public class Pawn extends Piece {

    public Pawn (Cell loc, Color c) {
        teamColour = c;
        unmoved = true;
        setLocation(loc);

        moves = new ArrayList<>();
        offensiveMoves = new ArrayList<>();
    }

    @Override
    public void setFeatures () {
        features = new ArrayList<Polygon>();

        Polygon head = new Polygon();
        head.addPoint(loc.x + 28, loc.y + 20);
        head.addPoint(loc.x + 28, loc.y + 35);
        head.addPoint(loc.x + 47, loc.y + 35);
        head.addPoint(loc.x + 47, loc.y + 20);

        Polygon neck = new Polygon();
        neck.addPoint(loc.x + 32, loc.y + 35);
        neck.addPoint(loc.x + 30, loc.y + 55);
        neck.addPoint(loc.x + 45 , loc.y + 55);
        neck.addPoint(loc.x + 43, loc.y + 35);

        Polygon base1 = new Polygon();
        base1.addPoint(loc.x + 25, loc.y + 55);
        base1.addPoint(loc.x + 25, loc.y + 60);
        base1.addPoint(loc.x + 50, loc.y + 60);
        base1.addPoint(loc.x + 50, loc.y + 55);

        Polygon base2 = new Polygon();
        base2.addPoint(loc.x + 22, loc.y + 60);
        base2.addPoint(loc.x + 22, loc.y + 65);
        base2.addPoint(loc.x + 53, loc.y + 65);
        base2.addPoint(loc.x + 53, loc.y + 60);

        features.add(head);
        features.add(neck);  
        features.add(base1);  
        features.add(base2);

    }

    @Override
    public void setMoves(ArrayList<Cell> occupied, ArrayList<Piece> pieces) {
        Cell cell = loc; // overlay for piece's current Cell location
        moves.add(cell);
        
        if (teamColour == Color.WHITE) {
            /* NORMAL MOVES */
            cell = new Cell(new Point(loc.x, loc.y + Cell.size));
            // if the cell is within grid boundaries and is not occupied
            if (cell.withinGridBounds() && !occupied.contains(cell)) { 
                moves.add(cell); // add cell to the list

                // if cell is unmoved
                if (unmoved) {
                    cell = new Cell(new Point (loc.x, loc.y + Cell.size * 2));
                    if (!occupied.contains(cell)) {
                        moves.add(cell);
                    }
                }
            }

            /* OFFENSIVE MOVES */
            cell = new Cell(new Point(loc.x + Cell.size, loc.y + Cell.size));
            // if cell for offensive move is occupied
            if (cell.withinGridBounds() && occupied.contains(cell)) {
                for (Piece p : pieces) {
                    // check if the piece occupying cell is on the opposing team
                    if ((p.loc).equals(cell) && p.teamColour == Color.GRAY) {
                        offensiveMoves.add(cell); // add piece to the list
                        break;
                    }
                }
            }
            cell = new Cell(new Point(loc.x - Cell.size, loc.y + Cell.size));
            if (cell.withinGridBounds() && occupied.contains(cell)) {
                for (Piece p : pieces) {
                    if ((p.loc).equals(cell) && p.teamColour == Color.GRAY) {
                        offensiveMoves.add(cell);
                        break;
                    }
                }
            }

        } else {
            /* NORMAL MOVES */
            cell = new Cell(new Point(loc.x, loc.y - Cell.size));
            if (cell.withinGridBounds() && !occupied.contains(cell)) {
                moves.add(cell);

                if (unmoved) {
                    cell = new Cell(new Point (loc.x, loc.y - Cell.size * 2));
                    if (!occupied.contains(cell)) {
                        moves.add(cell);
                    }
                }
            }

            /* OFFENSIVE MOVES */
            cell = new Cell(new Point(loc.x + Cell.size, loc.y - Cell.size));
            // if cell for offensive move is occupied
            if (cell.withinGridBounds() && occupied.contains(cell)) {
                for (Piece p : pieces) {
                    // check if the piece occupying cell is on the opposing team
                    if ((p.loc).equals(cell) && p.teamColour == Color.WHITE) {
                        moves.add(cell); // add piece to the list
                        break;
                    }
                }
            }
            cell = new Cell(new Point(loc.x - Cell.size, loc.y - Cell.size));
            if (cell.withinGridBounds() && occupied.contains(cell)) {
                for (Piece p : pieces) {
                    if ((p.loc).equals(cell) && p.teamColour == Color.WHITE) {
                        moves.add(cell);
                        break;
                    }
                }
            }
        }
    }

}
