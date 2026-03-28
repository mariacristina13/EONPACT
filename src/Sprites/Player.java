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
    	if(direction==1) {
    		moveRight();
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
