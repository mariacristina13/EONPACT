package game;
import java.awt.Graphics2D;


import Sprites.Background;
import Sprites.Decor;
import constants.Constants;

public class Introduction {
    // Class variables
    public Background bg;
    public Decor msg;
    private boolean introductionActive;

    public Introduction(){
        introductionActive = true;
        bg = new Background("menu_bg.png", 0, Constants.SCREEN_HEIGHT, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
        msg = new Decor("msg.png", 0, 0, 900, 610);
    }

    //draw introduction display
    public void drawIntroduction(Graphics2D g){
        //draw the background
        g.drawImage(bg.getImage(), bg.getX(), bg.getY(), bg.getWidth(), bg.getHeight(), null); 
        g.drawImage(msg.getImage(), msg.getX(), msg.getY(), msg.getWidth(), msg.getHeight(), null);
    }

    // Hide introduction card
    public void mouseClicked(int mouseX, int mouseY, int panelWidth, int panelHeight) {
        if (introductionActive) {
            introductionActive = false;
        }
    }
    // getter for introductionActive
    public boolean getIntroductionActive(){
        return introductionActive;
    }

}
