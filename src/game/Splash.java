package game;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Splash extends GameObject {

	private Handler handler;
	
	private BufferedImage splash1;
	private BufferedImage splash2;
	private BufferedImage splash3;
	private BufferedImage splash4;
	
	private BufferedImage bulletSplash1;
	private BufferedImage bulletSplash2;
	
	private int animCount = 0;
	private String type;

	public Splash(float x, float y, ID id, String typ, Handler handler) {
		super(x, y, id);
		this.handler = handler;
		this.type = typ;
		
		BufferedImageLoader loader = new BufferedImageLoader();
		splash1 = loader.loadImage("/splash1.png");
		splash2 = loader.loadImage("/splash2.png");
		splash3 = loader.loadImage("/splash3.png");
		splash4 = loader.loadImage("/splash4.png");
		
		bulletSplash1 = loader.loadImage("/bulletSplash1.png");
		bulletSplash2 = loader.loadImage("/bulletSplash2.png");
		
	}

	@Override
	public void tick() {
		animCount++;
		if(animCount > 7) handler.removeObject(this);
		
		
	}

	@Override
	public void render(Graphics g) {
		if(type.equals("slime")){
			if(animCount >= 0 && animCount <= 2) g.drawImage(splash2, (int)x, (int)y, null);
			if(animCount >= 3 && animCount <= 5) g.drawImage(splash3, (int)x, (int)y, null);
			if(animCount >= 6 && animCount <= 7) g.drawImage(splash4, (int)x, (int)y, null);
		}
		if(type.equals("bullet")){
			if(animCount >= 0 && animCount <= 2) g.drawImage(bulletSplash1, (int)x, (int)y, null);
			if(animCount >= 3 && animCount <= 5) g.drawImage(bulletSplash2, (int)x, (int)y, null);

		}
	
		
	}

	@Override
	public Rectangle getBounds() {
		return null;
	}

}
