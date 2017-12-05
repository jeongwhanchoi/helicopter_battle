package helicopterbattle;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
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
    public static enum GameState{STARTING, VISUALIZING, GAME_CONTENT_LOADING, MAIN_MENU, PLAYING, GAMEOVER, HELP, SELECT, RESELECT, QUIT}
    /**
     * Current state of the game
     */
    public static GameState gameState;
    
    /**
     * Elapsed game time in nanoseconds.
     */
    private long gameTime;
    // It is used for calculating elapsed time.
    private long lastTime;
    
    // The actual game
    private Game game;
    
    
    public FrameworkMenuData menuData = new FrameworkMenuData();


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
        menuData.setFont(new Font("monospaced", Font.BOLD, 28));
        menuData.setButtonFont(new Font("arial", Font.BOLD, 60));
        menuData.setPlayButton(new Rectangle(frameWidth / 2 - 200, frameHeight / 2 - 120, 400, 100));
        menuData.setHelpButton(new Rectangle(frameWidth / 2 - 200, frameHeight / 2, 400, 100));
        menuData.setQuitButton(new Rectangle(frameWidth / 2 - 200, frameHeight / 2 + 120, 400, 100));
        menuData.setBackButton(new Rectangle(frameWidth / 2 - 200, frameHeight / 2 + 120, 400, 100));
        
        menuData.setHeli1Button(new Rectangle(frameWidth / 6, frameHeight / 3, 150, 75));
        menuData.setHeli2Button(new Rectangle(frameWidth / 6 +200, frameHeight / 3, 150, 75));
        menuData.setHeli3Button(new Rectangle(frameWidth / 6 +400, frameHeight / 3, 150, 75));
        menuData.setHeli4Button(new Rectangle(frameWidth / 6 +600, frameHeight / 3, 150, 75));
        menuData.setHeli5Button(new Rectangle(frameWidth / 6, frameHeight / 3 +150, 150, 75));
        menuData.setHeli6Button(new Rectangle(frameWidth / 6 +200, frameHeight / 3 +150, 150, 75));
        menuData.setHeli7Button(new Rectangle(frameWidth / 6 +400, frameHeight / 3 +150, 150, 75));
        menuData.setHeli8Button(new Rectangle(frameWidth / 6 +600, frameHeight / 3 +150, 150, 75));
        
        menuData.setRestartButton(new Rectangle(frameWidth / 2 - 200, frameHeight / 2, 400, 100));
        
        menuData.setSelect(1);
        menuData.setSelectHeli(1);
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
            menuData.setMenuBorderImg(ImageIO.read(menuBorderImgUrl));
            
            URL skyColorImgUrl = this.getClass().getResource("/helicopterbattle/resources/images/sky_color.jpg");
            menuData.setSkyColorImg(ImageIO.read(skyColorImgUrl));
            
            URL gameTitleImgUrl = this.getClass().getResource("/helicopterbattle/resources/images/helicopter_battle_title.png");
            menuData.setGameTitleImg(ImageIO.read(gameTitleImgUrl));
            
            URL cloudLayer1ImgUrl = this.getClass().getResource("/helicopterbattle/resources/images/cloud_layer_1.png");
            menuData.setCloudLayer1Img(ImageIO.read(cloudLayer1ImgUrl));
            URL cloudLayer2ImgUrl = this.getClass().getResource("/helicopterbattle/resources/images/cloud_layer_2.png");
            menuData.setCloudLayer2Img(ImageIO.read(cloudLayer2ImgUrl));
            
            URL helpImgUrl = this.getClass().getResource("/helicopterbattle/resources/images/help_01.png");
            menuData.setHelpImg(ImageIO.read(helpImgUrl)); 
            
            URL heli1ImgUrl = this.getClass().getResource("/helicopterbattle/resources/images/1_helicopter_body_01.png");
            menuData.setHeli1Img(ImageIO.read(heli1ImgUrl)); 
            URL heli2ImgUrl = this.getClass().getResource("/helicopterbattle/resources/images/1_helicopter_body_02.png");
            menuData.setHeli2Img(ImageIO.read(heli2ImgUrl)); 
            URL heli3ImgUrl = this.getClass().getResource("/helicopterbattle/resources/images/1_helicopter_body_03.png");
            menuData.setHeli3Img(ImageIO.read(heli3ImgUrl)); 
            URL heli4ImgUrl = this.getClass().getResource("/helicopterbattle/resources/images/1_helicopter_body_04.png");
            menuData.setHeli4Img(ImageIO.read(heli4ImgUrl)); 
            URL heli5ImgUrl = this.getClass().getResource("/helicopterbattle/resources/images/1_helicopter_body_05.png");
            menuData.setHeli5Img(ImageIO.read(heli5ImgUrl)); 
            URL heli6ImgUrl = this.getClass().getResource("/helicopterbattle/resources/images/1_helicopter_body_06.png");
            menuData.setHeli6Img(ImageIO.read(heli6ImgUrl)); 
            URL heli7ImgUrl = this.getClass().getResource("/helicopterbattle/resources/images/1_helicopter_body_07.png");
            menuData.setHeli7Img(ImageIO.read(heli7ImgUrl)); 
            URL heli8ImgUrl = this.getClass().getResource("/helicopterbattle/resources/images/1_helicopter_body_00.png");
            menuData.setHeli8Img(ImageIO.read(heli8ImgUrl)); 
            
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
                		game.checkScore();
                break;
                case MAIN_MENU:
                    //...
                break;
                case HELP:
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
                
                game.DrawStatistic(g2d, gameTime);
                /*g2d.setFont(buttonFont);
                g2d.drawString("GAME OVER", frameWidth/2 - 150, frameHeight/4);
                */
                g2d.setFont(menuData.getButtonFont());
                g2d.setColor(Color.white);
                g2d.drawString("RESTART", menuData.getHelpButton().x+75, menuData.getHelpButton().y+75);
                g2d.drawString("QUIT", menuData.getQuitButton().x+125, menuData.getQuitButton().y+75);
                
                g2d.draw(menuData.getRestartButton());
                g2d.draw(menuData.getQuitButton());
                
                drawRestartSelect(g2d, menuData.getSelect());
            break;
            case MAIN_MENU:
                drawMenuBackground(g2d);
                g2d.drawImage(menuData.getGameTitleImg(), frameWidth/2 - menuData.getGameTitleImg().getWidth()/2, frameHeight/5 - 50, null);
                
                g2d.setFont(menuData.getButtonFont());
                g2d.setColor(Color.white);
                g2d.drawString("PLAY", menuData.getPlayButton().x+125, menuData.getPlayButton().y+75);
                g2d.drawString("HELP", menuData.getHelpButton().x+125, menuData.getHelpButton().y+75);
                g2d.drawString("QUIT", menuData.getQuitButton().x+125, menuData.getQuitButton().y+75);
                g2d.draw(menuData.getPlayButton());
                g2d.draw(menuData.getHelpButton());
                g2d.draw(menuData.getQuitButton());
                
                drawMenuSelect(g2d, menuData.getSelect());
                
            break;
            case HELP:
            		drawMenuBackground(g2d);
            		
            		g2d.drawImage(menuData.getHelpImg(), frameWidth - menuData.getHelpImg().getWidth() - 110, frameHeight/5 - 50, null);
            		
                g2d.setFont(menuData.getButtonFont());
                g2d.drawString("BACK", menuData.getBackButton().x+120, menuData.getBackButton().y+75);
                g2d.draw(menuData.getBackButton());
                g2d.setFont(menuData.getFont());
                g2d.drawString("Press BACKSPACE to return", menuData.getBackButton().x, menuData.getBackButton().y+150);
                
                
            	break;
            case QUIT:
            		drawMenuBackground(g2d);
            		g2d.setFont(menuData.getButtonFont());
            		g2d.setColor(Color.white);
            		g2d.drawString("Press ENTER to quit", frameWidth/2 - 300, 140);
            		g2d.setFont(menuData.getButtonFont());
            		g2d.drawString("BACK", menuData.getQuitButton().x+120, menuData.getQuitButton().y+75);
            		g2d.draw(menuData.getBackButton());
            		g2d.setFont(menuData.getFont());
            		g2d.setColor(Color.white);
            		g2d.drawString("Press BACKSPACE to return", menuData.getBackButton().x, menuData.getBackButton().y+150);
            		g2d.setColor(Color.black);
            		g2d.setFont(menuData.getButtonFont());
            		g2d.drawString("Are you sure", frameWidth/2 -200, frameHeight/2 - 50);
            		g2d.drawString("you really want to quit?", frameWidth/2 -300, frameHeight/2 +25);
            break;
            case SELECT:
            		drawMenuBackground(g2d);
            		g2d.setFont(menuData.getButtonFont());
            		g2d.setColor(Color.white);
            		g2d.drawString("SELECT YOUR HELICOPTER", frameWidth/2 - 400, 140);
            		g2d.drawString("BACK", menuData.getBackButton().x+120, menuData.getBackButton().y+75);
            		g2d.draw(menuData.getBackButton());
            		g2d.setFont(menuData.getFont());
            		g2d.drawString("Press BACKSPACE to return", menuData.getBackButton().x, menuData.getBackButton().y+150);
            		
            		g2d.draw(menuData.getHeli1Button());
            		g2d.draw(menuData.getHeli2Button());
            		g2d.draw(menuData.getHeli3Button());
            		g2d.draw(menuData.getHeli4Button());
            		g2d.draw(menuData.getHeli5Button());
            		g2d.draw(menuData.getHeli6Button());
            		g2d.draw(menuData.getHeli7Button());
            		g2d.draw(menuData.getHeli8Button());
            		
            		
            		drawHelicopterSelect(g2d, menuData.getSelectHeli());
            		
            		drawHelicopter(g2d);
            		
            	break;
            case RESELECT:
	            	drawMenuBackground(g2d);
	        		g2d.setFont(menuData.getButtonFont());
	        		g2d.setColor(Color.white);
	        		g2d.drawString("SELECT YOUR HELICOPTER", frameWidth/2 - 400, 140);
	        		g2d.drawString("BACK", menuData.getBackButton().x+120, menuData.getBackButton().y+75);
	        		g2d.draw(menuData.getBackButton());
	        		g2d.setFont(menuData.getFont());
	        		g2d.drawString("Press BACKSPACE to return", menuData.getBackButton().x, menuData.getBackButton().y+150);
	        		
	        		g2d.draw(menuData.getHeli1Button());
	        		g2d.draw(menuData.getHeli2Button());
	        		g2d.draw(menuData.getHeli3Button());
	        		g2d.draw(menuData.getHeli4Button());
	        		g2d.draw(menuData.getHeli5Button());
	        		g2d.draw(menuData.getHeli6Button());
	        		g2d.draw(menuData.getHeli7Button());
	        		g2d.draw(menuData.getHeli8Button());
	        		
	        		drawHelicopterSelect(g2d,menuData.getSelectHeli());
	        		
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
    private void restartGame(int helicopterSelect)
    {
        // We set gameTime to zero and lastTime to current time for later calculations.
        gameTime = 0;
        lastTime = System.nanoTime();
        
        game.RestartGame(helicopterSelect);
        
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
                /*if(e.getKeyCode() == KeyEvent.VK_ENTER)
                    restartGame();*/
                
                if(e.getKeyCode() == KeyEvent.VK_DOWN)
        			{
                		Sound reload = new Sound("load.mp3", false);
                		reload.start();
        				menuData.setSelect(menuData.getSelect() + 1);
        				if(menuData.getSelect() > 2)
        					menuData.setSelect(menuData.getSelect() - 2);
	        		}
                else if(e.getKeyCode() == KeyEvent.VK_UP)
	        		{
                		Sound reload = new Sound("load.mp3", false);
                		reload.start();
	        			menuData.setSelect(menuData.getSelect() - 1);
	        			if(menuData.getSelect() < 1)
	        				menuData.setSelect(menuData.getSelect() + 2);
	        		}
	            	
                if(menuData.getSelect() == 1 && e.getKeyCode() == KeyEvent.VK_ENTER)
	            		{
//                			restartGame();
                			gameState = GameState.RESELECT;
	            		}
                else	if(menuData.getSelect() == 2 && e.getKeyCode() == KeyEvent.VK_ENTER)
	            		System.exit(0);
	                
            break;
            case MAIN_MENU:
            		
            		if(e.getKeyCode() == KeyEvent.VK_DOWN)
            		{
            			Sound reload = new Sound("load.mp3", false);
                		reload.start();
            			menuData.setSelect(menuData.getSelect() + 1);
            			if(menuData.getSelect() > 3)
            				menuData.setSelect(menuData.getSelect() - 3);
            		}
            		if(e.getKeyCode() == KeyEvent.VK_UP)
            		{
            			Sound reload = new Sound("load.mp3", false);
                		reload.start();
            			menuData.setSelect(menuData.getSelect() - 1);
            			if(menuData.getSelect() < 1)
            				menuData.setSelect(menuData.getSelect() + 3);
            		}
                	if(menuData.getSelect() == 1 && e.getKeyCode() == KeyEvent.VK_ENTER)
                		gameState = GameState.SELECT;
                	if(menuData.getSelect() == 2 && e.getKeyCode() == KeyEvent.VK_ENTER)
                			gameState = GameState.HELP;
                	if(menuData.getSelect() == 3 && e.getKeyCode() == KeyEvent.VK_ENTER)
                			gameState = GameState.QUIT;
            break;
            case SELECT:
            		if(e.getKeyCode() == KeyEvent.VK_RIGHT)
            		{
            			Sound reload = new Sound("load.mp3", false);
                		reload.start();
            			menuData.setSelectHeli(menuData.getSelectHeli() + 1);
            			if(menuData.getSelectHeli() > 8)
            				menuData.setSelectHeli(menuData.getSelectHeli() - 8);
            		}
            		if(e.getKeyCode() == KeyEvent.VK_LEFT)
            		{
            			Sound reload = new Sound("load.mp3", false);
                		reload.start();
            			menuData.setSelectHeli(menuData.getSelectHeli() - 1);
            			if(menuData.getSelectHeli() < 1)
            				menuData.setSelectHeli(menuData.getSelectHeli() + 8);
            		}
            		if(e.getKeyCode() == KeyEvent.VK_DOWN)
            		{
            			Sound reload = new Sound("load.mp3", false);
                		reload.start();
            			menuData.setSelectHeli(menuData.getSelectHeli() + 4);
            			if(menuData.getSelectHeli() > 8)
            				menuData.setSelectHeli(menuData.getSelectHeli() - 8);
            		}
            		if(e.getKeyCode() == KeyEvent.VK_UP)
            		{
            			Sound reload = new Sound("load.mp3", false);
                		reload.start();
            			menuData.setSelectHeli(menuData.getSelectHeli() - 4);
            			if(menuData.getSelectHeli() < 1)
            				menuData.setSelectHeli(menuData.getSelectHeli() + 8);
            		}
            		
            		Sound reload = new Sound("reload.mp3", false);
            		if(menuData.getSelectHeli() == 1 && e.getKeyCode() == KeyEvent.VK_ENTER)
            		{
            			reload.start();
            			newGame(menuData.getSelectHeli());
            		}
            		else if(menuData.getSelectHeli() == 2 && e.getKeyCode() == KeyEvent.VK_ENTER)
                	{
            			reload.start();
            			newGame(menuData.getSelectHeli());
                	}
            		else if(menuData.getSelectHeli() == 3 && e.getKeyCode() == KeyEvent.VK_ENTER)
            		{
            			reload.start();
            			newGame(menuData.getSelectHeli());
                	}
            		else if(menuData.getSelectHeli() == 4 && e.getKeyCode() == KeyEvent.VK_ENTER)
            		{
            			reload.start();
            			newGame(menuData.getSelectHeli());
                	}
            		else if(menuData.getSelectHeli() == 5 && e.getKeyCode() == KeyEvent.VK_ENTER)
            		{
            			reload.start();
            			newGame(menuData.getSelectHeli());
                	}
            		else if(menuData.getSelectHeli() == 6 && e.getKeyCode() == KeyEvent.VK_ENTER)
            		{
            			reload.start();
            			newGame(menuData.getSelectHeli());
                	}
            		else if(menuData.getSelectHeli() == 7 && e.getKeyCode() == KeyEvent.VK_ENTER)
            		{
            			reload.start();
            			newGame(menuData.getSelectHeli());
                	}
            		else if(menuData.getSelectHeli() == 8 && e.getKeyCode() == KeyEvent.VK_ENTER)
            		{
            			reload.start();
            			newGame(menuData.getSelectHeli());
                	}
                	
            		if(e.getKeyCode() == KeyEvent.VK_BACK_SPACE)
            			gameState = GameState.MAIN_MENU;
            break;
            case RESELECT:
	        		if(e.getKeyCode() == KeyEvent.VK_RIGHT)
	        		{
	        			Sound reload1 = new Sound("load.mp3", false);
	            		reload1.start();
	        			menuData.setSelectHeli(menuData.getSelectHeli() + 1);
	        			if(menuData.getSelectHeli() > 8)
	        				menuData.setSelectHeli(menuData.getSelectHeli() - 8);
	        		}
	        		if(e.getKeyCode() == KeyEvent.VK_LEFT)
	        		{
	        			Sound reload1 = new Sound("load.mp3", false);
	            		reload1.start();
	        			menuData.setSelectHeli(menuData.getSelectHeli() - 1);
	        			if(menuData.getSelectHeli() < 1)
	        				menuData.setSelectHeli(menuData.getSelectHeli() + 8);
	        		}
	        		if(e.getKeyCode() == KeyEvent.VK_DOWN)
	        		{
	        			Sound reload1 = new Sound("load.mp3", false);
	            		reload1.start();
	        			menuData.setSelectHeli(menuData.getSelectHeli() + 4);
	        			if(menuData.getSelectHeli() > 8)
	        				menuData.setSelectHeli(menuData.getSelectHeli() - 8);
	        		}
	        		if(e.getKeyCode() == KeyEvent.VK_UP)
	        		{
	        			Sound reload1 = new Sound("load.mp3", false);
	            		reload1.start();
	        			menuData.setSelectHeli(menuData.getSelectHeli() - 4);
	        			if(menuData.getSelectHeli() < 1)
	        				menuData.setSelectHeli(menuData.getSelectHeli() + 8);
	        		}
	        		
	        		Sound reload1 = new Sound("reload.mp3", false);
	        		if(menuData.getSelectHeli() == 1 && e.getKeyCode() == KeyEvent.VK_ENTER)
	        		{
	        			reload1.start();
	        			restartGame(menuData.getSelectHeli());
	        		}
	        		else if(menuData.getSelectHeli() == 2 && e.getKeyCode() == KeyEvent.VK_ENTER)
	            	{
	        			reload1.start();
	        			restartGame(menuData.getSelectHeli());
	            	}
	        		else if(menuData.getSelectHeli() == 3 && e.getKeyCode() == KeyEvent.VK_ENTER)
	        		{
	        			reload1.start();
	        			restartGame(menuData.getSelectHeli());
	            	}
	        		else if(menuData.getSelectHeli() == 4 && e.getKeyCode() == KeyEvent.VK_ENTER)
	        		{
	        			reload1.start();
	        			restartGame(menuData.getSelectHeli());
	            	}
	        		else if(menuData.getSelectHeli() == 5 && e.getKeyCode() == KeyEvent.VK_ENTER)
	        		{
	        			reload1.start();
	        			restartGame(menuData.getSelectHeli());
	            	}
	        		else if(menuData.getSelectHeli() == 6 && e.getKeyCode() == KeyEvent.VK_ENTER)
	        		{
	        			reload1.start();
	        			restartGame(menuData.getSelectHeli());
	            	}
	        		else if(menuData.getSelectHeli() == 7 && e.getKeyCode() == KeyEvent.VK_ENTER)
	        		{
	        			reload1.start();
	        			restartGame(menuData.getSelectHeli());
	            	}
	        		else if(menuData.getSelectHeli() == 8 && e.getKeyCode() == KeyEvent.VK_ENTER)
	        		{
	        			reload1.start();
	        			restartGame(menuData.getSelectHeli());
	            	}
	            	
	        		if(e.getKeyCode() == KeyEvent.VK_BACK_SPACE)
	        			gameState = GameState.GAMEOVER;
            	break;
            case HELP:
            		if(e.getKeyCode() == KeyEvent.VK_BACK_SPACE)
            			gameState = GameState.MAIN_MENU;
            	break;
            case QUIT:
            		if(e.getKeyCode() == KeyEvent.VK_BACK_SPACE)
            			gameState = GameState.MAIN_MENU;
            		if(e.getKeyCode() == KeyEvent.VK_ENTER)
            			System.exit(0);
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
    
    
    private void drawMenuBackground(Graphics2D g2d){
        g2d.drawImage(menuData.getSkyColorImg(),    0, 0, Framework.frameWidth, Framework.frameHeight, null);
        g2d.drawImage(menuData.getCloudLayer1Img(), 0, 0, Framework.frameWidth, Framework.frameHeight, null);
        g2d.drawImage(menuData.getCloudLayer2Img(), 0, 0, Framework.frameWidth, Framework.frameHeight, null);
        g2d.drawImage(menuData.getMenuBorderImg(),  0, 0, Framework.frameWidth, Framework.frameHeight, null);
        g2d.setColor(Color.white);
        g2d.drawString("", 7, frameHeight - 5);
    }
    
    private void drawHelicopter(Graphics2D g2d)
    {
    		g2d.drawImage(menuData.getHeli1Img(),frameWidth / 6, frameHeight / 3, 150, 48, null);
    		g2d.drawImage(menuData.getHeli2Img(),frameWidth / 6 +200, frameHeight / 3, 150, 42, null);
    		g2d.drawImage(menuData.getHeli3Img(),frameWidth / 6 +400, frameHeight / 3, 150, 52, null);
    		g2d.drawImage(menuData.getHeli4Img(),frameWidth / 6 +600, frameHeight / 3, 150, 75, null);
    		g2d.drawImage(menuData.getHeli5Img(),frameWidth / 6, frameHeight / 3 +150, 150, 68, null);
    		g2d.drawImage(menuData.getHeli6Img(),frameWidth / 6 +200, frameHeight / 3 +150, 150, 33, null);
    		g2d.drawImage(menuData.getHeli7Img(),frameWidth / 6 +400, frameHeight / 3 +150, 132, 75, null);
    		g2d.drawImage(menuData.getHeli8Img(),frameWidth / 6 +600, frameHeight / 3 +150, 150, 31, null);
    }
    
    private void drawMenuSelect(Graphics2D g2d, int menu)
    {
		g2d.setColor(Color.darkGray);
    		switch(menu)
    		{
    		case 1:
    			g2d.fillRect(menuData.getPlayButton().x, menuData.getPlayButton().y, menuData.getPlayButton().width, menuData.getPlayButton().height);
    			g2d.setFont(menuData.getButtonFont());
    			g2d.setColor(Color.white);
    			g2d.drawString("PLAY", menuData.getPlayButton().x+125, menuData.getPlayButton().y+75);
    			break;
    		case 2:
    			g2d.fillRect(menuData.getHelpButton().x, menuData.getHelpButton().y, menuData.getHelpButton().width, menuData.getHelpButton().height);
    			g2d.setFont(menuData.getButtonFont());
    			g2d.setColor(Color.white);
    			g2d.drawString("HELP", menuData.getHelpButton().x+125, menuData.getHelpButton().y+75);
    			break;
    		case 3:
    			g2d.fillRect(menuData.getQuitButton().x, menuData.getQuitButton().y, menuData.getQuitButton().width, menuData.getQuitButton().height);
    			g2d.setFont(menuData.getButtonFont());
    			g2d.setColor(Color.white);
    			g2d.drawString("QUIT", menuData.getQuitButton().x+125, menuData.getQuitButton().y+75);
    			break;
    		}
    		
    }
    
    private void drawHelicopterSelect(Graphics2D g2d, int heli)
    {
    	g2d.setColor(Color.darkGray);
    	switch(heli)
		{
		case 1:
			g2d.fillRect(menuData.getHeli1Button().x, menuData.getHeli1Button().y, menuData.getHeli1Button().width, menuData.getHeli1Button().height);
			g2d.setFont(menuData.getButtonFont());
			g2d.setColor(Color.white);
			break;
		case 2:
			g2d.fillRect(menuData.getHeli2Button().x, menuData.getHeli2Button().y, menuData.getHeli2Button().width, menuData.getHeli2Button().height);
			g2d.setFont(menuData.getButtonFont());
			g2d.setColor(Color.white);
			break;
		case 3:
			g2d.fillRect(menuData.getHeli3Button().x, menuData.getHeli3Button().y, menuData.getHeli3Button().width, menuData.getHeli3Button().height);
			g2d.setFont(menuData.getButtonFont());
			g2d.setColor(Color.white);
			break;
		case 4:
			g2d.fillRect(menuData.getHeli4Button().x, menuData.getHeli4Button().y, menuData.getHeli4Button().width, menuData.getHeli4Button().height);
			g2d.setFont(menuData.getButtonFont());
			g2d.setColor(Color.white);
			break;
		case 5:
			g2d.fillRect(menuData.getHeli5Button().x, menuData.getHeli5Button().y, menuData.getHeli5Button().width, menuData.getHeli5Button().height);
			g2d.setFont(menuData.getButtonFont());
			g2d.setColor(Color.white);
			break;
		case 6:
			g2d.fillRect(menuData.getHeli6Button().x, menuData.getHeli6Button().y, menuData.getHeli6Button().width, menuData.getHeli6Button().height);
			g2d.setFont(menuData.getButtonFont());
			g2d.setColor(Color.white);
			break;
		case 7:
			g2d.fillRect(menuData.getHeli7Button().x, menuData.getHeli7Button().y, menuData.getHeli7Button().width, menuData.getHeli7Button().height);
			g2d.setFont(menuData.getButtonFont());
			g2d.setColor(Color.white);
			break;
		case 8:
			g2d.fillRect(menuData.getHeli8Button().x, menuData.getHeli8Button().y, menuData.getHeli8Button().width, menuData.getHeli8Button().height);
			g2d.setFont(menuData.getButtonFont());
			g2d.setColor(Color.white);
			break;
		
		}
    }
    
    private void drawRestartSelect(Graphics2D g2d, int menu)
    {
		g2d.setColor(Color.darkGray);
    		switch(menu)
    		{
    		case 1:
    			g2d.fillRect(menuData.getRestartButton().x, menuData.getRestartButton().y, menuData.getRestartButton().width, menuData.getRestartButton().height);
    			g2d.setFont(menuData.getButtonFont());
    			g2d.setColor(Color.white);
    			g2d.drawString("RESTART", menuData.getRestartButton().x+75, menuData.getRestartButton().y+75);
    			break;
    		case 2:
    			g2d.fillRect(menuData.getQuitButton().x, menuData.getQuitButton().y, menuData.getQuitButton().width, menuData.getQuitButton().height);
    			g2d.setFont(menuData.getButtonFont());
    			g2d.setColor(Color.white);
    			g2d.drawString("QUIT", menuData.getQuitButton().x+125, menuData.getQuitButton().y+75);
    			break;
    		}
    		
    }
}
