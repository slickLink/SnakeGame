import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;

public class Player
{
	private Color color = Color.WHITE;
	private ArrayList<GameElement> body;
	private GameElement head;
	private int x;
	private int y;
	private int direction;
	public Player(int x, int y)
	{
		this.body = new ArrayList<>();
		this.head = new GameElement(x, y);
		this.body.add(head);
		this.x = x;
		this.y = y;
		this.direction = 1;
	}
	public ArrayList getBody()
	{
		return this.body;
	}
	public int getX()
	{
		return this.x;
	}
	public int getY()
	{
		return this.y;
	}
	public int getdirection()
	{
		return this.direction;
	}
	public void setX(int x)
	{
		this.x = x;
	}
	public void setY(int y)
	{
		this.y = y;
	}
	public void setDirection(int d)
	{
		this.direction = d;
	}
	public void setPlayerColor(Color c)
	{
		this.color = c;
	}
	public void add()
	{
		GameElement newBod = new GameElement(0,0);
		this.body.add(newBod);
	}
	public void reset(int x, int y, int d)
	{
		for(int i = this.body.size() - 1; i > 0; i--)
		{
			this.body.remove(i);
		}
		this.x = x;
		this.y = y;
		this.direction = d;
	}
	public Rectangle getHead()
	{
		return body.get(0).getRec();
	}
	
	public void draw(Graphics g, int ch)
	{
		for(int i = this.body.size() - 1; i >= 0; i--)
		{
			if(i == 0)
			{
				body.get(i).setX(this.x);
				body.get(i).setY(this.y);
			}
			else
			{
				body.get(i).setX(body.get(i - 1).getPosX());
				body.get(i).setY(body.get(i - 1).getPosY());
			}
		}
		Graphics2D g2 = (Graphics2D)g;
		if(ch == 1)
		{
			for(int i = 0; i < body.size(); i++)
			{
				body.get(i).paint(g2, color);
			}
		}
	}
	
	public boolean equals(Object other)
	{
		// checking if a player has collided with another player
		Player otherPlayer = (Player) other;
		boolean collision = false;
		Rectangle temp;
		for(int i = 0; i < otherPlayer.getBody().size(); i++)
		{
			temp = otherPlayer.body.get(i).getRec();
			if(getHead().intersects(temp))
			{
				collision = true;
				break;
			}
		}
		return collision;
	}
}