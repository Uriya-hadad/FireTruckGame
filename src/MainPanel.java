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
    private int score = 0,time,takeCurrentTime,timeLeft;
    private boolean exit = false;
    private final JButton restartGame;

    public MainPanel(boolean darkMode) {
        setLayout(null);
        setPreferredSize(new Dimension(DataForGame.FrameWeight,
                DataForGame.FrameHeight));
        restartGame = new JButton("play again");
        timer = new Timer(1000, this);
        truck = new FireTruck(this, darkMode);
        fire = new Fire();
        startGame();
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_RIGHT -> truck.moveRight();
                    case KeyEvent.VK_LEFT -> truck.moveLeft();
                    case KeyEvent.VK_UP -> truck.moveUp();
                    case KeyEvent.VK_DOWN -> truck.moveDown();
                    case KeyEvent.VK_F1 -> {
                        timer.stop();
                        LoginScreen.showInstruction();
                        timer.start();
                    }
                    case KeyEvent.VK_SPACE -> {
                        if (canSpray) splashDisplay();
                    }
                }
                repaint();
            }
        });
        try {
            cryingFace = ImageIO.read(new File("Pngs/sadFace.png"));
            if (darkMode) {
                backgroundImage = ImageIO.read(new File("Pngs/grassNight.png"));
            } else {
                backgroundImage = ImageIO.read(new File("Pngs/grassDay.png"));
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(MainPanel.this, DataForGame.failMessage);
        }

    }

    private void startGame() {
        setFocusable(true);
        timer.start();
        takeCurrentTime = -1;
        timeLeft = 0;
        exit = false;
        add(truck);
        add(fire);
        Font restartFont = new Font("Ariel", Font.BOLD, 33);
        restartGame.setForeground(Color.WHITE);
        restartGame.setFont(restartFont);
        restartGame.setBounds((DataForGame.FrameWeight - 200) / 2, (DataForGame.FrameHeight / 2) + 155, 200, 50);
        restartGame.setBackground(Color.BLUE);
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
            gameOver(g);
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

    private void gameOver(Graphics g) {
        repaint();
        remove(truck);
        remove(fire);
        exit = true;
        g.clearRect(0, 0, getWidth(), getHeight());
        g.setColor(Color.MAGENTA);
        g.fillRect(0, 0, DataForGame.FrameWeight, DataForGame.FrameHeight);
        g.drawImage(cryingFace, (DataForGame.FrameWeight / 2) - 50, 40, 120, 120, this);
        g.setFont(new Font("arial", Font.BOLD + Font.ITALIC, 65));
        g.setColor(Color.RED);
        g.drawString("Game over", 230, (DataForGame.FrameHeight / 2) - 30);
        g.setColor(Color.BLUE);
        g.setFont(new Font("arial", Font.BOLD, 30));
        g.drawString("Your score is: " + score, (DataForGame.FrameWeight / 2) - 110, (DataForGame.FrameHeight / 2) + 30);
        g.setFont(new Font("Ink Free", Font.ITALIC, 25));
        g.drawString("Did you enjoy the game?  Feel free to play again!", 150, (DataForGame.FrameHeight / 2) + 130);
        timer.stop();
        add(restartGame);
        restartGame.addActionListener(e -> {
            exit = false;
            timer.start();
            fire.setVisible(false);
            remove(restartGame);
            repaint();
            startGame();
            score = 0;
        });
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
