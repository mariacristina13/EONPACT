package constants;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

public final class Constants {
    public final static int MAX_ATTEMPTS = 5;
    public final static int SHOW_HINT_AFTER_ATTEMPTS = 3;


	public final static int REFRESH_RATE = 100;

	public final static Dimension SCREEN_SIZE =  Toolkit.getDefaultToolkit().getScreenSize();
	public final static int GROUND_HEIGHT  = SCREEN_SIZE.height/3;

	public final static int LEFTKEY =37;
	public final static int RIGHTKEY =39;
	public final static int SPACEKEY =32;

	public final static int PLAYER_JUMP_HEIGHT = 70;
	public final static int PLAYER_FALL_SPEED = 6;
	
	public final static Color BLUE = new Color(174, 227, 245);
	public final static Color GREEN = new Color(79, 179, 85);

}
