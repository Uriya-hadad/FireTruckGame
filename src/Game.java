import javax.swing.*;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Game extends JFrame implements ActionListener {
    LoginScreen firstScreen;
    public Game() {
        setTitle("FireTruck Game");
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
        MainPanel panel = new MainPanel();
        remove(firstScreen);
        add(panel);
        panel.requestFocus();
        getContentPane().invalidate();
        getContentPane().validate();
    }
}