import java.awt.*;
import java.util.ArrayList;

public class King extends Piece {
    public King (Cell loc, Color c) {
        this.setLocation(loc);;
        this.teamColour = c;
    }

    public void setFeatures () {
        this.features = new ArrayList<Polygon>();
        Polygon face = new Polygon();
        face.addPoint(loc.x + 8, loc.y + 12);
        face.addPoint(loc.x + 27, loc.y + 12);
        face.addPoint(loc.x + 27, loc.y + 25);
        face.addPoint(loc.x + 8, loc.y + 25);
        this.features.add(face);
    }

    @Override
    public ArrayList<Cell> setMoves() {
        ArrayList<Cell> moves = new ArrayList<>();
        Cell c = loc;
        moves.add(c);

        if (loc.y + Cell.size < Grid.gridSpan) {
            c = new Cell(new Point (loc.x, loc.y + Cell.size)); // S
            moves.add(c);
        }
        if (loc.y - Cell.size >= 0) {
            c = new Cell(new Point (loc.x, loc.y - Cell.size)); // N
            moves.add(c);
        }
        if (loc.x + Cell.size < Grid.gridSpan) {
            c = new Cell(new Point (loc.x + Cell.size, loc.y)); // E
            moves.add(c);
        }
        if (loc.x - Cell.size >= 0) {   
            c = new Cell(new Point (loc.x - Cell.size, loc.y)); // W
            moves.add(c);
        }
        if (loc.y + Cell.size < Grid.gridSpan && loc.x + Cell.size < Grid.gridSpan) {
            c = new Cell(new Point (loc.x + Cell.size, loc.y + Cell.size)); // SE
            moves.add(c);
        }
        if (loc.y + Cell.size < Grid.gridSpan && loc.x - Cell.size >= 0) {
            c = new Cell(new Point (loc.x - Cell.size, loc.y + Cell.size)); // SW
            moves.add(c);
        }
        if (loc.y - Cell.size >= 0 && loc.x + Cell.size < Grid.gridSpan) {
            c = new Cell(new Point (loc.x + Cell.size, loc.y - Cell.size)); // NE
            moves.add(c);
        }
        if (loc.y - Cell.size >= 0 && loc.x - Cell.size >= 0) {
            c = new Cell(new Point (loc.x - Cell.size, loc.y - Cell.size)); // NW
            moves.add(c);
        }

        return moves;
    }

}
