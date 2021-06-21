import javax.imageio.ImageIO;
import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class LoginScreen extends JPanel {
    private int lastFrame;
    private Image backgroundImage;
    private JButton instructions, play, audioButton, darkMode;
    private boolean isAudioOn = true, isDarkMode = false;
    private Icon img;
    private Clip clip;
    private final String soundName;
    private AudioInputStream audioInputStream;

    public LoginScreen() {
        soundName = "audio/sami the fireman song.wav";
        panelInit();
        add(instructions);
        add(play);
        add(darkMode);
        instructions.addActionListener((e) -> JOptionPane.showMessageDialog(this, """
                                                                  |~~~~~~~~~~~~|
                                                                  |  Instructions    |
                                                                  |~~~~~~~~~~~~|
                You can switch between day and night mode by clicking the "Night Time" button.
                You can turn on and turn off the music by pressing the button with the icon.
                                         
                                         Your mission is:
                                         
                Find the fire and put it out as quickly as possible!
                Don't drive over the fire!
                                           
                                           How to play:
               
                At each stage of the game to get the instructions again press F1.                           
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
                audioInputStream = AudioSystem.getAudioInputStream(new File(soundName).getAbsoluteFile());
                clip = AudioSystem.getClip();
                clip.open(audioInputStream);
                clip.start();
            }catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
                e.printStackTrace();
            }
        }
        instructions = new JButton("Instructions");
        instructions.setBounds(0, 0, 125, 30);
        instructions.setFont(instFont);
        instructions.setBackground(Color.CYAN);

        darkMode = new JButton("Night Time Off");
        darkMode.setBounds(127, 0, 125, 30);
        darkMode.addActionListener(e -> {
            if (!isDarkMode) {
                darkMode.setBackground(new Color(161, 44, 118));
                darkMode.setText("Night Time On");
            } else {
                darkMode.setBackground(null);
                darkMode.setText("Night Time Off");
            }
            isDarkMode = !isDarkMode;
        });
        play = new JButton("Play");
        play.setForeground(Color.WHITE);
        play.setFont(playFont);
        play.setBounds((DataForGame.FrameWeight - 110) / 2, (DataForGame.FrameHeight / 6) * 4, 120, 70);
        play.setBackground(Color.RED);

        img = new ImageIcon("Pngs/audio on.png");
        audioButton = new JButton(img);
        audioButton.setBounds(254,0,30,30);
        add(audioButton);
        audioButton.addActionListener((e) -> {
            isAudioOn = !isAudioOn;
            if (isAudioOn){
                img = new ImageIcon("Pngs/audio on.png");
                audioButton.setIcon(img);
                try {
                    audioInputStream = AudioSystem.getAudioInputStream(new File(soundName).getAbsoluteFile());
                    clip = AudioSystem.getClip();
                    clip.open(audioInputStream);
                    if (lastFrame !=0){
                        clip.setFramePosition(lastFrame);
                    }
                    clip.start();
                } catch (UnsupportedAudioFileException | LineUnavailableException | IOException unsupportedAudioFileException) {
                    unsupportedAudioFileException.printStackTrace();
                }
            }else{
                img = new ImageIcon("Pngs/audio off.png");
                audioButton.setIcon(img);
                lastFrame = clip.getFramePosition();
                clip.close();
            }
        });

    }

    public JButton getPlay() {
        return play;
    }

    public Clip getClip() {
        return clip;
    }
    public boolean isDarkMode() {
        return isDarkMode;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, DataForGame.FrameWeight, DataForGame.FrameHeight, this);

    }
}


