package helicopterbattle;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/**
 * @author jeongwhanchoi
 *
 */

public abstract class Bonus {
	public long secDuration;
	public long consumeTime;
//	public double fallingSpeed;
	public BufferedImage image;
	
	public double xCoordinate;
	public double yCoordinate;
	
	/*protected Bonus(double xCoordinate, double yCoordinate, long secDuration, double fallingSpeed, long consumeTime, BufferedImage image)
	{
		this.xCoordinate = xCoordinate;
		this.yCoordinate = yCoordinate;
		this.secDuration = secDuration;
		this.fallingSpeed = fallingSpeed;
		this.consumeTime = consumeTime;
		this.image = image;
	}*/
	protected Bonus(double xCoordinate, double yCoordinate, long secDuration, long consumeTime, BufferedImage image)
	{
		this.xCoordinate = xCoordinate;
		this.yCoordinate = yCoordinate;
		this.secDuration = secDuration;
		this.consumeTime = consumeTime;
		this.image = image;
	}
	
	/*public void update()
	{
		yCoordinate += fallingSpeed;
		
	}*/
	
	public void Draw(Graphics2D g2d)
	{
		g2d.drawImage(image, (int)xCoordinate, (int)yCoordinate, null);
	}
	
	public boolean isLeftScreen()
	{
		return yCoordinate > Framework.frameHeight;
	}
	
	public abstract void apply(PlayerHelicopter player);
}
