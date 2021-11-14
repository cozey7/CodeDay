import processing.core.PImage;
import processing.core.PApplet;

public class Projectile extends MovingImage{

    private double xVel;

    public Projectile(PImage img, double x, double y, double width, double height, double speed) {
        super(img, x, y, width, height);
        xVel = speed;
    }
    
    public void act(PApplet g){
        moveByAmount(xVel, 0);
        if (x + width > g.width)
        moveToLocation(0, y);
        else if (x < 0)
        moveToLocation(g.width - width, y);
    }

    public void setSpeed(double v){
        xVel = v;
    }

}
