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

}