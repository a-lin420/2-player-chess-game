import java.awt.*;
import java.util.ArrayList;

public class Pawn extends Piece {

    public Pawn (Cell loc, Color c) {
        this.teamColour = c;
        this.unmoved = true;
        this.setLocation(loc);
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
        Cell c = loc; // overlay for piece's current Cell location
        moves.add(c);
        
        if (teamColour == Color.WHITE) {
            c = new Cell(new Point (loc.x, loc.y + Cell.size));
        } else {
            c = new Cell(new Point (loc.x, loc.y - Cell.size));
        }
        moves.add(c);

        if (unmoved) {
            if (teamColour == Color.WHITE) {
                c = new Cell(new Point (loc.x, loc.y + Cell.size * 2));
            } else {
                c = new Cell(new Point (loc.x, loc.y - Cell.size * 2));
            }
            moves.add(c);
        }

        return moves;
    }
}
