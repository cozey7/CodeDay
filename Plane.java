import processing.core.PApplet;
import processing.core.PImage;

public class Plane extends MovingImage{

    private final double GRAVITY = 0.98;
    private double xVel, yVel;

    public Plane(PImage img, double x, double y, double width, double height) {
        super(img, x, y, width, height);
        xVel = 0;
        yVel = 0;
    }

    private void act(PApplet g){
        yVel += GRAVITY;
        moveByAmount(xVel, yVel);
        if (x > g.displayWidth)
            moveToLocation(-width, y);
        else if (x - width < 0)
            moveToLocation(g.displayWidth, y);

    }
    public boolean collide(MovingImage image) {
        return false;
    }
    public void setVx(double xVel) {
        this.xVel = xVel;
    }
    public void setVy(double yVel) {
        this.yVel = yVel;
    }

    public void draw(PApplet g) {
        act(g);
        super.draw(g);
    }
}