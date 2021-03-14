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

        c = new Cell(new Point (loc.x, loc.y + Cell.size)); // top
        moves.add(c);
        c = new Cell(new Point (loc.x, loc.y - Cell.size)); // bottom
        moves.add(c);
        c = new Cell(new Point (loc.x + Cell.size, loc.y)); // right
        moves.add(c);
        c = new Cell(new Point (loc.x - Cell.size, loc.y)); // left
        moves.add(c);
        c = new Cell(new Point (loc.x + Cell.size, loc.y + Cell.size)); // top-right
        moves.add(c);
        c = new Cell(new Point (loc.x - Cell.size, loc.y + Cell.size)); // top-left
        moves.add(c);
        c = new Cell(new Point (loc.x + Cell.size, loc.y - Cell.size)); // bottom-right
        moves.add(c);
        c = new Cell(new Point (loc.x - Cell.size, loc.y - Cell.size)); // bottom-left
        moves.add(c);

        return moves;
    }

}
