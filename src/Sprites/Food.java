package Sprites;

public class Food extends Sprite {
    // Class variable.
    private boolean collected = false;

    // Initialise the constructor in the parent class.
    public Food(String fileName, int x, int y, int width, int height) {
        super(fileName, x, y, width, height);
    }

    // Getter and setter.
    public boolean isCollected() {
        return collected;
    }

    public void setCollected(boolean collected) {
        this.collected = collected;
    }
}