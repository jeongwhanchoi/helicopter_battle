package helicopterbattle;

import java.awt.image.BufferedImage;

/**
 * @author jeongwhanchoi
 *
 */


public class BulletBonus extends Bonus {
	private int bullets;
	public static BufferedImage image;
	
	public BulletBonus(double xCoordinate, double yCoordinate, int bullets) {
		super(xCoordinate, yCoordinate, 0, 0L, BulletBonus.image);
		this.bullets = bullets;
	}

	@Override
	public void apply(PlayerHelicopter player) {
		player.data.setNumberOfAmmo(player.data.getNumberOfAmmo() + bullets);
	}
}
