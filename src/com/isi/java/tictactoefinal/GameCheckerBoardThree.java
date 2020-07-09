package com.isi.java.tictactoefinal;

import java.util.Random;

/**
 * this class contains all the data and logic required for the game and it implements ITicTacToeModel interface
 * @author Bharat Gupta, Hardeep Singh, Kirandeep Singh
 */
public class GameCheckerBoardThree implements ITicTacToeModel
{
	/*
	// [0] = x of X, [1] = y of X , [2] = x of O and [3] = y of O
//	private int[] count0 =new int[4]; // counts number of 0
//	private int[] count1 =new int[4]; // counts number of 1
//	private int[] count2 =new int[4]; // counts number of 2
	 */
	private ITicTacToeView view;
	
	private PlayerModel playerX;
	private PlayerModel playerO;
	
	private Square[][] grid = new Square[3][3];
	//	private PlayerModel playerX;
	//	private AIPlayerModel playerO;

	Random random = new Random(System.currentTimeMillis());

	private int[][] count = new int[3][4]; 
	/*
	 * comment to understand count[][] array
	 * [0][0] -> when x coordinate of X player = 0
	 * [0][1] -> when y coordinate of X player = 0
	 * [0][2] -> when x coordinate of O player = 0
	 * [0][3] -> when y coordinate of O player = 0

	 * [1][0] -> when x coordinate of X player = 1
	 * [1][1] -> when y coordinate of X player = 1
	 * [1][2] -> when x coordinate of O player = 1
	 * [1][3] -> when y coordinate of O player = 1

	 * [2][0] -> when x coordinate of X player = 2
	 * [2][1] -> when y coordinate of X player = 2
	 * [2][2] -> when x coordinate of O player = 2
	 * [2][3] -> when y coordinate of O player = 2
	 */
	private int xDiagonal1 = 0; // for diagonal where x == y (player X)
	private int oDiagonal1 = 0; // for diagonal where x == y (player O)
	private int xDiagonal2 = 0; // for diagonal where x + y = 2 (player X)
	private int oDiagonal2 = 0; // for diagonal where x + y = 2 (player O)
	private int countTurnForX = 0; // to count turns played by X
	private int countTurnForO = 0; // to count turns played by O
	private int scoreO = 0; // contains Score of player O
	private int scoreX = 0; // contains Score of player X
	private boolean playerTurn;  // true for player O turn and false for player X turn

	private int xAI;
	private int yAI;

	public void setView(ITicTacToeView view)
	{
		this.view = view;
	}
	
	@Override
	public void setAI(int x, int y)
	{
		this.xAI = x;
		this.yAI = y;
	}

	public int getXAI() {return xAI;}
	public int getYAI() {return yAI;}

	public GameCheckerBoardThree(PlayerModel playerX, PlayerModel playerO)
	{
		this.playerX = playerX;
		this.playerO = playerO;
		scoreO = 0;
		scoreX = 0;
		resetGame();
	}

	public Square[][] getGrid() { return grid; }
	
	public int getScore(char player)
	{
		if(player == 'O')
			return scoreO;
		else 
			return scoreX;
	}

	private void updateCount(int x, int y, char XO)
	{
		switch (x) 
		{
		case 0:	 
			if (XO == 'X') 
				count[0][0]++; // when x of X = 0
			else if (XO == 'O')
				count[0][2]++; // when x of O = 0
			//				(XO == 'X') ?countxX0++:countxO0++; --> did not work
			break;
		case 1: 
			if (XO == 'X') 
				count[1][0]++; // when x of X = 1
			else if (XO == 'O')
				count[1][2]++; // when x of O = 1
			break;
		case 2:
			if (XO == 'X') 
				count[2][0]++; // when x of X = 2
			else if (XO == 'O')
				count[2][2]++; // when x of O = 2
			break;
		}

		switch (y) 
		{
		case 0:	 
			if (XO == 'X') 
				count[0][1]++; // when y of X = 0
			else if (XO == 'O')
				count[0][3]++; // when y of O = 0
			break;
		case 1: 
			if (XO == 'X') 
				count[1][1]++; // when y of X = 1
			else if (XO == 'O')
				count[1][3]++; // when y of O = 1
			break;
		case 2:
			if (XO == 'X') 
				count[2][1]++; // when y of X = 2
			else if (XO == 'O')
				count[2][3]++; // when y of O = 2
			break;
		}
	}

