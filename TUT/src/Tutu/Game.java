package Tutu;

import java.awt.Canvas;
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
	private BufferedImage backgroundImg;
	private BufferedImage backgroundMenu;
	
	private Handler handler;
	private HUD hud;
	private Menu menu;
	
	
	public Game() throws IOException{
		
		backgroundImg = ImageIO.read(new File("data/background.jpg"));
		backgroundMenu = ImageIO.read(new File("data/menu.jpg"));
		
		handler = new Handler();
		this.addKeyListener(new KeyInput(handler));
		
		new Window(WIDTH,HEIGHT,"Arkanoid ", this);
		
		hud = new HUD();
		menu = new Menu();
		
		handler.addObject(new Player(WIDTH/2 -32,HEIGHT - 90, 100,12,ID.Player,handler));
		handler.addObject(new Ball(WIDTH/2 -32,HEIGHT - 102,12,12,ID.Ball,handler));
		
		new Level(handler);
		
		
		
		
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
		menu.tick();
		
	}

	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		
		if(bs==null){
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		
		if(handler.getVisible()) g.drawImage(backgroundImg, 0, 0, null);
		if(Menu.visible) g.drawImage(backgroundMenu,0,0,null);
//		g.setColor(Color.WHITE);
//		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		
		handler.render(g);
		hud.render(g);
		menu.render(g);
		
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
	
	public void stopGame(){
		running=false;
	}

	public static void main(String[] args) throws IOException {
		new Game();
	}
}
