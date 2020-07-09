package com.isi.java.tictactoefinal;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * this class is responsible for creating GUI or the console window required for the game board of size 3x3 and it implements ITicTacToeView interface
 * @author Bharat Gupta, Hardeep Singh, Kirandeep Singh
 */
public class TicTacToeBoardThree extends JFrame implements ITicTacToeView
{
	// buttons for game board as array
	private JButton mark[][] = new JButton[3][3];
	private JButton mark4[][] = new JButton[4][4];
	
	private Square square;
	private JLabel result; // result display label 
	private JLabel player1; // player 1 turn indicator
	private JLabel player2; // player 2 turn indicator
	private JLabel player1Score; // player 1 score indicator
	private JLabel player2Score; // player 2 score indicator
	
	private JButton reset; // to reset game 
	private JButton exit; // to exit game
	private JButton chooseBoard; // to choose another board
	private JButton playAI; // to make ai play

	private JPanel contentPane; // default panel
	private JPanel game_board; // contains all 9 buttons for game only
	private JPanel display; // contains labels to display info
	private JPanel actions; // contains reset and exit
	private JPanel upper; // contains game_board and display panels
	private JPanel lower; // contains actions panel
	
	private ITicTacToeModel gameChecker;
	
	
	public TicTacToeBoardThree(ITicTacToeModel gameChecker)
	{
		super("TIC-TAC-TOE");
		
		this.gameChecker = gameChecker;

		createComponents();
		createContainers();
		addComponentsToContainers();
		addListeners();
	
		pack();
		setSize(1000, 600);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
	}
	
	public void createComponents()
	{
		// create game board buttons array
		for (int i = 0; i < mark.length; i++) 
		{
			for (int j = 0; j < mark.length; j++) 
			{
				mark[i][j] = new JButton("X/O");
				mark[i][j].setCursor(new Cursor(HAND_CURSOR));
			}
		}	
		
		// labels created
		result = new JLabel("RESULT : ");
		result.setFont(new Font("SimSun",Font.CENTER_BASELINE,24));
		player1 = new JLabel("PLAYER O");
		player1.setFont(new Font("SimSun",Font.CENTER_BASELINE,24));
		player2 = new JLabel("PLAYER X");
		player2.setFont(new Font("SimSun",Font.CENTER_BASELINE,24));
		player1Score = new JLabel("PLAYER O SCORE : " + gameChecker.getScore('O'));
		player1Score.setFont(new Font("SimSun",Font.CENTER_BASELINE,24));
		player2Score = new JLabel("PLAYER X SCORE : " + gameChecker.getScore('X'));
		player2Score.setFont(new Font("SimSun",Font.CENTER_BASELINE,24));
		
		// button to reset game
		reset = new JButton("PLAY AGAIN");
		// button to exit
		exit = new JButton("EXIT");		
		// button to choose another board
		chooseBoard = new JButton("CHANGE BOARD");
		// button to make ai player take turn
		playAI = new JButton("PLAY AI");
	}
	

	public void createContainers() 
	{
		// default panel
		contentPane = (JPanel)getContentPane(); // only do this once as this is pre-made by JFrame constructor by default in code
		contentPane.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
		
		// upper panel
		upper = new JPanel();
		upper.setLayout(new BoxLayout(upper, BoxLayout.X_AXIS));
		
		// lower panel
		lower = new JPanel();
		
		// game panel
		game_board = new JPanel();
		game_board.setLayout(new GridLayout(3, 3, 5, 5));
		game_board.setBorder(BorderFactory.createEmptyBorder(50,50,50,50));
		
		// display panel
		display = new JPanel();
		display.setLayout(new BoxLayout(display, BoxLayout.Y_AXIS));
		display.setBorder(BorderFactory.createEmptyBorder(50,50,50,50));
		
		// actions panel
		actions = new JPanel();
		actions.setLayout(new BoxLayout(actions, BoxLayout.X_AXIS));
		actions.setBorder(BorderFactory.createEmptyBorder(50,50,50,50));
	}
	
	
	public void addComponentsToContainers()
	{
		// create game board
		for (int y = 0; y < mark.length; y++) 
		{
			for (int x = 0; x < mark.length; x++)
			{
				game_board.add(mark[x][y]);
			}
		}
		
		// add game board
		upper.add(game_board);
		
		// create display board
		display.add(result);
		display.add(Box.createRigidArea(new Dimension(0, 10)));
		display.add(player1);
		display.add(Box.createRigidArea(new Dimension(0, 10)));
		display.add(player2);
		display.add(Box.createRigidArea(new Dimension(0, 10)));
		display.add(player1Score);
		display.add(Box.createRigidArea(new Dimension(0, 10)));
		display.add(player2Score);
		
		// add display board
		upper.add(display);
		
		// create actions board
		actions.add(playAI);
		actions.add(Box.createRigidArea(new Dimension(50, 0)));
		actions.add(reset);
		actions.add(Box.createRigidArea(new Dimension(50, 0)));
		actions.add(chooseBoard);
		actions.add(Box.createRigidArea(new Dimension(50, 0)));
		actions.add(exit);		
		
		// add actions board
		lower.add(actions);
		 
		contentPane.add(upper);
		contentPane.add(lower);
	}
	
	
	
