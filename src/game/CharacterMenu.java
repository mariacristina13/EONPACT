package game;

import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import constants.Constants;

public class CharacterMenu {
    // Class variables.
    private ArrayList<CharacterButton> characters;
    private ArrayList<String> selectedCharacters;
    private MenuButton playBtn;
    private MenuButton backBtn;
    private int maxSelections = 2;

    // Initialise class variables.
    public CharacterMenu() {
        selectedCharacters = new ArrayList<String>();
        initCharacters();
        initButtons();
    }

    // Inithialise the character buttons.
    private void initCharacters() {
        characters = new ArrayList<CharacterButton>();

        // Set the width of the row that the characters are going to be displayed.
        int totalWidth = (Constants.CHARACTER_WIDTH * 4) + (Constants.CHARACTER_SPACEING * 3);
        // Set the starting X position for the character display.
        int startX = Constants.CHARACTER_BUTTON_X - (totalWidth / 2);

        // Initialise characters.
        characters.add(new CharacterButton("Box Turtle", "box turtle.png", startX, Constants.CHARACTER_BUTTON_Y, Constants.CHARACTER_WIDTH, Constants.CHARACTER_HEIGHT));

        characters.add(new CharacterButton("Kakapo", "kakapo.png", startX + Constants.CHARACTER_WIDTH + Constants.CHARACTER_SPACEING, Constants.CHARACTER_BUTTON_Y, Constants.CHARACTER_WIDTH, Constants.CHARACTER_HEIGHT));

        characters.add(new CharacterButton("African Forest Elephant", "african forest elephant.png", startX + (Constants.CHARACTER_WIDTH + Constants.CHARACTER_SPACEING) * 2, Constants.CHARACTER_BUTTON_Y, Constants.CHARACTER_WIDTH, Constants.CHARACTER_HEIGHT));

        characters.add(new CharacterButton("Lemur", "lemur.png", startX + (Constants.CHARACTER_WIDTH + Constants.CHARACTER_SPACEING) * 3, Constants.CHARACTER_BUTTON_Y, Constants.CHARACTER_WIDTH, Constants.CHARACTER_HEIGHT));
    }

    // Initialise the buttons.
    private void initButtons() {
        // Set the width of the row that the buttons are going to be displayed.
        int totalWidth = (Constants.BUTTON_WIDTH * 2) + Constants.BUTTON_SPACEING;
        // Set the starting X position for the button display.
        int startX = Constants.BUTTON_X - (totalWidth / 2);

        // Initialise the buttons.
        playBtn = new MenuButton("Play", startX, Constants.BUTTON_Y, Constants.BUTTON_WIDTH, Constants.BUTTON_HEIGHT);
        backBtn = new MenuButton("Back", startX + Constants.BUTTON_WIDTH + Constants.BUTTON_SPACEING, Constants.BUTTON_Y, Constants.BUTTON_WIDTH, Constants.BUTTON_HEIGHT);
    }

    // Draw the character menu.
    public void draw(Graphics2D g) {
        // Draw the background.
        g.setColor(Constants.DARK_GREEN);
        g.fillRect(0, 0, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);

        // Draw the text on the background.
        g.setColor(Constants.WHITE);
        g.setFont(Constants.CHARACTER_MENU_FONT);
        String title = "Select Your Characters";
        int titleWidth = g.getFontMetrics().stringWidth(title);
        g.drawString(title, Constants.SCREEN_CENTER - titleWidth / 2, 120);

        // Draw the selected animals counter.
        g.setFont(Constants.QUESTION_FONT);
        String countText = "Selected: " + selectedCharacters.size() + "/2";
        int countWidth = g.getFontMetrics().stringWidth(countText);
        g.drawString(countText, Constants.SCREEN_CENTER - countWidth / 2, 160);

        // Draw the character buttons
        for (CharacterButton character : characters) {
            character.drawButton(g);
        }

        // Draw the play/back buttons.
        playBtn.drawButton(g);
        backBtn.drawButton(g);
    }

    // Event listener for the mouse.
    public void mouseClicked(MouseEvent e) {
        // Handle character selection (toggle)
        for (CharacterButton character : characters) {
            if (character.contains(e.getX(), e.getY())) {
                if (character.isSelected()) {
                    character.setSelected(false);
                    selectedCharacters.remove(character.getName());
                } 
                else if (selectedCharacters.size() < maxSelections) {
                    character.setSelected(true);
                    selectedCharacters.add(character.getName());
                }
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
        return playBtn.contains(e.getX(), e.getY()) && selectedCharacters.size() == 2;
    }

    public boolean backButtonClicked(MouseEvent e) {
        return backBtn.contains(e.getX(), e.getY());
    }

    // Getter
    public ArrayList<String> getSelectedCharacters() {
        return selectedCharacters;
    }

}