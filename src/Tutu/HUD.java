package Tutu;

import java.awt.Color;
import java.awt.Graphics;

public class HUD {
	
	public static int HEALTH = 3;
	public static int score=0;
	public static boolean gameOver = false;
	
	public void tick(){		
		HEALTH = Game.clamp(HEALTH, 0, 3);		
		
	}
	
	public void render(Graphics g){
		for(int i = 0; i<HEALTH;i++){
			g.setColor(Color.green);
			g.fillRect(22*i, 0, 20, 20);
			g.setColor(Color.white);
			g.drawRect(22*i, 0, 20, 20);
		}
		g.setColor(Color.BLACK);
		g.drawString("Score: "+ score, 22*3+10, 13);
		if(gameOver==true){
			g.setColor(Color.BLACK);
			g.drawString("GAME OVER", Game.WIDTH/2-50, Game.HEIGHT/2);
		}
	}
}
