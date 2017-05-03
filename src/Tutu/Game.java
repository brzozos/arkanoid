package Tutu;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Game extends Canvas implements Runnable {
	
	private static final long serialVersionUID = 1L;
	
	public static final int WIDTH =640, HEIGHT = WIDTH / 12 * 9;
	public static final int BRICKWIDTH =48, BRICKHEIGHT = 24;
	
	private Thread thread;
	private boolean running = false;
	

	private Handler handler;
	private HUD hud;
	private BufferedImage imgRed;
	private BufferedImage imgBlue;

	
	
	
	public Game(){
		handler = new Handler();
		this.addKeyListener(new KeyInput(handler));
		
		new Window(WIDTH,HEIGHT,"game", this);
		
		hud = new HUD();
		
		handler.addObject(new Player(WIDTH/2 -32,HEIGHT - 90, 100,16,ID.Player,handler));
		handler.addObject(new Ball(WIDTH/2 -32,HEIGHT - 102,12,12,ID.Ball,handler));
		
		
		try {
			imgRed = ImageIO.read(new File("data/RED_BRICK.jpg"));
			imgBlue = ImageIO.read(new File("data/BLUE_BRICK.jpg"));
			
		} catch (IOException e){
			
		}
		for(int i = 0; i < 12; i++){
			handler.addObject(new Brick(10+i*(BRICKWIDTH+2),BRICKHEIGHT+2,BRICKWIDTH, BRICKHEIGHT,1,imgBlue, ID.Brick,handler));
		}
		for(int i = 0; i < 11; i++){
			handler.addObject(new Brick(10+(BRICKWIDTH/2)+i*(BRICKWIDTH+2),(BRICKHEIGHT+2)*2,BRICKWIDTH, BRICKHEIGHT,2,imgRed, ID.Brick,handler));
		}
		for(int i = 0; i < 12; i++){
			handler.addObject(new Brick(10+i*(BRICKWIDTH+2),(BRICKHEIGHT+2)*3,BRICKWIDTH, BRICKHEIGHT,1,imgBlue, ID.Brick,handler));
		}
		for(int i = 0; i < 11; i++){
			handler.addObject(new Brick(10+(BRICKWIDTH/2)+i*(BRICKWIDTH+2),(BRICKHEIGHT+2)*4,BRICKWIDTH, BRICKHEIGHT,2,imgRed, ID.Brick,handler));
		}
		for(int i = 0; i < 12; i++){
			handler.addObject(new Brick(10+i*(BRICKWIDTH+2),(BRICKHEIGHT+2)*5,BRICKWIDTH, BRICKHEIGHT,1,imgBlue, ID.Brick,handler));
		}
		for(int i = 0; i < 11; i++){
			handler.addObject(new Brick(10+(BRICKWIDTH/2)+i*(BRICKWIDTH+2),(BRICKHEIGHT+2)*6,BRICKWIDTH, BRICKHEIGHT,2,imgRed, ID.Brick,handler));
		}
		
	}

	public synchronized void start(){
		thread = new Thread(this);
		thread.start();
		running = true;
	}
	public synchronized void stop(){
		try {
			thread.join();
			running = false;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public void run(){
		this.requestFocus();
		long lastTime = System.nanoTime();
		double amountOfTicks = 50.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		while(running){
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while(delta >= 1){
				tick();
				delta--;
			}
			if(running)
				render();
			frames++;
			
			if(System.currentTimeMillis() - timer > 1000){
				timer += 1000;
				System.out.println("FPS: " + frames);
				frames = 0;
			}
		}
		stop();
	}
	
	private void tick() {
		handler.tick();
		hud.tick();
		
	}

	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		
		if(bs==null){
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		
		handler.render(g);
		hud.render(g);
		
		g.dispose();
		bs.show();
		
	}
	
	public static int clamp(int var, int min, int max){
		if(var >=max)
			return var = max;
		else if(var <=min)
			return var = min;
		else return var;
	}

	public static void main(String[] args) {
		new Game();
	}
}
