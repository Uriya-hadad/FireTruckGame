import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class FireTruck extends Component {
    private final File fileUp, fileDown, fileLeft, fileRight;
    private int position = 1;
    private Image image;
    private int X_POSITION = DataForGame.FrameWeight / 2;
    private int Y_POSITION = DataForGame.FrameHeight / 2;


    public FireTruck(MainPanel parent) {
        fileUp = new File("Pngs/fireTruckUp.png");
        fileDown = new File("Pngs/fireTruckDown.png");
        fileLeft = new File("Pngs/fireTruckLeft.png");
        fileRight = new File("Pngs/fireTruckRight.png");

        try {
            image = ImageIO.read(fileUp);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(parent,DataForGame.failMessage);
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

    public void moveUp() throws IOException {
        if (position != 1) {
            position = 1;
            image = ImageIO.read(fileUp);
        } else if (Y_POSITION >= 5) {
            setY_POSITION(getY_POSITION() - 5);
        }
    }

    public void moveRight() throws IOException {
        if (position != 2) {
            position = 2;
            image = ImageIO.read(fileRight);
        }
        else if (X_POSITION <= DataForGame.FrameWeight - DataForGame.ImageSize - 5) {
            setX_POSITION(getX_POSITION() + 5);
        }
    }

    public void moveDown() throws IOException {
        if (position != 3)
            {
                position = 3;
                image = ImageIO.read(fileDown);
            }
        else if (Y_POSITION <= DataForGame.FrameHeight - DataForGame.ImageSize - 5) {
            setY_POSITION(getY_POSITION() + 5);
        }
    }

    public void moveLeft() throws IOException {
        if (position != 4)
           {
                position = 4;
                image = ImageIO.read(fileLeft);
            }
        else if (X_POSITION >= 5) {
            setX_POSITION(getX_POSITION() - 5);
        }
    }


}
