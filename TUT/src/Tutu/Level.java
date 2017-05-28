package Tutu;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Level {
	private Handler handler;
	private BufferedImage imgRedBrick[] = new BufferedImage[2];
	private BufferedImage imgBlueBrick[] = new BufferedImage[1];
	
	public Level(Handler handler){
		this.handler = handler;
		newLevel();
	}
	
	public void newLevel(){
		try {
			imgRedBrick[1] = ImageIO.read(new File("data/RED_BRICK.png"));
			imgRedBrick[0] = ImageIO.read(new File("data/RED_BROKEN_BRICK.png"));			
			imgBlueBrick[0] = ImageIO.read(new File("data/BLUE_BRICK.png"));			
		} catch (IOException e){
			
		}
		for(int j=0;j<4;j++){		
			for(int i = 0; i < 12;i++){
				handler.addObject(new Brick(18+i*(Game.BRICKWIDTH+2),(Game.BRICKHEIGHT+2)*j*2+25,Game.BRICKWIDTH, Game.BRICKHEIGHT,1,imgBlueBrick, ID.Brick,handler));
			}
		}
		for(int j=0;j<4;j++){			
			for(int i = 0; i < 11;i++){
				handler.addObject(new Brick(18+(Game.BRICKWIDTH/2)+i*(Game.BRICKWIDTH+2),(Game.BRICKHEIGHT+2)*(2+j*2),Game.BRICKWIDTH, Game.BRICKHEIGHT,2,imgRedBrick, ID.Brick,handler));	
			}
		}
		
	}
}
