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
    drawWrapped(graphics, riddle.getQuestion(), x+20, y+55, width-40, 20);

    String hint = riddle.displayHint();
        if (!hint.isEmpty()) {
            graphics.setColor(Constants.BLACK);
            graphics.setFont(Constants.QUESTION_FONT);
            drawWrapped(graphics, "Hint:" + hint, x+20, y+150, width-40,18);
        }

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

  public void keyPressed(int keyCode) {
    if (!active) return;

    if (keyCode == Constants.ENTERKEY) {
        submitAnswer();
    } else if (keyCode == Constants.BACKSPACEKEY) {
        if (!userInput.isEmpty()) {
            userInput = userInput.substring(0, userInput.length() - 1);
        }
    }
}
  public void keyTyped(char c) {
    if (!active) return;
    if (Character.isLetterOrDigit(c) || c == ' ') {
        userInput += c;
    }
}

private void submitAnswer() {
  if (riddle.attemptsFinished()) return;

  if (riddle.checkAnswer(userInput)) {
      feedback = "Correct!";
      active = false; 
  } else {
      riddle.incrementAttempt();
      if (riddle.attemptsFinished()) {
          feedback = "No attempts left. The answer was: " + riddle.getAnswer();
      } else {
          feedback = "Wrong answer, try again.";
      }
  }
  userInput = "";
}



}
