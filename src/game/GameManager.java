package game;

import java.awt.BasicStroke;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

import javax.swing.Timer;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JPanel;

import Sprites.Player;
import Sprites.Sprite;
import Sprites.CheckPoint;
import Sprites.Food;
import Sprites.Map;
import constants.Constants;
import riddles.Riddle;
import riddles.RiddleData;

public class GameManager {

    private Set<Integer> keysHeld = new HashSet<>();

    public Player player1;
    public Player player2;
    public Map map;

    // Timer variables.
    private Timer timer;
    private int second = 0;
    private int minute = 2;

    // Variables to format the timer.
    private String decimalSecond = "00"; 
    private String decimalMinute = "02"; 
    private DecimalFormat decimalTime = new DecimalFormat("00");

    // Riddle system
    private Riddle currentRiddleDisplayed;
    RiddleData data;
    private boolean riddleActive;
    private boolean feedbackActive = false;
    private String userInput = "";
    private String feedback = "";


    // Checkpoint
    private CheckPoint checkpoint;
    private void dismissCheckpoint() {
        checkpoint = null;
        riddleActive = false;
        feedbackActive = false;
        feedback = "";
        userInput = "";
    }

    public GameManager() {
        // Load riddles
        data = new RiddleData();
        // Variable that holds the current riddle displayed.
        currentRiddleDisplayed = null;
        // Flag that checks if a riddle is displayed.
        riddleActive = false;
    }

    public void initializeGame(ArrayList<String> selectedCharacters) {
        //Initialise the players with the characters choosen.
        String player1Img = getCharacterImage(selectedCharacters.get(0));
        String player2Img = getCharacterImage(selectedCharacters.get(1));
 
        player1 = new Player(player1Img, 0, Constants.GROUND_HEIGHT - 90, 90, 90);
        player2 = new Player(player2Img, 80, Constants.GROUND_HEIGHT - 90, 90, 90);
        map = new Map("tile.png","tile2.png", "tile3.png", "log.png", 0, 0, 64, 64);
 
        // Initialize timer.
        timer();
        
        // Initialise checkpoint.
        createCheckpoint();
    }

    // Get the images file names.
    private String getCharacterImage(String characterName) {
        switch (characterName) {
            case "Box Turtle":
                return "box turtle.png";
            case "Kakapo":
                return "kakapo.png";
            case "African Forest Elephant":
                return "african forest elephant.png";
            case "Lemur":
                return "lemur.png";
            default:
                return "box turtle.png";
        }
    }

