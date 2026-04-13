package griffith;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Test;

import Sprites.Food;
import Sprites.Map;
import Sprites.Player;
import constants.Constants;
import game.GameManager;

public class TestPlayerMovement {
    ArrayList<Map> map = new ArrayList<>();

    // PlayerMovement class Test
    @Test
    public void testPlayerMoveLeft() {
        Player player = new Player(null, 50, 3, 10, 10);
        int actual = player.moveLeft();
        int expected = 50 - Constants.PLAYER_SPEED;
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
    public void testPlayerJump() {
        Player player = new Player(null, 20, 40, 0, 0);
        player.jump();
        assertEquals(40 - Constants.PLAYER_JUMP_HEIGHT, player.getY());
    }

    @Test
    public void testDoubleJump() {
        Player player = new Player(null, 50, 40, 0, 0);
        player.jump();// First jump
        int afterFirst = player.getY();// Position of y after first jump
        player.jump();// Second jump
        int afterSecond = player.getY();// Position of y after second jump
        assertTrue(afterSecond < afterFirst);// Checks if the second jump made the player go higher than the first jump
    }

    @Test
    public void testResetJumpAfterLanding() {
        Player player = new Player(null, 50, 40, 0, 0);
        player.jump();// First jump
        player.jump();// Second jump
        while (player.getY() < Constants.GROUND_HEIGHT - player.getHeight()) {// While the player is above the ground
            player.update(map);// Reset the jump count
        }
        int groundY = player.getY();// value of y before jumping again
        player.jump();// Player jumps again
        assertTrue(player.getY() < groundY);// checks if the jump works again
    }

    @Test
    public void testCollisionWithFood() {
        GameManager game = new GameManager();
        Player player = new Player(null, 50, 40, 30, 40);
        Food food = new Food(null, 70, 70, 50, 50);
        game.checkCollision(player, food);
        assertTrue(food.isCollected());
    }

    @Test
    public void testPlayerLandingOnPlatform() {
        Map tile = new Map(null, 100, 200, 300, 220);
        map.add(tile);
        int tileTop = tile.getY() + Constants.TILE_HEIGHT;// 200
        Player player = new Player(null, 105, 150, 100, 40);// Y=110
        for (int i = 0; i < 10; i++) {
            player.update(map); // getY()=90 each iteration
        }

        int expected = tileTop - player.getHeight() - player.getHeight() - Constants.PLAYER_FALL_SPEED;// 90
        assertEquals(expected, player.getY());
        assertFalse(player.jump);
        assertEquals(0, player.getJumpCount());
    }

    @Test
    public void testUpdateDirectionIs1() {
        Player player = new Player(null, 20, 40, 0, 0);
        player.setDirection(1);
        player.update(map);
        int expected = 20 + Constants.PLAYER_SPEED;
        assertEquals(expected, player.getX());
    }

    @Test
    public void testUpdateDirection() {
        Player player = new Player(null, 20, 40, 0, 0);
        player.setDirection(-1);
        player.update(map);
        int expected = 20 - Constants.PLAYER_SPEED;
        assertEquals(expected, player.getX());
    }

    @Test
    public void testPlayerFallsInAir() {
        Player player = new Player(null, 50, 40, 0, 0);
        int beforeY = player.getY();// players current position
        player.update(map);
        assertEquals(beforeY + Constants.PLAYER_FALL_SPEED, player.getY());
    }
}