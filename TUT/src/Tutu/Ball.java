package Tutu;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Ball extends GameObject {
	
	private Handler handler;
	private boolean collisionSpoted;	
	

	public Ball(int x, int y, int width, int height, ID id,Handler handler) {
		super(x, y,width,height, id);		
		this.handler=handler;
		collisionSpoted = false;
			
	}
	

	
	public void tick() {
		if(GameObject.start){
			x+=velX;
			y+=velY;
			if(y <= 22) velY *= -1;
			if(x <= 0 || x >= Game.WIDTH- 10) velX *= -1;
			if(y >= Game.HEIGHT - 32){
				GameObject.stopGame();
				HUD.HEALTH--;
			}
			collision();
		}else if(HUD.HEALTH!=0){
			for(int i = 0;i<handler.object.size();i++){
				
				GameObject tempObject = handler.object.get(i);
				
				if(tempObject.getID() == ID.Player){
					
					x = tempObject.getX() + tempObject.getWidth()/2-6;
				}
				
			}
			y=Game.HEIGHT - 102;
		}
		else {
			HUD.gameOver=true;
			handler.object.remove(this);
			
		}
		
	}
	
	private void collision() {
		for(int i = 0;i<handler.object.size();i++){
			
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getID() == ID.Player){
				
				if(getBounds().intersects(tempObject.getBounds())){
					
					velY*=-1;
					int barMiddle = tempObject.getWidth()/2 + tempObject.getX();
					if(x<barMiddle-tempObject.getWidth()/4){
						if(velX>=-6)velX-=2;
					}
					else if(x<barMiddle){
						if(velX>=-6)velX-=1;
					}
					else if(x<barMiddle+tempObject.getWidth()/4){
						if(velX<=6) velX+=1;
					}
					else if(x<barMiddle+tempObject.getWidth()){
						if(velX<=6) velX+=2;
					}				
					
				}
			}
			if((tempObject.getID() == ID.Brick)&&collisionSpoted==false){
				
				if(getBounds().intersects(tempObject.getBounds())){
					int topBrick = tempObject.getY()+2;
					int botBrick = tempObject.getY()+tempObject.getHeight()-2;
					
					int topBall = this.getY()+1;
					int botBall = this.getY()+this.getHeight()-1;
					
					if(botBall>topBrick&&topBall<botBrick){
						velX*=-1;
						collisionSpoted = true;
					}				
					else {
						velY*=-1;
						collisionSpoted = true;
					}
				}
			}
			
		}
		collisionSpoted=false;
		
	}

	
	public void render(Graphics g) {		
		
		g.setColor(Color.MAGENTA);
		g.fillOval(x, y,width,height);
		
		
	}

	public Rectangle getBounds() {
		
		return new Rectangle(x,y,width,height);
	}

}
