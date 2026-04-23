package griffith;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import game.CharacterButton;

public class TestUnlockingCharacters {
    @Test
    public void testUnlockingElephant(){
        CharacterButton elephantButton = new CharacterButton("test", "test", 0, 0, 0, 0);

        boolean actual = elephantButton.isElephantUnlocked(2);
        boolean actual1 = elephantButton.isElephantUnlocked(3);
        boolean actual2 = elephantButton.isElephantUnlocked(5);
        boolean actual3 = elephantButton.isElephantUnlocked(-9);

        assertFalse(actual);
        assertTrue(actual1);
        assertTrue(actual2);
        assertFalse(actual3);
    }

    @Test
    public void testUnlockingLemur(){
        CharacterButton lemurButton = new CharacterButton("test", "test", 0, 0, 0, 0);

        boolean actual = lemurButton.isLemmurUnlocked(4);
        boolean actual1 = lemurButton.isLemmurUnlocked(6);
        boolean actual2 = lemurButton.isLemmurUnlocked(12);
        boolean actual3 = lemurButton.isLemmurUnlocked(-9);

        assertFalse(actual);
        assertTrue(actual1);
        assertTrue(actual2);
        assertFalse(actual3);
    }

    @Test
    public void testUnlockingBear(){
        CharacterButton bearButton = new CharacterButton("test", "test", 0, 0, 0, 0);

        boolean actual = bearButton.isBearUnlocked(4);
        boolean actual1 = bearButton.isBearUnlocked(9);
        boolean actual2 = bearButton.isBearUnlocked(15);
        boolean actual3 = bearButton.isBearUnlocked(-9);

        assertFalse(actual);
        assertTrue(actual1);
        assertTrue(actual2);
        assertFalse(actual3);
    }

    @Test
    public void testUnlockingRedPanda(){
        CharacterButton redPandaButton = new CharacterButton("test", "test", 0, 0, 0, 0);

        boolean actual = redPandaButton.isPandaUnlocked(9);
        boolean actual1 = redPandaButton.isPandaUnlocked(12);
        boolean actual2 = redPandaButton.isPandaUnlocked(18);
        boolean actual3 = redPandaButton.isPandaUnlocked(-9);

        assertFalse(actual);
        assertTrue(actual1);
        assertTrue(actual2);
        assertFalse(actual3);
    }

    @Test
    public void testUnlockingArcticFox(){
        CharacterButton arcticFoxButton = new CharacterButton("test", "test", 0, 0, 0, 0);

        boolean actual = arcticFoxButton.isFoxUnlocked(12);
        boolean actual1 = arcticFoxButton.isFoxUnlocked(15);
        boolean actual2 = arcticFoxButton.isFoxUnlocked(20);
        boolean actual3 = arcticFoxButton.isFoxUnlocked(-9);

        assertFalse(actual);
        assertTrue(actual1);
        assertTrue(actual2);
        assertFalse(actual3);
    }

    @Test
    public void testUnlockingLeopard(){
        CharacterButton leopardButton = new CharacterButton("test", "test", 0, 0, 0, 0);

        boolean actual = leopardButton.isLeopardUnlocked(17);
        boolean actual1 = leopardButton.isLeopardUnlocked(18);
        boolean actual2 = leopardButton.isLeopardUnlocked(22);
        boolean actual3 = leopardButton.isLeopardUnlocked(-9);

        assertFalse(actual);
        assertTrue(actual1);
        assertTrue(actual2);
        assertFalse(actual3);
    }

    @Test
    public void testAllCharactersLocked(){}
}
