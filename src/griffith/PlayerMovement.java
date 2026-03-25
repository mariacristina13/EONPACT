package griffith;

public class PlayerMovement extends Player {
	public int x ;
    public int y ;
    //Constructor
    public PlayerMovement(String name, int x, int y) {
		super(name);
		this.x=x;
		this.y=y;
	}
    
	public int getX() {
		return 0;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return 0;
	}

	public void setY(int y) {
		this.y = y;
	}
	//Move up
	public int moveUp(int x,int y) {
    	return 0;
    }
}
