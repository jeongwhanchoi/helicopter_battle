package helicopterbattle;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 * Framework that controls the game (Game.java) that created it, update it and draw it on the screen.
 * 
 * @author www.gametutorial.net
 */

public class Framework extends Canvas {
    
    /**
     * Width of the frame.
     */
    public static int frameWidth;
    /**
     * Height of the frame.
     */
    public static int frameHeight;

    /**
     * Time of one second in nanoseconds.
     * 1 second = 1 000 000 000 nanoseconds
     */
    public static final long secInNanosec = 1000000000L;
    
    /**
     * Time of one millisecond in nanoseconds.
     * 1 millisecond = 1 000 000 nanoseconds
     */
    public static final long milisecInNanosec = 1000000L;
    
    /**
     * FPS - Frames per second
     * How many times per second the game should update?
     */
    private final int GAME_FPS = 60;
    /**
     * Pause between updates. It is in nanoseconds.
     */
    private final long GAME_UPDATE_PERIOD = secInNanosec / GAME_FPS;
    
    /**
     * Possible states of the game
     */
    public static enum GameState{STARTING, VISUALIZING, GAME_CONTENT_LOADING, MAIN_MENU, OPTIONS, PLAYING, GAMEOVER, HELP, SELECT}
    /**
     * Current state of the game
     */
    public static GameState gameState;
    
    public Rectangle playButton;
	public Rectangle helpButton;
	public Rectangle settingButton;
	public Rectangle backButton;
	
	public Rectangle heli1Button, heli2Button, heli3Button, heli4Button, heli5Button, heli6Button, heli7Button, heli8Button; 
    
    /**
     * Elapsed game time in nanoseconds.
     */
    private long gameTime;
    // It is used for calculating elapsed time.
    private long lastTime;
    
    // The actual game
    private Game game;
    
    
    private Font font;
    private Font buttonFont;
    
    // Images for menu.
    private BufferedImage gameTitleImg;
    private BufferedImage menuBorderImg;
    private BufferedImage skyColorImg;
    private BufferedImage cloudLayer1Img;
    private BufferedImage cloudLayer2Img;
    private BufferedImage helpImg;
    private BufferedImage heli1Img, heli2Img, heli3Img,heli4Img;
    private BufferedImage heli5Img, heli6Img, heli7Img, heli8Img;
    
	private int select;
    
    
    public Framework ()
    {
        super();
        
        gameState = GameState.VISUALIZING;
        
        //We start game in new thread.
        Thread gameThread = new Thread() {
            @Override
            public void run(){
                GameLoop();
            }
        };
        gameThread.start();
    }
    
    
   /**
     * Set variables and objects.
     * This method is intended to set the variables and objects for this class, variables and objects for the actual game can be set in Game.java.
     */
    private void Initialize()
    {
        font = new Font("monospaced", Font.BOLD, 28);
        buttonFont = new Font("arial", Font.BOLD, 60);
        playButton = new Rectangle(frameWidth / 2 - 200, frameHeight / 2 - 120, 400, 100);
        helpButton = new Rectangle(frameWidth / 2 - 200, frameHeight / 2, 400, 100);
        settingButton = new Rectangle(frameWidth / 2 - 200, frameHeight / 2 + 120, 400, 100);
        backButton = new Rectangle(frameWidth / 2 - 200, frameHeight / 2 + 120, 400, 100);
        
        heli1Button = new Rectangle(frameWidth / 6, frameHeight / 3, 150, 75);
        heli2Button = new Rectangle(frameWidth / 6 +200, frameHeight / 3, 150, 75);
        heli3Button = new Rectangle(frameWidth / 6 +400, frameHeight / 3, 150, 75);
        heli4Button = new Rectangle(frameWidth / 6 +600, frameHeight / 3, 150, 75);
        heli5Button = new Rectangle(frameWidth / 6, frameHeight / 3 +150, 150, 75);
        heli6Button = new Rectangle(frameWidth / 6 +200, frameHeight / 3 +150, 150, 75);
        heli7Button = new Rectangle(frameWidth / 6 +400, frameHeight / 3 +150, 150, 75);
        heli8Button = new Rectangle(frameWidth / 6 +600, frameHeight / 3 +150, 150, 75);
        
        select = 1;
    }
    
