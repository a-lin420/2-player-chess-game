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
    public ArrayList<Cell> setMoves() {
        ArrayList<Cell> moves = new ArrayList<>();
        Cell c = loc;
        moves.add(c);

        int next = Cell.size;
        for (int i = 0; i < 7; i++) {
            c = new Cell(new Point (loc.x, loc.y + next)); // bottom
            moves.add(c);
            next += Cell.size;
        }

        next = Cell.size;
        for (int i = 0; i < 7; i++) {
            c = new Cell(new Point (loc.x, loc.y - next)); // top
            moves.add(c);
            next += Cell.size;
        }

        next = Cell.size;
        for (int i = 0; i < 7; i++) {
            c = new Cell(new Point (loc.x + next, loc.y)); // right
            moves.add(c);
            next += Cell.size;
        }

        next = Cell.size;
        for (int i = 0; i < 7; i++) {
            c = new Cell(new Point (loc.x - next, loc.y)); // left
            moves.add(c);
            next += Cell.size;
        }

        return moves;
    }
}
