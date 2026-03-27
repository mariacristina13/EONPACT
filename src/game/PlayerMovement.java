package game;

public class PlayerMovement {
	public int x ;//Left or Right
    public int y ;//Up or Down
  //Constructor
    public PlayerMovement(int x, int y) {
		this.x=x;
		this.y=y;
	}
 //Getter and setter X  
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
//Getter and setter Y
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
//Move up
	public int moveUp() {
    	return y-1;
    }
//Move Down
	public int moveDown() {
		return y+1;
	}
//Move Left
	public int moveLeft() {
			return x-1 ;
		}
//Move right
	public int moveRight() {
			return x+1;
		}
}