    // Method that handles the timer countdown.
    // https://www.ryisnow.online/2021/04/java-beginner-code-sample-create-timer.html
    private void timer(){
        // Initialise the timer with a delay of 1 second and an ActionListener that updates the timer every second.
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                second--;

                decimalSecond = decimalTime.format(second);
                decimalMinute = decimalTime.format(minute);

                // Check if a minute has passed and update the minute variable.
                if(second == -1){
                    second = 59;
                    minute--;

                    decimalSecond = decimalTime.format(second);
                    decimalMinute = decimalTime.format(minute);
                }

                // Check if time has run out and stop the timer.
                if(minute == 0 && second == 0){
                    timer.stop();
                }
            }
        });

        timer.start();
    }

    // Create new checkpoint with next riddle.
    private void createCheckpoint() {

        int newX = checkpoint == null ? 500 : checkpoint.getX() + 300; // move forward
        checkpoint = new CheckPoint("cabage.png", newX, Constants.SCREEN_SIZE.height / 3, 60, 60);
        Riddle randomRiddle = data.getRandomRiddle();
        if (randomRiddle == null)
            return;
        checkpoint.setRiddle(randomRiddle);
        int rand = (int)(Math.random() * 3);//random type
        if (rand == 0) {
            checkpoint.setType("normal"); // normal checkpoint
        } else if (rand == 1) {
            checkpoint.setType("fast"); // time speeds up
        } else {
            checkpoint.setType("slow"); // time slows down
        }
        userInput = "";
        feedback = "";
    }
    
    // Draw Background + Tiles
    public void drawBG(Graphics2D graphics, int width, int height) {
        graphics.setColor(Constants.BLUE);
        graphics.fillRect(0, 0, Constants.SCREEN_SIZE.width, Constants.SCREEN_SIZE.height);

        graphics.setColor(Constants.GREEN);
        graphics.fillRect(0, Constants.GROUND_HEIGHT, Constants.SCREEN_SIZE.width, Constants.SCREEN_SIZE.height);

        if (map != null) {
            map.draw(graphics, width, height);
        }
    }

    // Draw players + checkpoint
    public void drawSprites(Graphics2D g, JPanel panel) {
        g.drawImage(player1.getImage(), player1.getX(), player1.getY(),
                player1.getWidth(), player1.getHeight(), panel);

        g.drawImage(player2.getImage(), player2.getX(), player2.getY(),
                player2.getWidth(), player2.getHeight(), panel);

        if (checkpoint != null) {
        g.drawImage(checkpoint.getImage(), checkpoint.getX(), checkpoint.getY(),
                checkpoint.getWidth(), checkpoint.getHeight(), panel);
        }
    }

    public void drawRiddle(Graphics2D g, int panelWidth, int panelHeight) {
        if (!riddleActive && !feedbackActive)
            return;

        Riddle riddle = checkpoint.getRiddle();

        int cardW = 500;
        int cardH = 350;
        int x = (panelWidth - cardW) / 2;
        int y = (panelHeight - cardH) / 2;

        g.setColor(Constants.BROWN);
        g.fillRoundRect(x, y, cardW, cardH,20,20);
        g.setColor(Constants.COFFEE_BROWN);
        g.setStroke(new BasicStroke(2));
        g.drawRoundRect(x, y, cardW, cardH,20,20);

        if (feedbackActive) {
           g.setColor(Constants.BLACK);
           g.setFont(Constants.FINAL_FEEDBACK);
           String[] lines = feedback.split("\n");
           int lineY = y + cardH / 2 - (lines.length * 25) / 2;
           for (String line : lines) {
               drawCentered(g, line.trim(), x + cardW / 2, lineY);
               lineY += 30;
           }
           return;
       }
        
        // number of attempts
        int dot = y + 300;
        int spacing = 14;
        int dotsWidth = (Constants.MAX_ATTEMPTS - 1)* spacing + 8;
        int start = x + cardW/ 2 - dotsWidth/2;
        for (int i = 0; i < Constants.MAX_ATTEMPTS; i++) {
            g.setColor(i < riddle.getCountAttempts()
                    ? (Constants.LIGHT_GRAY) // used
                    : (Constants.GRAY)); // remaining
            g.fillOval(start + i * spacing, dot, 8, 8);
        }

        String attempts = "Atempts:" + riddle.getCountAttempts() + "/" + Constants.MAX_ATTEMPTS;
        g.setColor(Constants.GRAY);
        g.setFont(Constants.ATTEMPTS_FONT);
        drawCentered(g, attempts, x + cardW/2, dot + 25);

        // question
        g.setColor(Constants.BLACK);
        g.setFont(Constants.QUESTION_FONT);
        drawWrapped(g, riddle.getQuestion(), x + 20, y + 55, cardW - 40, 20);

        // hint
        String hint = riddle.displayHint();
        if (!hint.isEmpty()) {
            g.setColor(Constants.BLACK);
            g.setFont(Constants.QUESTION_FONT);
            drawWrapped(g, "Hint:" + hint, x + 20, y + 150, cardW - 40, 18);
        }

        // answer input field
        int input = y + 220;
        g.setColor(Constants.WHITE);
        g.fillRect(x + 20, input, cardW - 120, 30);
        g.setColor(Constants.BLACK);
        g.drawRect(x + 20, input, cardW - 120, 30);
        g.setFont(Constants.QUESTION_FONT);
        g.drawString(userInput, x + 30, input + 20);

        // submit button
        int button = x + cardW - 90;
        g.setColor(Constants.BLACK);
        g.fillRect(button, input, 70, 30);
        g.setColor(Constants.WHITE);
        g.setFont(Constants.QUESTION_FONT);
        drawCentered(g, "Answer", button + 35, input + 20);

        // feedback
        if (!feedback.isEmpty() && !feedbackActive) {
            g.setColor(Constants.BLACK);
            g.setFont(Constants.QUESTION_FONT);
            drawCentered(g, feedback, x + cardW/2, input + 63);
        }
    }

    private void drawCentered(Graphics2D graphics, String text, int centre, int y) {
        FontMetrics font = graphics.getFontMetrics(); // https://docs.oracle.com/javase/8/docs/api/java/awt/FontMetrics.html
        int textWidth = font.stringWidth(text);
        graphics.drawString(text, centre - textWidth / 2, y);
    }

    private void drawWrapped(Graphics2D graphics, String text, int x, int y, int maxWidth, int lineHeight) {
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
        if (line.length() > 0)
            graphics.drawString(line.toString(), x, y);
    }

    // INPUT
    public void keyPressed(int keyCode) {
        keysHeld.add(keyCode);
        if (feedbackActive) {
            if (keyCode == Constants.ENTERKEY) {
                feedbackActive = false;
                dismissCheckpoint();
            }
        return;
    }
        if (riddleActive) {
            // BACKSPACE
            if (keyCode == Constants.BACKSPACEKEY) {
                if (!userInput.isEmpty()) {
                    userInput = userInput.substring(0, userInput.length() - 1);
                }
            return;
            }
            // ENTER
            if (keyCode == Constants.ENTERKEY) {
                submitAnswer();
                return;
            }
            //BLOCK ALL MOVEMENT INPUT
            return;
        }
    
        switch (keyCode) {
            // Player1
            case Constants.RIGHTKEY: // right
                player1.setDirection(1);
                break;
            case Constants.LEFTKEY: // left
                player1.setDirection(-1);
                break;
            case Constants.SPACEKEY: // space
                player1.jump();
                break;
            // Player 2
            case Constants.DKEY: // right
                player2.setDirection(1);
                break;
            case Constants.AKEY: // left
                player2.setDirection(-1);
                break;
            case Constants.WKEY: // space
                player2.jump();
                break;
        }
    }

    public void keyReleased(int keyCode) {
        keysHeld.remove(keyCode);
        switch (keyCode) {
            // Player1
            case Constants.RIGHTKEY: // right
                player1.setDirection(0);
                break;
            case Constants.LEFTKEY: // left
                player1.setDirection(0);
                break;
            // Player 2
            case Constants.DKEY: // right
                player2.setDirection(0);
                break;
            case Constants.AKEY: // left
                player2.setDirection(0);
                break;
        }
    }
    
