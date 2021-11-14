import javax.swing.*;

import processing.awt.PSurfaceAWT;
import processing.core.PApplet;

public class Main{
    public static void main(String [] args){
        DrawingSurface drawing = new DrawingSurface();
		PApplet.runSketch(new String[]{""}, drawing);
		PSurfaceAWT surf = (PSurfaceAWT) drawing.getSurface();
		PSurfaceAWT.SmoothCanvas canvas = (PSurfaceAWT.SmoothCanvas) surf.getNative();
		JFrame window = (JFrame)canvas.getFrame();
        window.setBounds(100, 100, 800, 600);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);
        canvas.requestFocus();
    }


}