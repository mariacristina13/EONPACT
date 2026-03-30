package game;

import java.awt.FontMetrics;
import java.awt.Graphics2D;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.*;

import javax.swing.JPanel;

import Sprites.Player;
import Sprites.CheckPoint;
import constants.Constants;
import riddles.Riddle;
import riddles.RiddleData;

public class GameManager {

    private Set<Integer> keysHeld = new HashSet<>();

    public Player player1;
    public Player player2;

    // Riddle system
    private ArrayList<Riddle> riddles;
    private ArrayList<Riddle> unplayedRiddles;
    private Riddle currentRiddleDisplayed;
    private boolean riddleActive;
    private String userInput = "";
    private String feedback = "";
    Random rand = new Random();
    
    public boolean gameWon = false;
    
    private int currentIndex;

    // Checkpoint
    private CheckPoint checkpoint;

    public GameManager() {

        player1 = new Player("box turtle.png", 0, Constants.GROUND_HEIGHT-90, 90, 90);
        player2 = new Player("kakapo.png", 80, Constants.GROUND_HEIGHT-90, 90, 90);

        // Load riddles
        RiddleData data = new RiddleData();
        riddles = data.getRiddles();
        // Craete a list for the riddles that havent been shown to the players.
        unplayedRiddles = new ArrayList<Riddle>(riddles);
        // Variable that holds the current riddle displayed.
        currentRiddleDisplayed = null;
        // Flag that checks if a riddle is displayed.
        riddleActive = false;
        currentIndex = 0;

        createCheckpoint();
    }

