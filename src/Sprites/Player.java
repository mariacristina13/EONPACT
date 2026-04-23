package Sprites;

import java.util.ArrayList;

import constants.Constants;

// Class represents a player in the game.
public class Player extends Sprite {
	// Class variables.
	public String name;
	public boolean jump;
	public int jumpCount;
	// Direction: -1 for left , 0 for none ,+1 for right
	public int direction;

	// Constructor to initialise the class variables.
	public Player(String fileName, int x, int y, int width, int height) {
		super(fileName, x, y, width, height);
		direction = 0;
	}

	// Update the player's position based on the number in the direction variable.
	public void update(ArrayList<Map> map) {
		if (direction == 1) {
			moveRight();
		} else if (direction == -1) {
			moveLeft();
		}

		// Change position to fall.
		setY(getY() + Constants.PLAYER_FALL_SPEED);

		if (tileLanding(map)) {
			return;
		}

		if (getY() >= Constants.GROUND_HEIGHT - getHeight()) {
			setY(Constants.GROUND_HEIGHT - getHeight());
			// Reset jump.
			jump = false;
			// Reset jump count.
			jumpCount = 0;
		}

	}

	// Method that handles the jump.
	public void jump() {
		if (jumpCount < Constants.MAX_JUMPS) {// If the count is less than the jumps allowed,
			setY(getY() - Constants.PLAYER_JUMP_HEIGHT);// y becomes current y - how high the player should jump and
			jumpCount++;// count increases.
			jump = true;
		}
	}

	// Move Left
	public int moveLeft() {
		int newX = getX() - Constants.PLAYER_SPEED;
		if (newX < 0) {// Prevents user going off the board.
			newX = 0;
		}
		setX(newX);
		return getX();
	}

	// Move right
	public int moveRight() {
		int newX = getX() + Constants.PLAYER_SPEED;// Calculate the new position.
		if (newX + getWidth() > Constants.SCREEN_WIDTH) {// Prevents the player from going off the background.
			newX = Constants.SCREEN_WIDTH - getWidth();// Stop the players at the background end.
		}
		// Set the x position.
		setX(newX);
		// Return the x position.
		return getX();
	}

	// Platform Landing
	public boolean tileLanding(ArrayList<Map> map) {
		for (int i = 0; i < map.size(); i++) {
			Map tile = map.get(i);// get a platform
			boolean overlapX = getX() + getWidth() >= tile.getX() + 100 && // Player right side at the left side of
																			// platform.
					getX() <= tile.getX() + tile.getWidth() - 100;// Left side of the player is before the right side of
																	// platform (passed the platform completely).

			if (!overlapX) {
				continue;
			}

			int tileTop = tile.getY() + Constants.TILE_HEIGHT;// Actual tile top.
			int playerBottom = getY() + getHeight() + getHeight();// Players feet.

			// Check if the clayer lands on the platform.
			boolean landing = playerBottom >= tileTop &&
					playerBottom <= tileTop + Constants.PLAYER_FALL_SPEED;

			if (landing) {
				setY(tileTop - getHeight() - getHeight() - Constants.PLAYER_FALL_SPEED);
				jump = false;
				jumpCount = 0;// Allows player to jump again.
				return true;
			}

		}
		return false;
	}
	
	//the player is on the right side of the log , log is moving left
	public boolean pushLeft(Map tile) {
		boolean touchingRight= getX() <= tile.getX()+tile.getWidth()&&//player gone past the tiles right boundary
				               getX()>=tile.getX()+tile.getWidth()-Constants.PLAYER_SPEED;//is the player close enough to the right edge
	    boolean overlap = getY()+getHeight()>tile.getY()&&//the bottom of the player below the top of the log
	    		          getY()<tile.getY()+Constants.TILE_HEIGHT;//top of the player above the bottom of the tile  
	
	    return touchingRight && overlap&&direction==-1;
	}

	
	
	// JumpCount getter
	public int getJumpCount() {
		return jumpCount;
	}

	// Direction: Getter and Setter
	public int getDirection() {
		return direction;
	}

	public void setDirection(int direction) {
		this.direction = direction;
	}

}
