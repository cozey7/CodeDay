import processing.core.PApplet;
import processing.core.PImage;

public class DrawingSurface extends PApplet {

    PImage plane;
    private Plane bluePlane;

    public DrawingSurface(){
        bluePlane = new Plane(plane, 10, 10, 50, 50);
    }

    public void draw() {
        bluePlane.draw(this);
    }
    
    public void setup(){
        plane = loadImage("plane.png");
    }

    public void settings() {
        size(800, 600);
    }

}