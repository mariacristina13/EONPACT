package griffith;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

import Sprites.CheckPoint;
import riddles.Riddle;
import riddles.RiddleData;

class TestCheckPoint {

	 // Test correct answer
    @Test
    public void testCorrectAnswer() {
        Riddle r = new Riddle("Q", "Dog", "Hint");
        CheckPoint cp = new CheckPoint("x.png", 0, 0, 10, 10);
        cp.setRiddle(r);

        assertTrue(cp.attempt("dog"));
    }

    // Test wrong answer(Checkpoint)
    @Test
    public void testWrongAnswer() {
        Riddle r = new Riddle("Q", "Dog", "Hint");
        CheckPoint cp = new CheckPoint("x.png", 0, 0, 10, 10);
        cp.setRiddle(r);

        assertFalse(cp.attempt("cat"));
    }

    // Test max attempts = 5
    @Test
    public void testMaxAttempts() {
        Riddle r = new Riddle("Q", "Dog", "Hint");
        CheckPoint cp = new CheckPoint("x.png", 0, 0, 10, 10);
        cp.setRiddle(r);

        for (int i = 0; i < 5; i++) {
            cp.attempt("wrong");
        }

        assertTrue(cp.isFailed());
    }

    // Test hint after 3 attempts
    @Test
    public void testHintAfterThreeAttempts() {
        Riddle r = new Riddle("Q", "Dog", "Hint");
        CheckPoint cp = new CheckPoint("x.png", 0, 0, 10, 10);
        cp.setRiddle(r);

        cp.attempt("a");
        cp.attempt("b");
        cp.attempt("c");

        assertEquals("Hint", cp.getHint());
    }

     //Test multiple checkpoints handling
    @Test
    public void testMultipleCheckpoints() {
        ArrayList<CheckPoint> list = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            CheckPoint cp = new CheckPoint("cabage.png", i * 100, 0, 10, 10);
            cp.setRiddle(new Riddle("Q", "Dog", "Hint"));
            list.add(cp);
        }

        assertEquals(3, list.size());
    }

    //Test removing checkpoint after completion
    @Test
    public void testCheckpointRemoval() {

        ArrayList<CheckPoint> list = new ArrayList<>();
        CheckPoint cp1 = new CheckPoint("cabage.png", 0, 0, 10, 10);
        cp1.setRiddle(new Riddle("Q", "Dog", "Hint"));
        list.add(cp1);
        cp1.attempt("Dog"); // correct
        list.remove(cp1);
        assertEquals(0, list.size());
    }

    //est riddle not finished before max attempts
    @Test
    public void testNotFinishedBeforeMaxAttempts() {
        Riddle r = new Riddle("Q", "Dog", "Hint");
        CheckPoint cp = new CheckPoint("x.png", 0, 0, 10, 10);
        cp.setRiddle(r);

        cp.attempt("a");
        cp.attempt("b");

        assertFalse(cp.isFailed());
    }

    //Test riddle finished after max attempts(5 attempts)
    @Test
    public void testFinishedAfterMaxAttempts() {

        Riddle r = new Riddle("Q", "Dog", "Hint");
        CheckPoint cp = new CheckPoint("x.png", 0, 0, 10, 10);
        cp.setRiddle(r);
        for (int i = 0; i < 5; i++) {
            cp.attempt("wrong");
        }

        assertTrue(cp.isFailed());
    }
 
    @Test
    public void testCheckpointGetsRandomRiddle() {
        // Initialise the riddle data object.
        RiddleData data = new RiddleData();

        // Initialise a list of checkpoints.
        ArrayList<CheckPoint> checkpoints = new ArrayList<CheckPoint>();

        // Initialise a list of riddle answers.
        ArrayList<String> assignedAnswers = new ArrayList<String>();

        // Create checkpoints and assign random riddles.
        int numCheckpoints = 5;
        for (int i = 0; i < numCheckpoints; i++) {
            // Initialise each checkpoint at diffrent positions.
            CheckPoint checkPoint = new CheckPoint("test.png", i * 50, 0, 10, 10);

            // Get a random riddle from the riddle data.
            Riddle randomRiddle = data.getRandomRiddle();

            // Add the random riddle to the checkpoint.
            checkPoint.setRiddle(randomRiddle);

            // Add the chekpoint into the checkpoints list.
            checkpoints.add(checkPoint);

            // Add the answer to the riddle into the list of answers.
            assignedAnswers.add(randomRiddle.getAnswer());
        }

        // Check that some of the asnwers are unique and add them to a list of unique answers.
        ArrayList<String> uniqueAnswers = new ArrayList<>();
        for (String answer : assignedAnswers) {
            if (!uniqueAnswers.contains(answer)) {
                uniqueAnswers.add(answer);
            }
        }

        // Check if there are more than two answers that are diffrent.
        assertTrue(uniqueAnswers.size() > 2);
    }
}
