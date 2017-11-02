package helicopterbattle;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 * Helicopter which is managed by player.
 * 
 * @author www.gametutorial.net
 */

public class PlayerHelicopter {
    
    // Health of the helicopter.
    private int healthInit;
    public int health;
    private float healthPercent;
    
    // Position of the helicopter on the screen.
    public int xCoordinate;
    public int yCoordinate;
    
    // Moving speed and also direction.
    private double movingXspeed;
    public double movingYspeed;
    private double acceleratingXspeed;
    private double acceleratingYspeed;
    private double stoppingXspeed;
    private double stoppingYspeed;
    
    // Helicopter rockets.
    private final int numberOfRocketsInit = 80;
    public int numberOfRockets;
    
    // Helicopter machinegun ammo.
    private final int numberOfAmmoInit = 1400;
    public int numberOfAmmo;
    
    // Helicopter missiles.
 	private final int numberOfMissilesInit = 80;
 	public int numberOfMissiles;
    
    // Images of helicopter and its propellers.
    public BufferedImage helicopterBodyImg;
    public BufferedImage helicopterProfileImg;
    public BufferedImage helicopterProfileImg75, helicopterProfileImg50, helicopterProfileImg00;
    private BufferedImage helicopterFrontPropellerAnimImg;
    private BufferedImage helicopterRearPropellerAnimImg;
    
    // Animation of the helicopter propeller.
    private Animation helicopterFrontPropellerAnim;
    private Animation helicopterRearPropellerAnim;
    // Offset for the propeler. We add offset to the position of the position of helicopter.
    private int offsetXFrontPropeller;
    private int offsetYFrontPropeller;
    private int offsetXRearPropeller;
    private int offsetYRearPropeller;
    
    // Offset of the helicopter rocket holder.
    private int offsetXRocketHolder;
    private int offsetYRocketHolder;
    // Position on the frame/window of the helicopter rocket holder.
    public int rocketHolderXcoordinate;
    public int rocketHolderYcoordinate;
    
    // Offset of the helicopter machine gun. We add offset to the position of the position of helicopter.
    private int offsetXMachineGun;
    private int offsetYMachineGun;
    // Position on the frame/window of the helicopter machine gun.
    public int machineGunXcoordinate;
    public int machineGunYcoordinate;
    
    // Helicopter Type
    public String helicopterName;
    private String helicopterTypeStr;
    private String helicopterPlayerStr;
    private String helicopterPlayerHealth75Str;
    private String helicopterPlayerHealth50Str;
    private String helicopterPlayerHealth00Str;
    
    
    /**
     * Creates object of player.
     * 
     * @param xCoordinate Starting x coordinate of helicopter.
     * @param yCoordinate Starting y coordinate of helicopter.
     * @param helicopterType
     */
    public PlayerHelicopter(int xCoordinate, int yCoordinate, KeyEvent helicopterType)
    {
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        
        LoadContent(helicopterType);
        Initialize();
    }
    
    
    /**
     * Set variables and objects for this class.
     */
    private void Initialize()
    {
        /*this.health = healthInit;
        
        this.numberOfRockets = numberOfRocketsInit;
        this.numberOfAmmo = numberOfAmmoInit;
        this.numberOfMissiles = numberOfMissilesInit;
        */
        this.movingXspeed = 0;
        this.movingYspeed = 0;
        /*this.acceleratingXspeed = 0.2;
        this.acceleratingYspeed = 0.2;
        this.stoppingXspeed = 0.1;
        this.stoppingYspeed = 0.1;

        this.offsetXFrontPropeller = 70;
        this.offsetYFrontPropeller = -23;        
        this.offsetXRearPropeller = -6;
        this.offsetYRearPropeller = -21;*/
        
        this.offsetXRocketHolder = 138;
        this.offsetYRocketHolder = 40;
        this.rocketHolderXcoordinate = this.xCoordinate + this.offsetXRocketHolder;
        this.rocketHolderYcoordinate = this.yCoordinate + this.offsetYRocketHolder;
        
        this.offsetXMachineGun = helicopterBodyImg.getWidth() + this.offsetXMachineGun;
        this.offsetYMachineGun = helicopterBodyImg.getHeight() + this.offsetYMachineGun;
        this.machineGunXcoordinate = this.xCoordinate + this.offsetXMachineGun;
        this.machineGunYcoordinate = this.yCoordinate + this.offsetYMachineGun;
    }
    
