package com.isi.java.tictactoefinal;

import java.awt.GraphicsEnvironment;

import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

public class Main 
{
	public static void main(String[] args)
	{
		setNimbusLookAndFeel();
		
		WelcomeWindow welcomeWindow = new WelcomeWindow();
		welcomeWindow.setVisible(true);
	}
	
	private static void setNimbusLookAndFeel()
	{
		try
		{
		    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels())
		    {
		        if ("Nimbus".equals(info.getName())) {
		            UIManager.setLookAndFeel(info.getClassName());
		            break;
		        }
		    }
		} 
		catch (Exception e) 
		{
		    // If Nimbus is not available, you can set the GUI to another look and feel.
			e.printStackTrace();
		}
	}
}
