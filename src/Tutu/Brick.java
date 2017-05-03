package Tutu;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Brick extends GameObject {
	
	Handler handler;
	private int life;
	private BufferedImage img;

	public Brick(int x, int y, int width, int height, int life,BufferedImage img, ID id,Handler handler) {
		super(x, y, width, height, id);
		this.handler=handler;
		this.life = life;
		this.img = img;		
		
	}

	
	public void tick() {
		collision();
		
	}
	
	private void collision() {
		for(int i = 0;i<handler.object.size();i++){
			
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getID() == ID.Ball){
				
				if(getBounds().intersects(tempObject.getBounds())){
					
					life--;
					if(life==0){
						HUD.score+=2;
						handler.object.remove(this);
					}
					if(life==1){						   
						try {							
							img = ImageIO.read(new File("data/RED_BROKEN_BRICK.jpg"));							
						} catch (IOException e){}						
						HUD.score++;
					}
				}
			}
		}
		
	}

	
	public void render(Graphics g) {
		
		
		g.drawImage(img, x, y, null);
		
		
	}

	
	public Rectangle getBounds() {
		
		return new Rectangle(x,y,width,height);
	}

}
