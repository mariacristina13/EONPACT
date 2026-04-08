package Sprites;

public class Food extends Sprite{

    private boolean collected = false;

    public Food(String fileName, int x, int y, int width, int height) {
        super(fileName, x, y, width, height);
    }

    public boolean isCollected(){
        return collected;
    }

    public void setCollected(boolean collected){
        this.collected = collected;
    }

}
