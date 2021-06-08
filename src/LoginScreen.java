import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class LoginScreen extends JPanel {
    private Image backgroundImage;
    private JButton instructions, play;

    public LoginScreen() {
        panelInit();
        add(instructions);
        add(play);
        instructions.addActionListener((e) -> JOptionPane.showMessageDialog(this, """
                                  |~~~~~~~~~~~~|
                                  |  Instructions:   |
                                  |~~~~~~~~~~~~|
            Your mission is:
            Find the fire and put it out as quickly as possible!
            Don't drive over the fire!
            How to play:
            Use the arrow keys to move left right up and down.
            Use the space bar to spray water and put out the fire."""));
    }


    public void panelInit() {
        setLayout(null);
        setPreferredSize(new Dimension(DataForGame.FrameWeight,
                DataForGame.FrameHeight));
        Font playFont = new Font("Ariel", Font.BOLD, 40);
        Font instFont = new Font("Ariel", Font.BOLD, 15);

        {
            try {
                backgroundImage = ImageIO.read(new File("Pngs/backGround.jpg"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        instructions = new JButton("Instructions");
        instructions.setBounds(0, 0, 140, 40);
        instructions.setFont(instFont);
        instructions.setBackground(Color.CYAN);

        play = new JButton("Play");
        play.setForeground(Color.WHITE);
        play.setFont(playFont);
        play.setBounds((DataForGame.FrameWeight - 110) / 2, (DataForGame.FrameHeight / 6) * 4, 120, 70);
        play.setBackground(Color.RED);
    }
    public JButton getPlay() {
        return play;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, DataForGame.FrameWeight, DataForGame.FrameHeight, this);
    }
}


