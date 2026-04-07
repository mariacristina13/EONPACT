package Sprites;

import java.awt.Graphics;
import constants.Constants;
public class Map extends Sprite{

    public Map(String fileName, int x, int y, int width, int height) {
        super(fileName, x, y, width, height);
    }
    
    public void draw(Graphics g, int panelWidth, int panelHeight) {
    
        int x = 50;                 
        int y = 100; 
    
        if (getImage() != null) {
            g.drawImage(getImage(), x, y, Constants.TILE_WIDTH, Constants.TILE_HEIGHT, null);
        }
    }
    

    public void update() {
    }
}
