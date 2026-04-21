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

    // Player variables.
    public Player player1;
    public Player player2;

    // Background variable.
    public Background bg;

    // ArrayList that will hold the tile display.
    public ArrayList<Map> map;

    // ArrayList that will hold the food display.
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

    // Counter variables
    private int completedCheckpoints = 0;
    private int failedCheckPoints = 0;

    // Checkpoint variables.
    private ArrayList<CheckPoint> checkpoints; // list of all the checkpoints
    private CheckPoint activeCheckpoint; // currently triggered the checkpoint

    // Initialise class variables.
    public GameManager() {
        // Load riddles
        data = new RiddleData();
        // Variable that holds the current riddle displayed.
        currentRiddleDisplayed = null;
        // Flag that checks if a riddle is displayed.
        riddleActive = false;
    }

    // Initialise the game.
    public void initializeGame(ArrayList<String> selectedCharacters) {
        // Initialise the players with the characters choosen.
        String player1Img = getCharacterImage(selectedCharacters.get(0));
        String player1FlippedImg=getFlippedCharacterImage(selectedCharacters.get(0));
        String player2Img = getCharacterImage(selectedCharacters.get(1));
        String player2FlippedImg=getFlippedCharacterImage(selectedCharacters.get(1));

        
        // Initialise the player position at the start of the game.
        player1 = new Player(player1Img, 150, Constants.GROUND_HEIGHT - 500, 90, 90);
        player1.loadFlippedImage(player1FlippedImg);
        player2 = new Player(player2Img, 80, Constants.GROUND_HEIGHT - 200, 90, 90);
        player2.loadFlippedImage(player2FlippedImg);
        // Imitialise background.
        bg = new Background("bg.png", 0, Constants.SCREEN_HEIGHT, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
        // Initialise map.
        map = new ArrayList<Map>();
        // Add the tiles to the ArrayList to be displayed in the game at diffrent positions.
        map.add(new Map("tile.png", 50, Constants.GROUND_HEIGHT - 300, Constants.TILE_WIDTH, Constants.TILE_HEIGHT));
        map.add(new Map("tile2.png", Constants.TILE_WIDTH + 70, Constants.GROUND_HEIGHT - 60, Constants.TILE_WIDTH,
                Constants.TILE_HEIGHT));
        map.add(new Map("tile3.png", Constants.TILE_WIDTH + 260, Constants.GROUND_HEIGHT - 250, Constants.TILE_WIDTH,
                Constants.TILE_HEIGHT));
        map.add(new Map("log.png", Constants.TILE_WIDTH + 600, Constants.GROUND_HEIGHT + 10, Constants.TILE_WIDTH,
                Constants.TILE_HEIGHT));
        map.add(new Map("tile.png", 0, Constants.GROUND_HEIGHT, Constants.TILE_WIDTH, Constants.TILE_HEIGHT));
        map.add(new Map("tile.png", Constants.TILE_WIDTH + 350, Constants.GROUND_HEIGHT - 100, Constants.TILE_WIDTH,
                Constants.TILE_HEIGHT));
        map.add(new Map("tile.png", Constants.TILE_WIDTH + 600, Constants.GROUND_HEIGHT - 300, Constants.TILE_WIDTH,
                Constants.TILE_HEIGHT));
        map.add(new Map("tile2.png", 125, Constants.GROUND_HEIGHT - 140, Constants.TILE_WIDTH, Constants.TILE_HEIGHT));
        map.add(new Map("tile2.png", Constants.TILE_WIDTH + 550, Constants.GROUND_HEIGHT - 150, Constants.TILE_WIDTH,
                Constants.TILE_HEIGHT));
        map.add(new Map("tile3.png", Constants.TILE_WIDTH + 770, Constants.GROUND_HEIGHT - 200, Constants.TILE_WIDTH,
                Constants.TILE_HEIGHT));

        // Initialise food.
        foods = new ArrayList<Food>();
        // Add the food to the ArrayList to be displayed in the game at diffrent positions.
        foods.add(new Food("cabage.png", 700, Constants.GROUND_HEIGHT - 250, 60, 60));
        foods.add(new Food("leaf.png", 190, Constants.GROUND_HEIGHT - 290, 60, 60));
        foods.add(new Food("seeds.png", 600, Constants.GROUND_HEIGHT - 400, 60, 60));
        foods.add(new Food("bamboo.png", 1100, Constants.GROUND_HEIGHT - 355, 60, 60));

        // Initialize timer.
        timer();

        // Initialise the checkpoints.
        checkpoints = new ArrayList<CheckPoint>();
        for (int i = 0; i < 5; i++) {
            int x = 250 + i * 200; // smaller spacing and also spread the checkpoints across the map
            // Create a checkpoint with a random food image.
            CheckPoint cp = new CheckPoint(getRandomCheckpointImage(), x, Constants.GROUND_HEIGHT - 60, 60, 60);
            Riddle r = data.getRandomRiddle(); // Assign random riddle.
            if (r != null) {
                cp.setRiddle(r);
            }
            checkpoints.add(cp); // Add the checkpoint to the ArrayList.
        }
    }

    // Add the images to an array.
    private String[] checkpointImages = {
            "cabage.png",
            "leaf.png",
            "seeds.png",
            "bamboo.png",
            "berries.png",
    };

    // Get a random food image from the array of images.
    private String getRandomCheckpointImage() {// to get a random image for the checkpoint
        int index = (int) (Math.random() * checkpointImages.length);
        return checkpointImages[index];
    }

    // Get the images' file names.
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
            case "Gobi Bear":
                return "gobi bear.png";
            case "Red Panda":
                return "red panda.png";
            case "Arctic Fox":
                return "arctic fox.png";
            case "Leopard":
                return "leopard.png";
            default:
                return "box turtle.png";
        }
    }
    
    private String getFlippedCharacterImage(String characterName) {
    	 switch (characterName) {
         case "Box Turtle":
             return "box turtle flipped.png";
         case "Kakapo":
             return "kakapo flipped.png";
         case "African Forest Elephant":
             return "african forest elephant flipped.png";
         case "Lemur":
             return "lemur flipped.png";
         case "Gobi Bear":
             return "gobi bear flipped.png";
         case "Red Panda":
             return "red panda flipped.png";
         case "Arctic Fox":
             return "arctic fox flipped.png";
         case "Leopard":
             return "leopard flipped.png";
         default:
             return "box turtle flipped.png";
     }
    }

    // Method that handles the timer countdown.
    // https://www.ryisnow.online/2021/04/java-beginner-code-sample-create-timer.html
    private void timer() {
        // Initialise the timer with a delay of 1 second and an ActionListener that updates the timer every second.
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                // Decrease the seconds.
                second--;

                // Format the timer.
                decimalSecond = decimalTime.format(second);
                decimalMinute = decimalTime.format(minute);

                // Check if a minute has passed and update the minute variable then format the timer again.
                if (second == -1) {
                    second = 59;
                    minute--;

                    decimalSecond = decimalTime.format(second);
                    decimalMinute = decimalTime.format(minute);
                }

                // Check if time has run out and stop the timer.
                if (minute == 0 && second == 0) {
                    timer.stop();
                }
            }
        });

        timer.start();
    }

    // Draw the background and tiles.
    public void drawBG(Graphics2D graphics, JPanel panel) {
        graphics.drawImage(bg.getImage(), bg.getX(), bg.getY(), bg.getWidth(), bg.getHeight(), panel);

        // Check if for each tile in the map ArrayList there is a tile, otherwise create it.
        for (Map tile : map)
            if (tile != null) {
                graphics.drawImage(tile.getImage(), tile.getX(), tile.getY(), tile.getWidth(), tile.getHeight(), panel);
            }
    }

    public void drawSprites(Graphics2D g, JPanel panel) {
        // Draw the players.
        g.drawImage(player1.getImage(), player1.getX(), player1.getY(),
                player1.getWidth(), player1.getHeight(), panel);

        g.drawImage(player2.getImage(), player2.getX(), player2.getY(),
                player2.getWidth(), player2.getHeight(), panel);

        // Display the checkpoints.
        for (CheckPoint cp : checkpoints) {
            g.drawImage(cp.getImage(), cp.getX(), cp.getY(), cp.getWidth(), cp.getHeight(), panel);
        }

        // Check that the food wasn't collected and draw the it.
        for (Food food : foods) {
            if (food.isCollected() == false)
                g.drawImage(food.getImage(), food.getX(), food.getY(), food.getWidth(), food.getHeight(), panel);
        }

        // draw score
        // g.setColor(Constants.GREEN);
        // g.setFont(Constants.TIMER_FONT);
        // g.drawString(Integer.toString(player1.getScore() + player2.getScore()), 20,
        // 20);
    }

    // Draw the riddle counter.
    public void drawCounter(Graphics2D g) {
        g.setFont(Constants.TIMER_FONT);
        g.setColor(Constants.GOLD);
        g.drawString("Solved Riddles: " + completedCheckpoints + "/5", 20, 70);
    }

    // Draw the riddle card.
    public void drawRiddle(Graphics2D g, int panelWidth, int panelHeight) {
        // Prevent a crash if none of the checkpoints are active.
        if ((!riddleActive && !feedbackActive) || activeCheckpoint == null){
            return;
        }

        // Get the riddle from the checkpoint.
        Riddle riddle = activeCheckpoint.getRiddle();

        // Variables for the riddle card.
        int cardW = 500;
        int lineHeight = 20;

        g.setColor(Constants.BLACK);
        g.setFont(Constants.QUESTION_FONT);

        // Count the number of lines in riddle and adjusting card's height accordingly.
        FontMetrics fm = g.getFontMetrics();
        String[] words = riddle.getQuestion().split(" ");
        int lineCount = 1;
        StringBuilder line = new StringBuilder();

        for (String word : words) {
            String test = line + (line.length() > 0 ? " " : "") + word;
            if (fm.stringWidth(test) > cardW - 40) {
                lineCount++;
                line = new StringBuilder(word);
            } else {
                line = new StringBuilder(test);
            }
        }

        int cardH = 320 + Math.max(0, lineCount - 3) * lineHeight;
        int x = (panelWidth - cardW) / 2;
        int y = (panelHeight - cardH) / 2;

        // Draw the card.
        g.setColor(Constants.BROWN);
        g.fillRoundRect(x, y, cardW, cardH, 20, 20);
        g.setColor(Constants.COFFEE_BROWN);
        g.setStroke(new BasicStroke(2));
        g.drawRoundRect(x, y, cardW, cardH, 20, 20);

        // If the feedback is active add a card on top of the riddle card to show it.
        if (feedbackActive) {
            g.setColor(Constants.BLACK);
            g.setFont(Constants.FINAL_FEEDBACK);
            String[] lines = feedback.split("\n");
            int lineY = y + cardH / 2 - (lines.length * 25) / 2;
            for (String line1 : lines) {
                drawCentered(g, line1.trim(), x + cardW / 2, lineY);
                lineY += 30;
            }
            return;
        }

        // Display the number of attempts.
        int dot = y + cardH - 50;
        int spacing = 14;
        int dotsWidth = (Constants.MAX_ATTEMPTS - 1) * spacing + 8;
        int start = x + cardW / 2 - dotsWidth / 2;
        // Every time the player submits an answer decerase the number of attempts left.
        for (int i = 0; i < Constants.MAX_ATTEMPTS; i++) {
            g.setColor(i < riddle.getCountAttempts()
                    ? (Constants.LIGHT_GRAY) // used
                    : (Constants.GRAY)); // remaining
            g.fillOval(start + i * spacing, dot, 8, 8);
        }

        String attempts = "Atempts:" + riddle.getCountAttempts() + "/" + Constants.MAX_ATTEMPTS;
        g.setColor(Constants.GRAY);
        g.setFont(Constants.ATTEMPTS_FONT);
        drawCentered(g, attempts, x + cardW / 2, dot + 25);

        // Draw the question.
        g.setColor(Constants.BLACK);
        g.setFont(Constants.QUESTION_FONT);
        drawWrapped(g, riddle.getQuestion(), x + 20, y + 55, cardW - 40, 20);

        // Draw the hint.
        String hint = riddle.displayHint();
        if (!hint.isEmpty()) {
            g.setColor(Constants.BLACK);
            g.setFont(Constants.HINT_FONT);
            drawWrapped(g, "Hint: " + hint, x + 20, y + cardH - 190, cardW - 40, 18);
        }

        // Draw the input field.
        int input = y + cardH - 120;
        g.setColor(Constants.WHITE);
        g.fillRect(x + 20, input, cardW - 120, 30);
        g.setColor(Constants.BLACK);
        g.drawRect(x + 20, input, cardW - 120, 30);
        g.setFont(Constants.QUESTION_FONT);
        g.drawString(userInput, x + 30, input + 20);

        // Draw the submit button.
        int button = x + cardW - 90;
        g.setColor(Constants.BLACK);
        g.fillRect(button, input, 70, 30);
        g.setColor(Constants.WHITE);
        g.setFont(Constants.QUESTION_FONT);
        drawCentered(g, "Answer", button + 35, input + 20);

        // Draw feedback.
        if (!feedback.isEmpty() && !feedbackActive) {
            g.setColor(Constants.BLACK);
            g.setFont(Constants.FEEDBACK_FONT);
            drawCentered(g, feedback, x + cardW / 2, input + 60);
        }
    }

    //Add methods to postion elements in the drawRiddle method.
    private void drawCentered(Graphics2D graphics, String text, int centre, int y) {
        FontMetrics font = graphics.getFontMetrics(); // https://docs.oracle.com/javase/8/docs/api/java/awt/FontMetrics.html
        // Calculate the text width.
        int textWidth = font.stringWidth(text);
        // Offset left the element by half the text width so it's visually centered.
        graphics.drawString(text, centre - textWidth / 2, y);
    }

    private void drawWrapped(Graphics2D graphics, String text, int x, int y, int maxWidth, int lineHeight) {
        FontMetrics font = graphics.getFontMetrics();
        String[] words = text.split(" ");
        StringBuilder line = new StringBuilder();

        // Add space after each word in the sentance.
        for (String word : words) {
            // Buid the sentence by adding the next word with a space if its not the first word. 
            String test = line + (line.length() > 0 ? " " : "") + word;
            // If the sentence is too wide then continue it on the next row.
            if (font.stringWidth(test) > maxWidth) {
                graphics.drawString(line.toString(), x, y);
                y += lineHeight;
                // Start a new line with the words that dont fit the previous one.
                line = new StringBuilder(word);
            } else {
                // Draw the sentance as normal.
                line = new StringBuilder(test);
            }
        }
        // Draw any remaining text that wasn't abel to fill a full line.
        if (line.length() > 0)
            graphics.drawString(line.toString(), x, y);
    }

    public void keyPressed(int keyCode) {
        keysHeld.add(keyCode);
        if (feedbackActive) {
            if (keyCode == Constants.ENTERKEY) {
                // Remove the feedback card
                feedbackActive = false;
                // Remove the checkpoint from the game.
                checkpoints.remove(activeCheckpoint);
                // Create the next checkpoint.
                int newX = activeCheckpoint.getX() + 1500;
                CheckPoint newCP = new CheckPoint("cabage.png", newX,
                        Constants.GROUND_HEIGHT - 60, 60, 60);
                Riddle r = data.getRandomRiddle();
                if (r != null) {
                    newCP.setRiddle(r);
                }
                // Add the new checkpoint to the ArrayList
                checkpoints.add(newCP); 
                // Reset the checkpoint.
                activeCheckpoint = null;
                feedback = "";
            }
            return;
        }
        if (riddleActive) {
            // Enable the backspace key to allow the user to delete characters from the input filed.
            if (keyCode == Constants.BACKSPACEKEY) {
                if (!userInput.isEmpty()) {
                    userInput = userInput.substring(0, userInput.length() - 1);
                }
                return;
            }
            // Enable the enter key to submit an answer.
            if (keyCode == Constants.ENTERKEY) {
                submitAnswer();
                return;
            }
            return;
        }

        switch (keyCode) {
            // Player1 movement.
            // Move right.
            case Constants.RIGHTKEY:
                player1.setDirection(1);
                player1.setImage(player1.getOriginalImage());
                break;
            // Move left.
            case Constants.LEFTKEY:
                player1.setDirection(-1);
                player1.setImage(player1.getFlippedImage());      
                break;
            // Jump.
            case Constants.SPACEKEY:
                player1.jump();
                break;
            // Player 2 movement.
            // Move right.
            case Constants.DKEY: 
                player2.setDirection(1);
                player2.setImage(player2.getOriginalImage());
                break;
            // Move left.
            case Constants.AKEY:
                player2.setDirection(-1);
                player2.setImage(player2.getFlippedImage());
                break;
            // Jump
            case Constants.WKEY:
                player2.jump();
                break;
        }
    }

    public void keyReleased(int keyCode) {
        keysHeld.remove(keyCode);
        switch (keyCode) {
            // Remove player1's movement when the key isn't pressed.
            case Constants.RIGHTKEY: 
                player1.setDirection(0);
                break;
            case Constants.LEFTKEY:
                player1.setDirection(0);
                break;
            // Remove player2's movement when the key isn't pressed.
            case Constants.DKEY: 
                player2.setDirection(0);
                break;
            case Constants.AKEY: 
                player2.setDirection(0);
                break;
        }
    }

    public void checkCollision(Player player, Food other) {
        if (player.getX() < other.getX() + other.getWidth() && // if the player's left side collides with the food's right side,
                player.getX() + player.getWidth() > other.getX() && // Player right side is not completely to the left of the food,
                player.getY() < other.getY() + other.getHeight() && // Player is not below food
                player.getY() + player.getHeight() > other.getY()) // Player is not above food
        {
            // Set collected to true to remove the food.
            other.setCollected(true);
        }
    }

    // Method that returns the key that is held.
    public boolean isKeyHeld(int keyCode) {
        return keysHeld.contains(keyCode);
    }

    // Method that lets the user type in the answer box.
    public void keyTyped(char c) {
        if (!riddleActive)
            return;
        if (Character.isLetterOrDigit(c) || c == ' ') {
            userInput += c;
        }
    }

    private CheckPoint getReachedCheckpoint() {
        // For each checkpoint in the array list check if both characters have rached it.
        for (CheckPoint cp : checkpoints) {
            if (Math.abs(player1.getX() - cp.getX()) < 30 && Math.abs(player2.getX() - cp.getX()) < 30) {
                // Return the checkpoint that was reached.
                return cp;
            }
        }
        // If the characters didn't reach any checkpoints then return null.
        return null;
    }

    public void update() {
        if (!riddleActive && !feedbackActive) {
            // Check which checkpoint was reached.
            CheckPoint hit = getReachedCheckpoint(); 
            // If a checkpoint was reached,
            if (hit != null) {
                // Activate the checkpoint.
                activeCheckpoint = hit;
                // Display the riddle card.
                riddleActive = true; 
                // Reset the feedback for each checkpoint.
                feedback = "";
                // Reset the user input for each checkpoint.
                userInput = "";
            }
        }

        // Disable the players movement when answering the riddles.
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

        // Loop through the foods to check each player's collision with the food.
        for (Food food : foods) {
            checkCollision(player1, food);
            checkCollision(player2, food);

        }

        // Remove the food that the palyer collided with.
        foods.removeIf(food -> food.isCollected());
    }

    private void submitAnswer() {
        if (activeCheckpoint == null)
            return;
        // Get the user input.
        boolean correct = activeCheckpoint.attempt(userInput); 
        // Clear input.
        userInput = "";

        // If the answer is correct.
        if (correct) {
            // Display the feedback.
            feedback = "Correct!";
            // Increase the counter for the completed checkpoints.
            completedCheckpoints++;
            // Hide the riddle card.
            riddleActive = false;
            // Display the feedback card.
            feedbackActive = true;
        } 
        // If the answer is wrong.
        else {
            // Check if the attempts are finished.
            if (activeCheckpoint.getRiddle().attemptsFinished()) {
                // Display the answer if the atempts finished. 
                feedback = "No attempts left!\nAnswer: " + activeCheckpoint.getRiddle().getAnswer();
                // Incraese the counter for the failed checkpoints.
                failedCheckPoints++;
                // Hide the riddle card.
                riddleActive = false;
                // Show the feedback card.
                feedbackActive = true;
            } 
            else {
                // Display the feedback on the riddle card.
                feedback = "Wrong! Try again.";
            }
        }
    }

    public void mouseClicked(int mouseX, int mouseY, int panelWidth, int panelHeight) {
        if (feedbackActive) {
            // Hide the checkpoint card.
            feedbackActive = false;
            // Remove the checkpoint
            checkpoints.remove(activeCheckpoint);
            return;
        }

        if (!riddleActive){
            return;
        }
        
        // Draw the riddle card.
        int cardW = 500;
        int cardH = 350;
        int cardX = (panelWidth - cardW) / 2;
        int cardY = (panelHeight - cardH) / 2;

        int buttonX = cardX + cardW - 90;
        int inputY = cardY + 220;

        // Check if the players click inside the submit button.
        if (mouseX >= buttonX && mouseX <= buttonX + 70 &&
                mouseY >= inputY && mouseY <= inputY + 30) {
            // If they do then submit the answer.
            submitAnswer();
        }
    }

    // Method that stops the timer if the game was won or lost before the timer ran out.
    public void stopTimer() {
        if (timer != null) {
            timer.stop();
        }
    }

    // Reset the timer for each game.
    public void resetTimer(){
        minute = 2;
        second = 0;
        decimalMinute = decimalTime.format(minute);
        decimalSecond = decimalTime.format(second);
    }

    // Reset the completed checkpoints for each game.
    public void resetCompletedCheckpoints(){
        completedCheckpoints = 0;
    }

    // Getters and setters
    public Riddle getCurrentRiddleDisplayed() {
        return currentRiddleDisplayed;
    }

    public boolean isRiddleActive() {
        return riddleActive;
    }

    public void setRiddleActive(boolean active) {
        this.riddleActive = active;
    }

    public boolean isFeedbackActive() {
        return feedbackActive;
    }

    public String getUserInput() {
        return userInput;
    }

    public void setUserInput(String userInput) {
        this.userInput = userInput;
    }

    public String getTimer() {
        return decimalMinute + ":" + decimalSecond;
    }

    public int getMinute() {
        return minute;
    }

    public int getSecond() {
        return second;
    }

    public int getCompletedCheckpoints(){
        return completedCheckpoints;
    }

    public int getFailedCheckPoints(){
        return failedCheckPoints;
    }
}
