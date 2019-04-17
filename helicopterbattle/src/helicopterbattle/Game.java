package helicopterbattle;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 * Actual game.
 * 
 * @author www.gametutorial.net
 */

public class Game {
	
	// Enemy helicopters.
    private static ArrayList<EnemyHelicopter> enemyHelicopterList = new ArrayList<EnemyHelicopter>();
 // List of all the machine gun bullets.
    private static ArrayList<Bullet> bulletsList;
    // List of all the missiles
    private static ArrayList<Missile> missilesList;
    // List of all the rockets.
    private static ArrayList<Rocket> rocketsList;
    // List of all the rockets smoke.
    private static ArrayList<RocketSmoke> rocketSmokeList;
    // List of all the rockets for boss
    private static ArrayList<Rocket> bossRocketsList;
    
    public static int level;
    
    // Statistics (destroyed enemies, run away enemies)
    public static int runAwayEnemies;
    public static int destroyedEnemies;
    
	private static int score;
    public static String highScore = "";
    
    public static ArrayList<EnemyHelicopter> getEnemyHelicopterList(){
    	return enemyHelicopterList;
    } 
    
    public static ArrayList<Bullet> getBulletsList(){
    	return bulletsList;
    } 
    
    public static ArrayList<Missile> getMissilesList(){
    	return missilesList;
    } 
    
    public static ArrayList<Rocket> getRocketsList(){
    	return rocketsList;
    } 

    public static ArrayList<RocketSmoke> getRocketSmokeList(){
    	return rocketSmokeList;
    } 

    public static ArrayList<Rocket> getBossRocketsList(){
    	return bossRocketsList;
    } 
    
    
    static GameData data = new GameData(new Shooting());

    public Game(int helicopterSelect)
    {
        Framework.gameState = Framework.GameState.GAME_CONTENT_LOADING;

        // Sets variables and objects for the game.
        Initialize(helicopterSelect);
        // Load game files (images, sounds, ...)
        LoadContent();
        
        Framework.gameState = Framework.GameState.PLAYING;
        
//        Thread threadForInitGame = new Thread() {
//            @Override
//            public void run(){
//
//            }
//        };
//        threadForInitGame.start();
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
        
        enemyHelicopterList = new ArrayList<EnemyHelicopter>();
        
        data.setExplosionsList(new ArrayList<Animation>());
        
        bulletsList = new ArrayList<Bullet>();
//        bossBulletsList = new ArrayList<Bullet>();
        bossRocketsList = new ArrayList<Rocket>();
        
        data.setBonusList(new ArrayList<Bonus>());
        
        missilesList = new ArrayList<Missile>();
        rocketsList = new ArrayList<Rocket>();
        rocketSmokeList = new ArrayList<RocketSmoke>();
        
        // Moving images.
        data.setCloudLayer1Moving(new MovingBackground());
        data.setCloudLayer2Moving(new MovingBackground());
        data.setMountainsMoving(new MovingBackground());
        data.setGroundMoving(new MovingBackground());
        
        data.setFont(new Font("monospaced", Font.BOLD, 18));
        data.setScoreFont(new Font("arial", Font.BOLD, 60));
        
        runAwayEnemies = 0;
        destroyedEnemies = 0;
        data.setNumOfEnemiesForBoss(5);
        level = 1;
        score = 0;
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
            
            // Images of rocket for boss
            URL bossRocket1ImgUrl = this.getClass().getResource("/helicopterbattle/resources/images/rocket_boss.png");
            Rocket.bossRocket1Img = ImageIO.read(bossRocket1ImgUrl);
            
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
        Rocket.timeOfLastCreatedBossRocket = 0;
        
        // Empty all the lists.
        enemyHelicopterList.clear();
        bulletsList.clear();
        rocketsList.clear();
        rocketSmokeList.clear();
        data.getExplosionsList().clear();
        missilesList.clear();
//        bossBulletsList.clear();
        bossRocketsList.clear();
        data.getBonusList().clear();
        
        // Statistics
        runAwayEnemies = 0;
        destroyedEnemies = 0;
        level = 1;
        score = 0;
        
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
        if(data.getPlayer().data.getNumberOfAmmo() <= 0 && 
           data.getPlayer().data.getNumberOfRockets() <= 0 &&
           data.getPlayer().data.getNumberOfMissiles() <= 0 &&
           bulletsList.isEmpty() && 
           rocketsList.isEmpty() && 
           data.getExplosionsList().isEmpty() &&
           missilesList.isEmpty() &&
           rocketSmokeList.isEmpty())
        {
            
        	Sound gameover = new Sound("gameover.mp3", false);
        	gameover.start();
        	
        	Framework.gameState = Framework.GameState.GAMEOVER;
        
        	return;
        }
        // If player is alive we update him.
        if(isPlayerAlive()){
            data.getSh().isPlayerShooting(gameTime, mousePosition, data.getPlayer());
            data.getSh().didPlayerFiredRocket(gameTime, data.getPlayer());
            data.getSh().didPlayerFiredMissile(gameTime, data.getPlayer());
            data.getPlayer().isMoving();
            data.getPlayer().Update();
        }
        
        /* Mouse */
        /*if(player.helicopterName == "Chinook" || player.helicopterName == "Black Shark")
        {
        		limitMousePosition(mousePosition);
	    }*/
        
        /* Bullets */
        data.getSh().updateBullets(data.getBoss(), data.isBossFight());
        
        /* Rockets */
        data.getSh().updateMissile(gameTime, data.getBoss(), data.isBossFight(), data.getRandom()); // It also checks for collisions (if any of the missiles hit any of the enemy helicopter).
        data.getSh().updateRockets(gameTime, data.getBoss(), data.isBossFight(), data.getRandom()); // It also checks for collisions (if any of the rockets hit any of the enemy helicopter).
        data.getSh().updateRocketSmoke(gameTime);
        data.getSh().updateBossRockets(gameTime, data.getPlayer());
        
        /* Enemies */
        createEnemyHelicopter(gameTime);
        updateEnemies();
        
        /* Explosions */
        updateExplosions();
        
        updateBonuses();
        
        /*if(bossFight && boss.rageMode)
        {
        		updateStones(gameTime);
        		updateStonesSmoke(gameTime);
        }*/
        if(data.getPlayer().data.getHelicopterName().equals("Chinook") || data.getPlayer().data.getHelicopterName().equals("Black Shark") 
           || data.getPlayer().data.getHelicopterName().equals("SNOC") || data.getPlayer().data.getHelicopterName().equals("Viper"))
            data.assistanceSystem();
    }
    
