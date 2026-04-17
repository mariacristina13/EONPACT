package game;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;

import constants.Constants;

public class MyPanel extends JPanel implements KeyListener, MouseListener, MouseMotionListener {
    private GameManager game;
    private Menu menuScreen;
    private CharacterMenu characterMenu;
    GameOverMenu gameOverScreen;
    private GameStates currentState;

    public MyPanel() {
        addKeyListener(this);
        addMouseListener(this);
        addMouseMotionListener(this);

        // Initialise the game.
        game = new GameManager();
        // Initialise the main menu.
        menuScreen = new Menu();
        // Initialise the character menu.
        characterMenu = new CharacterMenu();
        // Set the initial state.
        currentState = GameStates.MENU;
        // Initialise the game over screen.
        gameOverScreen = new GameOverMenu();
    }

    // https://projectai.in/projects/e79f02df-4d51-473e-90f0-4ff8443ff473/tasks/5b55ecc1-ac91-4092-8e63-097ce794218b?tab=task
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D graphics = (Graphics2D) g;
        // improve rendering quality
        RenderingHints hints = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics.setRenderingHints(hints); // https://docs.oracle.com/javase/8/docs/api/javax/swing/package-summary.html

        switchStates(graphics);
    }

    // Draw the specific display, based on the state that the game is in.
    private void switchStates(Graphics2D graphics) {

        switch (currentState) {
            case MENU:
                menuScreen.drawMenu(graphics);
                break;
            case CHARACTER_SELECT:
                characterMenu.draw(graphics);
                break;
            case PLAYING:
                drawGame(graphics);
                break;
            case GAME_OVER:
               gameOverScreen.drawGameOver(graphics);
                break;
        }
    }

    // Draw the game when the PLAYING state is reached.
    private void drawGame(Graphics2D g) {
        // Add the backgound.
        game.drawBG(g, this);
        // Add the animals and checkpoints.
        game.drawSprites(g, this);
        // Add the riddle card.
        game.drawRiddle(g, getWidth(), getHeight());
        // Add the timer.
        drawTimer(g);
        game.drawCounter(g);
    }

    // Draw the timer.
    private void drawTimer(Graphics2D graphics) {
        String timeText = "Time remaining: " + game.getTimer();

        graphics.setFont(Constants.TIMER_FONT);

        int textWidth = graphics.getFontMetrics().stringWidth(timeText);
        int padding = 10;
        int x = getWidth() - textWidth - padding * 2 - 30;
        int y = 70;

        graphics.setColor(Constants.GOLD);
        graphics.drawString(timeText, x, y);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        game.keyTyped(e.getKeyChar());
        this.repaint();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        game.keyPressed(e.getKeyCode());
        this.repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {
        game.keyReleased(e.getKeyCode());
        this.repaint();
    }

    // Event listener for the mouse.
    public void mousePressed(MouseEvent e) {
        // Switch between game states.
        switch (currentState) {
            case MENU:
                // Check if the Animals button was clicked.
                if (menuScreen.characterButtonClicked(e)) {
                    characterMenu.updateRiddleScore(game.getCompletedCheckpoints());
                    // Change the current state.
                    currentState = GameStates.CHARACTER_SELECT;
                }
                // Check if the quit button was clicked.
                if (menuScreen.quitButtonClicked(e)) {
                    // https://forums.oracle.com/ords/apexds/post/closing-a-swing-app-through-menu-file-exit-5345
                    // Exit the game.
                    System.exit(0);
                }
                break;
            case CHARACTER_SELECT:
                // Add the MouseClicked event listener to the character menu screen to handle
                // selecting and deselecting characters.
                characterMenu.mouseClicked(e);

                // Check if the play button was clicked.
                if (characterMenu.playButtonClicked(e)) {
                    // Update the current state.
                    currentState = GameStates.PLAYING;
                    // Initialise the game with the characters sdelected by the players.
                    game.initializeGame(characterMenu.getSelectedCharacters());
                }
                // Check if the back button was clicked.
                if (characterMenu.backButtonClicked(e)) {
                    // Update the current state of the game.
                    currentState = GameStates.MENU;
                    // If the players chose a character and then went back to the main menu, reset
                    // the selected characters.
                    characterMenu.resetSelection();
                }
                break;
            case PLAYING:
                // Add the MouseClicked event listener to the game manager to handle any button
                // interaction in the game.
                game.mouseClicked(e.getX(), e.getY(), getWidth(), getHeight());
                break;

            case GAME_OVER:
                // Check if the play button was clicked.
                if(gameOverScreen.menuButtonClicked(e)){
                    // Update the game state.
                    currentState = GameStates.MENU;
                }

                // Exit the game.
                if(gameOverScreen.quitButtonClicked(e)){
                    System.exit(0);
                }
                break;
            default:
                currentState = GameStates.MENU;
                break;
        }
        this.repaint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    public void mouseMoved(MouseEvent e) {
        switch (currentState) {
            case MENU:
                menuScreen.mouseMoved(e);
                break;
            case CHARACTER_SELECT:
                characterMenu.mouseMoved(e);
                break;
            case GAME_OVER:
                gameOverScreen.mouseMoved(e);
            default:
                break;
        }
        this.repaint();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mouseDragged(MouseEvent e) {
    }

    // Update the game when in the PLAYING state.
    public void update() {
        if (currentState == GameStates.PLAYING) {
            game.update();
        }
        this.repaint();
    }

    // Getters
    public GameManager getGameManager() {
        return game;
    }

    public GameStates getCurrentState() {
        return currentState;
    }

    // Setter
    public void setCurrentState(GameStates state) {
        currentState = state;
    }
}
