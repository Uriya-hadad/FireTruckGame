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
        instructions.addActionListener((e) -> showInstruction());

    }

    public void panelInit() {

        setLayout(null);
        setPreferredSize(new Dimension(DataForGame.FrameWeight,
                DataForGame.FrameHeight));
        Font playFont = new Font("Ariel", Font.BOLD, DataForGame.playFontSize);
        Font instFont = new Font("Ariel", Font.BOLD, DataForGame.instFontSize);

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
        instructions.setBounds(DataForGame.instructions_X, DataForGame.instructions_Y, DataForGame.instructionsWeight, DataForGame.instructionsHeight);
        instructions.setFont(instFont);
        instructions.setBackground(Color.CYAN);

        darkMode = new JButton("Night Time Off");
        darkMode.setBounds(DataForGame.darkMode_X, DataForGame.darkMode_Y, DataForGame.darkModeWeight, DataForGame.darkModeHeight);
        darkMode.addActionListener(e -> {
            if (!isDarkMode) {
                darkMode.setBackground(DataForGame.darkModeColor);
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
        play.setBounds(DataForGame.play_X, DataForGame.play_Y, DataForGame.playWeight, DataForGame.playHeight);
        play.setBackground(Color.RED);

        img = new ImageIcon("Pngs/audio on.png");
        audioButton = new JButton(img);
        audioButton.setBounds(DataForGame.audioButton_X,DataForGame.audioButton_Y,DataForGame.audioButtonWeight,DataForGame.audioButtonHeight);
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
        g.drawImage(backgroundImage, DataForGame.backgroundImage_X, DataForGame.backgroundImage_Y, DataForGame.FrameWeight, DataForGame.FrameHeight, this);

    }
    static public void showInstruction() {
        JOptionPane.showMessageDialog(null, """
                                                                  |~~~~~~~~~~~~|
                                                                  |  Instructions    |
                                                                  |~~~~~~~~~~~~|
                During the game you can watch this message again by pressing F1.
                You can switch between day and night mode by clicking the "Night Time" button.
                You can turn on and turn off the music by pressing the button with the icon.
                                         
                                         Your mission is:
                                         
                Find the fire and put it out as quickly as possible!
                Don't drive over the fire!
                                           
                                           How to play:
                                           
                Use the arrow keys to move left right up and down.
                Use the space bar to spray water and put out the fire.""");
    }
}


