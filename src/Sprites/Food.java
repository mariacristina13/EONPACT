package Sprites;

public class Food extends Sprite {
    
    public enum FoodType {
        NORMAL, 
        FAST_TIMER,   // makes timer faster
    }
    // Class variable.
    private boolean collected = false;
    private FoodType type;
    //variables for floating animation
    private float animationTick = 0f;
    private float animationOffset = 0f;

    // Initialise the constructor in the parent class.
    public Food(String fileName, int x, int y, int width, int height, FoodType type) {
        super(fileName, x, y, width, height);
        this.type = type;
        animationTick = (float)(Math.random() * Math.PI * 2);
    }

    public Food(String fileName, int x, int y, int width, int height) {
        super(fileName, x, y, width, height);
        this.type = FoodType.NORMAL;
    }

    //methods to animate food floating
    public void updateAnimation() {
        animationTick += 0.05f;
        animationOffset = (float)(Math.sin(animationTick) * 4); // 4px float range
    }

    public int getAnimatedY() {
        return getY() + (int) animationOffset;
    }

    // Getter and setter for collected.
    public boolean isCollected() {
        return collected;
    }

    public void setCollected(boolean collected) {
        this.collected = collected;
    }

    //getter for food type
    public FoodType getType(){
        return type;
    }
}