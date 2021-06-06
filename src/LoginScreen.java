import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class LoginScreen extends JPanel {

    public JButton getPlay() {
        return play;
    }

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
            Keys:
            Use the ARROW KEYS to move left right up and down.
            Use the SPACEBAR to spray water and put out the fire."""));
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
        instructions.setBounds(0, 0, 120, 40);
        instructions.setFont(instFont);
        instructions.setBackground(Color.CYAN);
        instructions.setFocusCycleRoot(true);


        play = new JButton("Play");
        play.setFont(playFont);
        play.setBounds((DataForGame.FrameWeight - 110) / 2, (DataForGame.FrameHeight / 6) * 4, 120, 70);
        play.setBackground(Color.RED);
        play.setFocusCycleRoot(true);
//        play.addActionListener(e->{
//            removeAll();
//            setVisible(false);
//        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, DataForGame.FrameWeight, DataForGame.FrameHeight, null);
    }
}


