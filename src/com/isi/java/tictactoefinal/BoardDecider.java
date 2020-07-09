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
public class BoardDecider extends JFrame
{
	// buttons for game board as array
	private JButton grid4[][] = new JButton[4][4];
	private JButton grid3[][] = new JButton[3][3];
	private JButton grid3Choice;
	private JButton grid4Choice;

	private JPanel contentPane;
	private JPanel grid3x3;
	private JPanel grid4x4;
	private JPanel grid;
	private JPanel choose;

	private JLabel heading;


	public BoardDecider()
	{
		super("TIC-TAC-TOE");

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
		for (int i = 0; i < grid4.length; i++) 
		{
			for (int j = 0; j < grid4[i].length; j++) 
			{
				if (i<grid4.length-1&&j<grid4.length-1) 
				{
					grid3[i][j] = new JButton("X/O");
				}
				grid4[i][j] = new JButton("X/O");
			}
		}		

		grid3Choice = new JButton("3 X 3");
		grid4Choice = new JButton("4 X 4");

		heading = new JLabel("Choose your game board");
		heading.setAlignmentX(CENTER_ALIGNMENT);
		heading.setFont(new Font("Comic Sans Ms", Font.BOLD|Font.ITALIC, 30));

	}


	public void createContainers() 
	{
		// default panel
		contentPane = (JPanel)getContentPane(); // only do this once as this is pre-made by JFrame constructor by default in code
		contentPane.setBorder(BorderFactory.createEmptyBorder(50,50,50,50));
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));

		grid3x3 = new JPanel();
		//		grid3x3.setBorder(BorderFactory.createEmptyBorder(30,30,30,30));
		grid3x3.setLayout(new GridLayout(3,3,0,0));

		grid4x4 = new JPanel();
		//		grid4x4.setBorder(BorderFactory.createEmptyBorder(30,30,30,30));
		grid4x4.setLayout(new GridLayout(4,4,0,0));

		grid = new JPanel();
		//		grid.setBorder(BorderFactory.createEmptyBorder(50,50,50,50));
		grid.setLayout(new BoxLayout(grid, BoxLayout.X_AXIS));

		choose = new JPanel();
		choose.setLayout(new BoxLayout(choose, BoxLayout.X_AXIS));
	} 


	public void addComponentsToContainers()
	{
		// create game board 4x4
		for (int y = 0; y < grid4.length; y++) 
		{
			for (int x = 0; x < grid4.length; x++)
			{
				if (x < grid4.length-1 && y < grid4.length-1) 
				{
					grid3x3.add(grid3[x][y]);
				}
				grid4x4.add(grid4[x][y]);
			}
		}

		grid.add(grid3x3);
		grid.add(Box.createRigidArea(new Dimension(50, 0)));
		grid.add(grid4x4);

		choose.add(grid3Choice);
		choose.add(Box.createRigidArea(new Dimension(400, 0)));
		choose.add(grid4Choice);

		contentPane.add(heading);
		contentPane.add(Box.createRigidArea(new Dimension(0, 40)));
		contentPane.add(grid);
		contentPane.add(Box.createRigidArea(new Dimension(0, 40)));
		contentPane.add(choose);
	}



	public void addListeners() 
	{
		grid3Choice.addActionListener((ActionEvent e)->
		{
			PlayerModel playerX = new PlayerModel();
			PlayerModel playerO = new AIPlayerModel();
			GameCheckerBoardThree gameChecker = new GameCheckerBoardThree(playerX, playerO);
			playerX.setGameChecker(gameChecker);
			playerO.setGameChecker(gameChecker);
			ITicTacToeView game = new TicTacToeBoardThree((ITicTacToeModel)gameChecker);
			game.setVisible(true);
			gameChecker.setView(game);
			this.dispose();
		}
				);

		grid4Choice.addActionListener((ActionEvent e)->
		{
//			ITicTacToeModel gameChecker = new GameCheckerBoardFour();
//			ITicTacToeView game = new TicTacToeBoardFour(gameChecker);
//			game.setVisible(true);
//			this.dispose();
		}
				);	
	}

}

