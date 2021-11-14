import java.util.ArrayList;

import java.awt.geom.Rectangle2D;
import processing.core.PApplet;
import processing.core.PImage;

public class DrawingSurface extends PApplet {

    private PImage redPlaneImg, bluePlaneImg, groundImg, powerupImg, bulletImg;
    private Plane bluePlane, redPlane;
    private ArrayList<Rectangle2D.Double> objects;
    private Powerup pu1;
    private Ground ground;
    private Sky sky;
    private int victoryTimer, scoreRed, scoreBlue, blueShots, redShots;

    public void setup(){
        bluePlaneImg = loadImage("bluePlane.png");
        redPlaneImg = loadImage("redPlane.png");
        groundImg = loadImage("ground.png");
        powerupImg = loadImage("powerup.png");
        bulletImg = loadImage("bullet.png");
        bluePlane = new Plane(bluePlaneImg, 100, 100, 50, 50);
        bluePlane.setVx(2);
        redPlane = new Plane(redPlaneImg, 600, 100, 50, 50);
        redPlane.setVx(-2);
        ground = new Ground(groundImg, 0, 395, 800, 205);
        sky = new Sky(0, -50, 800, 50);
        objects = new ArrayList<Rectangle2D.Double>();
        objects.add(bluePlane);
        objects.add(redPlane);
        objects.add(ground);
        objects.add(sky);
        pu1 = new Powerup(powerupImg, 100, 200, 20, 20);
        objects.add(pu1);
        blueShots = 0;
        redShots = 0;
        scoreRed = 0;
        scoreBlue = 0;
    }
    public DrawingSurface(){
        
    }

    public void draw() {
        background(130, 199, 255);
        ArrayList<Rectangle2D.Double> temp = new ArrayList<Rectangle2D.Double>(objects);
        for (Rectangle2D.Double object : objects) {
            if (object instanceof MovingImage) {
                ((MovingImage) object).draw(this);
                if (object instanceof Projectile)
                    temp = ((Projectile)object).act(this, objects);
            }
        }
        objects = temp;
        bluePlane.act(this, objects);
        redPlane.act(this, objects);

        if(bluePlane.isFiring()) {
            blueShots++;
            if (blueShots % 20 == 0)
                objects.add(bluePlane.shoot(bulletImg));
            if (blueShots == 60)
                bluePlane.setFiring(false);
        }
        else blueShots = 0;
        if(redPlane.isFiring()) {
            redShots++;
            if (redShots % 20 == 0)
                objects.add(redPlane.shoot(bulletImg));
            if (redShots == 60)
                redPlane.setFiring(false);
        }
        else redShots = 0;
        if (bluePlane.isDead() || redPlane.isDead()) {
            victoryTimer++;
            if (victoryTimer >= 120) {
                if (bluePlane.isDead())
                    scoreRed++;
                if (redPlane.isDead())
                    scoreBlue++;
                bluePlane = new Plane(bluePlaneImg, 100, 100, 50, 50);
                bluePlane.setVx(2);
                redPlane = new Plane(redPlaneImg, 600, 100, 50, 50);
                redPlane.setVx(-2);
                objects = new ArrayList<Rectangle2D.Double>();
                objects.add(bluePlane);
                objects.add(redPlane);
                objects.add(ground);
                objects.add(sky);
                victoryTimer = 0;
            }
        }
        textSize(100);
        textAlign(CENTER);
        text(scoreBlue + "         " + scoreRed, 400, 500);
    }
    public void settings() {
        size(800, 600);
    }
    public void keyPressed() {
        if ((key == 'w' || key == 'W') && !bluePlane.isCrashing())
            bluePlane.setFlying(true);
        else if (keyCode == UP && !redPlane.isCrashing())
            redPlane.setFlying(true);
    }
    public void keyReleased() {
        if ((key == 'w' || key == 'W') && !bluePlane.isCrashing())
            bluePlane.setFlying(false);
        else if (keyCode == UP && !redPlane.isCrashing())
            redPlane.setFlying(false);
    }
}