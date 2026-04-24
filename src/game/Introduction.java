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
        bg = new Background("intro_bg.png", 0, Constants.SCREEN_HEIGHT, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
        int imageWidth = 608;
        int x =  (Constants.SCREEN_WIDTH - imageWidth)/2; 
        msg = new Decor("msg.png", x, 540 , 608, 457);
    }

    //draw introduction display
    public void drawIntroduction(Graphics2D g){
        //draw the background
        g.drawImage(bg.getImage(), bg.getX(), bg.getY(), bg.getWidth(), bg.getHeight(), null); 
        g.drawImage(msg.getImage(), msg.getX(), msg.getY(), msg.getWidth(), msg.getHeight(), null);

        //add instruction text 
        g.setColor(Constants.GOLD);
        g.setFont(Constants.INTRO_FONT);
        String instruction = "Click on the screen to continue";
        int instructionWidth = g.getFontMetrics().stringWidth(instruction);
        g.drawString(instruction, Constants.SCREEN_CENTER - instructionWidth / 2, Constants.GROUND_HEIGHT+5);
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