	private void undoCount(int x, int y, char XO)
	{
		switch (x) 
		{
		case 0:	 
			if (XO == 'X') 
				count[0][0]--; // when x of X = 0
			else if (XO == 'O')
				count[0][2]--; // when x of O = 0
			
			//				(XO == 'X') ?countxX0++:countxO0++; --> did not work
			break;
		case 1: 
			if (XO == 'X') 
				count[1][0]--; // when x of X = 1
			else if (XO == 'O')
				count[1][2]--; // when x of O = 1
			break;
		case 2:
			if (XO == 'X') 
				count[2][0]--; // when x of X = 2
			else if (XO == 'O')
				count[2][2]--; // when x of O = 2
			break;
		}

		switch (y) 
		{
		case 0:	 
			if (XO == 'X') 
				count[0][1]--; // when y of X = 0
			else if (XO == 'O')
				count[0][3]--; // when y of O = 0
			break;
		case 1: 
			if (XO == 'X') 
				count[1][1]--; // when y of X = 1
			else if (XO == 'O')
				count[1][3]--; // when y of O = 1
			break;
		case 2:
			if (XO == 'X') 
				count[2][1]--; // when y of X = 2
			else if (XO == 'O')
				count[2][3]--; // when y of O = 2
			break;
		}
	}

	public void setXCoordinates(int x, int y)
	{
		if (x+y==2)
		{
			xDiagonal2++;
		}
		if(x == y)
		{
			xDiagonal1++;
		}
		updateCount(x, y, 'X');
		countTurnForX++;
	}

	// for ai player
	public void resetXCoordinates(int x, int y)
	{
		if (x+y==2) 
		{
			xDiagonal2--;
		}
		if(x == y)
		{
			xDiagonal1--;
		}
		undoCount(x, y, 'X');
		countTurnForX--;
	}

	public void setOCoordinates(int x, int y)
	{
		if (x+y==2) 
		{
			oDiagonal2++;
		}
		if(x == y)
		{
			oDiagonal1++;
		}
		updateCount(x, y, 'O');
		countTurnForO++;
	}

	// for ai player
	public void resetOCoordinates(int x, int y)
	{
		if (x+y==2) 
		{
			oDiagonal2--;
		}
		if(x == y)
		{
			oDiagonal1--;
		}
		undoCount(x, y, 'O');
		countTurnForO--;
	}

	public void resetGame()
	{
		//		playerX = new PlayerModel();
		//		playerO = new AIPlayerModel();

		for (int i = 0; i < grid.length; i++)
		{
			for (int j = 0; j < grid[i].length; j++)
			{
				grid[i][j] = Square.EMPTY;
			}
		}


		for (int i = 0; i < count.length; i++) 
		{
			for (int j = 0; j < count[i].length; j++) 
			{
				count[i][j] = 0;
			}
		}
		countTurnForO = 0;
		countTurnForX = 0;
		oDiagonal1 = 0;
		xDiagonal1 = 0;
		oDiagonal2 = 0;
		xDiagonal2 = 0;
		playerTurn = (random.nextInt(10) < 5);
	}


	public boolean getTurn()
	{
		return playerTurn;
	}

	//	private char checkWinner(Square player, int x, int y)
	//	{
	//		
	//	}
	//	
	//	private char checkWinner()
	//	{
	//		
	//	}