	public void addListeners() 
	{
		for (int i = 0; i < mark.length; i++) 
		{
			for (int j = 0; j < mark[i].length; j++) 
			{
				// changed to make i,j accessible inside action event inner class
				final int g = i;
				final int h = j;
				mark[i][j].addActionListener((ActionEvent e)->
				{
					
					try
					{
						gameChecker.makeMove(g, h, false);
					}
					catch (InvalidMoveException e1) {JOptionPane.showMessageDialog(this, e1.getMessage(), "Invalid move!",JOptionPane.WARNING_MESSAGE);}
				});
			}
		}			
				
				// reset button listener
				reset.addActionListener((ActionEvent e)->
				{
					setGameBoard("X/O");
					gameChecker.resetGame();
					result.setText("RESULT : ");
				});
				
				// exit button listener
				exit.addActionListener((ActionEvent e)->
				{
					System.exit(0); 
				});
				
				// listener to play for AI player
				playAI.addActionListener((ActionEvent e)->
				{
					try
					{
						char resultChar = gameChecker.makeMove(0, 0, true);
						switch (resultChar) 
						{
						case 'O': result.setText("RESULT : PLAYER O WINS");
								  player2.setText("PLAYER X");
						    break;
						case 'X': result.setText("RESULT : PLAYER X WINS");
						  		  player1.setText("PLAYER O");
							break;
						case 'D': result.setText("IT'S A DRAW");
							break;
						}
						playerTurn(true, gameChecker.getXAI(), gameChecker.getYAI());
						
						if (resultChar == 'O' || resultChar == 'X' || resultChar == 'D')
						{
							String winner = "Player " + resultChar + " won the game.";
							JOptionPane.showMessageDialog(this, winner, "Game over", JOptionPane.INFORMATION_MESSAGE);
							setGameBoard("X/O");
						}
							
					}
					catch (InvalidMoveException e1) {JOptionPane.showMessageDialog(this, e1.getMessage(), "Invalid move!",JOptionPane.WARNING_MESSAGE);}
				});
				
				// board choosing button listener
				chooseBoard.addActionListener((ActionEvent e)->
				{
					this.dispose();
					new BoardDecider().setVisible(true);
				});
	}
	
	/**
	* to set game board buttons state and label them
	* @author Bharat Gupta, Hardeep Singh, Kirandeep Singh
	* @param buttonText -> text to be displayed on all buttons
	* @param buttonState -> true to enable button and false to disable button
	*/
	public void setGameBoard(String buttonText)
	{
		for (int k = 0; k < mark.length; k++) 
	  		for (int l = 0; l < mark.length; l++) 										
	  			{
	  			mark[k][l].setText(buttonText);
	  			}
		player1.setText("PLAYER O");
		player2.setText("PLAYER X");
		player1Score.setText("PLAYER O SCORE : " + gameChecker.getScore('O'));
		player2Score.setText("PLAYER X SCORE : " + gameChecker.getScore('X'));
	}
	
	/**
	* to do steps on each turn for both players
	* @author Bharat Gupta, Hardeep Singh, Kirandeep Singh
	* @param player -> the current player playing
	* @param x -> x coordinate of button pressed by current player
	* @param y -> y coordinate of button pressed by current player
	*/
	public void playerTurn(boolean player, int x, int y)
	{
		String playerSymbol = (player) ? "O" : "X";
		mark[x][y].setText(playerSymbol);
	}

	@Override
	public void updateWinner(char winResult)
	{
		switch (winResult) 
		{
		case 'O': result.setText("RESULT : PLAYER O WINS");
				  player2.setText("PLAYER X");
		    break;
		case 'X': result.setText("RESULT : PLAYER X WINS");
		  		  player1.setText("PLAYER O");
			break;
		case 'D': result.setText("IT'S A DRAW");
			break;
		}
		
		if (winResult == 'O' || winResult == 'X' || winResult == 'D')
		{
			String winner = "Player " + winResult + " won the game.";
			JOptionPane.showMessageDialog(this, winner, "Game over", JOptionPane.INFORMATION_MESSAGE);
			setGameBoard("X/O");
		}
	}

	@Override
	public void updateTurn(boolean player)
	{
		if (player)
		{
			player1.setText("PLAYER O TURN");
			player2.setText("PLAYER X");
		}
		else
		{
			player1.setText("PLAYER O");
			player2.setText("PLAYER X TURN");
		}
	}
	
}

