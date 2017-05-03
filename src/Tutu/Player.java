package Tutu;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Player extends GameObject{
	
	Handler handler;
	

	public Player(int x, int y, int width,int height, ID id, Handler handler) {
		super(x, y,width,height, id);
		this.handler = handler;
				
	}

	
	public void tick() {
		x += velX;		
		x = Game.clamp(x, 0, Game.WIDTH - 105);	
		
		
		collision();
	}

	
	private void collision() {
		
		
	}


	public void render(Graphics g) {
		g.setColor(Color.BLACK);		
		g.fillRect(x, y, width, height);
		
		
	}

	public Rectangle getBounds() {
		
		return new Rectangle(x,y,width,height);
	}

}
