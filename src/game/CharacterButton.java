package game;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import constants.Constants;
import ui.Button;

public class CharacterButton extends Button{
    // Class variables.
    private String name;
    private BufferedImage image;
    private boolean selected = false;

    public CharacterButton(String name, String fileName,int x, int y, int width, int height) {
        super(x, y, width, height);
        this.name = name;
        loadImage(fileName);
    }

    // Load the images for the character menu.
    private void loadImage(String fileName){
        try{
            image = ImageIO.read(new File("images/" + fileName));
        }
        catch (IOException e){
            e.printStackTrace();
			System.out.print("file not found");
        }
    }

    @Override
    public void drawButton(Graphics2D g) {
    }
}
