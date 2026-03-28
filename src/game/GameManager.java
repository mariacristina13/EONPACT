package game;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JPanel;

import Sprites.Player;
import constants.Constants;

public class GameManager {
private Player movement;
    private Set<Integer> keysHeld = new HashSet<>();

    public Player player1;
    public Player player2;
    
    public boolean gameWon = false;
    public GameManager() {
        player1 = new Player("box turtle.png", 100, Constants.SCREEN_SIZE.height/3, 90, 90);
        player2 = new Player("kakapo.png", 300, Constants.SCREEN_SIZE.height/3, 90, 90);
        movement=player1;
        movement=player2;
    }

    public void drawSprites(Graphics2D graphics, JPanel panel){
        graphics.drawImage(player1.getImage(), player1.getX(), player1.getY(),player1.getWidth(),player1.getHeight(),panel);
        graphics.drawImage(player2.getImage(), player2.getX(), player2.getY(),player2.getWidth(),player2.getHeight(),panel);
    }


    public void keyPressed(int keyCode) {
        keysHeld.add(keyCode);
        if(keyCode==Constants.RIGHTKEY){//right
        	movement.setDirection(1); } 
        else if(keyCode==Constants.LEFTKEY) { 
        	movement.setDirection(-1); } 
        else if(keyCode==Constants.SPACEKEY) { movement.jump(); }
        }
    

    public void keyReleased(int keyCode) {
        keysHeld.remove(keyCode);
        System.out.println(KeyEvent.getKeyText(keyCode));
    }

    public boolean isKeyHeld(int keyCode) {
        return keysHeld.contains(keyCode);
    }

    public void update() {
        if (isKeyHeld(Constants.LEFTKEY)) {
        	player1.moveLeft();
        }
        if (isKeyHeld(Constants.RIGHTKEY)) {
        	player1.moveRight();
        }
       
    }

}
