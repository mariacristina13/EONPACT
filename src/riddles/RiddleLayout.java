package riddles;

import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import constants.Constants;

public class RiddleLayout{
    
    private Riddle riddle;
    private String userInput;
    private String feedback;
    private boolean active = false;

    public RiddleLayout(Riddle riddle){
     this.riddle = riddle;
   }
   public void draw(Graphics2D graphics, int width, int height){
    graphics.setColor(Constants.BROWN);
    width = 400;
    height = 300;
    int x = width/2;
    int y = height/2;
    graphics.fillRect(x,y,width,height);
    
    String attempts = "Atempts:" + riddle.getCountAttempts() + "/" + Constants.MAX_ATTEMPTS;
    graphics.setColor(Constants.BLACK);
    graphics.drawString(attempts, x+width-120, y+24);
    
    graphics.setColor(Constants.BLACK);
    graphics.setFont(Constants.QUESTION_FONT);
    riddle.getQuestion();
   }

   private void drawCentered(Graphics2D graphics, String text, int centre, int y){
    FontMetrics font = graphics.getFontMetrics(); // https://docs.oracle.com/javase/8/docs/api/java/awt/FontMetrics.html
    int textWidth = font.stringWidth(text);
    graphics.drawString(text, centre - textWidth/2, y);
   }

   private void drawWrapped(Graphics2D graphics, String text, int x, int y, int maxWidth, int lineHeight){
    FontMetrics font = graphics.getFontMetrics();
    String[] words = text.split(" ");
    StringBuilder line = new StringBuilder();
    for (String word : words) {
      String test = line + (line.length() > 0 ? " " : "") + word;
      if (font.stringWidth(test) > maxWidth) {
          graphics.drawString(line.toString(), x, y);
          y += lineHeight;
          line = new StringBuilder(word);
      } else {
          line = new StringBuilder(test);
      }
  }
  if (line.length() > 0) graphics.drawString(line.toString(), x, y);
  }

}
