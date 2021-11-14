import processing.core.PApplet;
import processing.core.PImage;

public class DrawingSurface extends PApplet {

    PImage plane;
    private Plane bluePlane;

    public void setup(){
        plane = loadImage("plane.png");
        bluePlane = new Plane(plane, 100, 100, 50, 50);
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

}