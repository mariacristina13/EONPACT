package griffith;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

import Sprites.CheckPoint;
import game.GameManager;
import riddles.Riddle;
import riddles.RiddleData;

class TestCheckPoint {

    // Helper method to initialize a GameManager instance with test players
    private GameManager setupGame() {
        GameManager gm = new GameManager();
        ArrayList<String> chars = new ArrayList<>();
        chars.add("Box Turtle");
        chars.add("Kakapo");
        gm.initializeGame(chars);
        return gm;

    }

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

    // Test multiple checkpoints handling
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

    // Test removing checkpoint after completion
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

    // est riddle not finished before max attempts
    @Test
    public void testNotFinishedBeforeMaxAttempts() {
        Riddle r = new Riddle("Q", "Dog", "Hint");
        CheckPoint cp = new CheckPoint("x.png", 0, 0, 10, 10);
        cp.setRiddle(r);

        cp.attempt("a");
        cp.attempt("b");

        assertFalse(cp.isFailed());
    }

    // Test riddle finished after max attempts(5 attempts)
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

        // Check that some of the asnwers are unique and add them to a list of unique
        // answers.
        ArrayList<String> uniqueAnswers = new ArrayList<>();
        for (String answer : assignedAnswers) {
            if (!uniqueAnswers.contains(answer)) {
                uniqueAnswers.add(answer);
            }
        }

        // Check if there are more than two answers that are diffrent.
        assertTrue(uniqueAnswers.size() > 2);
    }

    // Test to ensure checkpoints are created after game initialization
    @Test
    public void testCheckpointsCreated() {
        GameManager gm = setupGame();
        assertEquals(5, gm.getCheckpoints().size());
    }

    @Test
    public void testCheckpointHasRandomImage() {// check for random image
        CheckPoint cp = new CheckPoint("test.png", 0, 0, 10, 10);
        assertNotNull(cp.getImage());
    }

    // Test that each checkpoint is assigned a riddle
    @Test
    public void testCheckpointsHaveRiddles() {
        GameManager gm = setupGame();
        for (CheckPoint cp : gm.getCheckpoints()) {
            assertNotNull(cp.getRiddle());
        }
    }

    @Test
    public void testCheckpointAnimationChangesValues() {// checking for animation
        CheckPoint cp = new CheckPoint("x.png", 0, 100, 10, 10);
        int y1 = cp.getAnimatedY();
        cp.updateAnimation();
        int y2 = cp.getAnimatedY();
        assertNotEquals(y1, y2);
    }

    @Test
    public void testCheckpointRequiresBothPlayers() {// Riddle triggers only when BOTH players near
        GameManager gm = new GameManager();
        ArrayList<String> chars = new ArrayList<>();
        chars.add("Box Turtle");
        chars.add("Kakapo");

        gm.initializeGame(chars);
        CheckPoint cp = gm.getCheckpoints().get(0);// Move both near checkpoint

        gm.player1.setX(cp.getX());// Only when the player1 near
        gm.player1.setY(cp.getY());

        gm.player2.setX(0);
        gm.player2.setY(0);
        gm.update();
        assertFalse(gm.isRiddleActive());
    }
}