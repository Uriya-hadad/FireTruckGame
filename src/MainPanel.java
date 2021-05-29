import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


public class MainPanel extends JPanel {
    FireTruck truck;

    public MainPanel() {
        setLayout(null);
        setPreferredSize(new Dimension(DimensionGame.FrameWeight,
                DimensionGame.FrameHeight));
        setFocusable(true);
        truck = new FireTruck();
        add(truck);
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_RIGHT -> truck.moveRight();
                    case KeyEvent.VK_LEFT -> truck.moveLeft();
                    case KeyEvent.VK_UP -> truck.moveUp();
                    case KeyEvent.VK_DOWN -> truck.moveDown();
                    case KeyEvent.VK_SPACE -> splashDisplay();
                }
                repaint();
            }
        });
    }

    private void splashDisplay() {
        Splash splash = new Splash();
        splash.displayWater(truck.getX_POSITION(), truck.getY_POSITION(), truck.getPosition());
        add(splash);
        Thread thread = new Thread(() -> {
            splash.setVisible(true);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            splash.setVisible(false);
            repaint();
        });
        thread.start();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        truck.paint(g);
    }
}
