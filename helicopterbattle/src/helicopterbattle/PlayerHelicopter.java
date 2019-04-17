package helicopterbattle;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

/**
 * Helicopter which is managed by player.
 * 
 * @author www.gametutorial.net
 */

public class PlayerHelicopter {
    private float healthPercent;
    
    // Position of the helicopter on the screen.
    public int xCoordinate;
    public int yCoordinate;
    
    // Moving speed and also direction.
    private double movingXspeed;
    public double movingYspeed;
    public PlayerHelicopterData data = new PlayerHelicopterData(30, 300, 30);


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
        this.movingXspeed = 0;
        this.movingYspeed = 0;
             
        this.data.setOffsetXRocketHolder(138);
        this.data.setOffsetYRocketHolder(40);
        this.data.setRocketHolderXcoordinate(this.xCoordinate + this.data.getOffsetXRocketHolder());
        this.data.setRocketHolderYcoordinate(this.yCoordinate + this.data.getOffsetYRocketHolder());
        
        this.data.setOffsetXMachineGun(data.getHelicopterBodyImg().getWidth() + this.data.getOffsetXMachineGun());
        this.data.setOffsetYMachineGun(data.getHelicopterBodyImg().getHeight() + this.data.getOffsetYMachineGun());
        this.data.setMachineGunXcoordinate(this.xCoordinate + this.data.getOffsetXMachineGun());
        this.data.setMachineGunYcoordinate(this.yCoordinate + this.data.getOffsetYMachineGun());
    }
    
    /**
     * Load files for this class.
     */
    private void LoadContent(int helicopterSelect)
    {
    		data.loadHeli(helicopterSelect);
        // Now that we have images of propeller animation we initialize animation object.
        data.setHelicopterFrontPropellerAnim(new Animation(data.getHelicopterFrontPropellerAnimImg(), 204, 34, 3, 20, true, xCoordinate + data.getOffsetXFrontPropeller(), yCoordinate + data.getOffsetYFrontPropeller(), 0));
        data.setHelicopterRearPropellerAnim(new Animation(data.getHelicopterRearPropellerAnimImg(), 54, 54, 4, 20, true, xCoordinate + data.getOffsetXRearPropeller(), yCoordinate + data.getOffsetYRearPropeller(), 0));
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
            this.data.getNumberOfAmmo() > 0) 
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
            this.data.getNumberOfRockets() > 0 ) 
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
		return (Canvas.keyboardKeyState(KeyEvent.VK_SPACE) && 
				((gameTime - Missile.timeOfLastCreatedRocket) >= Missile.timeBetweenNewRockets) && 
				this.data.getNumberOfMissiles() > 0);
	}
    
    /**
     * Checks if player moving helicopter and sets its moving speed if player is moving.
     */
    public void isMoving()
    {
        // Moving on the x coordinate.
        if(Canvas.keyboardKeyState(KeyEvent.VK_D) || Canvas.keyboardKeyState(KeyEvent.VK_RIGHT))
            movingXspeed += data.getAcceleratingXspeed();
        else if(Canvas.keyboardKeyState(KeyEvent.VK_A) || Canvas.keyboardKeyState(KeyEvent.VK_LEFT))
            movingXspeed -= data.getAcceleratingXspeed();
        else    // Stoping
            if(movingXspeed < 0)
                movingXspeed += data.getStoppingXspeed();
            else if(movingXspeed > 0)
                movingXspeed -= data.getStoppingXspeed();
        
        // Prevent helicopter from moving off-screen along x-axis.
     	if (this.xCoordinate <= 0) 
     	{
     		movingXspeed = 0;
    			this.xCoordinate = 1;
    		} 
     	else if (this.xCoordinate + this.data.getHelicopterBodyImg().getWidth() >= Framework.frameWidth)
     	{
    			movingXspeed = 0;
    			this.xCoordinate = Framework.frameWidth - this.data.getHelicopterBodyImg().getWidth() - 1;
    		}
        
        // Moving on the y coordinate.
        if(Canvas.keyboardKeyState(KeyEvent.VK_W) || Canvas.keyboardKeyState(KeyEvent.VK_UP))
            movingYspeed -= data.getAcceleratingYspeed();
        else if(Canvas.keyboardKeyState(KeyEvent.VK_S) || Canvas.keyboardKeyState(KeyEvent.VK_DOWN))
            movingYspeed += data.getAcceleratingYspeed();
        else    // Stoping
            if(movingYspeed < 0)
                movingYspeed += data.getStoppingYspeed();
            else if(movingYspeed > 0)
                movingYspeed -= data.getStoppingYspeed();
        
        // Prevent helicopter from moving off-screen along y-axis.
     	if (this.yCoordinate <= 0) 
     	{
    			movingYspeed = 0;
    			this.yCoordinate = 1;
    		}
     	else if (this.yCoordinate + this.data.getHelicopterBodyImg().getHeight() >= Framework.frameHeight) {
    			movingYspeed = 0;
    			this.yCoordinate = Framework.frameHeight - this.data.getHelicopterBodyImg().getHeight() - 1;
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
        data.getHelicopterFrontPropellerAnim().changeCoordinates(xCoordinate + data.getOffsetXFrontPropeller(), yCoordinate + data.getOffsetYFrontPropeller());
        data.getHelicopterRearPropellerAnim().changeCoordinates(xCoordinate + data.getOffsetXRearPropeller(), yCoordinate + data.getOffsetYRearPropeller());
        
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
        data.getHelicopterFrontPropellerAnim().Draw(g2d);
        data.getHelicopterRearPropellerAnim().Draw(g2d);
        g2d.drawImage(data.getHelicopterBodyImg(), xCoordinate, yCoordinate, null);
    }
    
    public void DrawAvatar(Graphics2D g2d)
    {
    		healthPercent = (data.getHealth() * 100.0f) / data.getHealthInit();
    		g2d.drawImage(data.getHelicopterProfileImg(), 10, 17, null);
    		
    		if(healthPercent <= 0)
    		{
    			g2d.drawImage(data.getHelicopterProfileImg00(), 10, 17, null);
    		}
    		else if(healthPercent <= 50)
    		{
    			g2d.drawImage(data.getHelicopterProfileImg50(), 10, 17, null);
    		}
    		else if(healthPercent <= 75)
    		{
    			g2d.drawImage(data.getHelicopterProfileImg75(), 10, 17, null);
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
     		if(enemy.yCoordinate < yCoordinate + data.getHelicopterBodyImg().getHeight()) {
     			movingYspeed -= 1.0;
     			if(Math.abs(movingYspeed) > 5.0)
     				movingYspeed = -5.0;
     		}
     	}
	}
}
