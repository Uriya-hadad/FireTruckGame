import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Fire extends JLabel {
    private final Rectangle location;

    public Fire() {
        location = new Rectangle();
        Icon upIcon = new ImageIcon("Gifs/fire.gif");
        setIcon(upIcon);
        setVisible(false);
    }
    void place(FireTruck truck){
        Random random = new Random();
        int x_position = random.nextInt(DataForGame.FrameWeight - 60);
        int y_position = random.nextInt(DataForGame.FrameHeight - 80) + 20;
        setBounds(x_position, y_position, 60, 60);
        location.setBounds(x_position + 10, y_position + 10, 40, 50);
        if (location.intersects(truck.getLocations())) {
            place(truck);
        }
        setVisible(true);
    }

    public Rectangle getLocations() {
        return location;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
    }

}
