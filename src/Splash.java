import javax.swing.*;

public class Splash extends JLabel {
    int x_splash, y_splash;
    Icon upIcon,downIcon,rightIcon,leftIcon;
    public Splash() {
        upIcon = new ImageIcon("SplashUp.gif");
        downIcon = new ImageIcon("SplashDown.gif");
        rightIcon = new ImageIcon("SplashRight.gif");
        leftIcon = new ImageIcon("SplashLeft.gif");
        setVisible(false);

    }

    public void displayWater(int xTruck,int yTruck,int positionTruck) {
        x_splash = xTruck;
        y_splash = yTruck;
        switch (positionTruck) {
            case 1 -> {
                setIcon(upIcon);
                setBounds(x_splash, y_splash -DimensionGame.SplashSize, 57, 57);
            }
            case 2 -> {
                setIcon(rightIcon);
                setBounds(x_splash + DimensionGame.SplashSize, y_splash, 57, 57);
            }
            case 3 -> {
                setIcon(downIcon);
                setBounds(x_splash, y_splash + DimensionGame.SplashSize, 57, 57);
            }
            case 4 -> {
                setIcon(leftIcon);
                setBounds(x_splash -DimensionGame.SplashSize, y_splash, 57, 57);
            }
        }
    }
}