    /**
     * Load files for this class.
     */
    private void LoadContent(KeyEvent helicopterType)
    {
        try 
        {
        		if(helicopterType.getKeyCode() == KeyEvent.VK_H)
        		{
        			helicopterName = "Hello Kitty";
        			helicopterTypeStr = "/helicopterbattle/resources/images/1_helicopter_body_helloKitty.png";
        			helicopterPlayerStr = "/helicopterbattle/resources/images/profile_helloKitty.png";
        			this.healthInit = 150;
        			this.health = healthInit;
        			this.numberOfAmmo = 1000;
        			this.numberOfRockets = 0;
        			this.numberOfMissiles = 100;
        			this.offsetXFrontPropeller = 38;
        			this.offsetYFrontPropeller = -17;
        			this.offsetXRearPropeller = 3;
        			this.offsetYRearPropeller = -21;
        			this.acceleratingXspeed = 0.4;
        			this.acceleratingYspeed = 0.4;
        			this.stoppingXspeed = 0.4;
        			this.stoppingYspeed = 0.4;
        			this.offsetXMachineGun = -5;
        			this.offsetYMachineGun = -5;
        		}
        		else if(helicopterType.getKeyCode() == KeyEvent.VK_C)
        		{
        			helicopterName = "Chinook";
        			helicopterTypeStr = "/helicopterbattle/resources/images/1_helicopter_body_helloKitty.png";
        			helicopterPlayerStr = "/helicopterbattle/resources/images/profile_helloKitty.png\"";
        			this.healthInit = 150;
        			this.health = healthInit;
        			this.numberOfAmmo = 2000;
        			this.numberOfRockets = 5;
        			this.numberOfMissiles = 5;
        			this.offsetXFrontPropeller = 70;
        			this.offsetYFrontPropeller = -23;
        			this.offsetXRearPropeller = -10;
        			this.offsetYRearPropeller = -21;
        			this.acceleratingXspeed = 0.2;
        			this.acceleratingYspeed = 0.2;
        			this.stoppingXspeed = 0.1;
        			this.stoppingYspeed = 0.1;
        			this.offsetXMachineGun = -70;
        			this.offsetYMachineGun = -7;
        		}
        		else if(helicopterType.getKeyCode() == KeyEvent.VK_V)
        		{
        			helicopterName = "Viper";
        			helicopterTypeStr = "/helicopterbattle/resources/images/1_helicopter_body_viper.png";
        			helicopterPlayerStr = "/helicopterbattle/resources/images/profile_helloKitty.png";
        			this.healthInit = 150;
        			this.health = healthInit;
        			this.numberOfAmmo = 100;
        			this.numberOfRockets = 10;
        			this.numberOfMissiles = 25;
        			this.offsetXFrontPropeller = 30;
        			this.offsetYFrontPropeller = -33;
        			this.offsetXRearPropeller = -5;
        			this.offsetYRearPropeller = -10;
        			this.acceleratingXspeed = 0.2;
        			this.acceleratingYspeed = 0.2;
        			this.stoppingXspeed = 0.1;
        			this.stoppingYspeed = 0.1;
        			this.offsetXMachineGun = -50;
        			this.offsetYMachineGun = -53;
        		}
        		else if(helicopterType.getKeyCode() == KeyEvent.VK_T)
        		{
        			helicopterName = "Tiger";
        			helicopterTypeStr = "/helicopterbattle/resources/images/1_helicopter_body_tiger.png";
        			helicopterPlayerStr = "/helicopterbattle/resources/images/profile_helloKitty.png";
        			this.healthInit = 150;
        			this.health = healthInit;
        			this.numberOfAmmo = 5000;
        			this.numberOfRockets = 1000;
        			this.numberOfMissiles = 0;
        			this.offsetXFrontPropeller = 5;
        			this.offsetYFrontPropeller = -10;
        			this.offsetXRearPropeller = -3;
        			this.offsetYRearPropeller = 40;
        			this.acceleratingXspeed = 0.2;
        			this.acceleratingYspeed = 0.2;
        			this.stoppingXspeed = 0.3;
        			this.stoppingYspeed = 0.1;
        			this.offsetXMachineGun = -10;
        			this.offsetYMachineGun = -5;
        		}
        		else if(helicopterType.getKeyCode() == KeyEvent.VK_L)
        		{
        			helicopterName = "Little Bird";
        			helicopterTypeStr = "/helicopterbattle/resources/images/1_helicopter_body_littleBird.png";
        			helicopterPlayerStr = "/helicopterbattle/resources/images/profile_helloKitty.png";
        			this.healthInit = 150;
        			this.health = healthInit;
        			this.numberOfAmmo = 500;
        			this.numberOfRockets = 14;
        			this.numberOfMissiles = 15;
        			this.offsetXFrontPropeller = 65;
        			this.offsetYFrontPropeller = -23;
        			this.offsetXRearPropeller = -6;
        			this.offsetYRearPropeller = -11;
        			this.acceleratingXspeed = 0.2;
        			this.acceleratingYspeed = 0.2;
        			this.stoppingXspeed = 0.1;
        			this.stoppingYspeed = 0.1;
        			this.offsetXMachineGun = -50;
        			this.offsetYMachineGun = -30;
        		}
        		else if(helicopterType.getKeyCode() == KeyEvent.VK_B)
        		{
        			helicopterName = "Black Shark";
        			helicopterTypeStr = "/helicopterbattle/resources/images/1_helicopter_body_blackShark.png";
        			helicopterPlayerStr = "/helicopterbattle/resources/images/profile_helloKitty.png";
        			this.healthInit = 150;
        			this.health = healthInit;
        			this.numberOfAmmo = 1000;
        			this.numberOfRockets = 10;
        			this.numberOfMissiles = 5;
        			this.offsetXFrontPropeller = 70;
        			this.offsetYFrontPropeller = -23;
        			this.offsetXRearPropeller = -6;
        			this.offsetYRearPropeller = -21;
        			this.acceleratingXspeed = 0.2;
        			this.acceleratingYspeed = 0.2;
        			this.stoppingXspeed = 0.1;
        			this.stoppingYspeed = 0.1;
        			this.offsetXMachineGun = -50;
        			this.offsetYMachineGun = -20;
        		}
        		else if(helicopterType.getKeyCode() == KeyEvent.VK_S)
        		{
        			helicopterName = "SNOC";
        			helicopterTypeStr = "/helicopterbattle/resources/images/1_helicopter_body_snoc.png";
        			helicopterPlayerStr = "/helicopterbattle/resources/images/profile_helloKitty.png";
        			this.healthInit = 250;
        			this.health = healthInit;
        			this.numberOfAmmo = 100;
        			this.numberOfRockets = 25;
        			this.numberOfMissiles = 15;
        			this.offsetXFrontPropeller = 70;
        			this.offsetYFrontPropeller = -23;
        			this.offsetXRearPropeller = -4;
        			this.offsetYRearPropeller = 34;
        			this.acceleratingXspeed = 0.2;
        			this.acceleratingYspeed = 0.2;
        			this.stoppingXspeed = 0.1;
        			this.stoppingYspeed = 0.1;
        			this.offsetXMachineGun = -30;
        			this.offsetYMachineGun = -20;
        		}
        		else
        		{
        			helicopterName = "Default";
        			helicopterTypeStr = "/helicopterbattle/resources/images/1_helicopter_body.png";
        			helicopterPlayerStr = "/helicopterbattle/resources/images/profile_helloKitty.png";
        			this.healthInit = 450;
        			this.health = healthInit;
        			this.numberOfAmmo = numberOfAmmoInit;
        			this.numberOfRockets = numberOfRocketsInit;
        			this.numberOfMissiles = numberOfMissilesInit;
        			this.offsetXFrontPropeller = 70;
        			this.offsetYFrontPropeller = -23;
        			this.offsetXRearPropeller = -6;
        			this.offsetYRearPropeller = -21;
        			this.acceleratingXspeed = 0.2;
        			this.acceleratingYspeed = 0.2;
        			this.stoppingXspeed = 0.1;
        			this.stoppingYspeed = 0.1;
        			this.offsetXMachineGun = -40;
        			this.offsetYMachineGun = -10;
        		}
        		
        		URL helicopterProfileImgUrl = this.getClass().getResource(helicopterPlayerStr);
        		helicopterProfileImg = ImageIO.read(helicopterProfileImgUrl);
        		
        		helicopterPlayerHealth75Str = "/helicopterbattle/resources/images/profile_health75.png";
        		URL helicopterProfileImgUrl75 = this.getClass().getResource(helicopterPlayerHealth75Str);
        		helicopterProfileImg75 = ImageIO.read(helicopterProfileImgUrl75);
        		
        		helicopterPlayerHealth50Str = "/helicopterbattle/resources/images/profile_health50.png";
        		URL helicopterProfileImgUrl50 = this.getClass().getResource(helicopterPlayerHealth50Str);
        		helicopterProfileImg50 = ImageIO.read(helicopterProfileImgUrl50);
        		
        		helicopterPlayerHealth00Str = "/helicopterbattle/resources/images/profile_health00.png";
        		URL helicopterProfileImgUrl00 = this.getClass().getResource(helicopterPlayerHealth00Str);
        		helicopterProfileImg00 = ImageIO.read(helicopterProfileImgUrl00);
        		
            URL helicopterBodyImgUrl = this.getClass().getResource(helicopterTypeStr);
            helicopterBodyImg = ImageIO.read(helicopterBodyImgUrl);
            
            URL helicopterFrontPropellerAnimImgUrl = this.getClass().getResource("/helicopterbattle/resources/images/1_front_propeller_anim.png");
            helicopterFrontPropellerAnimImg = ImageIO.read(helicopterFrontPropellerAnimImgUrl);
            
            URL helicopterRearPropellerAnimImgUrl = this.getClass().getResource("/helicopterbattle/resources/images/1_rear_propeller_anim_blur.png");
            helicopterRearPropellerAnimImg = ImageIO.read(helicopterRearPropellerAnimImgUrl);
        } 
        catch (IOException ex) {
            Logger.getLogger(PlayerHelicopter.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        // Now that we have images of propeller animation we initialize animation object.
        helicopterFrontPropellerAnim = new Animation(helicopterFrontPropellerAnimImg, 204, 34, 3, 20, true, xCoordinate + offsetXFrontPropeller, yCoordinate + offsetYFrontPropeller, 0);
        helicopterRearPropellerAnim = new Animation(helicopterRearPropellerAnimImg, 54, 54, 4, 20, true, xCoordinate + offsetXRearPropeller, yCoordinate + offsetYRearPropeller, 0);
    }
    
    
    /**
     * Resets the player.
     * 
     * @param xCoordinate Starting x coordinate of helicopter.
     * @param yCoordinate Starting y coordinate of helicopter.
     */
    public void Reset(int xCoordinate, int yCoordinate)
    {
        this.health = healthInit;
        
        this.numberOfRockets = numberOfRocketsInit;
        this.numberOfAmmo = numberOfAmmoInit;
        this.numberOfMissiles = numberOfMissilesInit;
        
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        
        this.machineGunXcoordinate = this.xCoordinate + this.offsetXMachineGun;
        this.machineGunYcoordinate = this.yCoordinate + this.offsetYMachineGun;
        
        this.rocketHolderXcoordinate = this.xCoordinate + this.offsetXRocketHolder;
        this.rocketHolderYcoordinate = this.yCoordinate + this.offsetYRocketHolder;
        
        this.movingXspeed = 0;
        this.movingYspeed = 0;
    }
    
    
    /**
     * Checks if player is shooting. It also checks if player can 
     * shoot (time between bullets, does a player have any bullet left).
     * 
     * @param gameTime The current elapsed game time in nanoseconds.
     * @return true if player is shooting.
     */
    public boolean isShooting(long gameTime)
    {
        // Checks if left mouse button is down && if it is the time for a new bullet.
        if( Canvas.mouseButtonState(MouseEvent.BUTTON1) && 
            ((gameTime - Bullet.timeOfLastCreatedBullet) >= Bullet.timeBetweenNewBullets) &&
            this.numberOfAmmo > 0) 
        {
            return true;
        } else
            return false;
    }
    
    
    /**
     * Checks if player is fired a rocket. It also checks if player can 
     * fire a rocket (time between rockets, does a player have any rocket left).
     * 
     * @param gameTime The current elapsed game time in nanoseconds.
     * @return true if player is fired a rocket.
     */
    public boolean isFiredRocket(long gameTime)
    {
        // Checks if right mouse button is down && if it is the time for new rocket && if he has any rocket left.
        if( Canvas.mouseButtonState(MouseEvent.BUTTON3) && 
            ((gameTime - Rocket.timeOfLastCreatedRocket) >= Rocket.timeBetweenNewRockets) && 
            this.numberOfRockets > 0 ) 
        {
            return true;
        } else
            return false;
    }
    
    
    /**
	 * Checks if player is fired a missile. It also checks if player can 
	 * fire a missile (time between rockets, does a player have any missile left).
	 * 
	 * @param gameTime The current elapsed game time in nanoseconds.
	 * @return true if player is fired a missile.
	 */
	public boolean isFiredMissile(long gameTime)
	{
//		if (Canvas.mouseButtonState(MouseEvent.BUTTON2)
		if (Canvas.keyboardKeyState(KeyEvent.VK_SPACE) && 
				((gameTime - Missile.timeOfLastCreatedRocket) >= Missile.timeBetweenNewRockets) && 
				this.numberOfMissiles > 0)
			return true; 
		else 
			return false;
	}
    
    /**
     * Checks if player moving helicopter and sets its moving speed if player is moving.
     */
    public void isMoving()
    {
        // Moving on the x coordinate.
        if(Canvas.keyboardKeyState(KeyEvent.VK_D) || Canvas.keyboardKeyState(KeyEvent.VK_RIGHT))
            movingXspeed += acceleratingXspeed;
        else if(Canvas.keyboardKeyState(KeyEvent.VK_A) || Canvas.keyboardKeyState(KeyEvent.VK_LEFT))
            movingXspeed -= acceleratingXspeed;
        else    // Stoping
            if(movingXspeed < 0)
                movingXspeed += stoppingXspeed;
            else if(movingXspeed > 0)
                movingXspeed -= stoppingXspeed;
        
        // Prevent helicopter from moving off-screen along x-axis.
     	if (this.xCoordinate <= 0) 
     	{
     		movingXspeed = 0;
    			this.xCoordinate = 1;
    		} 
     	else if (this.xCoordinate + this.helicopterBodyImg.getWidth() >= Framework.frameWidth)
     	{
    			movingXspeed = 0;
    			this.xCoordinate = Framework.frameWidth - this.helicopterBodyImg.getWidth() - 1;
    		}
        
        // Moving on the y coordinate.
        if(Canvas.keyboardKeyState(KeyEvent.VK_W) || Canvas.keyboardKeyState(KeyEvent.VK_UP))
            movingYspeed -= acceleratingYspeed;
        else if(Canvas.keyboardKeyState(KeyEvent.VK_S) || Canvas.keyboardKeyState(KeyEvent.VK_DOWN))
            movingYspeed += acceleratingYspeed;
        else    // Stoping
            if(movingYspeed < 0)
                movingYspeed += stoppingYspeed;
            else if(movingYspeed > 0)
                movingYspeed -= stoppingYspeed;
        
        // Prevent helicopter from moving off-screen along y-axis.
     	if (this.yCoordinate <= 0) 
     	{
    			movingYspeed = 0;
    			this.yCoordinate = 1;
    		}
     	else if (this.yCoordinate + this.helicopterBodyImg.getHeight() >= Framework.frameHeight) {
    			movingYspeed = 0;
    			this.yCoordinate = Framework.frameHeight - this.helicopterBodyImg.getHeight() - 1;
     	}
    }
    
    
    /**
     * Updates position of helicopter, animations.
     */
    public void Update()
    {
        // Move helicopter and its propellers.
        xCoordinate += movingXspeed;
        yCoordinate += movingYspeed;
        helicopterFrontPropellerAnim.changeCoordinates(xCoordinate + offsetXFrontPropeller, yCoordinate + offsetYFrontPropeller);
        helicopterRearPropellerAnim.changeCoordinates(xCoordinate + offsetXRearPropeller, yCoordinate + offsetYRearPropeller);
        
        // Change position of the rocket holder.
        this.rocketHolderXcoordinate = this.xCoordinate + this.offsetXRocketHolder;
        this.rocketHolderYcoordinate = this.yCoordinate + this.offsetYRocketHolder;
        
        // Move the machine gun with helicopter.
        this.machineGunXcoordinate = this.xCoordinate + this.offsetXMachineGun;
        this.machineGunYcoordinate = this.yCoordinate + this.offsetYMachineGun;
    }
    
    
    /**
     * Draws helicopter to the screen.
     * 
     * @param g2d Graphics2D
     */
    public void Draw(Graphics2D g2d)
    {
        helicopterFrontPropellerAnim.Draw(g2d);
        helicopterRearPropellerAnim.Draw(g2d);
        g2d.drawImage(helicopterBodyImg, xCoordinate, yCoordinate, null);
    }
    
    public void DrawAvatar(Graphics2D g2d)
    {
    		healthPercent = (health * 100.0f) / healthInit;
    		g2d.drawImage(helicopterProfileImg, 10, 17, null);
    		
    		if(healthPercent <= 0)
    		{
    			g2d.drawImage(helicopterProfileImg00, 10, 17, null);
    		}
    		else if(healthPercent <= 50)
    		{
    			g2d.drawImage(helicopterProfileImg50, 10, 17, null);
    		}
    		else if(healthPercent <= 75)
    		{
    			g2d.drawImage(helicopterProfileImg75, 10, 17, null);
    		}
    }
    
}
