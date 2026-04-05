package ui;

import java.awt.Graphics2D;

public abstract class Button {
    // Class variables.
    private int x;
    private int y;
    private int width;
    private int height;
    private boolean hovered = false;

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

}