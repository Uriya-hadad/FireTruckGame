import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class Main {

    public static void main(String[] args) {
//        new Game();
        JFrame jFrame = new JFrame();
        jFrame.getContentPane().setLayout(new FlowLayout());
        a v =new a();
        jFrame.add(v);
        JButton button = new JButton();
        button.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                v.check();
            }
        });
        jFrame.add(button);
        jFrame.pack();
        jFrame.setVisible(true);
    }
}
