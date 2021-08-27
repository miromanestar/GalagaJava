import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Player object source code
 * 
 * @author Miro Manestar
 * @version 1.0
 */
public class Player extends SmoothMover
{
    private SimpleTimer timer = new SimpleTimer();
    private int fireRate; //value is in shots/min
    public int playerHealth;
    
    public Player()
    {
    }

    public Player(int rateOfFire, int healthOfPlayer)
    {
        fireRate = rateOfFire;
        playerHealth = healthOfPlayer;
    }
    /**
     * Act - do whatever the Player wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        health();
        atEdge();
        playerControl();
        fireWeapon();
        die();
    }
    
    public void playerControl()
    {
        if(Greenfoot.isKeyDown("left")) {
            setLocation(getX()- 7, getY()); 
        }
        if(Greenfoot.isKeyDown("right")) {
            setLocation(getX()+ 7, getY());
        }
        if(Greenfoot.isKeyDown("up") && getY() > 550) {
            setLocation(getX(), getY() - 7);
        }
        if(Greenfoot.isKeyDown("down") && getY() < 680) {
            setLocation(getX(), getY() + 7);
        }
    }
    
    public void fireWeapon()
    {
        if(timer.millisElapsed() > ((60.0/fireRate) * 1000.0) && Greenfoot.isKeyDown("space")) {
            for(int i = 1; i <= 1; i++) {
            getWorld().addObject(new PlayerWeapon(), getX(), getY()); }
            timer.mark();  
        }
    }
    
    private void atEdge()
    {
       if(isAtEdge() && getY() != 0 && getX() < 400) { setLocation(1278, getY()); }
       if(isAtEdge() && getY() != 0 && getX() > 800) { setLocation(1, getY()); }
    }
    
    public void threadAccess()
    {
        atEdge();
    }
    
    public void die()
    {
        if(playerHealth <= 0) {
            MyWorld world = (MyWorld) getWorld();
            world.gameOver();
            getWorld().removeObject(this);
         }
    }
    
    public void health()
    {
        Health health = (Health) getWorld().getObjects(Health.class).get(0);
        playerHealth = health.getValue();
    }
}
