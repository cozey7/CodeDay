import processing.core.PImage;

public class Projectile extends MovingImage{

    private double xVel;

    public Projectile(PImage img, double x, double y, double width, double height, double speed) {
        super(img, x, y, width, height);
        xVel = speed;
    }
    
    public void act(){
        moveByAmount(xVel, 0);
    }

    public void setSpeed(double v){
        xVel = v;
    }

}
