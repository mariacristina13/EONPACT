package griffith;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import game.CharacterButton;

public class TestUnlockingCharacters {
    @Test
    public void testUnlockingElephant() {
        // Initialise character button.
        CharacterButton elephantButton = new CharacterButton("test", "test", 0, 0, 0, 0);

        // Get the actual values from the locked button.
        boolean actual = elephantButton.isElephantUnlocked(2);
        boolean actual1 = elephantButton.isElephantUnlocked(3);
        boolean actual2 = elephantButton.isElephantUnlocked(5);
        boolean actual3 = elephantButton.isElephantUnlocked(-9);

        // Check if the acutual values are true or false.
        assertFalse(actual);
        assertTrue(actual1);
        assertTrue(actual2);
        assertFalse(actual3);
    }

    @Test
    public void testUnlockingLemur() {
        // Initialise character button.
        CharacterButton lemurButton = new CharacterButton("test", "test", 0, 0, 0, 0);

        // Get the actual values from the locked button.
        boolean actual = lemurButton.isLemmurUnlocked(4);
        boolean actual1 = lemurButton.isLemmurUnlocked(6);
        boolean actual2 = lemurButton.isLemmurUnlocked(12);
        boolean actual3 = lemurButton.isLemmurUnlocked(-9);

        // Check if the acutual values are true or false.
        assertFalse(actual);
        assertTrue(actual1);
        assertTrue(actual2);
        assertFalse(actual3);
    }

    @Test
    public void testUnlockingBear() {
        // Initialise character button.
        CharacterButton bearButton = new CharacterButton("test", "test", 0, 0, 0, 0);

        // Get the actual values from the locked button.
        boolean actual = bearButton.isBearUnlocked(4);
        boolean actual1 = bearButton.isBearUnlocked(9);
        boolean actual2 = bearButton.isBearUnlocked(15);
        boolean actual3 = bearButton.isBearUnlocked(-9);

        // Check if the acutual values are true or false.
        assertFalse(actual);
        assertTrue(actual1);
        assertTrue(actual2);
        assertFalse(actual3);
    }

    @Test
    public void testUnlockingRedPanda() {
        // Initialise character button.
        CharacterButton redPandaButton = new CharacterButton("test", "test", 0, 0, 0, 0);

        // Get the actual values from the locked button.
        boolean actual = redPandaButton.isPandaUnlocked(9);
        boolean actual1 = redPandaButton.isPandaUnlocked(12);
        boolean actual2 = redPandaButton.isPandaUnlocked(18);
        boolean actual3 = redPandaButton.isPandaUnlocked(-9);

        // Check if the acutual values are true or false.
        assertFalse(actual);
        assertTrue(actual1);
        assertTrue(actual2);
        assertFalse(actual3);
    }

    @Test
    public void testUnlockingArcticFox() {
        // Initialise character button.
        CharacterButton arcticFoxButton = new CharacterButton("test", "test", 0, 0, 0, 0);

        // Get the actual values from the locked button.
        boolean actual = arcticFoxButton.isFoxUnlocked(12);
        boolean actual1 = arcticFoxButton.isFoxUnlocked(15);
        boolean actual2 = arcticFoxButton.isFoxUnlocked(20);
        boolean actual3 = arcticFoxButton.isFoxUnlocked(-9);

        // Check if the acutual values are true or false.
        assertFalse(actual);
        assertTrue(actual1);
        assertTrue(actual2);
        assertFalse(actual3);
    }

    @Test
    public void testUnlockingLeopard() {
        // Initialise character button.
        CharacterButton leopardButton = new CharacterButton("test", "test", 0, 0, 0, 0);

        // Get the actual values from the locked button.
        boolean actual = leopardButton.isLeopardUnlocked(17);
        boolean actual1 = leopardButton.isLeopardUnlocked(18);
        boolean actual2 = leopardButton.isLeopardUnlocked(22);
        boolean actual3 = leopardButton.isLeopardUnlocked(-9);

        // Check if the acutual values are true or false.
        assertFalse(actual);
        assertTrue(actual1);
        assertTrue(actual2);
        assertFalse(actual3);
    }

    @Test
    public void testAllCharactersLocked() {
        // Initialise character buttons.
        CharacterButton elephantButton = new CharacterButton("test", "test", 0, 0, 0, 0);
        CharacterButton lemurButton = new CharacterButton("test", "test", 0, 0, 0, 0);
        CharacterButton bearButton = new CharacterButton("test", "test", 0, 0, 0, 0);
        CharacterButton redPandaButton = new CharacterButton("test", "test", 0, 0, 0, 0);
        CharacterButton arcticFoxButton = new CharacterButton("test", "test", 0, 0, 0, 0);
        CharacterButton leopardButton = new CharacterButton("test", "test", 0, 0, 0, 0);

        // Variable to store the minimum score for the completed checkpoint.
        int minScore = 0;

        // Get the actual values from the locked buttons.
        boolean actual = elephantButton.isElephantUnlocked(minScore);
        boolean actual1 = lemurButton.isLemmurUnlocked(minScore);
        boolean actual2 = bearButton.isBearUnlocked(minScore);
        boolean actual3 = redPandaButton.isPandaUnlocked(minScore);
        boolean actual4 = arcticFoxButton.isFoxUnlocked(minScore);
        boolean actual5 = leopardButton.isLeopardUnlocked(minScore);

        // Check if the acutual values are true or false.
        assertFalse(actual);
        assertFalse(actual1);
        assertFalse(actual2);
        assertFalse(actual3);
        assertFalse(actual4);
        assertFalse(actual5);
    }
}
