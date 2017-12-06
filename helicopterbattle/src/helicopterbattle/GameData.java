package helicopterbattle;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

public class GameData {
	private Shooting sh;
	private Random random;
	private Robot robot;
	private PlayerHelicopter player;
	private ArrayList<Animation> explosionsList;
	private BufferedImage explosionAnimImg;
	private ArrayList<Bonus> bonusList;
	private BufferedImage skyColorImg;
	private BufferedImage cloudLayer1Img;
	private BufferedImage cloudLayer2Img;
	private BufferedImage mountainsImg;
	private BufferedImage groundImg;
	private MovingBackground cloudLayer1Moving;
	private MovingBackground cloudLayer2Moving;
	private MovingBackground mountainsMoving;
	private MovingBackground groundMoving;
	private BufferedImage mouseCursorImg;
	private Font font;
	private Font scoreFont;
	private int statXCoordinate;
	private boolean bossFight;
	private int numOfEnemiesForBoss;
	private Boss boss;

	public GameData(Shooting sh) {
		this.sh = sh;
	}

	public Shooting getSh() {
		return sh;
	}

	public void setSh(Shooting sh) {
		this.sh = sh;
	}

	public Random getRandom() {
		return random;
	}

	public void setRandom(Random random) {
		this.random = random;
	}

	public Robot getRobot() {
		return robot;
	}

	public void setRobot(Robot robot) {
		this.robot = robot;
	}

	public PlayerHelicopter getPlayer() {
		return player;
	}

	public void setPlayer(PlayerHelicopter player) {
		this.player = player;
	}

	public ArrayList<Animation> getExplosionsList() {
		return explosionsList;
	}

	public void setExplosionsList(ArrayList<Animation> explosionsList) {
		this.explosionsList = explosionsList;
	}

	public BufferedImage getExplosionAnimImg() {
		return explosionAnimImg;
	}

	public void setExplosionAnimImg(BufferedImage explosionAnimImg) {
		this.explosionAnimImg = explosionAnimImg;
	}

	public ArrayList<Bonus> getBonusList() {
		return bonusList;
	}

	public void setBonusList(ArrayList<Bonus> bonusList) {
		this.bonusList = bonusList;
	}

	public BufferedImage getSkyColorImg() {
		return skyColorImg;
	}

	public void setSkyColorImg(BufferedImage skyColorImg) {
		this.skyColorImg = skyColorImg;
	}

	public BufferedImage getCloudLayer1Img() {
		return cloudLayer1Img;
	}

	public void setCloudLayer1Img(BufferedImage cloudLayer1Img) {
		this.cloudLayer1Img = cloudLayer1Img;
	}

	public BufferedImage getCloudLayer2Img() {
		return cloudLayer2Img;
	}

	public void setCloudLayer2Img(BufferedImage cloudLayer2Img) {
		this.cloudLayer2Img = cloudLayer2Img;
	}

	public BufferedImage getMountainsImg() {
		return mountainsImg;
	}

	public void setMountainsImg(BufferedImage mountainsImg) {
		this.mountainsImg = mountainsImg;
	}

	public BufferedImage getGroundImg() {
		return groundImg;
	}

	public void setGroundImg(BufferedImage groundImg) {
		this.groundImg = groundImg;
	}

	public MovingBackground getCloudLayer1Moving() {
		return cloudLayer1Moving;
	}

	public void setCloudLayer1Moving(MovingBackground cloudLayer1Moving) {
		this.cloudLayer1Moving = cloudLayer1Moving;
	}

	public MovingBackground getCloudLayer2Moving() {
		return cloudLayer2Moving;
	}

	public void setCloudLayer2Moving(MovingBackground cloudLayer2Moving) {
		this.cloudLayer2Moving = cloudLayer2Moving;
	}

	public MovingBackground getMountainsMoving() {
		return mountainsMoving;
	}

	public void setMountainsMoving(MovingBackground mountainsMoving) {
		this.mountainsMoving = mountainsMoving;
	}

	public MovingBackground getGroundMoving() {
		return groundMoving;
	}

	public void setGroundMoving(MovingBackground groundMoving) {
		this.groundMoving = groundMoving;
	}

	public BufferedImage getMouseCursorImg() {
		return mouseCursorImg;
	}

	public void setMouseCursorImg(BufferedImage mouseCursorImg) {
		this.mouseCursorImg = mouseCursorImg;
	}

