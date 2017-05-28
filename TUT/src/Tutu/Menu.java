package Tutu;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Menu {
	public static boolean visible;
	public BufferedImage background;
	public static int menuPosition;
	public static boolean enter;
	public static boolean stop;
	
	public Menu(){
		visible = true;
		menuPosition = 0;
		enter = false;
				
	}
	
	
	public void tick(){
		if(enter==true&&menuPosition==0){
			Menu.visible=false;
			HUD.visible=true;
			Handler.visible=true;
		}
		if(enter==true&&menuPosition==1){
			System.exit(0);
		}
	}
	
	public void render(Graphics g){
		if(visible){
			g.drawImage(background, 0 , 0, null);
			g.setColor(Color.lightGray);
			g.setFont(new Font("Impact", Font.PLAIN, 40));
			if(menuPosition==0) g.setColor(Color.yellow);
			g.drawString("PLAY", Game.WIDTH/2-50, 100);
			if(menuPosition==0) g.setColor(Color.lightGray);
			
			if(menuPosition==1) g.setColor(Color.yellow);
			g.drawString("QUIT", Game.WIDTH/2-50, 200);
			if(menuPosition==1) g.setColor(Color.lightGray);
			
		}
	}
}
