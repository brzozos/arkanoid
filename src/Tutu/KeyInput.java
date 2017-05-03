package Tutu;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter{
	
	private Handler handler;
	
	public KeyInput(Handler handler){
		this.handler =handler;
	}
	
	public void keyPressed(KeyEvent e){
		int key = e.getKeyCode();
			
		for(int i = 0;i < handler.object.size();i++){
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getID()==ID.Player){
				
				if(key==KeyEvent.VK_LEFT) tempObject.setVelX(-5);
				if(key==KeyEvent.VK_RIGHT) tempObject.setVelX(5);			
				
			}
			if(tempObject.getID()==ID.Ball){
				if(key==KeyEvent.VK_SPACE&& !GameObject.getStart()){
					tempObject.setVelX(-4);
					tempObject.setVelY(-4);
					GameObject.startGame();
				}
			}
									
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
		
	}

}
