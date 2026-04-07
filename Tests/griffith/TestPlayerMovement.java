package griffith;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import Sprites.Player;
import constants.Constants;

public class TestPlayerMovement {
    // PlayerMovement class Test
    @Test
    public void testPlayerMoveLeft() {
        Player player = new Player(null, 5, 3, 0, 0);
        int actual = player.moveLeft();
        int expected = 5 - Constants.PLAYER_SPEED;
        assertEquals(expected, actual);
    }

    @Test
    public void testPlayerMoveRight() {
        Player player = new Player(null, 6, 6, 0, 0);
        int actual = player.moveRight();
        int expected = 6 + Constants.PLAYER_SPEED;
        assertEquals(expected, actual);
    }

    @Test
    public void testPlayerDoubleJump() {
        Player player = new Player(null, 20, 40, 0, 0);
        int start=player.getY();//Before jumping
        player.jump();
        assertEquals(40 - Constants.PLAYER_JUMP_HEIGHT, player.getY());
    }

    @Test
    public void testUpdateDirectionIs1() {
        Player player = new Player(null, 20, 40, 0, 0);
        player.setDirection(1);
        player.update();
        int expected = 20 + Constants.PLAYER_SPEED;
        assertEquals(expected, player.getX());
    }

    @Test
    public void testUpdateDirection() {
        Player player = new Player(null, 20, 40, 0, 0);
        player.setDirection(-1);
        player.update();
        int expected = 20 - Constants.PLAYER_SPEED;
        assertEquals(expected, player.getX());
    }

    @Test
    public void testPlayerFallsInAir() {
        Player player = new Player(null, 50, 40, 0, 0);
        int beforeY = player.getY();// players current position
        player.update();
        assertEquals(beforeY + Constants.PLAYER_FALL_SPEED, player.getY());
    }
}
