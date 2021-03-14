import java.util.ArrayList;
import java.awt.*;

public abstract class Piece {
    Cell loc;
    Color teamColour;
    ArrayList<Polygon> features;
    Boolean unmoved;

    public abstract void setFeatures ();
    public abstract ArrayList<Cell> setMoves ();

    public void paint (Graphics g) {
        this.setFeatures();
        for (Polygon f: features) {
            g.setColor(teamColour);
            g.fillPolygon(f);
            g.setColor(Color.GRAY);
            g.drawPolygon(f);
        }
    }

    public void setLocation (Cell loc) {
        this.loc = loc;
        setFeatures();
    }

}
