package game;

import constants.Constants;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;

import Sprites.Background;
import Sprites.Decor;

public class GameOver {
    // Class variables.
    private MenuButton menuBtn;
    private MenuButton quitBtn;
    private Background background;
    private boolean gameWon;
    private Decor gameLost;
    private Decor gameWonImg;
    private Decor panda;
    private Decor win;

    public GameOver() {
        initButtons();

        // Initialse the backgroeund image.
        background = new Background("menu_bg.png", 0, Constants.SCREEN_HEIGHT, Constants.SCREEN_WIDTH,
                Constants.SCREEN_HEIGHT);
        gameLost = new Decor("game lost.png", 420, Constants.SCREEN_CENTER - 400, 445, 180);
        gameWonImg = new Decor("game won.png", 420, Constants.SCREEN_CENTER - 400, 445, 180);
        panda = new Decor("sad panda.png", 500, Constants.SCREEN_CENTER - 180, 270, 202);
        win = new Decor("win.png", 500, Constants.SCREEN_CENTER - 180, 270, 202);
    }

    // Initialise the buttons.
    private void initButtons() {
        // Set the width of the row that the buttons are going to be displayed.
        int totalWidth = (Constants.BUTTON_WIDTH * 2) + Constants.BUTTON_SPACEING;
        // Set the starting X position for the character display.
        int startX = Constants.BUTTON_X - (totalWidth / 2);

        menuBtn = new MenuButton("Menu", "menu button.png", "menu button hover.png", startX,
                Constants.BUTTON_Y + 50, Constants.BUTTON_WIDTH, Constants.BUTTON_HEIGHT);
        quitBtn = new MenuButton("Quit", "quit button.png", "quit button hover.png",
                startX + Constants.BUTTON_WIDTH + Constants.BUTTON_SPACEING, Constants.BUTTON_Y + 50,
                Constants.BUTTON_WIDTH,
                Constants.BUTTON_HEIGHT);
    }

    // Draw the game over screen.
    public void drawGameOver(Graphics2D g) {
        // Draw the background.
        g.drawImage(background.getImage(), background.getX(), background.getY(), background.getWidth(),
                background.getHeight(), null);

        // Message typography and color.
        /*
         * g.setColor(Constants.GOLD);
         * g.setFont(Constants.GAME_FONT);
         */

        if (!gameWon) {
            // Draw the game over message.
            g.drawImage(gameLost.getImage(), gameLost.getX(), gameLost.getY(), gameLost.getWidth(),
                    gameLost.getHeight(), null);
            g.drawImage(panda.getImage(), panda.getX(), panda.getY(), panda.getWidth(), panda.getHeight(), null);

        } else if (gameWon) {
            // Draw the game won message.
            g.drawImage(gameWonImg.getImage(), gameWonImg.getX(), gameWonImg.getY(), gameWonImg.getWidth(),
                    gameWonImg.getHeight(), null);
            g.drawImage(win.getImage(), win.getX(), win.getY(), win.getWidth(), win.getHeight(), null);
        }

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

    // Check if the quit button was pressed.
    public boolean quitButtonClicked(MouseEvent e) {
        return quitBtn.contains(e.getX(), e.getY());
    }

    public void setGameWon(boolean gameWon) {
        this.gameWon = gameWon;
    }
}
