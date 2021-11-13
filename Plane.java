import processing.core.PImage;

public class Plane extends MovingImage{

    private double gravity;
    private double xVel, yVel;

    public Plane(PImage img, double x, double y, double width, double height) {
        super(img, x, y, width, height);
        gravity = -9.8;
        xVel = 0;
        yVel = 0;
    }

    public void fly(){
        yVel += 0.1;
    }

    public void fall(){

    }

    public void act(){
        double xCoord = getX();

        yVel += gravity;
        double yCoord = getY() + yVel;

    }

}