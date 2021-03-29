import java.awt.Graphics;
import java.awt.Point;
import java.awt.Color;
import java.util.*;
import java.util.function.Consumer;

public class Grid implements Iterable<Cell> {
    public Cell[][] cells = new Cell[8][8];
    public static final int gridSpan = Cell.size * 8;

    public Grid (Point p) {
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                if (((i % 2 == 0) && (j % 2 == 0)) || ((i % 2 != 0) && (j % 2 != 0))) {
                    cells[i][j] = new Cell (new Point (p.x+75*i, p.y+75*j), Color.WHITE);
                } else {
                    cells[i][j] = new Cell (new Point (p.x+75*i, p.y+75*j), Color.BLACK);
                }
            }
        }
    }
    public void paint(Graphics g, Point mousePos){
        for(int i = 0; i < cells.length; i++){
            for(int j = 0; j < cells[i].length; j++){
                cells[i][j].paint(g, mousePos);
            }
        }
    }

    public Optional<Cell> cellAtPoint(Point p) {
        for(Cell c: this){
            if (c.contains(p)){
                return Optional.of(c);
            }
        }

        return Optional.empty();
    }

        /**
     * Takes a cell consumer (i.e. a function that has a single `Cell` argument and
     * returns `void`) and applies that consumer to each cell in the grid.
     * @param func The `Cell` to `void` function to apply at each spot.
     */
    public void doToEachCell(Consumer<Cell> func) {
        for(Cell c : this){
            func.accept(c);
        }
    }

    public void paintOverlay(Graphics g, List<Cell> cells, Color colour){
        g.setColor(colour);
        for(Cell c: cells){
            g.fillRect(c.x+2, c.y+2, c.width-4, c.height-4);
        }
    }

    @Override
    public CellIterator iterator(){
        return new CellIterator(cells);
    }
}
