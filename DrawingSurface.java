import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PImage;

public class DrawingSurface extends PApplet {

    private PImage planeImg, groundImg, powerupImg;
    private Plane bluePlane, redPlane;
    private ArrayList<Powerup> powerups;
    private Powerup pu1;
    private Ground ground;
    private Sky sky;
    private int victoryTimer;

    public void setup(){
        planeImg = loadImage("plane.png");
        groundImg = loadImage("ground.png");
        powerupImg = loadImage("powerup.png");
        bluePlane = new Plane(planeImg, 100, 100, 50, 50);
        bluePlane.setVx(2);
        redPlane = new Plane(planeImg, 600, 100, 50, 50);
        redPlane.setVx(-2);
        ground = new Ground(groundImg, 0, 395, 800, 205);
        sky = new Sky(0, -50, 800, 50);
        powerups = new ArrayList<Powerup>();
        pu1 = new Powerup(powerupImg, 100, 200, 20, 20);
        powerups.add(pu1);

    }
    public DrawingSurface(){
        
    }

    public void draw() {
        background(130, 199, 255);
        bluePlane.draw(this);
        redPlane.draw(this);
        ground.draw(this);
        bluePlane.collide(redPlane);
        redPlane.collide(bluePlane);
        bluePlane.collide(ground);
        redPlane.collide(ground);
        bluePlane.collide(sky);
        redPlane.collide(sky);
        for(Powerup p : powerups) {
            p.draw(this);
            bluePlane.collide(p);
            redPlane.collide(p);
        }
        if(bluePlane.isFiring())
            System.out.println("blue firing");
        if(redPlane.isFiring())
            System.out.println("red firing");
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