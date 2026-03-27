package constants;
import java.awt.Dimension;
import java.awt.Toolkit;

public final class Constants {
    public static final int MAX_ATTEMPTS = 5;
    public static final int SHOW_HINT_AFTER_ATTEMPTS = 3;

    public final static int REFRESH_RATE = 100;

    public final static Dimension SCREEN_SIZE =  Toolkit.getDefaultToolkit().getScreenSize();
	public final static int GROUND_HEIGHT  = SCREEN_SIZE.height/3;

    public final static int LEFTKEY =37;
    public final static int RIGHTKEY =39;
    public final static int SPACEKEY =32;

}
