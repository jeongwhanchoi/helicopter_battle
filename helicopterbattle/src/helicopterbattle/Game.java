package helicopterbattle;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

/**
 * Actual game.
 * 
 * @author www.gametutorial.net
 */

public class Game {
    
    
    // List of all the machine gun bullets of the boss.
//    private ArrayList<Bullet> bossBulletsList;
    
    private GameData data = new GameData(new ArrayList<EnemyHelicopter>(), "");

	public Game(int helicopterSelect)
    {
        Framework.gameState = Framework.GameState.GAME_CONTENT_LOADING;
        
        Thread threadForInitGame = new Thread() {
            @Override
            public void run(){
                // Sets variables and objects for the game.
                Initialize(helicopterSelect);
                // Load game files (images, sounds, ...)
                LoadContent();
                
                Framework.gameState = Framework.GameState.PLAYING;
            }
        };
        threadForInitGame.start();
    }
    
    
   /**
     * Set variables and objects for the game.
     * 
     * @param helicopterType
     */
    private void Initialize(int helicopterSelect)
    {
    		Sound backgroundMusic = new Sound("intro.mp3", true);
    		backgroundMusic.start();
    	
    		data.setRandom(new Random());
        
        try {
            data.setRobot(new Robot());
        } catch (AWTException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        data.setPlayer(new PlayerHelicopter(Framework.frameWidth / 4, Framework.frameHeight / 4, helicopterSelect));
        
        data.setEnemyHelicopterList(new ArrayList<EnemyHelicopter>());
        
        data.setExplosionsList(new ArrayList<Animation>());
        
        data.setBulletsList(new ArrayList<Bullet>());
//        bossBulletsList = new ArrayList<Bullet>();
        data.setBonusList(new ArrayList<Bonus>());
        
        data.setMissilesList(new ArrayList<Missile>());
        data.setRocketsList(new ArrayList<Rocket>());
        data.setRocketSmokeList(new ArrayList<RocketSmoke>());
        
        // Moving images.
        data.setCloudLayer1Moving(new MovingBackground());
        data.setCloudLayer2Moving(new MovingBackground());
        data.setMountainsMoving(new MovingBackground());
        data.setGroundMoving(new MovingBackground());
        
        data.setFont(new Font("monospaced", Font.BOLD, 18));
        data.setScoreFont(new Font("arial", Font.BOLD, 60));
        
        data.setRunAwayEnemies(0);
        data.setDestroyedEnemies(0);
        data.setNumOfEnemiesForBoss(5);
        data.setLevel(1);
        data.setScore(0);
        data.setBossFight(false);
        
    }
    
    /**
     * Load game files (images).
     */
    private void LoadContent()
    {
        try 
        {
            // Images of environment
            URL skyColorImgUrl = this.getClass().getResource("/helicopterbattle/resources/images/sky_color.jpg");
            data.setSkyColorImg(ImageIO.read(skyColorImgUrl));
            URL cloudLayer1ImgUrl = this.getClass().getResource("/helicopterbattle/resources/images/cloud_layer_1.png");
            data.setCloudLayer1Img(ImageIO.read(cloudLayer1ImgUrl));
            URL cloudLayer2ImgUrl = this.getClass().getResource("/helicopterbattle/resources/images/cloud_layer_2.png");
            data.setCloudLayer2Img(ImageIO.read(cloudLayer2ImgUrl));
            URL mountainsImgUrl = this.getClass().getResource("/helicopterbattle/resources/images/mountains.png");
            data.setMountainsImg(ImageIO.read(mountainsImgUrl));
            URL groundImgUrl = this.getClass().getResource("/helicopterbattle/resources/images/ground.png");
            data.setGroundImg(ImageIO.read(groundImgUrl));
            
            // Load images for enemy helicopter
            URL helicopterBodyImgUrl = this.getClass().getResource("/helicopterbattle/resources/images/2_helicopter_body.png");
            EnemyHelicopter.helicopterBodyImg = ImageIO.read(helicopterBodyImgUrl);
            URL helicopterFrontPropellerAnimImgUrl = this.getClass().getResource("/helicopterbattle/resources/images/2_front_propeller_anim.png");
            EnemyHelicopter.helicopterFrontPropellerAnimImg = ImageIO.read(helicopterFrontPropellerAnimImgUrl);
            URL helicopterRearPropellerAnimImgUrl = this.getClass().getResource("/helicopterbattle/resources/images/2_rear_propeller_anim.png");
            EnemyHelicopter.helicopterRearPropellerAnimImg = ImageIO.read(helicopterRearPropellerAnimImgUrl);
            
            // Load images for boss
            URL helicopter1ImgUrl = this.getClass().getResource("/helicopterbattle/resources/images/boss_1.png");
            Boss.helicopter1Img = ImageIO.read(helicopter1ImgUrl);
            URL helicopter2ImgUrl = this.getClass().getResource("/helicopterbattle/resources/images/boss_2.png");
            Boss.helicopter2Img = ImageIO.read(helicopter2ImgUrl);
            
            // Images of rocket and its smoke.
            URL missileImgUrl = this.getClass().getResource("/helicopterbattle/resources/images/rocket.png");
            Missile.rocketImg = ImageIO.read(missileImgUrl);
            URL rocketImgUrl = this.getClass().getResource("/helicopterbattle/resources/images/rocket.png");
            Rocket.rocketImg = ImageIO.read(rocketImgUrl);
            URL rocketSmokeImgUrl = this.getClass().getResource("/helicopterbattle/resources/images/rocket_smoke.png");
            RocketSmoke.smokeImg = ImageIO.read(rocketSmokeImgUrl);
            
            // Imege of explosion animation.
            URL explosionAnimImgUrl = this.getClass().getResource("/helicopterbattle/resources/images/explosion_anim.png");
            data.setExplosionAnimImg(ImageIO.read(explosionAnimImgUrl));
            
            // Image of mouse cursor.
            URL mouseCursorImgUrl = this.getClass().getResource("/helicopterbattle/resources/images/mouse_cursor.png");
            data.setMouseCursorImg(ImageIO.read(mouseCursorImgUrl));
            
            // Helicopter machine gun bullet.
            URL bulletImgUrl = this.getClass().getResource("/helicopterbattle/resources/images/bullet.png");
            Bullet.bulletImg = ImageIO.read(bulletImgUrl);
            
            URL healthBonusImgUrl = this.getClass().getResource("/helicopterbattle/resources/images/health_shield.png");
            HealthBonus.image = ImageIO.read(healthBonusImgUrl);
            URL bulletBonusImgUrl = this.getClass().getResource("/helicopterbattle/resources/images/bullet_shield.png");
            BulletBonus.image = ImageIO.read(bulletBonusImgUrl);
            URL rocketBonusImgUrl = this.getClass().getResource("/helicopterbattle/resources/images/rocket_shield.png");
            RocketBonus.image = ImageIO.read(rocketBonusImgUrl);
        } 
        catch (IOException ex) 
        {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        // Now that we have images we initialize moving images.
        data.getCloudLayer1Moving().Initialize(data.getCloudLayer1Img(), -6, 0);
        data.getCloudLayer2Moving().Initialize(data.getCloudLayer2Img(), -2, 0);
        data.getMountainsMoving().Initialize(data.getMountainsImg(), -1, Framework.frameHeight - data.getGroundImg().getHeight() - data.getMountainsImg().getHeight() + 40);
        data.getGroundMoving().Initialize(data.getGroundImg(), -1.2, Framework.frameHeight - data.getGroundImg().getHeight());
    }
    
    
    /**
     * Restart game - reset some variables.
     */
    public void RestartGame(int helicopterSelect)
    {
//        player.Reset(Framework.frameWidth / 4, Framework.frameHeight / 4, helicopterSelect);
        data.setPlayer(new PlayerHelicopter(Framework.frameWidth / 4, Framework.frameHeight / 4, helicopterSelect));
        EnemyHelicopter.restartEnemy();
        
        Bullet.timeOfLastCreatedBullet = 0;
        Rocket.timeOfLastCreatedRocket = 0;
        Missile.timeOfLastCreatedRocket = 0;
        
        // Empty all the lists.
        data.getEnemyHelicopterList().clear();
        data.getBulletsList().clear();
        data.getRocketsList().clear();
        data.getRocketSmokeList().clear();
        data.getExplosionsList().clear();
        data.getMissilesList().clear();
//        bossBulletsList.clear();
        data.getBonusList().clear();
        
        // Statistics
        data.setRunAwayEnemies(0);
        data.setDestroyedEnemies(0);
        data.setLevel(1);
        data.setScore(0);
        
        data.setBossFight(false);
    }
    
    
    /**
     * Update game logic.
     * 
     * @param gameTime The elapsed game time in nanoseconds.
     * @param mousePosition current mouse position.
     */
    public void UpdateGame(long gameTime, Point mousePosition)
    {
        /* Player */
        // When player is destroyed and all explosions are finished showing we change game status.
        if( !isPlayerAlive() && data.getExplosionsList().isEmpty() ){
        		Sound gameover = new Sound("gameover.mp3", false);
        		gameover.start();
            Framework.gameState = Framework.GameState.GAMEOVER;
            return; // If player is destroyed, we don't need to do thing below.
        }
        // When a player is out of rockets and machine gun bullets, and all lists 
        // of bullets, rockets and explosions are empyt(end showing) we finish the game.
        if(data.getPlayer().numberOfAmmo <= 0 && 
           data.getPlayer().numberOfRockets <= 0 &&
           data.getPlayer().numberOfMissiles <= 0 &&
           data.getBulletsList().isEmpty() && 
           data.getRocketsList().isEmpty() && 
           data.getExplosionsList().isEmpty() &&
           data.getMissilesList().isEmpty() &&
           data.getRocketSmokeList().isEmpty())
        {
            
        	Sound gameover = new Sound("gameover.mp3", false);
        	gameover.start();
        	
        	Framework.gameState = Framework.GameState.GAMEOVER;
        
        	return;
        }
        // If player is alive we update him.
        if(isPlayerAlive()){
            isPlayerShooting(gameTime, mousePosition);
            didPlayerFiredRocket(gameTime);
            didPlayerFiredMissile(gameTime);
            data.getPlayer().isMoving();
            data.getPlayer().Update();
        }
        
        /* Mouse */
        /*if(player.helicopterName == "Chinook" || player.helicopterName == "Black Shark")
        {
        		limitMousePosition(mousePosition);
	    }*/
        
        /* Bullets */
        updateBullets();
        
        /* Rockets */
        updateMissile(gameTime); // It also checks for collisions (if any of the missiles hit any of the enemy helicopter).
        updateRockets(gameTime); // It also checks for collisions (if any of the rockets hit any of the enemy helicopter).
        updateRocketSmoke(gameTime);
        
        /* Enemies */
        createEnemyHelicopter(gameTime);
        updateEnemies(gameTime);
        
        /* Explosions */
        updateExplosions();
        
        updateBonuses(gameTime);
        
        /*if(bossFight && boss.rageMode)
        {
        		updateStones(gameTime);
        		updateStonesSmoke(gameTime);
        }*/
        assistanceSystem();
    }
    
    /**
     * Draw the game to the screen.
     * 
     * @param g2d Graphics2D
     * @param mousePosition current mouse position.
     */
    public void Draw(Graphics2D g2d, Point mousePosition, long gameTime)
    {
        // Image for background sky color.
        g2d.drawImage(data.getSkyColorImg(), 0, 0, Framework.frameWidth, Framework.frameHeight, null);
        
        // Moving images.
        data.getMountainsMoving().Draw(g2d);
        data.getGroundMoving().Draw(g2d);
        data.getCloudLayer2Moving().Draw(g2d);
        
        if(isPlayerAlive())
        {
            data.getPlayer().Draw(g2d);
        		data.setStatXCoordinate(10 + data.getPlayer().helicopterProfileImg.getWidth() + 10);
        }
		
        // Draws helicopter's profile
        data.getPlayer().DrawAvatar(g2d);
        		        		
        // Draws all the enemies.
        for(int i = 0; i < data.getEnemyHelicopterList().size(); i++)
        {
            data.getEnemyHelicopterList().get(i).Draw(g2d);
        }
        
        if(data.isBossFight())
        {
        		data.getBoss().Draw(g2d);
        		/*for(int i = 0; i < stonesList.size(); i++)
        		{
        			stoneList.get(i).draw(g2d);
        		}*/
     /*   		for(int i=0; i < bossBulletsList.size(); i++)
        		{
        			bossBulletsList.get(i).Draw(g2d);
        		}*/
        }
        
        // Draws all the bullets. 
        for(int i = 0; i < data.getBulletsList().size(); i++)
        {
            data.getBulletsList().get(i).Draw(g2d);
        }
        
        // Draws all the missiles.
        for(int i = 0; i < data.getMissilesList().size(); i++)
        {
        		data.getMissilesList().get(i).Draw(g2d);
        }
        
        // Draws all the rockets. 
        for(int i = 0; i < data.getRocketsList().size(); i++)
        {
            data.getRocketsList().get(i).Draw(g2d);
        }
        // Draws smoke of all the rockets.
        for(int i = 0; i < data.getRocketSmokeList().size(); i++)
        {
            data.getRocketSmokeList().get(i).Draw(g2d);
        }
        
        // Draw all explosions.
        for(int i = 0; i < data.getExplosionsList().size(); i++)
        {
            data.getExplosionsList().get(i).Draw(g2d);
        }
        
        // 	Draw all bonuses
        for(Bonus bonus : data.getBonusList()) 
        {
        		bonus.Draw(g2d);
        }
        
        // Draw statistics
        g2d.setFont(data.getFont());
        g2d.setColor(Color.darkGray);
        
        g2d.drawString(formatTime(gameTime), Framework.frameWidth/2 - 45, 21);
        g2d.drawString("HEALTH: "    + data.getPlayer().health, data.getStatXCoordinate(), 41);
        g2d.drawString("DESTROYED: " + data.getDestroyedEnemies(), data.getStatXCoordinate(), 61);
        g2d.drawString("RUNAWAY: "   + data.getRunAwayEnemies(),   data.getStatXCoordinate(), 81);
        g2d.drawString("ROCKETS: "   + data.getPlayer().numberOfRockets, data.getStatXCoordinate(), 111);
        g2d.drawString("AMMO: "      + data.getPlayer().numberOfAmmo, data.getStatXCoordinate(), 131);
        g2d.drawString("MISSILE: "   + data.getPlayer().numberOfMissiles, data.getStatXCoordinate(), 151);
        
        g2d.drawString("STAGE: " + data.getLevel(), Framework.frameWidth/2 + 300, 21);
        g2d.drawString("SCORE: " + getScore(), Framework.frameWidth/2 + 300, 41);
        
//        g2d.drawString("HIGH SCORE: " + leaderBoard.getHighScore(), Framework.frameWidth/2 + 300, 51);
        
        if(data.isBossFight())
        {
        		g2d.drawString("HP: " + data.getBoss().health, (int)data.getBoss().xCoordinate, (int)data.getBoss().yCoordinate + 20);
        }
        /*g2d.drawString("HP: " + player.health, player.xCoordinate, player.yCoordinate - 5);
        for(EnemyHelicopter eh : enemyHelicopterList)
        {
        		g2d.drawString("HP: " + eh.health, eh.xCoordinate, eh.yCoordinate - 5);
        }*/
        
        // Moving images. We draw this cloud in front of the helicopter.
        data.getCloudLayer1Moving().Draw(g2d);
        
        // Mouse cursor
        if(isPlayerAlive())
            drawRotatedMouseCursor(g2d, mousePosition);
    }
    
    /**
     * Draws some game statistics when game is over.
     * 
     * @param g2d Graphics2D
     * @param gameTime Elapsed game time.
     */
    public void DrawStatistic(Graphics2D g2d, long gameTime){
        /*g2d.drawString("Time: " + formatTime(gameTime),                    Framework.frameWidth/2 - 50, Framework.frameHeight/3 + 80);
        g2d.drawString("Rockets left: "      + player.numberOfRockets,     Framework.frameWidth/2 - 55, Framework.frameHeight/3 + 105);
        g2d.drawString("Ammo left: "         + player.numberOfAmmo,        Framework.frameWidth/2 - 55, Framework.frameHeight/3 + 125);
        g2d.drawString("Missile left: "         + player.numberOfMissiles, Framework.frameWidth/2 - 60, Framework.frameHeight/3 + 150);
        g2d.drawString("Destroyed enemies: " + destroyedEnemies,           Framework.frameWidth/2 - 70, Framework.frameHeight/3 + 175);
        g2d.drawString("Runaway enemies: "   + runAwayEnemies,             Framework.frameWidth/2 - 70, Framework.frameHeight/3 + 200);
        g2d.setFont(font);
        g2d.drawString("Statistics: ",                                     Framework.frameWidth/2 - 75, Framework.frameHeight/3 + 60);
        */
        g2d.setFont(data.getScoreFont());
        g2d.setColor(Color.white);
		g2d.drawString("Score: "+getScore(), Framework.frameWidth/2 - 150, Framework.frameHeight/4 + 140);
//    		g2d.drawString(""+getScore(), Framework.frameWidth/2 - 150, Framework.frameHeight/4 + 180);
    		
    		if(data.getHighScore().equals(""))
    		{
    			//init the highscore
    			data.setHighScore(this.getHighScore());
    		}
    		g2d.setColor(Color.darkGray);
    		g2d.drawString("High Score ", Framework.frameWidth/2 -150, Framework.frameHeight/4 -50);
    		g2d.drawString(""+data.getHighScore(), Framework.frameWidth/2 -100, Framework.frameHeight/4 +30);

    }
    
    public int getScore()
    {
    		data.setScore(10 * (data.getDestroyedEnemies() - data.getRunAwayEnemies()) + 100 * (data.getLevel()-1));
    		if(data.getScore() < 0)
    			data.setScore(0);
    		return data.getScore();
    }
    
    public String getHighScore()
    {
    		FileReader readFile = null;
    		BufferedReader  reader = null;
    		try
    		{
    			readFile = new FileReader("highscore.dat");
    			reader = new BufferedReader(readFile);
    			return reader.readLine();
    		}
    		catch(Exception e)
    		{
    			return "Nobody:0";
    		}
    		finally
    		{
    			try {
    				if(reader != null)
    				reader.close();
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
    		}
    }
    
    public void checkScore()
    {
    		if(data.getHighScore().equals(""))
    			return;
    		//format Choi/:/100
    		if(getScore() > Integer.parseInt((data.getHighScore().split(":")[1])))
    		{
    			//user has set a new record
    			String name = JOptionPane.showInputDialog("You set a new high score. What is your name?");
    			data.setHighScore(name + ":" + getScore());
    			
    			File scoreFile = new File("highscore.dat");
    			if(!scoreFile.exists())
    			{
    				try {
						scoreFile.createNewFile();
					} catch (IOException e) {
						// TODO: handle exception
						e.printStackTrace();
					}
    			}
    		FileWriter writeFile = null;
    		BufferedWriter writer = null;
    		try
    		{
    			writeFile = new FileWriter(scoreFile);
    			writer = new BufferedWriter(writeFile);
    			writer.write(this.data.getHighScore());
    		}
    		catch(Exception e)
    		{
    			//errors
    		}
    		finally
    		{
    			try
    			{
    				if(writer != null)
    					writer.close();
    			}
    			catch(Exception e) {}
    		}
    }
   }
    /**
     * Draws rotated mouse cursor.
     * It rotates the cursor image on the basis of the player helicopter machine gun.
     * 
     * @param g2d Graphics2D
     * @param mousePosition Position of the mouse.
     */
    private void drawRotatedMouseCursor(Graphics2D g2d, Point mousePosition)
    {
        double RIGHT_ANGLE_RADIANS = Math.PI / 2;
        
        // Positon of the player helicopter machine gun.
        int pivotX = data.getPlayer().data.getMachineGunXcoordinate();
        int pivotY = data.getPlayer().data.getMachineGunYcoordinate();
        
        int a = pivotX - mousePosition.x;
        int b = pivotY - mousePosition.y;
        double ab = (double)a / (double)b;
        double alfaAngleRadians = Math.atan(ab);

        if(mousePosition.y < pivotY) // Above the helicopter.
            alfaAngleRadians = RIGHT_ANGLE_RADIANS - alfaAngleRadians - RIGHT_ANGLE_RADIANS*2;
        else if (mousePosition.y > pivotY) // Under the helicopter.
            alfaAngleRadians = RIGHT_ANGLE_RADIANS - alfaAngleRadians;
        else
            alfaAngleRadians = 0;

        AffineTransform origXform = g2d.getTransform();
        AffineTransform newXform = (AffineTransform)(origXform.clone());

        newXform.rotate(alfaAngleRadians, mousePosition.x, mousePosition.y);
        g2d.setTransform(newXform);
        
        g2d.drawImage(data.getMouseCursorImg(), mousePosition.x, mousePosition.y - data.getMouseCursorImg().getHeight()/2, null); // We substract half of the cursor image so that will be drawn in center of the y mouse coordinate.
        
        g2d.setTransform(origXform);
    }
    
    /**
     * Format given time into 00:00 format.
     * 
     * @param time Time that is in nanoseconds.
     * @return Time in 00:00 format.
     */
    private static String formatTime(long time){
            // Given time in seconds.
            int sec = (int)(time / Framework.milisecInNanosec / 1000);

            // Given time in minutes and seconds.
            int min = sec / 60;
            sec = sec - (min * 60);

            String minString, secString;

            if(min <= 9)
                minString = "0" + Integer.toString(min);
            else
                minString = "" + Integer.toString(min);

            if(sec <= 9)
                secString = "0" + Integer.toString(sec);
            else
                secString = "" + Integer.toString(sec);

            return minString + ":" + secString;
    }
    
    
    
    
    
    /*
     * 
     * Methods for updating the game. 
     * 
     */
    
     
    /**
     * Check if player is alive. If not, set game over status.
     * 
     * @return True if player is alive, false otherwise.
     */
    private boolean isPlayerAlive()
    {
        if(data.getPlayer().health <= 0)
            return false;
        else
        		return true;
    }
    
    /*private static Vector2d mousePositionToVector(int xOrigin, int yOrigin, Point mousePosition)
    {
    		Vector2d direction = new Vector2d(mousePosition.x - xOrigin, mousePosition.y - yOrigin);
    		return direction.multiply(1.0 / direction.length());
    }*/
    
    /**
     * Checks if the player is shooting with the machine gun and creates bullets if he shooting.
     * 
     * @param gameTime Game time.
     */
    private void isPlayerShooting(long gameTime, Point mousePosition)
    {
        if(data.getPlayer().isShooting(gameTime))
        {
            Bullet.timeOfLastCreatedBullet = gameTime;
            data.getPlayer().numberOfAmmo--;
            
            Bullet b = new Bullet(data.getPlayer().data.getMachineGunXcoordinate(), data.getPlayer().data.getMachineGunYcoordinate(), mousePosition);
            data.getBulletsList().add(b);
            
            Sound gun = new Sound("gun.mp3", false);
            gun.start();
        }
    }
    
    /**
     * Checks if the player is fired the rocket and creates it if he did.
     * It also checks if player can fire the rocket.
     * 
     * @param gameTime Game time.
     */
    private void didPlayerFiredRocket(long gameTime)
    {
        if(data.getPlayer().isFiredRocket(gameTime))
        {
            Rocket.timeOfLastCreatedRocket = gameTime;
            data.getPlayer().numberOfRockets--;
            
            Rocket r = new Rocket();
            r.Initialize(data.getPlayer().data.getRocketHolderXcoordinate(), data.getPlayer().data.getRocketHolderYcoordinate());
            data.getRocketsList().add(r);
            
            Sound rocket = new Sound("rocket.mp3", false);
            rocket.start();
        }
    }
    
    
    /**
     * Checks if the player is fired the missile and creates it if hed did.
     * It also checks if player can fire the rocket.
     * 
     * @param gameTime
     */
    private void didPlayerFiredMissile(long gameTime)
    {
    		if(data.getPlayer().isFiredMissile(gameTime))
    		{
    			Missile.timeOfLastCreatedRocket = gameTime;
    			data.getPlayer().numberOfMissiles--;
    			
    			Missile m = new Missile();
    			m.Initialize(data.getPlayer().data.getRocketHolderXcoordinate(), data.getPlayer().data.getRocketHolderYcoordinate());
    			data.getMissilesList().add(m);
    			
    			Sound missile = new Sound("missile.mp3", false);
    			missile.start();
    		}
    }
    
    /**
     * Creates a new enemy if it's time.
     * 
     * @param gameTime Game time.
     */
    private void createEnemyHelicopter(long gameTime)
    {
    		if(data.isBossFight())
    		{
    			return;
    		}
    		if(data.getDestroyedEnemies() == data.getLevel() * data.getNumOfEnemiesForBoss())
    		{
    			data.setBossFight(true);
    			BufferedImage bossImg = data.getRandom().nextInt() % 2 == 0 ? Boss.helicopter1Img : Boss.helicopter2Img;
    			data.setBoss(new Boss(data.getLevel() * Boss.initHealth, Framework.frameWidth, Framework.frameHeight / 2 - bossImg.getHeight() / 2, bossImg));
    			data.getEnemyHelicopterList().clear();
    			EnemyHelicopter.spawnEnemies = false;
    		}
    		else if(EnemyHelicopter.spawnEnemies && gameTime - EnemyHelicopter.timeOfLastCreatedEnemy >= EnemyHelicopter.timeBetweenNewEnemies)
    		{
    			EnemyHelicopter eh = new EnemyHelicopter();
    			int xCoordinate = Framework.frameWidth;
    			int yCoordinate = data.getRandom().nextInt(Framework.frameHeight - EnemyHelicopter.helicopterBodyImg.getHeight());
    			eh.Initialize(xCoordinate, yCoordinate);
    			
    			// Add created enemy to the list of enemies.
    			data.getEnemyHelicopterList().add(eh);
                
    			// Speed up enemy speed and aperence.
    			EnemyHelicopter.speedUp();
                
    			// Sets new time for last created enemy.
    			EnemyHelicopter.timeOfLastCreatedEnemy = gameTime;
    		}
        /*if(gameTime - EnemyHelicopter.timeOfLastCreatedEnemy >= EnemyHelicopter.timeBetweenNewEnemies)
        {
            EnemyHelicopter eh = new EnemyHelicopter();
            int xCoordinate = Framework.frameWidth;
            int yCoordinate = random.nextInt(Framework.frameHeight - EnemyHelicopter.helicopterBodyImg.getHeight());
            eh.Initialize(xCoordinate, yCoordinate);
            // Add created enemy to the list of enemies.
            enemyHelicopterList.add(eh);
            
            // Speed up enemy speed and aperence.
            EnemyHelicopter.speedUp();
            
            // Sets new time for last created enemy.
            EnemyHelicopter.timeOfLastCreatedEnemy = gameTime;
        }*/
    }
    
    private void addPlayerExplosion()
    {
    		for(int exNum =0; exNum < 3; exNum++)
    			{
    				new Animation(data.getExplosionAnimImg(), 134, 134, 12, 45, false, data.getPlayer().xCoordinate + exNum*60,
    						data.getPlayer().yCoordinate - data.getRandom().nextInt(100), exNum * 200 +data.getRandom().nextInt(100));
    			}
    }
    private boolean isPlayerCrashed(Rectangle playerRectangle, Rectangle enemyRectangle)
    {
    		if(playerRectangle.intersects(enemyRectangle)){
    			// Add explosion of enemy helicopter.
    			addPlayerExplosion();
    			for(int exNum = 0; exNum < 3; exNum++){
    				Animation expAnim = new Animation(data.getExplosionAnimImg(), 134, 134, 12, 45, false, enemyRectangle.x + exNum*60,
                	enemyRectangle.y - data.getRandom().nextInt(100), exNum * 200 +data.getRandom().nextInt(100));
    				data.getExplosionsList().add(expAnim);
            }
            return true;
        }
        return false;
    }
    
    /**
     * Updates all enemies.
     * Move the helicopter and checks if he left the screen.
     * Updates helicopter animations.
     * Checks if enemy was destroyed.
     * Checks if any enemy collision with player.
     */
    private void updateEnemies(long gameTime)
    {
    		if(data.isBossFight()) {
    			// Update boss position
    			data.getBoss().updateVelocity(data.getBulletsList(), data.getRocketsList());
    			data.getBoss().update();
    			
    			// Create the bullets
    			/*if(gameTime - boss.lastBulletSpawnTime >= Boss.timeBetweenBullets)
    			{
    				boss.lastBulletSpawnTime = gameTime;
    				bossBulletsList.add(boss.spawnBullet((double)player.xCoordinate, (double)player.yCoordinate));
    			}*/
    			
    			if(isPlayerCrashed(new Rectangle(data.getPlayer().xCoordinate, data.getPlayer().yCoordinate, data.getPlayer().helicopterBodyImg.getWidth(), data.getPlayer().helicopterBodyImg.getHeight()), new Rectangle((int)data.getBoss().xCoordinate, (int)data.getBoss().yCoordinate, data.getBoss().helicopterImg.getWidth(), data.getBoss().helicopterImg.getHeight())))
    			{
    				data.getPlayer().health -= 500;
    				data.setBossFight(false);
    			}
    			else if(data.getBoss().health <= 0)
    			{
    				data.setLevel(data.getLevel() + 1);
    				data.setBossFight(false);
    				
    				// Boss explosion
    				Animation expAnim = new Animation(data.getExplosionAnimImg(), 134, 134, 12, 45, false, (int)data.getBoss().xCoordinate, (int)data.getBoss().yCoordinate - data.getExplosionAnimImg().getHeight() / 3, 0);
    				data.getExplosionsList().add(expAnim);
    				
    				Sound bossbomb = new Sound("bomb.mp3", false);
    				bossbomb.start();
    				
    				EnemyHelicopter.spawnEnemies = true;
    				
//    				bossBulletsList.clear();
    			}
    		}
    		else
    		{
    			for(int i = 0; i < data.getEnemyHelicopterList().size(); i++)
    			{
    				EnemyHelicopter eh = data.getEnemyHelicopterList().get(i);
    		            
    				eh.Update();
    		            
    				// Is chrashed with player?
    				Rectangle playerrectangle = new Rectangle(data.getPlayer().xCoordinate, data.getPlayer().yCoordinate, data.getPlayer().helicopterBodyImg.getWidth(), data.getPlayer().helicopterBodyImg.getHeight());
    				Rectangle enemyrectangle = new Rectangle(eh.xCoordinate, eh.yCoordinate, EnemyHelicopter.helicopterBodyImg.getWidth(), EnemyHelicopter.helicopterBodyImg.getHeight());
    				if(playerrectangle.intersects(enemyrectangle)){
    					data.getPlayer().health -= 50;
    					
    					// Remove helicopter from the list.
    					data.getEnemyHelicopterList().remove(i);
    					
    					Sound helibomb = new Sound("bomb.mp3", false);
    					helibomb.start();
    					
    					// Add explosion of player helicopter.
    					for(int exNum = 0; exNum < 3; exNum++)
    					{
    						Animation expAnim = new Animation(data.getExplosionAnimImg(), 134, 134, 12, 45, false, data.getPlayer().xCoordinate + exNum*60, data.getPlayer().yCoordinate - data.getRandom().nextInt(100), exNum * 200 +data.getRandom().nextInt(100));
    						data.getExplosionsList().add(expAnim);
    					}
    					// Add explosion of enemy helicopter.
    					for(int exNum = 0; exNum < 3; exNum++)
    					{
    						Animation expAnim = new Animation(data.getExplosionAnimImg(), 134, 134, 12, 45, false, eh.xCoordinate + exNum*60, eh.yCoordinate - data.getRandom().nextInt(100), exNum * 200 +data.getRandom().nextInt(100));
    						data.getExplosionsList().add(expAnim);
    					}
    		                
    					// Because player crashed with enemy the game will be over so we don't need to check other enemies.
    					break;
    				}
    				
    				// Check health.
    				if(eh.health <= 0)
    				{
    					// Add explosion of helicopter.
    					Animation expAnim = new Animation(data.getExplosionAnimImg(), 134, 134, 12, 45, false, eh.xCoordinate, eh.yCoordinate - data.getExplosionAnimImg().getHeight()/3, 0); // Substring 1/3 explosion image height (explosionAnimImg.getHeight()/3) so that explosion is drawn more at the center of the helicopter.
    					data.getExplosionsList().add(expAnim);
    					
    					// Increase the destroyed enemies counter.
    					data.setDestroyedEnemies(data.getDestroyedEnemies() + 1);
    		                
    					// Remove helicopter from the list.
    					data.getEnemyHelicopterList().remove(i);
    		                
    					// Helicopter was destroyed so we can move to next helicopter.
    					continue;
    				}
    				
    				// If the current enemy is left the screen we remove him from the list and update the runAwayEnemies variable.
    				if(eh.isLeftScreen())
    				{
    					data.getEnemyHelicopterList().remove(i);
    					data.setRunAwayEnemies(data.getRunAwayEnemies() + 1);
    				}
    			}
    		}
    
        /*for(int i = 0; i < enemyHelicopterList.size(); i++)
        {
            EnemyHelicopter eh = enemyHelicopterList.get(i);
            
            eh.Update();
            
            // Is chrashed with player?
            Rectangle playerrectangle = new Rectangle(player.xCoordinate, player.yCoordinate, player.helicopterBodyImg.getWidth(), player.helicopterBodyImg.getHeight());
            Rectangle enemyrectangle = new Rectangle(eh.xCoordinate, eh.yCoordinate, EnemyHelicopter.helicopterBodyImg.getWidth(), EnemyHelicopter.helicopterBodyImg.getHeight());
            if(playerrectangle.intersects(enemyrectangle)){
                player.health -= 50;
                
                // Remove helicopter from the list.
                enemyHelicopterList.remove(i);
                
                // Add explosion of player helicopter.
                for(int exNum = 0; exNum < 3; exNum++){
                    Animation expAnim = new Animation(explosionAnimImg, 134, 134, 12, 45, false, player.xCoordinate + exNum*60, player.yCoordinate - random.nextInt(100), exNum * 200 +random.nextInt(100));
                    explosionsList.add(expAnim);
                }
                // Add explosion of enemy helicopter.
                for(int exNum = 0; exNum < 3; exNum++){
                    Animation expAnim = new Animation(explosionAnimImg, 134, 134, 12, 45, false, eh.xCoordinate + exNum*60, eh.yCoordinate - random.nextInt(100), exNum * 200 +random.nextInt(100));
                    explosionsList.add(expAnim);
                }
                
                // Because player crashed with enemy the game will be over so we don't need to check other enemies.
                break;
            }
            
            // Check health.
            if(eh.health <= 0){
                // Add explosion of helicopter.
                Animation expAnim = new Animation(explosionAnimImg, 134, 134, 12, 45, false, eh.xCoordinate, eh.yCoordinate - explosionAnimImg.getHeight()/3, 0); // Substring 1/3 explosion image height (explosionAnimImg.getHeight()/3) so that explosion is drawn more at the center of the helicopter.
                explosionsList.add(expAnim);

                // Increase the destroyed enemies counter.
                destroyedEnemies++;
                
                // Remove helicopter from the list.
                enemyHelicopterList.remove(i);
                
                // Helicopter was destroyed so we can move to next helicopter.
                continue;
            }
            
            // If the current enemy is left the screen we remove him from the list and update the runAwayEnemies variable.
            if(eh.isLeftScreen())
            {
                enemyHelicopterList.remove(i);
                runAwayEnemies++;
            }*/
    }
    
    /**
     * Update bullets. 
     * It moves bullets.
     * Checks if the bullet is left the screen.
     * Checks if any bullets is hit any enemy.
     */
    private void updateBullets()
    {
        for(int i = 0; i < data.getBulletsList().size(); i++)
        {
            Bullet bullet = data.getBulletsList().get(i);
            
            // Move the bullet.
            bullet.Update();
            
            // Is left the screen?
            if(bullet.isItLeftScreen()){
                data.getBulletsList().remove(i);
                // Bullet have left the screen so we removed it from the list and now we can continue to the next bullet.
                continue;
            }
            
            // Did hit any enemy?
            // Rectangle of the bullet image.
            Rectangle bulletRectangle = new Rectangle((int)bullet.xCoordinate, (int)bullet.yCoordinate, Bullet.bulletImg.getWidth(), Bullet.bulletImg.getHeight());
            // Go trough all enemis.
            for(int j = 0; j < data.getEnemyHelicopterList().size(); j++)
            {
                EnemyHelicopter eh = data.getEnemyHelicopterList().get(j);

                // Current enemy rectangle.
                Rectangle enemyrectangle = new Rectangle(eh.xCoordinate, eh.yCoordinate, EnemyHelicopter.helicopterBodyImg.getWidth(), EnemyHelicopter.helicopterBodyImg.getHeight());

                // Is current bullet over currnet enemy?
                if(bulletRectangle.intersects(enemyrectangle))
                {
                    // Bullet hit the enemy so we reduce his health.
                    eh.health -= Bullet.damagePower;
                    
                    // Bullet was also destroyed so we remove it.
                    data.getBulletsList().remove(i);
                    
                    // That bullet hit enemy so we don't need to check other enemies.
                    break;
                }
            }
            // Check if boss is hit
            if(data.isBossFight() && !data.getBoss().invincible)
            {
            		Rectangle bossRectangle = new Rectangle((int)data.getBoss().xCoordinate, (int)data.getBoss().yCoordinate, data.getBoss().helicopterImg.getWidth(), data.getBoss().helicopterImg.getHeight());
            		if(bulletRectangle.intersects(bossRectangle))
            		{
            				data.getBoss().health -= Bullet.damagePower;
            				data.getBulletsList().remove(i);
            		}
            }
            /*for(int k=0; k < bossBulletsList.size(); ++k)
            {
            		Bullet bulletBoss = bossBulletsList.get(k);
            		
            		bulletBoss.Update();
            		
            		// Is left the screen?
            		if(bullet.isItLeftScreen()) 
            		{
            			bossBulletsList.remove(i--);
            			// Bullet have left the screen so we removed it from the list and now we can continue to the next bullet.
            			continue;
            		}
                    
            		Rectangle bulletBossRectangle = new Rectangle((int)bullet.xCoordinate, (int)bullet.yCoordinate,
            			bulletBoss.bulletImg.getWidth(),
            			bulletBoss.bulletImg.getHeight()),
            			playerRectangle = new Rectangle(player.xCoordinate, player.yCoordinate, player.helicopterBodyImg.getWidth(), player.helicopterBodyImg.getHeight());
                    if(player.health > 0 && bulletBossRectangle.intersects(playerRectangle)) 
                    {
                    		player.health -= bullet.damagePoints;
                    		if(player.health <= 0) 
                    		{
                    			addPlayerExplosion();
                    		}
                    }
                    bossBulletsList.remove(i--);
            	}*/
        	}
    }
  


    /**
     * Update rockets. 
     * It moves rocket and add smoke behind it.
     * Checks if the rocket is left the screen.
     * Checks if any rocket is hit any enemy.
     * 
     * @param gameTime Game time.
     */
    private void updateRockets(long gameTime)
    {
        for(int i = 0; i < data.getRocketsList().size(); i++)
        {
            Rocket rocket = data.getRocketsList().get(i);
            
            // Moves the rocket.
            rocket.Update();
            
            // Checks if it is left the screen.
            if(rocket.isItLeftScreen())
            {
                data.getRocketsList().remove(i);
                // Rocket left the screen so we removed it from the list and now we can continue to the next rocket.
                continue;
            }
            
            // Creates a rocket smoke.
            RocketSmoke rs = new RocketSmoke();
            int xCoordinate = rocket.xCoordinate - RocketSmoke.smokeImg.getWidth(); // Subtract the size of the rocket smoke image (rocketSmokeImg.getWidth()) so that smoke isn't drawn under/behind the image of rocket.
            int yCoordinte = rocket.yCoordinate - 5 + data.getRandom().nextInt(6); // Subtract 5 so that smoke will be at the middle of the rocket on y coordinate. We rendomly add a number between 0 and 6 so that the smoke line isn't straight line.
            rs.Initialize(xCoordinate, yCoordinte, gameTime, rocket.currentSmokeLifeTime);
            data.getRocketSmokeList().add(rs);
            
            // Because the rocket is fast we get empty space between smokes so we need to add more smoke. 
            // The higher is the speed of rockets, the bigger are empty spaces.
            int smokePositionX = 5 + data.getRandom().nextInt(8); // We will draw this smoke a little bit ahead of the one we draw before.
            rs = new RocketSmoke();
            xCoordinate = rocket.xCoordinate - RocketSmoke.smokeImg.getWidth() + smokePositionX; // Here we need to add so that the smoke will not be on the same x coordinate as previous smoke. First we need to add 5 because we add random number from 0 to 8 and if the random number is 0 it would be on the same coordinate as smoke before.
            yCoordinte = rocket.yCoordinate - 5 + data.getRandom().nextInt(6); // Subtract 5 so that smoke will be at the middle of the rocket on y coordinate. We rendomly add a number between 0 and 6 so that the smoke line isn't straight line.
            rs.Initialize(xCoordinate, yCoordinte, gameTime, rocket.currentSmokeLifeTime);
            data.getRocketSmokeList().add(rs);
            
            // Increase the life time for the next piece of rocket smoke.
            rocket.currentSmokeLifeTime *= 1.02;
            
            // Checks if current rocket hit any enemy.
            if( checkIfRocketHitEnemy(rocket) )
                // Rocket was also destroyed so we remove it.
                data.getRocketsList().remove(i);
            
         // Check if boss is hit
            if(data.isBossFight() && !data.getBoss().invincible)
            {
            		Rectangle bossRectangle = new Rectangle((int)data.getBoss().xCoordinate, (int)data.getBoss().yCoordinate, data.getBoss().helicopterImg.getWidth(), data.getBoss().helicopterImg.getHeight());
            		Rectangle rocketRectangle = new Rectangle(rocket.xCoordinate, rocket.yCoordinate, 2, Rocket.rocketImg.getHeight());
            		if(rocketRectangle.intersects(bossRectangle))
            		{
            				data.getBoss().health -= rocket.damagePower;
            				data.getRocketsList().remove(i);
            		}
            }
        }
    }
    
    
    /**
     * Update missiles.
     * It moves missiles and add smoke behind it.
     * Checks if the missile is left the screen.
     * Checks if any missile is hit any enemy.
     * 
     * @param gameTime
     */
    private void updateMissile(long gameTime)
    {
    		for(int i = 0; i < data.getMissilesList().size(); i++)
    		{
    			Missile missile = data.getMissilesList().get(i);
    			
    			// Moves the missile.
    			missile.Update();
    			
    			// Finds the enemy target.
    			if (data.getEnemyHelicopterList().size() > 0) 
    			{
    				for (int j = 0; j < data.getEnemyHelicopterList().size(); j++) 
    				{
    					EnemyHelicopter eh = data.getEnemyHelicopterList().get(j);
    					// If rocket's nose is in front of the enemy helicopter's tail, then...
    					if (missile.xCoordinate + missile.rocketImg.getWidth() < eh.xCoordinate + eh.helicopterBodyImg.getWidth()) 
    					{
    						// ...find the enemy helicopter
    						missile.findTarget(eh);
    						break;
    					}
    				}
    			}
    			
    			// Checks if it is left the screen.
    			if(missile.isItLeftScreen())
    			{
    				data.getMissilesList().remove(i);
    				// Missile left the screen so we removed it from the list and now we can continue to the next missile.
    				continue;
    			}
    			
    			// Creates a missile smoke.
    			RocketSmoke rs = new RocketSmoke();
    		    int xCoordinate = missile.xCoordinate - RocketSmoke.smokeImg.getWidth(); // Subtract the size of the rocket smoke image (rocketSmokeImg.getWidth()) so that smoke isn't drawn under/behind the image of rocket.
            int yCoordinte = missile.yCoordinate - 5 + data.getRandom().nextInt(6); // Subtract 5 so that smoke will be at the middle of the rocket on y coordinate. We randomly add a number between 0 and 6 so that the smoke line isn't straight line.
            rs.Initialize(xCoordinate, yCoordinte, gameTime, missile.currentSmokeLifeTime);
            data.getRocketSmokeList().add(rs);
    			
            // Because the missile is fast we get empty space between smokes so we need to add more smoke. 
            // The higher is the speed of missiles, the bigger are empty spaces.
            int smokePositionX = 5 + data.getRandom().nextInt(8); // We will draw this smoke a little bit ahead of the one we draw before.
            rs = new RocketSmoke();
            xCoordinate = missile.xCoordinate - RocketSmoke.smokeImg.getWidth() + smokePositionX; // Here we need to add so that the smoke will not be on the same x coordinate as previous smoke. First we need to add 5 because we add random number from 0 to 8 and if the random number is 0 it would be on the same coordinate as smoke before.
            yCoordinte = missile.yCoordinate - 5 + data.getRandom().nextInt(6); // Subtract 5 so that smoke will be at the middle of the rocket on y coordinate. We randomly add a number between 0 and 6 so that the smoke line isn't straight line.
            rs.Initialize(xCoordinate, yCoordinte, gameTime, missile.currentSmokeLifeTime);
            data.getRocketSmokeList().add(rs);
            
            // Increase the life time for the next piece of rocket smoke.
            missile.currentSmokeLifeTime *= 1.02;
            
            // Checks if current missile hit any enemy.
            if( checkIfMissileHitEnemy(missile) )
            		// Missile was also destroyed so we remove it.
            		data.getMissilesList().remove(i);
            
         // Check if boss is hit
            if(data.isBossFight() && !data.getBoss().invincible)
            {
            		Rectangle bossRectangle = new Rectangle((int)data.getBoss().xCoordinate, (int)data.getBoss().yCoordinate, data.getBoss().helicopterImg.getWidth(), data.getBoss().helicopterImg.getHeight());
            		Rectangle missileRectangle = new Rectangle(missile.xCoordinate, missile.yCoordinate, 2, Rocket.rocketImg.getHeight());
            		if(missileRectangle.intersects(bossRectangle))
            		{
            				data.getBoss().health -= missile.damagePower;
            				data.getMissilesList().remove(i);
            		}
            }
    		}
    }
    
    private void updateBonuses(long gameTime) {
    	// Generate new bonuses
    		if(data.getRandom().nextInt() % 500 == 0) {
//    			double speed = 50 + random.nextDouble() * 2.7001;
//    			double speed = 50;
    			switch(data.getRandom().nextInt() % 3) {
    			case 0:
//    				bonusList.add(new HealthBonus(random.nextInt(Framework.frameWidth - HealthBonus.image.getWidth()),
//    					-HealthBonus.image.getHeight(), speed, 50));
    				data.getBonusList().add(new HealthBonus(data.getRandom().nextInt(Framework.frameWidth - HealthBonus.image.getWidth()),
        					data.getRandom().nextInt(Framework.frameHeight - HealthBonus.image.getHeight()), 50));
    				break;
    			case 1:
    				data.getBonusList().add(new BulletBonus(data.getRandom().nextInt(Framework.frameWidth - BulletBonus.image.getWidth()),
    						data.getRandom().nextInt(Framework.frameHeight -BulletBonus.image.getHeight()), 100));
    				break;
    			case 2:
    				data.getBonusList().add(new RocketBonus(data.getRandom().nextInt(Framework.frameWidth - RocketBonus.image.getWidth()),
    						data.getRandom().nextInt(Framework.frameHeight-RocketBonus.image.getHeight()), 5));
    				break;
    			/*case 3:
    				bonusList.add(new ShieldBonus(random.nextInt(Framework.frameWidth - ShieldBonus.image.getWidth()),
    					-ShieldBonus.image.getHeight(), 10, speed));
    				break;*/
    			}
    		}
    	
	    	// Update bonuses
	    	Rectangle playerRect = new Rectangle(data.getPlayer().xCoordinate, data.getPlayer().yCoordinate,
	    			data.getPlayer().helicopterBodyImg.getWidth(), data.getPlayer().helicopterBodyImg.getHeight());
	    	for(int i = 0; i < data.getBonusList().size(); ++i) 
	    		{
	    		Bonus bonus = data.getBonusList().get(i);
	    		
//	    		bonus.update();
	    		
	    		Rectangle bonusRect = new Rectangle((int)bonus.xCoordinate, (int)bonus.yCoordinate,
	    				bonus.image.getWidth(), bonus.image.getHeight());
	    		
	    		if(playerRect.intersects(bonusRect) || bonusRect.intersects(playerRect)) 
	    			{
	    			bonus.apply(data.getPlayer());
//	    			bonus.consumeTime = gameTime;a
	    			data.getBonusList().remove(i--);
	    			} 
	    		else if(bonus.isLeftScreen())
	    			{
	    			data.getBonusList().remove(i--);
	    			}
	    		}
    		
    }
    
    /**
     * Checks if the given rocket is hit any of enemy helicopters.
     * 
     * @param rocket Rocket to check.
     * @return True if it hit any of enemy helicopters, false otherwise.
     */
    private boolean checkIfRocketHitEnemy(Rocket rocket)
    {
        boolean didItHitEnemy = false;
        
        // Current rocket rectangle. // I inserted number 2 insted of rocketImg.getWidth() because I wanted that rocket 
        // is over helicopter when collision is detected, because actual image of helicopter isn't a rectangle shape. (We could calculate/make 3 areas where helicopter can be hit and checks these areas, but this is easier.)
        Rectangle rocketRectangle = new Rectangle(rocket.xCoordinate, rocket.yCoordinate, 2, Rocket.rocketImg.getHeight());
        
        // Go trough all enemis.
        for(int j = 0; j < data.getEnemyHelicopterList().size(); j++)
        {
            EnemyHelicopter eh = data.getEnemyHelicopterList().get(j);

            // Current enemy rectangle.
            Rectangle enemyrectangle = new Rectangle(eh.xCoordinate, eh.yCoordinate, EnemyHelicopter.helicopterBodyImg.getWidth(), EnemyHelicopter.helicopterBodyImg.getHeight());

            // Is current rocket over currnet enemy?
            if(rocketRectangle.intersects(enemyrectangle))
            {
                didItHitEnemy = true;
                
                // Rocket hit the enemy so we reduce his health.
                eh.health -= Rocket.damagePower;
                
                // Rocket hit enemy so we don't need to check other enemies.
                break;
            }
        }
        
        return didItHitEnemy;
    }
    
    
    /**
     * Checks if the given missile is hit any of enemy helicopters.
     * 
     * @param missile
     * @return True if it hit any of enemy helicopeters, false otherwise.
     */
    private boolean checkIfMissileHitEnemy(Missile missile)
    {
    		boolean didItHitEnemy = false;
    		
    		// Current missile rectangle. // I inserted number 2 instead of rocketImg.getWidth() because I wanted that missile
    		// is over helicopter when collision is detected, because actual image of helicopter isn't a rectangle shape.
    		Rectangle missileRectangle = new Rectangle(missile.xCoordinate, missile.yCoordinate, 2, Rocket.rocketImg.getHeight());
    		
    		// Go through all enemies.
    		for(int j = 0; j < data.getEnemyHelicopterList().size(); j++ )
    		{
    			EnemyHelicopter eh = data.getEnemyHelicopterList().get(j);
    			
    			// Current enemy rectangle
    			Rectangle enemyRectangle = new Rectangle(eh.xCoordinate, eh.yCoordinate, EnemyHelicopter.helicopterBodyImg.getWidth(), EnemyHelicopter.helicopterBodyImg.getHeight());
    			
    			// Is current rocket over current enemy?
    			if(missileRectangle.intersects(enemyRectangle))
    			{
    				didItHitEnemy = true;
    				
    				// Missile hit the enemy so we reduce his health.
    				eh.health -= Missile.damagePower;
    				
    				// Missile hit enemy so we don't need to check other enemies.
    				break;
    			}
    		}
    		
    		return didItHitEnemy;
    }
    
    /**
     * Updates smoke of all the rockets.
     * If the life time of the smoke is over then we delete it from list.
     * It also changes a transparency of a smoke image, so that smoke slowly disappear.
     * 
     * @param gameTime Game time.
     */
    private void updateRocketSmoke(long gameTime)
    {
        for(int i = 0; i < data.getRocketSmokeList().size(); i++)
        {
            RocketSmoke rs = data.getRocketSmokeList().get(i);
            
            // Is it time to remove the smoke.
            if(rs.didSmokeDisapper(gameTime))
                data.getRocketSmokeList().remove(i);
            
            // Set new transparency of rocket smoke image.
            rs.updateTransparency(gameTime);
        }
    }
    
    /**
     * Updates all the animations of an explosion and remove the animation when is over.
     */
    private void updateExplosions()
    {
        for(int i = 0; i < data.getExplosionsList().size(); i++)
        {
            // If the animation is over we remove it from the list.
            if(!data.getExplosionsList().get(i).active)
                data.getExplosionsList().remove(i);
        }
    }
	private void assistanceSystem()
    {
    			
    		// Finds the enemy target.
    		if (data.getEnemyHelicopterList().size() > 0) 
    		{
    			for (int j = 0; j < data.getEnemyHelicopterList().size(); j++) 
    			{
    				EnemyHelicopter eh = data.getEnemyHelicopterList().get(j);
    				data.getPlayer().preventCrash(eh);
    				break;
    			}
    		}
    	}
}