    // Create new checkpoint with next riddle.
    private void createCheckpoint() {
        if (currentIndex >= riddles.size()) {
            currentIndex = 0;
        }

        checkpoint = new CheckPoint("checkpoint.png", 500, Constants.SCREEN_SIZE.height/3, 60, 60, riddles.get(currentIndex));

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

      public void drawRiddle(Graphics2D g, int panelWidth, int panelHeight) {
        if (!riddleActive) return;

        Riddle riddle = checkpoint.getRiddle();

        int cardW = 400;
        int cardH = 300;
        int x = (panelWidth - cardW)/2;
        int y = (panelHeight - cardH)/2;

        g.setColor(Constants.BROWN);
        g.fillRect(x,y,cardW,cardH);
    
        // number of attempts
        String attempts = "Atempts:" + riddle.getCountAttempts() + "/" + Constants.MAX_ATTEMPTS;
        g.setColor(Constants.BLACK);
        g.setFont(Constants.ATTEMPTS_FONT);
        g.drawString(attempts, x+cardW-120, y+24);

        int dot = y + 270;
        int spacing = 14;
        int start = panelWidth/2 - (Constants.MAX_ATTEMPTS * spacing);
        for (int i = 0; i < Constants.MAX_ATTEMPTS; i++) {
            g.setColor(i < riddle.getCountAttempts()
            ? (Constants.WHITE)   // used
            : (Constants.GRAY)); // remaining 
            g.fillOval(start + i * spacing, dot, 8, 8);
        }
    
        //question
        g.setColor(Constants.BLACK);
        g.setFont(Constants.QUESTION_FONT);
        drawWrapped(g, riddle.getQuestion(), x+20, y+55, cardW-40, 20);

        //hint
        String hint = riddle.displayHint();
        if (!hint.isEmpty()) {
            g.setColor(Constants.BLACK);
            g.setFont(Constants.QUESTION_FONT);
            drawWrapped(g, "Hint:" + hint, x+20, y+150, cardW-40,18);
        }

        // answer input field
        int input = y + 195;
        g.setColor(Constants.WHITE);  
        g.fillRect(x+20, input, cardW - 120, 30);
        g.setColor(Constants.BLACK);
        g.drawRect(x+20,y,cardW-120,30);     
        g.setFont(Constants.QUESTION_FONT);
        g.drawString(userInput, x+30, input+20);

        // submit button
        int button = x + cardW - 90;
        g.setColor(Constants.BLACK);
        g.fillRect(button, input, 70, 30);
        g.setColor(Constants.WHITE);  
        g.setFont(Constants.QUESTION_FONT);
        drawCentered(g, "Submit", button + 35, input + 20);

        // feedback
        if (!feedback.isEmpty()){
        g.setColor(Constants.BLACK);
        g.setFont(Constants.QUESTION_FONT);
        drawCentered(g, feedback, panelWidth, y + 250);
    }
    }


    private void drawCentered(Graphics2D graphics, String text, int centre, int y){
        FontMetrics font = graphics.getFontMetrics(); // https://docs.oracle.com/javase/8/docs/api/java/awt/FontMetrics.html
        int textWidth = font.stringWidth(text);
        graphics.drawString(text, centre - textWidth/2, y);
    }

    private void drawWrapped(Graphics2D graphics, String text, int x, int y, int maxWidth, int lineHeight){
        FontMetrics font = graphics.getFontMetrics();
        String[] words = text.split(" ");
        StringBuilder line = new StringBuilder();
        for (String word : words) {
          String test = line + (line.length() > 0 ? " " : "") + word;
          if (font.stringWidth(test) > maxWidth) {
              graphics.drawString(line.toString(), x, y);
              y += lineHeight;
              line = new StringBuilder(word);
          } else {
              line = new StringBuilder(test);
          }
      }
      if (line.length() > 0) graphics.drawString(line.toString(), x, y);
      }
    
    // INPUT
    public void keyPressed(int keyCode) {
        keysHeld.add(keyCode);
        if (riddleActive) {
            if (keyCode == Constants.ENTERKEY) submitAnswer();
        }    
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

    public void keyTyped(char c) {
        if (!riddleActive) return;
        if (Character.isLetterOrDigit(c) || c == ' ') {
            userInput += c;
        }
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

            System.out.println("Read the following riddle and if u get it ull recive food.");
            System.out.println(checkpoint.getRiddle().getQuestion());
        }
    }

    // Method that returns a random riddle from the list.
    public Riddle getRandomRiddle(){
        // Check if the copy of the riddle list is emplty and end the game.
        if (unplayedRiddles.isEmpty()){
            return null;
        }

        // If the array isn't empty then return the random riddle picked and delete it form the copy list.
        int index = rand.nextInt(unplayedRiddles.size());
        Riddle pickedRiddle = unplayedRiddles.get(index);
        unplayedRiddles.remove(index);

        return pickedRiddle;
    }

     // Getters
    public Riddle getCurrentRiddleDisplayed(){
        return currentRiddleDisplayed;
    }

    private void submitAnswer() {
        if (checkpoint.getRiddle().attemptsFinished()) return;

        if (checkpoint.attempt(userInput)) {
            feedback = "Correct!";
            riddleActive = false;
            userInput = "";
            createCheckpoint();
        } else {
            if (checkpoint.getRiddle().attemptsFinished()) {
                feedback = "No attempts left. The answer was: " + checkpoint.getRiddle().getAnswer();
                riddleActive = false;
                createCheckpoint();
            } else {
                feedback = "Wrong answer, try again.";
            }
            userInput = "";
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


public void mouseClicked(int mouseX, int mouseY) {
    if (!riddleActive) return;

    int cardW = 400;
    int cardH = 300;
    int cardX = (Constants.SCREEN_SIZE.width - cardW) / 2;  
    int cardY = (Constants.SCREEN_SIZE.height - cardH) / 2;

    int buttonX = cardX + cardW - 90;
    int inputY = cardY + 195;

    // Check if click is inside the submit button
    if (mouseX >= buttonX && mouseX <= buttonX + 70 &&
        mouseY >= inputY && mouseY <= inputY + 30) {
        submitAnswer();
    }
}

}
