package Tutu;

import java.awt.Graphics;
import java.util.LinkedList;

public class Handler {
	
	LinkedList<GameObject> object = new LinkedList<GameObject>();
	public static boolean visible;
	
	
	public Handler(){
		visible=false;
	}
	
	public void tick(){
		if(visible){
			for(int i = 0; i<object.size(); i++){
				GameObject tempObject = object.get(i);
				
				tempObject.tick();
			}
		}
	}
	
	public void render(Graphics g){
		if(visible){
			for(int i = 0; i<object.size(); i++){
				GameObject tempObject = object.get(i);
				
				tempObject.render(g);
			}
		}
	}
	
	public void addObject(GameObject object){
		this.object.add(object);
	}
	
	public void removeObject(GameObject object){
		this.object.remove(object);
	}
	
	public void setVisible(boolean x){
		this.visible=x;
	}
	
	public boolean getVisible(){
		return this.visible;
	}
		
}
