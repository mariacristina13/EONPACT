package Sprites;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import constants.Constants;
public class Map extends Sprite{

    private BufferedImage tile2;
    private BufferedImage tile3;
    private BufferedImage tile4;


    public Map(String fileName, String tile2FileName, String tile3FileName, String tile4FileName, int x, int y, int width, int height) {
        super(fileName, x, y, width, height);
        try {
            tile2 = ImageIO.read(new File("images/" + tile2FileName));
        } catch (IOException e) {
            e.printStackTrace();
            tile2 = null;
        }

        try {
            tile3 = ImageIO.read(new File("images/" + tile3FileName));
        } catch (IOException e) {
            e.printStackTrace();
            tile3 = null;
        }

        try {
            tile4 = ImageIO.read(new File("images/" + tile4FileName));
        } catch (IOException e) {
            e.printStackTrace();
            tile3 = null;
        }
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
