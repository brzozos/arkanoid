package Tutu;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class PowerUp extends GameObject {
	
	public Handler handler;

	public PowerUp(int x, int y, int width, int height, ID id,Handler handler) {
		super(x, y, width, height, id);
		this.handler=handler;
		// TODO Auto-generated constructor stub
	}

	public void tick() {
		this.y++;
		collision();
		
	}

	public void render(Graphics g) {
		g.setColor(Color.white);
		g.fillRect(x, y, width, height);
		
	}

	public Rectangle getBounds() {
		// TODO Auto-generated method stub
		return new Rectangle(x,y,width,height);
	}
	
	private void collision(){
		for(int i = 0;i<handler.object.size();i++){
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getID()==ID.Player){
				if(getBounds().intersects(tempObject.getBounds())){
					handler.object.remove(this);
					tempObject.setWidth(tempObject.getWidth()+50);
				}
			}
		
		}
	}

}
