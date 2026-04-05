package game;

import java.awt.Graphics2D;

import constants.Constants;
import ui.Button;

public class MenuButton extends Button {
    // Class variable.
    private String text;

    public MenuButton(String text, int x, int y, int width, int height) {
        super(x, y, width, height);
        this.text = text;
    }

    // Draw the characters/quit buttons.
    @Override
    public void drawButton(Graphics2D g) {
         if(isHovered()){
            g.setColor(Constants.MAROON);
        }
        else{
            g.setColor(Constants.BROWN_RED);
        }

        g.fillRect(getX(), getY(), getWidth(), getHeight());
        
        g.setColor(Constants.WHITE);
        g.setFont(Constants.BUTTON_FONT);
    
        int textWidth = g.getFontMetrics().stringWidth(text);
        int textHeight = g.getFontMetrics().getAscent();

        g.drawString(text, getX() +(getWidth() - textWidth)/2, getY() +(getHeight() + textHeight)/2);
    }

    // Getters and setters
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }  

}