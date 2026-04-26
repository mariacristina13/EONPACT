package Sprites;

public class Food extends Sprite {
    
    public enum FoodType {
        NORMAL, 
        FAST_TIMER,   // makes timer faster
    }
    // Class variable.
    private boolean collected = false;
    private FoodType type;

    // Initialise the constructor in the parent class.
    public Food(String fileName, int x, int y, int width, int height, FoodType type) {
        super(fileName, x, y, width, height);
        this.type = type;
    }

    public Food(String fileName, int x, int y, int width, int height) {
        super(fileName, x, y, width, height);
        this.type = FoodType.NORMAL;
    }

    // Getter and setter.
    public boolean isCollected() {
        return collected;
    }

    public void setCollected(boolean collected) {
        this.collected = collected;
    }
}