package helicopterbattle;

import java.awt.image.BufferedImage;

/**
 * @author jeongwhanchoi
 *
 */

public class HealthBonus extends Bonus {
	public static BufferedImage image;
	
	public HealthBonus(double xCoordinate, double yCoordinate)
	{
//		super(xCoordinate, yCoordinate, 0, fallingSpeed, 0L, HealthBonus.image);
		super(xCoordinate, yCoordinate, 0, 0L, HealthBonus.image);
	}
	
	public void apply(PlayerHelicopter player)
	{
		if(player.data.getHealth() > 0)
		{
			player.data.setHealth(player.data.getHealth() + 50);
		}
	}
}
