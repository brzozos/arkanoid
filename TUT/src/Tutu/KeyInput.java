package Tutu;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter{
	
	private Handler handler;
	private boolean[] menuPressed = new boolean[3]; 
	
	public KeyInput(Handler handler){
		this.handler =handler;
		this.menuPressed[0] = false;
		this.menuPressed[1] = false;
		this.menuPressed[2] = false;
		
	}
	
	public void keyPressed(KeyEvent e){
		int key = e.getKeyCode();
			
		for(int i = 0;i < handler.object.size();i++){
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getID()==ID.Player){
				
				if(key==KeyEvent.VK_LEFT) tempObject.setVelX(-6);
				if(key==KeyEvent.VK_RIGHT) tempObject.setVelX(6);	
				if(key==KeyEvent.VK_Q){	
					tempObject.setWidth((int)(tempObject.getWidth()*1.5));
					tempObject.setX(tempObject.getX()-(int)((tempObject.getWidth())/6));
				}
				if(key==KeyEvent.VK_A){	
					tempObject.setWidth((int)(tempObject.getWidth()*0.75));
					tempObject.setX(tempObject.getX()+(int)((tempObject.getWidth())/6));
				}
				
			}
			if(tempObject.getID()==ID.Ball){
				if(key==KeyEvent.VK_SPACE&& !GameObject.getStart()){
					tempObject.setVelX(-4);
					tempObject.setVelY(-4);
					GameObject.startGame();
				}
			}
									
		}
		if(key==KeyEvent.VK_UP){
			if(Menu.menuPosition!=0)Menu.menuPosition--;
			this.menuPressed[1] = true;
			
		}
		if(key==KeyEvent.VK_DOWN){
			if(Menu.menuPosition!=1)Menu.menuPosition++;
			this.menuPressed[2] = true;			
		}
		if(key==KeyEvent.VK_ENTER){
			Menu.enter=true;
		}
	}
	public void keyReleased(KeyEvent e){
		int key = e.getKeyCode();
		for(int i = 0;i < handler.object.size();i++){
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getID()==ID.Player){
				
				if(key==KeyEvent.VK_LEFT) tempObject.setVelX(0);
				if(key==KeyEvent.VK_RIGHT) tempObject.setVelX(0);			
				
			}			
		}
		if(key==KeyEvent.VK_UP){
			this.menuPressed[1] = false;
			
		}
		if(key==KeyEvent.VK_DOWN){
			this.menuPressed[2] = false;
			
		}
		if(key==KeyEvent.VK_ENTER){
			Menu.enter=false;	
		}
		
		
		
	}

}
