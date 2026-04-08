package Sprites;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import constants.Constants;
public class Map extends Sprite{

    //private BufferedImage tile2;
    //private BufferedImage tile3;
    //private BufferedImage tile4;


    public Map(String fileName,  int x, int y, int width, int height) {
        super(fileName, x, y, width, height);
        /* try {
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
        } */
    }
    
    /*  
    public void draw(Graphics g, int panelWidth, int panelHeight) {
    
        int x = 50;   
        int x2 = x + Constants.TILE_WIDTH + 10;
        int x3 = x2 + Constants.TILE_WIDTH + 10;
        int x4 =x3 + 50;        
        int y = 100; 
    
        if (getImage() != null) {
            g.drawImage(getImage(), x, y, Constants.TILE_WIDTH, Constants.TILE_HEIGHT, null);
        }

        if (tile2 != null) {
            g.drawImage(tile2, x2, y+50, Constants.TILE_WIDTH, Constants.TILE_HEIGHT, null);
        }

        if (tile3 != null) {
            g.drawImage(tile3, x3, y-30, Constants.TILE_WIDTH, Constants.TILE_HEIGHT, null);
        }

        if (tile4 != null) {
            g.drawImage(tile4, x4, y+60, Constants.TILE_WIDTH, Constants.TILE_HEIGHT, null);
        }
    } */
    

    public void update() {
    }
}
