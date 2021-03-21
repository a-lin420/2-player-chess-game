import java.util.ArrayList;
import java.awt.*;

public abstract class Piece implements Move {
    Cell loc;
    Color teamColour;
    ArrayList<Polygon> features;
    Boolean unmoved;

    ArrayList<Cell> moves;
    ArrayList<Cell> offensiveMoves;

    public abstract void setFeatures ();
    public abstract void setMoves (ArrayList<Cell> occupied, ArrayList<Piece> pieces);

    public void paint (Graphics g) {
        this.setFeatures();
        for (Polygon f: features) {
            g.setColor(teamColour);
            g.fillPolygon(f);
            g.setColor(Color.BLACK);
            g.drawPolygon(f);
        }
    }

    public void setLocation (Cell loc) {
        this.loc = loc;
        setFeatures();
    }

}
