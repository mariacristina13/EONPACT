package game;

import java.awt.Graphics2D;

import java.awt.event.MouseEvent;
import java.util.ArrayList;

import Sprites.Background;
import Sprites.Map;
import constants.Constants;

public class Menu {
    // Class variables.
    private MenuButton characterBtn;
    private MenuButton quitBtn;
    public Background bg;
    public ArrayList<Map> characters;
    private Map randomCharacter;
    public Map logo;

    public Menu() {
        initButtons();
        bg = new Background("menu_bg.png", 0, Constants.SCREEN_HEIGHT, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
        characters = new ArrayList<Map>();
        characters.add(new Map("leopard 2.png", 50, 700,510 , 178));
        characters.add(new Map("bear.png", 50, 700,510 , 178));
        characters.add(new Map("arctic fox 2.png", 50, 700,510 , 178));
        selectCharacter();

        logo = new Map("logo.png", 225,Constants.SCREEN_CENTER-50,850 , 590);
    }

    // Initialise buttons
    private void initButtons() {
        // Set the width of the row that the buttons are going to be displayed.
        int totalWidth = (Constants.BUTTON_WIDTH * 2) + Constants.BUTTON_SPACEING;
        // Set the starting X position for the character display.
        int startX = Constants.BUTTON_X - (totalWidth / 2);

        // Initialise buttons.
        characterBtn = new MenuButton("Characters", "animals button.png", "animals button hover.png", startX,
                Constants.BUTTON_Y + 30, Constants.BUTTON_WIDTH, Constants.BUTTON_HEIGHT);
        quitBtn = new MenuButton("Quit", "quit button.png", "quit button hover.png",
                startX + Constants.BUTTON_WIDTH + Constants.BUTTON_SPACEING, Constants.BUTTON_Y + 30, Constants.BUTTON_WIDTH,
                Constants.BUTTON_HEIGHT);
    }

    // Draw the menu display.
    public void drawMenu(Graphics2D g) {
        // Draw the background.
        g.drawImage(bg.getImage(), bg.getX(), bg.getY(), bg.getWidth(), bg.getHeight(), null);
        displayCharacter(g);

        // Draw the title of the game.
        g.drawImage(logo.getImage(), logo.getX(), logo.getY(), logo.getWidth(), logo.getHeight(), null);

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

    // Method to select random character from arraylist
    public void selectCharacter(){
        int x = (int)(Math.random()*characters.size());
        randomCharacter = characters.get(x);
    }

    //Method to display selected character 
    public void displayCharacter(Graphics2D g){
        g.drawImage(randomCharacter.getImage(), randomCharacter.getX(), randomCharacter.getY(), randomCharacter.getWidth(), randomCharacter.getHeight(), null);
    }

    //Method to reset menu = select new random character
    public void resetMenu() {
        selectCharacter(); 
    }    
}