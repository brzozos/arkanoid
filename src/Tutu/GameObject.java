package Tutu;

import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class GameObject {
	
	protected int x,y;
	protected ID id;
	protected int velX, velY;
	protected int height, width;
	protected static boolean start = false;
	
	public GameObject(int x, int y,int width, int height, ID id){
		this.x = x;
		this.y = y;
		this.id = id;
		this.width = width;
		this.height = height;
	}
	
	public abstract void tick();
	public abstract void render(Graphics g);
	public abstract Rectangle getBounds();
		
	public void setX(int x){
		this.x=x;
	}
	public void setY(int y){
		this.y=y;
	}
	public void setID(ID id){
		this.id=id;
	}
	public void setVelX(int velX){
		this.velX=velX;
	}
	public void setVelY(int velY){
		this.velY=velY;
	}
	public int getX(){
		return x;
	}
	public int getY(){
		return y;
	}
	public ID getID(){
		return id;
	}
	public int getVelX(){
		return velX;
	}
	public int getVelY(){
		return velY;
	}
	
	public static void startGame(){
		start=true;
	}
	public static void stopGame(){
		start=false;
	}
	
	public static boolean getStart(){
		return start;
	}
	
	public void setWidth(int width){
		this.width=width;
	}
	public void setHeight(int height){
		this.height=height;
	}
	public int getWidth(){
		return width;
	}
	public int getHeight(){
		return height;
	}
	
}
