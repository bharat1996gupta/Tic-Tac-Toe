package com.isi.java.tictactoefinal;

public interface ITicTacToeView
{
	/**
	 * creates all the components of console i.e all buttons and labels
	 * @author Bharat Gupta, Hardeep Singh, Kirandeep Singh
	 */
	public abstract void createComponents();
	/**
	 * creates the containers and sets properties to help them display the console properly
	 * @author Bharat Gupta, Hardeep Singh, Kirandeep Singh
	 */ 
	public abstract void createContainers();
	/**
	 * adds all the components i.e buttons, labels and panels to their specific containers
	 * @author Bharat Gupta, Hardeep Singh, Kirandeep Singh
	 */
	public abstract void addComponentsToContainers();
	/**
	 * adds action listeners to all buttons on the game console
	 * @author Bharat Gupta, Hardeep Singh, Kirandeep Singh
	 */
	public abstract void addListeners();
	public abstract void setVisible(boolean bool);
	
	public abstract void updateWinner(char winResult);
	public abstract void updateTurn(boolean player);
	public abstract void playerTurn(boolean player, int x, int y);
	
}
