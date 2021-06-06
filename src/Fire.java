import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Fire extends JLabel {
    int x_position, y_position;
    private Rectangle location;

    public Fire() {
        Icon upIcon = new ImageIcon("Gifs/fire.gif");
        setIcon(upIcon);
        setVisible(false);
    }
    void place(FireTruck truck){
        Random random = new Random();
        x_position = random.nextInt(DataForGame.FrameWeight - 60);
        y_position = random.nextInt(DataForGame.FrameHeight - 80)+20;
        location = new Rectangle();
        setBounds(x_position, y_position, 60, 60);
        location.setBounds(x_position + 10, y_position + 10, 40, 50);
        if (location.intersects(truck.getLocations()))
            place(truck);
    }

    public Rectangle getLocations() {
        return location;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
    }

}
