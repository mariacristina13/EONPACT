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

    public Sprite(String fileName , int x, int y , int width, int height) {
		this.x = x;
		this.y = y - height;
		this.width = width;
		this.height = height;
		File pic = new File("images/"+fileName);
		try {
			image = ImageIO.read(pic);
		} catch (IOException e) {
			e.printStackTrace();
			System.out.print("file not found");
		}
	}


}
