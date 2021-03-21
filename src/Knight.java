import java.awt.*;
import java.util.ArrayList;

public class Knight extends Piece {
    public Knight (Cell loc, Color c) {
        this.setLocation(loc);
        this.teamColour = c;

        moves = new ArrayList<>();
        offensiveMoves = new ArrayList<>();
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
    public void setMoves(ArrayList<Cell> occupied, ArrayList<Piece> pieces) {
        Cell cell = loc;
        moves.add(cell);

        cell = new Cell(new Point(loc.x + Cell.size * 2, loc.y + Cell.size)); // SE1
        if (cell.withinGridBounds() && !occupied.contains(cell)) {
            moves.add(cell);
        } else if (cell.withinGridBounds() && occupied.contains(cell)) {
            for (Piece p : pieces) {
                if (p.loc.equals(cell) && p.teamColour != this.teamColour) {
                    offensiveMoves.add(cell);
                    break;
                }
            }
        }
        cell = new Cell(new Point(loc.x + Cell.size * 2, loc.y - Cell.size)); // NE1
        if (cell.withinGridBounds() && !occupied.contains(cell)) {
            moves.add(cell);
        } else if (cell.withinGridBounds() && occupied.contains(cell)) {
            for (Piece p : pieces) {
                if (p.loc.equals(cell) && p.teamColour != this.teamColour) {
                    offensiveMoves.add(cell);
                    break;
                }
            }
        }
        cell = new Cell(new Point(loc.x + Cell.size, loc.y + Cell.size * 2)); // SE2
        if (cell.withinGridBounds() && !occupied.contains(cell)) {
            moves.add(cell);
        } else if (cell.withinGridBounds() && occupied.contains(cell)) {
            for (Piece p : pieces) {
                if (p.loc.equals(cell) && p.teamColour != this.teamColour) {
                    offensiveMoves.add(cell);
                    break;
                }
            }
        }
        cell = new Cell(new Point(loc.x + Cell.size, loc.y - Cell.size * 2)); // NE2
        if (cell.withinGridBounds() && !occupied.contains(cell)) {
            moves.add(cell);
        } else if (cell.withinGridBounds() && occupied.contains(cell)) {
            for (Piece p : pieces) {
                if (p.loc.equals(cell) && p.teamColour != this.teamColour) {
                    offensiveMoves.add(cell);
                    break;
                }
            }
        }
        cell = new Cell(new Point (loc.x - Cell.size * 2, loc.y + Cell.size)); // SW1
        if (cell.withinGridBounds() && !occupied.contains(cell)) {
            moves.add(cell);
        } else if (cell.withinGridBounds() && occupied.contains(cell)) {
            for (Piece p : pieces) {
                if (p.loc.equals(cell) && p.teamColour != this.teamColour) {
                    offensiveMoves.add(cell);
                    break;
                }
            }
        }
        cell = new Cell(new Point (loc.x - Cell.size * 2, loc.y - Cell.size)); // NW1
        if (cell.withinGridBounds() && !occupied.contains(cell)) {
            moves.add(cell);
        } else if (cell.withinGridBounds() && occupied.contains(cell)) {
            for (Piece p : pieces) {
                if (p.loc.equals(cell) && p.teamColour != this.teamColour) {
                    offensiveMoves.add(cell);
                    break;
                }
            }
        }
        cell = new Cell(new Point (loc.x - Cell.size, loc.y + Cell.size * 2)); // SW2
        if (cell.withinGridBounds() && !occupied.contains(cell)) {
            moves.add(cell);
        } else if (cell.withinGridBounds() && occupied.contains(cell)) {
            for (Piece p : pieces) {
                if (p.loc.equals(cell) && p.teamColour != this.teamColour) {
                    offensiveMoves.add(cell);
                    break;
                }
            }
        }
        cell = new Cell(new Point (loc.x - Cell.size, loc.y - Cell.size * 2)); // NW2
        if (cell.withinGridBounds() && !occupied.contains(cell)) {
            moves.add(cell);
        } else if (cell.withinGridBounds() && occupied.contains(cell)) {
            for (Piece p : pieces) {
                if (p.loc.equals(cell) && p.teamColour != this.teamColour) {
                    offensiveMoves.add(cell);
                    break;
                }
            }
        }
    }

}
