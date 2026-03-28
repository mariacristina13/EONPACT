package Sprites;

public class Player extends Sprite {//Class represents a player in the game
	public String name;
 public int direction;
  //Constructor
    public Player(String fileName , int x, int y , int width, int height) {
    	super(fileName, x,y,width,height);
    	direction=0;
	}
  
	//Move up
  	public int moveUp() {
  	setY(getY()-1);
  		return getY();
      }
  //Move Down
  	public int moveDown() {
  		setY(getY()+1);
  		return getY();
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
