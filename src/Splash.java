import javax.swing.*;

public class Splash extends JLabel {
    int x_splash, y_splash;
    Icon upIcon,downIcon,rightIcon,leftIcon;
    public Splash() {
        upIcon = new ImageIcon("Gifs/SplashUp.gif");
        downIcon = new ImageIcon("Gifs/SplashDown.gif");
        rightIcon = new ImageIcon("Gifs/SplashRight.gif");
        leftIcon = new ImageIcon("Gifs/SplashLeft.gif");
        setVisible(false);

    }

    public void displayWater(int xTruck,int yTruck,int positionTruck) {
        x_splash = xTruck;
        y_splash = yTruck;
        switch (positionTruck) {
            case 1 -> {
                setIcon(upIcon);
                setBounds(x_splash, y_splash - DataForGame.SplashSize, 57, 57);
            }
            case 2 -> {
                setIcon(rightIcon);
                setBounds(x_splash + DataForGame.SplashSize, y_splash, 57, 57);
            }
            case 3 -> {
                setIcon(downIcon);
                setBounds(x_splash, y_splash + DataForGame.SplashSize, 57, 57);
            }
            case 4 -> {
                setIcon(leftIcon);
                setBounds(x_splash - DataForGame.SplashSize, y_splash, 57, 57);
            }
        }
    }
}
