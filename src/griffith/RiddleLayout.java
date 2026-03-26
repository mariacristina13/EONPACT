package griffith;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.FontMetrics;

public class RiddleLayout{
    
    private Riddle riddle;
    private String userInput;
    private String feedback;
    private boolean active = false;

    public RiddleLayout(Riddle riddle){
     this.riddle = riddle;
   }
}
