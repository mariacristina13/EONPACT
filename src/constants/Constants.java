package constants;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;

public final class Constants {
    public final static int MAX_ATTEMPTS = 5;
    public final static int SHOW_HINT_AFTER_ATTEMPTS = 3;


	public final static int REFRESH_RATE = 100;

	public final static Dimension SCREEN_SIZE =  Toolkit.getDefaultToolkit().getScreenSize();
	public final static int GROUND_HEIGHT  = SCREEN_SIZE.height/3;

	public final static int LEFTKEY =37;
	public final static int RIGHTKEY =39;
	public final static int SPACEKEY =32;
	public final static int ENTERKEY = KeyEvent.VK_ENTER;
	public final static int BACKSPACEKEY = KeyEvent.VK_BACK_SPACE;
	public final static int AKEY=65;
	public final static int SKEY=83;
	public final static int DKEY=68;
	public final static int WKEY=87;//Reference: https://stackoverflow.com/questions/15313469/java-keyboard-keycodes-list
	
	public final static int PLAYER_SPEED = 4;

	// Player 2 keys
    public final static int AKEY = 65; // A key-left
    public final static int DKEY = 68; // D key-right

	public final static int PLAYER_JUMP_HEIGHT = 70;
	public final static int PLAYER_FALL_SPEED = 8;//The amount of pixels to move the player downwards 
	
	public final static Color BLUE = new Color(174, 227, 245);
	public final static Color GREEN = new Color(79, 179, 85);
	public final static Color BROWN = new Color(196,164,132);
	public final static Color BLACK = new Color(0,0,0);

	public final static	Font ATTEMPTS_FONT = new Font( "SansSerif", Font.PLAIN, 12 );
	public final static Font QUESTION_FONT = new Font("SansSerif", Font.PLAIN, 16);

}
