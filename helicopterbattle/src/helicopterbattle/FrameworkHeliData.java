package helicopterbattle;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class FrameworkHeliData {
	private BufferedImage heli1Img;
	private BufferedImage heli2Img;
	private BufferedImage heli3Img;
	private BufferedImage heli4Img;
	private BufferedImage heli5Img;
	private BufferedImage heli6Img;
	private BufferedImage heli7Img;
	private BufferedImage heli8Img;
	
	
	
	public BufferedImage getHeli1Img() {
		return heli1Img;
	}

	public void setHeli1Img(BufferedImage heli1Img) {
		this.heli1Img = heli1Img;
	}

	public BufferedImage getHeli2Img() {
		return heli2Img;
	}

	public void setHeli2Img(BufferedImage heli2Img) {
		this.heli2Img = heli2Img;
	}

	public BufferedImage getHeli3Img() {
		return heli3Img;
	}

	public void setHeli3Img(BufferedImage heli3Img) {
		this.heli3Img = heli3Img;
	}

	public BufferedImage getHeli4Img() {
		return heli4Img;
	}

	public void setHeli4Img(BufferedImage heli4Img) {
		this.heli4Img = heli4Img;
	}

	public BufferedImage getHeli5Img() {
		return heli5Img;
	}

	public void setHeli5Img(BufferedImage heli5Img) {
		this.heli5Img = heli5Img;
	}

	public BufferedImage getHeli6Img() {
		return heli6Img;
	}

	public void setHeli6Img(BufferedImage heli6Img) {
		this.heli6Img = heli6Img;
	}

	public BufferedImage getHeli7Img() {
		return heli7Img;
	}

	public void setHeli7Img(BufferedImage heli7Img) {
		this.heli7Img = heli7Img;
	}

	public BufferedImage getHeli8Img() {
		return heli8Img;
	}

	public void setHeli8Img(BufferedImage heli8Img) {
		this.heli8Img = heli8Img;
	}
	
	
	
	public void drawHelicopter(Graphics2D g2d)
    {
    		g2d.drawImage(heli1Img,Framework.frameWidth / 6, Framework.frameHeight / 3, 150, 48, null);
    		g2d.drawImage(heli2Img,Framework.frameWidth / 6 +200, Framework.frameHeight / 3, 150, 42, null);
    		g2d.drawImage(heli3Img,Framework.frameWidth / 6 +400, Framework.frameHeight / 3, 150, 52, null);
    		g2d.drawImage(heli4Img,Framework.frameWidth / 6 +600, Framework.frameHeight / 3, 150, 75, null);
    		g2d.drawImage(heli5Img,Framework.frameWidth / 6, Framework.frameHeight / 3 +150, 150, 68, null);
    		g2d.drawImage(heli6Img,Framework.frameWidth / 6 +200, Framework.frameHeight / 3 +150, 150, 33, null);
    		g2d.drawImage(heli7Img,Framework.frameWidth / 6 +400, Framework.frameHeight / 3 +150, 132, 75, null);
    		g2d.drawImage(heli8Img,Framework.frameWidth / 6 +600, Framework.frameHeight / 3 +150, 150, 31, null);
    }
    
}
