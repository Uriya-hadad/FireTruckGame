import javax.swing.*;
import java.awt.*;

public class Splash extends JLabel {
    private final Icon upIcon, downIcon, rightIcon, leftIcon;
    private Rectangle location;

    public Splash() {
        upIcon = new ImageIcon("Gifs/SplashUp.gif");
        downIcon = new ImageIcon("Gifs/SplashDown.gif");
        rightIcon = new ImageIcon("Gifs/SplashRight.gif");
        leftIcon = new ImageIcon("Gifs/SplashLeft.gif");
        setVisible(false);

    }

    public void displayWater(int xTruck, int yTruck, int positionTruck) {
        location = new Rectangle();
        switch (positionTruck) {
            case 1 -> {
                setIcon(upIcon);
                setBounds(xTruck, yTruck - DataForGame.SplashSize, 57, 57);
                location.setBounds(xTruck +17, yTruck - DataForGame.SplashSize, 25, 25);
            }
            case 2 -> {
                setIcon(rightIcon);
                setBounds(xTruck + DataForGame.SplashSize, yTruck, 57, 57);
                location.setBounds(xTruck + DataForGame.SplashSize+30, yTruck +37, 28, 20);
            }
            case 3 -> {
                setIcon(downIcon);
                setBounds(xTruck, yTruck + DataForGame.SplashSize, 57, 57);
                location.setBounds(xTruck +10, yTruck +35 + DataForGame.SplashSize, 25, 25);
            }
            case 4 -> {
                setIcon(leftIcon);
                setBounds(xTruck - DataForGame.SplashSize, yTruck, 57, 57);
                location.setBounds(xTruck - DataForGame.SplashSize, yTruck +30, 28, 25);
            }
        }
    }

    public Rectangle getLocations() {
        return location;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        }
}
