package helicopterbattle;

import java.awt.image.BufferedImage;

/**
 * @author jeongwhanchoi
 *
 */

public class HealthBonus extends Bonus {
	public static BufferedImage image;
	
	public HealthBonus(double xCoordinate, double yCoordinate, int health)
	{
//		super(xCoordinate, yCoordinate, 0, fallingSpeed, 0L, HealthBonus.image);
		super(xCoordinate, yCoordinate, 0, 0L, HealthBonus.image);
	}
	
	public void apply(PlayerHelicopter player)
	{
		if(player.health > 0)
		{
			player.health += 50;
		}
	}
}
