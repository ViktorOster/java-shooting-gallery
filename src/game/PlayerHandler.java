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

public class PlayerHandler extends MouseAdapter implements MouseListener {
	private Game game;
	private Handler handler;
	private int mx;
	private int my;
	
	
	
	private BufferedImage crosshair;

	public PlayerHandler(Game game, Handler handler){
		
		this.game = game;
		this.handler = handler;
		BufferedImageLoader loader = new BufferedImageLoader();
	
		crosshair = loader.loadImage("/crosshair.png");


	}
	
	public void mousePressed(MouseEvent e){
		
		
		mx = e.getX();
		my = e.getY();
		
		if(HUD.AMMO >0){
			HUD.AMMO -= 1;
			AudioPlayer.getSound("shoot").play(1, 23);
			//check if over object bounding box when pressed -> do things
			for(int i = 0; i < handler.object.size(); i++){
				GameObject tempObject = handler.object.get(i);
				if(!tempObject.isDead()){
					if(tempObject.getId() == ID.Target){
						if(getBounds().intersects(tempObject.getBounds())){
							AudioPlayer.getSound("score").play(1, 23);
							tempObject.trueHit();
						}
					}
						
				}
				
			}
		}
		
	}
	@Override
	public void mouseMoved(MouseEvent e){
		mx = e.getX();
		my = e.getY();
		
		
	}
	public Rectangle getBounds() {
		return new Rectangle((int)mx, (int)my, 2, 2);
	}
	
	public void mouseReleased(MouseEvent e){
		//System.out.println("released");
	}
	
	private boolean mouseOver(int mx, int my, int x, int y, int width, int height){
		if(mx > x && mx < x + width){
			if(my > y && my < y + height){
				return true;
			} else return false;
		} else return false;
	}
	
	public void tick(){
		//System.out.println("" + mx + ", " + my);
	}
	
	public void render(Graphics g){
		g.drawImage(crosshair, (int)mx-(crosshair.getWidth()/2), (int)my-(crosshair.getHeight()/2), null);
		
	}

	
}
