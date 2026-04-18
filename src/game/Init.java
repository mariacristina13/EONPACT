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

        frame.setSize(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
        frame.setVisible(true);

        frame.requestFocus();
        panel.requestFocus();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // https://projectai.in/projects/e79f02df-4d51-473e-90f0-4ff8443ff473/tasks/5b55ecc1-ac91-4092-8e63-097ce794218b?tab=task
        // Initialise a timer for the game loop that updates the game every 100
        // milliseconds.
        Timer gameLoop = new Timer(Constants.REFRESH_RATE, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel.update();

                // End condition.
                if (panel.getCurrentState() == GameStates.PLAYING) {
                    // Check if the players completed all the riddles before the timer ran out.
                    if (gameManager.getCompletedCheckpoints() == 5) {
                        // Stop the timer.
                        gameManager.stopTimer();
                        // Change the game's state to the game won state.
                        panel.setCurrentState(GameStates.GAME_WON);
                    } 
                    // Check if the players failed all the checkpoints before the time ran out and if the riddle and feedback cards are not active.
                    else if (gameManager.getFailedCheckPoints() == 5 && !gameManager.isRiddleActive()
                            && !gameManager.isFeedbackActive()) {
                        // Stop the timer.
                        gameManager.stopTimer();
                       // Change the game's state to the game lost state.
                        panel.setCurrentState(GameStates.GAME_LOST);
                    } 
                    // Check if the timer ran out.
                    else if (gameManager.getMinute() == 0 && gameManager.getSecond() == 0) {
                        // Stop the timer.
                        gameManager.stopTimer();
                        // Check if the player completed 3 or more checkpoints.
                        if (gameManager.getCompletedCheckpoints() >= 3) {
                            // Change the game's state to the game won state.
                            panel.setCurrentState(GameStates.GAME_WON);
                        } 
                        else {
                            // Otherwise change the game's state to the game lost state.
                            panel.setCurrentState(GameStates.GAME_LOST);
                        }
                    }
                }
            }
        });

        // Start the game loop.
        gameLoop.start();
    }

}