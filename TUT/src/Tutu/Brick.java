package Tutu;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;


public class Brick extends GameObject {
	
	Handler handler;
	private int life;
	private BufferedImage img;
	private BufferedImage[] tImg;
	private Random random;

	public Brick(int x, int y, int width, int height, int life,BufferedImage[] tImg, ID id,Handler handler) {
		super(x, y, width, height, id);
		this.handler=handler;
		this.life = life;
		this.tImg = tImg;
		this.img = this.tImg[this.life-1];
		this.random = new Random();
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
						if(random.nextInt(10)==1) handler.object.add(new PowerUp(this.x,this.y,10,10,ID.ExtendPlayer,handler));
					}
					if(life==1){			   
											
						this.img = tImg[life-1];			
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
