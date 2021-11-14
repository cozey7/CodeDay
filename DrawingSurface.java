import processing.core.PApplet;
import processing.core.PImage;

public class DrawingSurface extends PApplet {

    PImage plane;
    private Plane bluePlane;
    private boolean p1Fly, p2Fly;

    public void setup(){
        plane = loadImage("plane.png");
        bluePlane = new Plane(plane, 100, 100, 50, 50);
        bluePlane.setVx(-4);
    }
    public DrawingSurface(){
        
    }

    public void draw() {
        background(130, 199, 255);
        bluePlane.draw(this);
    }
    public void settings() {
        size(800, 600);
    }
    public void keyPressed() {
        if (key == 'w' || key == 'W')
            bluePlane.setFlying(true);
        else if (keyCode == UP);
    }
    public void keyReleased() {
        if (key == 'w' || key == 'W')
            bluePlane.setFlying(false);
        else if (keyCode == UP);
    }
}