public void checkCollision(Player player,Sprite other) {
	//Checks if the right edge of the player is between the left and right edge of the other object
		if(player.getX() + player.getWidth() >= other.getX() && player.getX() + player.getWidth()  <= other.getX() + other.getWidth()){
			//bottom edge of the player between top and bottom of the other edge
			if(	player.getY()+ player.getHeight()  >= other.getY() && player.getY() + player.getHeight()  <= other.getY() + other.getHeight()){
				if(other instanceof Food) {
					((Food)other).setCollected(true);//Mark food as collected so it disappears
				}
			}
		}
}

    public boolean isKeyHeld(int keyCode) {
        return keysHeld.contains(keyCode);
    }

    public void keyTyped(char c) {
        if (!riddleActive)
            return;
        if (Character.isLetterOrDigit(c) || c == ' ') {
            userInput += c;
        }
    }

    // CHECKPOINT CONDITION (both players)
   private boolean reachedCheckpoint() {
    if (checkpoint == null) return false;
    return Math.abs(player1.getX() - checkpoint.getX()) < 30 && Math.abs(player2.getX() - checkpoint.getX()) < 30;
}

    // UPDATE GAME
    public void update() {

        /*if (riddleActive || feedbackActive) {
            player1.setDirection(0);
            player2.setDirection(0);
        }
        if (!riddleActive && !feedbackActive) {
            player1.update();
            player2.update();
        }*/
        // Movement disabled when answering
        if (riddleActive) {
            if (isKeyHeld(Constants.LEFTKEY))
                player1.setDirection(0);
            if (isKeyHeld(Constants.RIGHTKEY))
                player1.setDirection(0);

            if (isKeyHeld(Constants.AKEY))
                player2.setDirection(0);
            if (isKeyHeld(Constants.DKEY))
                player2.setDirection(0);
        }
       
        map.update();
        player1.update();
        player2.update();

        // Trigger checkpoint
        if (!riddleActive && !feedbackActive && reachedCheckpoint()) {
            riddleActive = true;
        }

        if (riddleActive && checkpoint != null) {
        if (checkpoint.getRiddle().attemptsFinished()) {
             feedback = "No attempts left.\nThe answer was: "  + checkpoint.getRiddle().getAnswer();
            userInput = "";
            riddleActive = false;
            feedbackActive = true;
            dismissCheckpoint();
            }
        }
    }

    // Getters
    public Riddle getCurrentRiddleDisplayed() {
        return currentRiddleDisplayed;
    }

    private void submitAnswer() {
        if (checkpoint.attempt(userInput)) {
            feedback = "Correct!";
            userInput = "";
        //apply the timer effect
        if (checkpoint.getType().equals("fast")) {
            second -= 10; // lose time
            feedback += "\nTime sped up! (-10s)";
        } 
        else if (checkpoint.getType().equals("slow")) {
            second += 10; // gain time
            feedback += "\nTime slowed! (+10s)";
        }
        riddleActive = false;
        feedbackActive = true;
    }
}

    // ANSWER SYSTEM
    /* public void answer(String input) {

        if (!riddleActive)
            return;

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
    }*/

    public boolean isRiddleActive() {
        return riddleActive;
    }

    public void mouseClicked(int mouseX, int mouseY, int panelWidth, int panelHeight) {
        if (feedbackActive) {
            feedbackActive = false;
            return;
        }

        if (!riddleActive)
            return;

        int cardW = 500;
        int cardH = 350;
        int cardX = (panelWidth - cardW) / 2;
        int cardY = (panelHeight - cardH) / 2;

        int buttonX = cardX + cardW - 90;
        int inputY = cardY + 220;

        // Check if click is inside the submit button
        if (mouseX >= buttonX && mouseX <= buttonX + 70 &&
                mouseY >= inputY && mouseY <= inputY + 30) {
            submitAnswer();
        }
    }
    
    public String getTimer(){
        return decimalMinute + ":" + decimalSecond;
    }

    public int getMinute(){
        return minute;
    }

    public int getSecond(){
        return second;
    }

    // Method that stops the timer if the game was won or lost before the timer ran out.
    public void stopTimer(){
        if(timer != null){
            timer.stop();
        }
    }
}
