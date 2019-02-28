package game;

import java.awt.Color;
import java.util.Random;

public class Spawn {
	private Handler handler;
	private HUD hud;
	private Random r = new Random();
	
	static int counterGlobal = 0;
	private int topSpawn;
	private int midSpawn;
	private int lowSpawn;
	
	private int deadCount = 0;
	private int randInt;
	private int randX;
	private int randY;
	
	public Spawn(Handler handler, HUD hud){
		this.handler = handler;
		this.hud = hud;
	}
	public static void clearSpawn(){
		counterGlobal = 0;

		
	}
	
	public void tick(){
		
		
		if(topSpawn > 50) topSpawn = 0;
		if(midSpawn > 100) midSpawn = 0;
		if(lowSpawn > 200) lowSpawn = 0;
		
		if(counterGlobal == 1) {
			//handler.addObject(new StaticObject(400, 400, ID.StaticObject, "barrel", handler));
			
			
			
		}

		topSpawn++;
		midSpawn++;
		lowSpawn++;
		
		counterGlobal++;
		if(topSpawn == 10) {
			handler.addObject(new Target(-32, 73, ID.Target, "top", handler));
			
		}
		if(midSpawn == 10) {
			handler.addObject(new Target(Game.WIDTH+32, 158, ID.Target, "mid", handler));
			
		}
		if(lowSpawn == 10) {
			handler.addObject(new Target(-32, 240, ID.Target, "low", handler));
			
		}
		
		
	}

	
}
