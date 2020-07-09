package com.isi.java.tictactoefinal;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.ActionEvent;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class WelcomeWindow extends JFrame
{

	private JButton playButton;
	private JLabel tictactoeJLabel;
	private JLabel copyrightJLabel;
	
	private JPanel contentJPane;
	
	public WelcomeWindow()
	{
		createComponents();
		setupContainers();
		addComponentsToContainers();
		addActionListeners();
		
		//JWindow welcomeJWindow=new JWindow();
		setSize(780, 790);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		
	}
	
	public void createComponents() 
	{
		ImageIcon tictactoeIcon=new ImageIcon("imgs/tictactoe.png");
		ImageIcon playIcon=new ImageIcon("imgs/rsz_splash.png");
		
		tictactoeJLabel=new JLabel();
		tictactoeJLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		tictactoeJLabel.setIcon(tictactoeIcon);
		playButton=new JButton(playIcon);
		playButton.setBackground(Color.black);
		playButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		playButton.setCursor(new Cursor(HAND_CURSOR));
		copyrightJLabel=new JLabel("All_Rights_Reserved@_Bharat_Gupta_Hardeep_Singh_Kirandeep_Singh_2019");
		copyrightJLabel.setForeground(Color.white);
		copyrightJLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		
	}
	
	public void setupContainers()
	{
		
		contentJPane=(JPanel)getContentPane();
		contentJPane.setLayout(new BoxLayout(contentJPane, BoxLayout.Y_AXIS));
		contentJPane.setBackground(Color.BLACK);

	}
	
	
	public void addComponentsToContainers()
	{
		contentJPane.add(tictactoeJLabel);
		contentJPane.add(Box.createRigidArea(new Dimension(0, 40)));
		contentJPane.add(playButton);
		contentJPane.add(Box.createRigidArea(new Dimension(0, 40)));
		contentJPane.add(copyrightJLabel);
	}
	
	public void addActionListeners()
	{
		playButton.addActionListener((ActionEvent e)->
		{
			BoardDecider boardDecider = new BoardDecider();
			this.dispose();
			boardDecider.setVisible(true);
		}
	);
		
	}

}
