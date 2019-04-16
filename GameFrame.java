import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GameFrame extends JPanel implements KeyListener, ActionListener
{
	
	private boolean play = false;
	private int winner;
	private int delay = 65; // 50
	private int num = 0;
	private  Timer timer;
	private Player playerOne = new Player(0, 0);
	private Player playerTwo = new Player(475, 451);
	private GameElement objective = new GameElement(237, 225);
	private enum STATE
	{
		MENU,
		GAME,
		GAMEOVER
	};
	
	private STATE state = STATE.MENU; 
	public GameFrame()
	{
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		timer = new Timer(delay,this); 
		playerTwo.setPlayerColor(Color.CYAN);
		playerOne.setDirection(3);
		this.winner = 0;
		timer.start();
	}
	
	public void paint(Graphics g)
	{
		Graphics2D g2 = (Graphics2D) g;
		//background
		g.setColor(Color.GRAY);
		g.fillRect(0, 0, getWidth(), getHeight());
		Font comic = new Font("comic sans", Font.BOLD, 50);
		if(state == STATE.GAME)
		{
			// objective
			if(num % 3 == 0 & num != 0)
			{
				objective.paint(g, Color.YELLOW);
			}
			else
			{
				objective.paint(g, Color.RED);
			}
		
			
			//players
			playerOne.draw(g,1);
			playerTwo.draw(g,1);
		}
		else if(state == STATE.MENU)
		{
			
			g.setColor(Color.BLACK);
			g.setFont(comic);
			g2.drawString("PRESS ENTER", super.getWidth() / 8, super.getHeight() / 2);
		}
		
		else if( state == STATE.GAMEOVER)
		{
			g.setColor(Color.BLACK);
			g.setFont(comic);
			String win = " PLAYER " + this.winner + " WINS";
			g2.drawString(win, super.getWidth() / 9, super.getHeight() / 2);
		}
		
		g.dispose();
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(state == STATE.GAME)
		{
			movePlayer(playerOne);
			movePlayer(playerTwo);
			checkObjective(playerTwo);
			checkObjective(playerOne);
			if(playerTwo.equals(playerOne))
			{
				this.winner = 1;
				state = STATE.GAMEOVER;
			}
			if(playerOne.equals(playerTwo))
			{
				this.winner = 2;
				state = STATE.GAMEOVER;
			}
		}
		
		repaint();
	}
	public void checkObjective(Player player)
	{
		if(player.getHead().intersects(objective.getRec()))
		{
			int x = (int) (Math.random() * (super.getWidth() - 0));
			int y = (int) (Math.random() * (super.getHeight() - 0));
			objective.setX(((x + 5)/ 10) * 10);
			objective.setY(((y + 5)/ 10) * 10);
			// after every 3 objectives eaten there is a special one that grants 10 length
			if(num % 3 == 0 && num != 0)
			{
				for(int i = 0; i < 10; i++)
				{
					player.add();
					player.draw(getGraphics(), 0);
				}
			}
			player.add();
			num++;
		}
	}
	public void movePlayer(Player player)
	{
		// the constant motion of a player
		if(player.getdirection() == 1)
		{
			player.setY(player.getY() - 10);
			if(player.getY() < 0)
			{
				player.setY(super.getHeight());
			}
		}
		else if(player.getdirection() == 2)
		{
			player.setX(player.getX() + 10);
			if(player.getX() > super.getWidth())
			{
				player.setX(0);
			}
		}
		else if(player.getdirection() == 3)
		{
			player.setY(player.getY() + 10);
			if(player.getY() > super.getHeight())
			{
				player.setY(0);
			}
		}
		else
		{
			player.setX(player.getX() - 10);
			if(player.getX() < 0)
			{
				player.setX(super.getWidth());
			}
		}
		
		
	}
	public void resetGame()
	{
		playerOne.reset(0,0,3);
		playerTwo.reset(475,451,1);
		this.num = 0;
		objective.setX(237);
		objective.setY(225);
		state = STATE.GAME;
	}
	@Override
	public void keyPressed(KeyEvent e) 
	{
		if(state == STATE.GAME)
		{
			// when the arrow keys are pressed player 1
			if(e.getKeyCode() == KeyEvent.VK_UP)
			{
				if(playerOne.getdirection() == 2 || playerOne.getdirection() == 4)
					playerOne.setDirection(1);
			}
			if(e.getKeyCode() == KeyEvent.VK_DOWN)
			{
				if(playerOne.getdirection() == 2 || playerOne.getdirection() == 4)
					playerOne.setDirection(3);
			}
			if(e.getKeyCode() == KeyEvent.VK_LEFT)
			{
				if(playerOne.getdirection() == 1 || playerOne.getdirection() == 3)
					playerOne.setDirection(4);
			}
			if(e.getKeyCode() == KeyEvent.VK_RIGHT)
			{
				if(playerOne.getdirection() == 1 || playerOne.getdirection() == 3)
					playerOne.setDirection(2);
			}	
			
			//player Two
			
			if(e.getKeyCode() == KeyEvent.VK_W)
			{
				if(playerTwo.getdirection() == 2 || playerTwo.getdirection() == 4)
					playerTwo.setDirection(1);
			}
			if(e.getKeyCode() == KeyEvent.VK_S)
			{
				if(playerTwo.getdirection() == 2 || playerTwo.getdirection() == 4)
					playerTwo.setDirection(3);
			}
			if(e.getKeyCode() == KeyEvent.VK_A)
			{
				if(playerTwo.getdirection() == 1 || playerTwo.getdirection() == 3)
					playerTwo.setDirection(4);
			}
			if(e.getKeyCode() == KeyEvent.VK_D)
			{
				if(playerTwo.getdirection() == 1 || playerTwo.getdirection() == 3)
					playerTwo.setDirection(2);
			}	
		}
		if(state == STATE.MENU)
		{
			if(e.getKeyCode() == KeyEvent.VK_ENTER)
				state = STATE.GAME;
		}
		// returning to play the game if the game is over
		if(state == STATE.GAMEOVER && e.getKeyCode() == KeyEvent.VK_ESCAPE)
		{
			resetGame();
		}
		
	}
	
	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
}
