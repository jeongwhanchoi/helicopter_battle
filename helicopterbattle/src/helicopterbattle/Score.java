//package helicopterbattle;
//
//import java.io.BufferedReader;
//import java.io.BufferedWriter;
//import java.io.File;
//import java.io.FileReader;
//import java.io.FileWriter;
//import java.io.IOException;
//
//import javax.swing.JOptionPane;
//
//public class Score {
//	
//	private static String highScore;
//    
//    public static String getHighScore()
//    {
//    		FileReader readFile = null;
//    		BufferedReader  reader = null;
//    		try
//    		{
//    			readFile = new FileReader("highscore.dat");
//    			reader = new BufferedReader(readFile);
//    			return reader.readLine();
//    		}
//    		catch(Exception e)
//    		{
//    			return "Nobody:0";
//    		}
//    		finally
//    		{
//    			try {
//    				if(reader != null)
//    				reader.close();
//				} catch (Exception e) {
//					// TODO: handle exception
//					e.printStackTrace();
//				}
//    		}
//    }
//    
//    public static void checkScore()
//    {
//    		if(Game.getHighScore().equals(""))
//    			return;
//    		//format Choi/:/100
//    		if(Game.getScore() > Integer.parseInt((Game.getHighScore().split(":")[1])))
//    		{
//    			//user has set a new record
//    			String name = JOptionPane.showInputDialog("You set a new high score. What is your name?");
//    			highScore = name + ":" + Game.getScore();
//    			
//    			File scoreFile = new File("highscore.dat");
//    			if(!scoreFile.exists())
//    			{
//    				try {
//						scoreFile.createNewFile();
//					} catch (IOException e) {
//						// TODO: handle exception
//						e.printStackTrace();
//					}
//    			}
//    		FileWriter writeFile = null;
//    		BufferedWriter writer = null;
//    		try
//    		{
//    			writeFile = new FileWriter(scoreFile);
//    			writer = new BufferedWriter(writeFile);
//    			writer.write(highScore);
//    		}
//    		catch(Exception e)
//    		{
//    			//errors
//    		}
//    		finally
//    		{
//    			try
//    			{
//    				if(writer != null)
//    					writer.close();
//    			}
//    			catch(Exception e) {}
//    		}
//    }
//   }
//}
