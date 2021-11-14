import java.awt.geom.Rectangle2D;
import processing.core.PApplet;
import processing.core.PImage;

public class Plane extends MovingImage{

    private final double GRAVITY = 0.2;
    private double xVel, yVel;
    private boolean flying, crashing, dead;

    public Plane(PImage img, double x, double y, double width, double height) {
        super(img, x, y, width, height);
        xVel = 0;
        yVel = 0;
        crashing = false;
    }

    private void act(PApplet g) {
        if (flying && yVel >= -4)
            yVel -= 1;
        else
            yVel += GRAVITY;
        if (!dead)
            moveByAmount(xVel, yVel);
        if (x > g.width)
            moveToLocation(-width, y);
        else if (x + width < 0)
            moveToLocation(g.width, y);
    }
    public boolean collide(Rectangle2D.Double image) {
        Rectangle2D.Double hitbox = new Rectangle2D.Double(x + xVel, y + yVel, width, height);
        if (hitbox.intersects(image)) {
            if (image instanceof Plane) {
                if (y > image.getY() + image.getHeight() * 2.0/3) {
                    yVel = -yVel;
                    crashing = true;
                    flying = false;
                    return true;
                }
                else if (y + image.getHeight() * 2.0/3 < image.getY())
                    yVel = -yVel;
                else
                    xVel = -xVel;
            }
            if (image instanceof Ground) {
                dead = true;
                return true;
            }
        }
        return false;
    }
    public void setFlying(boolean flying) {
        this.flying = flying;
    }
    public void setVx(double xVel) {
        this.xVel = xVel;
    }
    public void setVy(double yVel) {
        this.yVel = yVel;
    }
    public boolean isCrashing() {
        return crashing;
    }
    public void draw(PApplet g) {
        act(g);
        super.draw(g);
    }
}