package game;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import constants.Constants;
import ui.Button;

public class CharacterButton extends Button {
    // Class variables.
    private String name;
    private boolean locked;
    private BufferedImage characterImg;
    private boolean selected = false;

    // Initialise the class variables
    public CharacterButton(String name, String fileName, int x, int y, int width, int height) {
        super(x, y, width, height);
        this.name = name;
        loadImages(fileName);
    }

    // Load the images for the character menu.
    public void loadImages(String fileName) {
        try {
            characterImg = ImageIO.read(new File("images/" + fileName));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.print("file not found");
        }
    }

    // Draw a box around the buttons for the animals if they were selected and the text on the background.
    @Override
    public void drawButton(Graphics2D g) {
        if (selected) {
            g.setColor(Constants.GOLD);
            g.fillRect(getX() - 5, getY() - 5, getWidth() + 10, getHeight() + 10);
        }

        if (characterImg != null) {
            g.drawImage(characterImg, getX(), getY(), getWidth(), getHeight(), null);
        }

        g.setColor(Constants.GOLD);
        g.setFont(Constants.CHARACTER_NAME_FONT);

        int textWidth = g.getFontMetrics().stringWidth(name);
        g.drawString(name, getX() + (getWidth() - textWidth) / 2, getY() + getHeight() + 25);
    }

    // Getters and setters.
    public void setSelected(boolean selected) {
        this.selected = selected;
    }
    
    public void setLocked(boolean locked){
        this.locked = locked;
    }

    public boolean isSelected() {
        return selected;
    }

    public String getName() {
        return name;
    }

    public boolean isLocked(){
        return locked;
    }

     // Helper methods for testing.
    public boolean isElephantUnlocked(int score){
        return score >= 3;
    }

    public boolean isLemmurUnlocked(int score){
        return score >= 6;
    }

    public boolean isBearUnlocked(int score){
        return score >= 9;
    }

    public boolean isPandaUnlocked(int score){
        return score >= 12;
    }

    public boolean isFoxUnlocked(int score){
        return score >= 15;
    }

    public boolean isLeopardUnlocked(int score){
        return score >= 18;
    }
}
