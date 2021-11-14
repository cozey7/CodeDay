import java.util.ArrayList;

import java.awt.geom.Rectangle2D;
import processing.core.PApplet;
import processing.core.PImage;

public class DrawingSurface extends PApplet {

    private PImage redPlaneImg, bluePlaneImg, groundImg, powerupImg, bulletLeftImg;
    private Plane bluePlane, redPlane;
    private ArrayList<Powerup> powerups;
    private ArrayList<Rectangle2D.Double> objects;
    private Powerup pu1;
    private Ground ground;
    private Sky sky;
    private int victoryTimer, scoreRed, scoreBlue;

    public void setup(){
        bluePlaneImg = loadImage("bluePlane.png");
        redPlaneImg = loadImage("redPlane.png");
        groundImg = loadImage("ground.png");
        powerupImg = loadImage("powerup.png");
        bulletLeftImg = loadImage("bulletL.png");
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
        powerups = new ArrayList<Powerup>();
        pu1 = new Powerup(powerupImg, 100, 200, 20, 20);
        powerups.add(pu1);
        objects.addAll(powerups);
        scoreRed = 0;
        scoreBlue = 0;
        
        bluePlane.setBullets(new Projectile(bulletLeftImg, -100, -100, 30, 10, 0), new Projectile(bulletLeftImg, -100, -100, 30, 10, 0), new Projectile(bulletLeftImg, -100, -100, 30, 10, 0));
        redPlane.setBullets(new Projectile(bulletLeftImg, -100, -100, 30, 10, 0), new Projectile(bulletLeftImg, -100, -100, 30, 10, 0), new Projectile(bulletLeftImg, -100, -100, 30, 10, 0));
    }
    public DrawingSurface(){
        
    }

    public void draw() {
        background(130, 199, 255);
        bluePlane.draw(this);
        bluePlane.act(this, objects);
        redPlane.draw(this);
        redPlane.act(this, objects);
        ground.draw(this);
        for(Powerup p : powerups)
            p.draw(this);

        if(bluePlane.isFiring())
            System.out.println("blue firing");
        if(redPlane.isFiring())
            System.out.println("red firing");
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
                Projectile p = new Projectile(bulletLeftImg, -100, -100, 30, 10, 0);
                objects = new ArrayList<Rectangle2D.Double>();
                objects.add(bluePlane);
                objects.add(redPlane);
                objects.add(ground);
                objects.add(sky);
                bluePlane.setBullets(new Projectile(bulletLeftImg, -100, -100, 30, 10, 0), new Projectile(bulletLeftImg, -100, -100, 30, 10, 0), new Projectile(bulletLeftImg, -100, -100, 30, 10, 0));
                redPlane.setBullets(new Projectile(bulletLeftImg, -100, -100, 30, 10, 0), new Projectile(bulletLeftImg, -100, -100, 30, 10, 0), new Projectile(bulletLeftImg, -100, -100, 30, 10, 0));
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