package Sprites;

import constants.Constants;

public class Player extends Sprite {//Class represents a player in the game
	public String name;
	public boolean jump=false;
 public int direction;//-1left , 0 none ,+1 right
  //Constructor
    public Player(String fileName , int x, int y , int width, int height) {
    	super(fileName, x,y,width,height);
    	direction=0;
	}
    
    public void update(){
		if(direction == 1)
			moveRight();
		else if(direction == -1)
			moveLeft();
		if(getY() < Constants.GROUND_HEIGHT - getHeight()) {//if the player is above ground 
			setY( getY() +Constants.PLAYER_FALL_SPEED);//pull the player down
		}
		else
		{
			if(jump == true)//if jump
				setY(Constants.GROUND_HEIGHT  - getHeight());//Player returns to the ground
			jump = false;//jump false
		}	
	}
  //jump
	public void jump() {
	setY(getY()-Constants.PLAYER_JUMP_HEIGHT);
	}
  //Move Left
  	public int moveLeft() {
  		setX(getX()-1);
  		return getX();
  		}
  //Move right
  	public int moveRight() {
  		setX(getX()+1);
  		return getX();
  		}
  	//Direction:Getter and Setter
  	public int getDirection() {
		return direction;
	}
	public void setDirection(int direction) {
		this.direction = direction;
	}
}
