package helicopterbattle;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
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
    // Use this to generate a random number.
    private Random random;
    
    // We will use this for seting mouse position.
    private Robot robot;
    
    // Player - helicopter that is managed by player.
    private PlayerHelicopter player;
    
    // Enemy helicopters.
    private ArrayList<EnemyHelicopter> enemyHelicopterList = new ArrayList<EnemyHelicopter>();
    
    // Explosions
    private ArrayList<Animation> explosionsList;
    private BufferedImage explosionAnimImg;
    
    // List of all the machine gun bullets.
    private ArrayList<Bullet> bulletsList;
    
    // List of all the machine gun bullets of the boss.
//    private ArrayList<Bullet> bossBulletsList;
    
    // List of all the missiles
    private ArrayList<Missile> missilesList;
    
    // List of all the rockets.
    private ArrayList<Rocket> rocketsList;
    
    // List of all the rockets smoke.
    private ArrayList<RocketSmoke> rocketSmokeList;
    
    // List of all the bonuses
    private ArrayList<Bonus> bonusList;
    
    // Image for the sky color.
    private BufferedImage skyColorImg;
    
    // Images for white spot on the sky.
    private BufferedImage cloudLayer1Img;
    private BufferedImage cloudLayer2Img;
    // Images for mountains and ground.
    private BufferedImage mountainsImg;
    private BufferedImage groundImg;
    
    // Objects of moving images.
    private MovingBackground cloudLayer1Moving;
    private MovingBackground cloudLayer2Moving;
    private MovingBackground mountainsMoving;
    private MovingBackground groundMoving;
    
    // Image of mouse cursor.
    private BufferedImage mouseCursorImg;
    
    // Font that we will use to write statistic to the screen.
    private Font font;
    private Font scoreFont;
    
    private int level;
    
    // Statistics (destroyed enemies, run away enemies)
    private int runAwayEnemies;
    private int destroyedEnemies;
    
    private int statXCoordinate;

    private boolean bossFight;
    private int numOfEnemiesForBoss;
    private Boss boss;
    
    private int score;
    
    
    public Game(final int helicopterSelect)
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
        random = new Random();
        
        try {
            robot = new Robot();
        } catch (AWTException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        player = new PlayerHelicopter(Framework.frameWidth / 4, Framework.frameHeight / 4, helicopterSelect);
        
        enemyHelicopterList = new ArrayList<EnemyHelicopter>();
        
        explosionsList = new ArrayList<Animation>();
        
        bulletsList = new ArrayList<Bullet>();
//        bossBulletsList = new ArrayList<Bullet>();
        bonusList = new ArrayList<Bonus>();
        
        missilesList = new ArrayList<Missile>();
        rocketsList = new ArrayList<Rocket>();
        rocketSmokeList = new ArrayList<RocketSmoke>();
        
        // Moving images.
        cloudLayer1Moving = new MovingBackground();
        cloudLayer2Moving = new MovingBackground();
        mountainsMoving = new MovingBackground();
        groundMoving = new MovingBackground();
        
        font = new Font("monospaced", Font.BOLD, 18);
        scoreFont = new Font("arial", Font.BOLD, 60);
        
        runAwayEnemies = 0;
        destroyedEnemies = 0;
        numOfEnemiesForBoss = 5;
        level = 1;
        score = 0;
        bossFight = false;
        
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
            skyColorImg = ImageIO.read(skyColorImgUrl);
            URL cloudLayer1ImgUrl = this.getClass().getResource("/helicopterbattle/resources/images/cloud_layer_1.png");
            cloudLayer1Img = ImageIO.read(cloudLayer1ImgUrl);
            URL cloudLayer2ImgUrl = this.getClass().getResource("/helicopterbattle/resources/images/cloud_layer_2.png");
            cloudLayer2Img = ImageIO.read(cloudLayer2ImgUrl);
            URL mountainsImgUrl = this.getClass().getResource("/helicopterbattle/resources/images/mountains.png");
            mountainsImg = ImageIO.read(mountainsImgUrl);
            URL groundImgUrl = this.getClass().getResource("/helicopterbattle/resources/images/ground.png");
            groundImg = ImageIO.read(groundImgUrl);
            
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
            explosionAnimImg = ImageIO.read(explosionAnimImgUrl);
            
            // Image of mouse cursor.
            URL mouseCursorImgUrl = this.getClass().getResource("/helicopterbattle/resources/images/mouse_cursor.png");
            mouseCursorImg = ImageIO.read(mouseCursorImgUrl);
            
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
        cloudLayer1Moving.Initialize(cloudLayer1Img, -6, 0);
        cloudLayer2Moving.Initialize(cloudLayer2Img, -2, 0);
        mountainsMoving.Initialize(mountainsImg, -1, Framework.frameHeight - groundImg.getHeight() - mountainsImg.getHeight() + 40);
        groundMoving.Initialize(groundImg, -1.2, Framework.frameHeight - groundImg.getHeight());
    }
    
    
    /**
     * Restart game - reset some variables.
     */
    public void RestartGame()
    {
        player.Reset(Framework.frameWidth / 4, Framework.frameHeight / 4);
        
        EnemyHelicopter.restartEnemy();
        
        Bullet.timeOfLastCreatedBullet = 0;
        Rocket.timeOfLastCreatedRocket = 0;
        Missile.timeOfLastCreatedRocket = 0;
        
        // Empty all the lists.
        enemyHelicopterList.clear();
        bulletsList.clear();
        rocketsList.clear();
        rocketSmokeList.clear();
        explosionsList.clear();
        missilesList.clear();
//        bossBulletsList.clear();
        bonusList.clear();
        
        // Statistics
        runAwayEnemies = 0;
        destroyedEnemies = 0;
        level = 1;
        score = 0;
        
        bossFight = false;
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
        if( !isPlayerAlive() && explosionsList.isEmpty() ){
            Framework.gameState = Framework.GameState.GAMEOVER;
            return; // If player is destroyed, we don't need to do thing below.
        }
        // When a player is out of rockets and machine gun bullets, and all lists 
        // of bullets, rockets and explosions are empyt(end showing) we finish the game.
        if(player.numberOfAmmo <= 0 && 
           player.numberOfRockets <= 0 &&
           player.numberOfMissiles <= 0 &&
           bulletsList.isEmpty() && 
           rocketsList.isEmpty() && 
           explosionsList.isEmpty() &&
           missilesList.isEmpty() &&
           rocketSmokeList.isEmpty())
        {
            Framework.gameState = Framework.GameState.GAMEOVER;
            return;
        }
        // If player is alive we update him.
        if(isPlayerAlive()){
            isPlayerShooting(gameTime, mousePosition);
            didPlayerFiredRocket(gameTime);
            didPlayerFiredMissile(gameTime);
            player.isMoving();
            player.Update();
        }
        
        /* Mouse */
        if(player.helicopterName == "Chinook" || player.helicopterName == "Black Shark")
        {
        		limitMousePosition(mousePosition);
	    }
        
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
        g2d.drawImage(skyColorImg, 0, 0, Framework.frameWidth, Framework.frameHeight, null);
        
        // Moving images.
        mountainsMoving.Draw(g2d);
        groundMoving.Draw(g2d);
        cloudLayer2Moving.Draw(g2d);
        
        if(isPlayerAlive())
        {
            player.Draw(g2d);
        		statXCoordinate = 10 + player.helicopterProfileImg.getWidth() + 10;
        }
		
        // Draws helicopter's profile
        player.DrawAvatar(g2d);
        		        		
        // Draws all the enemies.
        for(int i = 0; i < enemyHelicopterList.size(); i++)
        {
            enemyHelicopterList.get(i).Draw(g2d);
        }
        
        if(bossFight)
        {
        		boss.Draw(g2d);
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
        for(int i = 0; i < bulletsList.size(); i++)
        {
            bulletsList.get(i).Draw(g2d);
        }
        
        // Draws all the missiles.
        for(int i = 0; i < missilesList.size(); i++)
        {
        		missilesList.get(i).Draw(g2d);
        }
        
        // Draws all the rockets. 
        for(int i = 0; i < rocketsList.size(); i++)
        {
            rocketsList.get(i).Draw(g2d);
        }
        // Draws smoke of all the rockets.
        for(int i = 0; i < rocketSmokeList.size(); i++)
        {
            rocketSmokeList.get(i).Draw(g2d);
        }
        
        // Draw all explosions.
        for(int i = 0; i < explosionsList.size(); i++)
        {
            explosionsList.get(i).Draw(g2d);
        }
        
        // 	Draw all bonuses
        for(Bonus bonus : bonusList) 
        {
        		bonus.Draw(g2d);
        }
        
        // Draw statistics
        g2d.setFont(font);
        g2d.setColor(Color.darkGray);
        
        g2d.drawString(formatTime(gameTime), Framework.frameWidth/2 - 45, 21);
        g2d.drawString("HEALTH: "    + player.health, statXCoordinate, 41);
        g2d.drawString("DESTROYED: " + destroyedEnemies, statXCoordinate, 61);
        g2d.drawString("RUNAWAY: "   + runAwayEnemies,   statXCoordinate, 81);
        g2d.drawString("ROCKETS: "   + player.numberOfRockets, statXCoordinate, 111);
        g2d.drawString("AMMO: "      + player.numberOfAmmo, statXCoordinate, 131);
        g2d.drawString("MISSILE: "   + player.numberOfMissiles, statXCoordinate, 151);
        
        g2d.drawString("LEVEL: " + level, Framework.frameWidth/2 + 300, 21);
        g2d.drawString("SCORE: " + getScore(), Framework.frameWidth/2 + 300, 41);
        
//        g2d.drawString("HIGH SCORE: " + leaderBoard.getHighScore(), Framework.frameWidth/2 + 300, 51);
        
        if(bossFight)
        {
        		g2d.drawString("HP: " + boss.health, (int)boss.xCoordinate, (int)boss.yCoordinate + 20);
        }
        /*g2d.drawString("HP: " + player.health, player.xCoordinate, player.yCoordinate - 5);
        for(EnemyHelicopter eh : enemyHelicopterList)
        {
        		g2d.drawString("HP: " + eh.health, eh.xCoordinate, eh.yCoordinate - 5);
        }*/
        
        // Moving images. We draw this cloud in front of the helicopter.
        cloudLayer1Moving.Draw(g2d);
        
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
        g2d.setFont(scoreFont);
        g2d.setColor(Color.BLACK);
    		g2d.drawString("Score: "	+ getScore(), Framework.frameWidth/2 - 150, Framework.frameHeight/3 + 80);
//        g2d.drawString("Best: " + lBoard.getTopScores(), Framework.frameWidth, Framework.frameHeight);
    }
    
    public int getScore()
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
        int pivotX = player.machineGunXcoordinate;
        int pivotY = player.machineGunYcoordinate;
        
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
        
        g2d.drawImage(mouseCursorImg, mousePosition.x, mousePosition.y - mouseCursorImg.getHeight()/2, null); // We substract half of the cursor image so that will be drawn in center of the y mouse coordinate.
        
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
        if(player.health <= 0)
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
        if(player.isShooting(gameTime))
        {
            Bullet.timeOfLastCreatedBullet = gameTime;
            player.numberOfAmmo--;
            
            Bullet b = new Bullet(player.machineGunXcoordinate, player.machineGunYcoordinate, mousePosition);
            bulletsList.add(b);
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
        if(player.isFiredRocket(gameTime))
        {
            Rocket.timeOfLastCreatedRocket = gameTime;
            player.numberOfRockets--;
            
            Rocket r = new Rocket();
            r.Initialize(player.rocketHolderXcoordinate, player.rocketHolderYcoordinate);
            rocketsList.add(r);
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
    		if(player.isFiredMissile(gameTime))
    		{
    			Rocket.timeOfLastCreatedRocket = gameTime;
    			player.numberOfMissiles--;
    			
    			Missile m = new Missile();
    			m.Initialize(player.rocketHolderXcoordinate, player.rocketHolderYcoordinate);
    			missilesList.add(m);
    		}
    }
    
    /**
     * Creates a new enemy if it's time.
     * 
     * @param gameTime Game time.
     */
    private void createEnemyHelicopter(long gameTime)
    {
    		if(bossFight)
    		{
    			return;
    		}
    		if(destroyedEnemies == level * numOfEnemiesForBoss)
    		{
    			bossFight = true;
    			BufferedImage bossImg = random.nextInt() % 2 == 0 ? Boss.helicopter1Img : Boss.helicopter2Img;
    			boss = new Boss(level * Boss.initHealth, Framework.frameWidth, Framework.frameHeight / 2 - bossImg.getHeight() / 2, bossImg);
    			enemyHelicopterList.clear();
    			EnemyHelicopter.spawnEnemies = false;
    		}
    		else if(EnemyHelicopter.spawnEnemies && gameTime - EnemyHelicopter.timeOfLastCreatedEnemy >= EnemyHelicopter.timeBetweenNewEnemies)
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
    				new Animation(explosionAnimImg, 134, 134, 12, 45, false, player.xCoordinate + exNum*60,
    						player.yCoordinate - random.nextInt(100), exNum * 200 +random.nextInt(100));
    			}
    }
    private boolean isPlayerCrashed(Rectangle playerRectangle, Rectangle enemyRectangle)
    {
    		if(playerRectangle.intersects(enemyRectangle)){
    			// Add explosion of enemy helicopter.
    			addPlayerExplosion();
    			for(int exNum = 0; exNum < 3; exNum++){
    				Animation expAnim = new Animation(explosionAnimImg, 134, 134, 12, 45, false, enemyRectangle.x + exNum*60,
                	enemyRectangle.y - random.nextInt(100), exNum * 200 +random.nextInt(100));
    				explosionsList.add(expAnim);
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
    		if(bossFight) {
    			// Update boss position
    			boss.updateVelocity(bulletsList, rocketsList);
    			boss.update();
    			
    			// Create the bullets
    			/*if(gameTime - boss.lastBulletSpawnTime >= Boss.timeBetweenBullets)
    			{
    				boss.lastBulletSpawnTime = gameTime;
    				bossBulletsList.add(boss.spawnBullet((double)player.xCoordinate, (double)player.yCoordinate));
    			}*/
    			
    			if(isPlayerCrashed(new Rectangle(player.xCoordinate, player.yCoordinate, player.helicopterBodyImg.getWidth(), player.helicopterBodyImg.getHeight()), new Rectangle((int)boss.xCoordinate, (int)boss.yCoordinate, boss.helicopterImg.getWidth(), boss.helicopterImg.getHeight())))
    			{
    				player.health -= 200;
    				bossFight = false;
    			}
    			else if(boss.health <= 0)
    			{
    				++level;
    				bossFight = false;
    				
    				// Boss explosion
    				Animation expAnim = new Animation(explosionAnimImg, 134, 134, 12, 45, false, (int)boss.xCoordinate, (int)boss.yCoordinate - explosionAnimImg.getHeight() / 3, 0);
    				explosionsList.add(expAnim);
    				
    				EnemyHelicopter.spawnEnemies = true;
    				
//    				bossBulletsList.clear();
    			}
    		}
    		else
    		{
    			for(int i = 0; i < enemyHelicopterList.size(); i++)
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
    					for(int exNum = 0; exNum < 3; exNum++)
    					{
    						Animation expAnim = new Animation(explosionAnimImg, 134, 134, 12, 45, false, player.xCoordinate + exNum*60, player.yCoordinate - random.nextInt(100), exNum * 200 +random.nextInt(100));
    						explosionsList.add(expAnim);
    					}
    					// Add explosion of enemy helicopter.
    					for(int exNum = 0; exNum < 3; exNum++)
    					{
    						Animation expAnim = new Animation(explosionAnimImg, 134, 134, 12, 45, false, eh.xCoordinate + exNum*60, eh.yCoordinate - random.nextInt(100), exNum * 200 +random.nextInt(100));
    						explosionsList.add(expAnim);
    					}
    		                
    					// Because player crashed with enemy the game will be over so we don't need to check other enemies.
    					break;
    				}
    				
    				// Check health.
    				if(eh.health <= 0)
    				{
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
        for(int i = 0; i < bulletsList.size(); i++)
        {
            Bullet bullet = bulletsList.get(i);
            
            // Move the bullet.
            bullet.Update();
            
            // Is left the screen?
            if(bullet.isItLeftScreen()){
                bulletsList.remove(i);
                // Bullet have left the screen so we removed it from the list and now we can continue to the next bullet.
                continue;
            }
            
            // Did hit any enemy?
            // Rectangle of the bullet image.
            Rectangle bulletRectangle = new Rectangle((int)bullet.xCoordinate, (int)bullet.yCoordinate, Bullet.bulletImg.getWidth(), Bullet.bulletImg.getHeight());
            // Go trough all enemis.
            for(int j = 0; j < enemyHelicopterList.size(); j++)
            {
                EnemyHelicopter eh = enemyHelicopterList.get(j);

                // Current enemy rectangle.
                Rectangle enemyrectangle = new Rectangle(eh.xCoordinate, eh.yCoordinate, EnemyHelicopter.helicopterBodyImg.getWidth(), EnemyHelicopter.helicopterBodyImg.getHeight());

                // Is current bullet over currnet enemy?
                if(bulletRectangle.intersects(enemyrectangle))
                {
                    // Bullet hit the enemy so we reduce his health.
                    eh.health -= Bullet.damagePower;
                    
                    // Bullet was also destroyed so we remove it.
                    bulletsList.remove(i);
                    
                    // That bullet hit enemy so we don't need to check other enemies.
                    break;
                }
            }
            // Check if boss is hit
            if(bossFight && !boss.invincible)
            {
            		Rectangle bossRectangle = new Rectangle((int)boss.xCoordinate, (int)boss.yCoordinate, boss.helicopterImg.getWidth(), boss.helicopterImg.getHeight());
            		if(bulletRectangle.intersects(bossRectangle))
            		{
            				boss.health -= Bullet.damagePower;
            				bulletsList.remove(i);
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
        for(int i = 0; i < rocketsList.size(); i++)
        {
            Rocket rocket = rocketsList.get(i);
            
            // Moves the rocket.
            rocket.Update();
            
            // Checks if it is left the screen.
            if(rocket.isItLeftScreen())
            {
                rocketsList.remove(i);
                // Rocket left the screen so we removed it from the list and now we can continue to the next rocket.
                continue;
            }
            
            // Creates a rocket smoke.
            RocketSmoke rs = new RocketSmoke();
            int xCoordinate = rocket.xCoordinate - RocketSmoke.smokeImg.getWidth(); // Subtract the size of the rocket smoke image (rocketSmokeImg.getWidth()) so that smoke isn't drawn under/behind the image of rocket.
            int yCoordinte = rocket.yCoordinate - 5 + random.nextInt(6); // Subtract 5 so that smoke will be at the middle of the rocket on y coordinate. We rendomly add a number between 0 and 6 so that the smoke line isn't straight line.
            rs.Initialize(xCoordinate, yCoordinte, gameTime, rocket.currentSmokeLifeTime);
            rocketSmokeList.add(rs);
            
            // Because the rocket is fast we get empty space between smokes so we need to add more smoke. 
            // The higher is the speed of rockets, the bigger are empty spaces.
            int smokePositionX = 5 + random.nextInt(8); // We will draw this smoke a little bit ahead of the one we draw before.
            rs = new RocketSmoke();
            xCoordinate = rocket.xCoordinate - RocketSmoke.smokeImg.getWidth() + smokePositionX; // Here we need to add so that the smoke will not be on the same x coordinate as previous smoke. First we need to add 5 because we add random number from 0 to 8 and if the random number is 0 it would be on the same coordinate as smoke before.
            yCoordinte = rocket.yCoordinate - 5 + random.nextInt(6); // Subtract 5 so that smoke will be at the middle of the rocket on y coordinate. We rendomly add a number between 0 and 6 so that the smoke line isn't straight line.
            rs.Initialize(xCoordinate, yCoordinte, gameTime, rocket.currentSmokeLifeTime);
            rocketSmokeList.add(rs);
            
            // Increase the life time for the next piece of rocket smoke.
            rocket.currentSmokeLifeTime *= 1.02;
            
            // Checks if current rocket hit any enemy.
            if( checkIfRocketHitEnemy(rocket) )
                // Rocket was also destroyed so we remove it.
                rocketsList.remove(i);
            
         // Check if boss is hit
            if(bossFight && !boss.invincible)
            {
            		Rectangle bossRectangle = new Rectangle((int)boss.xCoordinate, (int)boss.yCoordinate, boss.helicopterImg.getWidth(), boss.helicopterImg.getHeight());
            		Rectangle rocketRectangle = new Rectangle(rocket.xCoordinate, rocket.yCoordinate, 2, Rocket.rocketImg.getHeight());
            		if(rocketRectangle.intersects(bossRectangle))
            		{
            				boss.health -= rocket.damagePower;
            				rocketsList.remove(i);
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
    		for(int i = 0; i < missilesList.size(); i++)
    		{
    			Missile missile = missilesList.get(i);
    			
    			// Moves the missile.
    			missile.Update();
    			
    			// Finds the enemy target.
    			if (enemyHelicopterList.size() > 0) 
    			{
    				for (int j = 0; j < enemyHelicopterList.size(); j++) 
    				{
    					EnemyHelicopter eh = enemyHelicopterList.get(j);
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
    				missilesList.remove(i);
    				// Missile left the screen so we removed it from the list and now we can continue to the next missile.
    				continue;
    			}
    			
    			// Creates a missile smoke.
    			RocketSmoke rs = new RocketSmoke();
    		    int xCoordinate = missile.xCoordinate - RocketSmoke.smokeImg.getWidth(); // Subtract the size of the rocket smoke image (rocketSmokeImg.getWidth()) so that smoke isn't drawn under/behind the image of rocket.
            int yCoordinte = missile.yCoordinate - 5 + random.nextInt(6); // Subtract 5 so that smoke will be at the middle of the rocket on y coordinate. We randomly add a number between 0 and 6 so that the smoke line isn't straight line.
            rs.Initialize(xCoordinate, yCoordinte, gameTime, missile.currentSmokeLifeTime);
            rocketSmokeList.add(rs);
    			
            // Because the missile is fast we get empty space between smokes so we need to add more smoke. 
            // The higher is the speed of missiles, the bigger are empty spaces.
            int smokePositionX = 5 + random.nextInt(8); // We will draw this smoke a little bit ahead of the one we draw before.
            rs = new RocketSmoke();
            xCoordinate = missile.xCoordinate - RocketSmoke.smokeImg.getWidth() + smokePositionX; // Here we need to add so that the smoke will not be on the same x coordinate as previous smoke. First we need to add 5 because we add random number from 0 to 8 and if the random number is 0 it would be on the same coordinate as smoke before.
            yCoordinte = missile.yCoordinate - 5 + random.nextInt(6); // Subtract 5 so that smoke will be at the middle of the rocket on y coordinate. We randomly add a number between 0 and 6 so that the smoke line isn't straight line.
            rs.Initialize(xCoordinate, yCoordinte, gameTime, missile.currentSmokeLifeTime);
            rocketSmokeList.add(rs);
            
            // Increase the life time for the next piece of rocket smoke.
            missile.currentSmokeLifeTime *= 1.02;
            
            // Checks if current missile hit any enemy.
            if( checkIfMissileHitEnemy(missile) )
            		// Missile was also destroyed so we remove it.
            		missilesList.remove(i);
            
         // Check if boss is hit
            if(bossFight && !boss.invincible)
            {
            		Rectangle bossRectangle = new Rectangle((int)boss.xCoordinate, (int)boss.yCoordinate, boss.helicopterImg.getWidth(), boss.helicopterImg.getHeight());
            		Rectangle missileRectangle = new Rectangle(missile.xCoordinate, missile.yCoordinate, 2, Rocket.rocketImg.getHeight());
            		if(missileRectangle.intersects(bossRectangle))
            		{
            				boss.health -= missile.damagePower;
            				missilesList.remove(i);
            		}
            }
    		}
    }
    
    private void updateBonuses(long gameTime) {
    	// Generate new bonuses
    		if(random.nextInt() % 500 == 0) {
//    			double speed = 50 + random.nextDouble() * 2.7001;
//    			double speed = 50;
    			switch(random.nextInt() % 3) {
    			case 0:
//    				bonusList.add(new HealthBonus(random.nextInt(Framework.frameWidth - HealthBonus.image.getWidth()),
//    					-HealthBonus.image.getHeight(), speed, 50));
    				bonusList.add(new HealthBonus(random.nextInt(Framework.frameWidth - HealthBonus.image.getWidth()),
        					random.nextInt(Framework.frameHeight - HealthBonus.image.getHeight()), 50));
    				break;
    			case 1:
    				bonusList.add(new BulletBonus(random.nextInt(Framework.frameWidth - BulletBonus.image.getWidth()),
    						random.nextInt(Framework.frameHeight -BulletBonus.image.getHeight()), 100));
    				break;
    			case 2:
    				bonusList.add(new RocketBonus(random.nextInt(Framework.frameWidth - RocketBonus.image.getWidth()),
    						random.nextInt(Framework.frameHeight-RocketBonus.image.getHeight()), 5));
    				break;
    			/*case 3:
    				bonusList.add(new ShieldBonus(random.nextInt(Framework.frameWidth - ShieldBonus.image.getWidth()),
    					-ShieldBonus.image.getHeight(), 10, speed));
    				break;*/
    			}
    		}
    	
	    	// Update bonuses
	    	Rectangle playerRect = new Rectangle(player.xCoordinate, player.yCoordinate,
	    			player.helicopterBodyImg.getWidth(), player.helicopterBodyImg.getHeight());
	    	for(int i = 0; i < bonusList.size(); ++i) 
	    		{
	    		Bonus bonus = bonusList.get(i);
	    		
//	    		bonus.update();
	    		
	    		Rectangle bonusRect = new Rectangle((int)bonus.xCoordinate, (int)bonus.yCoordinate,
	    				bonus.image.getWidth(), bonus.image.getHeight());
	    		
	    		if(playerRect.intersects(bonusRect) || bonusRect.intersects(playerRect)) 
	    			{
	    			bonus.apply(player);
//	    			bonus.consumeTime = gameTime;a
	    			bonusList.remove(i--);
	    			} 
	    		else if(bonus.isLeftScreen())
	    			{
	    			bonusList.remove(i--);
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
        for(int j = 0; j < enemyHelicopterList.size(); j++)
        {
            EnemyHelicopter eh = enemyHelicopterList.get(j);

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
    		for(int j = 0; j < enemyHelicopterList.size(); j++ )
    		{
    			EnemyHelicopter eh = enemyHelicopterList.get(j);
    			
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
        for(int i = 0; i < rocketSmokeList.size(); i++)
        {
            RocketSmoke rs = rocketSmokeList.get(i);
            
            // Is it time to remove the smoke.
            if(rs.didSmokeDisapper(gameTime))
                rocketSmokeList.remove(i);
            
            // Set new transparency of rocket smoke image.
            rs.updateTransparency(gameTime);
        }
    }
    
    /**
     * Updates all the animations of an explosion and remove the animation when is over.
     */
    private void updateExplosions()
    {
        for(int i = 0; i < explosionsList.size(); i++)
        {
            // If the animation is over we remove it from the list.
            if(!explosionsList.get(i).active)
                explosionsList.remove(i);
        }
    }
    
    /**
     * It limits the distance of the mouse from the player.
     * 
     * @param mousePosition Position of the mouse.
     */
    private void limitMousePosition(Point mousePosition)
    {
        // Max distance from the player on y coordinate above player helicopter.
        int maxYcoordinateDistanceFromPlayer_top = 30;
        // Max distance from the player on y coordinate under player helicopter.
        int maxYcoordinateDistanceFromPlayer_bottom = 120;
        
        // Mouse cursor will always be the same distance from the player helicopter machine gun on the x coordinate.
        int mouseXcoordinate = player.machineGunXcoordinate + 250;
        
        // Here we will limit the distance of mouse cursor on the y coordinate.
        int mouseYcoordinate = mousePosition.y;
        if(mousePosition.y < player.machineGunYcoordinate){ // Above the helicopter machine gun.
            if(mousePosition.y < player.machineGunYcoordinate - maxYcoordinateDistanceFromPlayer_top)
                mouseYcoordinate = player.machineGunYcoordinate - maxYcoordinateDistanceFromPlayer_top;
        } else { // Under the helicopter.
            if(mousePosition.y > player.machineGunYcoordinate + maxYcoordinateDistanceFromPlayer_bottom)
                mouseYcoordinate = player.machineGunYcoordinate + maxYcoordinateDistanceFromPlayer_bottom;
        }
        
        // We move mouse on y coordinate with helicopter. That makes shooting easier.
        mouseYcoordinate += player.movingYspeed;
        
        // Move the mouse.
        robot.mouseMove(mouseXcoordinate, mouseYcoordinate);
    }
}
