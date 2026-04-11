package Sprites;

import java.util.ArrayList;

import constants.Constants;

public class Player extends Sprite {// Class represents a player in the game
	public String name;
	public boolean jump;
	public int jumpCount;
	public int direction;// -1left , 0 none ,+1 right

	// Constructor
	public Player(String fileName, int x, int y, int width, int height) {
		super(fileName, x, y, width, height);
		direction = 0;
	}

	public void update(ArrayList<Map> map) {
		if (direction == 1) {
			moveRight();
		} else if (direction == -1) {
			moveLeft();
		}	
		if (getY() < Constants.GROUND_HEIGHT - getHeight()) {// if player is above ground
			setY(getY() + Constants.PLAYER_FALL_SPEED);// change position to fall
		}
		if(tileLanding(map)) {
			return; 
		}
			if (getY() >= Constants.GROUND_HEIGHT - getHeight()) {
				setY(Constants.GROUND_HEIGHT - getHeight());
				jump = false;// reset jump
				jumpCount=0;//Reset jumpcount
			}
			
		}
	

	// jump
	public void jump() {
		if (jumpCount<Constants.MAX_JUMPS) {//If the count is less than the jumps allowed
			setY(getY() - Constants.PLAYER_JUMP_HEIGHT);//Y becomes current y - how high the player should jump
			jumpCount++;//count increases
			jump = true;
		}
	}

	// Move Left
	public int moveLeft() {
		int newX = getX() - Constants.PLAYER_SPEED;
		if (newX < 0) {// Prevents user going off board
			newX = 0;
		}		
		setX(newX);
		return getX();
	}

	// Move right
	public int moveRight() {
		int newX=getX()+Constants.PLAYER_SPEED;//new position
		if (newX + getWidth()> Constants.SCREEN_WIDTH) {//Prevents going off the background
			newX=Constants.SCREEN_WIDTH - getWidth();//Stop at the background
		}
		setX(newX);
		return getX();
	}
	
	//Platform Landing
	public boolean tileLanding(ArrayList<Map> map) {
	for(int i=0;i<map.size();i++) {
		Map tile=map.get(i);//get a platform
		boolean overlapX=getX()+getWidth()>tile.getX()&&//Player right side at the left side of platform
				getX()<tile.getX()+tile.getWidth();//Left side of the player is before the right side of platform(passed the platform completely)
		if(!overlapX) {
			continue;//if player is not above platform ignore it
		}
		int tileTop=tile.getY()+Constants.TILE_HEIGHT;//Actual tile top
		int playerBottom=getY()+getHeight()+getHeight();//Players feet
		int nextPlayerBottom =playerBottom+Constants.PLAYER_FALL_SPEED;//Players next feet position
		System.out.println("TileTop=" + tileTop + " playerBottom=" + playerBottom + " nextBottom=" + nextPlayerBottom);
		boolean landing = playerBottom>= tileTop &&
		                  playerBottom <= tileTop+Constants.PLAYER_FALL_SPEED;
	      if(landing) {
		                	  setY(tileTop-getHeight()-getHeight()-Constants.PLAYER_FALL_SPEED);
		                	  jump=false;
		                		jumpCount=0;//Allows player to jump again
		                		return true;
		                  }
	      
	}
	return false;
	}
	
 //JumpCount getter
	public int getJumpCount() {
		return jumpCount;
	}

	// Direction:Getter and Setter
	public int getDirection() {
		return direction;
	}
	public void setDirection(int direction) {
		this.direction = direction;
	}
	
}
