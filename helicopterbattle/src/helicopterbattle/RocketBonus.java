package helicopterbattle;
import java.awt.image.BufferedImage;

/**
 * @author jeongwhanchoi
 *
 */

public class RocketBonus extends Bonus {
	int rockets;
	public static BufferedImage image;
	
	public RocketBonus(double xCoordinate, double yCoordinate, int rockets) {
		super(xCoordinate, yCoordinate, 0, 0L, RocketBonus.image);
		this.rockets = rockets;
	}

	@Override
	public void apply(PlayerHelicopter player) {
		player.numberOfRockets += rockets;
	}
}
