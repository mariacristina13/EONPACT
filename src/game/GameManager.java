package game;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JPanel;

import Sprites.Player;
import constants.Constants;

public class GameManager {
    private Set<Integer> keysHeld = new HashSet<>();

    public Player player1;
    public Player player2;
    
    public boolean gameWon = false;
    public GameManager() {
        player1 = new Player("box turtle.png", 100, Constants.SCREEN_SIZE.height/3, 90, 90);
        player2 = new Player("kakapo.png", 300, Constants.SCREEN_SIZE.height/3, 90, 90);
    }

    public void drawSprites(Graphics2D graphics, JPanel panel){
        graphics.drawImage(player1.getImage(), player1.getX(), player1.getY(),player1.getWidth(),player1.getHeight(),panel);
        graphics.drawImage(player2.getImage(), player2.getX(), player2.getY(),player2.getWidth(),player2.getHeight(),panel);
    }


    public void keyPressed(int keyCode) {
        keysHeld.add(keyCode);
        switch(keyCode)
		{
        //Player1
		case  Constants.RIGHTKEY: //right
			player1.setDirection(1);
			break;
		case Constants.LEFTKEY: //left
			player1.setDirection(-1);
			break;
		case Constants.SPACEKEY: //space
			player1.jump();
			break;
			//Player 2
		case  Constants.DKEY: //right
			player2.setDirection(1);
			break;
		case Constants.AKEY: //left
			player2.setDirection(-1);
			break;
		case Constants.WKEY: //space
			player2.jump();
			break;
		}
        }
    

    public void keyReleased(int keyCode) {
        keysHeld.remove(keyCode);
        switch(keyCode)
		{
        //Player1
		case  Constants.RIGHTKEY: //right
			player1.setDirection(0);
			break;
		case Constants.LEFTKEY: //left
			player1.setDirection(0);
			break;
			//Player 2
		case  Constants.DKEY: //right
			player2.setDirection(0);
			break;
		case Constants.AKEY: //left
			player2.setDirection(0);
			break;
		}
    }

    public boolean isKeyHeld(int keyCode) {
        return keysHeld.contains(keyCode);  
    }

    public void update() {
        player1.update();
        player2.update();
       
    }

}
