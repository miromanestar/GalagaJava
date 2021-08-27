import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.Random;

public class Alien extends SmoothMover
{
    private int rotation;
    private int timesX = 0;
    private int fireChance;
    private boolean invincible = false;
    public boolean respawn = true;
    private SimpleTimer timer = new SimpleTimer();
    
    public Alien(int multiplied, int scale, int fire)
    {
        fireChance = fire;
        timesX = multiplied;
        getImage().scale(scale, scale); //Controls size of Alien
        rotation = Greenfoot.getRandomNumber(360); //immediate turn
        
        if(timesX != 0) {
            //spawnAnimate();
        } else { invincible = false; }
    }
    
    public void act() 
    {
        movement(4); //Change the int to adjust speed
        fireWeapon();
    }
    
    public void respawn(int max, int multiplied, int scale, int chance) {
        Random rand = new Random();
        if(getWorld().getObjects(Alien.class).size() < 35 && respawn && rand.nextInt(100) <= chance) {
            for(int i = 0; i < rand.nextInt(max); i++) {
                getWorld().addObject(new Alien(multiplied, scale, 100), 0, rand.nextInt(450)); }
        }
    }
    
    /**
    public void spawnAnimate()
    {
        GreenfootImage player = (GreenfootImage) getWorld().getObjects(Player.class).get(0).getImage();
    } */
    
    public void movement(int speed)
    {
        atEdge();
        limit();
        randomTurn();
        setRotation(rotation);
        move(speed);
        rotation = getRotation();
        setRotation(0);
    }
    
    public void randomTurn()
    {
        if(timer.millisElapsed() > 250) { //lessens the crazy random movement
            if(Greenfoot.getRandomNumber(100) < 5) {
                rotation = (Greenfoot.getRandomNumber(359)); timer.mark(); }
        }
    }
    
    public void limit()
    {
        if(this.getY() > 450) {
        rotation =(Greenfoot.getRandomNumber(50) - 50);
        }
    } 
    
    public void atEdge()
    {
        if(isAtEdge() && getY() == 0) { setLocation(getX(), getY() + 10); rotation = 90; if(timer.millisElapsed() < 250) { timer.mark(); } }
        if(isAtEdge() && getY() != 0 && getX() < 400 && timer.millisElapsed() > 250) { setLocation(1280, getY()); timer.mark(); }
        if(isAtEdge() && getY() != 0 && getX() > 800 && timer.millisElapsed() > 250) { setLocation(0, getY()); timer.mark(); }
    }
    
    public void fireWeapon()
    {
        try {
            if(Greenfoot.getRandomNumber(fireChance) == 1 && getWorld().getObjects(Player.class).get(0).playerHealth != 0) {
            getWorld().addObject(new AlienWeapon(), getX(), getY()); }
        } catch (IndexOutOfBoundsException e) { }
    }
    
    public void hit()
    {
        Random rand = new Random();
        if(timesX == 0 && invincible == false) {
            for(int i = 0; i < 2; i++ ) {
                getWorld().addObject(new Alien(1, 40, 250), getX(), getY()); }
            respawn(1, 0, 6, 90);
            getWorld().removeObject(this);
        } else if(timesX == 1) {
            for(int i = 0; i < 2; i++ ) {
                getWorld().addObject(new Alien(2, 30, 500), getX(), getY()); }
            respawn(2, rand.nextInt(2), 40, 90);
            getWorld().removeObject(this);
        } else if(timesX == 2) {
            for(int i = 0; i < 2; i++ ) {
                getWorld().addObject(new Alien(3, 20, 750), getX(), getY()); }
            respawn(4, rand.nextInt(3), 30, 90);
            getWorld().removeObject(this);
        } else if(timesX == 3) { getWorld().removeObject(this); }
    }
}


