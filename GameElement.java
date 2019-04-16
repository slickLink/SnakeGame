import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class GameElement 
{
	private int posX, posY, width, height;
	private Rectangle element;
	public GameElement(int x, int y)
	{
		this.posX = x;
		this.posY = y;
		this.width = 10;
		this.height = 10;
		this.element = new Rectangle(posX, posY, width, height);
	}
	public void paint(Graphics g, Color color)
	{
		Graphics2D box = (Graphics2D)g;
		box.setColor(color);
		this.element.setLocation(posX, posY);
		box.fill(element);
	}
	
	// accessors
	
	public Rectangle getRec()
	{
		return this.element;
	}
	
	public int getPosX()
	{
		return this.posX;
	}
	public int getPosY()
	{
		return this.posY;
	}
	public int getWidth()
	{
		return this.width;
	}
	public int getHeight()
	{
		return this.height;
	}
	
	//mutators
	
	public void setX(int x)
	{
		this.posX = x;
	}
	public void setY(int y)
	{
		this.posY = y;
	}
	public void setWidth(int w)
	{
		this.width= w;
	}
	public void setHeight(int h)
	{
		this.height = h;
	}
}
