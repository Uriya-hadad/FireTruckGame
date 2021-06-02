import javax.swing.*;
import java.awt.*;

public class Splash extends JLabel {
    int x_splash, y_splash;
    Icon upIcon, downIcon, rightIcon, leftIcon;
    Rectangle location;

    public Splash() {
        upIcon = new ImageIcon("Gifs/SplashUp.gif");
        downIcon = new ImageIcon("Gifs/SplashDown.gif");
        rightIcon = new ImageIcon("Gifs/SplashRight.gif");
        leftIcon = new ImageIcon("Gifs/SplashLeft.gif");
        setVisible(false);

    }

    public void displayWater(int xTruck, int yTruck, int positionTruck) {
        x_splash = xTruck;
        y_splash = yTruck;
        location = new Rectangle();
        switch (positionTruck) {
            case 1 -> {
                setIcon(upIcon);
                setBounds(x_splash, y_splash - DataForGame.SplashSize, 57, 57);
                location.setBounds(x_splash+17, y_splash - DataForGame.SplashSize, 25, 25);
            }
            case 2 -> {
                setIcon(rightIcon);
                setBounds(x_splash + DataForGame.SplashSize, y_splash, 57, 57);
                location.setBounds(x_splash + DataForGame.SplashSize+30, y_splash+37, 28, 20);
            }
            case 3 -> {
                setIcon(downIcon);
                setBounds(x_splash, y_splash + DataForGame.SplashSize, 57, 57);
                location.setBounds(x_splash+10, y_splash+35 + DataForGame.SplashSize, 25, 25);
            }
            case 4 -> {
                setIcon(leftIcon);
                setBounds(x_splash - DataForGame.SplashSize, y_splash, 57, 57);
                location.setBounds(x_splash - DataForGame.SplashSize, y_splash+30, 28, 25);
            }
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        }
}
