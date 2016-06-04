package fraudster.view;

import fraudster.engine.*;

import java.awt.*;
import javax.swing.*;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;


public class MainPanel extends JPanel
{
	private Image backgroundImage;
	
	public MainPanel(String imagePath)
	{
		File lol;
		try
		{
			lol = new File(imagePath);
			backgroundImage = ImageIO.read(lol);
		}
		
		catch (IOException e)
		{
			System.err.println(imagePath);
		}
	}
	
	@Override
    protected void paintComponent(Graphics g)
	{
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, this);
    }
	
}