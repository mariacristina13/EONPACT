package game;

import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import Sprites.Background;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import constants.Constants;

public class CharacterMenu {
    // Class variables.
    private ArrayList<CharacterButton> characters;
    private CharacterButton elephantBtn;
    private CharacterButton lemurBtn;
    private CharacterButton bearBtn;
    private CharacterButton foxBtn;
    private CharacterButton pandaBtn;
    private CharacterButton leopardBtn;
    private ArrayList<String> selectedCharacters;
    private MenuButton playBtn;
    private MenuButton backBtn;
    private BufferedImage lockImg;
    private int lockX;
    private int lockY;
    private int riddleScore = 0;
     public Background bg;

    // Initialise class variables.
    public CharacterMenu() {
        selectedCharacters = new ArrayList<String>();
        initCharacters();
        initButtons();
        loadImage();
        bg = new Background("menu_bg.png", 0, Constants.SCREEN_HEIGHT, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
    }

    // Load the image for the locked characters.
    private void loadImage() {
        try {
            lockImg = ImageIO.read(new File("images/" + "lock.png"));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.print("file not found");
        }
    }

    // Initialise the character buttons.
    private void initCharacters() {
        characters = new ArrayList<CharacterButton>();

        // Set the width of the row that the characters are going to be displayed.
        int totalWidth = (Constants.CHARACTER_WIDTH * 4) + (Constants.CHARACTER_SPACEING * 3);
        // Set the starting X position for the character display.
        int startX = Constants.CHARACTER_BUTTON_X - (totalWidth / 2);

        // Initialise characters.
        characters.add(new CharacterButton("Box Turtle", "box turtle.png", startX, Constants.CHARACTER_BUTTON_Y,
                Constants.CHARACTER_WIDTH, Constants.CHARACTER_HEIGHT));

        characters.add(new CharacterButton("Kakapo", "kakapo.png",
                startX + Constants.CHARACTER_WIDTH + Constants.CHARACTER_SPACEING, Constants.CHARACTER_BUTTON_Y,
                Constants.CHARACTER_WIDTH, Constants.CHARACTER_HEIGHT));

        elephantBtn = new CharacterButton("African Elephant", "african forest elephant.png",
                startX + (Constants.CHARACTER_WIDTH + Constants.CHARACTER_SPACEING) * 2, Constants.CHARACTER_BUTTON_Y,
                Constants.CHARACTER_WIDTH, Constants.CHARACTER_HEIGHT);

        // Lock the elephant character.
        elephantBtn.setLocked(true);
        characters.add(elephantBtn);

        lemurBtn = new CharacterButton("Lemur", "lemur.png",
                startX + (Constants.CHARACTER_WIDTH + Constants.CHARACTER_SPACEING) * 3, Constants.CHARACTER_BUTTON_Y,
                Constants.CHARACTER_WIDTH, Constants.CHARACTER_HEIGHT);

        // Lock the lemur character.
        lemurBtn.setLocked(true);
        characters.add(lemurBtn);

        bearBtn = new CharacterButton("Gobi Bear", "gobi bear.png", startX, Constants.CHARACTER_BUTTON_Y + 150,
                Constants.CHARACTER_WIDTH, Constants.CHARACTER_HEIGHT);

        // Lock the gobi bear character.
        bearBtn.setLocked(true);
        characters.add(bearBtn);

        pandaBtn = new CharacterButton("Red Panda", "red panda.png", startX + Constants.CHARACTER_WIDTH + Constants.CHARACTER_SPACEING, Constants.CHARACTER_BUTTON_Y + 150,
                Constants.CHARACTER_WIDTH, Constants.CHARACTER_HEIGHT);

        // Lock the red panda character.
        pandaBtn.setLocked(true);
        characters.add(pandaBtn);

        foxBtn = new CharacterButton("Arctic Fox", "arctic fox.png", startX + (Constants.CHARACTER_WIDTH + Constants.CHARACTER_SPACEING) * 2, Constants.CHARACTER_BUTTON_Y + 150,
                Constants.CHARACTER_WIDTH, Constants.CHARACTER_HEIGHT);

        // Lock the arctic fox character.
        foxBtn.setLocked(true);
        characters.add(foxBtn);

        leopardBtn = new CharacterButton("Leopard", "leopard.png", startX + (Constants.CHARACTER_WIDTH + Constants.CHARACTER_SPACEING) * 3, Constants.CHARACTER_BUTTON_Y + 150,
                Constants.CHARACTER_WIDTH, Constants.CHARACTER_HEIGHT);

        // Lock the leopard character.
        leopardBtn.setLocked(true);
        characters.add(leopardBtn);
    }

    // Initialise the buttons.
    private void initButtons() {
        // Set the width of the row that the buttons are going to be displayed.
        int totalWidth = (Constants.BUTTON_WIDTH * 2) + Constants.BUTTON_SPACEING;
        // Set the starting X position for the button display.
        int startX = Constants.BUTTON_X - (totalWidth / 2);

        // Initialise the buttons.
        playBtn = new MenuButton("Play", "play button.png", "play button hover.png", startX, Constants.BUTTON_Y + 70,
                Constants.BUTTON_WIDTH, Constants.BUTTON_HEIGHT);
        backBtn = new MenuButton("Back", "back button.png", "back button hover.png",
                startX + Constants.BUTTON_WIDTH + Constants.BUTTON_SPACEING, Constants.BUTTON_Y + 70, Constants.BUTTON_WIDTH,
                Constants.BUTTON_HEIGHT);
    }

    // Draw the character menu.
    public void drawCharacterMenu(Graphics2D g) {
        // Draw the background.
        g.drawImage(bg.getImage(), bg.getX(), bg.getY(), bg.getWidth(), bg.getHeight(), null);
        //g.setColor(Constants.DARK_GREEN);
        //g.fillRect(0, 0, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);

        // Draw the text on the background.
        g.setColor(Constants.GOLD);
        g.setFont(Constants.CHARACTER_MENU_FONT);
        String title = "Select Two Characters";
        int titleWidth = g.getFontMetrics().stringWidth(title);
        g.drawString(title, Constants.SCREEN_CENTER - titleWidth / 2, 120);

        // Draw the selected animals counter.
        g.setFont(Constants.QUESTION_FONT);
        String countText = "Selected: " + selectedCharacters.size() + "/2";
        int countWidth = g.getFontMetrics().stringWidth(countText);
        g.drawString(countText, Constants.SCREEN_CENTER - countWidth / 2, 160);

        for (CharacterButton character : characters) {
            // Draw the character buttons
            character.drawButton(g);

            // If the character is locked than draw a lock image with a transparent background over the character.
            if (character.isLocked()) {
                lockX = character.getX() + (character.getWidth() - Constants.LOCK_WIDTH) / 2;
                lockY = character.getY() + (character.getHeight() - Constants.LOCK_HEIGHT) / 2;

                g.setColor(Constants.TRANS_BLACK);
                g.fillRect(character.getX(), character.getY(), character.getWidth(), character.getHeight());

               g.drawImage(lockImg, lockX, lockY, Constants.LOCK_WIDTH, Constants.LOCK_HEIGHT, null);
            }
        }

        // Draw the play/back buttons.
        playBtn.drawButton(g);
        backBtn.drawButton(g);
    }

    // Event listener for the mouse.
    public void mouseClicked(MouseEvent e) {
        // Handle character selection.
        for (CharacterButton character : characters) {
            if (character.contains(e.getX(), e.getY())) {
                // If the character os locked don't let the user select it.
                if (character.isLocked()){
                    return;
                }
                // Otherwise add the character's name to the selectedCharacters list and remove it from the characters one.
                if (character.isSelected()) {
                    character.setSelected(false);
                    selectedCharacters.remove(character.getName());
                } 
                // If the players already choose 2 characters than don't allow them to choose another one.
                else if (selectedCharacters.size() < Constants.MAX_SELECTIONS) {
                    character.setSelected(true);
                    selectedCharacters.add(character.getName());
                }
                return;
            }
        }
    }

    // Method that unlockes a new character once the riddleScore is increased during one run of the whole game.
    public void updateRiddleScore(int newScore){
        if (newScore > riddleScore) {
            riddleScore = newScore;

            if (riddleScore >= 3) {
                elephantBtn.setLocked(false);
            }

            if (riddleScore >= 6) {
                lemurBtn.setLocked(false);
            }

            if (riddleScore >= 9) {
                bearBtn.setLocked(false);
            }

            if (riddleScore >= 12) {
                pandaBtn.setLocked(false);
            }

            if (riddleScore >= 15) {
                foxBtn.setLocked(false);
            }

            if (riddleScore >= 18) {
                leopardBtn.setLocked(false);
            }
        }
    }

    // Set the hovered variable if the mouse is on the buttons.
    public void mouseMoved(MouseEvent e) {
        playBtn.setHovered(playBtn.contains(e.getX(), e.getY()));
        backBtn.setHovered(backBtn.contains(e.getX(), e.getY()));
    }

    // Check if the play/back buttons were clicked.
    public boolean playButtonClicked(MouseEvent e) {
        return playBtn.contains(e.getX(), e.getY()) && selectedCharacters.size() == Constants.MAX_SELECTIONS;
    }

    public boolean backButtonClicked(MouseEvent e) {
        return backBtn.contains(e.getX(), e.getY());
    }

    // Getter
    public ArrayList<String> getSelectedCharacters() {
        return selectedCharacters;
    }

    // Reset the animal selection for every game.
    public void resetSelection() {
        selectedCharacters.clear();
        for (CharacterButton character : characters) {
            character.setSelected(false);
        }
    }

   
}