    /**
     * Load files (images).
     * This method is intended to load files for this class, files for the actual game can be loaded in Game.java.
     */
    private void LoadContent()
    {
        try 
        {
            URL menuBorderImgUrl = this.getClass().getResource("/helicopterbattle/resources/images/menu_border.png");
            menuBorderImg = ImageIO.read(menuBorderImgUrl);
            
            URL skyColorImgUrl = this.getClass().getResource("/helicopterbattle/resources/images/sky_color.jpg");
            skyColorImg = ImageIO.read(skyColorImgUrl);
            
            URL gameTitleImgUrl = this.getClass().getResource("/helicopterbattle/resources/images/helicopter_battle_title.png");
            gameTitleImg = ImageIO.read(gameTitleImgUrl);
            
            URL cloudLayer1ImgUrl = this.getClass().getResource("/helicopterbattle/resources/images/cloud_layer_1.png");
            cloudLayer1Img = ImageIO.read(cloudLayer1ImgUrl);
            URL cloudLayer2ImgUrl = this.getClass().getResource("/helicopterbattle/resources/images/cloud_layer_2.png");
            cloudLayer2Img = ImageIO.read(cloudLayer2ImgUrl);
            
            URL helpImgUrl = this.getClass().getResource("/helicopterbattle/resources/images/help_01.png");
            helpImg = ImageIO.read(helpImgUrl); 
            
            URL heli1ImgUrl = this.getClass().getResource("/helicopterbattle/resources/images/1_helicopter_body_01.png");
            heli1Img = ImageIO.read(heli1ImgUrl); 
            URL heli2ImgUrl = this.getClass().getResource("/helicopterbattle/resources/images/1_helicopter_body_02.png");
            heli2Img = ImageIO.read(heli2ImgUrl); 
            URL heli3ImgUrl = this.getClass().getResource("/helicopterbattle/resources/images/1_helicopter_body_03.png");
            heli3Img = ImageIO.read(heli3ImgUrl); 
            URL heli4ImgUrl = this.getClass().getResource("/helicopterbattle/resources/images/1_helicopter_body_04.png");
            heli4Img = ImageIO.read(heli4ImgUrl); 
            URL heli5ImgUrl = this.getClass().getResource("/helicopterbattle/resources/images/1_helicopter_body_05.png");
            heli5Img = ImageIO.read(heli5ImgUrl); 
            URL heli6ImgUrl = this.getClass().getResource("/helicopterbattle/resources/images/1_helicopter_body_06.png");
            heli6Img = ImageIO.read(heli6ImgUrl); 
            URL heli7ImgUrl = this.getClass().getResource("/helicopterbattle/resources/images/1_helicopter_body_07.png");
            heli7Img = ImageIO.read(heli7ImgUrl); 
            URL heli8ImgUrl = this.getClass().getResource("/helicopterbattle/resources/images/1_helicopter_body_00.png");
            heli8Img = ImageIO.read(heli8ImgUrl); 
        } 
        catch (IOException ex) 
        {
            Logger.getLogger(Framework.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    /**
     * In specific intervals of time (GAME_UPDATE_PERIOD) the game/logic is updated and then the game is drawn on the screen.
     */
    private void GameLoop()
    {
        // This two variables are used in VISUALIZING state of the game. We used them to wait some time so that we get correct frame/window resolution.
        long visualizingTime = 0, lastVisualizingTime = System.nanoTime();
        
        // This variables are used for calculating the time that defines for how long we should put threat to sleep to meet the GAME_FPS.
        long beginTime, timeTaken, timeLeft;
        
        while(true)
        {
            beginTime = System.nanoTime();
            
            switch (gameState)
            {
                case PLAYING:
                    gameTime += System.nanoTime() - lastTime;
                    
                    game.UpdateGame(gameTime, mousePosition());
                    
                    lastTime = System.nanoTime();
                break;
                case GAMEOVER:
                    //...
                break;
                case MAIN_MENU:
                    //...
                break;
                case HELP:
                		//...
                break;
                case OPTIONS:
                    //...
                break;
                case SELECT:
                		//...
                break;
                case GAME_CONTENT_LOADING:
                    //...
                break;
                case STARTING:
                    // Sets variables and objects.
                    Initialize();
                    // Load files - images, sounds, ...
                    LoadContent();

                    // When all things that are called above finished, we change game status to main menu.
                    gameState = GameState.MAIN_MENU;
                break;
                case VISUALIZING:
                    // On Ubuntu OS (when I tested on my old computer) this.getWidth() method doesn't return the correct value immediately (eg. for frame that should be 800px width, returns 0 than 790 and at last 798px). 
                    // So we wait one second for the window/frame to be set to its correct size. Just in case we
                    // also insert 'this.getWidth() > 1' condition in case when the window/frame size wasn't set in time,
                    // so that we although get approximately size.
                    if(this.getWidth() > 1 && visualizingTime > secInNanosec)
                    {
                        frameWidth = this.getWidth();
                        frameHeight = this.getHeight();

                        // When we get size of frame we change status.
                        gameState = GameState.STARTING;
                    }
                    else
                    {
                        visualizingTime += System.nanoTime() - lastVisualizingTime;
                        lastVisualizingTime = System.nanoTime();
                    }
                break;
            }
            
            // Repaint the screen.
            repaint();
            
            // Here we calculate the time that defines for how long we should put threat to sleep to meet the GAME_FPS.
            timeTaken = System.nanoTime() - beginTime;
            timeLeft = (GAME_UPDATE_PERIOD - timeTaken) / milisecInNanosec; // In milliseconds
            // If the time is less than 10 milliseconds, then we will put thread to sleep for 10 millisecond so that some other thread can do some work.
            if (timeLeft < 10) 
                timeLeft = 10; //set a minimum
            try {
                 //Provides the necessary delay and also yields control so that other thread can do work.
                 Thread.sleep(timeLeft);
            } catch (InterruptedException ex) { }
        }
    }
    
    /**
     * Draw the game to the screen. It is called through repaint() method in GameLoop() method.
     */
    @Override
    public void Draw(Graphics2D g2d)
    {
        switch (gameState)
        {
            case PLAYING:
                game.Draw(g2d, mousePosition(), gameTime);
            break;
            case GAMEOVER:
                drawMenuBackground(g2d);
                g2d.setColor(Color.black);
                g2d.drawString("Press ENTER to restart or ESC to exit.", frameWidth/2 - 113, frameHeight/4 + 30);
                game.DrawStatistic(g2d, gameTime);
                g2d.setFont(font);
                g2d.drawString("GAME OVER", frameWidth/2 - 90, frameHeight/4);
            break;
            case MAIN_MENU:
                drawMenuBackground(g2d);
                g2d.drawImage(gameTitleImg, frameWidth/2 - gameTitleImg.getWidth()/2, frameHeight/5 - 50, null);
                
                g2d.setFont(buttonFont);
                g2d.setColor(Color.white);
                g2d.drawString("PLAY", playButton.x+125, playButton.y+75);
                g2d.drawString("HELP", helpButton.x+125, helpButton.y+75);
                g2d.drawString("SETTING", settingButton.x+75, settingButton.y+75);
                g2d.draw(playButton);
                g2d.draw(helpButton);
                g2d.draw(settingButton);
                
                if(select == 1)
                		drawMenuSelect(g2d, 1);
                else if(select == 2)
                		drawMenuSelect(g2d, 2);
                else
                		drawMenuSelect(g2d, 3);
                
            break;
            case HELP:
            		drawMenuBackground(g2d);
            		
                g2d.setFont(buttonFont);
                g2d.drawString("BACK", settingButton.x+120, settingButton.y+75);
                g2d.draw(backButton);
                
                g2d.drawImage(helpImg, 0, 0, Framework.frameWidth, Framework.frameHeight, null);
            	break;
            case OPTIONS:
            		drawMenuBackground(g2d);
            		g2d.setFont(buttonFont);
            		g2d.setColor(Color.white);
            		g2d.drawString("OPTIONS", frameWidth/2 - 70, 140);
            		g2d.setFont(buttonFont);
            		g2d.drawString("BACK", settingButton.x+120, settingButton.y+75);
            		g2d.draw(backButton);
            break;
            case SELECT:
            		drawMenuBackground(g2d);
            		g2d.setFont(buttonFont);
            		g2d.setColor(Color.white);
            		g2d.drawString("SELECT", frameWidth/3 + 70, 140);
            		g2d.drawString("BACK", settingButton.x+120, settingButton.y+75);
            		g2d.draw(backButton);
            		
            		g2d.draw(heli1Button);
            		g2d.draw(heli2Button);
            		g2d.draw(heli3Button);
            		g2d.draw(heli4Button);
            		g2d.draw(heli5Button);
            		g2d.draw(heli6Button);
            		g2d.draw(heli7Button);
            		g2d.draw(heli8Button);
            		
            		
            		if(select == 1)
                		drawHelicopterSelect(g2d, 1);
                else if(select == 2)
                		drawHelicopterSelect(g2d, 2);
                else if(select == 3)
            			drawHelicopterSelect(g2d, 3);
                else if(select == 4)
            			drawHelicopterSelect(g2d, 4);
                else if(select == 5)
            			drawHelicopterSelect(g2d, 5);
                else if(select == 6)
            			drawHelicopterSelect(g2d, 6);
                else if(select == 7)
            			drawHelicopterSelect(g2d, 7);
                else
                		drawHelicopterSelect(g2d, 8);
            		
            		drawHelicopter(g2d);
            		
            	break;
            case GAME_CONTENT_LOADING:
                g2d.setColor(Color.white);
                g2d.drawString("GAME is LOADING", frameWidth/2 - 50, frameHeight/2);
            break;
        }
    }
    
    
    /**
     * Starts new game.
     */
    /*private void newGame(KeyEvent helicopterType)
    {
        // We set gameTime to zero and lastTime to current time for later calculations.
        gameTime = 0;
        lastTime = System.nanoTime();
        
        game = new Game(helicopterType);
    }*/
    private void newGame(int helicopterSelect)
    {
        // We set gameTime to zero and lastTime to current time for later calculations.
        gameTime = 0;
        lastTime = System.nanoTime();
        
        game = new Game(helicopterSelect);
    }
    
    /**
     *  Restart game - reset game time and call RestartGame() method of game object so that reset some variables.
     */
    private void restartGame()
    {
        // We set gameTime to zero and lastTime to current time for later calculations.
        gameTime = 0;
        lastTime = System.nanoTime();
        
        game.RestartGame();
        
        // We change game status so that the game can start.
        gameState = GameState.PLAYING;
    }
    
    
    /**
     * Returns the position of the mouse pointer in game frame/window.
     * If mouse position is null than this method return 0,0 coordinate.
     * 
     * @return Point of mouse coordinates.
     */
    private Point mousePosition()
    {
        try
        {
            Point mp = this.getMousePosition();
            
            if(mp != null)
                return this.getMousePosition();
            else
                return new Point(0, 0);
        }
        catch (Exception e)
        {
            return new Point(0, 0);
        }
    }
    
    
    /**
     * This method is called when keyboard key is released.
     * 
     * @param e KeyEvent
     */
    @Override
    public void keyReleasedFramework(KeyEvent e)
    {
        if(e.getKeyCode() == KeyEvent.VK_ESCAPE)
            System.exit(0);
        
        switch(gameState)
        {
            case GAMEOVER:
                if(e.getKeyCode() == KeyEvent.VK_ENTER)
                    restartGame();
            break;
            case MAIN_MENU:
            		
            		if(e.getKeyCode() == KeyEvent.VK_DOWN)
            		{
            			select ++;
            			if(select > 3)
            				select -= 3;
            		}
            		if(e.getKeyCode() == KeyEvent.VK_UP)
            		{
            			select --;
            			if(select < 1)
            				select += 3;
            		}
                	if(select == 1 && e.getKeyCode() == KeyEvent.VK_ENTER)
                		gameState = GameState.SELECT;
                	if(select == 2 && e.getKeyCode() == KeyEvent.VK_ENTER)
                			gameState = GameState.HELP;
                	if(select == 3 && e.getKeyCode() == KeyEvent.VK_ENTER)
                			gameState = GameState.OPTIONS;
            break;
            case SELECT:
            		if(e.getKeyCode() == KeyEvent.VK_RIGHT)
            		{
            			++select;
            			if(select > 8)
            				select -= 8;
            		}
            		if(e.getKeyCode() == KeyEvent.VK_LEFT)
            		{
            			--select;
            			if(select < 1)
            				select += 8;
            		}
            		if(e.getKeyCode() == KeyEvent.VK_DOWN)
            		{
            			select += 4;
            			if(select > 8)
            				select -= 8;
            		}
            		if(e.getKeyCode() == KeyEvent.VK_UP)
            		{
            			select -= 4;
            			if(select < 1)
            				select += 8;
            		}
            		
            		if(select == 1 && e.getKeyCode() == KeyEvent.VK_ENTER)
            			newGame(select);
                	if(select == 2 && e.getKeyCode() == KeyEvent.VK_ENTER)
                		newGame(select);
                	if(select == 3 && e.getKeyCode() == KeyEvent.VK_ENTER)
                		newGame(select);
                	if(select == 4 && e.getKeyCode() == KeyEvent.VK_ENTER)
                		newGame(select);
                	if(select == 5 && e.getKeyCode() == KeyEvent.VK_ENTER)
                		newGame(select);
                	if(select == 6 && e.getKeyCode() == KeyEvent.VK_ENTER)
                		newGame(select);
                	if(select == 7 && e.getKeyCode() == KeyEvent.VK_ENTER)
                		newGame(select);
                	if(select == 8 && e.getKeyCode() == KeyEvent.VK_ENTER)
                		newGame(select);
                	
            		if(e.getKeyCode() == KeyEvent.VK_BACK_SPACE)
            			gameState = GameState.MAIN_MENU;
//            		else
//            			newGame(e);
            break;
            case HELP:
            		if(e.getKeyCode() == KeyEvent.VK_BACK_SPACE)
            			gameState = GameState.MAIN_MENU;
            	break;
            case OPTIONS:
            		if(e.getKeyCode() == KeyEvent.VK_BACK_SPACE)
            			gameState = GameState.MAIN_MENU;
            	break;
           
        }
        
    }
    
    /**
     * This method is called when mouse button is clicked.
     * 
     * @param e MouseEvent
     */
    @Override
    public void mouseClicked(MouseEvent e)
    {
        
    }
    
    /*public void mousePressed(MouseEvent e)
    {
    		int mx = e.getX();
    		int my = e.getY();
    		
    		if(gameState == GameState.MAIN_MENU) {
    			//Play button
        		if(mouseOver(mx, my, frameWidth / 2 - 200, frameHeight / 2 - 120, 400, 100))
        		{
        			System.exit(0);
        		}
        		
        		//Help button
        		if(mouseOver(mx, my, frameWidth / 2 - 200, frameHeight / 2, 400, 100)) {
        			gameState = GameState.HELP;
        			
        		//Setting button
        			//...
        		}
    		}
    		    		
    		//Back button for HELP
    		if(gameState == GameState.HELP)
    		{
    			if(mouseOver(mx, my, frameWidth / 2 - 200, frameHeight / 2 + 120, 400, 100))
    			{
    				gameState = GameState.MAIN_MENU;
    			}
    		}
    		
    }
    public void mouseReleased(MouseEvent e)
    {
    	
    }
    private boolean mouseOver(int mx, int my, int x, int y, int width, int height)
    {
    		if(mx > x && mx < x + width)
    		{
    			if(my > y && my < y + height)
    			{
    				return true;
    			}
    			else return false;
    		}
    		else return false;
    }*/
    
    private void drawMenuBackground(Graphics2D g2d){
        g2d.drawImage(skyColorImg,    0, 0, Framework.frameWidth, Framework.frameHeight, null);
        g2d.drawImage(cloudLayer1Img, 0, 0, Framework.frameWidth, Framework.frameHeight, null);
        g2d.drawImage(cloudLayer2Img, 0, 0, Framework.frameWidth, Framework.frameHeight, null);
        g2d.drawImage(menuBorderImg,  0, 0, Framework.frameWidth, Framework.frameHeight, null);
        g2d.setColor(Color.white);
        g2d.drawString("", 7, frameHeight - 5);
    }
    
    private void drawHelicopter(Graphics2D g2d)
    {
    		g2d.drawImage(heli1Img,frameWidth / 6, frameHeight / 3, 150, 48, null);
    		g2d.drawImage(heli2Img,frameWidth / 6 +200, frameHeight / 3, 150, 42, null);
    		g2d.drawImage(heli3Img,frameWidth / 6 +400, frameHeight / 3, 150, 52, null);
    		g2d.drawImage(heli4Img,frameWidth / 6 +600, frameHeight / 3, 150, 75, null);
    		g2d.drawImage(heli5Img,frameWidth / 6, frameHeight / 3 +150, 150, 68, null);
    		g2d.drawImage(heli6Img,frameWidth / 6 +200, frameHeight / 3 +150, 150, 33, null);
    		g2d.drawImage(heli7Img,frameWidth / 6 +400, frameHeight / 3 +150, 132, 75, null);
    		g2d.drawImage(heli8Img,frameWidth / 6 +600, frameHeight / 3 +150, 150, 31, null);

//    		heli1Button = new Rectangle(frameWidth / 6, frameHeight / 3, 150, 75);
//          heli2Button = new Rectangle(frameWidth / 6 +200, frameHeight / 3, 150, 75);
//          heli3Button = new Rectangle(frameWidth / 6 +400, frameHeight / 3, 150, 75);
//          heli4Button = new Rectangle(frameWidth / 6 +600, frameHeight / 3, 150, 75);
//          heli5Button = new Rectangle(frameWidth / 6, frameHeight / 3 +150, 150, 75);
//          heli6Button = new Rectangle(frameWidth / 6 +200, frameHeight / 3 +150, 150, 75);
//          heli7Button = new Rectangle(frameWidth / 6 +400, frameHeight / 3 +150, 150, 75);
//          heli8Button = new Rectangle(frameWidth / 6 +600, frameHeight / 3 +150, 150, 75);
//          
    }
    
    private void drawMenuSelect(Graphics2D g2d, int menu)
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
    			g2d.fillRect(settingButton.x, settingButton.y, settingButton.width, settingButton.height);
    			g2d.setFont(buttonFont);
    			g2d.setColor(Color.white);
    			g2d.drawString("SETTING", settingButton.x+75, settingButton.y+75);
    			break;
    		}
    		
    }
    
    private void drawHelicopterSelect(Graphics2D g2d, int heli)
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
}
