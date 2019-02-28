package game;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Target extends GameObject{
	private GameObject player;
	private Handler handler;

	private float health = 100;
	private float hitCount;
	
	
	private BufferedImage targetImage;
	private BufferedImage destroy1;
	private BufferedImage destroy2;
	private BufferedImage destroy3;
	
	private BufferedImage hitBox;
	
	private int releasedDir;
	private int deadCount;
	
	Random r = new Random();

	private int animCount;
	private String shelf;
	private int stopCount;
	private int randInt;
	private String type;
	
	public Target(float x, float y, ID id, String sh, Handler handler) {
		
		super(x, y, id);
		this.handler = handler;
		this.shelf = sh;
		dead = false;
	
		BufferedImageLoader loader = new BufferedImageLoader();

		randInt = r.nextInt(3);
		
		hit = false;
		
		
		
		if(shelf.equals("top")){
			
			if(randInt == 0) {
				targetImage = loader.loadImage("/smiley.png");
				hitBox = loader.loadImage("/box_smiley.png");
				destroy1 = loader.loadImage("/smiley_dest1.png");
				destroy2 = loader.loadImage("/smiley_dest2.png");
				type = "smiley";
			}
			else if(randInt == 1) {
				targetImage = loader.loadImage("/mug.png");
				hitBox = loader.loadImage("/box_mug.png");
				destroy1 = loader.loadImage("/mug_dest1.png");
				destroy2 = loader.loadImage("/mug_dest2.png");
				type = "mug";
			}
			else if(randInt == 2) {
				targetImage = loader.loadImage("/bball.png");
				hitBox = loader.loadImage("/box_bball.png");
				destroy1 = loader.loadImage("/bball_dest1.png");
				destroy2 = loader.loadImage("/bball_dest2.png");
				type = "bball";
			}
			
			velX = 6;
		}
		else if(shelf.equals("mid")){
			
			if(randInt == 0) {
				targetImage = loader.loadImage("/rduck.png");
				hitBox = loader.loadImage("/box_rduck.png");
				destroy1 = loader.loadImage("/rduck_dest1.png");
				destroy2 = loader.loadImage("/rduck_dest2.png");
				type = "rduck";
			}
			else if(randInt == 1) {
				targetImage = loader.loadImage("/can.png");
				hitBox = loader.loadImage("/box_can.png");
				destroy1 = loader.loadImage("/can_dest1.png");
				destroy2 = loader.loadImage("/can_dest2.png");
				type = "can";
			}
			else if(randInt == 2) {
				targetImage = loader.loadImage("/bottle.png");
				hitBox = loader.loadImage("/box_bottle.png");
				destroy1 = loader.loadImage("/bottle_dest1.png");
				destroy2 = loader.loadImage("/bottle_dest2.png");
				destroy3 = loader.loadImage("/bottle_dest3.png");
				type = "bottle";
			}
			
			velX = -4;
		}
		else if(shelf.equals("low")){
			
			if(randInt == 0) {
				targetImage = loader.loadImage("/can.png");
				hitBox = loader.loadImage("/box_can.png");
				destroy1 = loader.loadImage("/can_dest1.png");
				destroy2 = loader.loadImage("/can_dest2.png");
				type = "can";
			}
			else if(randInt == 1) {
				targetImage = loader.loadImage("/candle.png");
				hitBox = loader.loadImage("/box_candle.png");
				destroy1 = loader.loadImage("/candle_dest1.png");

				
				type = "candle";
			}
			else if(randInt == 2) {
				targetImage = loader.loadImage("/bottle.png");
				hitBox = loader.loadImage("/box_bottle.png");
				destroy1 = loader.loadImage("/bottle_dest1.png");
				destroy2 = loader.loadImage("/bottle_dest2.png");
				destroy3 = loader.loadImage("/bottle_dest3.png");
				type = "bottle";
			}
			
			velX = 2;
		} 
		
	}

	public void tick() {
		
		if(dead) deadCount++;

		animCount++;
		
		if(animCount >12) animCount = 0;

		if(hit){
			hitCount++;
			if(hitCount == 2){
				health -= 100;
			}
			
		}

		x += velX;
		
		//if(y >= Game.HEIGHT*2+100) handler.removeObject(this);
		
		if(x <= -50 || x >= Game.WIDTH + 50) handler.removeObject(this);
		
		if(health <= 0){
			dead = true;
			if(deadCount == 1){
				
				
				//handler.addObject(new Explosion(x-7, y+20, ID.Explosion, "monster", handler));
				
				if(type.equals("candle")) HUD.points = HUD.points + 25;
				else if(type.equals("can")) HUD.points = HUD.points + 10;
				else if(type.equals("smiley")) HUD.points = HUD.points + 10;
				else if(type.equals("bball")) HUD.points = HUD.points + 10;
				else if(type.equals("bottle")) HUD.points = HUD.points + 25;
				else if(type.equals("mug")) HUD.points = HUD.points + 10;
				else if(type.equals("rduck")) HUD.points = HUD.points + 10;
				
				/*
				if(shelf.equals("top")) HUD.points = HUD.points + 50;
				else if(shelf.equals("mid")) HUD.points = HUD.points + 25;
				else if(shelf.equals("low")) HUD.points = HUD.points + 10;
				*/
				
				//AudioPlayer.getSound("manDie").play();

			}	
		}
		
		
	} 
	public Rectangle getBoundsBig() {
		
		return new Rectangle((int)x, (int)y, targetImage.getWidth(), targetImage.getHeight());
		
	}
	public Rectangle getBounds() {
		if(type.equals("smiley")) return new Rectangle((int)x, (int)y+(targetImage.getHeight()-hitBox.getHeight()), hitBox.getWidth(), hitBox.getHeight());
		else if(type.equals("mug")) return new Rectangle((int)x+4, (int)y+(targetImage.getHeight()-hitBox.getHeight()), hitBox.getWidth(), hitBox.getHeight());
		else if(type.equals("bball")) return new Rectangle((int)x+5, (int)y+(targetImage.getHeight()-hitBox.getHeight()), hitBox.getWidth(), hitBox.getHeight());
		else if(type.equals("rduck")) return new Rectangle((int)x+5, (int)y+(targetImage.getHeight()-hitBox.getHeight()), hitBox.getWidth(), hitBox.getHeight());
		else if(type.equals("can")) return new Rectangle((int)x+12, (int)y+(targetImage.getHeight()-hitBox.getHeight()), hitBox.getWidth(), hitBox.getHeight());
		else if(type.equals("bottle")) return new Rectangle((int)x+14, (int)y+(targetImage.getHeight()-hitBox.getHeight()), hitBox.getWidth(), hitBox.getHeight());
		else if(type.equals("candle")) return new Rectangle((int)x+16, (int)y+(targetImage.getHeight()-hitBox.getHeight()), hitBox.getWidth(), hitBox.getHeight());
		else return new Rectangle((int)x, (int)y, 0, 0);
	}
	public void render(Graphics g) {

		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(Color.green);

		
		if(!dead){		
			g.drawImage(targetImage, (int)x, (int)y, null);
			//if(animCount >=0 && animCount <=6) g2d.drawImage(walkDown1, (int)x, (int)y, null);
			//if(animCount >=7 && animCount <=12) g2d.drawImage(walkDown2, (int)x, (int)y, null);
			
		} else{
		
			if(destroy1 != null){
				if(deadCount >=0 && deadCount <=3) g.drawImage(destroy1, (int)x, (int)y, null);
			}
			if(destroy2 != null){
				if(deadCount >=4 && deadCount <=7) g.drawImage(destroy2, (int)x, (int)y, null);
			} else{
				g.drawImage(destroy1, (int)x, (int)y, null);
			}
			if(destroy3 != null){
				if(deadCount >=8) g.drawImage(destroy3, (int)x, (int)y, null);
			
			} else if (!type.equals("smiley")){
				g.drawImage(destroy2, (int)x, (int)y, null);
			}
			
			
		}
		//g2d.draw(getBounds());
		
	}

}
