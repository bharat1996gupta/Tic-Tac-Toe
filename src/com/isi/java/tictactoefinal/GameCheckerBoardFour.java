//package com.isi.java.tictactoefinal;
//
//import java.util.Random;
//
///**
// * this class contains all the data and logic required for the game and it implements ITicTacToeModel interface
// * @author Bharat Gupta, Hardeep Singh, Kirandeep Singh
// */
//public class GameCheckerBoardFour implements ITicTacToeModel
//{
///*
//	// [0] = x of X, [1] = y of X , [2] = x of O and [3] = y of O
////	private int[] count0 =new int[4]; // counts number of 0
////	private int[] count1 =new int[4]; // counts number of 1
////	private int[] count2 =new int[4]; // counts number of 2
//*/
//	private Square[][] grid4 = new Square[4][4];
//	private PlayerModel playerX;
//	private PlayerModel playerO;
//	
//	Random random = new Random(System.currentTimeMillis());
//	
//	private int[][] count4 = new int[4][4]; 
//	/*
//	 * comment to understand count4[][] array
//	 * [0][0] -> when x coordinate of X player = 0
//	 * [0][1] -> when y coordinate of X player = 0
//	 * [0][2] -> when x coordinate of O player = 0
//	 * [0][3] -> when y coordinate of O player = 0
//	 
//	 * [1][0] -> when x coordinate of X player = 1
//	 * [1][1] -> when y coordinate of X player = 1
//	 * [1][2] -> when x coordinate of O player = 1
//	 * [1][3] -> when y coordinate of O player = 1
//	 
//	 * [2][0] -> when x coordinate of X player = 2
//	 * [2][1] -> when y coordinate of X player = 2
//	 * [2][2] -> when x coordinate of O player = 2
//	 * [2][3] -> when y coordinate of O player = 2
//	 
//	 * [3][0] -> when x coordinate of X player = 3
//	 * [3][1] -> when y coordinate of X player = 3
//	 * [3][2] -> when x coordinate of O player = 3
//	 * [3][3] -> when y coordinate of O player = 3
//	 */
//	private int xDiagonal1 = 0; // for diagonal where x == y (player X)
//	private int oDiagonal1 = 0; // for diagonal where x == y (player O)
//	private int xDiagonal2 = 0; // for diagonal where x + y = 2 (player X)
//	private int oDiagonal2 = 0; // for diagonal where x + y = 2 (player O)
//	private int countTurnForX = 0; // to count4 turns played by X
//	private int countTurnForO = 0; // to count4 turns played by O
//	private int scoreO = 0; // contains Score of player O
//	private int scoreX = 0; // contains Score of player X
//	private boolean playerTurn;  // true for player O turn and false for player X turn
//
//	
//	public GameCheckerBoardFour()
//	{
//		scoreO = 0;
//		scoreX = 0;
//		resetGame();
//	}
//	
//	
//	public int getScore(char player)
//	{
//		if(player == 'O')
//			return scoreO;
//		else 
//			return scoreX;
//	}
//	
//	public void updateCount(int x, int y, char XO)
//	{
//		switch (x) 
//		{
//		case 0:	 
//				if (XO == 'X') 
//					count4[0][0]++; // when x of X = 0
//				else
//					count4[0][2]++; // when x of O = 0
////				(XO == 'X') ?countxX0++:countxO0++; --> did not work
//			break;
//		case 1: 
//				if (XO == 'X') 
//					count4[1][0]++; // when x of X = 1
//				else
//					count4[1][2]++; // when x of O = 1
//				break;
//		case 2:
//				if (XO == 'X') 
//					count4[2][0]++; // when x of X = 2
//				else
//					count4[2][2]++; // when x of O = 2
//			break;
//		case 3:
//				if (XO == 'X') 
//					count4[3][0]++; // when x of X = 3
//				else
//					count4[3][2]++; // when x of O = 3
//			break;
//		}
//		
//		switch (y) 
//		{
//		case 0:	 
//				if (XO == 'X') 
//					count4[0][1]++; // when y of X = 0
//				else
//					count4[0][3]++; // when y of O = 0
//			break;
//		case 1: 
//				if (XO == 'X') 
//					count4[1][1]++; // when y of X = 1
//				else
//					count4[1][3]++; // when y of O = 1
//			break;
//		case 2:
//				if (XO == 'X') 
//					count4[2][1]++; // when y of X = 2
//				else
//					count4[2][3]++; // when y of O = 2
//			break;
//		case 3:
//				if (XO == 'X') 
//					count4[3][1]++; // when x of X = 3
//				else
//					count4[3][3]++; // when x of O = 3
//			break;
//		}
//	}
//
//	
//	public void setXCoordinates(int x, int y)
//	{
//		if (x+y==3)
//		{
//			xDiagonal2++;
//		}
//		if(x == y)
//		{
//			xDiagonal1++;
//		}
//		updateCount(x, y, 'X');
//		countTurnForX++;
//	}
//	
//	
//	public void setOCoordinates(int x, int y)
//	{
//		if (x+y==3) 
//		{
//			oDiagonal2++;
//		}
//		if(x == y)
//		{
//			oDiagonal1++;
//		}
//		updateCount(x, y, 'O');
//		countTurnForO++;
//	}
//		
//	
//	public void resetGame()
//	{
//		playerX = new PlayerModel(this);
//		playerO = new PlayerModel(this);
//		
//		for (int i = 0; i < grid4.length; i++)
//		{
//			for (int j = 0; j < grid4[i].length; j++)
//			{
//				grid4[i][j] = Square.EMPTY;
//			}
//		}
//		
//		
//		for (int i = 0; i < count4.length; i++) 
//		{
//			for (int j = 0; j < count4[i].length; j++) 
//			{
//				count4[i][j] = 0;
//			}
//		}
//		countTurnForO = 0;
//		countTurnForX = 0;
//		oDiagonal1 = 0;
//		xDiagonal1 = 0;
//		oDiagonal2 = 0;
//		xDiagonal2 = 0;
//		playerTurn = (random.nextInt(10) < 5);
//	}
//	
//	
//	public boolean getTurn()
//	{
//		return playerTurn;
//	}
//	
//	
//	public char checkWinner()
//	{
//		char result = 'a';
//		int checkCount = 4;
//		int checkTurn = 8;
//		for (int i = 0; i < count4.length; i++) 
//		{
//			for (int j = 0; j < count4[i].length; j++) 
//			{
//				// to represent this condition --> (count4[0][0] == 3 || count4[0][1] == 3 || count4[1][0] == 3 || count4[1][1] == 3 || count4[2][0] == 3 || count4[2][1] == 3)
//				if (count4[i][j] == checkCount && j < 2)
//				{
//					scoreX++;
//					result = 'X';
//				}
//				
//				
//				// to represent this condition --> (count4[0][2] == 3 || count4[0][3] == 3 || count4[1][2] == 3 || count4[1][3] == 3 || count4[2][2] == 3 || count4[2][3] == 3)
//				if (count4[i][j] == checkCount && j >= 2) 
//				{
//					scoreO++;
//					result = 'O';
//				}
//			}
//		}
//		// below are diagonal winning conditions
//		if(oDiagonal1 == checkCount || oDiagonal2 == checkCount)
//			{
//				scoreO++;
//				result = 'O';
//			}
//		else if(xDiagonal1 == checkCount || xDiagonal2 == checkCount)
//			{
//				scoreX++;
//				result = 'X';
//			}
//		else if(countTurnForO == checkTurn || countTurnForX == checkTurn)
//			result = 'D';
//		
//		if (result != 'a')
//			resetGame();
//		return result;
//	}
//
//	
//	@Override
//	public char makeMove(int x, int y, boolean chance) throws InvalidMoveException
//	{
//		// check whether player can play at [x][y]
//		// if so, change grid4 square
//		// check win 
//		// if no win, change turn
//		if(grid4[x][y] == Square.EMPTY)
//		{
//			// to do if box chosen by payer is empty
//			if (playerTurn) 
//			{
//				setOCoordinates(x, y);
//				grid4[x][y] = Square.O;
//				playerTurn = false;
//				playerX.startTurn();
//			}
//			else
//			{
//				setXCoordinates(x, y);
//				grid4[x][y] = Square.X;
//				playerTurn = true;
//				playerO.startTurn(); 
//			}
//			return checkWinner();
//		}
//		else 
//		{
//			// to do if box chosen by payer is not empty
//			throw new InvalidMoveException();
//		}
//	}
//
//
//	@Override
//	public int getXAI() {
//		// TODO Auto-generated method stub
//		return 0;
//	}
//
//
//	@Override
//	public int getYAI() {
//		// TODO Auto-generated method stub
//		return 0;
//	}
//	
//}
//	
//	