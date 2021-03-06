import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class Fire extends JLabel {
    private final Rectangle location;

    public Fire() {
        location = new Rectangle();
        Icon upIcon = new ImageIcon("Gifs/fire.gif");
        setIcon(upIcon);
        setVisible(false);
    }

    void place(FireTruck truck,MainPanel parent) {
        Random random = new Random();
        int x_position = random.nextInt(DataForGame.Fire_X_position);
        int y_position = random.nextInt(DataForGame.Fire_Y_position) + 20;
        setBounds(x_position, y_position, DataForGame.FireBoundsWidth, DataForGame.FireBoundsHeight);
        location.setBounds(x_position + 10, y_position + 10, DataForGame.locationBoundsWidth, DataForGame.locationBoundsHeight);
        if (location.intersects(truck.getLocations())) {
            place(truck,parent);
        }
        String soundName = "audio/alarmSound.wav";
        AudioInputStream audioInputStream;
        try {
            audioInputStream = AudioSystem.getAudioInputStream(new File(soundName).getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (UnsupportedAudioFileException | LineUnavailableException e) {
            e.printStackTrace();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(parent, DataForGame.failMessage);
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
