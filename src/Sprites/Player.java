package Sprites;

public class Player extends Sprite {//Class represents a player in the game
	public String name;

  //Constructor
    public Player(String fileName , int x, int y , int width, int height) {
    	super(fileName, x,y,width,height);
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
}
