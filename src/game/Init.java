package game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.Timer;

import constants.Constants;

public class Init {

    public static void main(String[] args) {

        JFrame frame = new JFrame();
        MyPanel panel = new MyPanel();
        GameManager gameManager = panel.getGameManager();
        frame.add(panel);

        frame.setSize(Constants.SCREEN_SIZE.width / 2, Constants.SCREEN_SIZE.height / 2);
        frame.setVisible(true);

        frame.requestFocus();
        panel.requestFocus();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //long startTime = System.currentTimeMillis();

        /*while (true) {
            long elapsedTime = System.currentTimeMillis() - startTime;
            if (elapsedTime > Constants.REFRESH_RATE) {
                panel.update();
                startTime = System.currentTimeMillis();
            }

            if (gameManager.getMinute() == 0 && gameManager.getSecond() == 0) {
                gameManager.stopTimer();
                break;
            }
        }*/

        // https://projectai.in/projects/e79f02df-4d51-473e-90f0-4ff8443ff473/tasks/5b55ecc1-ac91-4092-8e63-097ce794218b?tab=task
        // Initialise a timer for the game loop that updates the game every 100 milliseconds.
        Timer gameLoop = new Timer(Constants.REFRESH_RATE, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel.update();

                // End condition.
                if (gameManager.getMinute() == 0 && gameManager.getSecond() == 0) {
                    gameManager.stopTimer();
                    ((Timer) e.getSource()).stop();
                    System.out.println("Game Over!");
                }
            }
        });
        
        // Start the game loop.
        gameLoop.start();
    }

}


