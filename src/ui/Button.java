package ui;

import java.awt.Graphics2D;

public abstract class Button {
    // Class variables.
    private int x;
    private int y;
    private int width;
    private int height;
    
   
    // Initialise class variables.
    public Button(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    // Abstract method for drawing the buttons.
    public abstract void drawButton(Graphics2D g);

    // Check if the mouse is on the button.
    public boolean contains(int mouseX, int mouseY) {
        return mouseX >= x && mouseX <= x + width && mouseY >= y && mouseY <= y + height;
    }

    // Getters
    public int getX() {
        return x;
    }
    
    public int getY() {
        return y;
    }


    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    // Setters
    public void setPosition(int x, int y){
        this.x = x;
        this.y = y;
    }

    public void setSize(int width, int height){
        this.width = width;
        this.height = height;
    }

}