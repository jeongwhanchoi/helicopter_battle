package helicopterbattle;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class LeaderBoard{
	private static LeaderBoard lBoard;
	private String filePath;
	private String highScores;
	private ArrayList<Integer> topScores;
	
	public LeaderBoard()
	{
		filePath = new File("leaderBoard.txt").getAbsolutePath();
		
		topScores = new ArrayList<Integer>();
	}
	
	public static LeaderBoard getInstance()
	{
		if(lBoard == null)
		{
			lBoard = new LeaderBoard();
		}
		return lBoard;
	}
	
	public void addScore(int score)
	{
		for(int i =0; i <topScores.size(); i++)
		{
			if(score >= topScores.get(i))
			{
				topScores.add(i, score);
				topScores.remove(topScores.size() - 1);
			}
		}
	}
	
	public void loadScores()
	{
		try 
		{
			File f = new File(filePath, highScores);
			if(!f.isFile())
			{
				createSaveData();
			}
			
			BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(f)));
			
			topScores.clear();
			
			String[] scores = reader.readLine().split("-");
			
			for(int i =0 ; i < scores.length; i++)
			{
				topScores.add(Integer.parseInt(scores[i]));
			}
			reader.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
	public void saveScores()
	{
		FileWriter output = null;
		
		try
		{
			File f = new File(filePath, highScores);
			output = new FileWriter(f);
			BufferedWriter writer = new BufferedWriter(output);
			
			writer.write(topScores.get(0) + "-" + topScores.get(1) + "-" + topScores.get(2) + "-" + topScores.get(3));
			writer.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	private void createSaveData()
	{
		FileWriter output = null;
		
		try
		{
			File f = new File(filePath, highScores);
			output = new FileWriter(f);
			BufferedWriter writer = new BufferedWriter(output);
			
			writer.write("0-0-0-0-0");
			writer.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public int getHighScore()
	{
		return topScores.get(0);
	}

	public ArrayList<Integer> getTopScores() 
	{
		return topScores;
	}
}
