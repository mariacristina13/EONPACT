package riddles;

import java.awt.FontMetrics;
import java.awt.Graphics2D;

import constants.Constants;
import game.GameManager;

public class RiddleLayout{
    
    private Riddle riddle;
    private GameManager game;
    private String userInput;
    private String feedback;
    private boolean active = false;


    public RiddleLayout(Riddle riddle, GameManager game){
     this.riddle = riddle;
     this.game = game;
   }
   public void setActive(boolean active) {
    this.active = active;
    if (active) {
        userInput = "";
        feedback = "";
    }
}

public boolean isActive() {
    return active;
}
   public void draw(Graphics2D graphics, int width, int height){
    if (!active) return;
    // card with riddle
    int cardW = 400;
    int cardH = 300;
    int x = (width - cardW)/2;
    int y = (height - cardH)/2;

    graphics.setColor(Constants.BROWN);
    graphics.fillRect(x,y,cardW,cardH);
    
    // number of attempts
    String attempts = "Atempts:" + riddle.getCountAttempts() + "/" + Constants.MAX_ATTEMPTS;
    graphics.setColor(Constants.BLACK);
    graphics.setFont(Constants.ATTEMPTS_FONT);
    graphics.drawString(attempts, x+cardW-120, y+24);

    int dot = y + 270;
    int spacing = 14;
    int start = width/2 - (Constants.MAX_ATTEMPTS * spacing);
    for (int i = 0; i < Constants.MAX_ATTEMPTS; i++) {
        graphics.setColor(i < riddle.getCountAttempts()
            ? (Constants.WHITE)   // used
            : (Constants.GRAY)); // remaining 
        graphics.fillOval(start + i * spacing, dot, 8, 8);
    }
    
    //question
    graphics.setColor(Constants.BLACK);
    graphics.setFont(Constants.QUESTION_FONT);
    drawWrapped(graphics, riddle.getQuestion(), x+20, y+55, cardW-40, 20);

    //hint
    String hint = riddle.displayHint();
        if (!hint.isEmpty()) {
            graphics.setColor(Constants.BLACK);
            graphics.setFont(Constants.QUESTION_FONT);
            drawWrapped(graphics, "Hint:" + hint, x+20, y+150, cardW-40,18);
        }

    // answer input field
    int input = y + 195;
    graphics.setColor(Constants.WHITE);  
    graphics.fillRect(x+20, input, cardW - 120, 30);
    graphics.setColor(Constants.BLACK);
    graphics.drawRect(x+20,y,cardW-120,30);     
    graphics.setFont(Constants.QUESTION_FONT);
    graphics.drawString(userInput, x+30, y+20);

    // submit button
    int button = x + cardW - 90;
    graphics.setColor(Constants.BLACK);
    graphics.fillRect(button, input, 70, 30);
    graphics.setColor(Constants.WHITE);  
    graphics.setFont(Constants.QUESTION_FONT);
    drawCentered(graphics, "Submit", button + 35, input + 20);

    // feedback
    if (!feedback.isEmpty()){
        graphics.setColor(Constants.BLACK);
        graphics.setFont(Constants.QUESTION_FONT);
        drawCentered(graphics, feedback, width, y + 250);
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
