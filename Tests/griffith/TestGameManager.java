package griffith;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;


import org.junit.Test;

public class TestGameManager {

    // Riddle Tests
    @Test
    public void testCheckAnswer(){
        Riddle riddle = new Riddle("I can smell food from a mile away.", "Bear", "Inwinter I go into a deep sleep called hibernation.");

        // Actual values generated when we run the checkAnswer method.
        boolean actual = riddle.checkAnswer(" Bear ");
        boolean actual1 = riddle.checkAnswer("bear");
        boolean actual2 = riddle.checkAnswer("");
        boolean actual3 = riddle.checkAnswer("hair");

        assertTrue(actual);
        assertTrue(actual1);
        assertFalse(actual2);
        assertFalse(actual3);

        Exception exception = assertThrows(NullPointerException.class, () -> {
            riddle.checkAnswer(null);
        });
         String expectedMessage = "null";
       
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void testIncrementAttempt(){
        Riddle riddle = new Riddle("I can smell food from a mile away.", "Bear", "Inwinter I go into a deep sleep called hibernation.");
        
        // Check that the counter starts from zero in the begining.
        int expected = 0;
        int actual = riddle.getCountAttempts();
        assertEquals(expected, actual);

        // Increase the counter.
        riddle.incrementAttempt();
        riddle.incrementAttempt();
        riddle.incrementAttempt();
        
        // Check if the counter increases correctly.
        int expected1 = 3;
        int actual1 = riddle.getCountAttempts();

         assertEquals(expected1, actual1);
    }

    @Test
    public void testAttemptsFinished(){
        // Initialise the riddle class.
        Riddle riddle = new Riddle("I can smell food from a mile away.", "Bear", "Inwinter I go into a deep sleep called hibernation.");

        // Increase the number of attempts.
        riddle.incrementAttempt();
        riddle.incrementAttempt();
        riddle.incrementAttempt();

        // Check if the user has finished all their attempts.
        boolean actual = riddle.attemptsFinished();
        boolean expected = false;
        assertEquals(expected, actual);

        // Increase the attempts counter.
        riddle.incrementAttempt();
        riddle.incrementAttempt();
    
        // Check if the attempts have finished.
        boolean actual1 = riddle.attemptsFinished();
        boolean expected1 = true;

        assertEquals(expected1, actual1);


        // Increase the attempts counter.
        riddle.incrementAttempt();

        // Check if the attempts have finished.
        boolean actual2 = riddle.attemptsFinished();
        boolean expected2 = true;

        assertEquals(expected2, actual2);
    }
}
