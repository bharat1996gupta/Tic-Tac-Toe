package com.isi.java.tictactoefinal;

public interface ITicTacToeModel
{
	/**
	 * contains algorithm to check winner of game
	 * @author Bharat Gupta, Hardeep Singh, Kirandeep Singh
	 */
	public abstract char checkWinner();
	public abstract char checkForWinner(boolean player, int x, int y);
	/**
	 * to reset all variables for a new game
	 * @author Bharat Gupta, Hardeep Singh, Kirandeep Singh
	 */
	public abstract void resetGame();
	/**
	 * getter for score of mentioned player
	 * @author Bharat Gupta, Hardeep Singh, Kirandeep Singh
	 * @param player -> player whose score is required
	 */
	public abstract int getScore(char c);
	/**
	 * function to set coordinate values for player 'X'
	 * @author Bharat Gupta, Hardeep Singh, Kirandeep Singh 
	 * @param x -> x coordinate of player 'X'
	 * @param y -> y coordinate of player 'X'
	 */
	public abstract void setXCoordinates(int x, int y);
	/**
	 * function to set coordinate values for player 'O'
	 * @author Bharat Gupta, Hardeep Singh, Kirandeep Singh 
	 * @param x -> x coordinate of player 'O'
	 * @param y -> y coordinate of player 'O'
	 */
	public abstract void setOCoordinates(int x, int y);
	/**
	 * used to update the game status according to the move made by the user and throws exception if an invalid move is made
	 * and you can see InvalidMoveException description for it's details below
	 * @author Bharat Gupta, Hardeep Singh, Kirandeep Singh
	 * @exception InvalidMoveException
	 */
	
	public abstract void resetXCoordinates(int x, int y);
	public abstract void resetOCoordinates(int x, int y);
	
	public abstract char makeMove(int x, int y, boolean chance) throws InvalidMoveException;
	/**
	 * to choose game player randomly
	 * @author Bharat Gupta, Hardeep Singh, Kirandeep Singh
	 */
	boolean getTurn();
	
	Square[][] getGrid();

	
	public int getXAI();
	public int getYAI();
	public void setAI(int x, int y);
}
