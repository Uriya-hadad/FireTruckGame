import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class FireTruck extends Component {
    private final File fileUp, fileDown, fileLeft, fileRight;
    private int position = 1;
    private Image image;
    private int X_POSITION = DimensionGame.FrameWeight / 2;
    private int Y_POSITION = DimensionGame.FrameHeight / 2;


    public FireTruck() {
        fileUp = new File("fireTruckUp.png");
        fileDown = new File("fireTruckDown.png");
        fileLeft = new File("fireTruckLeft.png");
        fileRight = new File("fireTruckRight.png");

        try {
            image = ImageIO.read(fileUp);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public int getX_POSITION() {
        return X_POSITION;
    }

    public void setX_POSITION(int x_POSITION) {
        X_POSITION = x_POSITION;
    }

    public int getY_POSITION() {
        return Y_POSITION;
    }

    public void setY_POSITION(int y_POSITION) {
        Y_POSITION = y_POSITION;
    }

    public int getPosition() {
        return position;
    }

    public void paint(Graphics g) {
        g.drawImage(image, X_POSITION, Y_POSITION, this);


    }

    public void moveUp() {
        if (position != 1) {
            try {
                position = 1;
                image = ImageIO.read(fileUp);
            } catch (IOException e) {
                System.out.println("file doesn't existed!");
            }
        } else if (Y_POSITION >= 5) {
            setY_POSITION(getY_POSITION() - 5);
        }
    }

    public void moveRight() {
        if (position != 2)
            try {
                position = 2;
                image = ImageIO.read(fileRight);
            } catch (IOException e) {
                System.out.println("file doesn't existed!");
            }
        else if (X_POSITION <= DimensionGame.FrameWeight - DimensionGame.ImageSize - 5) {
            setX_POSITION(getX_POSITION() + 5);
        }
    }

    public void moveDown() {
        if (position != 3)
            try {
                position = 3;
                image = ImageIO.read(fileDown);
            } catch (IOException e) {
                System.out.println("file doesn't existed!");
            }
        else if (Y_POSITION <= DimensionGame.FrameHeight - DimensionGame.ImageSize - 5) {
            setY_POSITION(getY_POSITION() + 5);
        }
    }

    public void moveLeft() {
        if (position != 4)
            try {
                position = 4;
                image = ImageIO.read(fileLeft);
            } catch (IOException e) {
                System.out.println("file doesn't existed!");
            }
        else if (X_POSITION >= 5) {
            setX_POSITION(getX_POSITION() - 5);
        }
    }


}
