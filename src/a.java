import javax.swing.*;
import java.awt.*;

public class a extends JPanel {
    Rectangle a,b;
    public a() {
        setPreferredSize(new Dimension(300,300));

    }

    @Override
    public void paint(Graphics g) {
        super.paintComponent(g);
        a =new Rectangle(49,49,50,50);
        b =new Rectangle(90,90,50,50);
        g.drawRect(49,49,50,50);
        g.drawRect(90,90,50,50);
    }

    public void check() {
        if (a.intersects(b)){
            System.out.println("yes");
            return;
        }
            System.out.println("no");

    }
}
