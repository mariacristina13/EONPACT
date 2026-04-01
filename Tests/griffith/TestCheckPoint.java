package griffith;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

import Sprites.CheckPoint;
import riddles.Riddle;

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

}
