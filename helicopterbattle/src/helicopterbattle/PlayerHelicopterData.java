package helicopterbattle;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;

public class PlayerHelicopterData {
	private int healthInit;
	private int health;
	private double acceleratingXspeed;
	private double acceleratingYspeed;
	private double stoppingXspeed;
	private double stoppingYspeed;
	private int numberOfRocketsInit;
	private int numberOfRockets;
	private int numberOfAmmoInit;
	private int numberOfAmmo;
	private int numberOfMissilesInit;
	private int numberOfMissiles;
	private BufferedImage helicopterBodyImg;
	private BufferedImage helicopterProfileImg;
	private BufferedImage helicopterProfileImg75;
	private BufferedImage helicopterProfileImg50;
	private BufferedImage helicopterProfileImg00;
	private BufferedImage helicopterFrontPropellerAnimImg;
	private BufferedImage helicopterRearPropellerAnimImg;
	private Animation helicopterFrontPropellerAnim;
	private Animation helicopterRearPropellerAnim;
	private int offsetXFrontPropeller;
	private int offsetYFrontPropeller;
	private int offsetXRearPropeller;
	private int offsetYRearPropeller;
	private int offsetXRocketHolder;
	private int offsetYRocketHolder;
	private int rocketHolderXcoordinate;
	private int rocketHolderYcoordinate;
	private int offsetXMachineGun;
	private int offsetYMachineGun;
	private int machineGunXcoordinate;
	private int machineGunYcoordinate;
	private String helicopterName;
	private String helicopterTypeStr;
	private String helicopterPlayerStr;
	private String helicopterPlayerHealth75Str;
	private String helicopterPlayerHealth50Str;
	private String helicopterPlayerHealth00Str;

	public PlayerHelicopterData(int numberOfRocketsInit, int numberOfAmmoInit, int numberOfMissilesInit) {
		this.numberOfRocketsInit = numberOfRocketsInit;
		this.numberOfAmmoInit = numberOfAmmoInit;
		this.numberOfMissilesInit = numberOfMissilesInit;
	}

	public int getHealthInit() {
		return healthInit;
	}

