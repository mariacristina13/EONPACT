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
    	return 0;
    }
	//Move Down
	public int moveDown() {
		return 0;
	}
	//Move Left
	public int moveLeft() {
			return 0;
		}
	//Move right
	public int moveRight() {
			return 0;
		}
}
