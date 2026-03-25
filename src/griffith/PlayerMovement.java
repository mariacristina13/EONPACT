package griffith;

public class PlayerMovement extends Player {
	public int x ;//Left or Right
    public int y ;//Up or Down
    //Constructor
    public PlayerMovement(String name, int x, int y) {
		super(name);
		this.x=x;
		this.y=y;
	}
    
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

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
		y=y+1;
		return y;
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
