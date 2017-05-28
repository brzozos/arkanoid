package Tutu;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class HUD {
	
	public static int HEALTH = 3;
	public static int score=0;
	public static boolean gameOver = false;
	private BufferedImage lifeImg;
	public static boolean visible;
	
	public HUD(){
		try {
			lifeImg = ImageIO.read(new File("data/LIFE.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		HUD.visible=false;
	}
	
	public void tick(){		
		
		if(visible) HEALTH = Game.clamp(HEALTH, 0, 3);		
		
	}
	
	public void render(Graphics g){
		if(visible){
			for(int i = 0; i<HEALTH;i++){
				g.drawImage(lifeImg, 22*i, 2, null);
				
	//			g.setColor(Color.green);
	//			g.fillRect(22*i, 0, 20, 20);
	//			g.setColor(Color.BLACK);
	//			g.drawRect(22*i, 0, 20, 20);
			}
			g.setColor(Color.WHITE);
			g.drawString("Score: "+ score, 22*3+10, 13);
			if(gameOver==true){
				g.setColor(Color.WHITE);
				g.setFont(new Font("Impact", Font.PLAIN, 78));
				g.drawString("GAME OVER", Game.WIDTH/2-170, Game.HEIGHT/2+50);
			}
		}
			
	}
}
