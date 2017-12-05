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
    
    public int health;
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
    private final int numberOfRocketsInit = 30;
    public int numberOfRockets;
    
    // Helicopter machinegun ammo.
    private final int numberOfAmmoInit = 300;
    public int numberOfAmmo;
    
    // Helicopter missiles.
 	private final int numberOfMissilesInit = 30;
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
    public PlayerHelicopterData data = new PlayerHelicopterData();


	/**
     * Creates object of player.
     * 
     * @param xCoordinate Starting x coordinate of helicopter.
     * @param yCoordinate Starting y coordinate of helicopter.
     * @param helicopterType
     */
    public PlayerHelicopter(int xCoordinate, int yCoordinate, int helicopterSelect)
    {
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        
        LoadContent(helicopterSelect);
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
        
        this.data.setOffsetXRocketHolder(138);
        this.data.setOffsetYRocketHolder(40);
        this.data.setRocketHolderXcoordinate(this.xCoordinate + this.data.getOffsetXRocketHolder());
        this.data.setRocketHolderYcoordinate(this.yCoordinate + this.data.getOffsetYRocketHolder());
        
        this.data.setOffsetXMachineGun(helicopterBodyImg.getWidth() + this.data.getOffsetXMachineGun());
        this.data.setOffsetYMachineGun(helicopterBodyImg.getHeight() + this.data.getOffsetYMachineGun());
        this.data.setMachineGunXcoordinate(this.xCoordinate + this.data.getOffsetXMachineGun());
        this.data.setMachineGunYcoordinate(this.yCoordinate + this.data.getOffsetYMachineGun());
    }
    
    /**
     * Load files for this class.
     */
    private void LoadContent(int helicopterSelect)
    {
        try 
        {
        		if(helicopterSelect == 1)
        		{
        			data.setHelicopterName("Hello Kitty");
        			data.setHelicopterTypeStr("/helicopterbattle/resources/images/1_helicopter_body_helloKitty.png");
        			data.setHelicopterPlayerStr("/helicopterbattle/resources/images/profile_helloKitty.png");
        			this.data.setHealthInit(100);
        			this.health = data.getHealthInit();
        			this.numberOfAmmo = 300;
        			this.numberOfRockets = 0;
        			this.numberOfMissiles = 100;
        			this.data.setOffsetXFrontPropeller(38);
        			this.data.setOffsetYFrontPropeller(-17);
        			this.data.setOffsetXRearPropeller(3);
        			this.data.setOffsetYRearPropeller(-21);
        			this.acceleratingXspeed = 0.4;
        			this.acceleratingYspeed = 0.4;
        			this.stoppingXspeed = 0.4;
        			this.stoppingYspeed = 0.4;
        			this.data.setOffsetXMachineGun(-5);
        			this.data.setOffsetYMachineGun(-5);
        		}
        		else if(helicopterSelect == 2)
        		{
        			data.setHelicopterName("Chinook");
        			data.setHelicopterTypeStr("/helicopterbattle/resources/images/1_helicopter_body_chinook.png");
        			data.setHelicopterPlayerStr("/helicopterbattle/resources/images/fighter_pilot.png");
        			this.data.setHealthInit(150);
        			this.health = data.getHealthInit();
        			this.numberOfAmmo = 500;
        			this.numberOfRockets = 5;
        			this.numberOfMissiles = 10;
        			this.data.setOffsetXFrontPropeller(70);
        			this.data.setOffsetYFrontPropeller(-23);
        			this.data.setOffsetXRearPropeller(-10);
        			this.data.setOffsetYRearPropeller(-21);
        			this.acceleratingXspeed = 0.2;
        			this.acceleratingYspeed = 0.2;
        			this.stoppingXspeed = 0.1;
        			this.stoppingYspeed = 0.1;
        			this.data.setOffsetXMachineGun(-70);
        			this.data.setOffsetYMachineGun(-7);
        		}
        		else if(helicopterSelect == 3)
        		{
        			data.setHelicopterName("Viper");
        			data.setHelicopterTypeStr("/helicopterbattle/resources/images/1_helicopter_body_viper.png");
        			data.setHelicopterPlayerStr("/helicopterbattle/resources/images/fighter_pilot.png");
        			this.data.setHealthInit(150);
        			this.health = data.getHealthInit();
        			this.numberOfAmmo = 100;
        			this.numberOfRockets = 20;
        			this.numberOfMissiles = 25;
        			this.data.setOffsetXFrontPropeller(30);
        			this.data.setOffsetYFrontPropeller(-33);
        			this.data.setOffsetXRearPropeller(-5);
        			this.data.setOffsetYRearPropeller(-10);
        			this.acceleratingXspeed = 0.2;
        			this.acceleratingYspeed = 0.2;
        			this.stoppingXspeed = 0.1;
        			this.stoppingYspeed = 0.1;
        			this.data.setOffsetXMachineGun(-50);
        			this.data.setOffsetYMachineGun(-53);
        		}
        		else if(helicopterSelect == 4)
        		{
        			data.setHelicopterName("Tiger");
        			data.setHelicopterTypeStr("/helicopterbattle/resources/images/1_helicopter_body_tiger.png");
        			data.setHelicopterPlayerStr("/helicopterbattle/resources/images/fighter_pilot.png");
        			this.data.setHealthInit(150);
        			this.health = data.getHealthInit();
        			this.numberOfAmmo = 400;
        			this.numberOfRockets = 30;
        			this.numberOfMissiles = 0;
        			this.data.setOffsetXFrontPropeller(5);
        			this.data.setOffsetYFrontPropeller(-10);
        			this.data.setOffsetXRearPropeller(-3);
        			this.data.setOffsetYRearPropeller(40);
        			this.acceleratingXspeed = 0.2;
        			this.acceleratingYspeed = 0.2;
        			this.stoppingXspeed = 0.3;
        			this.stoppingYspeed = 0.1;
        			this.data.setOffsetXMachineGun(-10);
        			this.data.setOffsetYMachineGun(-5);
        		}
        		else if(helicopterSelect == 5)
        		{
        			data.setHelicopterName("Little Bird");
        			data.setHelicopterTypeStr("/helicopterbattle/resources/images/1_helicopter_body_littleBird.png");
        			data.setHelicopterPlayerStr("/helicopterbattle/resources/images/fighter_pilot.png");
        			this.data.setHealthInit(100);
        			this.health = data.getHealthInit();
        			this.numberOfAmmo = 200;
        			this.numberOfRockets = 20;
        			this.numberOfMissiles = 10;
        			this.data.setOffsetXFrontPropeller(65);
        			this.data.setOffsetYFrontPropeller(-23);
        			this.data.setOffsetXRearPropeller(-6);
        			this.data.setOffsetYRearPropeller(-11);
        			this.acceleratingXspeed = 0.2;
        			this.acceleratingYspeed = 0.2;
        			this.stoppingXspeed = 0.1;
        			this.stoppingYspeed = 0.1;
        			this.data.setOffsetXMachineGun(-50);
        			this.data.setOffsetYMachineGun(-30);
        		}
        		else if(helicopterSelect == 6)
        		{
        			data.setHelicopterName("Black Shark");
        			data.setHelicopterTypeStr("/helicopterbattle/resources/images/1_helicopter_body_blackShark.png");
        			data.setHelicopterPlayerStr("/helicopterbattle/resources/images/fighter_pilot.png");
        			this.data.setHealthInit(150);
        			this.health = data.getHealthInit();
        			this.numberOfAmmo = 200;
        			this.numberOfRockets = 10;
        			this.numberOfMissiles = 20;
        			this.data.setOffsetXFrontPropeller(70);
        			this.data.setOffsetYFrontPropeller(-23);
        			this.data.setOffsetXRearPropeller(-6);
        			this.data.setOffsetYRearPropeller(-21);
        			this.acceleratingXspeed = 0.2;
        			this.acceleratingYspeed = 0.2;
        			this.stoppingXspeed = 0.1;
        			this.stoppingYspeed = 0.1;
        			this.data.setOffsetXMachineGun(-50);
        			this.data.setOffsetYMachineGun(-20);
        		}
        		else if(helicopterSelect == 7)
        		{
        			data.setHelicopterName("SNOC");
        			data.setHelicopterTypeStr("/helicopterbattle/resources/images/1_helicopter_body_snoc.png");
        			data.setHelicopterPlayerStr("/helicopterbattle/resources/images/fighter_pilot.png");
        			this.data.setHealthInit(150);
        			this.health = data.getHealthInit();
        			this.numberOfAmmo = 100;
        			this.numberOfRockets = 25;
        			this.numberOfMissiles = 40;
        			this.data.setOffsetXFrontPropeller(70);
        			this.data.setOffsetYFrontPropeller(-23);
        			this.data.setOffsetXRearPropeller(-4);
        			this.data.setOffsetYRearPropeller(34);
        			this.acceleratingXspeed = 0.2;
        			this.acceleratingYspeed = 0.2;
        			this.stoppingXspeed = 0.1;
        			this.stoppingYspeed = 0.1;
        			this.data.setOffsetXMachineGun(-30);
        			this.data.setOffsetYMachineGun(-20);
        		}
        		else
        		{
        			data.setHelicopterName("Default");
        			data.setHelicopterTypeStr("/helicopterbattle/resources/images/1_helicopter_body.png");
        			data.setHelicopterPlayerStr("/helicopterbattle/resources/images/fighter_pilot.png");
        			this.data.setHealthInit(150);
        			this.health = data.getHealthInit();
        			this.numberOfAmmo = numberOfAmmoInit;
        			this.numberOfRockets = numberOfRocketsInit;
        			this.numberOfMissiles = numberOfMissilesInit;
        			this.data.setOffsetXFrontPropeller(70);
        			this.data.setOffsetYFrontPropeller(-23);
        			this.data.setOffsetXRearPropeller(-6);
        			this.data.setOffsetYRearPropeller(-21);
        			this.acceleratingXspeed = 0.2;
        			this.acceleratingYspeed = 0.2;
        			this.stoppingXspeed = 0.1;
        			this.stoppingYspeed = 0.1;
        			this.data.setOffsetXMachineGun(-40);
        			this.data.setOffsetYMachineGun(-10);
        		}
        		
        		URL helicopterProfileImgUrl = this.getClass().getResource(data.getHelicopterPlayerStr());
        		helicopterProfileImg = ImageIO.read(helicopterProfileImgUrl);
        		
        		data.setHelicopterPlayerHealth75Str("/helicopterbattle/resources/images/profile_health75.png");
        		URL helicopterProfileImgUrl75 = this.getClass().getResource(data.getHelicopterPlayerHealth75Str());
        		helicopterProfileImg75 = ImageIO.read(helicopterProfileImgUrl75);
        		
        		data.setHelicopterPlayerHealth50Str("/helicopterbattle/resources/images/profile_health50.png");
        		URL helicopterProfileImgUrl50 = this.getClass().getResource(data.getHelicopterPlayerHealth50Str());
        		helicopterProfileImg50 = ImageIO.read(helicopterProfileImgUrl50);
        		
        		data.setHelicopterPlayerHealth00Str("/helicopterbattle/resources/images/profile_health00.png");
        		URL helicopterProfileImgUrl00 = this.getClass().getResource(data.getHelicopterPlayerHealth00Str());
        		helicopterProfileImg00 = ImageIO.read(helicopterProfileImgUrl00);
        		
            URL helicopterBodyImgUrl = this.getClass().getResource(data.getHelicopterTypeStr());
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
        helicopterFrontPropellerAnim = new Animation(helicopterFrontPropellerAnimImg, 204, 34, 3, 20, true, xCoordinate + data.getOffsetXFrontPropeller(), yCoordinate + data.getOffsetYFrontPropeller(), 0);
        helicopterRearPropellerAnim = new Animation(helicopterRearPropellerAnimImg, 54, 54, 4, 20, true, xCoordinate + data.getOffsetXRearPropeller(), yCoordinate + data.getOffsetYRearPropeller(), 0);
    }
    
    
    /**
     * Resets the player.
     * 
     * @param xCoordinate Starting x coordinate of helicopter.
     * @param yCoordinate Starting y coordinate of helicopter.
     */
   /* public void Reset(int xCoordinate, int yCoordinate, int helicopterSelect)
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
    }*/
    
    
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
        helicopterFrontPropellerAnim.changeCoordinates(xCoordinate + data.getOffsetXFrontPropeller(), yCoordinate + data.getOffsetYFrontPropeller());
        helicopterRearPropellerAnim.changeCoordinates(xCoordinate + data.getOffsetXRearPropeller(), yCoordinate + data.getOffsetYRearPropeller());
        
        // Change position of the rocket holder.
        this.data.setRocketHolderXcoordinate(this.xCoordinate + this.data.getOffsetXRocketHolder());
        this.data.setRocketHolderYcoordinate(this.yCoordinate + this.data.getOffsetYRocketHolder());
        
        // Move the machine gun with helicopter.
        this.data.setMachineGunXcoordinate(this.xCoordinate + this.data.getOffsetXMachineGun());
        this.data.setMachineGunYcoordinate(this.yCoordinate + this.data.getOffsetYMachineGun());
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
    		data.setHealthPercent((health * 100.0f) / data.getHealthInit());
    		g2d.drawImage(helicopterProfileImg, 10, 17, null);
    		
    		if(data.getHealthPercent() <= 0)
    		{
    			g2d.drawImage(helicopterProfileImg00, 10, 17, null);
    		}
    		else if(data.getHealthPercent() <= 50)
    		{
    			g2d.drawImage(helicopterProfileImg50, 10, 17, null);
    		}
    		else if(data.getHealthPercent() <= 75)
    		{
    			g2d.drawImage(helicopterProfileImg75, 10, 17, null);
    		}
    }
    public void preventCrash(EnemyHelicopter enemy) {
		if(enemy.getYCenter() * 2 - enemy.yCoordinate < Framework.frameHeight / 2) {
     		if(enemy.getYCenter() * 2 - enemy.yCoordinate > yCoordinate) {
     			movingYspeed += 1.0;
     			if(movingYspeed > 5.0)
     				movingYspeed = 5.0;
     		}
     	}
     	else if(enemy.getYCenter() * 2 - enemy.yCoordinate >= Framework.frameHeight / 2){
     		if(enemy.yCoordinate < yCoordinate + helicopterBodyImg.getHeight()) {
     			movingYspeed -= 1.0;
     			if(Math.abs(movingYspeed) > 5.0)
     				movingYspeed = -5.0;
     		}
     	}
	}
}
