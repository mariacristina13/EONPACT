package griffith;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import Sprites.Food;
import Sprites.Player;
import constants.Constants;
import game.GameManager;

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
    public void testPlayerJump() {
        Player player = new Player(null, 20, 40, 0, 0);
        player.jump();
        assertEquals(40 - Constants.PLAYER_JUMP_HEIGHT, player.getY());
    }
    @Test
    public void testDoubleJump() {
    	Player player = new Player(null, 50, 40, 0, 0);
    	player.jump();//First jump
    	int afterFirst=player.getY();//Position of y after first jump
    	player.jump();//Second jump
    	int afterSecond=player.getY();//Position of y after second jump
    	assertTrue(afterSecond<afterFirst);//Checks if the second jump made the player go higher than the first jump
    }
    @Test
    public void testResetJumpAfterLanding() {
    	Player player = new Player(null, 50, 40, 0, 0);
    	player.jump();//First jump
    	player.jump();//Second jump
    	while(player.getY() < Constants.GROUND_HEIGHT - player.getHeight()) {//While the player is above the ground
    		player.update();//Reset the jump count
    	}
    	int groundY=player.getY();//value of y before jumping again
    	player.jump();//Player jumps again
    	assertTrue(player.getY()<groundY);//checks if the jump works again
    }
@Test
public void testCollisionWithFood() {
	GameManager game=new GameManager();
	Player player = new Player(null, 50, 40, 30, 40);
	Food food=new Food(null,70,70,50,50);
	game.checkCollision(player,food);
	assertTrue(food.isCollected());
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
