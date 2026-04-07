package constants;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;

public final class Constants {
	// Riddle constants
	public final static int MAX_ATTEMPTS = 5;
	public final static int SHOW_HINT_AFTER_ATTEMPTS = 3;

	// Refresh Rate
	public final static int REFRESH_RATE = 100;

	// Screen dimensions
	public final static Dimension SCREEN_SIZE = Toolkit.getDefaultToolkit().getScreenSize();
	public final static int GROUND_HEIGHT = SCREEN_SIZE.height / 3;
	public final static int SCREEN_HEIGHT = SCREEN_SIZE.height;
	public final static int SCREEN_WIDTH = SCREEN_SIZE.width;
	public final static int SCREEN_CENTER = SCREEN_WIDTH /2;


	// Key codes
	public final static int LEFTKEY = 37;
	public final static int RIGHTKEY = 39;
	public final static int SPACEKEY = 32;
	public final static int ENTERKEY = KeyEvent.VK_ENTER;
	public final static int BACKSPACEKEY = KeyEvent.VK_BACK_SPACE;
	public final static int AKEY = 65;
	public final static int SKEY = 83;
	public final static int DKEY = 68;
	public final static int WKEY = 87;// Reference: https://stackoverflow.com/questions/15313469/java-keyboard-keycodes-list

	// Player phisics
	public final static int PLAYER_SPEED = 10;

	public final static int PLAYER_JUMP_HEIGHT = 70;
	public final static int PLAYER_FALL_SPEED = 15;// The amount of pixels to move the player downwards

	// Colours
	public final static Color BLUE = new Color(174, 227, 245);
	public final static Color GREEN = new Color(79, 179, 85);
	public final static Color BROWN = new Color(196, 164, 132);
	public final static Color COFFEE_BROWN = new Color(78, 53, 30);
	public final static Color BLACK = new Color(0, 0, 0);
	public final static Color WHITE = new Color(255, 255, 255);
	public final static Color LIGHT_GRAY = new Color(224,224,222);
	public final static Color GRAY = new Color(90, 90, 85);
	public final static Color MAROON = new Color(123, 47, 47);
	public final static Color BROWN_RED = new Color(163, 62, 62);
	public final static Color GOLD = new Color(255, 215, 0);
	public final static Color DARK_GREEN = new Color(49, 73, 53);

	// Fonts
	public final static Font ATTEMPTS_FONT = new Font("SansSerif", Font.ITALIC, 10);
	public final static Font QUESTION_FONT = new Font("Monospaced", Font.PLAIN, 16);
	public final static Font FINAL_FEEDBACK = new Font("Monospaced", Font.BOLD, 20);
	public final static Font TIMER_FONT = new Font("Monospaced", Font.BOLD, 25);
	public final static Font BUTTON_FONT = new Font("Arial", Font.BOLD, 20);
	public final static Font GAME_FONT = new Font("SansSerif", Font.BOLD, 50);
	public final static Font CHARACTER_MENU_FONT = new Font("Arial", Font.BOLD, 40);

	// Button Dimensions
	public final static int BUTTON_WIDTH = 200;
	public final static int BUTTON_HEIGHT = 80;
	public final static int BUTTON_SPACEING = 160;
	public final static int BUTTON_Y = SCREEN_HEIGHT / 2;
	public final static int BUTTON_X = SCREEN_CENTER;

	// Character Button Dimensions
	public final static int CHARACTER_WIDTH = 100;
	public final static int CHARACTER_HEIGHT = 100;
	public final static int CHARACTER_SPACEING = 30;
	public final static int CHARACTER_BUTTON_Y = 300;
	public final static int CHARACTER_BUTTON_X = SCREEN_CENTER;


}
