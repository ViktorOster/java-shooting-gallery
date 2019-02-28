package game;

import java.awt.Graphics;
import java.awt.image.ImageObserver;
import java.util.LinkedList;

import javax.swing.JOptionPane;

import game.Game.STATE;

public class Handler {

	private int deadTimer = 0;
	
	LinkedList<GameObject> object = new LinkedList<GameObject>();
	
	public void tick(){
		
		if(HUD.TIME <= 0){
			
			while(HUD.TIME <= 0){
				String input = JOptionPane.showInputDialog("Time's up, play again? (Y/N)");
				if(input.equals("Y")){
					clearEverything();
					Game.gameState = STATE.Game;
				} else System.exit(0);
				
			}
			
		} 
		
		for(int i = 0; i < object.size(); i++){
			
			GameObject tempObject = object.get(i);
			
			tempObject.tick();
		}			
	}
	
	public void render(Graphics g){

		for(int i = 0; i < object.size(); i++){
			
			
			GameObject tempObject = object.get(i);
			
			tempObject.render(g);
		}	
		
	}
	
	public void addObject(GameObject object){
		this.object.add(object);
	}
	
	public void removeObject(GameObject object){
		this.object.remove(object);
	}

	public void clearEnemies(){
		object.clear();
	}
	
	public void clearEverything(){
		object.clear();
		//Menu menu = new Menu(game, handler);
		//Game.gameState = STATE.GameOver;
		HUD.TIME = 60;
		HUD.points = 0;
		HUD.AMMO = 100;
		Spawn.clearSpawn();
		HUD.clearScore();
		//AudioPlayer.getMusic("music").stop();
		//AudioPlayer.getMusic("game_over").loop();
	}
	
}
