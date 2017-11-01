package helicopterbattle;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/**
 * @author jeongwhanchoi
 *
 */
public class Missile {
		// Time that must pass before another rocket can be fired.
		public static final long timeBetweenNewRockets = Framework.secInNanosec / 4;
		public static long timeOfLastCreatedRocket = 0;

		// Damage that is made to an enemy helicopter when it is hit with a rocket.
		public static int damagePower = 100;

		// Rocket position
	    public int xCoordinate;
	    public int yCoordinate;

	    // Moving speed and also direction. Rocket goes always straight, so we move it only on x coordinate.
	    private double movingXSpeed;

		// Speed and direction of the missile
		private double priorYSpeed, currentYSpeed, maxYSpeed;

		// Life time of current piece of rocket smoke.
		public long currentSmokeLifeTime;

		// Image of rocket. Image is loaded and set in Game class in LoadContent() method.
		public static BufferedImage rocketImg;

		
		/**
		 * Set variables and objects for this class.
		 */
		
		public void Initialize(int xCoordinate, int yCoordinate) {
			this.xCoordinate = xCoordinate;
			this.yCoordinate = yCoordinate;

			this.movingXSpeed = 15;
			this.priorYSpeed = 0;
			this.currentYSpeed = 0;
			this.maxYSpeed = 5;

			this.currentSmokeLifeTime = Framework.secInNanosec / 2;
		}

		/**
		 * Checks if rocket has left screen
		 * 
		 * @return true if the rocket is left the screen, false otherwise.
		 */
		public boolean isItLeftScreen() {
			if (xCoordinate > 0 && xCoordinate < Framework.frameWidth) //Rocket moves only on x coordinate so we don't need to check y coordinate.
				return false;
			else
				return true;
		}

		/**
		 * Moves the rocket
		 */
		public void Update() {
			xCoordinate += movingXSpeed;
		}
		
		/**
		 * Find the target
		 */
		public void findTarget(EnemyHelicopter enemy) {
			final int RANGE = 300;
			final int BUTTER_ZONE = 100;
			final int SWEET_SPOT = 25;
			final int KILL_ZONE = 5;
			
			if (this.yCoordinate > enemy.getYCenter() && this.yCoordinate <= enemy.getYCenter() + RANGE) {
				if (this.yCoordinate > enemy.getYCenter() + BUTTER_ZONE
						|| this.xCoordinate + this.rocketImg.getWidth() < enemy.getXCenter() - enemy.helicopterBodyImg.getWidth()) {
					currentYSpeed -= 0.4;
				} else if (this.yCoordinate > enemy.getYCenter() + SWEET_SPOT) {
					currentYSpeed = -2;
				} else if (this.yCoordinate > enemy.getYCenter() + KILL_ZONE) {
					currentYSpeed = -0.5;
				} else {
					currentYSpeed = -0.1;
				}
				
				if (Math.abs(currentYSpeed) > maxYSpeed)
					currentYSpeed = -maxYSpeed;
				
			}
			else if (this.yCoordinate < enemy.getYCenter() && this.yCoordinate >= enemy.getYCenter() - RANGE) {
				if (this.yCoordinate < enemy.getYCenter() - BUTTER_ZONE || this.xCoordinate + this.rocketImg.getWidth() < enemy.getXCenter() - enemy.helicopterBodyImg.getWidth()) {
					currentYSpeed = currentYSpeed + 0.4;
				} else if (this.yCoordinate < enemy.getYCenter() - SWEET_SPOT) {
					currentYSpeed = 2;
				} else if (this.yCoordinate < enemy.getYCenter() - KILL_ZONE) {
					currentYSpeed = 0.5;
				} else {
					currentYSpeed = 0.1;
				}
				
				if (currentYSpeed > maxYSpeed)
					currentYSpeed = maxYSpeed;

			} 
			else if (this.yCoordinate == enemy.getYCenter()) {
				currentYSpeed = 0.1;
			}

			yCoordinate += currentYSpeed;
			
		}
		
		/**
	     * Draws the rocket to the screen.
	     * 
	     * @param g2d Graphics2D
	     */
	    public void Draw(Graphics2D g2d)
	    {
	        g2d.drawImage(rocketImg, xCoordinate, yCoordinate, null);
	    }

}
