import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Counter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Health extends Actor
{
    private static final Color textColor = new Color(255, 180, 150);
    
    private int value = 100;
    private int target = 100;
    private String text;
    private int stringLength;
    
    public Health()
    {
        this("", 0);
    }
    
    public Health(String prefix, int health)
    {
        value = health;
        target = health;
        
        text = prefix;
        stringLength = (text.length() + 2) * 10;
        
        setImage(new GreenfootImage(stringLength, 16));
        GreenfootImage image = getImage();
        image.setColor(textColor);
        
        updateImage();
    }
    
    public void act() {
        if(target >= 0) {
            if(value < target) {
                value++;
                updateImage(); }
            else if(value > target) {
                value--;
                updateImage(); }
        }
    }
    
    public void update(int damage)
    {
        target -= damage;
    }
    
    public int getValue()
    {
        return value;
    }
    
    private void updateImage()
    {
        GreenfootImage image = getImage();
        image.clear();
        image.drawString(text + value, 1, 12);
    }
}
