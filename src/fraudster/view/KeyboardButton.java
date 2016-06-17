package fraudster.view;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class KeyboardButton extends JButton
{
	public static final int buttonX=150, buttonY=90;
	
	/**
	 * Will try to use func to find the right buttons !
	 * button-light-<func>.png and button-<func>.png
	 */
	public KeyboardButton(String func)
	{
		super();
		setFocusPainted(false);
		setMargin(new Insets(0, 0, 0, 0));
		setBorderPainted(false);
		setContentAreaFilled(false);
		setBorder(new EmptyBorder(0,5,3,5));
		try
		{
			setIcon(new ImageIcon(ImageIO.read(new File("../assets/button-light-"+func+".png")).getScaledInstance(buttonX, buttonY, Image.SCALE_SMOOTH)));
			setPressedIcon(new ImageIcon(ImageIO.read(new File("../assets/button-"+func+".png")).getScaledInstance(buttonX, buttonY, Image.SCALE_SMOOTH)));
	
		}
		catch (IOException e)
		{ System.err.println("Couldn't open button images"); }
	}
}
