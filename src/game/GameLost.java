package game;

import constants.Constants;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;

import Sprites.Background;

public class GameLost {
    // Class variables.
    private MenuButton menuBtn;
    private MenuButton quitBtn;
    public Background bg;

    public GameLost() {
        initButtons();
    }

    // Initialise the buttons.
    private void initButtons() {
        // Set the width of the row that the buttons are going to be displayed.
        int totalWidth = (Constants.BUTTON_WIDTH * 2) + Constants.BUTTON_SPACEING;
        // Set the starting X position for the character display.
        int startX = Constants.BUTTON_X - (totalWidth / 2);

        menuBtn = new MenuButton("Menu", "menu button.png", "menu button hover.png", startX,
                Constants.BUTTON_Y, Constants.BUTTON_WIDTH, Constants.BUTTON_HEIGHT);
        quitBtn = new MenuButton("Quit", "quit button.png", "quit button hover.png",
                startX + Constants.BUTTON_WIDTH + Constants.BUTTON_SPACEING, Constants.BUTTON_Y, Constants.BUTTON_WIDTH,
                Constants.BUTTON_HEIGHT);
    }
}
