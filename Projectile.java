import processing.core.PImage;
import processing.core.PApplet;

public class Projectile extends MovingImage{

    private double xVel, yVel, speed, xVelBefore, yVelBefore;
    private final double GRAVITY = 0.2;
    private Plane owner;

    public Projectile(PImage img, Plane owner, double x, double y, double width, double height, double speed) {
        super(img, x, y, width, height);
        this.speed = speed;
        this.owner = owner;
        xVel = 0;
        yVel = 0;
    }
    
    public void act(PApplet g){
        xVelBefore = xVel;
        yVelBefore = yVel;
        yVel += GRAVITY;
        moveByAmount(xVel, yVel);
        if (x + width > g.width)
            moveToLocation(0, y);
        else if (x < 0)
            moveToLocation(g.width - width, y);
    }   

    public void setAngle(double angle) {
        xVel = Math.cos(angle) * speed;
        yVel = Math.sin(angle) * speed;
    }
    public void setSpeed(double v){
        this.speed = v;
    }
    public void draw(PApplet g) {
        float angle = (float)Math.atan2(yVel, xVel) - (float)Math.atan2(yVelBefore, xVelBefore);
        g.pushStyle();
        g.imageMode(g.CENTER);
        rotate(angle);
        super.draw(g);
        g.popStyle();
    }

}
