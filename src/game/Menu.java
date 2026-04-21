package game;

import java.awt.Graphics2D;

import java.awt.event.MouseEvent;

import Sprites.Background;
import constants.Constants;

public class Menu {
    // Class variables.
    private MenuButton characterBtn;
    private MenuButton quitBtn;
    public Background bg;

    public Menu() {
        initButtons();
        bg = new Background("menu_bg.png", 0, Constants.SCREEN_HEIGHT, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
    }

    // Initialise buttons
    private void initButtons() {
        // Set the width of the row that the buttons are going to be displayed.
        int totalWidth = (Constants.BUTTON_WIDTH * 2) + Constants.BUTTON_SPACEING;
        // Set the starting X position for the character display.
        int startX = Constants.BUTTON_X - (totalWidth / 2);

        // Initialise buttons.
        characterBtn = new MenuButton("Characters", "animals button.png", "animals button hover.png", startX,
                Constants.BUTTON_Y, Constants.BUTTON_WIDTH, Constants.BUTTON_HEIGHT);
        quitBtn = new MenuButton("Quit", "quit button.png", "quit button hover.png",
                startX + Constants.BUTTON_WIDTH + Constants.BUTTON_SPACEING, Constants.BUTTON_Y, Constants.BUTTON_WIDTH,
                Constants.BUTTON_HEIGHT);
    }

    // Draw the menu display.
    public void drawMenu(Graphics2D g) {
        // Draw the background.
        g.drawImage(bg.getImage(), bg.getX(), bg.getY(), bg.getWidth(), bg.getHeight(), null);
        //g.setColor(Constants.DARK_GREEN);
        //g.fillRect(0, 0, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);

        // Draw the title of the game.
        g.setColor(Constants.GOLD);
        g.setFont(Constants.GAME_FONT);
        String title = "EONPACT";
        int titleWidth = g.getFontMetrics().stringWidth(title);
        g.drawString(title, Constants.SCREEN_CENTER - titleWidth / 2, 200);

        // Draw the buttons.
        characterBtn.drawButton(g);
        quitBtn.drawButton(g);
    }

    // Event listener for the mouse.
    public void mouseMoved(MouseEvent e) {
        characterBtn.setHovered(characterBtn.contains(e.getX(), e.getY()));
        quitBtn.setHovered(quitBtn.contains(e.getX(), e.getY()));
    }

    // Check if the play/quit buttons were pressed.
    public boolean characterButtonClicked(MouseEvent e) {
        return characterBtn.contains(e.getX(), e.getY());
    }

    public boolean quitButtonClicked(MouseEvent e) {
        return quitBtn.contains(e.getX(), e.getY());
    }
}