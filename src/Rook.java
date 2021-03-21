import java.awt.*;
import java.util.ArrayList;

public class Rook extends Piece {
    public Rook (Cell loc, Color c) {
        this.setLocation(loc);
        this.teamColour = c;

        moves = new ArrayList<>();
        offensiveMoves = new ArrayList<>();
    }

    @Override
    public void setFeatures () {
        this.features = new ArrayList<Polygon>();
        Polygon ear1 = new Polygon();
        ear1.addPoint(loc.x + 11, loc.y + 5);
        ear1.addPoint(loc.x + 11, loc.y + 12);
        ear1.addPoint(loc.x + 16, loc.y + 12);
        ear1.addPoint(loc.x + 16, loc.y + 5);
        Polygon ear2 = new Polygon();
        ear2.addPoint(loc.x + 19, loc.y + 5);
        ear2.addPoint(loc.x + 19, loc.y + 12);
        ear2.addPoint(loc.x + 24, loc.y + 12);
        ear2.addPoint(loc.x + 24, loc.y + 5);
        Polygon face = new Polygon();
        face.addPoint(loc.x + 8, loc.y + 12);
        face.addPoint(loc.x + 27, loc.y + 12);
        face.addPoint(loc.x + 27, loc.y + 25);
        face.addPoint(loc.x + 8, loc.y + 25);
        this.features.add(ear1);
        this.features.add(ear2);
        this.features.add(face);
    }

    @Override
    public void setMoves(ArrayList<Cell> occupied, ArrayList<Piece> pieces) {
        Cell cell = loc;
        moves.add(cell);

        cell = new Cell(new Point(loc.x, loc.y - Cell.size)); // fill N
        while (cell.withinGridBounds() && !occupied.contains(cell)) {
            moves.add(cell);
            cell = new Cell(new Point(cell.x, cell.y - Cell.size)); // decrement y
        }
        if (cell.withinGridBounds() && occupied.contains(cell)) {
            for (Piece p : pieces) {
                // if an enemy piece is occupying the cell
                if (p.loc.equals(cell) && p.teamColour != this.teamColour) {
                    offensiveMoves.add(cell);
                }
            }
        }

        cell = new Cell(new Point (loc.x, loc.y + Cell.size)); // fill S
        while (cell.withinGridBounds() && !occupied.contains(cell)) {
            moves.add(cell);
            cell = new Cell(new Point(cell.x, cell.y + Cell.size)); // increment y
        }
        if (cell.withinGridBounds() && occupied.contains(cell)) {
            for (Piece p : pieces) {
                if (p.loc.equals(cell) && p.teamColour != this.teamColour) {
                    offensiveMoves.add(cell);
                }
            }
        }

        cell = new Cell(new Point (loc.x + Cell.size, loc.y)); // fill E
        while (cell.withinGridBounds() && !occupied.contains(cell)) {
            moves.add(cell);
            cell = new Cell(new Point(cell.x + Cell.size, cell.y)); // increment x
        }
        if (cell.withinGridBounds() && occupied.contains(cell)) {
            for (Piece p : pieces) {
                if (p.loc.equals(cell) && p.teamColour != this.teamColour) {
                    offensiveMoves.add(cell);
                }
            }
        }

        cell = new Cell(new Point (loc.x - Cell.size, loc.y)); // fill E
        while (cell.withinGridBounds() && !occupied.contains(cell)) {
            moves.add(cell);
            cell = new Cell(new Point(cell.x - Cell.size, cell.y)); // increment x
        }
        if (cell.withinGridBounds() && occupied.contains(cell)) {
            for (Piece p : pieces) {
                if (p.loc.equals(cell) && p.teamColour != this.teamColour) {
                    offensiveMoves.add(cell);
                }
            }
        }
    }

}