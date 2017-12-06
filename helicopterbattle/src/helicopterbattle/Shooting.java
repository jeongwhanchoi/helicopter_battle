package helicopterbattle;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.Random;

public class Shooting {
	
    /**
     * Checks if the player is shooting with the machine gun and creates bullets if he shooting.
     * 
     * @param gameTime Game time.
     */
    public void isPlayerShooting(long gameTime, Point mousePosition, PlayerHelicopter player)
    {
        if(player.isShooting(gameTime))
        {
            Bullet.timeOfLastCreatedBullet = gameTime;
            player.data.setNumberOfAmmo(player.data.getNumberOfAmmo() - 1);
            
            Bullet b = new Bullet(player.data.getMachineGunXcoordinate(), player.data.getMachineGunYcoordinate(), mousePosition);
            Game.getBulletsList().add(b);
            
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
    public void didPlayerFiredRocket(long gameTime, PlayerHelicopter player)
    {
        if(player.isFiredRocket(gameTime))
        {
            Rocket.timeOfLastCreatedRocket = gameTime;
            player.data.setNumberOfRockets(player.data.getNumberOfRockets() - 1);
            
            Rocket r = new Rocket();
            r.Initialize(player.data.getRocketHolderXcoordinate(), player.data.getRocketHolderYcoordinate());
            Game.getRocketsList().add(r);
            
            Sound rocket = new Sound("rocket.mp3", false);
            rocket.start();
        }
    }
        
    public void didBossFiredRocket(long gameTime, Boss boss)
    {
        if(boss.isFiredRocket(gameTime))
        {
            Rocket.timeOfLastCreatedBossRocket = gameTime;
            
            boss.numberOfRockets--;
            
            Rocket r = new Rocket();
            r.InitializeBossRocket(boss.xCoordinate, boss.yCoordinate);
            Game.getBossRocketsList().add(r);

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
    public void didPlayerFiredMissile(long gameTime, PlayerHelicopter player)
    {
    		if(player.isFiredMissile(gameTime))
    		{
    			Missile.timeOfLastCreatedRocket = gameTime;
    			player.data.setNumberOfMissiles(player.data.getNumberOfMissiles() - 1);
    			
    			Missile m = new Missile();
    			m.Initialize(player.data.getRocketHolderXcoordinate(), player.data.getRocketHolderYcoordinate());
    			Game.getMissilesList().add(m);
    			
    			Sound missile = new Sound("missile.mp3", false);
    			missile.start();
    		}
    }

    /**
     * Checks if the given rocket is hit any of enemy helicopters.
     * 
     * @param rocket Rocket to check.
     * @return True if it hit any of enemy helicopters, false otherwise.
     */
    public boolean checkIfRocketHitEnemy(Rocket rocket)
    {
        boolean didItHitEnemy = false;
        
        // Current rocket rectangle. // I inserted number 2 insted of rocketImg.getWidth() because I wanted that rocket 
        // is over helicopter when collision is detected, because actual image of helicopter isn't a rectangle shape. (We could calculate/make 3 areas where helicopter can be hit and checks these areas, but this is easier.)
        Rectangle rocketRectangle = new Rectangle(rocket.xCoordinate, rocket.yCoordinate, 2, Rocket.rocketImg.getHeight());
        
        // Go trough all enemis.
        for(int j = 0; j < Game.getEnemyHelicopterList().size(); j++)
        {
            EnemyHelicopter eh = Game.getEnemyHelicopterList().get(j);

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
    
    public boolean checkIfBossRocketHitPlayer(Rocket rocket, PlayerHelicopter player)
    {
        boolean didItHitPlayer = false;
        
        // Current rocket rectangle. // I inserted number 2 insted of rocketImg.getWidth() because I wanted that rocket 
        // is over helicopter when collision is detected, because actual image of helicopter isn't a rectangle shape. (We could calculate/make 3 areas where helicopter can be hit and checks these areas, but this is easier.)
        Rectangle rocketRectangle = new Rectangle(rocket.xCoordinate, rocket.yCoordinate, 2, Rocket.rocketImg.getHeight());
        
        // Go trough all enemis.
        for(int j = 0; j < Game.getBossRocketsList().size(); j++)
        {

            // Current enemy rectangle.
            Rectangle playerRectangle = new Rectangle(player.xCoordinate, player.yCoordinate, player.data.getHelicopterBodyImg().getWidth(), player.data.getHelicopterBodyImg().getHeight());

            // Is current rocket over currnet enemy?
            if(rocketRectangle.intersects(playerRectangle))
            {
                didItHitPlayer = true;
				
                // Rocket hit the enemy so we reduce his health.
                player.data.setHealth(player.data.getHealth() - Rocket.damagePower);
                
                // Rocket hit enemy so we don't need to check other enemies.
                break;
            }
        }
        
        return didItHitPlayer;
    }
    
    
    /**
     * Checks if the given missile is hit any of enemy helicopters.
     * 
     * @param missile
     * @return True if it hit any of enemy helicopeters, false otherwise.
     */
    public boolean checkIfMissileHitEnemy(Missile missile)
    {
    		boolean didItHitEnemy = false;
    		
    		// Current missile rectangle. // I inserted number 2 instead of rocketImg.getWidth() because I wanted that missile
    		// is over helicopter when collision is detected, because actual image of helicopter isn't a rectangle shape.
    		Rectangle missileRectangle = new Rectangle(missile.xCoordinate, missile.yCoordinate, 2, Rocket.rocketImg.getHeight());
    		
    		// Go through all enemies.
    		for(int j = 0; j < Game.getEnemyHelicopterList().size(); j++ )
    		{
    			EnemyHelicopter eh = Game.getEnemyHelicopterList().get(j);
    			
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
     * Update bullets. 
     * It moves bullets.
     * Checks if the bullet is left the screen.
     * Checks if any bullets is hit any enemy.
     */
    public void updateBullets(Boss boss, boolean bossFight)
    {
        for(int i = 0; i < Game.getBulletsList().size(); i++)
        {
            Bullet bullet = Game.getBulletsList().get(i);
            
            // Move the bullet.
            bullet.Update();
            
            // Is left the screen?
            if(bullet.isItLeftScreen()){
                Game.getBulletsList().remove(i);
                // Bullet have left the screen so we removed it from the list and now we can continue to the next bullet.
                continue;
            }
            
            // Did hit any enemy?
            // Rectangle of the bullet image.
            Rectangle bulletRectangle = new Rectangle((int)bullet.xCoordinate, (int)bullet.yCoordinate, Bullet.bulletImg.getWidth(), Bullet.bulletImg.getHeight());
            // Go trough all enemis.
            for(int j = 0; j < Game.getEnemyHelicopterList().size(); j++)
            {
                EnemyHelicopter eh = Game.getEnemyHelicopterList().get(j);

                // Current enemy rectangle.
                Rectangle enemyrectangle = new Rectangle(eh.xCoordinate, eh.yCoordinate, EnemyHelicopter.helicopterBodyImg.getWidth(), EnemyHelicopter.helicopterBodyImg.getHeight());

                // Is current bullet over currnet enemy?
                if(bulletRectangle.intersects(enemyrectangle))
                {
                    // Bullet hit the enemy so we reduce his health.
                    eh.health -= Bullet.damagePower;
                    
                    // Bullet was also destroyed so we remove it.
                    Game.getBulletsList().remove(i);
                    
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
            				Game.getBulletsList().remove(i);
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
    public void updateRockets(long gameTime, Boss boss, boolean bossFight, Random random)
    {
        for(int i = 0; i < Game.getRocketsList().size(); i++)
        {
            Rocket rocket = Game.getRocketsList().get(i);
            
            // Moves the rocket.
            rocket.Update();
            
            // Checks if it is left the screen.
            if(rocket.isItLeftScreen())
            {
            	Game.getRocketsList().remove(i);
                // Rocket left the screen so we removed it from the list and now we can continue to the next rocket.
                continue;
            }
            
            // Creates a rocket smoke.
            RocketSmoke rs = new RocketSmoke();
            int xCoordinate = rocket.xCoordinate - RocketSmoke.smokeImg.getWidth(); // Subtract the size of the rocket smoke image (rocketSmokeImg.getWidth()) so that smoke isn't drawn under/behind the image of rocket.
            int yCoordinte = rocket.yCoordinate - 5 + random.nextInt(6); // Subtract 5 so that smoke will be at the middle of the rocket on y coordinate. We rendomly add a number between 0 and 6 so that the smoke line isn't straight line.
            rs.Initialize(xCoordinate, yCoordinte, gameTime, rocket.currentSmokeLifeTime);
            Game.getRocketSmokeList().add(rs);
            
            // Because the rocket is fast we get empty space between smokes so we need to add more smoke. 
            // The higher is the speed of rockets, the bigger are empty spaces.
            int smokePositionX = 5 + random.nextInt(8); // We will draw this smoke a little bit ahead of the one we draw before.
            rs = new RocketSmoke();
            xCoordinate = rocket.xCoordinate - RocketSmoke.smokeImg.getWidth() + smokePositionX; // Here we need to add so that the smoke will not be on the same x coordinate as previous smoke. First we need to add 5 because we add random number from 0 to 8 and if the random number is 0 it would be on the same coordinate as smoke before.
            yCoordinte = rocket.yCoordinate - 5 + random.nextInt(6); // Subtract 5 so that smoke will be at the middle of the rocket on y coordinate. We rendomly add a number between 0 and 6 so that the smoke line isn't straight line.
            rs.Initialize(xCoordinate, yCoordinte, gameTime, rocket.currentSmokeLifeTime);
            Game.getRocketSmokeList().add(rs);
            
            // Increase the life time for the next piece of rocket smoke.
            rocket.currentSmokeLifeTime *= 1.02;
            
            // Checks if current rocket hit any enemy.
            if( checkIfRocketHitEnemy(rocket) )
                // Rocket was also destroyed so we remove it.
                Game.getRocketsList().remove(i);
            
         // Check if boss is hit
            if(bossFight && !boss.invincible)
            {
            		Rectangle bossRectangle = new Rectangle((int)boss.xCoordinate, (int)boss.yCoordinate, boss.helicopterImg.getWidth(), boss.helicopterImg.getHeight());
            		Rectangle rocketRectangle = new Rectangle(rocket.xCoordinate, rocket.yCoordinate, 2, Rocket.rocketImg.getHeight());
            		if(rocketRectangle.intersects(bossRectangle))
            		{
            				boss.health -= rocket.damagePower;
            				Game.getRocketsList().remove(i);
            		}
            }
        }
    }
    
    
    public void updateBossRockets(long gameTime, PlayerHelicopter player)
    {
        for(int i = 0; i < Game.getBossRocketsList().size(); i++)
        {
            Rocket rocket = Game.getBossRocketsList().get(i);
            
            // Moves the rocket.
            rocket.Update();
            
            // Checks if it is left the screen.
            if(rocket.isItLeftScreen())
            {
            	Game.getBossRocketsList().remove(i);
                // Rocket left the screen so we removed it from the list and now we can continue to the next rocket.
                continue;
            }
            
            // Checks if current rocket hit any enemy.
            if( checkIfBossRocketHitPlayer(rocket, player) )
                // Rocket was also destroyed so we remove it.
            	Game.getBossRocketsList().remove(i);
            

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
   public void updateMissile(long gameTime, Boss boss, boolean bossFight, Random random)
    {
    		for(int i = 0; i < Game.getMissilesList().size(); i++)
    		{
    			Missile missile = Game.getMissilesList().get(i);
    			
    			// Moves the missile.
    			missile.Update();
    			
    			// Finds the enemy target.
    			if (Game.getEnemyHelicopterList().size() > 0) 
    			{
    				for (int j = 0; j < Game.getEnemyHelicopterList().size(); j++) 
    				{
    					EnemyHelicopter eh = Game.getEnemyHelicopterList().get(j);
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
    				Game.getMissilesList().remove(i);
    				// Missile left the screen so we removed it from the list and now we can continue to the next missile.
    				continue;
    			}
    			
    			// Creates a missile smoke.
    			RocketSmoke rs = new RocketSmoke();
    		    int xCoordinate = missile.xCoordinate - RocketSmoke.smokeImg.getWidth(); // Subtract the size of the rocket smoke image (rocketSmokeImg.getWidth()) so that smoke isn't drawn under/behind the image of rocket.
            int yCoordinte = missile.yCoordinate - 5 + random.nextInt(6); // Subtract 5 so that smoke will be at the middle of the rocket on y coordinate. We randomly add a number between 0 and 6 so that the smoke line isn't straight line.
            rs.Initialize(xCoordinate, yCoordinte, gameTime, missile.currentSmokeLifeTime);
            Game.getRocketSmokeList().add(rs);
    			
            // Because the missile is fast we get empty space between smokes so we need to add more smoke. 
            // The higher is the speed of missiles, the bigger are empty spaces.
            int smokePositionX = 5 + random.nextInt(8); // We will draw this smoke a little bit ahead of the one we draw before.
            rs = new RocketSmoke();
            xCoordinate = missile.xCoordinate - RocketSmoke.smokeImg.getWidth() + smokePositionX; // Here we need to add so that the smoke will not be on the same x coordinate as previous smoke. First we need to add 5 because we add random number from 0 to 8 and if the random number is 0 it would be on the same coordinate as smoke before.
            yCoordinte = missile.yCoordinate - 5 + random.nextInt(6); // Subtract 5 so that smoke will be at the middle of the rocket on y coordinate. We randomly add a number between 0 and 6 so that the smoke line isn't straight line.
            rs.Initialize(xCoordinate, yCoordinte, gameTime, missile.currentSmokeLifeTime);
            Game.getRocketSmokeList().add(rs);
            
            // Increase the life time for the next piece of rocket smoke.
            missile.currentSmokeLifeTime *= 1.02;
            
            // Checks if current missile hit any enemy.
            if( checkIfMissileHitEnemy(missile) )
            		// Missile was also destroyed so we remove it.
            		Game.getMissilesList().remove(i);
            
         // Check if boss is hit
            if(bossFight && !boss.invincible)
            {
            		Rectangle bossRectangle = new Rectangle((int)boss.xCoordinate, (int)boss.yCoordinate, boss.helicopterImg.getWidth(), boss.helicopterImg.getHeight());
            		Rectangle missileRectangle = new Rectangle(missile.xCoordinate, missile.yCoordinate, 2, Rocket.rocketImg.getHeight());
            		if(missileRectangle.intersects(bossRectangle))
            		{
            				boss.health -= missile.damagePower;
            				Game.getMissilesList().remove(i);
            		}
            }
    		}
    }
   /**
    * Updates smoke of all the rockets.
    * If the life time of the smoke is over then we delete it from list.
    * It also changes a transparency of a smoke image, so that smoke slowly disappear.
    * 
    * @param gameTime Game time.
    */
   public void updateRocketSmoke(long gameTime)
   {
       for(int i = 0; i < Game.getRocketSmokeList().size(); i++)
       {
           RocketSmoke rs = Game.getRocketSmokeList().get(i);
           
           // Is it time to remove the smoke.
           if(rs.didSmokeDisapper(gameTime))
        	   Game.getRocketSmokeList().remove(i);
           
           // Set new transparency of rocket smoke image.
           rs.updateTransparency(gameTime);
       }
   }
   
}
