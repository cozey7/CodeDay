import java.util.ArrayList;
import java.lang.Math;
import java.awt.geom.Rectangle2D;
import processing.core.PApplet;
import processing.core.PImage;

public class DrawingSurface extends PApplet {

    private PImage redPlaneImg, bluePlaneImg, groundImg, powerupImg, bulletImg, explosionImg;
    private Plane bluePlane, redPlane;
    private ArrayList<Rectangle2D.Double> objects;
    private Powerup pu1, pu2;
    private Ground ground;
    private Sky sky;
    private int victoryTimer, scoreRed, scoreBlue, blueShots, redShots;
    private int spawnTimer;

    public void setup(){
        bluePlaneImg = loadImage("bluePlane.png");
        redPlaneImg = loadImage("redPlane.png");
        groundImg = loadImage("ground.png");
        powerupImg = loadImage("powerup.png");
        bulletImg = loadImage("bullet.png");
        explosionImg = loadImage("explosion.png");
        bluePlane = new Plane(bluePlaneImg, explosionImg, 100, 100, 50, 50);
        bluePlane.setVx(2);
        redPlane = new Plane(redPlaneImg, explosionImg, 600, 100, 50, 50);
        redPlane.setVx(-2);
        ground = new Ground(groundImg, 0, 395, 800, 205);
        sky = new Sky(0, -50, 800, 50);
        objects = new ArrayList<Rectangle2D.Double>();
        objects.add(bluePlane);
        objects.add(redPlane);
        objects.add(ground);
        objects.add(sky);
        pu1 = new Powerup(powerupImg, 30, 200, 30, 55);
        //pu2 = new Powerup(powerupImg, -50, -50, 20, 20);
        objects.add(pu1);
        //objects.add(pu2);
        blueShots = 0;
        redShots = 0;
        scoreRed = 0;
        scoreBlue = 0;
        spawnTimer = 0;
    }
    public DrawingSurface(){
        
    }

    public void draw() {
        background(130, 199, 255);
        ArrayList<Rectangle2D.Double> temp = new ArrayList<Rectangle2D.Double>(objects);
        for (Rectangle2D.Double object : objects) {
            if (object instanceof MovingImage) {
                // if (object instanceof Plane) {
                //     if (((Plane)object).isDead()) {
                //         continue;
                //     }
                // }
                ((MovingImage) object).draw(this);
                if (object instanceof Projectile)
                    temp = ((Projectile)object).act(this, objects);
            }
        }
        objects = temp;
        temp = bluePlane.act(this, temp);
        redPlane.act(this, temp);
        objects = temp;
        if (!objects.contains(pu1)) {
            pu1 = spawnPowerup();
            objects.add(pu1);
        }

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
                bluePlane = new Plane(bluePlaneImg, explosionImg, 100, 100, 50, 50);
                bluePlane.setVx(2);
                redPlane = new Plane(redPlaneImg, explosionImg, 600, 100, 50, 50);
                redPlane.setVx(-2);
                objects = new ArrayList<Rectangle2D.Double>();
                objects.add(bluePlane);
                objects.add(redPlane);
                objects.add(ground);
                objects.add(sky);
                pu1 = spawnPowerup();
                objects.add(pu1);
                victoryTimer = 0;
            }
        }
        textSize(100);
        textAlign(CENTER);
        text(scoreBlue + "         " + scoreRed, 400, 500);

        // if(spawnTimer > 1000){
        //     if(pu1.getX() < 0){
        //         pu1 = spawnPowerup();
        //         pu1.draw(this);
        //     } else if(pu2.getX() < 0){
        //         pu2 = spawnPowerup();
        //         objects.add(pu2);
        //         pu2.draw(this);
        //     } else 
        //         spawnTimer = 0;
        // }
        spawnTimer++;
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

    public Powerup spawnPowerup(){
        int x, y;
        int a = (int)(2*Math.random());
        if(a == 0){
            x = 30;
        } else {
            x = 750;
        }
        int b = (int)(300*Math.random());
        y = 50 + b;

        return new Powerup(powerupImg, x, y, 30, 55);
    }
}