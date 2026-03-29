package game;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JPanel;

import Sprites.CheckPoint;
import Sprites.Player;
import constants.Constants;
import riddles.RiddleLayout;

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
        if(keyCode==Constants.RIGHTKEY){//right
        	player1.setDirection(1); } 
        else if(keyCode==Constants.LEFTKEY) { 
        	player1.setDirection(-1); } 
        else if(keyCode==Constants.SPACEKEY) { 
        	player1.jump(); }
        }
    

    public void keyReleased(int keyCode) {
        keysHeld.remove(keyCode);
        if(keyCode==Constants.RIGHTKEY){//right
        	player1.setDirection(0); }
        else if(keyCode==Constants.LEFTKEY) { 
        	player1.setDirection(0); } 
    }

    public boolean isKeyHeld(int keyCode) {
        return keysHeld.contains(keyCode);
        
    }

    // Update game logic (movement + checkpoint check)
    public void update() {

    // Allow movement only if riddle is not active
    if (!riddleActive) {

        // Player 1 movement (arrow keys)
        if (isKeyHeld(Constants.LEFTKEY)) player1.moveLeft();
        if (isKeyHeld(Constants.RIGHTKEY)) player1.moveRight();

        // Player 2 movement (A / D keys)
        if (isKeyHeld(Constants.AKEY)) player2.moveLeft();
        if (isKeyHeld(Constants.DKEY)) player2.moveRight();
    }

    // Update both players
    player1.update();
    player2.update();

    // Check if both players reached checkpoint
    if (!riddleActive && reachedCheckpoint()) {
        riddleActive = true;
        layout = new RiddleLayout(CheckPoint.getRiddle()); // show riddle UI
    }
}

}
