package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Block extends GameObject{
	
	private Handler handler;
	
	private int sizeX;
	private int sizeY;
	private GameObject player;
	
	private BufferedImage block;

	private String type;
	
	private int collideCount = 0;
	private boolean collide = false;
	
	
	public Block(float x, float y, float w, float h, ID id, String typ, Handler handler) { //1= L, 2 = down, 3 = R, 4 = up, 5 = L/DOWN, 6 = R/DOWN 7= UP/L, 8= UP/R
		super(x, y, id);
		this.handler = handler;
		this.width = w;
		this.height = h;
		
		this.type = typ;


	}

	public void tick() {

		//collision();
		
	}
	private void collision(){
		for(int i = 0; i < handler.object.size(); i++){
			GameObject tempObject = handler.object.get(i);
			if(tempObject.getId() == ID.PlayerBullet || tempObject.getId() == ID.PlayerFlame){
				if(getBounds().intersects(tempObject.getBounds())){	
					
					handler.removeObject(tempObject);
	
				}
			}				
		}
	}
	
	@Override
	public void drawShadow(Graphics g){
	
		
	}
	
	public Rectangle getBounds() {

		return new Rectangle((int)x, (int)y, (int)width, (int)height);		
	}
	
	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(Color.blue);

		//g.setColor(Color.green);

		//g.drawImage(block, (int)x, (int)y, null);

		g2d.draw(getBounds());
	}	
}
	
