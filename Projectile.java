import processing.core.PImage;
import processing.core.PApplet;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

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
    
    public ArrayList<Rectangle2D.Double> act(PApplet g, ArrayList<Rectangle2D.Double> objects){
        ArrayList<Rectangle2D.Double> temp = new ArrayList<Rectangle2D.Double>(objects);
        xVelBefore = xVel;
        yVelBefore = yVel;
        yVel += GRAVITY;
        moveByAmount(xVel, yVel);
        if (x + width > g.width)
            moveToLocation(0, y);
        else if (x < 0)
            moveToLocation(g.width - width, y);
        for (Rectangle2D.Double object : objects) {
            if (collide(object) && object != owner && object != this) {
                if (object instanceof Plane)
                    ((Plane) object).crash();
                temp.remove(this);
                break;
            }
        }
        return temp;
    }   
    private boolean collide(Rectangle2D.Double image) {
        return this.intersects(image) && !(image instanceof Powerup && image instanceof Projectile);
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
