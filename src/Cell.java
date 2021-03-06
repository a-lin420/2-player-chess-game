import java.awt.*;

public class Cell extends Rectangle {
    public Color colour;
    public static int size = 75;

    public Cell (Point p) {
        super(p, new Dimension(size, size));
    }

    public Cell(Point p, Color c) {
        super(p, new Dimension(size, size));
        colour = c;
    }

    void paint(Graphics g, Point mousePos) {
        if (contains(mousePos)) {
            g.setColor(Color.ORANGE);
        } else {
            g.setColor(colour);
        }
        g.fillRect(x, y, size, size);
        g.setColor(Color.BLACK);
        g.drawRect(x, y, size, size);
    }

    public boolean contains(Point mousePos) {
        if (mousePos != null) {
            return super.contains(mousePos);
        } else {
            return false;
        }
    }

    
    public Boolean withinGridBounds() {
        if (getX() >= 0 && getX() < Grid.gridSpan && getY() >= 0 && getY() < Grid.gridSpan) {
            return true;
        } 
        return false;
    }
}
