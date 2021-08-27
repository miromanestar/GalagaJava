import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class PlayerWeapon here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class PlayerWeapon extends SmoothMover
{
    int speed = 15;
    int damage = 15;
    
    public PlayerWeapon()
    {
        setRotation(270);
        getImage().scale(30, 30);
    }
    
    public void act() 
    {
        movement(); //moves the object
        collDetect(); //collission detection
    }
    
    public void collDetect() 
    {
        if(isAtEdge() == true) {
            getWorld().removeObject(this);
        }
        else
        {
            if(isTouching(Alien.class)) {
                Alien alien = getIntersectingObjects(Alien.class).get(0);
                alien.hit();
                Counter counter = getWorld().getObjects(Counter.class).get(0);
                counter.add(1);
                getWorld().removeObject(this);
            }
        }
    }
    
    public void movement()
    {
        move(speed);
    }
    
}
