import java.awt.*;
import java.util.ArrayList;

public class Rook extends Piece {
    public Rook (Cell loc, Color c) {
        this.setLocation(loc);
        this.teamColour = c;
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
    public ArrayList<Cell> setMoves(ArrayList<Cell> occupied) {
        ArrayList<Cell> moves = new ArrayList<>();
        Cell c = loc;
        moves.add(c);

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

        return moves;
    }
}