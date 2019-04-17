package helicopterbattle;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class FrameworkData {
	private Rectangle playButton;
	private Rectangle helpButton;
	private Rectangle quitButton;
	private Rectangle backButton;
	private Rectangle heli1Button;
	private Rectangle heli2Button;
	private Rectangle heli3Button;
	private Rectangle heli4Button;
	private Rectangle heli5Button;
	private Rectangle heli6Button;
	private Rectangle heli7Button;
	private Rectangle heli8Button;
	private Rectangle restartButton;
	private Game game;
	private Font font;
	private Font buttonFont;
	private BufferedImage gameTitleImg;
	private BufferedImage menuBorderImg;
	private BufferedImage skyColorImg;
	private BufferedImage cloudLayer1Img;
	private BufferedImage cloudLayer2Img;
	private BufferedImage helpImg;
	private int select;
	private int selectHeli;

	public FrameworkData() {
	}

	public Rectangle getPlayButton() {
		return playButton;
	}

	public void setPlayButton(Rectangle playButton) {
		this.playButton = playButton;
	}

	public Rectangle getHelpButton() {
		return helpButton;
	}

	public void setHelpButton(Rectangle helpButton) {
		this.helpButton = helpButton;
	}

	public Rectangle getQuitButton() {
		return quitButton;
	}

	public void setQuitButton(Rectangle quitButton) {
		this.quitButton = quitButton;
	}

	public Rectangle getBackButton() {
		return backButton;
	}

	public void setBackButton(Rectangle backButton) {
		this.backButton = backButton;
	}

	public Rectangle getHeli1Button() {
		return heli1Button;
	}

	public void setHeli1Button(Rectangle heli1Button) {
		this.heli1Button = heli1Button;
	}

	public Rectangle getHeli2Button() {
		return heli2Button;
	}

	public void setHeli2Button(Rectangle heli2Button) {
		this.heli2Button = heli2Button;
	}

	public Rectangle getHeli3Button() {
		return heli3Button;
	}

	public void setHeli3Button(Rectangle heli3Button) {
		this.heli3Button = heli3Button;
	}

	public Rectangle getHeli4Button() {
		return heli4Button;
	}

	public void setHeli4Button(Rectangle heli4Button) {
		this.heli4Button = heli4Button;
	}

	public Rectangle getHeli5Button() {
		return heli5Button;
	}

	public void setHeli5Button(Rectangle heli5Button) {
		this.heli5Button = heli5Button;
	}

	public Rectangle getHeli6Button() {
		return heli6Button;
	}

	public void setHeli6Button(Rectangle heli6Button) {
		this.heli6Button = heli6Button;
	}

	public Rectangle getHeli7Button() {
		return heli7Button;
	}

	public void setHeli7Button(Rectangle heli7Button) {
		this.heli7Button = heli7Button;
	}

	public Rectangle getHeli8Button() {
		return heli8Button;
	}

	public void setHeli8Button(Rectangle heli8Button) {
		this.heli8Button = heli8Button;
	}

	public Rectangle getRestartButton() {
		return restartButton;
	}

	public void setRestartButton(Rectangle restartButton) {
		this.restartButton = restartButton;
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public Font getFont() {
		return font;
	}

	public void setFont(Font font) {
		this.font = font;
	}

	public Font getButtonFont() {
		return buttonFont;
	}

	public void setButtonFont(Font buttonFont) {
		this.buttonFont = buttonFont;
	}

	public BufferedImage getGameTitleImg() {
		return gameTitleImg;
	}

	public void setGameTitleImg(BufferedImage gameTitleImg) {
		this.gameTitleImg = gameTitleImg;
	}

	public BufferedImage getMenuBorderImg() {
		return menuBorderImg;
	}

	public void setMenuBorderImg(BufferedImage menuBorderImg) {
		this.menuBorderImg = menuBorderImg;
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

	public BufferedImage getHelpImg() {
		return helpImg;
	}

	public void setHelpImg(BufferedImage helpImg) {
		this.helpImg = helpImg;
	}


	public int getSelect() {
		return select;
	}

	public void setSelect(int select) {
		this.select = select;
	}

	public int getSelectHeli() {
		return selectHeli;
	}

	public void setSelectHeli(int selectHeli) {
		this.selectHeli = selectHeli;
	}
	public void drawMenuBackground(Graphics2D g2d){
        g2d.drawImage(skyColorImg,    0, 0, Framework.frameWidth, Framework.frameHeight, null);
        g2d.drawImage(cloudLayer1Img, 0, 0, Framework.frameWidth, Framework.frameHeight, null);
        g2d.drawImage(cloudLayer2Img, 0, 0, Framework.frameWidth, Framework.frameHeight, null);
        g2d.drawImage(menuBorderImg,  0, 0, Framework.frameWidth, Framework.frameHeight, null);
        g2d.setColor(Color.white);
        g2d.drawString("", 7, Framework.frameHeight - 5);
    }
    
    public void drawMenuSelect(Graphics2D g2d, int menu)
    {
		g2d.setColor(Color.darkGray);
    		switch(menu)
    		{
    		case 1:
    			g2d.fillRect(playButton.x, playButton.y, playButton.width, playButton.height);
    			g2d.setFont(buttonFont);
    			g2d.setColor(Color.white);
    			g2d.drawString("PLAY", playButton.x+125, playButton.y+75);
    			break;
    		case 2:
    			g2d.fillRect(helpButton.x, helpButton.y, helpButton.width, helpButton.height);
    			g2d.setFont(buttonFont);
    			g2d.setColor(Color.white);
    			g2d.drawString("HELP", helpButton.x+125, helpButton.y+75);
    			break;
    		case 3:
    			g2d.fillRect(quitButton.x, quitButton.y, quitButton.width, quitButton.height);
    			g2d.setFont(buttonFont);
    			g2d.setColor(Color.white);
    			g2d.drawString("QUIT", quitButton.x+125, quitButton.y+75);
    			break;
    		}
    		
    }
    
    public void drawHelicopterSelect(Graphics2D g2d, int heli)
    {
    	g2d.setColor(Color.darkGray);
    	switch(heli)
		{
		case 1:
			g2d.fillRect(heli1Button.x, heli1Button.y, heli1Button.width, heli1Button.height);
			g2d.setFont(buttonFont);
			g2d.setColor(Color.white);
			break;
		case 2:
			g2d.fillRect(heli2Button.x, heli2Button.y, heli2Button.width, heli2Button.height);
			g2d.setFont(buttonFont);
			g2d.setColor(Color.white);
			break;
		case 3:
			g2d.fillRect(heli3Button.x, heli3Button.y, heli3Button.width, heli3Button.height);
			g2d.setFont(buttonFont);
			g2d.setColor(Color.white);
			break;
		case 4:
			g2d.fillRect(heli4Button.x, heli4Button.y, heli4Button.width, heli4Button.height);
			g2d.setFont(buttonFont);
			g2d.setColor(Color.white);
			break;
		case 5:
			g2d.fillRect(heli5Button.x, heli5Button.y, heli5Button.width, heli5Button.height);
			g2d.setFont(buttonFont);
			g2d.setColor(Color.white);
			break;
		case 6:
			g2d.fillRect(heli6Button.x, heli6Button.y, heli6Button.width, heli6Button.height);
			g2d.setFont(buttonFont);
			g2d.setColor(Color.white);
			break;
		case 7:
			g2d.fillRect(heli7Button.x, heli7Button.y, heli7Button.width, heli7Button.height);
			g2d.setFont(buttonFont);
			g2d.setColor(Color.white);
			break;
		case 8:
			g2d.fillRect(heli8Button.x, heli8Button.y, heli8Button.width, heli8Button.height);
			g2d.setFont(buttonFont);
			g2d.setColor(Color.white);
			break;
		
		}
    }
    
    public void drawRestartSelect(Graphics2D g2d, int menu)
    {
		g2d.setColor(Color.darkGray);
    		switch(menu)
    		{
    		case 1:
    			g2d.fillRect(restartButton.x, restartButton.y, restartButton.width, restartButton.height);
    			g2d.setFont(buttonFont);
    			g2d.setColor(Color.white);
    			g2d.drawString("RESTART", restartButton.x+75, restartButton.y+75);
    			break;
    		case 2:
    			g2d.fillRect(quitButton.x, quitButton.y, quitButton.width, quitButton.height);
    			g2d.setFont(buttonFont);
    			g2d.setColor(Color.white);
    			g2d.drawString("QUIT", quitButton.x+125, quitButton.y+75);
    			break;
    		}
    		
    }
    public void drawMenu(Graphics2D g2d) {
    		drawMenuBackground(g2d);
        g2d.drawImage(gameTitleImg, Framework.frameWidth/2 - gameTitleImg.getWidth()/2, Framework.frameHeight/5 - 50, null);
        
        g2d.setFont(buttonFont);
        g2d.setColor(Color.white);
        g2d.drawString("PLAY", playButton.x+125, playButton.y+75);
        g2d.drawString("HELP", helpButton.x+125, helpButton.y+75);
        g2d.drawString("QUIT", quitButton.x+125, quitButton.y+75);
        g2d.draw(playButton);
        g2d.draw(helpButton);
        g2d.draw(quitButton);
    }
    public void drawGameOver(Graphics2D g2d) {
    		drawMenuBackground(g2d);
        
        g2d.setFont(buttonFont);
        g2d.setColor(Color.white);
        g2d.drawString("RESTART", helpButton.x+75, helpButton.y+75);
        g2d.drawString("QUIT", quitButton.x+125, quitButton.y+75);
        
        g2d.draw(restartButton);
        g2d.draw(quitButton);
    }
    public void drawHelp(Graphics2D g2d) {
	    	drawMenuBackground(g2d);
			
		g2d.drawImage(helpImg, Framework.frameWidth - helpImg.getWidth() - 110, Framework.frameHeight/5 - 50, null);
			
	    g2d.setFont(buttonFont);
	    g2d.drawString("BACK", backButton.x+120, backButton.y+75);
	    g2d.draw(backButton);
	    g2d.setFont(font);
	    g2d.drawString("Press BACKSPACE to return", backButton.x, backButton.y+150);
	    
    }
    public void drawQuit(Graphics2D g2d) {
    		drawMenuBackground(g2d);
		g2d.setFont(buttonFont);
		g2d.setColor(Color.white);
		g2d.drawString("Press ENTER to quit", Framework.frameWidth/2 - 300, 140);
		g2d.setFont(buttonFont);
		g2d.drawString("BACK", quitButton.x+120, quitButton.y+75);
		g2d.draw(backButton);
		g2d.setFont(font);
		g2d.setColor(Color.white);
		g2d.drawString("Press BACKSPACE to return", backButton.x, backButton.y+150);
		g2d.setColor(Color.black);
		g2d.setFont(buttonFont);
		g2d.drawString("Are you sure", Framework.frameWidth/2 -200, Framework.frameHeight/2 - 50);
		g2d.drawString("you really want to quit?", Framework.frameWidth/2 -300, Framework.frameHeight/2 +25);
    }
    public void drawSelect(Graphics2D g2d) {
    	drawMenuBackground(g2d);
		g2d.setFont(buttonFont);
		g2d.setColor(Color.white);
		g2d.drawString("SELECT YOUR HELICOPTER", Framework.frameWidth/2 - 400, 140);
		g2d.drawString("BACK", backButton.x+120, backButton.y+75);
		g2d.draw(backButton);
		g2d.setFont(font);
		g2d.drawString("Press BACKSPACE to return", backButton.x, backButton.y+150);
		
		g2d.draw(heli1Button);
		g2d.draw(heli2Button);
		g2d.draw(heli3Button);
		g2d.draw(heli4Button);
		g2d.draw(heli5Button);
		g2d.draw(heli6Button);
		g2d.draw(heli7Button);
		g2d.draw(heli8Button);
    }
}