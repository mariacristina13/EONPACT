package griffith;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public class MyPannel extends JPanel{

@Override
public void paint(Graphics g){
super.paint(g);
Graphics2D graphics = (Graphics2D) g;
BufferedImage image;
//improve rendering quality
RenderingHints hints = new RenderingHints( RenderingHints.KEY_ANTIALIASING,   RenderingHints.VALUE_ANTIALIAS_ON);
graphics.setRenderingHints(hints);
}
}
