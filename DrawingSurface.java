import java.awt.Canvas;
import java.awt.Graphics;
import javax.swing.JFrame;

public class DrawingSurface extends Canvas{
    public static void main(String [] args) {
       JFrame frame = new JFrame("My Drawing");
       Canvas canvas = new DrawingSurface();
       canvas.setSize(400, 400);
       frame.add(canvas);
       frame.pack();
       frame.setVisible(true);

       Plane plane1 = new Plane(100, 100);
    }

    public void paint(Graphics g){
        g.fillOval(100, 100, 200, 200);
    }
}
