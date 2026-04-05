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
    private GameStates currentState;

    public MyPanel() {
        addKeyListener(this);
        addMouseListener(this);
        addMouseMotionListener(this);

        game = new GameManager();
    }

    // https://projectai.in/projects/e79f02df-4d51-473e-90f0-4ff8443ff473/tasks/5b55ecc1-ac91-4092-8e63-097ce794218b?tab=task
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D graphics = (Graphics2D) g;
        // improve rendering quality
        RenderingHints hints = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics.setRenderingHints(hints); // https://docs.oracle.com/javase/8/docs/api/javax/swing/package-summary.html
    }

    // Draw the game when the PLAYING state is reached.
        private void drawGame(Graphics2D g) {
            // Add the backgound.
            drawBG(g);
            // Add the animals and checkpoints.
            game.drawSprites(g, this);
            // Add the riddle card.
            game.drawRiddle(g, getWidth(), getHeight());
            // Add the timer.
            drawTimer(g);
        }

    // Draw the timer.
    private void drawTimer(Graphics2D graphics){
        String timeText = "Time remaining: " + game.getTimer();

        graphics.setFont(Constants.TIMER_FONT);

        
        int textWidth = graphics.getFontMetrics().stringWidth(timeText);
        int padding = 10;
        int x = getWidth() - textWidth - padding * 2 - 30;
        int y = 30;

        graphics.setColor(Constants.GREEN);
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
                if (menuScreen.playButtonClicked(e)) {
                    currentState = GameStates.CHARACTER_SELECT;
                }
                if (menuScreen.quitButtonClicked(e)) {
                    System.exit(0);
                }
                break;
            case CHARACTER_SELECT:
                characterMenu.mouseClicked(e);
 
                if (characterMenu.playButtonClicked(e)) {
                    currentState = GameStates.PLAYING;
                    game.initializeGame(characterMenu.getSelectedCharacters());
                }
 
                if (characterMenu.backButtonClicked(e)) {
                    currentState = GameStates.MENU;
                    characterMenu.resetSelection();
                }
                break;
            case PLAYING:
                game.mouseClicked(e.getX(), e.getY());
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
            default:
                currentState = GameStates.MENU;
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

    public void drawBG(Graphics2D graphics) {
        graphics.setColor(Constants.BLUE);
        graphics.fillRect(0, 0, Constants.SCREEN_SIZE.width, Constants.SCREEN_SIZE.height);

        graphics.setColor(Constants.GREEN);
        graphics.fillRect(0, Constants.GROUND_HEIGHT, Constants.SCREEN_SIZE.width, Constants.SCREEN_SIZE.height);

    }

    // Getters
    public GameManager getGameManager() {
        return game;
    }

    public GameStates getCurrentState() {
        return currentState;
    }
}
