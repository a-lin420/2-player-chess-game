import java.util.ArrayList;
import java.awt.*;

public abstract class Piece implements Move {
    protected Cell loc;
    protected Color teamColour;
    protected Boolean unmoved;
    protected ArrayList<Polygon> features;

    ArrayList<Cell> moves;
    ArrayList<Cell> offensiveMoves;

    public void paint (Graphics g) {
        this.setFeatures();
        for (Polygon f: features) {
            g.setColor(teamColour);
            g.fillPolygon(f);
            g.setColor(Color.BLACK);
            g.drawPolygon(f);
        }
    }

    public Color getTeamColour() {
        return this.teamColour;
    }

    public Cell getLocation() {
        return this.loc;
    }

    public void setLocation (Cell loc) {
        this.loc = loc;
        setFeatures();
    }

    public abstract void setFeatures ();
    public abstract void setMoves (ArrayList<Cell> occupied, ArrayList<Piece> pieces);

}