	public Font getFont() {
		return font;
	}

	public void setFont(Font font) {
		this.font = font;
	}

	public Font getScoreFont() {
		return scoreFont;
	}

	public void setScoreFont(Font scoreFont) {
		this.scoreFont = scoreFont;
	}

	public int getStatXCoordinate() {
		return statXCoordinate;
	}

	public void setStatXCoordinate(int statXCoordinate) {
		this.statXCoordinate = statXCoordinate;
	}

	public boolean isBossFight() {
		return bossFight;
	}

	public void setBossFight(boolean bossFight) {
		this.bossFight = bossFight;
	}

	public int getNumOfEnemiesForBoss() {
		return numOfEnemiesForBoss;
	}

	public void setNumOfEnemiesForBoss(int numOfEnemiesForBoss) {
		this.numOfEnemiesForBoss = numOfEnemiesForBoss;
	}

	public Boss getBoss() {
		return boss;
	}

	public void setBoss(Boss boss) {
		this.boss = boss;
	}
	public void assistanceSystem()
    {
    			
    		// Finds the enemy target.
    		if (Game.getEnemyHelicopterList().size() > 0) 
    		{
    			for (int j = 0; j < Game.getEnemyHelicopterList().size(); j++) 
    			{
    				EnemyHelicopter eh = Game.getEnemyHelicopterList().get(j);
    				player.preventCrash(eh);
    				break;
    			}
    		}
    	}
	public void drawGame(Graphics2D g2d) {
		g2d.drawImage(skyColorImg, 0, 0, Framework.frameWidth, Framework.frameHeight, null);
        
        // Moving images.
        mountainsMoving.Draw(g2d);
        groundMoving.Draw(g2d);
        cloudLayer2Moving.Draw(g2d);
        
        if(Game.isPlayerAlive())
        {
            player.Draw(g2d);
        		statXCoordinate = 10 + player.helicopterProfileImg.getWidth() + 10;
        }
		
        // Draws helicopter's profile
        player.DrawAvatar(g2d);
        		        		
        // Draws all the enemies.
        for(int i = 0; i < Game.getEnemyHelicopterList().size(); i++)
        {
        		Game.getEnemyHelicopterList().get(i).Draw(g2d);
        }
        
        if(bossFight)
        {
        		boss.Draw(g2d);
        		
        		for(int i=0; i< Game.getBossRocketsList().size(); i++)
        		{
        			Game.getBossRocketsList().get(i).Draw(g2d);
        		}
        }
        
        // Draws all the bullets. 
        for(int i = 0; i < Game.getBulletsList().size(); i++)
        {
        		Game.getBulletsList().get(i).Draw(g2d);
        }
        
        // Draws all the missiles.
        for(int i = 0; i < Game.getMissilesList().size(); i++)
        {
        		Game.getMissilesList().get(i).Draw(g2d);
        }
        
        // Draws all the rockets. 
        for(int i = 0; i < Game.getRocketsList().size(); i++)
        {
            Game.getRocketsList().get(i).Draw(g2d);
        }
        // Draws smoke of all the rockets.
        for(int i = 0; i < Game.getRocketSmokeList().size(); i++)
        {
        		Game.getRocketSmokeList().get(i).Draw(g2d);
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
        
        g2d.drawString("HEALTH: "    + player.health, statXCoordinate, 41);
        g2d.drawString("DESTROYED: " + Game.destroyedEnemies, statXCoordinate, 61);
        g2d.drawString("RUNAWAY: "   + Game.runAwayEnemies,   statXCoordinate, 81);
        g2d.drawString("ROCKETS: "   + player.numberOfRockets, statXCoordinate, 111);
        g2d.drawString("AMMO: "      + player.numberOfAmmo, statXCoordinate, 131);
        g2d.drawString("MISSILE: "   + player.numberOfMissiles, statXCoordinate, 151);
        
        g2d.drawString("STAGE: " + Game.level, Framework.frameWidth/2 + 300, 21);
        g2d.drawString("SCORE: " + Game.getScore(), Framework.frameWidth/2 + 300, 41);
        
        
        if(bossFight)
        {
        		g2d.drawString("HP: " + boss.health, (int)boss.xCoordinate, (int)boss.yCoordinate + 20);
        }
        
        cloudLayer1Moving.Draw(g2d);
	}
}