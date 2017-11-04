package helicopterbattle;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

public class Boss {
	
    // Health of the boss helicopter.
    public int health;
   
    public boolean rageMode;
    
    // Position of the boss helicopter on the screen.
    public double xCoordinate;
    public double yCoordinate;
    
    // Position of the boss helicopter's center on the screen.
    public int xCenter;
    public int yCenter;
    
    // Moving speed and direction.
    private double xVelocity;
    private double yVelocity;
    private double yAccel;
    
    private int accelTime;
    private int totalAccelTime;
    private boolean isMoving;
    public boolean invincible;
    private Random random;
    
    private double offsetXMachineGun;
    private double offsetYMachineGun;
    
    public double machineGunXcoordinate;
    public double machineGunYcoordinate;
    
    long lastBulletSpawnTime = 0;
    
    // Images of enemy helicopter. Images are loaded and set in Game class in LoadContent() method.
   /* public static BufferedImage helicopterBodyImg;
    public static BufferedImage helicopterFrontPropellerAnimImg;
    public static BufferedImage helicopterRearPropellerAnimImg;*/
    
    public BufferedImage helicopterImg;
    
    public static BufferedImage helicopter1Img;
    public static BufferedImage helicopter2Img;
    
    public static BufferedImage bulletImg;
    
    static final int initHealth = 500;
    static final long timeBetweenBullets = Framework.secInNanosec / 2;
    static final int bulletSpeed = 10;
    static final int bulletDamage = 40;
    static final int rageHealth = initHealth / 2;
    
/*    // Animation of the helicopter propeller.
    private Animation helicopterFrontPropellerAnim;
    private Animation helicopterRearPropellerAnim;
    // Offset for the propeler. We add offset to the position of the position of helicopter.
    private static int offsetXFrontPropeller = 4;
    private static int offsetYFrontPropeller = -7;
    private static int offsetXRearPropeller = 205;
    private static int offsetYRearPropeller = 6;
*/

    /**
     * Initialize enemy helicopter.
     * 
     * @param xCoordinate Starting x coordinate of helicopter.
     * @param yCoordinate Starting y coordinate of helicopter.
     * @param helicopterBodyImg Image of helicopter body.
     * @param helicopterFrontPropellerAnimImg Image of front helicopter propeller.
     * @param helicopterRearPropellerAnimImg Image of rear helicopter propeller.
     */
  /*  public void Initialize(int xCoordinate, int yCoordinate)
    {
        health = 500;
        
        // Sets enemy position.
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        
        // Sets enemy's center position
        this.xCenter = xCoordinate + helicopterBodyImg.getWidth() / 2;
        this.yCenter = yCoordinate + helicopterBodyImg.getHeight() / 2;
        
        // Initialize animation object.
        helicopterFrontPropellerAnim = new Animation(helicopterFrontPropellerAnimImg, 158, 16, 3, 20, true, xCoordinate + offsetXFrontPropeller, yCoordinate + offsetYFrontPropeller, 0);
        helicopterRearPropellerAnim = new Animation(helicopterRearPropellerAnimImg, 47, 47, 10, 10, true, xCoordinate + offsetXRearPropeller, yCoordinate + offsetYRearPropeller, 0);
       
        // Moving speed and direction of enemy.
        Boss.movingXspeed = -1;
    }*/
    
    /**
     * It sets speed and time between enemies to the initial properties.
     */
   /* public static void restartEnemy(){
    		Boss.timeBetweenNewEnemies = timeBetweenNewEnemiesInit;
    		Boss.timeOfLastCreatedEnemy = 0;
    		Boss.movingXspeed = movingXspeedInit;
    }*/
    
    public Boss(int health, double xCoordinate, double yCoordinate, BufferedImage helicopterImg)
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
    
    public Bullet spawnBullet(double x, double y)
    {
    		Vector2d direction = new Vector2d(x - machineGunXcoordinate, y - machineGunYcoordinate);
    		Vector2d velocity = direction.multiply(bulletSpeed / direction.length());
    		return new Bullet(machineGunXcoordinate, machineGunYcoordinate, velocity.x, velocity.y, bulletDamage, Boss.bulletImg);
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
     * It increase enemy speed and decrease time between new enemies.
     */
    /*public static void speedUp(){
        if(Boss.timeBetweenNewEnemies > Framework.secInNanosec)
        		Boss.timeBetweenNewEnemies -= Framework.secInNanosec / 100;
        
        Boss.movingXspeed -= 0.25;
    }*/
    
    
    /**
     * Checks if the enemy is left the screen.
     * 
     * @return true if the enemy is left the screen, false otherwise.
     */
    /*public boolean isLeftScreen()
    {
        if(xCoordinate < 0 - helicopterBodyImg.getWidth()) // When the entire helicopter is out of the screen.
            return true;
        else
            return false;
    }*/
    
        
    /**
     * Updates position of helicopter, animations.
     */
//    public void Update()
//    {
//        // Move enemy on x coordinate.
//        xCoordinate += movingXspeed;
//        
//        // Moves helicoper propeler animations with helicopter.
//        helicopterFrontPropellerAnim.changeCoordinates(xCoordinate + offsetXFrontPropeller, yCoordinate + offsetYFrontPropeller);
//        helicopterRearPropellerAnim.changeCoordinates(xCoordinate + offsetXRearPropeller, yCoordinate + offsetYRearPropeller);
//    }
    
    
    /**
     * Draws helicopter to the screen.
     * 
     * @param g2d Graphics2D
     */
    public void Draw(Graphics2D g2d)
    { 
        /*helicopterFrontPropellerAnim.Draw(g2d);
        g2d.drawImage(helicopterBodyImg, xCoordinate, yCoordinate, null);
        helicopterRearPropellerAnim.Draw(g2d);*/
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
