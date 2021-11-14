import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PImage;

public class Plane extends MovingImage{

    private final double GRAVITY = 0.2;
    private double xVel, yVel, xVelBefore, yVelBefore;
    private boolean flying, crashing, dead, won;
    private boolean firing;
    private int explosionTimer;
    private PImage explosion;

    public Plane(PImage img, PImage explosion, double x, double y, double width, double height) {
        super(img, x, y, width, height);
        this.explosion = explosion;
        xVel = 0;
        yVel = 0;
        xVelBefore = 0;
        yVelBefore = 0;
        explosionTimer = 0;
        crashing = false;
    }

    public ArrayList<Rectangle2D.Double> act(PApplet g, ArrayList<Rectangle2D.Double> images) {
        ArrayList<Rectangle2D.Double> temp = new ArrayList<Rectangle2D.Double>(images);
        xVelBefore = xVel;
        yVelBefore = yVel;
        if (won) {
            yVel = 0;
        }
        else {
            if (flying && yVel >= -4)
                yVel -= 1;
            else
                yVel += GRAVITY;
        }
        if (!dead)
            moveByAmount(xVel, yVel);
        if (x + width > g.width)
            moveToLocation(0, y);
        else if (x < 0)
            moveToLocation(g.width - width, y);
        for (Rectangle2D.Double image : images)
            if(collide(image) && image instanceof Powerup){
                temp.remove(image);
            }
        return temp;
    }
    public void crash() {
        yVel = Math.abs(yVel);
        crashing = true;
        flying = false;
    }
    public Projectile shoot(PImage image) {
        Projectile projectile = new Projectile(image, this, x, y, 30, 10, 10);
        projectile.setAngle(Math.atan2(yVel, xVel));
        return projectile;
    }

    private boolean collide(Rectangle2D.Double image) {
        if (image != this) {
            Rectangle2D.Double hitbox = new Rectangle2D.Double(x, y, width + xVel, height + yVel);
            if (image instanceof Plane) {
                Plane plane = (Plane) image;
                if (!plane.isDead()) {
                    if (hitbox.intersects(image) && !(plane.isCrashing() || this.crashing)) {

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
                else if (!this.isCrashing())
                    won = true;
                
            }
            else if (image instanceof Ground && hitbox.intersects(image)) {
                crash();
                if (!dead)
                    explosionTimer = 60;
                dead = true;
                return true;
            }
            else if (image instanceof Sky && hitbox.intersects(image)) {
                crash();
                return true;
            }
            else if (image instanceof Powerup && hitbox.intersects(image)){
                
                setFiring(true);
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
    public boolean isDead() {
        return dead;
    }
    public boolean isFiring(){
        return firing;
    }
    public void setFiring(boolean firing){
        this.firing = firing;
    }
    public void draw(PApplet g) {
        if (explosionTimer > 0) {
            explosionTimer--;
            g.pushStyle();
            g.tint(255, explosionTimer*255f/60);
            g.image(explosion, (float)x, (float)y, (float)width, (float)height);
            g.popStyle();
        }
        if (!isDead()) {
            float angle = (float)Math.atan2(yVel, xVel) - (float)Math.atan2(yVelBefore, xVelBefore);
            g.pushStyle();
            g.imageMode(g.CENTER);
            rotate(angle);
            g.pushMatrix();
            g.translate((float)width/2, (float)height/2);
            super.draw(g);
            g.popMatrix();
            g.popStyle();
        }
    }
}