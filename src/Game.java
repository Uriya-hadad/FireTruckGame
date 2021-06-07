import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Game extends JFrame implements ActionListener {
    LoginScreen firstScreen;
    ImageIcon icon = new ImageIcon("Pngs/icon.jpg");
    public Game() {
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

    public static void main(String[] args) {
        new Game();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        MainPanel panel = new MainPanel();
        remove(firstScreen);
        add(panel);
        panel.requestFocus();
        getContentPane().invalidate();
        getContentPane().validate();
    }
}