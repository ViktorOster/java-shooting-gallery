package game;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


import game.Game.STATE;

public class KeyInput extends KeyAdapter{
	
	private Handler handler;
	private boolean[] keyDown = new boolean[4];
	static boolean spacePressed = false;
	static boolean secondaryFire = false;
	static int speedUp = 0;
	public KeyInput(Handler handler){
		this.handler = handler;
		
		keyDown[0] = false;
		keyDown[1] = false;
		keyDown[2] = false;
		keyDown[3] = false;
	}
	
	public void keyPressed(KeyEvent e){
		float key = e.getKeyCode();
		if(Game.gameState == STATE.Menu){
			if(key == KeyEvent.VK_DOWN) { 

				Menu.pressedKey = 1;
			}
			if(key == KeyEvent.VK_UP) { 

				Menu.pressedKey = 0;
			}
			if(key == KeyEvent.VK_ENTER) { Menu.pressedStart = true; }
			
		}
		

		for(int i = 0; i < handler.object.size(); i++){
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getId() == ID.Player){
				//key events player 1
				if(key == KeyEvent.VK_Z) {
				
				}
				
				
				
				
			}
		}
		
		if(key == KeyEvent.VK_ESCAPE) System.exit(1);
	}
	public void keyReleased(KeyEvent e){
		float key = e.getKeyCode();
		
		for(int i = 0; i < handler.object.size(); i++){
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getId() == ID.Player){
				//key events player 1
				if(key == KeyEvent.VK_Z){
					
				}
				
				
				if(key == KeyEvent.VK_UP) keyDown[0] = false; 
				if(key == KeyEvent.VK_DOWN) keyDown[1] = false; 
				if(key == KeyEvent.VK_LEFT) keyDown[2] = false; 				
				if(key == KeyEvent.VK_RIGHT) keyDown[3] = false; 
								
				//vertical movement
				if(!keyDown[0] && !keyDown[1]) tempObject.setVelY(0);
				
				//horizontal movement
				if(!keyDown[2] && !keyDown[3]) tempObject.setVelX(0);
			}
		}
	}
}
