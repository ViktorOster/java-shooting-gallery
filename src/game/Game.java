package game;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import game.Game.STATE;


public class Game extends Canvas implements Runnable{
	

	private static final long serialVersionUID = -4584388369897487885L;
	
	public static final float WIDTH = 768, HEIGHT = 608;
	private Thread thread;
	private Handler handler;
	private boolean running = false;
	private HUD hud;
	private Spawn spawner;
	private int velY;
	private int velY2;
	private int yPosition = 0;
	private int yPosition2 = -640;
	static boolean testRange = true;
	
	Camera cam;
	public static BufferedImage testBlock;
	public static BufferedImage testShadow;
	
	public static BufferedImage mapImage;
	public static BufferedImage mapImageB;
	public static BufferedImage megaNukeLayer;
	public static BufferedImage megaNukeLayer2;
	public static BufferedImage section1Screen;
	public static BufferedImage darkOverlay;
	public static BufferedImage darkOverlayScreen;
	public static BufferedImage roomLight;
	
	//private BufferedImage mapLoad = null;
	
	private int animCount = 0;
	
	private Random r;
	private Menu menu;
	private PlayerHandler playerHandler;
	
	//private int mapScroll = -5985;
	
	public enum STATE {
		Menu,
		Help,
		GameOver,
		Game,
		Section
	};
	
	static STATE gameState = STATE.Game;
	
	public Game(){
		
		
		AudioPlayer.load();
		velY = 1;
		velY2 = 3;
		
		handler = new Handler();
		this.addKeyListener(new KeyInput(handler));	
		
		menu = new Menu(this, handler);
		this.addMouseListener(menu);
		this.addMouseMotionListener(menu);
		
		playerHandler = new PlayerHandler(this, handler);
		this.addMouseListener(playerHandler);
		this.addMouseMotionListener(playerHandler);

		hud = new HUD();
		hud.loadHudImages();
		spawner = new Spawn(handler, hud);
		
		new Window((int)WIDTH, (int)HEIGHT, "Alien Plague", this);
		BufferedImageLoader loader = new BufferedImageLoader();
		cam = new Camera(0, 0);
		
		//handler.addObject(new Player(70, 150, ID.Player, handler));
		
		
		
		
		if(gameState == STATE.Game){

		}
		

	}

	public synchronized void start(){
		thread = new Thread(this);
		thread.start();		running = true;
	}
	
	public synchronized void stop(){
		try{
			thread.join();
			running = false;
		} catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void run(){
		this.requestFocus();
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
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
				//System.out.println("FPS: " + frames);
				frames = 0;
			}
			
		}
		stop();
	}
	
	public void loadImageLevel(BufferedImage image){
		int w = image.getWidth();
		int h = image.getHeight();
		for(int i = 0; i < h; i++){
			for(int j = 0; j < w; j++){
				int pixel = image.getRGB(i, j);
				int red = (pixel >> 16) & 0xff;
				int green = (pixel >> 8) & 0xff;
				int blue = (pixel) & 0xff;
				
				//if(red == 255 && green == 255 && blue == 255) handler.addObject(new Block(i*32, j*32, 32, 32, ID.Block, "default", handler));
				
				
			}
		}

	}

	private void render() {
	
		
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null){
			this.createBufferStrategy(3);
			return;
		}
		Graphics g = bs.getDrawGraphics();
		Graphics2D g2d = (Graphics2D) g;
		
		
		g.setColor(Color.black);
		g.fillRect(0, 0, (int)WIDTH, (int)HEIGHT);
		
		if(gameState == STATE.GameOver){
			//g.drawImage(slimeRedBgDark, 0, yPositionOverlay, this);
			//g.drawImage(slimeRedBgDark, 0, yPositionOverlay2, this);
		}
		
		
		if(gameState == STATE.Game){
			//g2d.translate(cam.getX(), cam.getY());
			
			//if(animCount >= 0) g.drawImage(mapImage, 0, 0, this);
				
			//if(animCount >= 7 && animCount <13) g.drawImage(mapImageB, 0, 0, this);

			for(int i = 0; i < handler.object.size(); i++){
				GameObject tempObject = handler.object.get(i);

				if(tempObject.getId() == ID.TankEnemy
						|| tempObject.isDead()){
					//tempObject.drawShadow(g);	
					tempObject.render(g);
				}
				
			}

			for(int i = 0; i < handler.object.size(); i++){
				GameObject tempObject = handler.object.get(i);
				if(tempObject.getId() != ID.TankEnemy){
					tempObject.render(g);
				}				
			}
			

			//g2d.translate(-cam.getX(), -cam.getY());
			

		} else if (gameState == STATE.Menu || gameState == STATE.Help || gameState == STATE.GameOver){
			menu.render(g);		
		}
		//g.drawImage(slimeBorder, 0, yPositionOverlay, this);
		//g.drawImage(slimeBorder, 0, yPositionOverlay2, this);
		
		
		if(gameState == STATE.Game) {
			hud.render(g);
			playerHandler.render(g);
		}
		if(Spawn.counterGlobal <= 100 && gameState == STATE.Game){
			g.drawImage(section1Screen, 0, 0, this);
		}
		
		g.dispose();
		bs.show();
	}

	private void tick() {
		if(Spawn.counterGlobal ==100){
			//AudioPlayer.getMusic("music").loop();
		}
		animCount++;
		if(animCount >= 13) animCount = 0;
		
		handler.tick();
		for(int i = 0; i < handler.object.size(); i++){
			GameObject tempObject = handler.object.get(i);
			if(tempObject.getId() == ID.Player){
				cam.tick(handler.object.get(i));
			}
		}
		
		if(gameState == STATE.Game){
			
			yPosition += velY;
			if(yPosition > 640){ 
				yPosition = -637;
			}
			
			yPosition2 += velY;
			
			if(yPosition2 > 640){
				yPosition2 = -637;
			}
			playerHandler.tick();
			hud.tick();
			spawner.tick();
		} else if(gameState == STATE.Menu || gameState == STATE.Help || gameState == STATE.GameOver){
			menu.tick();
		}
		if(gameState == STATE.GameOver){
			yPosition += velY;
			if(yPosition > 480){
				yPosition = -480;
			}
			
			yPosition2 += velY;
			
			if(yPosition2 > 480){
				yPosition2 = -480;
			}
		}	
			
	}
	
	public static float clamp(float var, float min, float max){
		if(var >= max){
			return var = max;
		}				
		else if(var <= min) {
			return var = min;	
		}
		else 
			return var;
	}
	
	
	public static void main(String[] args){
		new Game();

	}
	

}
