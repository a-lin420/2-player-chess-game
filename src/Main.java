import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.time.Duration;
import java.time.Instant;

public class Main extends JFrame {

     class App extends JPanel implements MouseListener {
        Board board;
        // Canvas constructor
        public App () {
            setPreferredSize(new Dimension(980, 720));
            this.addMouseListener(this);
            board = new Board(new Point(10, 10));
        }

        // Display Canvas
        @Override
        public void paint(Graphics g) {
            board.drawBoard(g, getMousePosition());
        }
        @Override
        public void mouseClicked(MouseEvent e) {
            board.mouseClicked(e.getX(), e.getY());
        }
        @Override
        public void mousePressed(MouseEvent e) {}

        @Override
        public void mouseReleased(MouseEvent e) {}

        @Override
        public void mouseEntered(MouseEvent e) {}

        @Override
        public void mouseExited(MouseEvent e) {}

    }

    public static void main(String[] args) throws Exception {
        Main window = new Main();
        window.run();
    }

    private Main() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        App canvas = new App();
        this.setContentPane(canvas);
        this.pack();
        this.setVisible(true);
    }
    public void run() {
        while (true) {
            Instant startTime = Instant.now();
            this.repaint();
            Instant endTime = Instant.now();
            long howLong = Duration.between(startTime, endTime).toMillis();
            try{
                Thread.sleep(20l - howLong);
            } catch (InterruptedException e){
                System.out.println("thread was interrupted, but who cares?");
            } catch (IllegalArgumentException e){
                System.out.println("application can't keep up with framerate");
            }
        }
    }
}