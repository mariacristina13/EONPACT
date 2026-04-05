package game;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import constants.Constants;
import ui.Button;

public class CharacterButton extends Button{

    public CharacterButton(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    @Override
    public void drawButton(Graphics2D g) {
    }
}
