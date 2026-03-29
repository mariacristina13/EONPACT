package Sprites;

import constants.Constants;

public class Player extends Sprite {//Class represents a player in the game
	public String name;
	public boolean jump=false;
 public int direction;//-1left , 0 none ,+1 right
 private int verticalSpeed;
  //Constructor
    public Player(String fileName , int x, int y , int width, int height) {
    	super(fileName, x,y,width,height);
    	direction=0;
    	verticalSpeed=0;
	}
    
    public void update(){
    	if(direction==1) {
    		moveRight();
    	}
    	else if(direction==-1) {
    		moveLeft();
    	}
    	if(getY()<Constants.GROUND_HEIGHT - getHeight()){//if player is above ground
    		setY( getY() +Constants.PLAYER_FALL_SPEED);//change position to fall
    	}
    	else {
    		if(getY()>=Constants.GROUND_HEIGHT - getHeight()) {
    			setY(Constants.GROUND_HEIGHT - getHeight());
    			jump=false;//reset jump
    		}
    	}
    	}
  //jump
	public void jump() {
	if(jump==false) {
		setY(getY()-Constants.PLAYER_JUMP_HEIGHT);
		jump=true;
	}
	}
  //Move Left
  	public int moveLeft() {
  		int newX=getX()-Constants.PLAYER_SPEED;
  		if(newX<0) {newX=0;}//Prevents user going off board
  		setX(newX);
  		return getX();
  		}
  //Move right
  	public int moveRight(){
		if(getX() + getWidth() + Constants.PLAYER_SPEED < Constants.SCREEN_SIZE.width/2) {
			this.setX(getX() +Constants.PLAYER_SPEED);
		}
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
