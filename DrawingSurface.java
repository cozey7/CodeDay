import processing.core.PApplet;
import processing.core.PImage;

public class DrawingSurface extends PApplet {

    PImage plane;
    private Plane bluePlane, redPlane;

    public void setup(){
        plane = loadImage("plane.png");
        bluePlane = new Plane(plane, 100, 100, 50, 50);
        bluePlane.setVx(2);
        redPlane = new Plane(plane, 600, 100, 50, 50);
        redPlane.setVx(-2);
    }
    public DrawingSurface(){
        
    }

    public void draw() {
        background(130, 199, 255);
        bluePlane.draw(this);
        redPlane.draw(this);
        bluePlane.collide(redPlane);
        redPlane.collide(bluePlane);
    }
    public void settings() {
        size(800, 600);
    }
    public void keyPressed() {
        if ((key == 'w' || key == 'W') && !bluePlane.isCrashing())
            bluePlane.setFlying(true);
        else if (keyCode == UP && !redPlane.isCrashing())
            redPlane.setFlying(true);
    }
    public void keyReleased() {
        if ((key == 'w' || key == 'W') && !bluePlane.isCrashing())
            bluePlane.setFlying(false);
        else if (keyCode == UP && !redPlane.isCrashing())
            redPlane.setFlying(false);
    }
}