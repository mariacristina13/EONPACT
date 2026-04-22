package game;

import constants.Constants;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;

import Sprites.Background;

public class GameLost {
    // Class variables.
    private MenuButton menuBtn;
    private MenuButton quitBtn;
    public Background background;

    public GameLost() {
        initButtons();

        // Initialse the backgroeund image.
        background = new Background("menu_bg.png", 0, Constants.SCREEN_HEIGHT, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
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

    // Draw the game lost screen.
    public void drawGameLost(Graphics2D g){
        // Draw the background.
        g.drawImage(background.getImage(), background.getX(), background.getY(), background.getWidth(), background.getHeight(), null);

        // Draw the title of the game.
        g.setColor(Constants.GOLD);
        g.setFont(Constants.GAME_FONT);
        String title = "Game Lost!";
        int titleWidth = g.getFontMetrics().stringWidth(title);
        g.drawString(title, Constants.SCREEN_CENTER - titleWidth / 2, 200);

        // Draw the buttons.
        menuBtn.drawButton(g);
        quitBtn.drawButton(g);
    }

    // Event listener for the mouse.
    public void mouseMoved(MouseEvent e) {
        menuBtn.setHovered(menuBtn.contains(e.getX(), e.getY()));
        quitBtn.setHovered(quitBtn.contains(e.getX(), e.getY()));
    }

    // Check if the menu buttons were pressed.
    public boolean menuButtonClicked(MouseEvent e) {
        return menuBtn.contains(e.getX(), e.getY());
    }
}
