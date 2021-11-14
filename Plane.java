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
        if (x + width > g.width)
            moveToLocation(0, y);
        else if (x < 0)
            moveToLocation(g.width - width, y);
    }
    private void crash() {
        yVel = -yVel;
        crashing = true;
        flying = false;
    }
    public boolean collide(Rectangle2D.Double image) {
        Rectangle2D.Double hitbox = new Rectangle2D.Double(x, y, width + xVel, height + yVel);
        if (hitbox.intersects(image)) {
            if (image instanceof Plane) {
                if (!((Plane)image).isDead()) {
                    if (y > image.getY() + image.getHeight() * 2.0/3) {
                        crash();
                        return true;
                    }
                    else if (y + image.getHeight() * 2.0/3 < image.getY())
                        yVel = -yVel;
                    else
                        xVel = -xVel;
                }
            }
            else if (image instanceof Ground) {
                crash();
                dead = true;
                return true;
            }
            else if (image instanceof Sky)
                crash();
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
    public boolean isDead() {
        return dead;
    }
    public void draw(PApplet g) {
        act(g);
        super.draw(g);
    }
}