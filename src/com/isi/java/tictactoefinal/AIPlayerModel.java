package com.isi.java.tictactoefinal;

import java.util.Random;

public class AIPlayerModel extends PlayerModel
{	
	private Random random = new Random(System.currentTimeMillis());
	
	@Override
	public void startTurn()
	{
		new Thread(() -> playTurn()).start();
	}
	
	private void playTurn()
	{
		decideMove();
		
		try
		{
			Thread.sleep(2000);
			gameChecker.makeMove(gameChecker.getXAI(), gameChecker.getYAI(), true);
		}
		catch (InvalidMoveException | InterruptedException e)
		{
			e.printStackTrace();
		}
	}
	
	private void decideMove()
	{
		Square[][] grid = gameChecker.getGrid();
		
		// first loop to try to win
		for (int i = 0; i < grid.length; i++) 
		{
			for(int j = 0; j < grid[i].length; j++)
			{
				if(grid[i][j]==Square.EMPTY)
				{
					gameChecker.setOCoordinates(i, j);
					gameChecker.setAI(i, j);
					grid[i][j] = Square.O;
					if (gameChecker.checkForWinner(true, i, j) == 'O') 
					{
						grid[i][j] = Square.EMPTY;
						return;
					}
					else
					{
						grid[i][j] = Square.EMPTY;
						gameChecker.setAI(10, 10);
						gameChecker.resetOCoordinates(i, j);
					}
				}
			}
		}

		// second loop to try to block if it cant win
		for (int i = 0; i < grid.length; i++) 
		{
			for(int j = 0; j < grid[i].length; j++)
			{
				if(grid[i][j]==Square.EMPTY)
				{
					gameChecker.setXCoordinates(i, j);
					grid[i][j] = Square.X;
					if (gameChecker.checkForWinner(false, i, j) == 'X') 
					{
						//						grid[i][j] = Square.EMPTY;
						gameChecker.setOCoordinates(i, j);
						gameChecker.setAI(i, j);
						grid[i][j] = Square.EMPTY;
						return;
					}
					else
					{
						grid[i][j] = Square.EMPTY;
						gameChecker.setAI(10, 10);
						gameChecker.resetXCoordinates(i, j);
					}
				}
			}
		}

		// third loop to try to play randomly
		while (true)
		{
			int x = random.nextInt(grid.length);
			int y = random.nextInt(grid.length);
			if (grid[x][y] == Square.EMPTY) 
			{
				gameChecker.setOCoordinates(x, y);
				gameChecker.setAI(x, y);
				break;
			}
		}
	}

}
