package com.isi.java.tictactoefinal;

/**
 * If the player will try to make move on the box at which already a chance has been played then this exception will be raised
 * @author Bharat Gupta, Hardeep Singh, Kirandeep Singh
 */
public class InvalidMoveException extends Exception
{
	public InvalidMoveException()
	{
		super("Invalid move made!!! Make another move.");
	}
}
