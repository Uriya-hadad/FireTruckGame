import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Game extends JFrame implements ActionListener {
    private final LoginScreen firstScreen;
    private final String selectedValue;

    public Game(String selectedValue) {
        ImageIcon icon = new ImageIcon("Pngs/icon.jpg");
        this.selectedValue = selectedValue;
        setTitle("FireTruck Game");
        setIconImage(icon.getImage());
        firstScreen = new LoginScreen();
        firstScreen.getPlay().addActionListener(this);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        add(firstScreen);
        setResizable(false);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        remove(firstScreen);
        firstScreen.getClip().stop();
        JOptionPane.showMessageDialog(this,
                """
                        Hello\040""" + selectedValue + """
                         we have all been waiting for you!
                        The city needs a good firefighter
                         we hope you can help us!
                        """, "Message from the city",JOptionPane.INFORMATION_MESSAGE);
        MainPanel panel = new MainPanel(firstScreen.isDarkMode());
        add(panel);
        panel.requestFocus();
        getContentPane().invalidate();
        getContentPane().validate();
    }
}