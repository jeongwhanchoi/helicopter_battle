package helicopterbattle;

import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

/**
 * @author jeongwhanchoi
 *
 */

public class Boss {
	
    // Health of the boss helicopter.
    public int health;
   
    public boolean rageMode;
    
    // Position of the boss helicopter on the screen.
    public int xCoordinate;
    public int yCoordinate;
    
    // Position of the boss helicopter's center on the screen.
    public int xCenter;
    public int yCenter;
    
    // Moving speed and direction.
    private double xVelocity;
    private double yVelocity;
    private double yAccel;
    
    // Boss rockets.
    private final int numberOfRocketsInit = 50;
    public int numberOfRockets;
    
    private int accelTime;
    private int totalAccelTime;
    private boolean isMoving;
    public boolean invincible;
    private Random random;
    
    private int offsetXMachineGun;
    private int offsetYMachineGun;
    
    public int machineGunXcoordinate;
    public int machineGunYcoordinate;
    
    long lastBulletSpawnTime = 0;
  
    
    public BufferedImage helicopterImg;
    
    public static BufferedImage helicopter1Img;
    public static BufferedImage helicopter2Img;
    
    public static BufferedImage bulletImg;
    
    static final int initHealth = 500;
    static final long timeBetweenBullets = Framework.secInNanosec / 2;
    static final int bulletSpeed = 10;
    static final int bulletDamage = 40;
    static final int rageHealth = initHealth / 2;
   
    
    public Boss(int health, int xCoordinate, int yCoordinate, BufferedImage helicopterImg)
    {
    		this.health = health;
    		this.rageMode = false;
    		this.xCoordinate = xCoordinate;
    		this.yCoordinate = yCoordinate;
    		this.xVelocity = -1.0;
    		this.yVelocity = 0.0;
    		this.yAccel = 0.0;
    		this.isMoving = true;
    		this.invincible = true;
    		this.random = new Random();
    		this.helicopterImg = helicopterImg;
    		this.accelTime = (int)Math.ceil(0.5 * helicopterImg.getWidth() / Math.abs(xVelocity));
    		this.totalAccelTime = 0;
    		this.numberOfRockets = numberOfRocketsInit;
    		
    		this.offsetXMachineGun = helicopterImg.getWidth() - 40;
    		this.offsetYMachineGun = helicopterImg.getWidth();
    		this.machineGunXcoordinate = this.xCoordinate + this.offsetXMachineGun;
    		this.machineGunYcoordinate = this.yCoordinate + this.offsetYMachineGun;
    }
    
    private void calculateAccel(double yPos)
    {
    		yVelocity = 0.0;
    		yAccel = (yPos - yCoordinate) / (accelTime * accelTime);
    }
    
  /*  public Bullet spawnBullet(double x, double y)
    {
    		Vector2d direction = new Vector2d(x - machineGunXcoordinate, y - machineGunYcoordinate);
    		Vector2d velocity = direction.multiply(bulletSpeed / direction.length());
    		return new Bullet(machineGunXcoordinate, machineGunYcoordinate, velocity.x, velocity.y, bulletDamage, Boss.bulletImg);
    }*/
    
    public boolean isFiredRocket(long gameTime)
    {
        //if it is the time for new rocket && if he has any rocket left.
        if( ((gameTime - Rocket.timeOfLastCreatedBossRocket) >= Rocket.timeBetweenNewBossRockets) && 
            this.numberOfRockets > 0 ) 
        {
            return true;
        } else
            return false;
    }
    
    
    public void updateVelocity(ArrayList<Bullet> playerBullets, ArrayList<Rocket> playerRockets)
    {
    		if(!isMoving)
    		{
    			accelTime = 50 + random.nextInt(20);
    			calculateAccel(random.nextDouble() * (Framework.frameHeight - helicopterImg.getHeight()));
    			isMoving = true;
    		}
    }
    
    public void update()
    {
    		if(health <= rageHealth)
    		{
    			rageMode = true;
    		}
    		if(xCoordinate > Framework.frameWidth - helicopterImg.getWidth() - 50)
    		{
    			xCoordinate += xVelocity;
    			machineGunXcoordinate = xCoordinate + offsetXMachineGun;
    		}
    		else
    		{
    			invincible = false;
    		}
    		
    		if(totalAccelTime ==  accelTime)
    		{
    			yAccel *= -1.0;
    		}
    		if(totalAccelTime == 2*accelTime)
    		{
    			isMoving = false;
    			yAccel *= -1.0;
    			totalAccelTime =0;
    		}
    		else
    		{
    			yCoordinate += yVelocity + 0.5 * yAccel;
    			machineGunYcoordinate = yCoordinate + offsetYMachineGun;
    			yVelocity += yAccel;
    			++totalAccelTime;
    		}
    }

    
    
    /**
     * Draws helicopter to the screen.
     * 
     * @param g2d Graphics2D
     */
    public void Draw(Graphics2D g2d)
    { 
      	g2d.drawImage(helicopterImg, (int)xCoordinate, (int)yCoordinate, null);
    }
    
    /**
     *  @return current xCenter and yCenter
     */
    public int getXCenter() {
    		return xCenter;
    }
    
    public int getYCenter() {
    		return yCenter;
    }
    
}
