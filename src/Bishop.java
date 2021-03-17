import java.awt.*;
import java.util.ArrayList;

public class Bishop extends Piece {
    public Bishop (Cell loc, Color c) {
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
    public ArrayList<Cell> setMoves() {
        ArrayList<Cell> moves = new ArrayList<>();
        Cell c = loc;
        moves.add(c);

        int nextInc = Cell.size;
        while ((loc.x + nextInc) < Grid.gridSpan && (loc.y + nextInc) < Grid.gridSpan) {
            c = new Cell(new Point (loc.x + nextInc, loc.y + nextInc)); // fill SE
            moves.add(c);
            nextInc += Cell.size;
        }

        nextInc = Cell.size;
        while ((loc.x - nextInc) >= 0 && (loc.y + nextInc) < Grid.gridSpan) {
            c = new Cell(new Point (loc.x - nextInc, loc.y + nextInc)); // fill SW
            moves.add(c);
            nextInc += Cell.size;
        }

        nextInc = Cell.size;
        while ((loc.x + nextInc) < Grid.gridSpan && (loc.y - nextInc) >= 0) {
            c = new Cell(new Point (loc.x + nextInc, loc.y - nextInc)); // fill NE
            moves.add(c);
            nextInc += Cell.size;
        }

        nextInc = Cell.size;
        while ((loc.x - nextInc) >= 0 && (loc.y - nextInc) >= 0) {
            c = new Cell(new Point (loc.x - nextInc, loc.y - nextInc)); // fill NW
            moves.add(c);
            nextInc += Cell.size;
        }

        return moves;
    }
}