	public void setHealthInit(int healthInit) {
		this.healthInit = healthInit;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public double getAcceleratingXspeed() {
		return acceleratingXspeed;
	}

	public void setAcceleratingXspeed(double acceleratingXspeed) {
		this.acceleratingXspeed = acceleratingXspeed;
	}

	public double getAcceleratingYspeed() {
		return acceleratingYspeed;
	}

	public void setAcceleratingYspeed(double acceleratingYspeed) {
		this.acceleratingYspeed = acceleratingYspeed;
	}

	public double getStoppingXspeed() {
		return stoppingXspeed;
	}

	public void setStoppingXspeed(double stoppingXspeed) {
		this.stoppingXspeed = stoppingXspeed;
	}

	public double getStoppingYspeed() {
		return stoppingYspeed;
	}

	public void setStoppingYspeed(double stoppingYspeed) {
		this.stoppingYspeed = stoppingYspeed;
	}

	public int getNumberOfRocketsInit() {
		return numberOfRocketsInit;
	}

	public void setNumberOfRocketsInit(int numberOfRocketsInit) {
		this.numberOfRocketsInit = numberOfRocketsInit;
	}

	public int getNumberOfRockets() {
		return numberOfRockets;
	}

	public void setNumberOfRockets(int numberOfRockets) {
		this.numberOfRockets = numberOfRockets;
	}

	public int getNumberOfAmmoInit() {
		return numberOfAmmoInit;
	}

	public void setNumberOfAmmoInit(int numberOfAmmoInit) {
		this.numberOfAmmoInit = numberOfAmmoInit;
	}

	public int getNumberOfAmmo() {
		return numberOfAmmo;
	}

	public void setNumberOfAmmo(int numberOfAmmo) {
		this.numberOfAmmo = numberOfAmmo;
	}

	public int getNumberOfMissilesInit() {
		return numberOfMissilesInit;
	}

	public void setNumberOfMissilesInit(int numberOfMissilesInit) {
		this.numberOfMissilesInit = numberOfMissilesInit;
	}

	public int getNumberOfMissiles() {
		return numberOfMissiles;
	}

	public void setNumberOfMissiles(int numberOfMissiles) {
		this.numberOfMissiles = numberOfMissiles;
	}

	public BufferedImage getHelicopterBodyImg() {
		return helicopterBodyImg;
	}

	public void setHelicopterBodyImg(BufferedImage helicopterBodyImg) {
		this.helicopterBodyImg = helicopterBodyImg;
	}

	public BufferedImage getHelicopterProfileImg() {
		return helicopterProfileImg;
	}

	public void setHelicopterProfileImg(BufferedImage helicopterProfileImg) {
		this.helicopterProfileImg = helicopterProfileImg;
	}

	public BufferedImage getHelicopterProfileImg75() {
		return helicopterProfileImg75;
	}

	public void setHelicopterProfileImg75(BufferedImage helicopterProfileImg75) {
		this.helicopterProfileImg75 = helicopterProfileImg75;
	}

	public BufferedImage getHelicopterProfileImg50() {
		return helicopterProfileImg50;
	}

	public void setHelicopterProfileImg50(BufferedImage helicopterProfileImg50) {
		this.helicopterProfileImg50 = helicopterProfileImg50;
	}

	public BufferedImage getHelicopterProfileImg00() {
		return helicopterProfileImg00;
	}

	public void setHelicopterProfileImg00(BufferedImage helicopterProfileImg00) {
		this.helicopterProfileImg00 = helicopterProfileImg00;
	}

	public BufferedImage getHelicopterFrontPropellerAnimImg() {
		return helicopterFrontPropellerAnimImg;
	}

	public void setHelicopterFrontPropellerAnimImg(BufferedImage helicopterFrontPropellerAnimImg) {
		this.helicopterFrontPropellerAnimImg = helicopterFrontPropellerAnimImg;
	}

	public BufferedImage getHelicopterRearPropellerAnimImg() {
		return helicopterRearPropellerAnimImg;
	}

	public void setHelicopterRearPropellerAnimImg(BufferedImage helicopterRearPropellerAnimImg) {
		this.helicopterRearPropellerAnimImg = helicopterRearPropellerAnimImg;
	}

	public Animation getHelicopterFrontPropellerAnim() {
		return helicopterFrontPropellerAnim;
	}

	public void setHelicopterFrontPropellerAnim(Animation helicopterFrontPropellerAnim) {
		this.helicopterFrontPropellerAnim = helicopterFrontPropellerAnim;
	}

	public Animation getHelicopterRearPropellerAnim() {
		return helicopterRearPropellerAnim;
	}

	public void setHelicopterRearPropellerAnim(Animation helicopterRearPropellerAnim) {
		this.helicopterRearPropellerAnim = helicopterRearPropellerAnim;
	}

	public int getOffsetXFrontPropeller() {
		return offsetXFrontPropeller;
	}

	public void setOffsetXFrontPropeller(int offsetXFrontPropeller) {
		this.offsetXFrontPropeller = offsetXFrontPropeller;
	}

	public int getOffsetYFrontPropeller() {
		return offsetYFrontPropeller;
	}

	public void setOffsetYFrontPropeller(int offsetYFrontPropeller) {
		this.offsetYFrontPropeller = offsetYFrontPropeller;
	}

	public int getOffsetXRearPropeller() {
		return offsetXRearPropeller;
	}

	public void setOffsetXRearPropeller(int offsetXRearPropeller) {
		this.offsetXRearPropeller = offsetXRearPropeller;
	}

	public int getOffsetYRearPropeller() {
		return offsetYRearPropeller;
	}

	public void setOffsetYRearPropeller(int offsetYRearPropeller) {
		this.offsetYRearPropeller = offsetYRearPropeller;
	}

	public int getOffsetXRocketHolder() {
		return offsetXRocketHolder;
	}

	public void setOffsetXRocketHolder(int offsetXRocketHolder) {
		this.offsetXRocketHolder = offsetXRocketHolder;
	}

	public int getOffsetYRocketHolder() {
		return offsetYRocketHolder;
	}

	public void setOffsetYRocketHolder(int offsetYRocketHolder) {
		this.offsetYRocketHolder = offsetYRocketHolder;
	}

	public int getRocketHolderXcoordinate() {
		return rocketHolderXcoordinate;
	}

	public void setRocketHolderXcoordinate(int rocketHolderXcoordinate) {
		this.rocketHolderXcoordinate = rocketHolderXcoordinate;
	}

	public int getRocketHolderYcoordinate() {
		return rocketHolderYcoordinate;
	}

	public void setRocketHolderYcoordinate(int rocketHolderYcoordinate) {
		this.rocketHolderYcoordinate = rocketHolderYcoordinate;
	}

	public int getOffsetXMachineGun() {
		return offsetXMachineGun;
	}

	public void setOffsetXMachineGun(int offsetXMachineGun) {
		this.offsetXMachineGun = offsetXMachineGun;
	}

	public int getOffsetYMachineGun() {
		return offsetYMachineGun;
	}

	public void setOffsetYMachineGun(int offsetYMachineGun) {
		this.offsetYMachineGun = offsetYMachineGun;
	}

	public int getMachineGunXcoordinate() {
		return machineGunXcoordinate;
	}

	public void setMachineGunXcoordinate(int machineGunXcoordinate) {
		this.machineGunXcoordinate = machineGunXcoordinate;
	}

	public int getMachineGunYcoordinate() {
		return machineGunYcoordinate;
	}

	public void setMachineGunYcoordinate(int machineGunYcoordinate) {
		this.machineGunYcoordinate = machineGunYcoordinate;
	}

	public String getHelicopterName() {
		return helicopterName;
	}

	public void setHelicopterName(String helicopterName) {
		this.helicopterName = helicopterName;
	}

	public String getHelicopterTypeStr() {
		return helicopterTypeStr;
	}

	public void setHelicopterTypeStr(String helicopterTypeStr) {
		this.helicopterTypeStr = helicopterTypeStr;
	}

	public String getHelicopterPlayerStr() {
		return helicopterPlayerStr;
	}

	public void setHelicopterPlayerStr(String helicopterPlayerStr) {
		this.helicopterPlayerStr = helicopterPlayerStr;
	}

	public String getHelicopterPlayerHealth75Str() {
		return helicopterPlayerHealth75Str;
	}

	public void setHelicopterPlayerHealth75Str(String helicopterPlayerHealth75Str) {
		this.helicopterPlayerHealth75Str = helicopterPlayerHealth75Str;
	}

	public String getHelicopterPlayerHealth50Str() {
		return helicopterPlayerHealth50Str;
	}

	public void setHelicopterPlayerHealth50Str(String helicopterPlayerHealth50Str) {
		this.helicopterPlayerHealth50Str = helicopterPlayerHealth50Str;
	}

	public String getHelicopterPlayerHealth00Str() {
		return helicopterPlayerHealth00Str;
	}

	public void setHelicopterPlayerHealth00Str(String helicopterPlayerHealth00Str) {
		this.helicopterPlayerHealth00Str = helicopterPlayerHealth00Str;
	}
	 public void loadHeli(int helicopterSelect) {
	    	try 
	        {
	        		if(helicopterSelect == 1)
	        		{
	        			helicopterName = "Hello Kitty";
	        			helicopterTypeStr = "/helicopterbattle/resources/images/1_helicopter_body_helloKitty.png";
	        			helicopterPlayerStr = "/helicopterbattle/resources/images/profile_helloKitty.png";
	        			this.healthInit = 100;
	        			this.health = healthInit;
	        			this.numberOfAmmo = 300;
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
	        		else if(helicopterSelect == 2)
	        		{
	        			helicopterName = "Chinook";
	        			helicopterTypeStr = "/helicopterbattle/resources/images/1_helicopter_body_chinook.png";
	        			helicopterPlayerStr = "/helicopterbattle/resources/images/fighter_pilot.png";
	        			this.healthInit = 150;
	        			this.health = healthInit;
	        			this.numberOfAmmo = 500;
	        			this.numberOfRockets = 5;
	        			this.numberOfMissiles = 10;
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
	        		else if(helicopterSelect == 3)
	        		{
	        			helicopterName = "Viper";
	        			helicopterTypeStr = "/helicopterbattle/resources/images/1_helicopter_body_viper.png";
	        			helicopterPlayerStr = "/helicopterbattle/resources/images/fighter_pilot.png";
	        			this.healthInit = 150;
	        			this.health = healthInit;
	        			this.numberOfAmmo = 100;
	        			this.numberOfRockets = 20;
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
	        		else if(helicopterSelect == 4)
	        		{
	        			helicopterName = "Tiger";
	        			helicopterTypeStr = "/helicopterbattle/resources/images/1_helicopter_body_tiger.png";
	        			helicopterPlayerStr = "/helicopterbattle/resources/images/fighter_pilot.png";
	        			this.healthInit = 150;
	        			this.health = healthInit;
	        			this.numberOfAmmo = 400;
	        			this.numberOfRockets = 30;
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
	        		else if(helicopterSelect == 5)
	        		{
	        			helicopterName = "Little Bird";
	        			helicopterTypeStr = "/helicopterbattle/resources/images/1_helicopter_body_littleBird.png";
	        			helicopterPlayerStr = "/helicopterbattle/resources/images/fighter_pilot.png";
	        			this.healthInit = 100;
	        			this.health = healthInit;
	        			this.numberOfAmmo = 200;
	        			this.numberOfRockets = 20;
	        			this.numberOfMissiles = 10;
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
	        		else if(helicopterSelect == 6)
	        		{
	        			helicopterName = "Black Shark";
	        			helicopterTypeStr = "/helicopterbattle/resources/images/1_helicopter_body_blackShark.png";
	        			helicopterPlayerStr = "/helicopterbattle/resources/images/fighter_pilot.png";
	        			this.healthInit = 150;
	        			this.health = healthInit;
	        			this.numberOfAmmo = 200;
	        			this.numberOfRockets = 10;
	        			this.numberOfMissiles = 20;
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
	        		else if(helicopterSelect == 7)
	        		{
	        			helicopterName = "SNOC";
	        			helicopterTypeStr = "/helicopterbattle/resources/images/1_helicopter_body_snoc.png";
	        			helicopterPlayerStr = "/helicopterbattle/resources/images/fighter_pilot.png";
	        			this.healthInit = 150;
	        			this.health = healthInit;
	        			this.numberOfAmmo = 100;
	        			this.numberOfRockets = 25;
	        			this.numberOfMissiles = 40;
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
	        			helicopterPlayerStr = "/helicopterbattle/resources/images/fighter_pilot.png";
	        			this.healthInit = 150;
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
	    }
}