package griffith;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;



import javax.swing.JPanel;

public class MyPanel extends JPanel implements KeyListener{

private GameManager game;

public MyPanel(){
    addKeyListener(this);
    game = new GameManager();
}

@Override
public void paint(Graphics g){
super.paint(g);
Graphics2D graphics = (Graphics2D) g;
//improve rendering quality
RenderingHints hints = new RenderingHints( RenderingHints.KEY_ANTIALIASING,   RenderingHints.VALUE_ANTIALIAS_ON);
graphics.setRenderingHints(hints); // https://docs.oracle.com/javase/8/docs/api/javax/swing/package-summary.html
}

@Override
public void keyTyped(KeyEvent e) {
    
}

@Override
public void keyPressed(KeyEvent e) {
    game.keyPressed(e.getKeyCode());
    this.repaint();
}

@Override
public void keyReleased(KeyEvent e) {
    game.keyReleased(e.getKeyCode());
    this.repaint();
}

public void update()
{
    game.update();
    this.repaint();
}
}
