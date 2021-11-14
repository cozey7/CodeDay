import java.awt.geom.Rectangle2D;
import processing.core.PApplet;
import processing.core.PImage;

public class MovingImage extends Rectangle2D.Double{

    private double angle;
    private PImage image;

    public MovingImage(PImage img, double x, double y, double width, double height){
        super(x, y, width, height);
        image = img;
        angle = 0;
    }

    public void moveToLocation(double x, double y){
        super.x = x;
        super.y = y;
    }

    public void moveByAmount(double x, double y) {
		super.x += x;
		super.y += y;
	}
    public void rotate(double angle) {
        this.angle += angle;
    }
    public void draw(PApplet g){
        g.pushMatrix();
        g.translate((float)x, (float)y);
        g.rotate((float)angle);
        g.image(image, 0, 0, (float)width, (float)height);
        g.popMatrix();
        
    }

}
