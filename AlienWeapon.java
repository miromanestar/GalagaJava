import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.Random;
/**
 * Write a description of class AlienWeapon here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class AlienWeapon extends SmoothMover
{
    int speed = 7;
    int damage = 2;
    boolean alreadyFired = false;
    private SimpleTimer timer = new SimpleTimer();
    Random rand = new Random();
    
    public AlienWeapon()
    {
        getImage().scale(20, 20);
    }
    
    public void act() 
    {
        if(alreadyFired == false) { target(); }
        fireWeapon();
    }
   
    public void target()
    {
        try {
            Actor player = getWorld().getObjects(Player.class).get(0);  
            if(rand.nextInt(100) <= 40) { //makes shots innacurate about 40% of the time
                   turnTowards(player.getX() + (rand.nextInt(120) + 40), player.getY() + (rand.nextInt(120) + 40)); timer.mark(); }
            if(rand.nextInt(100) <= 40) {
                turnTowards(player.getX() - (rand.nextInt(120) + 40), player.getY() - (rand.nextInt(120) + 40)); timer.mark(); }
            else { turnTowards(player.getX(), player.getY()); timer.mark(); }
            
            alreadyFired = true;
        } catch(IndexOutOfBoundsException e) {}
    }
    
    public void fireWeapon()
    {
      move(speed);
      if(isAtEdge()) {
          if(getX() > 1278) {setLocation(2, getY()); }
          else if(getX() < 2) {setLocation(1278, getY()); }
          else {getWorld().removeObject(this); }
      } else {
          if(isTouching(Player.class)) {
              Health health = (Health) getWorld().getObjects(Health.class).get(0);
              health.update(damage);
              getWorld().removeObject(this); }}
    }
    
}
