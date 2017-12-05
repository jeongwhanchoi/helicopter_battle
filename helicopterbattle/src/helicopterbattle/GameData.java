package helicopterbattle;

import java.awt.Font;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

public class GameData {
	private Random random;
	private Robot robot;
	private PlayerHelicopter player;
	private ArrayList<EnemyHelicopter> enemyHelicopterList;
	private ArrayList<Animation> explosionsList;
	private BufferedImage explosionAnimImg;
	private ArrayList<Bullet> bulletsList;
	private ArrayList<Missile> missilesList;
	private ArrayList<Rocket> rocketsList;
	private ArrayList<RocketSmoke> rocketSmokeList;
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
	private int level;
	private int runAwayEnemies;
	private int destroyedEnemies;
	private int statXCoordinate;
	private boolean bossFight;
	private int numOfEnemiesForBoss;
	private Boss boss;
	private int score;
	private String highScore;

	public GameData(ArrayList<EnemyHelicopter> enemyHelicopterList, String highScore) {
		this.enemyHelicopterList = enemyHelicopterList;
		this.highScore = highScore;
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

	public ArrayList<EnemyHelicopter> getEnemyHelicopterList() {
		return enemyHelicopterList;
	}

	public void setEnemyHelicopterList(ArrayList<EnemyHelicopter> enemyHelicopterList) {
		this.enemyHelicopterList = enemyHelicopterList;
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

	public ArrayList<Bullet> getBulletsList() {
		return bulletsList;
	}

	public void setBulletsList(ArrayList<Bullet> bulletsList) {
		this.bulletsList = bulletsList;
	}

	public ArrayList<Missile> getMissilesList() {
		return missilesList;
	}

	public void setMissilesList(ArrayList<Missile> missilesList) {
		this.missilesList = missilesList;
	}

	public ArrayList<Rocket> getRocketsList() {
		return rocketsList;
	}

	public void setRocketsList(ArrayList<Rocket> rocketsList) {
		this.rocketsList = rocketsList;
	}

	public ArrayList<RocketSmoke> getRocketSmokeList() {
		return rocketSmokeList;
	}

	public void setRocketSmokeList(ArrayList<RocketSmoke> rocketSmokeList) {
		this.rocketSmokeList = rocketSmokeList;
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

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getRunAwayEnemies() {
		return runAwayEnemies;
	}

	public void setRunAwayEnemies(int runAwayEnemies) {
		this.runAwayEnemies = runAwayEnemies;
	}

	public int getDestroyedEnemies() {
		return destroyedEnemies;
	}

	public void setDestroyedEnemies(int destroyedEnemies) {
		this.destroyedEnemies = destroyedEnemies;
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

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public String getHighScore() {
		return highScore;
	}

	public void setHighScore(String highScore) {
		this.highScore = highScore;
	}
}