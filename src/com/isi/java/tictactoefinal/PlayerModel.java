package com.isi.java.tictactoefinal;

public class PlayerModel
{
	protected ITicTacToeModel gameChecker;
	protected String name;
	protected int score;
	protected char symbol;
	
	public void setGameChecker(ITicTacToeModel gameChecker)
	{
		this.gameChecker = gameChecker;
	}
	
	public void startTurn()
	{
		
	}
}