	public char checkForWinner(boolean player, int x, int y)
	{
		boolean result =
				checkRowWin(player, y) ||
				checkColumnWin(player, x) ||
				checkLeftDiagonalWin(player, x, y) ||
				checkRightDiagonalWin(player, x, y);
		
		char playerChar = (player) ? 'O' : 'X';
		
		if (result)
			return playerChar;
		else if (countTurnForX + countTurnForO < 9)
			return 'a';
		else
			return 'D';
	}
	
	public boolean checkRowWin(boolean player, int y)
	{
		Square playerSquare = (player) ? Square.O : Square.X;
		for (int x = 0; x < grid.length; x++)
		{
			if(playerSquare != grid[x][y])
				return false;
		}
		return true;
	}
	
	public boolean checkColumnWin(boolean player, int x)
	{
		Square playerSquare = (player) ? Square.O : Square.X;
		for (int y = 0; y < grid.length; y++)
		{
			if(playerSquare != grid[x][y])
				return false;
		}
		return true;
	}
	
	public boolean checkLeftDiagonalWin(boolean player, int x, int y)
	{
		if(x != y)
			return false;
		
		Square playerSquare = (player) ? Square.O : Square.X;
		
		for (int i = 0; i < grid.length; i++)
		{
			if (playerSquare != grid[i][i])
				return false;
		}
		return true;
	}
	
	public boolean checkRightDiagonalWin(boolean player, int x, int y)
	{
		int total = grid.length - 1;
		if((x + y) != total)
			return false;
		
		Square playerSquare = (player) ? Square.O : Square.X;
		for (int i = 0; i < grid.length; i++)
		{
			if (playerSquare != grid[i][total - i])
				return false;
		}
		return true;
	}
	
	public char checkWinner()
	{
		char result = 'a';
		for (int i = 0; i < count.length; i++) 
		{
			for (int j = 0; j < count[i].length; j++) 
			{
				// to represent this condition --> (count[0][0] == 3 || count[0][1] == 3 || count[1][0] == 3 || count[1][1] == 3 || count[2][0] == 3 || count[2][1] == 3)
				if (count[i][j] == 3 && j < 2)
				{
					scoreX++;
					result = 'X';
				}


				// to represent this condition --> (count[0][2] == 3 || count[0][3] == 3 || count[1][2] == 3 || count[1][3] == 3 || count[2][2] == 3 || count[2][3] == 3)
				if (count[i][j] == 3 && j >= 2) 
				{
					scoreO++;
					result = 'O';
				}
			}
		}
		// below are diagonal winning conditions
		if(oDiagonal1 == 3 || oDiagonal2 == 3)
		{
			scoreO++;
			result = 'O';
		}
		else if(xDiagonal1 == 3 || xDiagonal2 == 3)
		{
			scoreX++;
			result = 'X';
		}
		else if(countTurnForO == 5 || countTurnForX == 5)
			result = 'D';

		if (result != 'a')
			resetGame();
		return result;
	}

	private void setPlayerTurn(boolean playerTurn)
	{
		this.playerTurn = playerTurn;
		view.updateTurn(playerTurn);
		if (playerTurn)
			playerO.startTurn();
		else
			playerX.startTurn();
	}

	@Override
	public char makeMove(int x, int y, boolean player) throws InvalidMoveException
	{
		// check whether player can play at [x][y]
		// if so, change grid square
		// check win 
		// if no win, change turn

		// to do if box chosen by payer is empty

		if (grid[x][y] != Square.EMPTY)
			throw new InvalidMoveException();

		Square playerSquare;
		if (player)
		{
			setOCoordinates(x, y);
			playerSquare = Square.O;
		}
		else
		{
			setXCoordinates(x, y);
			playerSquare = Square.X;
		}

		grid[x][y] = playerSquare;
		view.playerTurn(player, x, y);
		
		char winResult = checkForWinner(player, x, y);
		view.updateWinner(winResult);
		
		if (winResult == 'a')
			setPlayerTurn(!player);

		return winResult;
	}



}

