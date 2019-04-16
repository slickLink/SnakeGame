import javax.swing.JFrame;

public class Frame 
{
	public static void main(String[] args) 
	{
		JFrame frame = new JFrame();
		frame.setTitle("Snake Game");
		frame.setSize(500, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GameFrame gamePanel = new GameFrame();
		frame.add(gamePanel);
		frame.setVisible(true);
		
	}
}
