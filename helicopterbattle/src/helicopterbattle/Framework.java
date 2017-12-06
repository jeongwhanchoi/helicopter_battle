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
    
    public FrameworkData data = new FrameworkData();


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
        data.setFont(new Font("monospaced", Font.BOLD, 28));
        data.setButtonFont(new Font("arial", Font.BOLD, 60));
        data.setPlayButton(new Rectangle(frameWidth / 2 - 200, frameHeight / 2 - 120, 400, 100));
        data.setHelpButton(new Rectangle(frameWidth / 2 - 200, frameHeight / 2, 400, 100));
        data.setQuitButton(new Rectangle(frameWidth / 2 - 200, frameHeight / 2 + 120, 400, 100));
        data.setBackButton(new Rectangle(frameWidth / 2 - 200, frameHeight / 2 + 120, 400, 100));
        
        data.setHeli1Button(new Rectangle(frameWidth / 6, frameHeight / 3, 150, 75));
        data.setHeli2Button(new Rectangle(frameWidth / 6 +200, frameHeight / 3, 150, 75));
        data.setHeli3Button(new Rectangle(frameWidth / 6 +400, frameHeight / 3, 150, 75));
        data.setHeli4Button(new Rectangle(frameWidth / 6 +600, frameHeight / 3, 150, 75));
        data.setHeli5Button(new Rectangle(frameWidth / 6, frameHeight / 3 +150, 150, 75));
        data.setHeli6Button(new Rectangle(frameWidth / 6 +200, frameHeight / 3 +150, 150, 75));
        data.setHeli7Button(new Rectangle(frameWidth / 6 +400, frameHeight / 3 +150, 150, 75));
        data.setHeli8Button(new Rectangle(frameWidth / 6 +600, frameHeight / 3 +150, 150, 75));
        
        data.setRestartButton(new Rectangle(frameWidth / 2 - 200, frameHeight / 2, 400, 100));
        
        data.setSelect(1);
        data.setSelectHeli(1);
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
            data.setMenuBorderImg(ImageIO.read(menuBorderImgUrl));
            
            URL skyColorImgUrl = this.getClass().getResource("/helicopterbattle/resources/images/sky_color.jpg");
            data.setSkyColorImg(ImageIO.read(skyColorImgUrl));
            
            URL gameTitleImgUrl = this.getClass().getResource("/helicopterbattle/resources/images/helicopter_battle_title.png");
            data.setGameTitleImg(ImageIO.read(gameTitleImgUrl));
            
            URL cloudLayer1ImgUrl = this.getClass().getResource("/helicopterbattle/resources/images/cloud_layer_1.png");
            data.setCloudLayer1Img(ImageIO.read(cloudLayer1ImgUrl));
            URL cloudLayer2ImgUrl = this.getClass().getResource("/helicopterbattle/resources/images/cloud_layer_2.png");
            data.setCloudLayer2Img(ImageIO.read(cloudLayer2ImgUrl));
            
            URL helpImgUrl = this.getClass().getResource("/helicopterbattle/resources/images/help_01.png");
            data.setHelpImg(ImageIO.read(helpImgUrl)); 
            
            URL heli1ImgUrl = this.getClass().getResource("/helicopterbattle/resources/images/1_helicopter_body_01.png");
            data.setHeli1Img(ImageIO.read(heli1ImgUrl)); 
            URL heli2ImgUrl = this.getClass().getResource("/helicopterbattle/resources/images/1_helicopter_body_02.png");
            data.setHeli2Img(ImageIO.read(heli2ImgUrl)); 
            URL heli3ImgUrl = this.getClass().getResource("/helicopterbattle/resources/images/1_helicopter_body_03.png");
            data.setHeli3Img(ImageIO.read(heli3ImgUrl)); 
            URL heli4ImgUrl = this.getClass().getResource("/helicopterbattle/resources/images/1_helicopter_body_04.png");
            data.setHeli4Img(ImageIO.read(heli4ImgUrl)); 
            URL heli5ImgUrl = this.getClass().getResource("/helicopterbattle/resources/images/1_helicopter_body_05.png");
            data.setHeli5Img(ImageIO.read(heli5ImgUrl)); 
            URL heli6ImgUrl = this.getClass().getResource("/helicopterbattle/resources/images/1_helicopter_body_06.png");
            data.setHeli6Img(ImageIO.read(heli6ImgUrl)); 
            URL heli7ImgUrl = this.getClass().getResource("/helicopterbattle/resources/images/1_helicopter_body_07.png");
            data.setHeli7Img(ImageIO.read(heli7ImgUrl)); 
            URL heli8ImgUrl = this.getClass().getResource("/helicopterbattle/resources/images/1_helicopter_body_00.png");
            data.setHeli8Img(ImageIO.read(heli8ImgUrl)); 
            
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
                    
                    data.getGame().UpdateGame(gameTime, mousePosition());
                    
                    lastTime = System.nanoTime();
                break;
                case GAMEOVER:
                		Score.checkScore();
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
                data.getGame().Draw(g2d, mousePosition(), gameTime);
            break;
            case GAMEOVER:
            		
            		data.drawGameOver(g2d);
            		data.getGame().DrawStatistic(g2d, gameTime);
            		data.drawRestartSelect(g2d, data.getSelect());
            break;
            case MAIN_MENU:
            		data.drawMenu(g2d);
            		data.drawMenuSelect(g2d, data.getSelect());
                
            break;
            case HELP:
            		data.drawHelp(g2d);
                
            	break;
            case QUIT:
            		data.drawQuit(g2d);
            break;
            case SELECT:
            		data.drawSelect(g2d);
            		data.drawHelicopterSelect(g2d, data.getSelectHeli());
            		data.drawHelicopter(g2d);
            	break;
            case RESELECT:
            		data.drawSelect(g2d);
            		data.drawHelicopterSelect(g2d,data.getSelectHeli());
            		data.drawHelicopter(g2d);
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
    private void newGame(int helicopterSelect)
    {
        // We set gameTime to zero and lastTime to current time for later calculations.
        gameTime = 0;
        lastTime = System.nanoTime();
        
        data.setGame(new Game(helicopterSelect));
    }
    
    /**
     *  Restart game - reset game time and call RestartGame() method of game object so that reset some variables.
     */
    private void restartGame(int helicopterSelect)
    {
        // We set gameTime to zero and lastTime to current time for later calculations.
        gameTime = 0;
        lastTime = System.nanoTime();
        
        data.getGame().RestartGame(helicopterSelect);
        
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
        				data.setSelect(data.getSelect() + 1);
        				if(data.getSelect() > 2)
        					data.setSelect(data.getSelect() - 2);
	        		}
                else if(e.getKeyCode() == KeyEvent.VK_UP)
	        		{
                		Sound reload = new Sound("load.mp3", false);
                		reload.start();
	        			data.setSelect(data.getSelect() - 1);
	        			if(data.getSelect() < 1)
	        				data.setSelect(data.getSelect() + 2);
	        		}
	            	
                if(data.getSelect() == 1 && e.getKeyCode() == KeyEvent.VK_ENTER)
	            		{
                			gameState = GameState.RESELECT;
	            		}
                else	if(data.getSelect() == 2 && e.getKeyCode() == KeyEvent.VK_ENTER)
	            		System.exit(0);
	                
            break;
            case MAIN_MENU:
            		
            		if(e.getKeyCode() == KeyEvent.VK_DOWN)
            		{
            			Sound reload = new Sound("load.mp3", false);
                		reload.start();
            			data.setSelect(data.getSelect() + 1);
            			if(data.getSelect() > 3)
            				data.setSelect(data.getSelect() - 3);
            		}
            		if(e.getKeyCode() == KeyEvent.VK_UP)
            		{
            			Sound reload = new Sound("load.mp3", false);
                		reload.start();
            			data.setSelect(data.getSelect() - 1);
            			if(data.getSelect() < 1)
            				data.setSelect(data.getSelect() + 3);
            		}
                	if(data.getSelect() == 1 && e.getKeyCode() == KeyEvent.VK_ENTER)
                		gameState = GameState.SELECT;
                	if(data.getSelect() == 2 && e.getKeyCode() == KeyEvent.VK_ENTER)
                			gameState = GameState.HELP;
                	if(data.getSelect() == 3 && e.getKeyCode() == KeyEvent.VK_ENTER)
                			gameState = GameState.QUIT;
            break;
            case SELECT:
            		if(e.getKeyCode() == KeyEvent.VK_RIGHT)
            		{
            			Sound reload = new Sound("load.mp3", false);
                		reload.start();
            			data.setSelectHeli(data.getSelectHeli() + 1);
            			if(data.getSelectHeli() > 8)
            				data.setSelectHeli(data.getSelectHeli() - 8);
            		}
            		if(e.getKeyCode() == KeyEvent.VK_LEFT)
            		{
            			Sound reload = new Sound("load.mp3", false);
                		reload.start();
            			data.setSelectHeli(data.getSelectHeli() - 1);
            			if(data.getSelectHeli() < 1)
            				data.setSelectHeli(data.getSelectHeli() + 8);
            		}
            		if(e.getKeyCode() == KeyEvent.VK_DOWN)
            		{
            			Sound reload = new Sound("load.mp3", false);
                		reload.start();
            			data.setSelectHeli(data.getSelectHeli() + 4);
            			if(data.getSelectHeli() > 8)
            				data.setSelectHeli(data.getSelectHeli() - 8);
            		}
            		if(e.getKeyCode() == KeyEvent.VK_UP)
            		{
            			Sound reload = new Sound("load.mp3", false);
                		reload.start();
            			data.setSelectHeli(data.getSelectHeli() - 4);
            			if(data.getSelectHeli() < 1)
            				data.setSelectHeli(data.getSelectHeli() + 8);
            		}
            		
            		enter(e);          
            		
            		if(e.getKeyCode() == KeyEvent.VK_BACK_SPACE)
            			gameState = GameState.MAIN_MENU;
            break;
            case RESELECT:
	        		if(e.getKeyCode() == KeyEvent.VK_RIGHT)
	        		{
	        			Sound reload1 = new Sound("load.mp3", false);
	            		reload1.start();
	        			data.setSelectHeli(data.getSelectHeli() + 1);
	        			if(data.getSelectHeli() > 8)
	        				data.setSelectHeli(data.getSelectHeli() - 8);
	        		}
	        		if(e.getKeyCode() == KeyEvent.VK_LEFT)
	        		{
	        			Sound reload1 = new Sound("load.mp3", false);
	            		reload1.start();
	        			data.setSelectHeli(data.getSelectHeli() - 1);
	        			if(data.getSelectHeli() < 1)
	        				data.setSelectHeli(data.getSelectHeli() + 8);
	        		}
	        		if(e.getKeyCode() == KeyEvent.VK_DOWN)
	        		{
	        			Sound reload1 = new Sound("load.mp3", false);
	            		reload1.start();
	        			data.setSelectHeli(data.getSelectHeli() + 4);
	        			if(data.getSelectHeli() > 8)
	        				data.setSelectHeli(data.getSelectHeli() - 8);
	        		}
	        		if(e.getKeyCode() == KeyEvent.VK_UP)
	        		{
	        			Sound reload1 = new Sound("load.mp3", false);
	            		reload1.start();
	        			data.setSelectHeli(data.getSelectHeli() - 4);
	        			if(data.getSelectHeli() < 1)
	        				data.setSelectHeli(data.getSelectHeli() + 8);
	        		}
	        		
	        		reEnter(e);
	            	
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
    
    public void enter(KeyEvent e) {
	    	Sound reload1 = new Sound("reload.mp3", false);
	    	if(e.getKeyCode() == KeyEvent.VK_ENTER) {
	    		reload1.start();
	    		newGame(data.getSelectHeli());
	    	}
    }
    public void reEnter(KeyEvent e) {
	    	Sound reload1 = new Sound("reload.mp3", false);
	    	if(e.getKeyCode() == KeyEvent.VK_ENTER) {
	    		reload1.start();
	    		restartGame(data.getSelectHeli());
	    	}
    }

}
