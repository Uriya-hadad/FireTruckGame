import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;


public class MainPanel extends JPanel implements ActionListener {
    FireTruck truck;
    Splash splash;
    Fire fire;
    Timer timer;
    Image backgroundImage;
    private boolean canSpray = true;
    private int score = 0;
    private int time;
    private boolean placeFire;
    private int takeCurrentTime;
    private int timeLeft = 0;

    public MainPanel() {
        setLayout(null);
        timer = new Timer(1000, this);
        timer.start();
        setPreferredSize(new Dimension(DataForGame.FrameWeight,
                DataForGame.FrameHeight));
        truck = new FireTruck(this);
        fire = new Fire();
        add(truck);
        add(fire);
        replaceFire();
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                try {
                    switch (e.getKeyCode()) {
                        case KeyEvent.VK_RIGHT -> truck.moveRight();
                        case KeyEvent.VK_LEFT -> truck.moveLeft();
                        case KeyEvent.VK_UP -> truck.moveUp();
                        case KeyEvent.VK_DOWN -> truck.moveDown();
                        case KeyEvent.VK_SPACE -> {
                            if (canSpray) splashDisplay();
                        }
                    }
                } catch (IOException err) {
                    JOptionPane.showMessageDialog(MainPanel.this, DataForGame.failMessage);
                }
                repaint();
            }
        });

        {
            try {
                backgroundImage = ImageIO.read(new File("Pngs/Grass image.jpg"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void replaceFire() {
        fire.place();
        placeFire = true;
    }

    private void splashDisplay() {

        splash = new Splash();
        splash.displayWater(truck.getX_POSITION(), truck.getY_POSITION(), truck.getPosition());
        add(splash);
        Thread thread = new Thread(() -> {
            canSpray = false;
            splash.setVisible(true);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            waterIntersectsFire();
            splash.setVisible(false);
            repaint();
            canSpray = true;
        });
        thread.start();
    }

    private void waterIntersectsFire() {
        if (splash.location.intersects(fire.location)) {
            score += 1;
            fire.setVisible(false);
            timeLeft = 0;
            replaceFire();
            repaint();
        } else
            System.out.println("no!");

    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setFont(new Font("Arial", Font.BOLD, 17));
        g.drawImage(backgroundImage, 0, 0, DataForGame.FrameWeight, DataForGame.FrameHeight, this);
        g.drawString("Your score is: " + score, DataForGame.FrameWeight / 2, 20);
        g.drawString("your time left is: " + timeLeft, 10, 20);
        truck.paint(g);
    }
//TODO fix the timeLEFT do gameover
    @Override
    public void actionPerformed(ActionEvent e) {
        time++;
        if (placeFire && takeCurrentTime == -1)
            takeCurrentTime = time;
        else if (placeFire && time - takeCurrentTime == 5) {
            fire.setVisible(true);
            timeLeft = 10;
            takeCurrentTime = -1;
            placeFire = false;
        } else if (fire.isVisible())
            timeLeft--;
        repaint();


    }

}
