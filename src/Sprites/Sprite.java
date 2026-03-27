package Sprites;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public abstract class Sprite {
    private String fileName;
	private int x;
	private int y;
	private int width;
	private int height;
	private BufferedImage image;
}
