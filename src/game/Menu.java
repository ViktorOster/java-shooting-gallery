package game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

import game.Game.STATE;

public class Menu extends MouseAdapter implements MouseListener {
	private Game game;
	private Handler handler;
	private int mx;
	private int my;
	
	private int velY;
	private int yPosition = 0; //for moving stars
	private int yPosition2 = -480;
	static int pressedKey = 0;
	static boolean pressedStart = false;
	
	private BufferedImage menuImage;
	private BufferedImage menuBlip;
	
	private BufferedImage game_over_image;
	private BufferedImage help_screen_image;
	private BufferedImage mapLoad;
	
	private boolean playSelected = false;
	private boolean helpSelected = false;
	private boolean quitSelected = false;
	private boolean toMenuSelected = false;

	private Rectangle playRect;
	
	public Menu(Game game, Handler handler){
		velY = 1;
		this.game = game;
		this.handler = handler;
		BufferedImageLoader loader = new BufferedImageLoader();
		//AudioPlayer.getSound("introLick").play();
		
		//menuImage = loader.loadImage("/AlienMenu.png");
		//mapLoad = loader.loadImage("/room1Load.png");

		
		
	}
	
	
	public void tick(){

		if(pressedStart){
			if(pressedKey == 0) {
				AudioPlayer.getSound("selectSound").play();
				//AudioPlayer.getSound("selectSound").stop();
				//handler.addObject(new Player(Game.WIDTH/2-20, Game.HEIGHT/2-60, ID.Player, handler));
				
				game.loadImageLevel(mapLoad);
				HUD.clearScore();
				game.gameState = STATE.Game;
				//game.gameState = STATE.Game;

				//AudioPlayer.getMusic("music").loop(); 
				//AudioPlayer.getMusic("menu_music").stop();
				
			}
			if(pressedKey == 1) game.gameState = STATE.Help;
		}		
	}
	
	public void render(Graphics g){
		g.setColor(Color.black);
		g.fillRect(0, 0, (int)Game.WIDTH, (int)Game.HEIGHT);
				
		if(game.gameState == STATE.Menu){
			g.drawImage(menuImage, 0, 0, null);
			if(pressedKey == 0) g.drawImage(menuBlip, 320, 325, null);
			if(pressedKey == 1) g.drawImage(menuBlip, 320, 350, null);
			
		} else if (game.gameState == STATE.Help) {
			
			/*
			g.setColor(Color.white);
			Font fnt = new Font("arial", 1 , 50);
			Font fnt2 = new Font("arial", 1 , 25);
			Font fnt3 = new Font("arial", 1 , 20);
			g.setFont(fnt);
			g.drawString("Help", 254, 70);
			g.drawRect(20, 400, 100, 32);
			g.setFont(fnt2);
			g.drawString("Back", 38, 425);
			g.setFont(fnt3);
			g.drawString("Use WASD keys to control the ship, and space to shoot.", 50, 130);
			g.drawString("Avoid enemies, and enemy bullets and see how far you can get.", 18, 160);
			*/
			
			
		} else if (game.gameState == STATE.GameOver){
			/*
			g.setColor(Color.white);
			Font fnt = new Font("arial", 1 , 50);
			Font fnt2 = new Font("arial", 1 , 20);
			Font fnt3 = new Font("arial", 1 , 15);
			g.setFont(fnt);
			g.drawString("GAME OVER", 190, 70);
			g.setFont(fnt3);
			g.drawString(HUD.points, 220, 180);
			g.drawRect(20, 400, 100, 32);
			g.setFont(fnt2);
			g.drawString("To Menu", 30, 425);
			*/
			Font fnt = new Font("arial", 1 , 30);
			g.setColor(Color.orange);
			g.drawImage(game_over_image, 0, 0, null);
			g.setFont(fnt);
			g.drawString("" + HUD.points, 300, 195);
			
			
		} 
	}

	
}
