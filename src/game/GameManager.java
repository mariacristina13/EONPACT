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
import Sprites.Background;
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
    public Background bg;
    public ArrayList<Map>  map;
    public ArrayList<Food> foods;

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
    private ArrayList<CheckPoint> checkpoints; // list of all the checkpoints
    private CheckPoint activeCheckpoint;       // currently triggered the checkpoint

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
 
        player1 = new Player(player1Img, 150, Constants.GROUND_HEIGHT - 500, 90, 90);
        player2 = new Player(player2Img, 80, Constants.GROUND_HEIGHT - 200, 90, 90);
      
        
        
        //imitialise background
        bg = new Background("bg.png", 0, Constants.SCREEN_HEIGHT, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
        //initialise map
        map = new ArrayList<Map>();
        map.add(new Map("tile.png", 50, Constants.GROUND_HEIGHT - 300, Constants.TILE_WIDTH, Constants.TILE_HEIGHT));
        map.add(new Map("tile2.png", Constants.TILE_WIDTH + 70, Constants.GROUND_HEIGHT - 60, Constants.TILE_WIDTH, Constants.TILE_HEIGHT));
        map.add(new Map("tile3.png", Constants.TILE_WIDTH + 260, Constants.GROUND_HEIGHT - 250, Constants.TILE_WIDTH, Constants.TILE_HEIGHT));
        map.add(new Map("log.png", Constants.TILE_WIDTH + 600, Constants.GROUND_HEIGHT + 10, Constants.TILE_WIDTH, Constants.TILE_HEIGHT));
        
        map.add(new Map("tile.png", 0, Constants.GROUND_HEIGHT, Constants.TILE_WIDTH, Constants.TILE_HEIGHT));
        map.add(new Map("tile.png", Constants.TILE_WIDTH + 350, Constants.GROUND_HEIGHT - 100, Constants.TILE_WIDTH, Constants.TILE_HEIGHT));
        map.add(new Map("tile.png", Constants.TILE_WIDTH + 600, Constants.GROUND_HEIGHT - 300, Constants.TILE_WIDTH, Constants.TILE_HEIGHT));
        map.add(new Map("tile2.png",125, Constants.GROUND_HEIGHT - 140, Constants.TILE_WIDTH, Constants.TILE_HEIGHT));
        map.add(new Map("tile2.png",Constants.TILE_WIDTH + 550, Constants.GROUND_HEIGHT - 150, Constants.TILE_WIDTH, Constants.TILE_HEIGHT));
        map.add(new Map("tile3.png", Constants.TILE_WIDTH + 770, Constants.GROUND_HEIGHT - 200, Constants.TILE_WIDTH, Constants.TILE_HEIGHT));

        //initialise food
        foods = new ArrayList<Food>();
        foods.add(new Food("cabage.png", 700,Constants.GROUND_HEIGHT - 250, 60, 60)); 
        foods.add(new Food("leaf.png", 190,Constants.GROUND_HEIGHT - 285, 60, 60)); 
        foods.add(new Food("seeds.png", 600,Constants.GROUND_HEIGHT - 400, 60, 60)); 
        foods.add(new Food("bamboo.png", 1100,Constants.GROUND_HEIGHT - 355, 60, 60)); 
 
        // Initialize timer.
        timer();
        
        checkpoints = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            int x = 250 + i * 200; // smaller spacing // spread the checkpoints across the map
            CheckPoint cp = new CheckPoint("cabage.png", x,Constants.GROUND_HEIGHT - 60, 60, 60);
            Riddle r = data.getRandomRiddle(); // assign riddle
                if (r != null) {
                cp.setRiddle(r);
            }

        checkpoints.add(cp); // add to list
    }
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
    
    // Draw Background + Tiles
    public void drawBG(Graphics2D graphics, JPanel panel) {
        graphics.drawImage(bg.getImage(), bg.getX(), bg.getY(), bg.getWidth(), bg.getHeight(), panel);

        for (Map tile: map)
        if (tile != null) {
           graphics.drawImage(tile.getImage(), tile.getX(), tile.getY(), tile.getWidth(), tile.getHeight(), panel);
        }
    }

    // Draw players + checkpoint + food
    public void drawSprites(Graphics2D g, JPanel panel) {
        g.drawImage(player1.getImage(), player1.getX(), player1.getY(),
                player1.getWidth(), player1.getHeight(), panel);

        g.drawImage(player2.getImage(), player2.getX(), player2.getY(),
                player2.getWidth(), player2.getHeight(), panel);

        for (CheckPoint cp : checkpoints) { // loop all checkpoints
            g.drawImage(cp.getImage(), cp.getX(), cp.getY(), cp.getWidth(), cp.getHeight(), panel);
        }

        // draw food
        for (Food food: foods){
            if(food.isCollected() == false)
                g.drawImage(food.getImage(), food.getX(), food.getY(), food.getWidth(), food.getHeight(), panel);
        }

        //draw score
        //g.setColor(Constants.GREEN);
        //g.setFont(Constants.TIMER_FONT);
        //g.drawString(Integer.toString(player1.getScore() + player2.getScore()), 20, 20);
    }

    public void drawRiddle(Graphics2D g, int panelWidth, int panelHeight) {

        if ((!riddleActive && !feedbackActive) || activeCheckpoint == null)
            return; // prevent crash if no active checkpoint

        Riddle riddle = activeCheckpoint.getRiddle(); // use active checkpoint

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
                checkpoints.remove(activeCheckpoint); // remove old checkpoint
                int newX = activeCheckpoint.getX() + 1500;// create new checkpoint further ahead
                CheckPoint newCP = new CheckPoint("cabage.png", newX,
                Constants.GROUND_HEIGHT - 60, 60, 60);
                Riddle r = data.getRandomRiddle();
                if (r != null) {
                    newCP.setRiddle(r);
                }
            checkpoints.add(newCP); // add new checkpoint
            activeCheckpoint = null; // reset
            feedback = "";
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
    
public void checkCollision(Player player,Food other) {
	if(player.getX() < other.getX() + other.getWidth() &&//Players left side before foods right side
		       player.getX() + player.getWidth() > other.getX() &&//Player right side is not completely to the left of food
		       player.getY() < other.getY() + other.getHeight() &&//Player is not below food
		       player.getY() + player.getHeight() > other.getY()) //Player is not above food
	{        
		other.setCollected(true);//Set collected as true
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

    private CheckPoint getReachedCheckpoint() {
        for (CheckPoint cp : checkpoints) { // check each checkpoint
            if (Math.abs(player1.getX() - cp.getX()) < 30 && Math.abs(player2.getX() - cp.getX()) < 30) {
                return cp; // return the one reached
        }
    }

    return null; // none reached
}

    // UPDATE GAME
    public void update() {

        if (!riddleActive && !feedbackActive) {
            CheckPoint hit = getReachedCheckpoint(); // check which checkpoint
            if (hit != null) {
                activeCheckpoint = hit; // set active checkpoint
                riddleActive = true;    // open riddle UI
                feedback = "";//reset the feedback again after one riddle
                userInput = "";
            }
    }
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
       
        player1.update(map);
        player2.update(map);
        //Check collision
        for(Food food:foods) {//Loop because food is an arraylist
        	checkCollision(player1,food);
        	checkCollision(player2,food);
    
        }
        foods.removeIf(food->food.isCollected());
    }

    // Getters
    public Riddle getCurrentRiddleDisplayed() {
        return currentRiddleDisplayed;
    }

    private void submitAnswer() {
        if (activeCheckpoint == null)
            return;
            boolean correct = activeCheckpoint.attempt(userInput); // check answer
            userInput = ""; // clear input
            if (correct) {
                feedback = "Correct!";
                riddleActive = false;
                feedbackActive = true;
            } else {
                if (activeCheckpoint.getRiddle().attemptsFinished()) {
                    feedback = "No attempts left!\nAnswer: " + activeCheckpoint.getRiddle().getAnswer();
                    riddleActive = false;
                    feedbackActive = true;
                }
                else {
                    feedback = "Wrong! Try again.";
                }
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
