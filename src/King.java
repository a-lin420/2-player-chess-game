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
    public ArrayList<Cell> setMoves(ArrayList<Cell> occupied) {
        ArrayList<Cell> moves = new ArrayList<>();
        Cell c = loc;
        moves.add(c);

        // CARDINALS
        Point pt = new Point(loc.x, loc.y + Cell.size);
        if (pt.getY() < Grid.gridSpan && !occupied.contains(new Cell(pt))) {
            c = new Cell(pt); // S
            moves.add(c);
        }
        pt = new Point(loc.x, loc.y - Cell.size);
        if (pt.getY() >= 0 && !occupied.contains(new Cell(pt))) {
            c = new Cell(pt); // N
            moves.add(c);
        }
        pt = new Point(loc.x + Cell.size, loc.y);
        if (pt.getX() < Grid.gridSpan && !occupied.contains(new Cell(pt))) {
            c = new Cell(pt); // E
            moves.add(c);
        }
        pt = new Point(loc.x - Cell.size, loc.y);
        if (pt.getX() >= 0 && !occupied.contains(new Cell(pt))) {   
            c = new Cell(pt); // W
            moves.add(c);
        }

        // INTERCARDINALS
        pt = new Point(new Point (loc.x + Cell.size, loc.y + Cell.size));
        if (pt.getX() < Grid.gridSpan && pt.getY() < Grid.gridSpan && !occupied.contains(new Cell(pt))) {
            c = new Cell(pt); // fill SE
            moves.add(c);
        }
        pt = new Point(new Point (loc.x - Cell.size, loc.y + Cell.size));
        if (pt.getX() >= 0 && pt.getY() < Grid.gridSpan && !occupied.contains(new Cell(pt))) {
            c = new Cell(pt); // fill SW
            moves.add(c);
        }
        pt = new Point(new Point (loc.x + Cell.size, loc.y - Cell.size));
        if (pt.getX() < Grid.gridSpan && pt.getY() >= 0 && !occupied.contains(new Cell(pt))) {
            c = new Cell(pt); // fill NE
            moves.add(c);
        }
        pt = new Point(new Point (loc.x - Cell.size, loc.y - Cell.size));
        if (pt.getX() >= 0 && pt.getY() >= 0 && !occupied.contains(new Cell(pt))) {
            c = new Cell(pt); // fill NW
            moves.add(c);
        }

        return moves;
    }

}
