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
    public ArrayList<Cell> setMoves() {
        ArrayList<Cell> moves = new ArrayList<>();
        Cell c = loc;
        moves.add(c);

        if ((loc.x + Cell.size * 2) < Grid.gridSpan && (loc.y + Cell.size) < Grid.gridSpan) {
            c = new Cell(new Point (loc.x + Cell.size * 2, loc.y + Cell.size));
            moves.add(c);
        }
        if ((loc.x + Cell.size * 2) < Grid.gridSpan && (loc.y - Cell.size) >= 0) {
            c = new Cell(new Point (loc.x + Cell.size * 2, loc.y - Cell.size));
            moves.add(c);
        }
        if ((loc.x + Cell.size) < Grid.gridSpan && (loc.y + Cell.size * 2) < Grid.gridSpan) {
            c = new Cell(new Point (loc.x + Cell.size, loc.y + Cell.size * 2));
            moves.add(c);
        }
        if ((loc.x + Cell.size) < Grid.gridSpan && (loc.y - Cell.size * 2) >= 0) {
            c = new Cell(new Point (loc.x + Cell.size, loc.y - Cell.size * 2));
            moves.add(c);
        }
        if ((loc.x - Cell.size * 2) >= 0 && (loc.y + Cell.size) < Grid.gridSpan) {
            c = new Cell(new Point (loc.x - Cell.size * 2, loc.y + Cell.size));
            moves.add(c);
        }
        if ((loc.x - Cell.size * 2) >= 0 && (loc.y - Cell.size) >= 0) {
            c = new Cell(new Point (loc.x - Cell.size * 2, loc.y - Cell.size));
            moves.add(c);
        }
        if ((loc.x - Cell.size) >= 0 && (loc.y + Cell.size * 2) < Grid.gridSpan) {
            c = new Cell(new Point (loc.x - Cell.size, loc.y + Cell.size * 2));
            moves.add(c);
        }
        if ((loc.x - Cell.size) >= 0 && (loc.y - Cell.size * 2) >= 0) {
            c = new Cell(new Point (loc.x - Cell.size, loc.y - Cell.size * 2));
            moves.add(c);
        }

        return moves;
    }

}
