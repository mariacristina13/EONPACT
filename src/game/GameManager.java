package game;

import java.awt.event.KeyEvent;
import java.util.HashSet;
import java.util.Set;

import Sprites.Player;
import constants.Constants;

public class GameManager {

    private Set<Integer> keysHeld = new HashSet<>();

    public Player player1;
    public Player player2;

    public boolean gameWon = false;
    public GameManager() {
        player1 = new Player("box turtle.png", 100, Constants.SCREEN_SIZE.height/3, 50, 50);
        player2 = new Player("kakapo.png", 300, Constants.SCREEN_SIZE.height/3, 50, 50);
    }


    public void keyPressed(int keyCode) {
        keysHeld.add(keyCode);
        System.out.println(KeyEvent.getKeyText(keyCode));
    }

    public void keyReleased(int keyCode) {
        keysHeld.remove(keyCode);
        System.out.println(KeyEvent.getKeyText(keyCode));
    }

    public boolean isKeyHeld(int keyCode) {
        return keysHeld.contains(keyCode);
    }

    public void update() {
        if (isKeyHeld(Constants.LEFTKEY))  System.out.println("Left");
        if (isKeyHeld(Constants.RIGHTKEY)) System.out.println("Right");
    }

}
