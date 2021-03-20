import java.awt.*;
import java.util.ArrayList;

public class Knight extends Piece {
    public Knight (Cell loc, Color c) {
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

        Point pt = new Point(loc.x + Cell.size * 2, loc.y + Cell.size);
        if (pt.getX() < Grid.gridSpan && pt.getY() < Grid.gridSpan && !occupied.contains(new Cell(pt))) {
            c = new Cell(pt);
            moves.add(c);
        }
        pt = new Point(loc.x + Cell.size * 2, loc.y - Cell.size);
        if (pt.getX() < Grid.gridSpan && pt.getY() >= 0 && !occupied.contains(new Cell(pt))) {
            c = new Cell(pt);
            moves.add(c);
        }
        pt = new Point(loc.x + Cell.size, loc.y + Cell.size * 2);
        if (pt.getX() < Grid.gridSpan && pt.getY() < Grid.gridSpan && !occupied.contains(new Cell(pt))) {
            c = new Cell(pt);
            moves.add(c);
        }
        pt = new Point(loc.x + Cell.size, loc.y - Cell.size * 2);
        if (pt.getX() < Grid.gridSpan && pt.getY() >= 0 && !occupied.contains(new Cell(pt))) {
            c = new Cell(pt);
            moves.add(c);
        }
        pt = new Point (loc.x - Cell.size * 2, loc.y + Cell.size);
        if (pt.getX() >= 0 && pt.getY() < Grid.gridSpan && !occupied.contains(new Cell(pt))) {
            c = new Cell(pt);
            moves.add(c);
        }
        pt = new Point (loc.x - Cell.size * 2, loc.y - Cell.size);
        if (pt.getX() >= 0 && pt.getY() >= 0 && !occupied.contains(new Cell(pt))) {
            c = new Cell(pt);
            moves.add(c);
        }
        pt = new Point (loc.x - Cell.size, loc.y + Cell.size * 2);
        if (pt.getX() >= 0 && pt.getY() < Grid.gridSpan && !occupied.contains(new Cell(pt))) {
            c = new Cell(pt);
            moves.add(c);
        }
        pt = new Point (loc.x - Cell.size, loc.y - Cell.size * 2);
        if (pt.getX() >= 0 && pt.getY() >= 0 && !occupied.contains(new Cell(pt))) {
            c = new Cell(pt);
            moves.add(c);
        }

        return moves;
    }

}
