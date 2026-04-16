package game;

import constants.Constants;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;

public class GameOverMenu {
    // Class variables.
    private MenuButton menuBtn;
    private MenuButton quitBtn;

    public GameOverMenu() {
        initButtons();
    }

    // Initialise the buttons.
    private void initButtons() {
        // Set the width of the row that the buttons are going to be displayed.
        int totalWidth = (Constants.BUTTON_WIDTH * 2) + Constants.BUTTON_SPACEING;
        // Set the starting X position for the character display.
        int startX = Constants.BUTTON_X - (totalWidth / 2);

        menuBtn = new MenuButton("Menu", "menu button.png", "menu hover button.png", startX, Constants.BUTTON_Y,
                Constants.BUTTON_WIDTH, Constants.BUTTON_X);
        quitBtn = new MenuButton("Quit", "quit button.png", "quit button hover.png",
                startX + Constants.BUTTON_WIDTH + Constants.BUTTON_SPACEING, Constants.BUTTON_Y, Constants.BUTTON_WIDTH,
                Constants.BUTTON_HEIGHT);
    }

    public void drawGameOver(Graphics2D g){
        // Draw the background.
        g.setColor(Constants.DARK_GREEN);
        g.fillRect(0, 0, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);

        // Draw the title of the game.
        g.setColor(Constants.GOLD);
        g.setFont(Constants.GAME_FONT);
        String title = "EONPACT";
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
    public boolean characterButtonClicked(MouseEvent e) {
        return menuBtn.contains(e.getX(), e.getY());
    }

    // Check if the quit button was pressed.
    public boolean quitButtonClicked(MouseEvent e) {
        return quitBtn.contains(e.getX(), e.getY());
    }
}
