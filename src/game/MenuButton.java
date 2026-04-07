package game;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import constants.Constants;
import ui.Button;

public class MenuButton extends Button {
    // Class variable.
    private String text;
    private String normalImgFile;
    private String hoveredImgFile;
    private BufferedImage normalImg;
    private BufferedImage hoveredImg;

    public MenuButton(String text, String normalImgFile, String hoveredImgFile, int x, int y, int width, int height) {
        super(x, y, width, height);
        this.text = text;
        this.normalImgFile = normalImgFile;
        this.hoveredImgFile = hoveredImgFile;

        loadImages(normalImgFile, hoveredImgFile);
    }

     // Load the images for the main menu buttons
    public void loadImages(String normalImgFile, String hoveredImgFile){
        try{
            normalImg = ImageIO.read(new File("images/" + normalImgFile));
        }
        catch(IOException e){
            e.printStackTrace();
        }

        try{
            hoveredImg = ImageIO.read(new File("images/" + hoveredImgFile));
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    // Draw the characters/quit buttons.
    @Override
    public void drawButton(Graphics2D g) {
        // Draw the button image based on the hover state.
        if (isHovered() && getHoveredImg() != null) {
            g.drawImage(getHoveredImg(), getX(), getY(), getWidth(), getHeight(), null);
        } 
        else if (getNormalImg() != null) {
            g.drawImage(getNormalImg(), getX(), getY(), getWidth(), getHeight(), null);
        } 
        else {
            // If the images fail to load use the previous version of the buttons.
            if (isHovered()) {
                g.setColor(Constants.MAROON);
            } else {
                g.setColor(Constants.BROWN_RED);
            }
            g.fillRect(getX(), getY(), getWidth(), getHeight());
            
            g.setColor(Constants.WHITE);
            g.setFont(Constants.BUTTON_FONT);
        
            int textWidth = g.getFontMetrics().stringWidth(text);
            int textHeight = g.getFontMetrics().getAscent();
 
            g.drawString(text, getX() + (getWidth() - textWidth) / 2, getY() + (getHeight() + textHeight) / 2);
        }
    }

    // Getters and setters
    public String getText() {
        return text;
    }

    public String getNormalImgFile() {
        return normalImgFile;
    }

    public void setNormalImgFile(String normalImgFile) {
        this.normalImgFile = normalImgFile;
    }

    public String getHoveredImgFile() {
        return hoveredImgFile;
    }

    public void setHoveredImgFile(String hoveredImgFile) {
        this.hoveredImgFile = hoveredImgFile;
    }

    public void setText(String text) {
        this.text = text;
    }  

    public BufferedImage getNormalImg(){
        return normalImg;
    }

    public BufferedImage getHoveredImg(){
        return hoveredImg;
    }    

}