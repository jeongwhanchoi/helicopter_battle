package helicopterbattle;

import java.awt.image.BufferedImage;

public class HealthBonus extends Bonus {
	private int health;
	public static BufferedImage image;
	
	public HealthBonus(double xCoordinate, double yCoordinate, int health)
	{
//		super(xCoordinate, yCoordinate, 0, fallingSpeed, 0L, HealthBonus.image);
		super(xCoordinate, yCoordinate, 0, 0L, HealthBonus.image);
		this.health = health;
	}
	
	public void apply(PlayerHelicopter player)
	{
		if(player.health > 0)
		{
			player.health += 50;
		}
	}
}
