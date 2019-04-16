import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JButton;

public class Menu
{
	private JButton button;
	
	public void paint(Graphics g)
	{
		Graphics2D g2 = (Graphics2D)g;
		
		Font comic = new Font("comic sans", Font.BOLD, 50);
		g.setColor(Color.WHITE);
		g.setFont(comic);
		button = new JButton("PLAY");
		
	}
}
