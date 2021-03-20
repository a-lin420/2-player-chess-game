import java.awt.*;
import java.util.ArrayList;

public class Queen extends Piece {
    public Queen (Cell loc, Color c) {
        this.setLocation(loc);
        this.teamColour = c;
    }

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
    public ArrayList<Cell> setMoves(ArrayList<Cell> occupied) {
        ArrayList<Cell> moves = new ArrayList<>();
        Cell c = loc;
        moves.add(c);

        // CARDINALS
        Point pt = new Point (loc.x, loc.y - Cell.size);
        while (pt.getY() >= 0 && !occupied.contains(new Cell(pt))) {
            c = new Cell(pt); // fill N
            moves.add(c);
            pt.y -= Cell.size; // decrement y
        }
        pt = new Point (loc.x, loc.y + Cell.size);
        while (pt.getY() < Grid.gridSpan && !occupied.contains(new Cell(pt))) {
            c = new Cell(pt); // fill S
            moves.add(c);
            pt.y += Cell.size; // increment y
        }
        pt = new Point (loc.x + Cell.size, loc.y);
        while (pt.getX() < Grid.gridSpan && !occupied.contains(new Cell(pt))) {
            c = new Cell(pt); // fill E 
            moves.add(c);
            pt.x += Cell.size; // increment x
        }
        pt = new Point (loc.x - Cell.size, loc.y);
        while (pt.getX() >= 0 && !occupied.contains(new Cell(pt))) {
            c = new Cell(pt); // fill W
            moves.add(c);
            pt.x -= Cell.size; // decrement x
        }

        // INTERCARDINALS
        pt = new Point(new Point (loc.x + Cell.size, loc.y + Cell.size));
        while (pt.getX() < Grid.gridSpan && pt.getY() < Grid.gridSpan && !occupied.contains(new Cell(pt))) {
            c = new Cell(pt); // fill SE
            moves.add(c);
            // increment x and y of pt
            pt.x += Cell.size;
            pt.y += Cell.size;
        }
        pt = new Point(new Point (loc.x - Cell.size, loc.y + Cell.size));
        while (pt.getX() >= 0 && pt.getY() < Grid.gridSpan && !occupied.contains(new Cell(pt))) {
            c = new Cell(pt); // fill SW
            moves.add(c);
            // decrement x and increment y of pt
            pt.x -= Cell.size;
            pt.y += Cell.size;
        }
        pt = new Point(new Point (loc.x + Cell.size, loc.y - Cell.size));
        while (pt.getX() < Grid.gridSpan && pt.getY() >= 0 && !occupied.contains(new Cell(pt))) {
            c = new Cell(pt); // fill NE
            moves.add(c);
            // increment x and decrement y of pt
            pt.x += Cell.size;
            pt.y -= Cell.size;
        }
        pt = new Point(new Point (loc.x - Cell.size, loc.y - Cell.size));
        while (pt.getX() >= 0 && pt.getY() >= 0 && !occupied.contains(new Cell(pt))) {
            c = new Cell(pt); // fill NW
            moves.add(c);
            // decrement x and y of pt
            pt.x -= Cell.size;
            pt.y -= Cell.size;
        }

        return moves;
    }
}