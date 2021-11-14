import java.awt.geom.Rectangle2D;
import processing.core.PApplet;
import processing.core.PImage;

public class MovingImage extends Rectangle2D.Double{

    private PImage image;

    public MovingImage(PImage img, double x, double y, double width, double height){
        super(x, y, width, height);
        image = img;
    }

    public void moveToLocation(double x, double y){
        super.x = x;
        super.y = y;
    }

    public void moveByAmount(double x, double y) {
		super.x += x;
		super.y += y;
	}

    public void draw(PApplet g){
        g.image(image, (float)x, (float)y, (float)width, (float)height);
    }

}
