package game;

import java.awt.Graphics2D;
import java.util.*;

import javax.swing.JPanel;

import Sprites.Player;
import Sprites.CheckPoint;
import constants.Constants;
import riddles.*;

public class GameManager {

    private Set<Integer> keysHeld = new HashSet<>();

    public Player player1;
    public Player player2;

    // Riddle system
    private RiddleData riddleData;
    private List<Riddle> riddles;
    private int currentIndex = 0;

    // Checkpoint
    private CheckPoint checkpoint;

    private boolean riddleActive = false;

    public GameManager() {

        player1 = new Player("box turtle.png", 100, Constants.SCREEN_SIZE.height/3, 90, 90);
        player2 = new Player("kakapo.png", 300, Constants.SCREEN_SIZE.height/3, 90, 90);

        // Load riddles
        riddleData = new RiddleData();
        riddles = riddleData.getRiddles();

        Collections.shuffle(riddles); // random order

        createCheckpoint();
    }

    // Create new checkpoint with next riddle
    private void createCheckpoint() {

        if (currentIndex >= riddles.size()) {
            currentIndex = 0;
        }

        checkpoint = new CheckPoint(
            "checkpoint.png",
            500,
            Constants.SCREEN_SIZE.height/3,
            60,
            60,
            riddles.get(currentIndex)
        );

        currentIndex++;
    }

    // Draw players + checkpoint
    public void drawSprites(Graphics2D g, JPanel panel){
        g.drawImage(player1.getImage(), player1.getX(), player1.getY(),
                player1.getWidth(), player1.getHeight(), panel);

        g.drawImage(player2.getImage(), player2.getX(), player2.getY(),
                player2.getWidth(), player2.getHeight(), panel);

        g.drawImage(checkpoint.getImage(), checkpoint.getX(), checkpoint.getY(),
                checkpoint.getWidth(), checkpoint.getHeight(), panel);
    }

    // INPUT
    public void keyPressed(int keyCode) {
        keysHeld.add(keyCode);

        // Player 1
        if(keyCode == Constants.RIGHTKEY) player1.setDirection(1);
        else if(keyCode == Constants.LEFTKEY) player1.setDirection(-1);
        else if(keyCode == Constants.SPACEKEY) player1.jump();

        // Player 2
        if(keyCode == Constants.AKEY) player2.setDirection(-1);
        else if(keyCode == Constants.DKEY) player2.setDirection(1);
    }

    public void keyReleased(int keyCode) {
        keysHeld.remove(keyCode);

        // Stop Player 1
        if(keyCode == Constants.RIGHTKEY || keyCode == Constants.LEFTKEY){
            player1.setDirection(0);
        }

        // Stop Player 2
        if(keyCode == Constants.AKEY || keyCode == Constants.DKEY){
            player2.setDirection(0);
        }
    }

    public boolean isKeyHeld(int keyCode) {
        return keysHeld.contains(keyCode);
    }

    // CHECKPOINT CONDITION (both players)
    private boolean reachedCheckpoint() {
        return Math.abs(player1.getX() - checkpoint.getX()) < 30 &&
               Math.abs(player2.getX() - checkpoint.getX()) < 30;
    }

    // UPDATE GAME
    public void update() {

        // Movement disabled when answering
        if (!riddleActive) {
            if (isKeyHeld(Constants.LEFTKEY)) player1.setDirection(-1);
            if (isKeyHeld(Constants.RIGHTKEY)) player1.setDirection(1);

            if (isKeyHeld(Constants.AKEY)) player2.setDirection(-1);
            if (isKeyHeld(Constants.DKEY)) player2.setDirection(1);
        }

        player1.update();
        player2.update();

        // Trigger checkpoint
        if (!riddleActive && reachedCheckpoint()) {
            riddleActive = true;

            System.out.println("=== RIDDLE ===");
            System.out.println(checkpoint.getRiddle().getQuestion());
        }
    }

    // ANSWER SYSTEM
    public void answer(String input) {

        if (!riddleActive) return;

        if (checkpoint.attempt(input)) {

            System.out.println("Correct!");
            riddleActive = false;
            createCheckpoint();

        } else {

            System.out.println("Wrong!");

            String hint = checkpoint.getHint();
            if (!hint.isEmpty()) {
                System.out.println("Hint: " + hint);
            }

            if (checkpoint.isFailed()) {
                System.out.println("Failed → next checkpoint");
                riddleActive = false;
                createCheckpoint();
            }
        }
    }

    public boolean isRiddleActive() {
        return riddleActive;
    }
}