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
    private final FireTruck truck;
    private Splash splash;
    private final Fire fire;
    private final Timer timer;
    private Image backgroundImage, cryingFace;
    private boolean canSpray = true;
    private int score = 0;
    private int time;
    private int takeCurrentTime;
    private int timeLeft;
    private boolean exit = false;

    public MainPanel( boolean darkMode) {
        setLayout(null);
        timer = new Timer(1000, this);
        timer.start();
        setPreferredSize(new Dimension(DataForGame.FrameWeight,
                DataForGame.FrameHeight));
        truck = new FireTruck(this, darkMode);
        fire = new Fire();
        add(truck);
        add(fire);
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
        try {
            if (darkMode) {
                backgroundImage = ImageIO.read(new File("Pngs/grassNight.png"));
            } else {
                backgroundImage = ImageIO.read(new File("Pngs/grassDay.png"));
            }
            cryingFace = ImageIO.read(new File("Pngs/sadFace.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

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
            if (!exit) {
                waterIntersectsFire();
            }
            splash.setVisible(false);
            repaint();
            canSpray = true;
        });
        thread.start();
    }

    private void waterIntersectsFire() {
        if (splash.getLocations().intersects(fire.getLocations())) {
            score += 1;
            fire.setVisible(false);
            timeLeft = 0;
            takeCurrentTime = -1;
            repaint();
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (timeLeft == -1 ||
                (truck.getLocations().intersects(fire.getLocations()) && fire.isVisible())) {
            removeAll();
            exit = true;
            g.clearRect(0, 0, getWidth(), getHeight());
            g.setColor(Color.MAGENTA);
            g.fillRect(0, 0, DataForGame.FrameWeight, DataForGame.FrameHeight);
            g.drawImage(cryingFace, (DataForGame.FrameWeight / 2) - 50, 30, 120, 120, this);
            g.setFont(new Font("arial", Font.BOLD + Font.ITALIC, 65));
            g.setColor(Color.RED);
            g.drawString("Game over", 230, (DataForGame.FrameHeight / 2) - 30);
            g.setColor(Color.BLUE);
            g.setFont(new Font("arial", Font.BOLD, 30));
            g.drawString("Your score is: " + score, (DataForGame.FrameWeight / 2) - 110, (DataForGame.FrameHeight / 2) + 20);
            g.setFont(new Font("Ink Free", Font.ITALIC, 25));
            g.drawString("Did you enjoy the game?  Feel free to play again!", 150, (DataForGame.FrameHeight / 2) + 120);
            repaint();
            timer.stop();
        } else {
            g.setColor(new Color(49, 127, 49));
            g.fillRect(0, 0, DataForGame.FrameWeight, 25);
            g.setColor(Color.BLACK);
            g.setFont(new Font("MV Boli", Font.BOLD, 17));
            g.drawImage(backgroundImage, 0, 25, DataForGame.FrameWeight, DataForGame.FrameHeight, this);
            g.drawString("Your score is: " + score, DataForGame.FrameWeight / 2, 20);
            g.drawString("your time left is: " + timeLeft, 10, 20);
            truck.paintComponent(g);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        time++;
        if (takeCurrentTime == -1)
            takeCurrentTime = time;
        else if (time - takeCurrentTime == 5) {
            fire.place(truck, this);
            timeLeft = 10;
        } else if (fire.isVisible())
            timeLeft--;
        repaint();


    }

}
