package griffith;

import javax.swing.JFrame;

public class Init {

    public static void main (String[] args){
        JFrame frame = new JFrame();
        MyPannel panel = new MyPannel();
        frame.add(panel);

        frame.setSize(Constants.SCREEN_SIZE.width/2,Constants.SCREEN_SIZE.height/2);
		frame.setVisible(true);

        frame.requestFocus();
        panel.requestFocus();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		long startTime = System.currentTimeMillis();

        while(true){
            long elapsedTime = System.currentTimeMillis() - startTime;
			if (elapsedTime > Constants.REFRESH_RATE)  
			{ 
				panel.update();
				startTime = System.currentTimeMillis();
			}
        }

    }

    
}