    /**
     * Draw the game to the screen.
     * 
     * @param g2d Graphics2D
     * @param mousePosition current mouse position.
     */
    public void Draw(Graphics2D g2d, Point mousePosition, long gameTime)
    {      
        data.drawGame(g2d);
        
        g2d.drawString(formatTime(gameTime), Framework.frameWidth/2 - 45, 21);
        
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
        g2d.setFont(data.getScoreFont());
        g2d.setColor(Color.white);
		g2d.drawString("Score: "+getScore(), Framework.frameWidth/2 - 150, Framework.frameHeight/4 + 140);
    		
    		if(highScore.equals(""))
    		{
    			//init the highscore
//    			highScore = Score.getHighScore();
    			highScore = Score.getHighScore();
    		}
    		g2d.setColor(Color.darkGray);
    		g2d.drawString("High Score ", Framework.frameWidth/2 -150, Framework.frameHeight/4 -50);
    		g2d.drawString(""+highScore, Framework.frameWidth/2 -100, Framework.frameHeight/4 +30);

    }
    
    public static int getScore()
    {
    		score = 10 * (destroyedEnemies - runAwayEnemies) + 100 * (level-1);
    		if(score < 0)
    			score = 0;
    		return score;
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
    public static boolean isPlayerAlive()
    {
        return data.getPlayer().data.getHealth() > 0;
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
    			data.getSh().didBossFiredRocket(gameTime, data.getBoss());
    			return;
    		}
    		if(destroyedEnemies == level * data.getNumOfEnemiesForBoss())
    		{
    			data.setBossFight(true);
    			BufferedImage bossImg = data.getRandom().nextInt() % 2 == 0 ? Boss.helicopter1Img : Boss.helicopter2Img;
    			data.setBoss(new Boss(level * Boss.initHealth, Framework.frameWidth, Framework.frameHeight / 2 - bossImg.getHeight() / 2, bossImg));
    			
    			enemyHelicopterList.clear();
    			
       			data.getSh().didBossFiredRocket(gameTime, data.getBoss());
       			
    			EnemyHelicopter.spawnEnemies = false;
    			
    			
    		}
    		else if(EnemyHelicopter.spawnEnemies && gameTime - EnemyHelicopter.timeOfLastCreatedEnemy >= EnemyHelicopter.timeBetweenNewEnemies)
    		{
    			EnemyHelicopter eh = new EnemyHelicopter();
    			int xCoordinate = Framework.frameWidth;
    			int yCoordinate = data.getRandom().nextInt(Framework.frameHeight - EnemyHelicopter.helicopterBodyImg.getHeight());
    			eh.Initialize(xCoordinate, yCoordinate);
    			
    			// Add created enemy to the list of enemies.
    			enemyHelicopterList.add(eh);
                
    			// Speed up enemy speed and aperence.
    			EnemyHelicopter.speedUp();
                
    			// Sets new time for last created enemy.
    			EnemyHelicopter.timeOfLastCreatedEnemy = gameTime;
    		}
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
    private void updateEnemies()
    {
    		if(data.isBossFight()) {
    			// Update boss position
    			data.getBoss().updateVelocity(bulletsList, rocketsList);
    			data.getBoss().update();
    			
    			
    			if(isPlayerCrashed(new Rectangle(data.getPlayer().xCoordinate, data.getPlayer().yCoordinate, data.getPlayer().data.getHelicopterBodyImg().getWidth(), data.getPlayer().data.getHelicopterBodyImg().getHeight()), new Rectangle((int)data.getBoss().xCoordinate, (int)data.getBoss().yCoordinate, data.getBoss().helicopterImg.getWidth(), data.getBoss().helicopterImg.getHeight())))
    			{
    				data.getPlayer().data.setHealth(data.getPlayer().data.getHealth() - 500);
    				data.setBossFight(false);
    			}
    			else if(data.getBoss().health <= 0)
    			{
    				++level;
    				data.setBossFight(false);
    				
    				// Boss explosion
    				Animation expAnim = new Animation(data.getExplosionAnimImg(), 134, 134, 12, 45, false, (int)data.getBoss().xCoordinate, (int)data.getBoss().yCoordinate - data.getExplosionAnimImg().getHeight() / 3, 0);
    				data.getExplosionsList().add(expAnim);
    				
    				Sound bossbomb = new Sound("bomb.mp3", false);
    				bossbomb.start();
    				
    				bossRocketsList.clear();
    				
    				EnemyHelicopter.spawnEnemies = true;
    			}
    		}
    		else
    		{
    			for(int i = 0; i < enemyHelicopterList.size(); i++)
    			{
    				EnemyHelicopter eh = enemyHelicopterList.get(i);
    		            
    				eh.Update();
    		            
    				// Is chrashed with player?
    				Rectangle playerrectangle = new Rectangle(data.getPlayer().xCoordinate, data.getPlayer().yCoordinate, data.getPlayer().data.getHelicopterBodyImg().getWidth(), data.getPlayer().data.getHelicopterBodyImg().getHeight());
    				Rectangle enemyrectangle = new Rectangle(eh.xCoordinate, eh.yCoordinate, EnemyHelicopter.helicopterBodyImg.getWidth(), EnemyHelicopter.helicopterBodyImg.getHeight());
    				if(playerrectangle.intersects(enemyrectangle)){
    					data.getPlayer().data.setHealth(data.getPlayer().data.getHealth() - 50);
    					
    					// Remove helicopter from the list.
    					enemyHelicopterList.remove(i);
    					
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
    				}
    			}
    		}
    }
    

    
    private void updateBonuses() {
    	// Generate new bonuses
    		if(data.getRandom().nextInt() % 500 == 0) {
//    			double speed = 50 + random.nextDouble() * 2.7001;
//    			double speed = 50;
    			switch(data.getRandom().nextInt() % 3) {
    			case 0:
//    				bonusList.add(new HealthBonus(random.nextInt(Framework.frameWidth - HealthBonus.image.getWidth()),
//    					-HealthBonus.image.getHeight(), speed, 50));
    				data.getBonusList().add(new HealthBonus(data.getRandom().nextInt(Framework.frameWidth - HealthBonus.image.getWidth()),
        					data.getRandom().nextInt(Framework.frameHeight - HealthBonus.image.getHeight())));
    				break;
    			case 1:
    				data.getBonusList().add(new BulletBonus(data.getRandom().nextInt(Framework.frameWidth - BulletBonus.image.getWidth()),
    						data.getRandom().nextInt(Framework.frameHeight -BulletBonus.image.getHeight()), 100));
    				break;
    			case 2:
    				data.getBonusList().add(new RocketBonus(data.getRandom().nextInt(Framework.frameWidth - RocketBonus.image.getWidth()),
    						data.getRandom().nextInt(Framework.frameHeight-RocketBonus.image.getHeight()), 5));
    				break;
    			}
    		}
    	
	    	// Update bonuses
	    	Rectangle playerRect = new Rectangle(data.getPlayer().xCoordinate, data.getPlayer().yCoordinate,
	    			data.getPlayer().data.getHelicopterBodyImg().getWidth(), data.getPlayer().data.getHelicopterBodyImg().getHeight());
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
}
