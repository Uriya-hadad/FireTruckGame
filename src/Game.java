import javax.swing.*;


public class Game extends JFrame {

    public Game() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        add(new MainPanel());
        setResizable(false);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
}