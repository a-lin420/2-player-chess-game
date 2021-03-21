import java.awt.*;
import java.util.ArrayList;

public class King extends Piece {
    public King (Cell loc, Color c) {
        this.setLocation(loc);;
        this.teamColour = c;

        moves = new ArrayList<>();
        offensiveMoves = new ArrayList<>();
    }

    public void setFeatures () {
        this.features = new ArrayList<Polygon>();

        Polygon crossVert = new Polygon();
        crossVert.addPoint(loc.x + 33, loc.y + 10);
        crossVert.addPoint(loc.x + 42, loc.y + 25);
        crossVert.addPoint(loc.x + 33, loc.y + 25);
        crossVert.addPoint(loc.x + 42, loc.y + 10);
        Polygon crossHoriz = new Polygon();
        crossHoriz.addPoint(loc.x + 31, loc.y + 12);
        crossHoriz.addPoint(loc.x + 44, loc.y + 23);
        crossHoriz.addPoint(loc.x + 44, loc.y + 12);
        crossHoriz.addPoint(loc.x + 31, loc.y + 23);

        Polygon head = new Polygon();
        head.addPoint(loc.x + 28, loc.y + 25);
        head.addPoint(loc.x + 28, loc.y + 35);
        head.addPoint(loc.x + 47, loc.y + 35);
        head.addPoint(loc.x + 47, loc.y + 25);

        Polygon neck = new Polygon();
        neck.addPoint(loc.x + 32, loc.y + 35);
        neck.addPoint(loc.x + 30, loc.y + 55);
        neck.addPoint(loc.x + 45 , loc.y + 55);
        neck.addPoint(loc.x + 43, loc.y + 35);

        Polygon base1 = new Polygon();
        base1.addPoint(loc.x + 25, loc.y + 55);
        base1.addPoint(loc.x + 25, loc.y + 60);
        base1.addPoint(loc.x + 50, loc.y + 60);
        base1.addPoint(loc.x + 50, loc.y + 55);

        Polygon base2 = new Polygon();
        base2.addPoint(loc.x + 22, loc.y + 60);
        base2.addPoint(loc.x + 22, loc.y + 65);
        base2.addPoint(loc.x + 53, loc.y + 65);
        base2.addPoint(loc.x + 53, loc.y + 60);

        this.features.add(crossVert);
        this.features.add(crossHoriz);
        this.features.add(head);
        this.features.add(neck);  
        this.features.add(base1);  
        this.features.add(base2);
    }

    @Override
    public void setMoves(ArrayList<Cell> occupied, ArrayList<Piece> pieces) {
        Cell cell = loc;
        moves.add(cell);

        // CARDINALS
        cell = new Cell(new Point(loc.x, loc.y + Cell.size)); // S
        if (cell.withinGridBounds() && !occupied.contains(cell)) {
            moves.add(cell);
        }
        cell = new Cell(new Point(loc.x, loc.y - Cell.size)); // N
        if (cell.getY() >= 0 && !occupied.contains(cell)) {
            moves.add(cell);
        }
        cell = new Cell(new Point(loc.x + Cell.size, loc.y)); // E
        if (cell.getX() < Grid.gridSpan && !occupied.contains(cell)) {
            moves.add(cell);
        }
        cell = new Cell(new Point(loc.x - Cell.size, loc.y)); // W
        if (cell.getX() >= 0 && !occupied.contains(cell)) {   
            moves.add(cell);
        }

        // INTERCARDINALS
        cell = new Cell(new Point(new Point (loc.x + Cell.size, loc.y + Cell.size))); // SE
        if (cell.withinGridBounds() && !occupied.contains(cell)) {
            moves.add(cell);
        }
        cell = new Cell(new Point(new Point (loc.x - Cell.size, loc.y + Cell.size))); // SW
        if (cell.withinGridBounds() && !occupied.contains(cell)) {
            moves.add(cell);
        }
        cell = new Cell(new Point(new Point (loc.x + Cell.size, loc.y - Cell.size))); // NE
        if (cell.withinGridBounds() && !occupied.contains(cell)) {
            moves.add(cell);
        }
        cell = new Cell(new Point(new Point (loc.x - Cell.size, loc.y - Cell.size))); // NW
        if (cell.withinGridBounds() && !occupied.contains(cell)) {
            moves.add(cell);
        }
    }

}
