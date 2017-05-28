package Tutu;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class BasicEnemy extends GameObject {

	private Handler handler;
	
	public BasicEnemy(int x, int y,int width,int hight, ID id, Handler handler) {
		super(x, y,width,hight, id);
		velX=5;
		velY=5;
		this.handler=handler;
		// TODO Auto-generated constructor stub
	}

	public void tick() {
		x += velX;
		y += velY;
		if(y <= 0 || y >= Game.HEIGHT - 32) velY *= -1;
		if(x <= 0 || x >= Game.WIDTH- 16) velX *= -1;
		
		handler.addObject(new Trail(x,y,ID.Trail,Color.red,0.04f,handler));
	}

	public void render(Graphics g) {
				
				
		g.setColor(Color.red);
		g.fillRect(x, y, 16, 16);
		
	}
	public Rectangle getBounds() {
		
		return new Rectangle(x,y,16,16);
	}

}
