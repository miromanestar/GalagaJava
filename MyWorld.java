import greenfoot.*;
import java.util.Random;
/**
 * Write a description of class MyWorld here.
 * @author (your name) @version (a version number or a date)
 */
public class MyWorld extends World
{
    private Counter scoreCounter;
    private Health healthCounter;
    public boolean waveEnabled = true;
    
    /**
     * Constructor for objects of class MyWorld.
     */
    public MyWorld()
    {
        super(1280, 720, 1);
        GreenfootImage background = getBackground();
        Random rand = new Random();
        background.setColor(Color.BLACK);
        background.fill();
        createStars(rand.nextInt(1000));
        
        int p1Health = 100;
        
        addObject(new Player(500, p1Health), 640, 640);
        
        for(int i = 0; i < 5; i++) {
            addObject(new Alien(0, 60, 1000), rand.nextInt(1280), rand.nextInt(450));
        }
        
        scoreCounter = new Counter("Score: ");
        addObject(scoreCounter, 60, 690);
        healthCounter = new Health("Health: ", p1Health);
        addObject(healthCounter, 65, 707);
    }
    
    public void createStars(int number)
    {
        for(int i = 0; i < number; i++) {
            GreenfootImage background = getBackground();
            int color = Greenfoot.getRandomNumber(255);
            Color randColor = new Color(color, color, color);
            int x = Greenfoot.getRandomNumber(getWidth());
            int y = Greenfoot.getRandomNumber(getHeight());
            background.setColor(randColor);
            background.fillOval(x, y, 2, 2);
        }
    }
    
    public void countScore(int addAmount)
    {
        scoreCounter.add(addAmount);
    }
    
    public void gameOver()
    {
        addObject(new ScoreBoard(scoreCounter.getValue()), getWidth()/2, getHeight()/2);
    }
}
