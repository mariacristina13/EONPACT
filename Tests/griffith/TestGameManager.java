package griffith;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;


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

        assertTrue(actual);
        assertTrue(actual1);
        assertFalse(actual2);

        Exception exception = assertThrows(NullPointerException.class, () -> {
            riddle.checkAnswer(null);
        });
         String expectedMessage = "null";
       
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    

}
