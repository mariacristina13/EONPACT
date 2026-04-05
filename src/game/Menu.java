package game;

import java.awt.Graphics2D;

import java.awt.event.MouseEvent;

import constants.Constants;

public class Menu {
    // Class variables.
    private MenuButton characterBtn;
    private MenuButton quitBtn;

    public Menu(){
        initButtons();
    }

    // Initialise buttons
    private void initButtons() {
        // Set the width of the row that the buttons are going to be displayed.
        int totalWidth = (Constants.BUTTON_WIDTH * 2) + Constants.BUTTON_SPACEING;
        // Set the starting X position for the character display.
        int startX = Constants.BUTTON_X - (totalWidth / 2);

        // Initialise buttons.
        characterBtn = new MenuButton("Characters", startX, Constants.BUTTON_Y, Constants.BUTTON_WIDTH, Constants.BUTTON_HEIGHT);
        quitBtn = new MenuButton("Quit", startX + Constants.BUTTON_WIDTH + Constants.BUTTON_SPACEING, Constants.BUTTON_Y, Constants.BUTTON_WIDTH, Constants.BUTTON_HEIGHT);
    }

}
